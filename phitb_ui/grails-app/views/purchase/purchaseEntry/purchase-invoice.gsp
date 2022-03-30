<%@ page import="phitb_ui.SalesService; java.text.SimpleDateFormat" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Purchase Invoice</title>

    <script type="text/javascript">
        function generateBarCode() {
            var nric = $('#text').val();
            var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';
            $('#barcode').attr('src', url);
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
            margin-left: 208px !important;
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
%{--                                                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG0AAAArCAMAAABFJ/YVAAAAD1BMVEUAIgAFKwAIKQAJLgD///9auxmhAAAAAWJLR0QAiAUdSAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAZFJREFUWIXtlttywyAMRN1Z/f83t1N0WYHwJTFuH8IkwlZknwjEwiatAfJA2wz2CO5D+9D+Iw3e/oo2QYNshNavLtwT2izRW2nYibxMK1pPw+00/rWjHVXKrTSJ7DQQom69aZdunIbO314E/zbT0yw40WyModWjf2yo4Wys836kYaRxD3KF0fv6lp4fVoAOpd6MNDlBkwu0Nj7v0dgc0WgYC1p8wDQtDbHeQ2ieB1WmRYCSFjnHKHS14TQwzWvyEi1XT8xS1H3QUlyjddN1QOtG2In0jBTPN09Pk5dobiiipqGjCVcJLtBkTkNJg88MaQZXBdNIB1BrSaxa15JMo4uAR5Gn0dqhuU4mmqR5G9Tv7rYhFqatwsU0m8RQ5fU03d4Wwn5psYU9QUvbzUrYD83WgmdYNj4WIHk46BTtYkH6RiC24vJCqyVUabG/yTQ1MXkg0cs410N3fdW5yRmYZpUtaYILLEx2ipdtSUrmNEQP8kxyi9kdabS17af2dtvoVLIc1lT5KVg6cy2HtTPXQyyRb24URU7+XmydAAAAAElFTkSuQmCC"/>--}%
%{--            <br><br>--}%
            <b>${entity.entityName}</b><br>
            <sub>${entity.addressLine1}<br>${entity.addressLine2}</sub>
        </td>
        <td style="width: 16%;vertical-align:top;"><b>Bill to Supplier Address :(1012700)</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}<br>${customer.addressLine2}
            </sub>
        </td>
        <td style="width: 16%;vertical-align:top;"><b>Ship to Supplier Address :(1012700)</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}<br>${customer.addressLine2}
            </sub>
        </td>

        <td style="width: 25%;vertical-align:top;">
            <strong>PURCHASE INVOICE (Future ${customer.entityName})</strong>
            <ul style="margin: 0;">

                <li><b class="tab">Invoice No</b>: ${invoiceNumber}</li>
                <li><b class="tab" >Inv Date</b>:<span id="invDate"></span></li>
                <li><b class="tab">GR/PR No.</b>:</li>
                <li><b class="tab">GR/PR Date</b>:</li>
                <li><b class="tab">No of cases</b>:</li>
                <li><b class="tab">Weight in Kgs</b>:</li>
                <li><b class="tab">Party Ref No.</b>: </li>
                <li><b class="tab">Rev-Charge</b>:</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td style="width: 25%;vertical-align:top;">
            <ul>
                <li><b class="tab">Location</b>: ${customer.cityId}</li>
                <li><b class="tab">Phone</b>: ${customer.phoneNumber}</li>
                <li><b class="tab">GST No</b>: ${customer.gstn}</li>
                <li><b class="tab">FAX No</b>: ${customer.faxNumber}</li>
                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                <li><b class="tab">Food Lic. No.</b>:  ${customer.foodLicence1}</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <ul>
                <li><b class="tab">DELIVERY AT</b>: CHICKMAGALUR</li>
                <li><b class="tab">GST NO</b>: ${customer.gstn}</li>
                <li><b class="tab">PAN</b>: ${customer.pan}</li>
                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                <li><b class="tab">STATE NAME</b>: Karnataka</li>
                <li><b class="tab">Goods Through</b>:</li>
                <li><b class="tab">Place of Supply</b>: CHICKMAGALUR</li>
                <li><b class="tab">State Code</b>: 29</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <ul>
                <li><b class="tab">DELIVERY AT</b>: CHICKMAGALUR</li>
                <li><b class="tab">GST NO</b>: ${customer.gstn}</li>
                <li><b class="tab">PAN</b>: ${customer.pan}</li>
                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                <li><b class="tab">STATE NAME</b>: Karnataka</li>
                <li><b class="tab">Goods Through</b>:</li>
                <li><b class="tab">Place of Supply</b>: CHICKMAGALUR</li>
                <li><b class="tab">State Code</b>: 29</li>
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
                 width: 40%;"/>
        </td>
    </tr>

