<%@ page import="phitb_ui.WordsToNumbersUtil; phitb_ui.UtilsService; java.time.ZonedDateTime" contentType="text/html;charset=UTF-8" %>
<html id="reciptPrint">
<head>
    <title>Print Credit Debit Settlement</title>

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
        .print-watermark {
            position: fixed;
            z-index: -1 !important;
            color: lightgrey !important;
            opacity: 0.2 !important;
            font-size: 120px !important;
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

    #watermark {
        position: fixed;
        z-index: -1;
        color: lightgrey;
        opacity: 1;
        font-size: 120px;
    }
    </style>
</head>

<body>
<table style="width:100%;table-layout: auto;">
    <tr>
        <td style="width: 25%;vertical-align:top;">
            <b>${entity.entityName}</b><br>
            <sub>${entity.addressLine1}<br>${entity.addressLine2}</sub>
        </td>
        <td style="width: 26%;vertical-align:top;"><b>Customer :</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}<br>${customer.addressLine2}
            </sub>
        </td>
        <td style="width: 20%;vertical-align:top;">
            <ul style="margin: 0;">
                <li><b class="tab">Settlement No.</b>: ${saleReturnAdjustment.docNo}</li>
                <li><b class="tab">Date</b>: ${saleReturnAdjustment.dateCreated.split("T")[0]}</li>
            </ul>
        </td>
        <td style="width: 20%;vertical-align:top;">
            <input id="text" type="hidden" value="PharmIT" style="Width:20%" onblur='generateBarCode();'/>
            <img id='barcode'
                 src="https://api.qrserver.com/v1/create-qr-code/?data=${saleReturnAdjustment.docNo}&amp;size=100x100"
                 alt=""
                 title="PhramIT"
                 style="display: block;
                 margin-left: auto;
                 margin-right: auto;
                 width: 25%;"/>
        </td>
    </tr>
    <tr><td colspan="4"
            style="text-align: center; align-content: center;vertical-align: middle;"><h2>CREDIT SETTLEMENT</h2>
    </td></tr>
</table>
<table style="width:100%;table-layout: auto;">
    <thead>
    <tr>
        <th>Trans. No.</th>
        <th colspan="2">Amount</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${saleReturnAdjustmentDetails}" var="sd">
        <tr>
            <td>${saleBillDetail.invoiceNumber}</td>
            <td colspan="2">${String.format("%.2f", sd.adjAmount)}</td>
        </tr>
    </g:each>
    <tr>
        <td><strong>TOTAL</strong></td>
        <td colspan="2"><strong>${String.format("%.2f", saleReturnAdjustmentDetails.adjAmount.sum())}</strong></td>
        <g:if test="${saleReturnAdjustment.cancelledDate}">
            <div id="watermark" class="print-watermark">CANCELLED</div>
        </g:if>
    </tr>
    <tr>
        <td style="border-left: none;border-right: none;">
        </td>

        <div style="width: 50%;">
        </div>
        <td style="border-left:none; border-right:none;">
        </td>

        <td style="text-align: right;border-left:none;">
            <p>For, ${entity.entityName}</p>

            <p>&nbsp;</p>

            <p>&nbsp;</p>

            <p>Authorised Signatory</p>
        </td>
    </tr>
    </tbody>
</table>


<br>
<br>


<p style="float: left;margin-right: 24px;"><b>Printed By:</b> ${session.getAttribute("userName")}</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b> <span id="date"></span></p>

</body>
</html>
