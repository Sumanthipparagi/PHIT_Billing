<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Credit Debit Settlement</title>

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

    @media print {

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
    </style>
</head>

<body id="wrapper">
<table style="width:100%;table-layout: auto;">
    <tr>

        <td style="width: 25%;vertical-align:top;">
            <b>${crdbobj.entityId.entityName}</b><br>
            <sub>${crdbobj.entityId.addressLine1}<br>${crdbobj.entityId.addressLine2}</sub>
        </td>
        <td style="width: 34%;vertical-align:top;">
            <ul style="margin: 0;">
                <li><b class="tab">Settlement No.</b>:${crdbobj.crdbNumber}</li>
                <li><b class="tab">Customer</b>:${crdbobj.customerId.entityName}</li>
                <li><b class="tab">Date</b>:${crdbobj.dateCreated.split('T')[0]}</li>
            </ul>
        </td>
        <td style="width: 25%;vertical-align:top;">
            <div class="qrCode"></div>
        </td>

        %{--        <td style="width: 25%;vertical-align:top;">--}%
        %{--            <input id="text" type="hidden" value="PharmIT" style="Width:20%" onblur='generateBarCode();'/>--}%
        %{--            <img id='barcode'--}%
        %{--                 src="https://api.qrserver.com/v1/create-qr-code/?data=PharmIT&amp;size=100x100"--}%
        %{--                 alt=""--}%
        %{--                 title="PhramIT"--}%
        %{--                 style="display: block;--}%
        %{--                 margin-left: auto;--}%
        %{--                 margin-right: auto;--}%
        %{--                 width: 40%;"/>--}%
        %{--        </td>--}%
    </tr>
    %{--    <tr>--}%
    %{--        <td style="width: 25%;vertical-align:top;">--}%
    %{--            <ul>--}%
    %{--                <li><b class="tab">Location</b>: 1573</li>--}%
    %{--                <li><b class="tab">Phone</b>: 8050602246</li>--}%
    %{--                <li><b class="tab">GST No</b>: 29AAACM9401C1ZR</li>--}%
    %{--                <li><b class="tab">FAX No</b>:</li>--}%
    %{--                <li><b class="tab">DL No1</b>: 20B-KA-B22-208222</li>--}%
    %{--                <li><b class="tab">DL No2</b>: 21B-KA-B22-208223</li>--}%
    %{--                <li><b class="tab">Food Lic. No.</b>: 11221302000591</li>--}%
    %{--            </ul>--}%
    %{--        </td>--}%
    %{--        <td style="width: 25%;vertical-align:top;">--}%
    %{--            <ul>--}%
    %{--                <li><b class="tab">DELIVERY AT</b>: CHICKMAGALUR</li>--}%
    %{--                <li><b class="tab">GST NO</b>: 29AKMPS9322B1ZV</li>--}%
    %{--                <li><b class="tab">PAN</b>: AKMPS9322B</li>--}%
    %{--                <li><b class="tab">DL No1</b>: 20B/KACKM/88</li>--}%
    %{--                <li><b class="tab">DL No2</b>: 21B/KACKM/75</li>--}%
    %{--                <li><b class="tab">STATE NAME</b>: Karnataka</li>--}%
    %{--                <li><b class="tab">Goods Through</b>:</li>--}%
    %{--                <li><b class="tab">Place of Supply</b>: CHICKMAGALUR</li>--}%
    %{--                <li><b class="tab">State Code</b>: 29</li>--}%
    %{--            </ul>--}%
    %{--        </td>--}%
    %{--        <td style="width: 25%;vertical-align:top;">--}%
    %{--            <ul>--}%
    %{--                <li><b class="tab">DELIVERY AT</b>: CHICKMAGALUR</li>--}%
    %{--                <li><b class="tab">GST NO</b>: 29AKMPS9322B1ZV</li>--}%
    %{--                <li><b class="tab">PAN</b>: AKMPS9322B</li>--}%
    %{--                <li><b class="tab">DL No1</b>: 20B/KACKM/88</li>--}%
    %{--                <li><b class="tab">DL No2</b>: 21B/KACKM/75</li>--}%
    %{--                <li><b class="tab">STATE NAME</b>: Karnataka</li>--}%
    %{--                <li><b class="tab">Goods Through</b>:</li>--}%
    %{--                <li><b class="tab">Place of Supply</b>: CHICKMAGALUR</li>--}%
    %{--                <li><b class="tab">State Code</b>: 29</li>--}%
    %{--            </ul>--}%
    %{--        </td>--}%
    %{--        <td style="width: 25%;vertical-align:top;">--}%
    %{--            <input id="text" type="hidden" value="PharmIT" style="Width:20%" onblur='generateBarCode();'/>--}%
    %{--            <img id='barcode'--}%
    %{--                 src="https://api.qrserver.com/v1/create-qr-code/?data=PharmIT&amp;size=100x100"--}%
    %{--                 alt=""--}%
    %{--                 title="PhramIT"--}%
    %{--                 style="display: block;--}%
    %{--                 margin-left: auto;--}%
    %{--                 margin-right: auto;--}%
    %{--                 width: 40%;"/>--}%
    %{--        </td>--}%
    %{--    </tr>--}%

</table>

<div class="container" style="display: flex; height: 160px;">
    <div style="float: left;margin-left: -200px;">
        <table class="print" style="margin-top: 10px;width: 100%;">
            <tr>
                <th>Tran No.</th>
                <th>Amount</th>
            </tr>
            <%
                double debitAmt = 0;
                double creditAmt = 0;
            %>
            <g:each var="crdb" in="${crdbLogs}">
            <tr>
                <g:if test="${crdb.creditId!=0}">
                  <% creditAmt+=crdb.creditAmt %>
                <td>${crdb.creditSeries}</td>
                <td style="text-align: left;">${'-'+crdb.creditAmt}</td>
                </g:if>
                <g:if test="${crdb.debitId!=0}">
                    <% debitAmt+=crdb.debitAmt %>
                    <td>${crdb.debitSeries}</td>
                    <td style="text-align: left;">${crdb.debitAmt}</td>
                </g:if>
            </tr>
            </g:each>
            <tr>
                <td style="visibility: hidden;"></td>
                <td>${debitAmt - creditAmt}</td>
            </tr>
        </table>
    </div>
</div>
<br>
<br>

<p class="signatory" style="float: right;margin-right: 24px;">Authorized Signatory</p>

<p style="float: left;margin-right: 24px;"><b>Printed By:</b> ${session.getAttribute('entityName')}</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>
</body>
</html>
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/qr-code/jquery-qrcode-0.18.0.min.js"/>
<script type="text/javascript">
    window.onload = function () {
        var d = new Date().toLocaleTimeString();
        document.getElementById("date").innerHTML = d;
    }
    $('date').text(new Date())
    var qrText = '${crdbobj.crdbNumber}'
    jQuery('.qrCode').qrcode({
        // width: 100,
        // height: 100,
        minVersion: 1,
        maxVersion: 40,
        render: "canvas",
        size: 80,
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