</table>
<table style="width:1308px;table-layout: auto;">
    <tr>
        <th>Material HSN Code</th>
        <th>Material Description</th>
        <th>Pack</th>
        <th>C</th>
        <th>Mfg Name/ Batch</th>
        <th>Mfg Date/ Exp Date</th>
        <th>Mfg Date/ Use Before</th>
        <th>MRP</th>
        <th>PTR</th>
        <th>PTS</th>
        <th>QTY</th>
        <th>Amount</th>
        <th>Disc.Amt/Disc.%</th>
        <th>Amount/CGST%</th>
        <th>Amount/SGST%</th>
        <th>Amount/IGST%</th>
        <th>Net Amount</th>
    </tr>
<g:each var="sp" in="${purchaseProductDetails}">
    <tr>
        <td>${sp.productId.hsnCode}</td>
        <td><b>${sp.productId.productName}</b></td>
        <td>${sp.unitPacking}</td>
        <td>D</td>
        <td>${sp.batchNumber}</td>
        <td></td>
        <td></td>
        <td>${sp.mrp}</td>
        <td>${sp.productId.ptr}</td>
        <td></td>
        <td>${sp.repQty}</td>
        <td>${sp.amount}</td>
        <td>${sp.discount}</td>
        <td>${sp.cgstAmount}</td>
        <td>${sp.sgstAmount}</td>
        <td>${sp.igstAmount}</td>
        <td></td>
    </tr>
</g:each>
    <tr>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td class="hide"></td>
        <td><b>Total</b></td>
        <td>${total}</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>

<div class="container" style="display: flex; height: 200px;">
    <div style="width: 50%;">
        <p>For Pymt thru:<br>
            DD/Chq should be made in favour of MANKIND PHARMA LTD .Payable At New Delhi.<br> Online mode: Virtual A/C "15411012700"
        should be used. Bank Name:" CITI BANK LTD" IFSC Code: "CITI0000002 Account Type: CURRENT Payment not released before due date of this Invoice will incur Interest 14 % p.a. Any Payment Stockissue to any person under whatsoever
        Context/reason without the permission of the Co. is not bound on us.
        GOODS SUPPLIED AGAINST THISINVOICE DO NOT CONTRAVENE THE PROVISION OF SECTION 18 OF THE DRUG ANDCOSMETIC ACT 1940. DUE DATE: 31.12.2021 CHEQUE No. :
        Los Cr. Nt
        Add Debit Nt Ade Rounding Off
        Net Payable Amt
        For payment through UPI mode,kindly visit: <a href="www.aiocdawacs.com">www.aiocdawacs.com</a></p>
    </div>

%{--    <div style="float: right;">--}%
%{--        <table class="print" style="margin-top: 10px;width: 100%;">--}%
%{--            <tr>--}%
%{--                <th>Total</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>10977.43</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Add CGST 6 % on</th>--}%
%{--                <td>10977.43</td>--}%
%{--                <td>658.65</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Add SGST 6 % on</th>--}%
%{--                <td>10977.43</td>--}%
%{--                <td>658.65</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Net Invoice Amt.</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>12294.73</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Less Cr. Nt*</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>0.00</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Add Debit Nt*</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>0.00</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Add Rounding off*</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>0.27</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Net Payable Amt.</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>12295.00</td>--}%
%{--            </tr>--}%
%{--        </table>--}%
%{--    </div>--}%
</div>
<br>
<br>
<p class="signatory" style="float: right;margin-right: 24px;">Authorized Signatory</p>

<p style="float: left;margin-right: 24px;"><b>Printed By:</b> Name</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>
</body>
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>

<script>
    window.onload = function () {
        var d = new Date().toLocaleTimeString();
        document.getElementById("date").innerHTML = d;
        var invDate = new Date('${purchaseBillDetail.entryDate}')
        $("#invDate").text(moment(invDate).format('DD-MM-YYYY'));


    }
</script>
</html>
