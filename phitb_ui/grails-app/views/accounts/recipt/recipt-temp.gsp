<%@ page import="phitb_ui.WordsToNumbersUtil; phitb_ui.UtilsService; java.time.ZonedDateTime" contentType="text/html;charset=UTF-8" %>
<html id="reciptPrint">
<head>
    <title>Goods Sales Recipt</title>

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
    </style>
</head>

<body>
<table style="width:1308px;table-layout: auto;">
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
                <li><b class="tab">Receipt No.</b>: ${recipt.receiptId}</li>
                <li><b class="tab">Receipt Date</b>:${recipt.date.split("T")[0]}</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <input id="text" type="hidden" value="PharmIT" style="Width:20%" onblur='generateBarCode();'/>
            <img id='barcode'
                 src="https://api.qrserver.com/v1/create-qr-code/?data=${recipt.id}&amp;size=100x100"
                 alt=""
                 title="PhramIT"
                 style="display: block;
                 margin-left: auto;
                 margin-right: auto;
                 width: 25%;"/>
        </td>

    </tr>

</table>
<table style="width:1308px;table-layout: auto;">
    <tr>
        <th>Particulars</th>
        <th colspan="5">Amount</th>
    </tr>
    <tr>
        <td>
            <g:if test="${recipt.bank!=null && recipt.bank!=""}">
            <p>By Cheque No.: ${recipt.chequeNumber} of ${recipt.bank.bankName} dated ${recipt.paymentDate.split("T")[0]}</p>
            </g:if>
            <g:elseif test="${recipt?.cardNumber!=null && recipt?.cardNumber!=""}">
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
                <g:each var="sv" in="${settled}">
%{--                    <%--}%

%{--                        def invoiceNumber;--}%
%{--                        def series ="__"--}%
%{--                        def datepart = sv.entryDate.split("T")[0];--}%
%{--                        def month = datepart.split("-")[1];--}%
%{--                        def year = datepart.split("-")[0];--}%
%{--                        def seriesCode = "__";--}%
%{--                        if (sv.billStatus == "DRAFT")--}%
%{--                        {--}%
%{--                            invoiceNumber = sv.entityId+"/DR/S/" + month + year + "/" + series + "/__";--}%
%{--                        }--}%
%{--                        else--}%
%{--                        {--}%
%{--                            invoiceNumber =  sv.entityId+"/S/" + month + year + "/" + series + "/" + sv.id--}%
%{--                        }--}%
%{--%>--}%
                    <tr>
                        <td>${sv.invoiceNumber}</td>
                        <td>INV</td>
                        <td>${sv.dateCreated.split("T")[0]}</td>
                        <td>${sv.balance}</td>
                    </tr>
                </g:each>
                <g:each var="csv" in="${creditNoteArry}">
                    <tr>
                        <td>${csv.invoiceNumber}</td>
                        <td>CRNT</td>
                        <td>${csv.dateCreated.split("T")[0]}</td>
                        <td>${csv.balance}</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td colspan="5"><b>${recipt.amountPaid}</b></td>
    <tr>

        <% double data = recipt.amountPaid
        int value = (int) data;
        System.out.println(value)
        %>
    <tr>
        <td colspan="5">
            <p><strong>${WordsToNumbersUtil.convert(value)} Ruppees Only
            </strong></p>
        </td>
    </tr>

    <tr>
        <td colspan="5">
            <p><strong>Note:
            </strong></p>
        </td>
    </tr>

    <tr>
        <td style="border-left: none;border-right: none;">
            <p><strong><g:if test="${recipt.bank!=null && recipt.bank!=""}">Deposit Bank :${recipt.bank.bankName}</g:if> </strong></p>

            <p>&nbsp;</p>

            <p>&nbsp;</p>

            <p>Cashier / Accountant</p>
        </td>

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
