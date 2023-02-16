<%@ page import="phitb_ui.WordsToNumbersUtil; phitb_ui.UtilsService; java.time.ZonedDateTime" contentType="text/html;charset=UTF-8" %>
<html id="reciptPrint">
<head>
    <title>Receipt</title>

    <script type="text/javascript">
        function generateBarCode() {
            var nric = $('#text').val();
            var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';
            $('#barcode').attr('src', url);
        }

        window.onload = function () {
            var d = new Date().toLocaleTimeString();
            document.getElementById("date").innerHTML = d;
            window.print();
        }
    </script>

    <style>
    table {
        border-collapse: collapse;
        border: 1px solid black;
        font-family: Roboto, Sans-Serif, serif;
        font-size: 9pt;
    }

    th, td {
        text-align: left;
        padding: 5px;
        border: 1px solid black;
        /*word-break: break-word;*/
        vertical-align: top;
    }

    ul {
        list-style: none;
        display: table;
    }

    li {
        display: table-row;
    }

    .tab {
        display: table-cell;
        padding-right: 1em;
    }

    body {
        font-size: 9pt;
        font-family: Roboto, Sans-Serif, serif;
    }

    .hide {
        visibility: hidden;
        border: none;
    }

    @media print {
        .print-watermark
        {
            position: fixed;
            z-index:-1!important;
            color: lightgrey!important;
            opacity: 0.2!important;
            font-size:120px!important;
            top: 120px;

        }
        .print {
            margin-left: 200px !important;
        }

        .signatory {
            margin-right: 10px !important;
        }
    }

    .print {
        margin-left: 200px;
    }

    #watermark
    {
        position: fixed;
        z-index:-1;
        color: lightgrey;
        opacity: 1;
        font-size:120px;
    }
    </style>
</head>

<body>
<table style="width:100%;table-layout: auto;">
    <tr>

        <td style="width: 25%;vertical-align:top;">
            %{--            <img width="109" height="43"--}%
            %{--                                                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG0AAAArCAMAAABFJ/YVAAAAD1BMVEUAIgAFKwAIKQAJLgD///9auxmhAAAAAWJLR0QAiAUdSAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAZFJREFUWIXtlttywyAMRN1Z/f83t1N0WYHwJTFuH8IkwlZknwjEwiatAfJA2wz2CO5D+9D+Iw3e/oo2QYNshNavLtwT2izRW2nYibxMK1pPw+00/rWjHVXKrTSJ7DQQom69aZdunIbO314E/zbT0yw40WyModWjf2yo4Wys836kYaRxD3KF0fv6lp4fVoAOpd6MNDlBkwu0Nj7v0dgc0WgYC1p8wDQtDbHeQ2ieB1WmRYCSFjnHKHS14TQwzWvyEi1XT8xS1H3QUlyjddN1QOtG2In0jBTPN09Pk5dobiiipqGjCVcJLtBkTkNJg88MaQZXBdNIB1BrSaxa15JMo4uAR5Gn0dqhuU4mmqR5G9Tv7rYhFqatwsU0m8RQ5fU03d4Wwn5psYU9QUvbzUrYD83WgmdYNj4WIHk46BTtYkH6RiC24vJCqyVUabG/yTQ1MXkg0cs410N3fdW5yRmYZpUtaYILLEx2ipdtSUrmNEQP8kxyi9kdabS17af2dtvoVLIc1lT5KVg6cy2HtTPXQyyRb24URU7+XmydAAAAAElFTkSuQmCC"/><br><br>--}%
            <b>${entity.entityName}</b><br>
            <sub>${entity.addressLine1}<br>${entity.addressLine2}</sub>
        </td>
        <td style="width: 26%;vertical-align:top;"><b>Received with Thanks From :</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}<br>${customer.addressLine2}
            </sub>
        </td>
        <td style="width: 15%;vertical-align:top;">
            <ul style="margin: 0;">
                <li><b class="tab">Receipt No.</b>: ${receipt.receiptId}</li>
                <li><b class="tab">Receipt Date</b>:${receipt.date.split("T")[0]}</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <input id="text" type="hidden" value="PharmIT" style="Width:20%" onblur='generateBarCode();'/>
            <img id='barcode'
                 src="https://api.qrserver.com/v1/create-qr-code/?data=${receipt.id}&amp;size=100x100"
                 alt=""
                 title="PhramIT"
                 style="display: block;
                 margin-left: auto;
                 margin-right: auto;
                 width: 25%;"/>
        </td>

    </tr>

