<%@ page import="phitb_ui.SalesService; java.text.SimpleDateFormat" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Purchase Entry</title>

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
            <b>${supplier.entityName}</b><br>
            <sub>${supplier.addressLine1}<br>${supplier.addressLine2}
            </sub>
        </td>
        <td style="width: 16%;vertical-align:top;"><b>Ship to Supplier Address :(1012700)</b><br>
            <b>${supplier.entityName}</b><br>
            <sub>${supplier.addressLine1}<br>${supplier.addressLine2}
            </sub>
        </td>

        <td style="width: 25%;vertical-align:top;">
            <strong>TAX INVOICE</strong>
            <ul style="margin: 0;">

                <li><b class="tab">Invoice No</b>: ${invoiceNumber}</li>
                <li><b class="tab"  >Inv Date</b>:&nbsp;<span id="invDate"></span></li>
%{--                <li><b class="tab">GR/PR No.</b>:</li>--}%
%{--                <li><b class="tab">GR/PR Date</b>:</li>--}%
%{--                <li><b class="tab">No of cases</b>:</li>--}%
%{--                <li><b class="tab">Weight in Kgs</b>:</li>--}%
%{--                <li><b class="tab">Party Ref No.</b>: 429803</li>--}%
%{--                <li><b class="tab">Rev-Charge</b>: No Dist.Chnl.01</li>--}%
            </ul>
        </td>
    </tr>
    <tr>
        <td style="width: 25%;vertical-align:top;">
            <ul>
                <li><b class="tab">Location</b>: ${city.name}</li>
                <li><b class="tab">Phone</b>: ${entity.phoneNumber}</li>
                <li><b class="tab">GST No</b>: ${entity.gstn}</li>
                <li><b class="tab">FAX No</b>: ${entity.faxNumber}</li>
                <li><b class="tab">DL No1</b>: ${entity.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${entity.drugLicence2}</li>
                <li><b class="tab">Food Lic. No.</b>:  ${entity.foodLicence1}</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <ul>
                <li><b class="tab">DELIVERY AT</b>: ${supcity.name}</li>
                <li><b class="tab">GST NO</b>: ${supplier.gstn}</li>
                <li><b class="tab">PAN</b>: ${supplier.pan}</li>
                <li><b class="tab">DL No1</b>: ${supplier.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${supplier.drugLicence2}</li>
                <li><b class="tab">STATE NAME</b>: ${supcity.state.name}</li>
                <li><b class="tab">Goods Through</b>:</li>
                <li><b class="tab">Place of Supply</b>: ${supcity.name}</li>
                <li><b class="tab">State Code</b>: 29</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <ul>
                <li><b class="tab">DELIVERY AT</b>: ${supcity.name}</li>
                <li><b class="tab">GST NO</b>: ${supplier.gstn}</li>
                <li><b class="tab">PAN</b>: ${supplier.pan}</li>
                <li><b class="tab">DL No1</b>: ${supplier.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${supplier.drugLicence2}</li>
                <li><b class="tab">STATE NAME</b>: ${supcity.state.name}</li>
                <li><b class="tab">Goods Through</b>:</li>
                <li><b class="tab">Place of Supply</b>: ${supcity.name}</li>
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
        <th>Batch</th>
        <th>Exp Date</th>
        %{--        <th>Mfg Date/ Use Before</th>--}%
        <th>MRP</th>
        <th>PTR</th>
        <th>PTS</th>
        <th>QTY</th>
        <th>Scheme</th>
        <th>Amount</th>
        <th>Disc.Amt/Disc.%</th>
        <th>Amt/CGST%</th>
        <th>Amt/SGST%</th>
        <th>Amt/IGST%</th>
        <th>Net Amt</th>
    </tr>
    <%

        ArrayList<Double> cgst = new ArrayList<>()
        ArrayList<Double> sgst = new ArrayList<>()
        ArrayList<Double> igst = new ArrayList<>()
    %>

    <g:each var="sp" in="${purchaseProductDetails}">
        <tr>
            <td>${sp.productId.hsnCode}</td>
            <td><b>${sp.productId.productName}</b></td>
            <td><b>${sp.productId.unitPacking}</b></td>
            <td><b>D</b></td>
            <td>${sp.batchNumber}</td>
            <td id="expDate${sp.id}">${sp.expiryDate}</td>
            %{--            <td></td>--}%
            <td>${sp.mrp}</td>
            <td>${sp.productId.ptr}</td>
            <td></td>
            <td>${sp.sqty}</td>
            <td>${sp.freeQty}</td>
            <%
                float amount = sp.amount - sp.cgstAmount - sp.sgstAmount - sp.igstAmount
            %>
            <td>${amount}</td>
            <td>${sp.discount}</td>
            <%
                cgst.push(sp.cgstAmount/amount * 100)
                sgst.push(sp.sgstAmount/amount * 100)
                igst.push(sp.igstAmount/amount * 100)
            %>
            <td>${sp.cgstAmount}<br>${String.format("%.1f", sp.cgstAmount / amount * 100)}</td>
            <td>${sp.sgstAmount}<br>${String.format("%.1f", sp.sgstAmount / amount * 100)}</td>
            <td>${sp.igstAmount}<br>${String.format("%.1f", sp.igstAmount / amount * 100)}</td>
            <td>${String.format("%.2f",sp.amount)}</td>
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
        <td>${String.format("%.1f", total - totalcgst - totalsgst - totaligst)}</td>
        <td>${totaldiscount}</td>
        <td>${totalcgst}</td>
        <td>${totalsgst}</td>
        <td>${totaligst}</td>
        <td>${total}</td>
    </tr>
</table>

<div class="container" style="display: flex; ">
    %{--    height:200px--}%
    <div style="width: 50%;">

        <p>No of cases <br>
            Weight in Kgs :<br>
            Party Ref No. : <br>
            Rev-Charge : </p>
        <g:each in="${termsConditions}" var="t">
            <p>${t.termCondition}</p>
        </g:each>
    </div>

    <div style="float: right;">
%{--        <table class="print" style="margin-top: 10px;width: 100%;">--}%
%{--            <tr>--}%
%{--                <th>Total</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td>${total}</td>--}%
%{--            </tr>--}%
%{--            <g:each var="c" in="${cgst}">--}%
%{--                <g:if test="${c > 0 && c <= 5}">--}%
%{--                    <tr>--}%
%{--                        <th>Add CGST 5 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="cgst5">${0.5 * total}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--                <g:if test="${c > 5 && c <= 12}">--}%
%{--                    <tr>--}%
%{--                        <th>Add CGST 12 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="cgst12">${String.format("%.2f",0.12 * total)}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%

%{--                <g:if test="${c >12 && c <= 18}">--}%
%{--                    <tr>--}%
%{--                        <th>Add CGST 18 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="cgst18">${0.18 * total}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--                <g:if test="${c >18 && c <= 28}">--}%
%{--                    <tr>--}%
%{--                        <th>Add CGST 28 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="cgst28">${0.28 * total}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--            </g:each>--}%

%{--            <g:each var="s" in="${sgst}">--}%

%{--                <g:if test="${s > 0 && s <= 5}">--}%
%{--                    <tr>--}%
%{--                        <th>Add SGST 5 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="sgst5">${0.5 * total}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--                <g:if test="${s > 5 && s <= 12}">--}%
%{--                    <tr>--}%
%{--                        <th>Add SGST 12 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="sgst12">${String.format("%.2f",0.12 * total)}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--                <g:if test="${s > 12 && s <= 18}">--}%
%{--                    <tr>--}%
%{--                        <th>Add SGST 18 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="sgst18">${0.18 * total}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--                <g:if test="${s > 18 && s <= 28}">--}%
%{--                    <tr>--}%
%{--                        <th>Add SGST 28 % on</th>--}%
%{--                        <td>${total}</td>--}%
%{--                        <td id="sgst28">${0.28 * total}</td>--}%
%{--                    </tr>--}%
%{--                </g:if>--}%
%{--            </g:each>--}%
%{--            <tr>--}%
%{--                <th>Net Invoice Amt.</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td id="netInvAmt"></td>--}%
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
%{--                <td>0.00</td>--}%
%{--            </tr>--}%
%{--            <tr>--}%
%{--                <th>Net Payable Amt.</th>--}%
%{--                <td>0.00</td>--}%
%{--                <td id="netPayAmt"></td>--}%
%{--            </tr>--}%
%{--        </table>--}%
    </div>
</div>
<br>
<br>

<p class="signatory" style="float: right;margin-right: 24px;">Authorized Signatory</p>

<p style="float: left;margin-right: 24px;"><b>Printed By:</b> ${session.getAttribute("userName").toString()}</p>

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

        <g:each var="ppd" in="${purchaseProductDetails}">
        var expDate = new Date('${ppd.expiryDate}')
        $("#expDate${ppd.id}").text(moment(expDate).format('MMM-YY').toUpperCase());
        </g:each>
        generateBarCode()

        //sgst slabs
        var sgst5;
        var sgst12;
        var sgst18;
        var sgst28;

        //sgst slabs
        var cgst5;
        var cgst12;
        var cgst18;
        var cgst28;

        var netAmount = ${total}

        //Sgst slab total
        if($("#sgst5").text().length === 0)
        {
            sgst5 = 0;
        }
        else
        {
            sgst5 = $("#sgst5").text()
        }
        if($("#sgst12").text().length === 0) {
            sgst12 = 0;
        }
        else {
            sgst12 = $("#sgst12").text()
        }
        if($("#sgst18").text().length === 0) {
            sgst18 = 0;
        }
        else {
            sgst18 = $("#sgst18").text()
        }
        if ($("#sgst28").text().length === 0)
        {
            sgst28 = 0;
        }
        else
        {
            sgst28 = $("#sgst28").text()
        }

        //Sgst slab total
        if($("#cgst5").text().length === 0)
        {
            cgst5 = 0;
        }
        else
        {
            cgst5 = $("#cgst5").text()
        }
        if($("#cgst12").text().length === 0) {
            cgst12 = 0;
        }
        else {
            cgst12 = $("#cgst12").text()
        }
        if($("#cgst18").text().length === 0) {
            cgst18 = 0;
        }
        else {
            cgst18 = $("#cgst18").text()
        }
        if ($("#cgst28").text().length === 0)
        {
            cgst28 = 0;
        }
        else
        {
            cgst28 = $("#cgst28").text()
        }
        var sgstSlabTotal = parseFloat(sgst5) +  parseFloat(sgst12) +  parseFloat(sgst18) +  parseFloat(sgst28)
        var cgstSlabTotal = parseFloat(cgst5) +  parseFloat(cgst12) +  parseFloat(cgst18) +  parseFloat(cgst28)
        var netInvAmt =  parseFloat(sgstSlabTotal) + parseFloat(cgstSlabTotal)+netAmount
        $( "#netInvAmt" ).text(netInvAmt.toFixed(2));
        $( "#netPayAmt" ).text(netInvAmt.toFixed(2));

    }
</script>
</html>
