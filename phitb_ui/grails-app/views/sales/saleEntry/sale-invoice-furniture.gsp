<%@ page import="phitb_ui.WordsToNumbersUtil; phitb_ui.Constants" %>
<!DOCTYPE html>
<html>

<head>
    <title>Sale Invoice</title>
    <style>


    /* Styles go here */

    .page-header, .page-header-space {
        height: 500px;
    }

    .page-footer, .page-footer-space {
        height: 70px;

    }

    .page-footer {
        position: fixed;
        bottom: 0;
        width: 100%;
    }

    .page-header {
        position: fixed;
        top: 0mm;
        width: 100%;
    }

    .page {
        page-break-after: always;
    }


    @media print {
        thead {
            display: table-header-group;
        }

        tfoot {
            display: table-footer-group;
        }


        body {
            margin: 0;
        }
    }

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

    .page-number {
        content: counter(page)
    }

    #watermark {
        position: fixed;
        z-index: -1;
        color: lightgrey;
        opacity: 1;
        font-size: 120px;
    }

    /*#wrapper {*/
    /*    !*position: fixed;*!*/
    /*    left: 0;*/
    /*    right: 0;*/
    /*    top: 0;*/
    /*    bottom: 0;*/
    /*    border: 2px solid black;*/
    /*    padding: 20px;*/
    /*}*/

    @media print {

        /*table tbody tr td:before,*/
        /*table tbody tr td:after {*/
        /*    content: "";*/
        /*    height: 4px;*/
        /*    display: block;*/
        /*}*/
        #wrapper {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            border: 2px solid black;
            padding: 10px;
            page-break-after: always;

        }

        #wrapper::-webkit-scrollbar {
            width: 0 !important
        }

    }


    * {
        margin: 0;
        padding: 0;
    }

    html, body {
        height: 100%;
        overflow: hidden;
    }

    tbody {
        page-break-after: always;
        page-break-inside: avoid;
        page-break-before: avoid;
    }


    #wrapper {
        position: absolute;
        /*overflow: revert;*/
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        border: 2px solid black;
        padding: 10px;
        page-break-after: always;
    }

    #wrapper::-webkit-scrollbar {
        width: 0 !important
    }


    table {
        page-break-inside: auto
    }

    tr {
        page-break-inside: avoid;
        page-break-after: auto
    }

    thead {
        display: table-header-group
    }

    tfoot {
        display: table-footer-group
    }

    table {
        -fs-table-paginate: paginate;
    }
    </style>
</head>


<body>

%{--<div class="page-header">--}%

