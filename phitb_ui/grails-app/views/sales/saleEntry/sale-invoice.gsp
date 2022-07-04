<%@ page import="phitb_ui.Constants; phitb_ui.SalesService; java.text.SimpleDateFormat" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sale Invoice</title>

    <script type="text/javascript">
        %{--function generateBarCode() {--}%
        %{--    var nric = '${saleBillDetail.invoiceNumber}';--}%
        %{--    var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';--}%
        %{--    $('#barcode').attr('src', url);--}%
        %{--}--}%


    </script>
    <style>
    table {
        border-collapse: collapse;
        border: 1px solid black;
        font-family: Roboto, Sans-Serif, serif;
        font-size: 7pt;
        page-break-before: always;
    }


    th, td {
        text-align: left;
        padding: 1.5px;
        border: 1px solid black;
        /*word-break: break-word;*/
        vertical-align: top;
    }

    thead {
        /*display: block;*/
        width: 450px;
        font-size: 7pt;
        /*overflow: auto;*/
        /*color: #fff;*/
        /*background: #000;*/
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
            width: 220%!important;
            margin-left: -80px !important;
            float: left;
            /*white-space:nowrap;*/
        }

        .container-width
        {
            width: 90%!important;
        }

        .signatory {
            margin-right: 10px !important;
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

        thead {
            font-size: 6pt;
            padding: 0px;
        }
    }

    ul {
        list-style: none;
        display: table;
    }

    .tab {
        display: table-cell;
        padding-right: 1em;
    }

    li {
        display: table-row;
    }

    .print {
        width: 170%;
        float: left;
        margin: 5% ;
    }

    /*thead{*/
    /*    display:table-header-group;!*repeat table headers on each page*!*/
    /*}*/
    .page-number {
        content: counter(page)
    }

    #watermark
    {
        position: fixed;
        z-index:-1;
        color: lightgrey;
        opacity: 1;
        font-size:120px;
    }


    /* ----------- Non-Retina Screens ----------- */
    @media screen
    and (min-device-width: 1200px)
    and (max-device-width: 1600px)
    and (-webkit-min-device-pixel-ratio: 1) {
        .print {
            width: 170%;
            float: left;
            margin: 5% ;
        }
    }

    /* ----------- Retina Screens ----------- */
    @media screen
    and (min-device-width: 1200px)
    and (max-device-width: 1600px)
    and (-webkit-min-device-pixel-ratio: 2)
    and (min-resolution: 192dpi) {
        .print {
            width: 170%;
            float: left;
            margin: 5% ;
        }
    }
    </style>
</head>

<body>

%{--<table style="table-layout: auto;"  id="userDetails">--}%
%{--    <thead>--}%
%{--    <tr>--}%
%{--        <td style="width: 25%;vertical-align:top;">--}%
%{--            <ul>--}%
%{--                <li><b class="tab">Location</b>: ${city.name}</li>--}%
%{--                <li><b class="tab">Phone</b>: ${entity.phoneNumber}</li>--}%
%{--                <li><b class="tab">GST No</b>: ${entity.gstn}</li>--}%
%{--                <li><b class="tab">FAX No</b>: ${entity.faxNumber}</li>--}%
%{--                <li><b class="tab">DL No1</b>: ${entity.drugLicence1}</li>--}%
%{--                <li><b class="tab">DL No2</b>: ${entity.drugLicence2}</li>--}%
%{--                <li><b class="tab">Food Lic. No.</b>:  ${entity.foodLicence1}</li>--}%
%{--            </ul>--}%
%{--        </td>--}%
%{--        <td style="width: 25%;vertical-align:top;">--}%
%{--            <ul>--}%
%{--                <li><b class="tab">DELIVERY AT</b>:&nbsp;${custcity.name}</li>--}%
%{--                <li><b class="tab">GST NO</b>: ${customer.gstn}</li>--}%
%{--                <li><b class="tab">PAN</b>: ${customer.pan}</li>--}%
%{--                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>--}%
%{--                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>--}%
%{--                <li><b class="tab">STATE NAME</b>: ${custcity.state.name}</li>--}%
%{--                <li><b class="tab">Goods Through</b>:</li>--}%
%{--                <li><b class="tab">Place of Supply</b>: &nbsp;${custcity.name}</li>--}%
%{--                <li><b class="tab">State Code</b>: </li>--}%
%{--            </ul>--}%
%{--        </td>--}%
%{--        <td style="width: 25%;vertical-align:top;">--}%
%{--            <ul>--}%
%{--                <li><b class="tab">DELIVERY AT</b>:&nbsp;${custcity.name}</li>--}%
%{--                <li><b class="tab">GST NO</b>: ${customer.gstn}</li>--}%
%{--                <li><b class="tab">PAN</b>: ${customer.pan}</li>--}%
%{--                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>--}%
%{--                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>--}%
%{--                <li><b class="tab">STATE NAME</b>: ${custcity.state.name}</li>--}%
%{--                <li><b class="tab">Goods Through</b>:</li>--}%
%{--                <li><b class="tab">Place of Supply</b>:  &nbsp;${custcity.name}</li>--}%
%{--                <li><b class="tab">State Code</b>: </li>--}%
%{--            </ul>--}%
%{--        </td>--}%
%{--        <td style="width: 25%;vertical-align:top;">--}%
%{--            <div class="qrCode" ></div>--}%
%{--        </td>--}%
%{--    </tr>--}%
%{--    </thead>--}%
%{--</table>--}%
<table id="prodDetails" class="extended" style="width: 100%; padding: 5%;">
    <thead>
    <tr>
        <td colspan="4" style="vertical-align:top;font-size:8pt;">
            %{--            <img width="109" height="43"--}%
            %{--                                                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG0AAAArCAMAAABFJ/YVAAAAD1BMVEUAIgAFKwAIKQAJLgD///9auxmhAAAAAWJLR0QAiAUdSAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAZFJREFUWIXtlttywyAMRN1Z/f83t1N0WYHwJTFuH8IkwlZknwjEwiatAfJA2wz2CO5D+9D+Iw3e/oo2QYNshNavLtwT2izRW2nYibxMK1pPw+00/rWjHVXKrTSJ7DQQom69aZdunIbO314E/zbT0yw40WyModWjf2yo4Wys836kYaRxD3KF0fv6lp4fVoAOpd6MNDlBkwu0Nj7v0dgc0WgYC1p8wDQtDbHeQ2ieB1WmRYCSFjnHKHS14TQwzWvyEi1XT8xS1H3QUlyjddN1QOtG2In0jBTPN09Pk5dobiiipqGjCVcJLtBkTkNJg88MaQZXBdNIB1BrSaxa15JMo4uAR5Gn0dqhuU4mmqR5G9Tv7rYhFqatwsU0m8RQ5fU03d4Wwn5psYU9QUvbzUrYD83WgmdYNj4WIHk46BTtYkH6RiC24vJCqyVUabG/yTQ1MXkg0cs410N3fdW5yRmYZpUtaYILLEx2ipdtSUrmNEQP8kxyi9kdabS17af2dtvoVLIc1lT5KVg6cy2HtTPXQyyRb24URU7+XmydAAAAAElFTkSuQmCC"/>--}%
            %{--            <br><br>--}%
            <b>${entity.entityName}</b><br>
            <sub>${entity.addressLine1}<br>${entity.addressLine2}</sub>
        </td>
        <td colspan="5" style="vertical-align:top;font-size:8pt;"><b>Bill to Address :(${customer.id})</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}${customer.addressLine2}
            </sub>
        </td>
        <td colspan="5" style="vertical-align:top;font-size:8pt;"><b>Ship to Address :(${customer.id})</b><br>
            <b>${customer.entityName}</b><br>
            <sub>${customer.addressLine1}${customer.addressLine2}
            </sub>
        </td>
        <td colspan="4" style="vertical-align:top;font-size:8pt;">
            <strong>TAX INVOICE</strong>
            <ul style="margin: 0;">

                <li><b class="tab">Invoice No</b>:  <strong><g:if test="${saleBillDetail.billStatus == 'CANCELLED'}"><del>${saleBillDetail.invoiceNumber}</del></g:if><g:else>${saleBillDetail.invoiceNumber}</g:else></strong></li>
                <li><b class="tab">Inv Date</b>:&nbsp;<span id="invDate"></span></li>
                <li><b class="tab">Due Date</b>:&nbsp;<span id="dueDate"></span></li>
            %{--                <li><b class="tab">No of cases</b>:</li>--}%
            %{--                <li><b class="tab">Weight in Kgs</b>:</li>--}%
            %{--                <li><b class="tab">Party Ref No.</b>: 429803</li>--}%
            %{--                <li><b class="tab">Rev-Charge</b>: No Dist.Chnl.01</li>--}%
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="4" style="vertical-align:top;">
            <ul>
                <li><b class="tab">Location</b>: ${city?.districtName}</li>
                <li><b class="tab">Phone</b>: ${entity.phoneNumber}</li>
                <li><b class="tab">GST No</b>: ${entity.gstn}</li>
                <li><b class="tab">FAX No</b>: ${entity.faxNumber}</li>
                <li><b class="tab">DL No1</b>: ${entity.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${entity.drugLicence2}</li>
                <li><b class="tab">Food Lic. No.</b>:  ${entity.foodLicence1}</li>
            </ul>
        </td>
        <td colspan="5" style="vertical-align:top;">
            <ul>
                <li><b class="tab">DELIVERY AT</b>:&nbsp;${custcity?.districtName}</li>
                <li><b class="tab">GST NO</b>: ${customer.gstn}</li>
                <li><b class="tab">Phone</b>: ${customer.phoneNumber}</li>
                <li><b class="tab">PAN</b>: ${customer.pan}</li>
                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                <li><b class="tab">STATE NAME</b>: ${custcity?.stateName}</li>
                <li><b class="tab">Area PIN</b>: ${customer.pinCode}</li>
                <li><b class="tab">Goods Through</b>:</li>
                <li><b class="tab">Place of Supply</b>: &nbsp;${custcity?.districtName}</li>
                %{--                <li><b class="tab">State Code</b>: </li>--}%
            </ul>

        </td>
        <td colspan="5" style="vertical-align:top;">
            <ul>
                <li><b class="tab">DELIVERY AT</b>:&nbsp;${custcity?.districtName}</li>
                <li><b class="tab">GST NO</b>: ${customer.gstn}</li>
                <li><b class="tab">Phone</b>: ${customer.phoneNumber}</li>
                <li><b class="tab">PAN</b>: ${customer.pan}</li>
                <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                <li><b class="tab">STATE NAME</b>: ${custcity?.stateName}</li>
                <li><b class="tab">Area PIN</b>: ${customer.pinCode}</li>
                <li><b class="tab">Goods Through</b>:</li>
                <li><b class="tab">Place of Supply</b>:  &nbsp;${custcity?.districtName}</li>
                %{--                <li><b class="tab">State Code</b>: </li>--}%
            </ul>
        </td>
        <td colspan="4" style="vertical-align:center;padding: 10px;">
            <div class="qrCode"></div>
        </td>
    </tr>
    <g:if test="${irnDetails != null}">
        <tr>
            <td colspan="4">
                <strong>Ack No</strong>:&nbsp;${irnDetails.AckNo}
            </td>
            <td colspan="5">
                <strong>Ack Dt</strong>:&nbsp;${irnDetails.AckDt}
            </td>
            <td colspan="9">
                <strong>IRN</strong>:&nbsp;${irnDetails.Irn}
            </td>
        </tr>
    </g:if>
    <tr>
        <th>Sl.No</th>
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

    </thead>
    <%
        ArrayList<Double> cgst = new ArrayList<>()
        ArrayList<Double> sgst = new ArrayList<>()
        ArrayList<Double> igst = new ArrayList<>()
    %>
    <tbody style="padding: 5px!important;">
    <g:each var="sp" in="${saleProductDetails}" status="i">
        <tr>
            <td>${i + 1}</td>
            <td>${sp.productId.hsnCode}</td>
            <td><b>${sp.productId.productName}</b></td>
            <td><b>${sp.productId.unitPacking}</b></td>
            <td><b>D</b></td>
            <td>${sp.batchNumber}</td>
            <td id="expDate${sp.id}">${sp.expiryDate}</td>
            %{--            <td></td>--}%
            <td>${sp.mrp}</td>
            <td>${sp?.batch?.ptr}</td>
            <td>${sp.sRate}</td>
            <td>${sp.sqty}</td>
            <td>${sp.freeQty}</td>
            <%
                float amount = sp.amount - sp.cgstAmount - sp.sgstAmount - sp.igstAmount
            %>
            <td>${amount}</td>
            <td>${sp.discount}</td>
            <%
                cgst.push(sp.cgstAmount / amount * 100)
                sgst.push(sp.sgstAmount / amount * 100)
                igst.push(sp.igstAmount / amount * 100)
            %>
            <td>${String.format("%.2f", sp.cgstAmount)}<br>${String.format("%.2f", sp.cgstAmount / amount * 100)}</td>
            <td>${String.format("%.2f", sp.sgstAmount)}<br>${String.format("%.2f", sp.sgstAmount / amount * 100)}</td>
            <td>${String.format("%.2f", sp.igstAmount)}<br>${String.format("%.2f", sp.igstAmount / amount * 100)}</td>
            <td>${String.format("%.2f", sp.amount)}</td>
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
        <td class="hide"></td>
        <td><b>Total</b></td>
        <td>${String.format("%.2f", totalBeforeTaxes)}</td>
        <td>${String.format("%.2f", totaldiscount)}</td>
        <td>${String.format("%.2f", totalcgst)}</td>
        <td>${String.format("%.2f", totalsgst)}</td>
        <td>${String.format("%.2f", totaligst)}</td>
        <td>${String.format("%.2f", total)}</td>
    </tr>
    </tbody>
</table>

%{--<div id="breakPage" style="page-break-after: avoid;"></div>--}%


<div class="container" style="display: flex; ">
    %{--    height:200px--}%
    <div style="width: 80%;" class="container-width">
    <g:if test="${saleBillDetail.billStatus == 'CANCELLED'}">
        <div id="watermark" class="print-watermark">CANCELLED</div>
    </g:if>
    <g:elseif test="${saleBillDetail.billStatus == 'DRAFT'}">
        <div id="watermark" class="print-watermark">DRAFT</div>
    </g:elseif>
        <p>No of cases <br>
            Weight in Kgs :<br>
            Party Ref No. : <br>
            Rev-Charge :</p>

%{--        <p>${termsConditions[0].termCondition}</p>--}%
    <g:each var="t" in="${termsConditions}" status="i">
        <g:if test="${t?.form?.formType == Constants.SALE_INVOICE && t?.deleted == false}">
            <p>${raw(t?.termCondition)}</p>
        </g:if>
    </g:each>

</div>

    <div>
        <table class="print" style="">
            <tr>
                <th>Total</th>
                <td>0.00</td>
                <td>${String.format("%.2f", totalBeforeTaxes)}</td>
            </tr>

            <g:each in="${sgstGroup}" var="sg">
                <tr>
                    <th>Add SGST ${sg.key}% on</th>
                    <td>${String.format("%.2f", sg.value)}</td>
                    <td class="totalgst">${String.format("%.2f", sg.value * (Double.parseDouble(sg.key.toString()) / 100))}</td>
                </tr>

            </g:each>

            <g:each in="${cgstGroup}" var="cg">
                <tr>
                    <th>Add CGST ${cg.key}% on</th>
                    <td>${String.format("%.2f", cg.value)}</td>
                    <td class="totalgst">${String.format("%.2f", cg.value * (Double.parseDouble(cg.key.toString()) / 100))}</td>
                </tr>

            </g:each>

            <g:each in="${igstGroup}" var="ig">
                <tr>
                    <th>Add IGST ${ig.key}% on</th>
                    <td>${String.format("%.2f", ig.value)}</td>
                    <td class="totalgst">${String.format("%.2f", ig.value * (Double.parseDouble(ig.key.toString()) / 100))}</td>
                </tr>

            </g:each>
            <tr>
                <th>Net Invoice Amt.</th>
                <td>0.00</td>
                <td id="netInvAmt"></td>
            </tr>
            <tr>
                <th>Less Cr. Nt*</th>
                <td>0.00</td>
                <td>0.00</td>
            </tr>
            <tr>
                <th>Add Debit Nt*</th>
                <td>0.00</td>
                <td>0.00</td>
            </tr>
            <tr>
                <th>Add Rounding off*</th>
                <td>0.00</td>
                <td>0.00</td>
            </tr>
            <tr>
                <th>Net Payable Amt.</th>
                <td>0.00</td>
                <td id="netPayAmt"></td>
            </tr>
        </table>
    </div>
</div>
<br>
<br>

<p class="signatory" style="float: right;margin-right: 24px;">Authorized Signatory</p>

<p style="float: left;margin-right: 24px;"><b>Printed By:</b> ${session.getAttribute("userName").toString()}</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>

<div class="page-number"></div>

<br>
<br>

<div id="breakPageContent"></div>
</body>
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/qr-code/jquery-qrcode-0.18.0.min.js"/>
%{--<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>--}%
%{--<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>--}%

<script>
    window.onload = function () {

        %{--const qrcode = new QRCode(document.getElementsByClassName('qrcode'), {--}%
        %{--    text: ' ${saleBillDetail.invoiceNumber}',--}%
        %{--    width: 128,--}%
        %{--    height: 128,--}%
        %{--    colorDark : '#000000',--}%
        %{--    colorLight : '#fff',--}%
        %{--    correctLevel : QRCode.CorrectLevel.H--}%
        %{--});--}%

        window.print();
        var d = moment(new Date()).format('DD/MM/YYYY') + " " + new Date().toLocaleTimeString();
        document.getElementById("date").innerHTML = d;
        var invDate = new Date('${saleBillDetail.dateCreated}');
        var dueDate = new Date('${saleBillDetail.dueDate}');
        $("#invDate").text(moment(invDate).format('DD-MM-YYYY'));
        $("#dueDate").text(moment(dueDate).format('DD-MM-YYYY'));

        <g:each var="spd" in="${saleProductDetails}">
        var expDate = new Date('${spd.expiryDate}');
        $("#expDate${spd.id}").text(moment(expDate).format('MMM-YY').toUpperCase());
        </g:each>
        var totalGst = 0.0;
        var totalgstField = $(".totalgst");
        totalgstField.each(function (i) {
            var totGst = totalgstField.eq(i)[0].innerText;
            if (totGst !== "")
                totalGst += parseFloat(totGst);
        });
        var netAmount =
        ${totalBeforeTaxes}
        var netInvAmt = parseFloat(totalGst) + netAmount;
        $("#netInvAmt").text(netInvAmt.toFixed(2));
        $("#netPayAmt").text(netInvAmt.toFixed(2));


        //
        // var rowCount = $('.extended tr').length;
        // var row = rowCount - 2;
        //
        // var userDetails = $('#userDetails').prop('outerHTML');
        // var prodHeaders="";
        // var prodDetails="";
        // $(".extended tr th").each(function(){
        //     prodHeaders += $(this).prop('outerHTML');
        // });
        // $(".extended").each(function(){
        //     prodDetails += $(this).prop('outerHTML');
        // });
        // var array = [];
        // var headers = [];
        // $('.extended th').each(function(index, item) {
        //     headers[index] = $(item).html()
        // });
        //
        // $('.extended tr').has('td').each(function() {
        //     var arrayItem = {};
        //     $('td', $(this)).each(function(index, item) {
        //         arrayItem[headers[index].replace(/\s+/g, '').replace(/[\W_]/g, "_")] = $(item).html();
        //     });
        //     array.push(arrayItem);
        // });
        // var data = array.slice(5);
        // var pdetails="";
        // for (var i = 0; i < data.length; i++)
        // {
        //     pdetails +="<tr><td>"+data[i].MaterialHSNCode+"</td><td>"+data[i].MaterialDescription+"</td><td>"+data[i].Pack+"</td><td>"+data[i].C+"</td><td>"+data[i].Batch+"</td><td>"+data[i].ExpDate+"</td><td>"+data[i].MRP+"</td><td>"+data[i].PTR+"</td><td>"+data[i].PTS+"</td><td>"+data[i].QTY+"</td><td>"+data[i].Scheme+"</td><td>"+data[i].Amount+"</td><td>"+data[i].Disc_Amt_Disc__+"</td><td>"+data[i].Amt_CGST_+"</td><td>"+data[i].Amt_SGST_+"</td><td>"+data[i].Amt_IGST_+"</td><td>"+data[i].NetAmt+"</td></tr>";
        // }
        // var prodTableHeaders =
        //     '<table style="width:1308px;table-layout: auto;" class="extended">'+prodHeaders;
        // if(row > 5)
        // {
        //     document.getElementById("breakPage").style.pageBreakAfter = "always";
        //     $('#breakPageContent').html(userDetails+prodTableHeaders+pdetails+"</table>")
        //     $("#prodDetails tr").slice(-data.length).remove();
        // }


    };

    var qrText = '${saleBillDetail.invoiceNumber}';
    <g:if test="${irnDetails != null}">
    qrText = '${irnDetails.SignedQRCode}';
    </g:if>
    jQuery('.qrCode').qrcode({
        // width: 100,
        // height: 100,
        minVersion: 1,
        maxVersion: 40,
        render: "canvas",
        size: 100,
        // code color or image element
        fill: '#000',
        // background color or image element, null for transparent background
        background: null,
        // corner radius relative to module width: 0.0 .. 0.5
        radius: 0,
        // quiet zone in modules
        quiet: 1,
        ecLevel: 'L',
        // modes
        // 0: normal
        // 1: label strip
        // 2: label box
        // 3: image strip
        // 4: image box
        mode: 0,

        mSize: 0.1,
        mPosX: 0.5,
        mPosY: 0.5,
        label: qrText,
        fontname: 'sans',
        fontcolor: '#000',
        image: null,
        text: qrText
    });


</script>
</html>