</table>
<table style="width:100%;table-layout: auto;">
    <tr>
        <th>Particulars</th>
        <th colspan="2">Amount</th>
    </tr>
    <tr>
        <td>
            <g:if test="${receipt.bank != null && receipt.bank != ""}">
                <p>By Cheque No.: ${receipt.chequeNumber} of ${receipt.bank.bankName} dated ${receipt.paymentDate.split("T")[0]}</p>
            </g:if>
            <g:elseif test="${receipt?.cardNumber != null && receipt?.cardNumber != ""}">
                BY CARD
                <br>
                <br>
            </g:elseif>
            <g:else>
                BY CASH
                <br>
                <br>
            </g:else>
            <table>
                <tr>
                    <th>
                        Adj. Doc. No.
                    </th>
                    <th>
                        Doc Type
                    </th>
                    <th>
                        Doc. Date
                    </th>
                    <th>
                        Adj. Amount
                    </th>
                </tr>
                <g:each var="inv" in="${reciptloginvArray}">

                    <g:if test="${inv.amountPaid != 0}">
                        <tr>
                            <td>${inv.transId}</td>
                            <td>${inv.billType}</td>
                            <td>${inv.dateCreated.split("T")[0]}</td>
                            <td>${inv.amountPaid}</td>
                        </tr>
                    </g:if>
                </g:each>
                <g:each var="crnt" in="${reciptlogcrntArray}">

                    <g:if test="${crnt.amountPaid != 0}">
                        <tr>
                            <td>${crnt.transId}</td>
                            <td>${crnt.billType}</td>
                            <td>${crnt.dateCreated.split("T")[0]}</td>
                            <td>- ${UtilsService.round(crnt.amountPaid, 2)}</td>
                        </tr>
                    </g:if>
                </g:each>

                <g:each var="gtn" in="${reciptloggtnArray}">

                    <g:if test="${gtn.amountPaid != 0}">
                        <tr>
                            <td>${gtn.transId}</td>
                            <td>${gtn.billType}</td>
                            <td>${gtn.dateCreated.split("T")[0]}</td>
                            <td>${UtilsService.round(gtn.amountPaid, 2)}</td>
                        </tr>
                    </g:if>
                </g:each>
            %{--                <g:each var="csv" in="${creditNoteArry}">--}%
            %{--                    <g:if test="${csv.balance!=0}">--}%
            %{--                    <tr>--}%
            %{--                        <td>${csv.invoiceNumber}</td>--}%
            %{--                        <td>CRNT</td>--}%
            %{--                        <td>${csv.dateCreated.split("T")[0]}</td>--}%
            %{--                        <td>${csv.balance}</td>--}%
            %{--                    </tr>--}%
            %{--                    </g:if>--}%
            %{--                </g:each>--}%
            </table>
        </td>

        <%
            double inv = 0
            double crnt = 0
            double gtn = 0

            if(reciptloginvArray.amountPaid.sum()!=null)
            {
                 inv = reciptloginvArray.amountPaid.sum()
            }
            else {
                inv = 0
            }

            if(reciptlogcrntArray.amountPaid.sum()!=null)
            {
                crnt = reciptlogcrntArray.amountPaid.sum()
            }
            else {
                crnt = 0
            }
            if(reciptloggtnArray.amountPaid.sum()!=null)
            {
                gtn = reciptloggtnArray.amountPaid.sum()
            }
            else {
                gtn = 0
            }
        %>
        <td colspan="4"><b>${UtilsService.round(inv+gtn-crnt, 2)}</b><br>
        <g:if test="${paymentLogArray.size()!=0}">
            <p>There is a due of ${UtilsService.round(inv+gtn-Double.parseDouble(paymentLogArray.collectedAmount.sum().toString()), 2)}</p>
        </g:if>

    </td>
    <tr>

        <% double data = inv+gtn-crnt
//        int value = (int) data;
        System.out.println(data)
        %>
    </tr>
    <tr>
        <td colspan="5">
            <p><strong>${WordsToNumbersUtil.convertToIndianCurrency(data.toString())}
            </strong></p>

        </td>
    </tr>

    <tr>
        <td colspan="5">
            <p><strong>Note:</strong>${receipt.narration}
            </p>
        </td>
    </tr>

    <tr>
        <td style="border-left: none;border-right: none;">
            <p><strong><g:if
                    test="${receipt.bank != null && receipt.bank != ""}">Deposit Bank :${receipt.bank.bankName}</g:if></strong>
            </p>

            <p>&nbsp;</p>

            <p>&nbsp;</p>

            <p>Cashier / Accountant</p>
        </td>
        <div style="width: 50%;">
            <g:if test="${receipt.approvedStatus == 'CANCELLED'}">
                <div id="watermark" class="print-watermark">CANCELLED</div>
            </g:if>
        </div>
        <td style="border-left:none; border-right:none;">
            <p>&nbsp;</p>


            <p>&nbsp;</p>

            <p>&nbsp;</p>

            <p>** This receipt is valid Subject to realisation of cheque.**</p>
        </td>

        <td style="text-align: right;border-left:none;">
            <p>For, ${entity.entityName}</p>

            <p>&nbsp;</p>

            <p>&nbsp;</p>

            <p>Authorised Signatory</p>
        </td>
    </tr>

</table>




%{--<div class="container" style="display: flex;">--}%
%{--    <div style="float: left;margin-left: -200px;">--}%
%{--        <table class="print" style="margin-top: 10px;width: 100%;">--}%
%{--            <tr>--}%
%{--                <th>Doc. No.</th>--}%
%{--                <th>Date </th>--}%
%{--                <th>Adj. Amount  </th>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <td>21/TP 53</td>--}%
%{--                <td>-27755.00</td>--}%
%{--                <td>-27755.00</td>--}%
%{--            </tr>--}%

%{--        </table>--}%

%{--    </div>--}%
%{--    <div style="float: right;">--}%
%{--    --}%
%{--    </div>--}%

%{--</div>--}%
<br>
<br>

%{--<p class="signatory" style="float: right;margin-right: 24px;">Authorized Signatory</p>--}%

<p style="float: left;margin-right: 24px;"><b>Printed By:</b>${session.getAttribute("userName")}</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>

</body>
</html>