%{--</div>--}%
<div id="wrapper">
    <div class="page-footer">
        <p class="signatory" style="float: right;margin-right: 24px;">For <b>${session.getAttribute('entityName')}</b>,
            <br>
            <span style="line-height: 75px;">Authorized Signatory</span></p>

        <p style="float: left;margin-right: 24px;"><b>Printed By:</b> ${session.getAttribute("userName").toString()}</p>

        <p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>
    </div>


    <table id="prodDetails" class="extended" style="width: 100%; padding: 5%;border: 1px solid #000;">
        <div class="page-header">
            <thead>
            <tr>
                <td colspan="4" style="vertical-align:top;font-size:8pt;">
                    %{--            <img width="109" height="43"--}%
                    %{--                                                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG0AAAArCAMAAABFJ/YVAAAAD1BMVEUAIgAFKwAIKQAJLgD///9auxmhAAAAAWJLR0QAiAUdSAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAZFJREFUWIXtlttywyAMRN1Z/f83t1N0WYHwJTFuH8IkwlZknwjEwiatAfJA2wz2CO5D+9D+Iw3e/oo2QYNshNavLtwT2izRW2nYibxMK1pPw+00/rWjHVXKrTSJ7DQQom69aZdunIbO314E/zbT0yw40WyModWjf2yo4Wys836kYaRxD3KF0fv6lp4fVoAOpd6MNDlBkwu0Nj7v0dgc0WgYC1p8wDQtDbHeQ2ieB1WmRYCSFjnHKHS14TQwzWvyEi1XT8xS1H3QUlyjddN1QOtG2In0jBTPN09Pk5dobiiipqGjCVcJLtBkTkNJg88MaQZXBdNIB1BrSaxa15JMo4uAR5Gn0dqhuU4mmqR5G9Tv7rYhFqatwsU0m8RQ5fU03d4Wwn5psYU9QUvbzUrYD83WgmdYNj4WIHk46BTtYkH6RiC24vJCqyVUabG/yTQ1MXkg0cs410N3fdW5yRmYZpUtaYILLEx2ipdtSUrmNEQP8kxyi9kdabS17af2dtvoVLIc1lT5KVg6cy2HtTPXQyyRb24URU7+XmydAAAAAElFTkSuQmCC"/>--}%
                    %{--            <br><br>--}%
                    <b>${entity.entityName}</b><br>
                    <sub>${entity.addressLine1}<br>${entity.addressLine2}</sub>
                    <g:if test="${entity?.website && entity?.website != ''}">
                        <li><b class="tab">Website</b>: <a href="${entity?.website}"
                                                           target="_blank">${entity?.website}</a>
                        </li>
                    </g:if>
                    <g:if test="${entity?.email && entity?.email != ''}">
                        <li><b class="tab">Email</b>: <a href="mailto:${entity?.email}"
                                                         target="_blank">${entity?.email}</a>
                        </li>
                    </g:if>
                </td>
                <td colspan="6" style="vertical-align:top;font-size:8pt;"><b>Bill to Address :(${customer.id})</b><br>
                    <b>${customer.entityName}</b><br>
                    <sub>${customer.addressLine1}${customer.addressLine2}
                    </sub>
                    <g:if test="${customer?.phoneNumber && customer?.phoneNumber != ''}">
                        <li><b class="tab">Ph no.</b>: <a href="tel:${customer?.phoneNumber}"
                                                          target="_blank">${customer?.phoneNumber}</a>
                        </li>
                    </g:if>
                    <g:if test="${customer?.pinCode && customer?.pinCode != ''}">
                        <li><b class="tab">Pincode</b>:&nbsp;${customer?.pinCode}
                        </li>
                    </g:if>
                </td>
                <td colspan="6" style="vertical-align:top;font-size:8pt;"><b>Ship to Address :(${customer.id})</b><br>
                    <b>${customer.entityName}</b><br>
                    <sub>${customer.addressLine1}${customer.addressLine2}
                    </sub>
                    <g:if test="${customer?.phoneNumber && customer?.phoneNumber != ''}">
                        <li><b class="tab">Ph no.</b>: <a href="tel:${customer?.phoneNumber}"
                                                          target="_blank">${customer?.phoneNumber}</a>
                        </li>
                    </g:if>
                    <g:if test="${customer?.pinCode && customer?.pinCode != ''}">
                        <li><b class="tab">Pincode</b>:&nbsp;${customer?.pinCode}
                        </li>
                    </g:if>
                </td>
                <td colspan="5" style="vertical-align:top;font-size:8pt;">
                    <strong>TAX INVOICE</strong>
                    <ul style="margin: 0;">

                        <li><b class="tab">Invoice No</b>:  <strong style="font-size: 13px;"><g:if
                                test="${saleBillDetail.billStatus == 'CANCELLED'}"><del>${saleBillDetail.invoiceNumber}</del></g:if><g:else>${saleBillDetail.invoiceNumber}</g:else>
                        </strong></li>
                        <li><b class="tab">Inv Date</b>:&nbsp;<span id="invDate" style="font-size: 13px;"></span></li>
                        <li><b class="tab">Due Date</b>:&nbsp;<span id="dueDate"
                                                                    style="font-size: 13px;font-weight: bold;"></span>
                        </li>
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
                        %{--                <g:if test="${customer?.website && customer?.website!=''}">--}%
                        %{--                    <li><b class="tab">Website</b>: <a href="${customer?.website}" target="_blank">${customer?.website}</a></li>--}%
                        %{--                </g:if>--}%
                        <li><b class="tab">Food Lic. No.</b>:  ${entity.foodLicence1}</li>

                    </ul>
                </td>
                <td colspan="6" style="vertical-align:top;">
                    <ul>
                        <li><b class="tab">DELIVERY AT</b>:&nbsp;${custcity?.districtName}</li>
                        <li><b class="tab">GST NO</b>: ${customer.gstn}</li>
                        <li><b class="tab">Phone</b>: ${customer.phoneNumber}</li>
                        <li><b class="tab">PAN</b>: ${customer.pan}</li>
                        <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                        <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                        <li><b class="tab">STATE NAME</b>: ${custcity?.stateName}</li>
                        <li><b class="tab">Area PIN</b>: ${customer.pinCode}</li>
                        %{--                <g:if test="${customer?.website && customer?.website!=''}">--}%
                        %{--                <li><b class="tab">Website</b>: <a href="${customer?.website}" target="_blank">${customer?.website}</a></li>--}%
                        %{--                </g:if>--}%
                        <li><b class="tab">Transporter</b>:&nbsp;&nbsp;${transportDetails?.transporter?.name}</li>
                        <li><b class="tab">Place of Supply</b>: &nbsp;${custcity?.districtName}</li>
                        <li><b class="tab">Po No.</b>:  ${saleBillDetail?.refNo}</li>
                        <li><b class="tab">Po Date.</b>:<span id="poDate2"></span></li>
                        %{--                <li><b class="tab">State Code</b>: </li>--}%
                    </ul>

                </td>
                <td colspan="6" style="vertical-align:top;">
                    <ul>
                        <li><b class="tab">DELIVERY AT</b>:&nbsp;${custcity?.districtName}</li>
                        <li><b class="tab">GST NO</b>: ${customer.gstn}</li>
                        <li><b class="tab">Phone</b>: ${customer.phoneNumber}</li>
                        <li><b class="tab">PAN</b>: ${customer.pan}</li>
                        <li><b class="tab">DL No1</b>: ${customer.drugLicence1}</li>
                        <li><b class="tab">DL No2</b>: ${customer.drugLicence2}</li>
                        <li><b class="tab">STATE NAME</b>: ${custcity?.stateName}</li>
                        <li><b class="tab">Area PIN</b>: ${customer.pinCode}</li>
                        %{--                <g:if test="${customer?.website && customer?.website!=''}">--}%
                        %{--                    <li><b class="tab">Website</b>: <a href="${customer?.website}" target="_blank">${customer?.website}</a></li>--}%
                        %{--                </g:if>--}%
                        <li><b class="tab">Transporter</b>:&nbsp;&nbsp; ${transportDetails?.transporter?.name}</li>
                        <li><b class="tab">Place of Supply</b>:  &nbsp;${custcity?.districtName}</li>
                        <li><b class="tab">Po No.</b>:  ${saleBillDetail?.refNo}</li>
                        <li><b class="tab">Po Date.</b>:<span id="poDate1"></span></li>
                        %{--                <li><b class="tab">State Code</b>: </li>--}%
                    </ul>
                </td>
                <td colspan="5" style="vertical-align:center;padding: 10px;">
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
                <th>Pack(size)</th>
                <th>C</th>
                <th>Batch no.</th>
                <th>Exp Date</th>
                %{--        <th>Mfg Date/ Use Before</th>--}%
                <th>MRP</th>
                <th>PTR</th>
                <th>PTS</th>
                <th>Quantity</th>
                <th >Discount Quantity</th>
                <th >Discount Repl Quantity</th>
                <th style="background-color: #e0e0e0; -webkit-print-color-adjust: exact;">Total Quantity</th>
                <th >Final Bill Quantity</th>
                <th>Amt After Disc.</th>
                <th>Disc.Amt/Disc.%</th>
                <th>Amt/CGST%</th>
                <th>Amt/SGST%</th>
                <th>Amt/IGST%</th>
                <th>Net Amt</th>
            </tr>
            </thead>
        </div>

        <%
            ArrayList<Double> cgst = new ArrayList<>()
            ArrayList<Double> sgst = new ArrayList<>()
            ArrayList<Double> igst = new ArrayList<>()

            HashMap<String, Double> divGstGroup
            HashMap<String, Double> divSgstGroup
            HashMap<String, Double> divCgstGroup
            HashMap<String, Double> divIgstGroup

        %>
        <tbody>

        <g:if test="${settings.size()!= 0}">
            <g:each var="key, groupData" in="${groupDetails}" status="i">

                <%
                    def divTotalcgst = 0
                    def divTotalsgst = 0
                    def divTotaligst = 0
                    def divTotaldiscount = 0
                    def divtotalBeforeTaxes = 0
                    def divtotal = 0


                %>
                <tr><td colspan="20">${groupData[0].sortDetail.sortItem}</td></tr>
                <g:each var="pd" in="${groupData}" status="j">
                    <%
                        divGstGroup = new HashMap<>()
                        divSgstGroup = new HashMap<>()
                        divCgstGroup = new HashMap<>()
                        divIgstGroup = new HashMap<>()

                        divTotalcgst += pd.cgstAmount
                        divTotalsgst += pd.sgstAmount
                        divTotaligst += pd.igstAmount

                        double amountBeforeTaxes = pd.amount - pd.cgstAmount - pd.sgstAmount - pd.igstAmount
                        divtotalBeforeTaxes += amountBeforeTaxes
                        divTotaldiscount += amountBeforeTaxes/100*pd.discount
                        if (pd.igstPercentage > 0)
                        {
                            def igstPercentage = igstGroup.get(pd.igstPercentage.toString())
                            if (igstPercentage == null)
                            {
                                divIgstGroup.put(pd.igstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divIgstGroup.put(pd.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                        else
                        {
                            def gstPercentage = divGstGroup.get(pd.gstPercentage.toString())
                            if (gstPercentage == null)
                            {
                                divGstGroup.put(pd.gstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divGstGroup.put(pd.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)
                            }

                            def sgstPercentage = sgstGroup.get(pd.sgstPercentage.toString())
                            if (sgstPercentage == null)
                            {
                                divSgstGroup.put(pd.sgstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divSgstGroup.put(pd.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                            def cgstPercentage = cgstGroup.get(pd.cgstPercentage.toString())
                            if (cgstPercentage == null)
                            {
                                divCgstGroup.put(pd.cgstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divCgstGroup.put(pd.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                    %>
                    <tr>
                        <td>${j + 1}</td>
                        <td>${pd.productId.hsnCode}</td>
                        <td><b>${pd.productId.productName}</b></td>
                        <td><b>${pd.productId.unitPacking}</b></td>
                        <td><b>${pd?.productId?.schedule?.scheduleCode}</b></td>
                        <td>${pd.batchNumber}</td>
                        <td id="expDate${pd.id}">${pd.expiryDate}</td>
                        %{--            <td></td>--}%
                        <td>${pd.mrp}</td>
                        <td>${String.format("%.2f", pd?.batch?.ptr)}</td>
                        <td>${String.format("%.2f", pd.sRate)}</td>
                        <td>${(long) pd.sqty}</td>
                        <g:if test="${pd?.replacement == false}">
                            <td>${(long) pd.freeQty}</td>
                        </g:if>
                        <g:elseif test="${pd?.replacement == null}">
                            <td>${(long) pd.freeQty}</td>
                        </g:elseif>
                        <g:else>
                            <td>0</td>
                        </g:else>
                        <g:if test="${pd?.replacement!= null}">
                            <g:if test="${pd?.replacement == true}">
                                <td>${(long) pd.freeQty}</td>
                            </g:if>
                            <g:else>
                                <td>0</td>
                            </g:else>
                        </g:if>
                        <g:else>
                            <td>0</td>
                        </g:else>
                        <td style="background-color: #e0e0e0; -webkit-print-color-adjust: exact;">${(long) pd.sqty + (long) pd.freeQty}</td>
                        <td style="background-color: #e0e0e0; -webkit-print-color-adjust: exact;">${(long) pd.sqty}</td>
                        <%
                            float amount = pd.amount - pd.cgstAmount - pd.sgstAmount - pd.igstAmount
                        %>
                        <td>${String.format("%.2f", amount)}</td>
                        <td>${String.format("%.2f", amount/100*pd.discount)}<br>${pd.discount}</td>
                        <%
                            cgst.push(pd.cgstAmount / amount * 100)
                            sgst.push(pd.sgstAmount / amount * 100)
                            igst.push(pd.igstAmount / amount * 100)
                        %>
                        <td>${String.format("%.2f", pd.cgstAmount)}<br>${String.format("%.2f", pd.cgstAmount / amount * 100)}
                        </td>
                        <td>${String.format("%.2f", pd.sgstAmount)}<br>${String.format("%.2f", pd.sgstAmount / amount * 100)}
                        </td>
                        <td>${String.format("%.2f", pd.igstAmount)}<br>${String.format("%.2f", pd.igstAmount / amount * 100)}
                        </td>
                        <td>${String.format("%.2f", pd.amount)}</td>
                    </tr>
                </g:each>
                <tr>
                    <td colspan="14">
                    </td>
                    <% divtotal = divtotalBeforeTaxes + divTotalcgst + divTotalsgst + divTotaligst %>
                    <td><b>Total</b></td>
                    <td>${String.format("%.2f", divtotalBeforeTaxes)}</td>
                    <td>${String.format("%.2f", divTotaldiscount)}</td>
                    <td>${String.format("%.2f", divTotalcgst)}</td>
                    <td>${String.format("%.2f", divTotalsgst)}</td>
                    <td>${String.format("%.2f", divTotaligst)}</td>
                    <td>${String.format("%.2f", divtotal)}</td>
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <g:each var="sp" in="${saleProductDetails}" status="i">
                <tr>
                    <td>${i + 1}</td>
                    <td>${sp.productId.hsnCode}</td>
                    <td><b>${sp.productId.productName}</b></td>
                    <td><b>${sp.productId.unitPacking}</b></td>
                    <td><b>${sp?.productId?.schedule?.scheduleCode}</b></td>
                    <td>${sp.batchNumber}</td>
                    <td id="expDate${sp.id}">${sp.expiryDate}</td>
                    %{--                    <td></td>--}%
                    <td>${sp.mrp}</td>
                    <td>${String.format("%.2f", sp?.batch?.ptr)}</td>
                    <td>${String.format("%.2f", sp.sRate)}</td>
                    <td>${(long) sp.sqty}</td>
                    <g:if test="${sp?.replacement == false}">
                        <td>${(long) sp.freeQty}</td>
                    </g:if>
                    <g:elseif test="${sp?.replacement == null}">
                        <td>${(long) sp.freeQty}</td>
                    </g:elseif>
                    <g:else>
                        <td>0</td>
                    </g:else>
                    <g:if test="${sp?.replacement!= null}">
                        <g:if test="${sp?.replacement == true}">
                            <td>${(long) sp.freeQty}</td>
                        </g:if>
                        <g:else>
                            <td>0</td>
                        </g:else>
                    </g:if>
                    <g:else>
                        <td>0</td>
                    </g:else>
                    <td style="background-color: #e0e0e0; -webkit-print-color-adjust: exact;">${(long) sp.sqty + (long) sp.freeQty}</td>
                    <td>${(long) sp.sqty}</td>
                    <%
                        float amount = sp.amount - sp.cgstAmount - sp.sgstAmount - sp.igstAmount
                    %>
                    <td>${String.format("%.2f", amount)}</td>
                    <td>${String.format("%.2f", amount/100*sp.discount)}<br>${sp.discount}</td>
                    <%
                        cgst.push(sp.cgstAmount / amount * 100)
                        sgst.push(sp.sgstAmount / amount * 100)
                        igst.push(sp.igstAmount / amount * 100)
                    %>
                    <td>${String.format("%.2f", sp.cgstAmount)}<br>${String.format("%.2f", sp.cgstAmount / amount * 100)}
                    </td>
                    <td>${String.format("%.2f", sp.sgstAmount)}<br>${String.format("%.2f", sp.sgstAmount / amount * 100)}
                    </td>
                    <td>${String.format("%.2f", sp.igstAmount)}<br>${String.format("%.2f", sp.igstAmount / amount * 100)}
                    </td>
                    <td>${String.format("%.2f", sp.amount)}</td>
                </tr>
            </g:each>
        </g:else>
        <tr>
            <td colspan="14">Amount in words: <b>${WordsToNumbersUtil.convertToIndianCurrency(total.toString())}</b>
            </td>
            <td><b>Total</b></td>
            <td>${String.format("%.2f", totalBeforeTaxes)}</td>
            <td>${String.format("%.2f", totalDiscAmt)}</td>
            <td>${String.format("%.2f", totalcgst)}</td>
            <td>${String.format("%.2f", totalsgst)}</td>
            <td>${String.format("%.2f", totaligst)}</td>
            <g:if test="${settings.RON == Constants.NEXT_INTEGER_VALUE}">
                <td>${String.format("%.2f", Math.ceil(total))}</td>
            </g:if>
            <g:elseif test="${settings.RON == Constants.PREVIOUS_INTEGER_VALUE}">
                <td>${String.format("%.2f", Math.floor(total))}</td>
            </g:elseif>
            <g:else>
                <td>${String.format("%.2f", total)}</td>
            </g:else>
        </tr>
        <tr style="border: 1px solid #ffffff">
            <td colspan="14" style="border: 0"><g:if test="${saleBillDetail.billStatus == 'CANCELLED'}">
                <div id="watermark" class="print-watermark">CANCELLED</div>
            </g:if>
                <g:elseif test="${saleBillDetail.billStatus == 'DRAFT'}">
                    <div id="watermark" class="print-watermark">DRAFT</div>
                </g:elseif>
                <p><u>Note:</u> <span>${saleBillDetail?.publicNote}</span></p>

                <p>No of cases <br>
                    Weight in Kgs :<br>
                    Party Ref No. : <br>
                    Rev-Charge :</p>
                <g:each var="t" in="${termsConditions}" status="i">
                    <g:if test="${t?.form?.formType == Constants.SALE_INVOICE && t?.deleted == false}">
                        <p>${raw(t?.termCondition)}</p>
                    </g:if>
                </g:each></td>
            <td colspan="7" style="border: 0"><table class="print" style="margin-top: 2%;width: 100%">
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
            </table></td>
        </tr>

        <g:if test="${settings.size() != 0}">
            <tr style="border: 1px solid #ffffff;">
                <td colspan="7" style="border: 0">
                    <table class="print" style="margin-top: 2%;width: 100%">
                        <thead>
                        <th>
                            -
                        </th>
                        <th>
                            Gross Amt.
                        </th>
                        <th>
                            Net Amt.
                        </th>
                        %{--                    <g:each var="k, groupData" in="${groupDetails}" status="i">--}%
                        %{--                        <g:each in="${groupData[0].sortDetail.divCgstGroup}" var="cg">--}%
                        %{--                            <th>Add CGST ${cg.key}% on</th>--}%
                        %{--                        </g:each>--}%
                        %{--                    </g:each>--}%

                        </thead>

                        <g:each var="key, groupData" in="${groupDetails}" status="i">
                            <tr>
                                <td>${groupData[0].sortDetail.sortItem}</td>
                                <td>${String.format("%.2f", groupData[0].sortDetail.amountBeforeTaxes)}</td>
                                <td>${String.format("%.2f", groupData[0].sortDetail.amountAfterTaxes)}</td>
                            </tr>
                        %{--                        <g:each var="cg" in="${groupData[0].sortDetail.divCgstGroup}" status="j">--}%
                        %{--                            <td>${String.format("%.2f", cg.value)}</td>--}%
                        %{--                        </g:each>--}%
                        </g:each>

                    </table>
                </td>
            </tr>
        </g:if>
        </tbody>
        <tfoot style="border: 1px solid #ffffff">
        <tr style="border: 1px solid #ffffff">
            <td style="border: 0;">
                <!--place holder for the fixed-position footer-->
                <div class="page-footer-space"></div>
            </td>
        </tr>
        </tfoot>

    </table>
</div>
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
        /*  var css = '@page { size: landscape; }',
              head = document.head || document.getElementsByTagName('head')[0],
              style = document.createElement('style');

          style.type = 'text/css';
          style.media = 'print';

          if (style.styleSheet) {
              style.styleSheet.cssText = css;
          } else {
              style.appendChild(document.createTextNode(css));
          }

          head.appendChild(style);*/

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
        <g:if test="${settings.RON == Constants.NEXT_INTEGER_VALUE}">
        var netInvAmt = Math.ceil(Number(parseFloat(totalGst) + netAmount));
        </g:if>
        <g:elseif test="${settings.RON == Constants.PREVIOUS_INTEGER_VALUE}">
        var netInvAmt = Math.floor(Number(parseFloat(totalGst) + netAmount));
        </g:elseif>
        <g:else>
        var netInvAmt = parseFloat(totalGst) + netAmount
        </g:else>
        $("#netInvAmt").text(netInvAmt.toFixed(2));
        $("#netPayAmt").text(netInvAmt.toFixed(2));


        <g:if test="${saleBillDetail?.refDate!=null}">
        $("#poDate1").text(" " + moment('${saleBillDetail?.refDate}').format('DD/MM/YYYY'));
        $("#poDate2").text(" " + moment('${saleBillDetail?.refDate}').format('DD/MM/YYYY'));
        </g:if>
        <g:else>
        $("#poDate1").text();
        $("#poDate2").text();
        </g:else>

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