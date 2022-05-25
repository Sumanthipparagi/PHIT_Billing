<%@ page import="phitb_ui.WordsToNumbersUtil; phitb_ui.UtilsService" contentType="text/html;charset=UTF-8" %>
<html id="paymentPrint">
<head>
    <title>Payment Voucher</title>

    <script type="text/javascript">
        function generateBarCode() {
            var nric = $('#text').val();
            var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';
            $('#barcode').attr('src', url);
        }
        window.onload = function () {
            var d = new Date().toLocaleTimeString();
            document.getElementById("date").innerHTML = d;
            window.print()
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
        <td style="width: 26%;vertical-align:top;"><b>Payment To :</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}<br>${customer.addressLine2}</sub>
        </td>
        <td  style="width: 15%;vertical-align:top;">
            <ul style="margin: 0;">
                <li><b class="tab">Payment No.</b>: ${payment.paymentId}</li>
                <li><b class="tab">Payment Date </b>:${payment.paymentDate}</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <input id="text" type="hidden" value="PharmIT" style="Width:20%" onblur='generateBarCode();'/>
            <img id='barcode'
                 src="https://api.qrserver.com/v1/create-qr-code/?data=PharmIT&amp;size=100x100"
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
            <p>By Cheque No.: ${payment.chequeNumber} of ${payment.bank.bankName} dated ${payment.date}</p>
            <table >
                <tr>
                    <th>
                        Adj. Doc. No.
                    </th>
                    <th>
                        Ref No.
                    </th>
                    <th>
                        Doc. Date
                    </th>
                    <th>
                        Adj. Amount
                    </th>
                </tr>

                <g:each var="unv" in="${unsettled}">
                    <%

                        def invoiceNumber;
                        def series ="__"
                        def datepart = unv.entryDate.split("T")[0];
                        def month = datepart.split("-")[1];
                        def year = datepart.split("-")[0];
                        def seriesCode = "__";
                        if (unv.billStatus == "DRAFT")
                        {
                            invoiceNumber = unv.entityId+"/DR/S/" + month + year + "/" + series + "/__";
                        }
                        else
                        {
                            invoiceNumber =  unv.entityId+"/S/" + month + year + "/" + series + "/" + unv.id
                        }
                    %>
                    <tr>
                        <td>${invoiceNumber}</td>
                        <td></td>
                        <td>${unv.dateCreated.split("T")[0]}</td>
                        <td>${String.format("%.2f",unv.balance)}</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td colspan="5"><b>${payment.amountPaid}</b></td>
    <tr>

    <% double data = payment.amountPaid
    int value = (int) data;
    System.out.println(value)
    %>
    <tr>
        <td colspan="5">
            <p><strong>${WordsToNumbersUtil.convert(value)} Ruppes Only
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
            <p>&nbsp;</p>
            <p>&nbsp;</p>


            <p>&nbsp;</p>
            <p>Approved By</p>

        </td>

        <td style="border-left:none; border-right:none;">
            <p>&nbsp;</p>


            <p>&nbsp;</p>
            <p>&nbsp;</p>

            <p>Authorized By</p>
        </td>

        <td style="text-align: right;border-left:none;">
            <p>For,${session.getAttribute("entityName").toString()}</p>

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

<p style="float: left;margin-right: 24px;"><b>Printed By:</b> ${session.getAttribute("userName")}</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>
</body>
</html>