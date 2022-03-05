<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Credit Debit Settlement</title>

    <script type="text/javascript">
        function generateBarCode() {
            var nric = $('#text').val();
            var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';
            $('#barcode').attr('src', url);
        }

        window.onload = function () {
            var d = new Date().toLocaleTimeString();
            document.getElementById("date").innerHTML = d;
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

        <td style="width: 25%;vertical-align:top;"><img width="109" height="43"
                                                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG0AAAArCAMAAABFJ/YVAAAAD1BMVEUAIgAFKwAIKQAJLgD///9auxmhAAAAAWJLR0QAiAUdSAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAZFJREFUWIXtlttywyAMRN1Z/f83t1N0WYHwJTFuH8IkwlZknwjEwiatAfJA2wz2CO5D+9D+Iw3e/oo2QYNshNavLtwT2izRW2nYibxMK1pPw+00/rWjHVXKrTSJ7DQQom69aZdunIbO314E/zbT0yw40WyModWjf2yo4Wys836kYaRxD3KF0fv6lp4fVoAOpd6MNDlBkwu0Nj7v0dgc0WgYC1p8wDQtDbHeQ2ieB1WmRYCSFjnHKHS14TQwzWvyEi1XT8xS1H3QUlyjddN1QOtG2In0jBTPN09Pk5dobiiipqGjCVcJLtBkTkNJg88MaQZXBdNIB1BrSaxa15JMo4uAR5Gn0dqhuU4mmqR5G9Tv7rYhFqatwsU0m8RQ5fU03d4Wwn5psYU9QUvbzUrYD83WgmdYNj4WIHk46BTtYkH6RiC24vJCqyVUabG/yTQ1MXkg0cs410N3fdW5yRmYZpUtaYILLEx2ipdtSUrmNEQP8kxyi9kdabS17af2dtvoVLIc1lT5KVg6cy2HtTPXQyyRb24URU7+XmydAAAAAElFTkSuQmCC"/><br><br>
            <b>SUMUKHA ASSOCIATES</b><br>
            <sub>No:19/20, 1st Floor, 1st main, Byatarayanapura, Above Karnataka Bank, Mysore road, BANGALORE, Pin : 560026, Phone - 9620920222</sub>
        </td>
        <td style="width: 34%;vertical-align:top;">
            <ul style="margin: 0;">
                <li><b class="tab">Settlement No.</b>: 21/K 24</li>
                <li><b class="tab">Customer</b>: PATIL PHARMACEUTICALS &GENERAL MERCHANTS - C0193</li>
                <li><b class="tab">Date</b>:12-11-2021</li>
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
            <tr>
                <td>21/TP 53</td>
                <td>-27755.00</td>
            </tr>
            <tr>
                <td>21/TP 59</td>
                <td>-16364.00</td>
            </tr>
            <tr>
                <td>21/SP 157</td>
                <td>-16364.00</td>
            </tr>
            <tr>
                <td style="visibility: hidden;"></td>
                <td>0.00</td>
            </tr>
        </table>
    </div>
</div>
<br>
<br>

<p class="signatory" style="float: right;margin-right: 24px;">Authorized Signatory</p>

<p style="float: left;margin-right: 24px;"><b>Printed By:</b> Name</p>

<p style="float: left;margin-right: 24px;"><b>Printed On:</b><span id="date"></span></p>
</body>
</html>