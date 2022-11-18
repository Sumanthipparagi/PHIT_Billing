<%@ page import="java.text.SimpleDateFormat; java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"
      xmlns:o="urn:schemas-microsoft-com:office:office">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- So that mobile will display zoomed in -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> <!-- enable media queries for windows phone 8 -->
    <meta name="format-detection" content="telephone=no"> <!-- disable auto telephone linking in iOS -->
    <meta name="format-detection" content="date=no"> <!-- disable auto date linking in iOS -->
    <meta name="format-detection" content="address=no"> <!-- disable auto address linking in iOS -->
    <meta name="format-detection" content="email=no"> <!-- disable auto email linking in iOS -->
    <title></title>

    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
          rel="stylesheet">

    <style type="text/css">
    /*Basics*/
    body {
        margin: 0px !important;
        padding: 0px !important;
        display: block !important;
        min-width: 100% !important;
        width: 100% !important;
        -webkit-text-size-adjust: none;
    }

    table {
        border-spacing: 0;
        mso-table-lspace: 0pt;
        mso-table-rspace: 0pt;
    }

    table td {
        border-collapse: collapse;
        mso-line-height-rule: exactly;
    }

    td img {
        -ms-interpolation-mode: bicubic;
        width: auto;
        max-width: auto;
        height: auto;
        margin: auto;
        display: block !important;
        border: 0px;
    }

    td p {
        margin: 0;
        padding: 0;
    }

    td div {
        margin: 0;
        padding: 0;
    }

    td a {
        text-decoration: none;
        color: inherit;
    }

    /*Outlook*/
    .ExternalClass {
        width: 100%;
    }

    .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {
        line-height: inherit;
    }

    .ReadMsgBody {
        width: 100%;
        background-color: #ffffff;
    }

    /* iOS BLUE LINKS */
    a[x-apple-data-detectors] {
        color: inherit !important;
        text-decoration: none !important;
        font-size: inherit !important;
        font-family: inherit !important;
        font-weight: inherit !important;
        line-height: inherit !important;
    }

    /*Gmail blue links*/
    u + #body a {
        color: inherit;
        text-decoration: none;
        font-size: inherit;
        font-family: inherit;
        font-weight: inherit;
        line-height: inherit;
    }

    /*Buttons fix*/
    .undoreset a, .undoreset a:hover {
        text-decoration: none !important;
    }

    .yshortcuts a {
        border-bottom: none !important;
    }

    .ios-footer a {
        color: #aaaaaa !important;
        text-decoration: none;
    }

    /*Responsive*/
    @media screen and (max-width: 799px) {
        table.row {
            width: 100% !important;
            max-width: 100% !important;
        }

        td.row {
            width: 100% !important;
            max-width: 100% !important;
        }

        .img-responsive img {
            width: 100% !important;
            max-width: 100% !important;
            height: auto !important;
            margin: auto;
        }

        .center-float {
            float: none !important;
            margin: auto !important;
        }

        .center-text {
            text-align: center !important;
        }

        .container-padding {
            width: 100% !important;
            padding-left: 15px !important;
            padding-right: 15px !important;
        }

        .container-padding10 {
            width: 100% !important;
            padding-left: 10px !important;
            padding-right: 10px !important;
        }

        .hide-mobile {
            display: none !important;
        }

        .menu-container {
            text-align: center !important;
        }

        .autoheight {
            height: auto !important;
        }

        .m-padding-10 {
            margin: 10px 0 !important;
        }

        .m-padding-15 {
            margin: 15px 0 !important;
        }

        .m-padding-20 {
            margin: 20px 0 !important;
        }

        .m-padding-30 {
            margin: 30px 0 !important;
        }

        .m-padding-40 {
            margin: 40px 0 !important;
        }

        .m-padding-50 {
            margin: 50px 0 !important;
        }

        .m-padding-60 {
            margin: 60px 0 !important;
        }

        .m-padding-top10 {
            margin: 30px 0 0 0 !important;
        }

        .m-padding-top15 {
            margin: 15px 0 0 0 !important;
        }

        .m-padding-top20 {
            margin: 20px 0 0 0 !important;
        }

        .m-padding-top30 {
            margin: 30px 0 0 0 !important;
        }

        .m-padding-top40 {
            margin: 40px 0 0 0 !important;
        }

        .m-padding-top50 {
            margin: 50px 0 0 0 !important;
        }

        .m-padding-top60 {
            margin: 60px 0 0 0 !important;
        }

        .m-height10 {
            font-size: 10px !important;
            line-height: 10px !important;
            height: 10px !important;
        }

        .m-height15 {
            font-size: 15px !important;
            line-height: 15px !important;
            height: 15px !important;
        }

        .m-height20 {
            font-size: 20px !important;
            line-height: 20px !important;
            height: 20px !important;
        }

        .m-height25 {
            font-size: 25px !important;
            line-height: 25px !important;
            height: 25px !important;
        }

        .m-height30 {
            font-size: 30px !important;
            line-height: 30px !important;
            height: 30px !important;
        }

        .rwd-on-mobile {
            display: inline-block !important;
            padding: 5px !important;
        }

        .center-on-mobile {
            text-align: center !important;
        }
    }
    </style>

</head>

<body style="margin-top: 0; margin-bottom: 0; padding-top: 0; padding-bottom: 0; width: 100%; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;"
      bgcolor="#f0f0f0">

<span class="preheader-text"
      style="color: transparent; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; visibility: hidden; width: 0; display: none; mso-hide: all;"></span>

<div style="display:none; font-size:0px; line-height:0px; max-height:0px; max-width:0px; opacity:0; overflow:hidden; visibility:hidden; mso-hide:all;"></div>

<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%" style="width:100%;max-width:100%;">
    <tr><!-- Outer Table -->
        <td align="center" bgcolor="#f0f0f0" data-composer>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-header-16-->
                <tr>
                    <td align="center" bgcolor="#343e9e" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            <tr>
                                <td height="40" style="font-size:40px;line-height:40px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <!-- Logo & Webview -->
                                    <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation"
                                           width="100%" style="width:100%;max-width:100%;">
                                        <tr>
                                            <td align="center" class="container-padding">

                                                <!--[if (gte mso 9)|(IE)]><table border="0" cellpadding="0" cellspacing="0" dir="rtl"><tr><td><![endif]-->

                                                <!-- column -->
                                                <table border="0" align="right" cellpadding="0" cellspacing="0"
                                                       role="presentation" class="row" width="280"
                                                       style="width:280px;max-width:280px;">
                                                    <tr>
                                                        <td class="center-text" align="right" height="22"
                                                            style="height: 22px;">
                                                            <a href="#"
                                                               style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:12px;line-height:16px;font-style:italic;font-weight:400;color:#b5beff;text-decoration:none;letter-spacing:0px;"></a>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <!-- column -->

                                                <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->

                                                <!-- gap -->
                                                <table border="0" align="right" cellpadding="0" cellspacing="0"
                                                       role="presentation" class="row" width="20"
                                                       style="width:20px;max-width:20px;">
                                                    <tr>
                                                        <td height="20"
                                                            style="font-size:20px;line-height:20px;">&nbsp;</td>
                                                    </tr>
                                                </table>
                                                <!-- gap -->

                                                <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->

                                                <!-- column -->
                                                <table border="0" align="right" cellpadding="0" cellspacing="0"
                                                       role="presentation" class="row" width="280"
                                                       style="width:280px;max-width:280px;">
                                                    <tr>
                                                        <td align="left" class="center-text">
                                                            %{--                                                            <img style="width:72px;border:0px;display:--}%
                                                            %{--                                                            inline!important;" src="${assetPath(src:--}%
                                                            %{--                                                                    '/themeassets/images/logo.svg')}" width="72"--}%
                                                            %{--                                                                 border="0" alt="logo">--}%





                                                            <img
                                                                src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABfoAAAFKCAIAAADKZHnpAAAACXBIWXMAAAsTAAALEwEAmpwYAAAKJ2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDUgNzkuMTY0NTkwLCAyMDIwLzEyLzA5LTExOjU3OjQ0ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIiB4bWxuczpleGlmPSJodHRwOi8vbnMuYWRvYmUuY29tL2V4aWYvMS4wLyIgeG1wTU06RG9jdW1lbnRJRD0iYWRvYmU6ZG9jaWQ6cGhvdG9zaG9wOmM1NTE1ZTQ2LTY1NmQtODc0NC05YzM0LWQ3MTg5ZmI3MTliMSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2ZTIzMzI3ZC02OTFmLTNkNDEtYjg1Yi0zM2IzN2RkM2NjYjgiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0iNUM4QUU0OTA2QTAxODQ3RDE3NTc2Q0QzQjUwNDQ2RDgiIGRjOmZvcm1hdD0iaW1hZ2UvcG5nIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0ic1JHQiBJRUM2MTk2Ni0yLjEiIHhtcDpDcmVhdGVEYXRlPSIyMDIyLTExLTAxVDEyOjAxOjQ1KzA1OjMwIiB4bXA6TW9kaWZ5RGF0ZT0iMjAyMi0xMS0xOFQxNjozNzowNiswNTozMCIgeG1wOk1ldGFkYXRhRGF0ZT0iMjAyMi0xMS0xOFQxNjozNzowNiswNTozMCIgdGlmZjpJbWFnZVdpZHRoPSIxNTMwIiB0aWZmOkltYWdlTGVuZ3RoPSIzMzAiIHRpZmY6UGhvdG9tZXRyaWNJbnRlcnByZXRhdGlvbj0iMiIgdGlmZjpTYW1wbGVzUGVyUGl4ZWw9IjMiIHRpZmY6WFJlc29sdXRpb249IjEvMSIgdGlmZjpZUmVzb2x1dGlvbj0iMS8xIiB0aWZmOlJlc29sdXRpb25Vbml0PSIxIiBleGlmOkV4aWZWZXJzaW9uPSIwMjMxIiBleGlmOkNvbG9yU3BhY2U9IjY1NTM1IiBleGlmOlBpeGVsWERpbWVuc2lvbj0iMTUzMCIgZXhpZjpQaXhlbFlEaW1lbnNpb249IjMzMCI+IDx4bXBNTTpIaXN0b3J5PiA8cmRmOlNlcT4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjFiNmIzMmZlLWExN2UtYzg0Yi04ZjlhLWNkNjFiZWMwOGE5OSIgc3RFdnQ6d2hlbj0iMjAyMi0xMS0wMVQxMzoyNjo1MCswNTozMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIDIyLjEgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJjb252ZXJ0ZWQiIHN0RXZ0OnBhcmFtZXRlcnM9ImZyb20gaW1hZ2UvanBlZyB0byBpbWFnZS9wbmciLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249ImRlcml2ZWQiIHN0RXZ0OnBhcmFtZXRlcnM9ImNvbnZlcnRlZCBmcm9tIGltYWdlL2pwZWcgdG8gaW1hZ2UvcG5nIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDowNmYyMzRhMS1iZjBmLWI2NGItYmNmZS1kOTE1MzU5MTE4ZDAiIHN0RXZ0OndoZW49IjIwMjItMTEtMDFUMTM6MjY6NTArMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCAyMi4xIChXaW5kb3dzKSIgc3RFdnQ6Y2hhbmdlZD0iLyIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6NmUyMzMyN2QtNjkxZi0zZDQxLWI4NWItMzNiMzdkZDNjY2I4IiBzdEV2dDp3aGVuPSIyMDIyLTExLTE4VDE2OjM3OjA2KzA1OjMwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgMjIuMSAoV2luZG93cykiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPC9yZGY6U2VxPiA8L3htcE1NOkhpc3Rvcnk+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjFiNmIzMmZlLWExN2UtYzg0Yi04ZjlhLWNkNjFiZWMwOGE5OSIgc3RSZWY6ZG9jdW1lbnRJRD0iNUM4QUU0OTA2QTAxODQ3RDE3NTc2Q0QzQjUwNDQ2RDgiIHN0UmVmOm9yaWdpbmFsRG9jdW1lbnRJRD0iNUM4QUU0OTA2QTAxODQ3RDE3NTc2Q0QzQjUwNDQ2RDgiLz4gPHRpZmY6Qml0c1BlclNhbXBsZT4gPHJkZjpTZXE+IDxyZGY6bGk+ODwvcmRmOmxpPiA8cmRmOmxpPjg8L3JkZjpsaT4gPHJkZjpsaT44PC9yZGY6bGk+IDwvcmRmOlNlcT4gPC90aWZmOkJpdHNQZXJTYW1wbGU+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+mNx/owABOJBJREFUeJzs/Ul3ZEeyKOqZufvuow802ZEsnqo6jc65b717JxppJP0X/b830kQDraW7lgZ6uu92p6lTVSQzE1303e7dzTTYASSYTJCMJCIRAOxbWVkgEojYEbEb3+bmZsjMIH6CmdM0/Zd/+Zf/LbX/jx/ev83yjKkCssCkAJQCuH7fGBSDpu1/EdEnH/Cu7yv96e/fye3481h/8tvGmE9+X7Pd6eEZ3W7bs6M8AAAYbhAAas21gjwio+gfW8P/G8X/18HL//P/+r/0ej2l1F43QwghhBBCCCGEeEQ+fc8vGszsB0HSarWN5wHXyDWT04iIdB3uUb8u3IOIn34K+HQ45i4aPv04zt0Rdrkj3HPXdh5auMcGAACRQwAwmo2CMiKFO8a8hBBCCCGEEEKI50TCPXdiZmZ2zARMCIzIzPypYAshIILi7defpPWn00/uiLrc6a5wz505Wndtj3dXds9u23PnE9yTJgnJGAQA1swKjCEj4R4hhBBCCCGEEOJuEu75Bc45a21d1xWwBa6BHIJS6nZ2DwAQbb+w/OlIhLWfzprZNbvnrvDQXdk6oHZbzAW8a7bOfrN7mqQl5xAAHLBjcI4QCfRen1YIIYQQQgghhHjEJNzzC7Qx2vd8YgJmIEZWCEopdx3uaZZxaQZkAAC8I9xzV40kVLtlx3D16bCRd0e2DtyxiOyu8NNd23+3/SbaNFEsIgQAQiYAIiIJ9wghhBBCCCGEEHeTcM/P4W2QBhERARAQERABb1Xiab7C60VNeNdiq7vCPXeEY+5Cuz7OHd/X+tPxEn1gpbubzdQaAYA0kwKtUe/4pgkhhBBCCCGEEM+KhHt+DiIykXW2dLYCtsg1E2lEppuwCzEAgONfWMx1pzuybO7Cdzw+uTseBz/9/bvCPfTJ6kR30zv+/K620TTE5uttrG0b7jmsyJQQQgghhBBCCHEgJNzzC5RSRhtjyAETEINyCpRSN+Gepraxug73wB3hmLs6he9aqhlxx47j+OmYyF2LuXau3bNzrZ/d3FW7hzUzA0vERwghhBBCCCGE+AkJ9/wCxwQKQWFZVRWQ9j1EKGx9k9SiCfD6b7g74cTdFdfZscmUviOZ5s5SzXfU1sE7OoUh7xZOUrTfgEsTJVMKAUApVgqUAkS2RKgV1bUEfIQQQgghhBBCiI/smCryzOCulXXEl8LXHnpDhBBCCCGEEEKIgyPZPb+AARiv/2YgBKuACW6yewhBMTBuE3t2LmWz48/flUxzX/2xJL4lhBBCCCGEEEI8dpLdI4QQQgghhBBCCPGkSHbPL2AABiAEwu3XyMD4IZsGEQgAryNntOfsmLuyb+7MKtpxe2R1lBBCCCGEEEII8dhJdo8QQgghhBBCCCHEkyLZPb8EP07YIfVxpZybsj5wd2eun3n83Uj2jRBCCCGEEEIIIX6WZPf8nKbzkwRYDpZ0ThNCCCGEEEIIIX5Ksnt+CSuwwBY89BnJWiYCo3y6jgI1ATONoBgAwPKOPbJY7/TjyO6T39d3xD1ox83ZNXzicNddyO7005oAABQpANBABKAJNaJhrVjt3ghNCCGEEEIIIYR4+iS75+dcZ/dIfs8hQkDJ7hFCCCGEEEIIIX5Kwj1CCCGEEEIIIYQQT4qEe4QQQgghhBBCCCGeFAn3CCGEEEIIIYQQQjwpEu4RQgghhBBCCCGEeFIk3COEEEIIIYQQQgjxpEi4RwghhBBCCCGEEOJJMQ+9AQcNEXdtwq7UpyNoRHQPG7S7u7bnTg+zmUIIIYQQQgghhLg3kt0jhBBCCCGEEEII8aRIuEcIIYQQQgghhBDiSZFwjxBCCCGEEEIIIcSTIuEeIYQQQgghhBBCiCdFwj1CCCGEEEIIIYQQT4qEe8RjhfjQWyCEEEIIIYQQQhwkCff8HGbmHRuxiy8AAbd/EFGiPkIIIYQQQgghxI+Zh96Aw9VEeggQvABZ26qqAVXgKQRrLd8EGZp4EG7/n+4KPujdnp2IPv0P6HZ6HEO7fcR8RwDwzu3Z1Y7BmVJbACgNAAAhVBqsAkBQqByTMp6E44QQQgghhBBCiI9Ids/PkeSew4TYJPhsM3weenOEEEIIIYQQQojDIuEe8VhJoEcIIYQQQgghhPgkCfcIIYQQQgghhBBCPCkS7hFCCCGEEEIIIYR4UiTcI4QQQgghhBBCCPGkSLhHCCGEEEIIIYQQ4kmRcI8QQgghhBBCCCHEk2IeegMeK6W2kbLm/xSDYgAAYrrfx//Y/Tz8zu7aHnVHp3qiB9pQIYQQQgghhBDi2ZPsHiGEEEIIIYQQQognRcI9QgghhBBCCCGEEE+KhHuEEEIIIYQQQgghnhQJ9wghhBBCCCGEEEI8KRLuEUIIIYQQQgghhHhSJNwjhBBCCCGEEEII8aRIuOfnMDMD8B29xoUQQgghhBBCCCEOkHnoDTh4StcEFQEbH4Aqx06BMr67CQLh9v/5+uc/+TBE9OmHZ7fT5rCKdvp5y9VOPw/w6e2/2x3bf8fD3PE2/BK+jksyAFsAAtYA6sO7L4QQQgghhBBCiGuS3fNzeOuht0MIIYQQQgghhBDiV5NwjxBCCCGEEEIIIcSTIuEeIYQQQgghhBBCiCdFwj1CCCGEEEIIIYQQT4qEe4QQQgghhBBCCCGeFAn3CCGEEEIIIYQQQjwpEu75OYiIKK2+hRBCCCGEEEII8ZiYh96Ag8bMRGS0VsyuqhyQ9j1QYK1FtVukTN3x8+qONu9EtPPm7vK89/X4u7pre4QQQgghhBBCCHFf5N7750hujxBCCCGEEEIIIR4dCfcIIYQQQgghhBBCPCkS7hFCCCGEEEIIIYR4UiTcI4QQQgghhBBCCPGkSKlmIcS+8B2VyA+WVOv6DA/1KT+fD+vLv8PP570Vj8Kju5T8lBxTQjxnD34Sk1PQcybhHvFYPfrR35NGtwAAM99c6ppLzs2F56CuQL+4MXjt5uudfv3JaD5Q5xzf0vzTF/tklVJa659+Ck9Gc/g0DSKb4+ijY+ce3R6JIqJSqvn7mfdSvHn/mfnmbdnT/nb7IIIfn2qerY/OMw+9Ob8J/sQnf+bLb9ghk0j3c/PFPvHbI5Z9f+jMbK2t67o5lX307Dd/38tr/+i13Fy5jDHGmOYS9hkP+wTOwM/BJ8fhzdcS7vk5zMxy5j88zMAAxExyAjpIRFRV1WazSdO0LEvnnHPu5n719l3T4dzPfLRhN9+/uTbf/IDWWmttjPE87ybc8NGv31xTD+TV3TsiKstys9nkeV5VlbW2+XwB4CZSsL9nR0RjTBzH7XY7DMMn+SY3B1GaplmWFUVR1zUA7DX+0gQ1AMDzvCAI4jhOksT3/ecZ8WmiPGVZ5nme57lzzhgThqHv+3va34jIWmutZWatte/7nucZY552TPPnEVFRFOv1uiiK5p15pFf823dcxhjf940xH80c3ARYb58/n+fnDgBE5K7dzCj89qvqT3/39mMiotb65jT7bN/8L6z5cG8mCG8O8/0d7zfPiIi+7zeXuf3F8a21i8ViNBptNpvmPHYzHv5ozPnbX+9Hj8bMSqkwDHu93mAwSJJEa73rYzbXpuYkfDPSEwfl9syrumaMCYLA8zyllIR7fg4iEiiLmhAckgNiNAzACNxcjG8OTIbm+Nr5SL3r9HLHAXlvR9odxzu6aqeHsTveCBjabZdj9AC2NaYYmbVlpVlR4YC1odrK1fjQNJOxq9X6bDT97v3VdJWts7ImRUzMoBQqBKNAb0e3fCAjKkRUCo2Gn97cMnNznCKgVmi08rWJw6DTiiPfN1prrbRSRmtjtGdMGAZB4BtjrseNT23seD18Wb2fzP/y9nyelkVFbjt8Aa1BISoFwKD2MFRrPqkkjr5+cfyHV/b4+NjzvCfz3jaYua7r2WL19nL8w8Vots4yAibyifTegi+OyGqNCkOEQTv+5uXJ1y+Ph73u/gIch4yZi6IcTefvriZX40VR2igKB20/iSOt7/8jYOa6tllRpVnhyIWB3++1e+1WpxV3WkkUhk3Q596f95Axs7VuNlueTRd/eX++KS3VluhRhnuUQq10oCHwvDD0u60kCnyjFTArRKV1c/nwPS8I/CgKPONprbTeJjA+sSvIL2qC3es0XSzXaZqXZeXcdYYdYnNjzgTwq2+Pb6aWED9+G9UtxugwDJIkjsLQ80wT+tnHCxSNJvBhravqqijKLC/KqrTWOuucc7bmm0mIe0fEBMoPg+Nh52jQjaNof8dXWVVn49G/pOv/eXmeVaU1iggBQAEqBgWIDAjI2u32WkkBXN9FXm87IiIRKg0M7IhQGW16Rv9xnf1Ha1+/fv0ZM2TW2vlicT6fjSfjsiwJb4Xhmi+av3HnQJK4L8zM7Dnr2NMGyDM6VOr4+Pi01xsOh57nSbjn50h2z2G6Se0h3teVQPwW1trFYvn27PJ//x9/fj9NZ1nFOmAGBkZAg6SRNDQ37g4A8M6o5xelFCplP3khdG4by2iGm57RSRQOuu1hlHhKoQaDyij0PRNHwaDTTuIwisIg8MPAD8MgCIJm4H57ov5Rj92do9li8f3Z5X/+r/863tjMoSNiBETUSBoIkRWD2c8J1PPMUbdtCXo+9Xo9z/P28SwPqIk1XI4n//zXt//lz2/P10WtjLU2KStUuI/jhYGZYeMZzzOes6860SbLA62SMHx60bRf1ARf5svVX96e/Y+/nP372XRVcrcdH7cxDveT7sRQWTvL6sU6tbbsBP7LQee033511P/2zasXx0dJEn/GrOxjZ62dLZbfnV39v/77v62yGqpHfMXXSoVgfc8kcdTrtJIoNFoBokIEYK2UVirwTRKF/U6rnURREMShiaIoikLf800zsXArB+EJH5XOuU2Wvb+4+u58dDFZVhasc0SkUXkIClkrRGJm9ev3B6VQaVI/SZRTSjGR0kohep5pxdGw3x0Oev1up9NOgiCQiM+eNFODWZ7Pl+v5cjVbruardV4UtknsIuJSN2G+fTw1M09IH/Xa//TVwPe073l7utI1kzfj1eqiLP41z94DOd9nVACgCQMmn8AnRoAC3U6P3IyvmrfH3mQgMBORB6gZwJGnMSAOysql+YuLi+Pj4yAIdnqZzFzV9dnFxXfL+Z+Xs5XCWn/4dcPOEGgmAChVsNP2i/vSHCPKj3PrAmRbZS2toiL/J2O4rtutloR7xCP2WAd9zwAR5UW53KSXs9XZPJtntoTy5pptgDTb5tTjcBtGORBN+Omnqqq6nWxvtA59L4lWXhwoRAQyAAhgENuRd9LtdJMoDoIwMJ04HvbanVYSBn4chnEU+N428edm7P4YR+3NR7xYpWfjxSSHeWGbtEcAUOA0OwDSDGo/zR99z2Q1vX6ZpWno3G4jpEeBmcuynMzmP5yP/noxvthUpTZFUfRcEyjcxzMCAMzQRVEUkCtSf5CEXx31Xp8etVrJ/T/fYSOivMgvRuN///7sn7+/+MvlKq0hCjexVxij1V5uCcARFQRpXoK1gYG3V6OTVvz1aV8hRoEfhsH+lhscLCLKi2KxTt9P5sucqrR6vOEeADCuNlr7vkmiMPA8YwwiICrnrMImdVQnkT/otttJ1IqCOIBBu9XvdXvtVhKHrTjyPc/obd7oo76C/IwmCrDebN5dTf75h4u3V8uz0dIRMbNppouADDgAyHCHWxgE1Kb+6K1CRAD0jG4W2QWel0TBcbf9+mT4+zevvn1xNBz0d709Fr9G8ymnWXYxmry9nHx/PrqYLWfL9aYsHRExE/F6VRI1xRv2sQUQ91+8SOtBCK+Oe/1uZ3/zRs65vCgypjTQpR+tFAEbQgCA0CqfwXcEAKtgt93MOA0ABEAKHAIANI9JBL5SyjE69JUKFHqWO1BvNpW1dteNb8JVk+XihzJ96+GyFX43uXK4He95ZBWBTwAAhQp3fXBxn5xarVZx5Plcvei0OlkWpXNdF9+8eRMzS7hHCHH/iKmsqk2arTfZMq0z9+EyptlptpoZAJw6rGXAiJ++FtZ1jbfq1zbjbKN1WheIqJB9pZXCQJsoUJ0gHPY7PmrPg24UDXvtfquVJFG/3Qp8bMdJGAZxFLVbSRzFvu89xlF7U0A1L8vVJl1mMEvLm3APgr0J9yDff7gHAXzPhJ5O8/xJxnpgOxSmLC/mq/VkvhxvygJ1lmVO7TePaeSqOI4DsqYM58e9NMutdY/6BvvzENEmzS6uJn99f/HD+ehylm8qBgBfbfZ3kDKzVZ61VhH5BhahXoU+2/Lbl6dvXhwfOff0sth+DXZUFM15htJF+qj3xQioWRrcxO1uSoAbzzTxf9/z4jhqx8s4CkLf91XWb7UG/fag3eq04l47aUVRGPieMVEYtFutOIp8379dQu6hX+L9cM5t0uxqPPnzD+///H46XZXN6hHDoNlqIMMWAHK9YzaByj/5FuV57hnP870oCuMg6HdaF5NZVVuPas/zeko9wwzHvWrWcGV5fjme/Ntf3/77u8t/e3txMV0s12la5uV1NcDA6+xvAxAxSBGZlydBVdX7vsyRc3ldbrJ8be2C65twT2DJp+twT7jbNtwd7iFfaeUYHfmofaVNbVctsJ97BXHOFWWx2Gy+X07P0C5cfTvcowl8YgDIVfR5jy/uRV5v8jzvtqNQ2dDXqq7yui4ImyIwEu4RQuwFAzummmxpLSj/wz8gAmNzba3MoS1PuOOUqEMAcLfLOjKgwwtgBFCoFKJR6DNr6wJXYnrhgTIaWn7QjcMkCttxNGi3XiVJ5HmBb7pJdDLoHvU73VacRFEcR77n3az2+pIv+LdgauppAioFtyocNX8Yod7DWm5EZKMrrWvEx1u69ZchMIN1zlrrHKFnjDGV2u/xokEjIhE756x1zWY8N81KrtU6nSw3k3W5zKrSknNckYM9Jzo5WwKBBVZOZzX4ipeFXWVlmhe1tQEfSqWzLwkRmMk5WzmoPPOoj/ck7G6LlTTLVaDJY+AAA2DgmqGymK2VShFAIRjPJVHY68T9JOl1kqNWq5X4sW98o3pJcjLsDnvdbitJosj3mwyVjxd8PUZEVNV2leaTVT5ZF+fLgnXUfOp2G+5xmggAZpH/8w/1Eb7jdEYmgGYOI69VYcO8el/a1PPbGoMg1Fp3O+3HdWk+cMxcltV4Ov/z24t//uHy//en7/98td4URWHBccAQgAZWnEZ7DHAjgL9JbWmJ95ZAdJtSqFVFbu2YksCh2c6BElmCmggANp8V7nEKALbhniYEw8wONTBD7QLUVmlTQhFprj93vIRIShVxsrL5pcup3a8UWA0AoNkqgmb6lkEWcz2kmn1rbZkE6WZufJcb+wcf+LpahoR7hBB7gaiMMb7nB4Eq6UOWhwbQzIoZ7qxI/oA+PZ7DW20yb66XRJQkyYfvsCNiskTkbF1qAkRagxpr5RnteyYOgq9bHV8Z31e9ODoZdI577UGvddzvdltJr9vptttxFN70QjrwwSUxK6V93w9Ja1fcVDpDYH19heH9hHuuK2jio773+xlNUdEm3b2pG+V5HjPrPReT85VvjIG6cs5ZZwH4KaUM/EpElOf5bL4Yz5aLdVoUpSPQ2nh7WcX1I0EQgHLWVUjgmmq1m3Q0nc2X602aRWH4HMuIIDZ9ykJSRf2Ia/cAQNNfr3G7y95Nn6Cbv6/lq006XS5j349CvxOGYaBDg+0k6kXR8aB93O32Okmv027FUSuJ22HUbreiKGxascDBX0Q+iZmrulqn6WKdpkXliMq6aP5J/zjcU+rddgbi4pPf932/mbhoWjUx8GSd/ul89HuDgWeCwPc9L0nix/hmHqCm1cNytXp/fvnnH87+8n50Np6nBVnHDErpDz3psrLc32YgombcBnr2flJBbuYF1ZfYhZRS2wEYAPxo4Pq5z84M5BDQ833fuUVR3BHuecQn5yegIoeIda0267VJN35R1e0heNuYuIR7hBD7gUDMDpiALX1YdEPsCFxzeSgPrRY6f3p24q57DMsIANjkBpN2zMCExCYIgcChqwhqhtwyOpyX9uxy5msTBqYbp4NJ3m8tBq3otNfut4IXw+6bk+GL42G33QqDwBhtjDnkeVqG6z6iSm175W7DPQ7YITTD8X0s5kJiLMnVfFgrAfeAiYiav6y11pZ7zu6x1mqtkYhBMXNTfXuvz3homLmq6ul8+fZycj5dTVbFMq9qQmVQaW9d1L/8EL9BO2ImVxErxwoAWKlN/dfL+e+mqxeLVbfdbrp373UbDk0z864BQWH5yJsz5FkNP+6+3LDWIipErZRCjer6nwu2BXNBtMwI01orqzQZwDDIOmHQa68GybzXjvqdpBOHvXYyDMzrF8cnw36v047CwPM8Yx5lsg85SvNysSnTCkrnm6TdfF8TIFgFTrEFgDDYbfEI8ad/frFYIKLWzcJqrbRn2UzW9N9/GHlB3Gp3WknieV4QPMcehfeOiPK8GE0X351P/nQ2/9PFau6CXKkKuCJwTMzsGAGgH+9xcRAi2ixFQCJye+v/dY0RmJhJIxid1mWtXVPYMGMCBp8JAKpyt/GSRxau83q2M6rbLl1oQSEx19aiqkEFdQ3Kh99SW4fAEqSlm+WF63VLs+3vvB3ZswUG3LHzsrhfTABKZb6uPU1hEARhkCTgth+KfDZCiHvWtK9q5nCKosiySoetm39VzApUk93zZeY6dnBHrZlmVvanw/Sqyq5bw6qm6zoia0AiUgCoQCMgX0eLEP3Q14AEsCnqrJxfjV2guB14f/jdq4vR9Go8eXMyHPa7nSTptJNet5vEUVM14BBHmbxtl1vkoJS6Fe5R6rql4T6SEW5/Co/63u/nMTCiCvwgCAJTVzWAcy4w+63e0oTtPGN8Pwj8QKlm532yb/JPOefW6/X51ehsNF3nFQEioFLKGMPaeHt+J+o6BeuISaMyRvvaY8RlWlxM5m8msxfDfhgGz68/FzNxXdd5Ts155qG35/P54Y+mE27O6lEUwXWOD1xXNgEAB66pkoaIRintecYDD3WapbayaZlNZ6vA175nAg8j3/+708H7q8npsHcy6A177WG/1+t2oihsooSHeBG5QzN4qK1t3oc8z5vvN+EeDU43tXt2DPgTfzpbJIoirT/Mrzjnyqquyur7edqP/F4nacVhGPhad55hvPV+NXV/F8vV+4ur91eTi8l8NJ2tLZfgO1SkjNJKa43aU0rl6XR/W4KIbd/XWjPfOZ93v0+olPI9Pww8H2rQpumlpZkUgU8EALDjeMkjgOtwT7Oka9suQykDChyB0gaVx+gBgjHw2RNkiOB5EXomN845bLqD/ug4ePTdZp8ADgNoApfOAWzH5+C2n7qEex6ZnW+fdixluuvjEz/NUqniN2qCIKhQaa19z/0oyZPxerG020Mp39/kjmwjZbb5kLxtdbn9vx5+qB2gGGB7OeWmQaZiDU0W75bJm+OLSJNCxYZZI8+revJv79uheTns/G6WHvc77TDod+KvX5y8Oen3ut0gCA5xvM6ECAoVs+MfFdFpvmw+3/vfYARUjA6QDi017P7cLO6wzjrrAMAY7XvhPt7P28Iw9H0fC0fOWWebZSV7fcaD0qT2TKbzi/nmrxeLf3s7mqxLpz0EY8mBA2f2e74ypEArDxAAaoKaqCD0F/XVqr6crb5Js3a7/RiTNX4rBGBApdwj3x/5rg/up+M0RAAgFQIAagIAC2AJsCQAViqsGNICEEAVpJTVmj3tLsfvjrvTl8f9N8f9l8Puy+Hy9cng9KjfabebQOFj2XOaySAEzWhAVc31lxEIQLMCdgRKM7Tcrrcwdxy/BGAByuar5ocUIqZ++99Hqd8ah2EYRVHg+61W8ljew8NERGmanY+nfz0f/+v78fejVc6+04oxAFCsgBAcAZAFgP7On+8OEDH2vVWRojom92U6EnDtXO2wRlcrbAbFlgkYaiTPQVDt2JmLCK7LM9+u4ON7qs6yyA8MmirNYz9s6QBrgs+eKWCA2iniFqmjqLep2LKpt/N7zWo4jQxGbgcfVMUMxEahwyBkz3PgsQE0zX2NhHuEEOIBMHNTqgHIka2ztF6tltPZvNdKOlHYbUfj6SJdHb08Pe11u+1WKwwPLujzsLdeT7lOs3gIRFSU5WS+vJzMRrNFmpdEdOct4hfaJM7LajSbj2bRdLEc9nueZ55bgo8c5Z/UnP7IMRLXjkqwvgJbL5ZpPpktznvt10edybT/zevTF0eDo+Gg3Uqamj6HcwX5pJsUp4dNLWTm2rr5Ov3ufBR70GuFw247DAPp0vXZrpfKLt6+v/jLu/PLySIrK4aHbDjIwM9kVgPhgIaO4suTcI8QYi8Yr//8OGmGAW5uodShZffsmO0aWPuhWOT2tdx6RT/5jlMWAIABEBQTAwKqGhQoVVV6XVbzsmhtIImpvaZRMV5siteTzYvj/suj4VG/2+00ZX0eTT75Xj5fBiREUs1e1QR9Hssb8hvt+3hx13lrzxAzW+vWm/RysTmbptOMUzb19YJBaqrHfH42/A5IAbAhUADgENek383TV/PsYrI4HQ6iMDj8O/b7h4AAih/3Yi7A3fafpkLNjxHcXKUQAUBtM00VAKxIbWrmsrraVO8Xxft5+m6yOl9s3hxNf//1q1cnR/1uOwyCAw/64PXrUgAaQDHzj141ARAj0XX76v3JtF+TNil15/m70fKrV6teV9ZzfaZmgd56k16M53+9mL+fZpOUStJOK3aIQICk+Tq4i6Rpm72yJ4gIwHet398TBYAMGgBpe6FFBkWgAAyBcTsu5mKC64yeZo9s5gE8BUzgEXgA4MB3EFx3zfjcXZeB2ZDzHQeOKg0VUVOrmZqjlVkzBHs+HsXPc+ARgecUWKVJGacMffi4JdwjhBAPzBjjnLMOa+s2WV6U1XK9mc6Xq0t+ddR78/L4q9Pjl8f9VydHp0eDTrvVdO96zoPOj7qkCfEbMXNZluPZYjRfn49n6yx3jvih49HM7IjGs+Vo3rmczKcni363I/kF4i5EVJN1ta2LIl0tp2N9cXX5h69fLtfr+Xzx4ng46Hd6nU4cRYcc9EGEL9TE6Gc1ZUrKyi7W2dVseTWdvzgehGHwHBvk/Wbb5uuz+dvzq/dXs9lykxeVI2D1GzpGCSF+HQn3CCH2ggEIgXC75P5GU+WNGABA04ENm3C3xccI9YfoOSpgzWi351XWpBzwj5ZdBJQDAIMCgGb2nlEBgGVAE7LyAEzRrPOqCEq6XFcnefoqD16v+MV4/c14+cc369+9PD0+6kdhePi1GPb0+WpSmhXQswv37Pt4cer5Lr53zqVpdjGZXy2yy2W+rqAGw/ijHUx/mVp1rBwqhxq4OZOgLfLJxl7O0tFs+eo0j+Poud1wUjMrTo89u2e3/cfArdLCtyKPzVWj8eECxNp6AXroASvi2lWW66ygpS3WPB1tqvNl8fJqdtLvvj4Zvj4eDnrdJIkOMFelKcbvKTBIHtcKKmTtEK5bD1kEh0yAUO65WEhOkSKlK56k9fvJ6mw0/+p01Wm1tNbP7QD8jZrUntV688PF9C8X8/ezdJK6vKa6qculSDOZptUmOs0Wm86t+2xNgIjeF288qQk0g2bwABwDAHgEisF3AADljrXhmuSn21V7mjx6MlAZ0BoIodLgGfCuEwU///ypmNjWpnaudsqQAqdpe4UCoOY8hNVnPri4D05FAFArVRrlUFmFFiW7RwghDkZVVUp5xhitAwIgIiJyziHTpqjej+fL9eo8NqNRsl6vq7Kqbf3i5CiJ48OP+OwJX3voDRFPARHVtZ2v1leTxXixXqW5tYfS85uZiWG5ycbz1WyxSrO833PP9sAXPyPPc8/zlNFNxIQdMIAlfnt+tVmv5svNWTcedOI3w/7Xr47/+PWbr18edzqHuDrppv3iw25GE9MpymrBxSTxLifz0Wx2POwFgS/hnp0QUVEUV5P5D2ejd1fT6XKT5bkjAKWJCNTzKkb2IGS89MxJuEcIsRd8/adJ8PnwfQbGbTl/dXDNlX5aK+Hn1ObDz2+zdZovAYAVNWvDcfufANCvS2jmY9kwgkPNrBwaIiaFQMoiOGBgzymFGitOcuZlWc5LGqfFIq0zS46hdjUzHw/7SRwfck7+nj5fJETCbUeI5zSCObzj5Ylg5rwoxvPVxWx1tcjSkuvrLLxGk6H4qVoq+9BUeFDNGgeLEPjBKivn63S5ydIsq6rqea3nYmYAxaAYH/Xhzrjb/uNTffP1dTZo80BNfijC7WUwDKUFQuUA0bGjShForRyakqxLIYN8vHHJPPt+kn49z3KrQs3GmDiODyri03QjQAAFZJA0g2sujwAAoIAQSIEFgLW/Y5hgx7WZBiOwrqgrLGme1VeL9GI8f3WybiWxUkoiPr8SMzvnVqvV26vZD6P5aFUsi7pyjMpTWgMRsgMABKvBIZEGi+AAYBbscZ9ExFCB/uLVZjQBKtjWZgMwBJ4DnwAAlsFuD1Xfyu7ZZvkhAEDtgbPAPngAtQfKA43XVTM/E4OiEm3h2YJc7kGlNWkCvnlEIgINzzc7+CBoAIRSa6ch8yC3UOkmMxZAwj2/6DEPLZ4yRFQIXzwTU/xafN1qXfwaWmsH4Jyra1eTY9JgPK11ZWullK/IAhdUT+qMqo3LN5prW5bL5fD0+Ggw6MfRA6T5SHbNXj14CzZEVOq59PJwzqVZNprOr6bL2WJd1Y4ZDqeihDGmKPPFajNbrmfz5enRMIqexXouZiZiapoYyrnml7RarWavsK5yzjFxs+woDEPFkJdVlZWrlBYrv6ptJ0lOvBIRj46OWq1tPbiHfgWHxfM8YrDM1rqysstNcTmZX47G/U6rmWV56A18NKqqmk6n7y4n55PlKivKqmZmbTQYU9f1L/++EOK3kXDPna4bvigEg6AUsgJS6DECIG7vZG+3MWGA6zjrLk9zf1v8KfTjpa9NJFszEBHATYbFhx8od9wj2ncs1aQ7KupXO07GNJ0Xmu0kBVYBaVAKtPGryqHR8DzuRh4fBAWIzQ2To9ufEl7PXANApfZ7AOidH97f6aeJPv7527ujZtg2OwIAcACwNu27Hgm40gBaQagAwAKUwBAYDwBYWQdQI+fImYP1Ml/86ex9av+wrn63qf6mcq9PjpMk+pIRH2ZmQAfKMlYEDjUD860OEcgKAOyOn++v/bwUg+LmmZ5kPKJpGoIIiArBA/LBtYFKq/a8Np49oAC4QkBEQgTEp/kO38bMzlGWl+u02GTFZD53zFZrh9xUH4PrC3Vodzs/7CqmNQBYIOSAFBBohwoANpYqh5clna2Li9XmdZZ1u93nsZ6LiZwDDTwoXW5Vudeoz+7Xi924Ha8vudrl5xFUnTVfGgTjbwdzpbPbszIbQEDCrFR2adW7mTJwVX7/N6+Lv3l9Ouz3D2SNUjON4BhqUCUah8oqDdfHYDNXTawAIKj3ezzWdAYAJgIFalRlZoRJJ365zIazRZIkB5USdciIqCir6Sp/l7q/rMqzdVWi4UADMFANH24KjANzewgV7DMQhIhKQ6WvM+b2/UkiE5JTVBmyAA7AoYFtH0Zo0mQCt9v1vRlemVs5Po2Qoa4gZjAItoIEoK1A8W+540cgheAh+QzEoJEVOgMAxoFm8AgAoNZ7rLUkfpGuiYhamoqakppiy0FTIKrZTx524w6fzCcdJkR4NhPPj5JkfuwDM1eWVmn2pzQtsmy5WK2XK1dWyPDy9KiVfNEcH2aGB/2QHzz/Za8e/NUdSPmML4CZ67pOszzN8rys69rxAdz03taUFtpkNF2sJvPlar0ph6XvP/31XMxMREx0KIWUHr+m19s6K76/nNbpeHTUWa43tq7+yHw06AfBQbSdOrTKbMzsHK/S/N3l5LtB66gVnQz7QeAbI/dQv6BZyZVm2XS2OJ8ul5vcOndAH60Qz4OcqsSP7JpscVc20xdfDyvEE9T0qmjWR7smV4rZMa3L4oIdE5MDBiCG2rrXL47areR5FfUQ4jcjorwo5sv1Yp2nZW2JQSlg1ZQYay5xhNeVQ/Zpm7+wzR5WgNuuhgrZgkvLar7JJsvNbLlK8yIKQ9/fb3bDgZB7w/tFzLV1y01erLPKApmJ0tozxhgz6Cnf9+UK0lBNrR8kxUDMm8qNFpvvLyavhp2vX560kkQq+PwiZi6Kcr5cz1f5bJWWtSWS41mIL03CPUII8Zgws9K6rO1sufIcoKttUWdZXlfVN1+96B5kpxUhDhMzV1W9WK5Hk9lkvt5kuXOOtTmYuj0AAMYYqpW15WqTjefLq8ns9Yt1t5UYY+RuU3wGYibnwNnpchN4JlQu8XTgaaWw1+lIxOenmNkxbfLycjq/nC6uJtNBr+P7UsHn5zBzVVXz5eryajxdrBabrKqtBHuE+PIk3CN+E7tjdg/KmV6IX813AE1eD4BWBKAICUCHUWSLdL5Ji6LaVOWyrGZlSVpro4zxvvCqLiEeLyLK83w8XZxPlqNVusorixpuVaFqMm2+TKNgQgUAwBoQFBAzKVQAEHiBcj5X1aasrhbp2WT5ajIbdjtSXlf8FtZvTyooxqkjUNpnVAz47RvsdTuSJQofzgCKEBQDMxQ1TZfF+WT5fjR9cTxM4i9aMu/Rcc6lWX52NfnL+6vvJ8v5JneyLlOIhyDhHiGEeGS01g6xqmuqaibH1jFTP04Sw3EYKHWcxPEzKbwixG/hnFtv0vF0Pp4vZ8t1VhSIey4g/1maQz4vqtlyfT6evr8cvRj2mrtNifiIz6OUKstykZXG5R44tiW62tOote60W5Il+lNEtMmKy8nifDQZnR4Nel3flwo+n8bMdW2n8+Xbs6vv3l+cXU02uXOykkuIhyAnKfEju45zd83u8dxujy/Ec6aZAKAp5eEIAABRAYCragYDXmyZM2BbFvlsaf3zwFN+GIPSL48xjiOtv0xSghCPEhFVVb1K89kmW2R2nhZ5ReB7zdDo+mrYdIf8MrV7DAAwAiEQMjI1uUVVmWt2wKpwOEuLs0U2HM1fncwHnXYYhhLuEZ8nY88q1L5eOvXDsqp5zoBJ0grDyBgjqSvbM8C2gg8AQ42UWzqfrd+OFt9M5i9PjyWX9i5NhebLyez70eztLHu3KKxVktojxIOQcI94rPCgiisI8QWVZamUCoIAAMiVZVVPykVZFL/vdg28BSYF/OLkOI4juRUU4pOY2Vq3Xm+uxtP5cpMXVVlV1jmFeGi3JFVV+QqA2TpXlNUyzc8n8/Px7KvjXq/Xk7tN8XnqujbGBFpp4LKqx4uipdyfe0nka0+rF6cncSRzBh8jouV6M54tLyez8WTW77aNMZLg8xEiKstqMp1/9/787Go2X6ebLCcTP/R2CfFMyRnqcykFAMjb8L/ibVWau+6tiA6rV5W63n74KKOHd0u/uetV3XWTeVf20K7vj3OOWdaqiCfO3Sp2tT2itjONHhDUQADglA8KQOFG6f/vn88Xp8dkAj8IgyD0PCNFN4X4pKZqz8V08W40+340fz9fQ5D47K/zyoQGbq6PAADQdMjad3KqQwQABqWoaQrkCAgAtAEgUMZTGkuCi1kaRMk0q1dpVte2CfsKsStlfAIoHVmmnKG2Ws1q/7tRGCZRFAdB4BnznBcFK1IAwM2r37bqMxawIDte5ReLdLRYHa/WURhKyPUjzrlNmp5NZu9n6ffz7GxVlcp76I0S4vmScI94rBAQJMFHCABo1slbN5kvQ8bAx3agu0mURIHWWkaiQvwUEaVZ/v5i9O/fn709Hy/Wm8oiER14OgMDWOfSvJitNss0K8oyikJJLhC/nSPe5MW7y7rlYaipHQZRGBpj5ApyGzM7R4tN+sP51Uk3GnZb3VbL8zx5l240i2Qns8Xb89EPF+Ppcl3WNcibI8TDkSGC+JFdO2eZh0taYrl2iKeuNAAACKQJAEBfH57ICgA0KacAGKjJPWCYl+TnGK3q7tWy2xm1W7HneVLER4iPMLNzbpPlo8X6YpWNsmpV2IoVo9ZG0TanhzQBIGneTiy4Pa+M5JshGdL2GG+K9zAANAV9DAPmrJYlnC/L8TJdrDdJEiulZNmm2JVusrmRkIFR1axSAM5ZX26iaNrrjNrtVhgEz/YK0mSjE2xz7gCAAa1yzHqR0w+zVe9sejwcD/q9KAqjMJRwT4OI0iw7n87fTpbnq2Ja8BoDVqwPbZWsEM+GhHvEY4WADHLxEOIDVKoo68li885wJ/Q6cRCFwQujQxmJCnELEZVluVhvFussK+vaudo6y6SMUlodcvcYZnZEWVFeTBdn4/DryWzQ7QS+/9DbJZ6CpqDVfJWeTxY/nI+OunE7iT3vWS/p+iTnaLFOz8ezt5fjV8N+v9P2PQ8R5V1i5rqu56v1+Xh6PpnPVmlelNaSxKOFeEAS7hE/suv5GHfM7nH3dCmUa6p4DnKPAEATaAWGAAiQlWZoZlodoCLYLmpsSvqE7bW19bLQCpNo1WmNeu2kFUee58lyDyFuEFGaplfz1dUqXZeucOhQE2rWPiICEzQl+QAUK80Wv8jUAt26AqtblfEIAbYpPrpGhYgrq85W5XdXy69PZq9fnLSSRG7Ixa58LmFbgVEDK0Ig8Ag8cnSxqv90uUySUavVblYLPsPFSsgIAAoUADi8qeOjnCLLxGV9tc6+H81fHE+Hg34rlhZdAE1NtKIYTefvx4vzRTrelGvLBRoE7Oy9+pkQ4tPMs+qKJyfi25qbRGwaO998c8e3aNf2WHeuwLrjeRkRABRC87dCbP5oVAoRAIno0MpgH4hnPtHUvPh7nE6640R5WOdPrXVVVllZzLW7jHQvUied6KjfiaNIlnsI0WBma+1qtRrNV6P5ep3lZV0DgNaaEZ1zB37mZGbraJOXk8VqslguVpthr+t55sA3+5G6r+so/2SYc7DDbwZcbtJ3l9Tx4agTD7qtOIqiSFJEP0BEIlpu0qvZ4v1o+upocDLoBoH/zC+yTWrPYrW5GE3OJ7PpYp3lBbFWWgEc2nBJiGfElGV5OJecz7uW/Prf+uRP/vSbiNgMB51zhPAkVwwhgKdUgMooNPzhTdg1W8fQbh9ZdccC8LtCNowMAD0CAKgUl57yPTKae2BCMLWtV6s1MgPedE/YaXOeJkRUSnmeFwSBMc/uHgARFaJndOAZXync8cRycz4k3n7JwMzb73MDgJmJGJiImPZ2Cq0VQDO37wBQqevi5KrZ3Vk1eT0MwAzAprbglKd0kNZ0Nd9Eio7a4cvjYTuJPO/I87zntjMI8UnW2jRNF1mxzKtVUVeOWCvle465LsnzPN3kzYFDBuQv1BSgmd74cR/4bZ4RsHEKgBUrADYOVFarRV6N5+ur2fLF0SCKwmd+q3nvEFEr9Iz2jNFqt7DP9fUCri8dwMwWthcVYnbERES0vZrs5xX8AsMVADhQwOCQAAyAcgik1Tq3yEWvlf4wWpwMJ9122/PMc7uCNIfT7YyUJv8OkbUyVa02pR2vNmfT+fvx9PXxMIkirfVzPgydc2maXU2m70aTq9lmmuUZEZtQ+yEzQ2UfegPF50Optv2Yme++++72leaLXXV+PvLyi1eUT/7kL0ZzbvIdfvr3zb82/8nMaZrOZrNs0LPMTyzi08R6er55E4Z93wuVUtdvA5Lb6YDWtFtypr2rUf0dOx4jAUC73P5uZVzhsVb8EvRxWo8vp/9l9V9jowls7SFQMxZ+1ppYTxAEx8fHr1+/7na7nve8+l8qxDj0j7rJi0G3GwXe7jUm+Xr+nJiZmJgdkSMiYkdkHTlHtXNFVS/zMiurqrYPO2q/UVWV1trzPKJiud6c2+wo0V+d9Fuh10qSpkvXw26hEA+uKdyzXq/XeZFXdV3XjkhpT2tNzjnnDv+cyczWuU1Jo/nqajpbbY573Y6sJblfCjEOg+Ne66TXTqLg10R8tlGe6/kAbsI6zI6IiCoi64iIrXNZWW3yMi/r2rpmzoCYH/wK0lBKlXW9cXaxCs9Gs6NW0GsnURi02y1ZFHxbVdtVmo/my+/OL0+6rW4cBEHw3IJiN5rz6mg0/uH9+ffnV7P1pqhqR6y11r5PRFA99CaK3V1PcQI2CywAET70Rf7o64fZwgd63tsO4YDHT/65PheZ/+efx8y8jXFsf+P21wA/fit37dx0F/7Ush5ERKTrTbjj14ARsEmMBQDU23mT5pcQm6+3G42IzX82v6ABmQk/ZOfyzTuitouGuKkADAB5Vb2brq86kJGumMHTwK4mZoXoR0yu+X3LgAzA19t8x/uj7nhFbs/nv4BXAGDRc6gJjVOgCDwLrSwb/Jf/+Z9a0T/8/ttXnVZA1nNOG5NeJxAwwu0ZzW1obPupMTI0tQx4z1c1ZAcApQYAUAymVH6OwCqn6rIsFn/z6v89n60ccaVO8xYALKP1Jx/nrnCS4k9nFWn96bDRXT9/t/2OjdaBBYBOiQBAiBah8NlT8E3g/8dsYybTOI6fUoKPhgqaHhnbue5t8IKqEonbkafYQp79rjf840n07Un8t18dhWHw61++uz4tEVEzBicm56j5T3J0HfehqrbrTfovs/pyulhssrysagJLaAGAzTJdK/TQNwp9UpqZCZiZQ1fu9Hq7xY/2Q0IgBAtwfaLZBltx2yPP+coCAwHVCFYHNRq1dp3RMhkMTjZZGEZS4EM8c03q7jorZhW/ndVXG7txnlPKMdRFAQBRqAFquk6ss6AAtoWQm9PD7VGQ4m37Hrju43P76Mq8CAAQnHLblj6ATjMgMlxnCjBuO/4AAN0aQFzX8bk+A2xHJq450g2DYjy7ql/3TybrYr7cnB6Vvv9M7zPvi7JkjKk1FS5HA3FkBpH7p+P2P75Jvjo5jj2DyIwMwITMCMzBJx5lmwLKxOyco+vLh7WuVlA7VztXlPVynV7NV/MNZJXKrVum1SYv0tJZZgSjPIMmKBk3m8LaqhW0jTFcsdY6juM8TwGAVQ3gSFlAx2gBAF2y0+u1SkGzP6PVAAB1sz/bnJQPNel3q1JPy6BThe+n3VanWaz0fLJXcnP7OvthapMZLCB4CUMyr9WfxgxhfXRqX08XvV7/KY24fr2m1+Fqk72dLP802vxpWr9bVxV6fsjAGecZAki52MfLbyXe5fvfG24F5ipd10pbpYANAADr5to36u2xNhMDpCZngE6NYa3bNSJ4DMisR64K+/1RXaSbMooi54CKKo7jErL9bQ8AFCYEz+ta7hTVoHY9wEQhs91QZjUUip0ChwrYNIGKabzHjWGAIK7G33/fwt4/nQ54vvr73pFXWa+VoEIAMP/b/+e/AwDe3NvjdbjnJhxyfWPRfK3vqUzKT2+/t9FB9UvJfh+WVAAisKbmd5m5Scu5PRRDZmZQsM2gNQzOWcRtdIaZFAMAKwalmrxbx8BNKrUj2uT51fBv145IKziI4N29CYMg6PdPOvH/6dWLr1+/SqJQIwLiLuGb5o3e99tyawTdxJcZmHm53uR//suf8+xf1ssJqKLEv6uMI5q5u8I9d4R1+NOnp7sWl93183dv/n5niadxDQDHmQIAq7BWsA7ZKF5gOsD277Rv7TOqjcfMRKSYA2NeDHv/8R/+8Me/eRPH0Q7D0ybzHrEZuzQBH6JtxIeIrGtQWVXT2TyZZu9G7clys8qKZVZOFulss8myLGkldc1FXSMAer7WGpi/cJEpYq6tm63Tt1fTr4ad37046nfbUuBDPHPMXFX1ar2ZzBeT5SYva7fPA7NZ+tl8jYhwPQV1D48MQADLTTZerMaL5VdZvtu5TvwSpTCJgjenJ//wh9/9/tvfJR+K1/D14vFPvNvcrC5nvr5wOKLma1fVtSOyREVVL1eb0WI5W6bLrJiu0tEinaX5Iq2zqspzm1dlVdqw0wuCwPdN6IWIWNe1tbYsd5sz+Aw3L7Oq7Wy5+e58HGl+02sPeonvP/fyNB9xxJu8vJyu3o/n8yO/KMvnuaySmcuymi6Wby9G31+Mxot1WdfEu5b3FIfIGNNrt0/TTlFnla2CpFOjcgqvwz0KGRChHd5ZnfW3I+bcGAZIFIbGJB4yKweY1zQrUldVzHSzROcLjXLjWHt+XFStmnqOh6gTrTTqvlJWQanAITtEZKMBELAf7murGICYwzA+6h0nUXziheDXR55/bMJup9OkZJr/9u4CAEAhIjICwvaG/65wj7mnm0e+4/Ybsf7Z3+LbFTQAAA0DAN6qrIHEN7NtSNyEgW5+XlnL23eGsdkGIkRQzY+QZWYgB9Bcr7kqKqsVKvVIz1lIHgCA0gBmG1lnIITaeMHrry+pnOXFV8xJ0npEReaaBe+exhxcalSuvYx4WUPl7MR+ev/ZNXxz11uhYNe1x/t9S9dkASByCgAsq5phReQDrFyZYVDX9kCSw/fNGIPEWmtEUlobo8LAG3Tbg34vjuPPOPXfnE/gw2QtA0BTa6FJEEhfnr6aLUez4Wg2n67S8Xz5fuyPl94iq2tWi03OmbXM6EhDSEBIbv+nkR/t5444L914vr6arcaz5evjYRQ+x5GoEI0mjJsVxXSxHC3T2Tota7u/U6SibfaugusQAQPjJ2oBfUbnL2ZGpZab9GK6upou56tVt9N+5qVD7hcCGKXD0O+2m0tJ9OsvJT+9djQBoOa/rLV5UazTbLVJl+vNbLk5G88uZ8vRPJul6WKZTVflelPUVWYca62NIiC0yABkbaXuShe/Jzf5/ta5NC9H83XsqR9ezr59PWh32rJm8LZmWWValOPFZrZKVutNs276ub1Fzrk0y68m84vJ8nKyyorSEcM2kVE8YkqpMAhe9YdVWXYW81c2LYkIkN02PQRZAYBmjKZ7XO/hiOaeDwABKQ+0AmTG1NU/rNfkaOyVuY+ZdpXvyCkHVPs22PPiwVaaxbrur/OXq+wP2ryO4g5qrQ2RJuIK2AETg0KtABRgO9/XpZkZHFGZUVG2QvZjpYCTIQenSef18CjwfUQ0aVEB3Ar34I/CPfSTcI93T+GeOye68ec+n7vCPTdVVX853FM34YAm3OOaTQEA1cQQyDJswz26Wb3kCJR6YuUuGGC1SbHfZtP+980mfPfeM+ZoOAiC4LGMFFWz6AzBEdXgirLOC10URel/ev/ZNdxz1wL1ncM9e64lVFsLALVtsntUraC2hAoIzGEsaP1CjDHgyBijAbUiz/OaVUtK6ftavvRR4IyZ4zjqdjqvXxynaT5fri+ms7ej+WixnqyL88kiDHwvCDYFlc5Za1mj1hq+bIIPM9fWLdbZdJVO5ot1mnXarWc4EhWi0aT2zJery8l0NFsu09w6t7+Y+K1HvikUiPCTk8lnI4blOh3P/fF8NZ0vTo8Gj2ja5rFARKV1s4jpt5w5P6qSmSRxr9u11pZltU7Tb2bLs/H0bLy4mi/H8/RyHo9n6XeLlAkJsaoqIGTGpv7aXdOl90gpxYqJqKrqvKxXWTne5Ov12rlnlC/8KxFxVtaXs9Vo6o8n00675XnPq289EdW1nS+W51eT0Xw1W22q2jVpbuKxQ0Tf948Hg8D3Xx8dV1VFRNtzGX8o2rNdN7O3zWAABzVAkyKLiEjEWVH85d07t15sAI1WGhQAKKXcF7kI2rpmQK+u/6D1/+X09NvT0zgM9fVVgpj4puxMU6tmb8lP22pxSMiASiGgUcp4fhJH3Va7qSZmqu1ypmY9F9z0LqZb2T3NhaX5+r7O9HRXyPdnz4/M13nQ1/nRGkDTh+9/+NebXwFQfOsXt9NsfL0CjJuqPY4RGZmQebvHMjMiPPZFXIoCANCgADUpAwAMUCOUFr5bl14ndF5s5ytUb/8R8Wg4CB5Jpi4DgEIHUILbAJSKCgN1oCEMP/0Ld95mfzobqLCfDuvonUfp+x2WNWGs5sVR84e28ctnxTnH1tU1owaDqulhRcz3OCz+aOjWlMQ2xoRh0Gm3hoPey5PB716uRvPF1XRxNoq/v5ydzbLRKr9crNKqJlK+7++7LwX+ZIUlkUtrnKX5dLVZrjfHw77v+89nGCrEbUSU5vnVdP5+PLtcrLOicrTHsLihAgAQrm/8EEFpAOBb1yO8fY7C3U7cjmFTFKPF+mK2HM1XX23SdkviuXtwH+G5j9qGKKW01kHgR1HYasX9buflcf9vFqvpfHk1nr27jN6dTbTHyw3ltS3LoqydRg+90Gio3e3rmgImvNeqKMyslEKlmqVotXWVo6J2eVE690VnLB4FBiiqerRYv52Yk8txv99P4jgMn1GZPOfcJk0vJ7P3o9lokW3y2lJTLnX7A5Lj86gppaIw8j2/3+neJE8AwEf3x3tNOvzoWRmAGfIi14Df/bkY2SxjmGFVAZJHADVrgmq/B+DQ2q9Rv0T1Jo7++PLl33z1VRRFN6mXP53UUfs8Ifzo/bnWXGWam/pnVzfr+vx7KyQJ+EwWvNw2GA6Xl+f/vpyEw37i638eTZjoH6w9OT4KH89yj23GllLa14lKnHMZfnpZ+13X3bsWFt/VwEjvuqvwfjPDmu1sCkuzUqRAa9TqWWX2AADUde2qWhGSQaW27bS+wHGNiM3JdBv36XRenByvN5vz8WzQv0jejdX5LKvruubKPUzfLgaorJuvN5PlerpcvcxyWc8lnidmrmu7WK7Orsbno8l4vqhqtdejkoiwmfQE2OaGNA0p7i/LzzlKi2K2XE8Xy9UmParqZ9sY6NFpPqbmCuJ5XhSF/V7v1Wnx+uXq5HjQSd6rQe/tVTpebKaLDXHJrADxC+TXOOearWr2VUfkCGrH1rovkFj06DCzc7TOiovR9H239eJ42O+2fd97JtfZJgVstlieX07ORtPRbFE10R45Cz0VN7GDg2rM10Sl2+12OwhCrEPfqLokuk4A2WZ17FFVVSqIwsBvmaiVJEmSBMFhTabe3hhT6eZbgAh8/Td8tAbl1sbve3b6l9ISbqoe3nxBTm0/WeTr7zZVpa//k279NGitHDeleoAZEOg60cchMqqbUs38obvcY8YeACABosHrmUNCcMaz7d4kXf2Poir89sJX4/nK4dl/QDg5Pg7D8KB22Z9CBAQg4EqD9ZRzUABY4PyOschdpZrVHWN9re84qe2xEtnn2HZMu/77+m7i2YV7PM+j2hKRtUymyev5EuGexu04ehD4cRwmSZxEQeLrAKy2WeDK5TpNK3Lebp1Tdt6S5saSAQDcNjcTLfFkkV7OVqPZcrPJep3282wdIp45IirKcjxfnk0W58t0vC4c3ZENeh8QMaYCAEAbYgOgCTxQzKyuc963jQg0w/XYyt/tObRiLywsXa3Si/l6vFidHGVh+GgWZYtGcwXxPM/zvCDw4zjqtOJeHCZvz1vq8r2q/TobEVaOK64KS2g0bHPwmx3JKLZwf7nEzjlmVkpvwz3OWUdVbUvrrHVEdNd82LNFzGVlf5iXvdHieDQ9Ohq0kuSZdDFzzm3S7GI8eztZnM/Tq2VWMTJoAoXN/wAAJUr4FBzauFEpNApDhR2FbaPCknJrGQHY7XuuHQDqYunFQQRegKRu1pgd2Ft044ACdV8MXtcOQ3yOeT2NxeXlUb/vG5xfnf15vfZPB07jf5kvvDw1xhwNh4e+4oPhZv2ecw4ql+ZpVVVm8Old+q7ZVHXH52/vWMyldu3MtWcOHAA4xwDgmB2Dc+QYGLeLOR96A7+QOI6RWHOtwSkFWmvVxLC/rOZc7/t+V2ujtW884/l+GAbR+C9vL7LJ4kHOOUS83mTzdTZdrOarVbOeS8br4llpyqsvl6v3l1fvR5P5Os2LkuBTjbTvAyIarQLlISrLYFm5ZvH+vY6YlVKgtSM7W67PR9PL8fT18bDTSiSe+xg1H5kxRmttjPY9zwSB5wVxGDBgyYt1SVQz2D0XIL0eLzWXM2Jyjipr13m53qR5UbTbLbl8/JQjWqw2V9P5u4vxq2Fv0Nn2wXzaRyIRlWU1mc7enl9dTBbLdbrepCqMnsBcuXgsEFWTeYSITAREtwu47I8xxvd8j0wz1b7vp/uNDDbLQK6TYm7yY7bTBD/d/vt6A+98Z+6IhdP1BuF1w/hmc5yFpnsyNCk5fNMjk/n6+/jhB6yzzMCMzKBYMTM0N/CMDAqQGIBZwU3P1D3bNfa/axI43byfSIaUu87eUp3u0lUawDs6XYD9PyoeBCZP4pfVJjy7UEr3e72DTkZtPh2F3GSiKQRPIxhLnw7T3PW23VWLB9Wnw0a77/77PeM0H9DN30oBKkAFRuumPlaT5PK0BxwAUFWVc85o1EoDuKYDykO95maFV6uVvNbaaAw0+GyDetNS9b9uNDE456qqUkoFQcDMZVneX4pss6PfOmxZWcUV8rqop+tittykeZ4k8X1VsBbiUWj6BE8Wq4vZ6u10/W6+zkjdV40zrTU13baJbF0zcxyFx/0ezy7bScsqryB/VlSLdWYBvShhNACAQJodMiDYu/JMfx4xO+TK0aqky1X5frL8ar48HvbDMJQb8seryfTpdNrfaB14XmzQACnk//nDeWptq52sCwfXqca4TcW/z6FaFEXMXNc1IhpjiGiTZtPFOnMny9V60O9LPPGniDmF8O08b43mr8aLk6NlEsdPu5BW0+hwnaaXs+X76eqHy/nlqvCjboF0k1vR7J/q1m2lEPcJwYHzAv/s/VnnpL9cLZMwLp11e4+KA/X0hFZv/F5Z14dfnurpZ/cgYhPk+2he/aPv31RSfD4JETcYwBItqvr79WbKpXp/buv6b7/+6mg4POQ2H9tebA+9GYeJmxjo89uZ4cOitofMq2zWdsVx9OL0RBtPa09rpTz/7IfNpqhvii98meXQTafYdZqPZovRdLbenA563YNagy3EvhFRmmVXk+m7q8linVnie5zO4euxRBPqBeYo8F8MO9989Y9Hw/7lfP3d1TpnyEpEBmNMbe/nxufmDF9bWmzSi+lsNJt/k5502m2J5z5qt+cMlNYOMK3cIi/Ly+WyqgC+aCyPmGvrNnm5TrOsKKU518/YFMXFZP79+ejVsDvotH3fe8K1tLYx9Nny3fnV24vRZLEqq4ogPPhEB/Gk8IPeCPK2Nsyh320Z3C4eB7hVFOfjb922/+Knn/72HVP119krN0k9DMyEAIjAjNjc8jIgMLNmYMXstv9JigEQyGgA5pqBGRTjtsQNPYkkTNLbC7MCcEga4KYPCQGQgloZxSo1oBBXqP6zF3/Lxfl0WTL/I/PJ8dHB9upqPp5tchqAJiACdVdKxx3fxx0bpePOc2gyMHoYD55a2dz7RVF4ctRHYANW1+W/r7CeLJ1zN+eWZlh/XwPo67MhOQUf5nvZOGdXeT1ebUaz1Xy5fnFSB0HwBM5vQvwazGytS/Niuk5nabkmLJRPmu+roMTtoZ7W2mgdBn6/Hf+H3w1ev3r11/NRVr1dF1XqO3IalSGwAKAAeNsEFXBbu2e3xWUWkFGh8itFK8fjTT1arBebbFhWkn/x2DWXhigKT4+HdV1leZYV+boqVxdzrYxlTbg9y2uCbWeuvX3gRFxW9aqyeVVba59DyvBnKFVQQW3W9vvJ4vVodnI0aLef7MrKZnnsOs3OxrMfxouzZTkvqWTDWhGom11RMcHNjvkE3wbxwJqVOxaRQTF7ig2S1k4DOcD9VhsuNcx8yBVQTYc/v/6UJ3h/Gq1pStje+vY2owdvfX1zGXuSJ+ifQcwF0b+fj5JhR8fB/xjPkAgBjo+GQSClH4X4HEqpMAyPj4Z1XY/Hs5Oena3z2nFtbVMCk5nvsU3PXZg5L8rZan01WUxmiyzLkjiSg1o8E8xc1dUmzVabLCvK2joGIKL7ypFoWoQ0Mw/GmDDwQ98kgXlxdPTVqxc1wQ9Xi8tF7mUW6c7CcJ+BiLblYJGr2q6yfDxfTmbLF8O+FGx+GrZZoifHf5Pm03Vxvs5Gq3JR1vbLTiQ5oqKqN1m+SbOirFpEsnf9FBE5Z9O8uJot319Nvz4ZHPe7YRA8yQo+RFQU5dVk9u5qfDZeLNZpWdsDv+MVT9LD7nUMX7QzzGcz+taNRpMA0Uw28a2/txk923XCe96iO7It7mz+iLcadfH1FwCAihUANR0wmJmhSQVh4qYtFwISMDMiEAKyvu7vhcRw7yUVH0qtq5uPWN/626GCJsEHjdMKWDef75/a3Xe69bd++Pfa3yw38Pbtf9DqaDA4zFwAZFAEhqByYAiYwNCn95+7a/fstkOrHbOBAPc7KLNSn+HgKaWCwO912r122G+HgaeN0VrrJtBDRHVde553L8+F25k0pRjqW7uq1rqytM7rZV4tN1mWl86RMTJDK54F51ya5eP5crRYz7MytVSSqhmje9r9m1kivo77+J4X+ib2da/b7rZbJ4PucTeOfG0AnLMVAXoeABArRKWgBmb9Wf2ta2ZPKUS0RKnlWVq9m6UXs8VX67Tb6TzVtIJn5cOqrtOj8WJ5Nl9crLPleI7U9Ca9qZBy+//uGTMTcVZUo+Vmts6yonCOtJbLx8c2jog0VHSxLt5Ol1+PZq+Oh912++lV8GlKO82Xq+8vJt9dzs6X2bJwJahaKwTFP8qCJ8WwzTV+Uu+BOByKQNfKVMqrVKB0UAIxqtDtu5e4AVDAGqApi3vQ+/eTDc/jp/z892//683XD/sqvrzg6CjL879eXl1Wdmr8/zlb/OsPb2fzeVVVXyAHQYgnSSkVhEG32+nGQehpo5VnTDM72kR89r0Bnuc5oqKsiqpO87KQ+gvi2SCiqqqWq/XlZHY+mc/Xm6Ism8S6e38uZkYA3zOhp4+SoNNpx3HUTpJ2Ege+0QrhvrP5mvpfzWtcZ9linc5Wm8VmU9XV4c83il+jqZfc63bfnBy9OR6c9rtJGGj1RQeozFzWdrnZpEWRFaWMBj+puapaR+s0u5otLiaz0WyeZtmezjYPiIiyLHs/mvzp7fkPl5PJfJWXJQAopZ7YKxWH72Fv1bHpIXXw4QJzuzPRtvcVAwC4261dbv2f3vdJ/o7sibsuLtRsEMNHRYUUKAbeLs66rsdMChXhzfd5W+IHANGxUoCErAD5el3Xfb6uB+Oaj1IzAJC6fhsRlAMNeNO3q/lj8tYRQLhaj/9LSWyMMqE/m2ul/kg06PeDIDi0fh+awRBUtM3uuetDu+v7asf9+bBevHg8PM8b9Pvf9Od/aoWlJSJiZrpO8NnnMysAUEbXUFTWZYVdpdlqk1ZVHUXh4V+ihPiNiCgvislscTVdTFfpuqhKQkKPNd1jLcImu4eItFZJ6A870at+u9NuB77fSsJ+O2lHYej72mcocTv7ja65AN3VIPKXXxowKHTAlrV1ZVa7eVaOVuvxYvUmzeIoep6zVk8PIgaBfzIcfHUyfDmav5tHWZHZyn3YcbYj530F8Zt9e5WXeWWLsmriF7JrfcShMcaQq/K6Gq+ys/H87Gr68mgYR9FTSvDZpvbM5z9cTv56MTmbLqebLK8BdYDKkOObOwvd5PUwKKliKfYDARE0Ijo0tTKgdeEZUAxKgcv3+tSalG+1r5UCdfiX2ieb3QO3An4ffQaf/P4nP6cD//D2YjyGVouHw1WWfX81Gtdu6ui7q6v/47/91/OLizzPDyop4Pl9PDt4FPHmZ6KZnh0MBn//9cv/9Ic3v391NOwkYeB7nmeMua+VXD+jWTtWO7fJisvx7OJqvN5s7rGGiBCHqaknulqtL0fj0WyxzsumwIRS6t6nLphZIcSB9/Ko+7ffvPj65XG71fI8L4nj42Hv5XG/32k1R/39Pm8zm2WtrW29zvKLyfz95Xgym5elJPg8Ec0VpNNuffPi5JvT4YtBL/I9pb7o9d0RbbJ8neXrNKulSsunNCvvtNaOOC3K8WL17uLq/PJquVw29a0fegPvBxHleX51dXU+no0Xm1WaF0VprW3ueJ/MyxSPxcPe5zQJtod/t2Wqnx3w3O7b1bBqv/kN6o6JbuUpALiph3SrMNL1JiIq/lCf2dkaABQDQNNv/brVGBI0pZWAAej6CwAgVASOSDEzAYBCBsTCD5RSCAoZEY0BsMBcs1IGmjpHCKw+lFDBHRus7jqtp9Sn79DuShBgjpsvtr/2k/geclNepvn1ipIFrEbgNHXCS5v85wx/0NHfB/7XZZZ/d/EfUb98cRKF4YNX6dumzwE4jZUGMJAy1AjuriNOf3qDLdY7PS/vWmFhz2cAiwGAaT5cB+RwW+ZKowfMQHz4IednQinVarf/8LsTDWXHSz2uN5BOJ+vcat9rm1p5DjwiACBVO2Vr4wAAXbTTs6wCAwCBBd/psFIA4BQ4hJnNg1a7MvC9raNF+tV0/e1iPej3nnCPWCFge2dSXE4Wf72YfzcqxkvlbNfTIVivLEvws50eLbYWAGpUpMChcritbxiF3no+Q5u/7HW+HdIfe/X/+k3vm6+OkyQ2xkRheNrvfN0Lp0fBKl1fnV/GvaPmAR0qZGMRGPzPeHVDa2BjnQIfPDYdcrhI+ftR/s3L+mK6fHGUScHm3ag1KAOQAAdAHrgIOADATySQf3GIGIbBcb/9VeL9YxKVJvrXZZmDiwbttcum5UUcx0HR2u0x6eNuLXRdvhPg49GLAz1JebTJNnleVSVz8tmv5amKvAw4IwVgYO7o3+eZN1odTdfd7rzVaj2NWlpNl8NVmo839ruFe7t249LUXgsBanJAzmi4ueEABAK4t5L4QtzBoauVVVAC5a0q1AU55/Zd29Qy1YqQWHEThNjv0/1GT7kzl/iNiLkimNb1d3kdxBpH8wDZM/rk+OhAenU9eLPtQyaBnoOilPJ9fzgYIKqC3FVa/HmVaZXCl2orwMyOuKjddLl+dzkaT1998+o0jiPZScQT5pxbrTeX49nZ1Wy63KR5kReWfc3G3GN2z026kO957Th6eXz01asXvW7H8zyllOd5vW7n5GjYuVyEvhcEnxPZ+ZWbUVu3WKfnk/lo2F6/ybrdzlNaRfKcNZkjSZIcDfqtZOZ5xhgNlprLxxe43BNzVdu8rPKiqG0tSRw/zxFt8vJiOv/L+8thEhz1u2EY+L7/2A9GZm5KoV1N5+fT5ToriWRPEM8XAih8DNk9D70B4sDUGgCADLCutFp5UCgqHGMQFKalVmvv7Xul1fHRMPAPsVeXEAerifi0W62jfm/YbrU8Xyv1ZY6h5lAlIstUVPU6zdMsr2sZsounbDsRvUkXabbIyrSsyrquncPaKl17Rv2WjA3azjYQADCxVhBqkwReNwmH/Xa33QqC7a2d1jqO4n631UnCxMNO5Od4T31brxsywXXyiSUu6nq6SqfrdLHenFSV70sG3xOBiJ4xrSSJAr/p8IjONpndX+AjZmZAtM455+QO/5PwuvaoYgCgytrZavP9+ehFt/X6aNDttM29Rpm/PGZuuhxeTeeT+Xqy3BRVTTKKEOLgPXyChjhwDGCJvr+4mhMuvfBfJtN/+f77yWRaltKdQYjdIKIxOgyCOAh8Y3zPM9ctuvaq6ZdBW2wd1dY9vXYhQtzGzLWt0yxP8zIvqtpaVOom2+Uejzsi0loHftBpxUe9zlG/107imydCRM8z7VarHUdx6CdxvNdb89rScpPNV+lkvtyk6UGV2xO/kdY6SeI4CsLAC30fAZqq/18moodKNSXJ5cLxazQVfC4m8/Px7HI83aSPvkUXMxdFOZ4tLkaT8Xy12GT2kb8iIZ4Jye4RP+KXBgBqpRkNGFUYKIDWbNCUGdQzL/iDSbL5EvX7f1Bq2O8Hvn8Iq7qEeCyUUr7nhZ4XeV5k/Fw7ZgXM6sdDpiaSel/zgIjITEROXy8ypm1zMBmoiafpeiK6mK024/lqusny2nleEGmoWQE4zbDrfAXdNUPmnEFohaYX+8e99rDXvV3hDhE9z+u0kmG31Y39Xuyn+arkm3aRBKAIf8vcCRNuS64QYsU0z4rLxfpyuvhque52Ok+jaIho1nPFUdhOoiQKgsBXqSIi0F9o7XazS8tV4y4fDmkExYoZKgurrLycr0fz1WK17ncf8cHIzHVtl+vN+6vJ26v52SJPi8pJnpcQj4HcqItfJTk5WWXZXy4uLspqos1/G0/+x3ffjcbjoigkx0eIX6mp4e97XhKFSRgEvqe1+gKj56ZfhnOOaLsGxbl9N4AX4iExc1lWs8Xiajp/fzWZL9dVVTULKptkt3t8rqb/ehQGvVZy3O922y3f925PhGit263kZNg/GXS7rcgze6yn44jzohrPVxfj2WS2yA6sn6b4LZRSYRDGYRAGXhj4qLAJ23+51jCIzTyBBH1+DQaorZvOV5fT+Wg6W6fp423RRURFUVxNZ+8uxu8uJpfjWSUN2oR4JCS7R/xIXHkAUBgotAKNYLY9rTaJhmHnckFQFNZrl0qtpiuG8/9FqePjY9/fV+1JIZ4YpVTg+50k6UVxYgxSSZX12BBu+/Qxbv/cI0RkYiJi1UR7kJkdkYzVxFPlnNtk2eVkfj7djNb5quK8Zs84QCZbEzDs3gzr+qj8UPQHGQAtgvWU6UTBaa91POi1W/FHFToQMQzCo37n5aA9uJwlhuu6roCADTTpPZ8198bbnCB1a9ugBp1be7XavJvMLmfLN+u03WpJweanoVkO7GmtlTJawd1dWfdBKbUtFCUXjk+5TtH9cKA5gMLq8SZ7P9m8vZodDfpJHDXN2h9oGz8TM1trF6v12dXs3XRzuazO5xtnduscKoR4KJLdI36d8Vi3Wu3hcJ1lP4xGc+KV51+V9XQ6fcCCr/zQvVGF2FVTbjMM/NA3CsFZZ6395V/7zU8KAHw9KUvMtXUk4R7xRDXrDuaL5dnl+Gq+TIvaOmdvud+EFwTwjO7E0fGg1+92wp90rmzK93TbrZNh/6jXjsJAqT0GX4hotUkni9Vkvpwvl5KE+8QwEfF2Fc1NZ64v8LzNgjGp3bMTIsqKar7O3l9N355dzBfLx9gkgYjyvBhNZu8vx5Nlukrz9SZ9dK9CiGfr0Wf3YDOoui5Cwcx830tJlVLaGERsqqyhQmOMvWOwuHMhm3sac+76vHcN/gLlW2vZkWeAfO00QJVCvoFB380n2WZ14nsvozgAFXh+0uo8XKAHGLjpfodKgQKllFQREodvW2ShqkPjk6XI98kyWmiK6uzvSYkoDENPcV4UzADXJ7QvVuZTiC+GiLI8H8+X54v03Ty7XFdkImOwGSUEgUEgcCXgjpcMVs1f9OGIIc1cFUVn0DruRMN23GslnvdxM6ym6korjo+77eNW2I8gTQEc57ZApYIgsURFUWCw65CMAIARSAHhhxyfggAcTVb55WwzXW5eZHkcR19uvY/YJ2Z2wFp7602qlfKMWmdLr6vzPG/DfrMtsjQlItmH7qIZ4UOFL0UAAFQB+Np7N11Fkem1k6PBtJ0knuc9ogSfpsXhcr05ny7fT9f/9m70drJSQUeCPUI8Fo8+3CPuV1EUWmtjjFWqrmsgAM+DoIeTifL9l8dHXyv/RVW1sjziygv97vHJA160JLtHPFLNWqoHya5h/tCkS2bnxNPTrDvYpNnlZHY2ms6W66p2xAj7CXYgYhQG3SQ+HfROj4atVvLJa6JSKgzDYb931O8e9TrTtc1rAvgQclVK3dfR2BzhaV5ezRaXo+nXL44fdY1Y8REmds6R207aIaJSqJTaufb4jpRSiLB9LvGrVVW9qst3V24Qese95LjfjeP4EYVfm6o9k/ny7Gp6MV6s0oKYldJSEkyIx0LCPeJHMnCBMUahshVlOaBNAj0M/KIsv0b9VVG/gPrY2ddJ8Pvh4M3R0XA4+OlMphDi5zGzItbEmkET4K3UHoL7r93zk2dv/kisRzxBzFxV9Xy9GS3S0aaYly4HjxQ3x5QmVmAVOABwEOz0yNRc6XhbwAQAFJAB7rWjk0582u8M+92fruRqNOu5ep3Wab/9YtC+nKzSnEvkmsExozbKeLvePjWJFozAcNOZq8k/Mgpg42C0Ts+mi9FseXw0DAJpo/lEXO+FzTkcP4R79vqkiFprhaiVllJQn9R05lIAwOY6CUo5hNLWlh2uqu9nmxdXs1cvFv1e1xhtzOO4BXPOrdPscrI4n66v1vXaqgJjNAhQP/SmCSF+lcdxrhFfTBO7ISJHDgAQAImUtX//1ZujrDiu8m+0+YeTo29PT44H/SSOPWNkBCmEEOJANCu5ZovlZLFepXlelLy3MoWIqDUOu53jfvfFcNBtt34miUZrncRRv9s+6nVa0cQzRqEFBmZGAKWU4/tJz2hWjNaOFpvsfDQ7H01evzhuJbHcpT8N123X+ebrJjtsr0+qEOMoiIMgDgPPk0yxHSAiM5RVPV1u3l1N312MXg77cRQ9iuORiKqqni9XF+PZ1Wy53GRlVTN/oXJRQoh7IeEe8SO2ZUrnlCs7TC+N6jF2i/pFmX/r6hcK3nTavzs6Oj0edjsd3/cfxbVKiEPEgACKARmQQfFNUw9QABZACiQI8RmalVzrLLuaL8+Xm2lmM1IVakAAIMWgsQYmhKY++m7ZPc0x6hTATRceVEZTN46Ouq2jXrcVf9yT6zZE9D1v0Gkdd5J+4o18vdQKiKA5H6CCncM9Nz+/zSbg7evUBFwTrHI7WqTno9loOj/q9zzPPKKKIeJwNAlErSjstuJOK/E9yRT7BOQP7wnfHht7IThVs1tW9ny5+WE8/Wo6G/Q6nmcOPMGHmZ1zmzS7nMzPJsuLeT5L69zpGhF499aGQogHctAnGvHlNRU9DGLgmZZWA4IjoG8D/7Xif3r98pvTk363GwaB1lou9kIIIQ4KM1d1vd6k8/Vmvk7TvHSOAPcV40AAo3Xke50k7raSwP/0Sq7tDyMaY3rdzsmwfzLonc9SP60rcnDdNe/etgoREa21eeEWm+xquryazr9+eZokkYR7noDrveXH/Rb33HxNKYzDIAn9KAxlCf9OmnVwbF2Wl5PF+t3V5LvzqxeDXhQGBz5pysxFUU7m87Or8dloNl2usrwg9lAZafYnxCMi4R7xI4S1CXUXguO6Oi3Sr+vqbyPv7/u9b4/6L06O2+1Wc5k/5OuTEIcPEbd95fbZkOvuZwclR7F4ipxzWZ7PlqvpYrPc5HntLOqmV44CaDLnAFg1eTQ7HwFNKOfDfY5SHPimHQfdJGrFkTG/cPOmtW4lycmw/+Ko37+aXa2LwrEF1RRX/rxzASERkmJwqJscH41IADVTVlFa2UVWTuarxWrT77Vlquax20Z6UCFuF9QwMxHv+/a7yU3zPeN7soT/05qMP8LmBMHA2+Oxss5TwIyuonlWXszT76+mb06mvXbi+/7BJvgwc13Xi8Xycjw7u5qOF5vlJi8sKT8CHZK1ANVDb6MQ4lc50LOM+PIQQCP6QN04+r0ffF0Vpzn+o0n+/qj/5uS41+tEYXjgExFCPApKKWO0Mdo0nU6+bIO5psqDNlJu86A19/9STXsnzf3JcrW6mkwvp/NlmlV1DWpvqT2IRutOHJ30u8eDXhyFv3gb3PTnGvS6L44GR71xPMuWhXWE1GRo3OvhSETWudraNC+vpovzy9Gwl/i+LMN5xJrTgiPSWmmtAbGJ/3yBcI9C9IwOfd/3jFJP6sJx+zq4jw4GdV0rg0BE1uYFz9fpD1eTN8Pe636r3W4f7IWYiPK8uLi8fH8xuhjPF5ssLysi5WmtjGT3CPGYPNlwTxMvb8ZQyJ+fKe05g8ogG0a0CgkYNDqt4SYjmgFu1d1wuOMZ8K5P4M4z6QAAACtgB6oEtoAlADTTCQwKwABoYAOgAeArSOu6dspn5S/K0mYVtJP28VGap1RVYFNANj55SGjrNhX/d78MZzYGfRx3XveHr05Oh/1hkiQHl9TDAADM4BCcAtBIrIiVVbvt0ne//Xe8/zu+AXrXnQ6lteXTh4hhEhlnj8NkNaOsLNh4ldrWBGEFgOC7zxlLGecBgMKUtLPaAkCp/EoFBUVO9XpJZzqetmIKozYweZ55RL1gP+mp3rYyMxMwgSZGtj4RMBA4BlmJ83Occ1lejGer9+Pl5Tpdl2UBpSMATwNrYjAMwEaTQfYAdt6BaspjP/QVVGluXdkKvaMk+mO7/ToOXw36URT9YiQFEY3R7Th6OWi97idvY38yz1cVk594ocfVbKftsegDADBoBg3gXQeOEYlZoY6t4rMci2nB7epoZV+Op51OVzqyP15EVNU1Gizycegvuz5tiI+8lk1VrNFtK1L9WqxzaCrOsFbUDB0VXJ9knHIA4JQFANbge87T01h/FfiP9cLh1y0AqEzplGVVArqAyoAwKGoFoLQyOkjDINNmjtZZHBa7nW+rW+NPZABwzQhQaw0MoHwV+BnjuxWoiL5Zw3yZvqzqIAgO8M1siqAt1unZIvvXq8X/nGRv87IIPQauYMm0BANgdxtvx7QEgEy3awxKlQCAYuuT7fuM2bLOFqenp9NVNplnx29+v84y1rvtz0KIuzzZcI9oFEVRFIXFygtbvu87MKxUnuc0m0G7HfX7aKsqmxmqX3Ta/9Q/Df76z990j786OnkzPOm1O1EYeZ73SC/tQhymJr/GaA37mUv8pOaJmiICRusg8OWu75DdbzGX52C79GC1eX85ent+NZotKusAkRypew2TMfP2ODI6CoOjfuf0qN/vdv1fV9BEKRWGQafdbidx6HlKITBbaxFxH+FLYtjk1flsdT5drIe+rWvmUA78x6ipm5tl2XqTbrK8rGrrgBiY9x73RkRP6zgI4jAMnlCCGCJqpdpxFPnG9wwqXwHmVe2c29P9ETPXtVumxdV8tVjFeVEkSXyA7yczl2U1mc3PRrO3l5P5Oq2tJcZ9LD6v6zpUyvOMQmBXs6vJycSnEPdJwj2PTFhaAABkQAJEAAVoAIARAMAhAhuHGtgwGAAYD7uQGnAuD70wagWWbFV5ad6NYlwuw0lxFOhXrfBI69Yq/0Pt/vb467959eZkeJREsQR6hLh319n47JitczU5Yv5wjOFeprNQMTMxO19B6GHo6Sjwn0Az3c8sdnLwbsqvMiEQAiChgvte7PPENEsPRtP5xXh+PpnPlhmhNsYv6lKz1+S+EoAGcAhqW39nt7ssBM2MCAoVB+iFvt+OvGEnPuq04ij89VWQPc9L4rjTitux3wrMLK/I1RbIv6eo1O1AITFX1m3ycr7Ji6qu6lrCiI+Xcy7N8lWar4q6sC5nLAlqsNSsC97xg8Xm5xlvHQgKgK/7QtL2Z9AZwMQP+lGrncS/Mqx5kG7nzKqmvI7v6dN+7+vjXq+dbHL7l/l6PZppV2v9IdHpnjeCOS+r8XIzW22Wq02n3VZKHVTEZ9vfcJNeTebjxWY022RF5YgBEH/DyYNvnW+RCQAUkyFmLkIPAhMft2OfXJuBPKgs1IhyshLiXki454lDY9j3oa6BqKoqzUhE5Fyn26mrMqyLHsILBa+U+ioI/3HY/93rF71ON/APvV+AEI8UM1d1tUmzTZpnRdXM6n+B5yUiZvKMiUOTREEShZ7xDmqIKT7CwPxl6zo9as659XpzOZ5eTRfTxSrLC4wTrfX93jAwMzMppXzPJFHYa8X9bmvQ7fz6e+AmMyiJo0GvM+i22snGzzir9nJjg4hN4LCq3Sot8rzIsqzTactEzmPEzNa6TZZneVFWdV1b6xQxwl7Swj5oViB2k+hk0O932mHwc+3nHpems96g1/mbr17+7sXxxWyT6auLTe6lpTYe5Ht5UmIuqnqy3Ezmy8l01u91fP+wqiUQUVGU49n8Yjy7mi2XaWadu/fiYo0mq7EVR8ct/4/fvAqNmY1mf0p1MeG62s8HIMTzI+GeRyaxNQAwWgBwip1SAB4pIAbX9JplA2CAdTNdwy6FwAOloHSUrYmgx9wnd3yx+cY3X7fDo7o4LvKvBt2vX744Ph622y1jpO2CEPvinMuyYrZYTZebdZ5XrgbtERIjABKAQnDA8Hl1abazZ2wAtrnQCKSYNCC7Eqn2Dce+igMThf4vdhF6DB779v8cZGAARlUrAPScIjkv34WIqqpabtLJcj1Li01NtWUDWoPSaBRrAMDm8MCmENJn0ZoYgMkgBgYGkfey334x6Pa6nZ1y5ZRSURj2O61eK25FXsuoot5X3VOlFACW1s3X2Twrlut0OLTSSPsxIqIsz+frdJZWawspYK7IgSaFrAiAvB1XwKibVWAMtzN6Gk3KuMdWMSVKH8XBcTvptlqPN7sHb72664x4BYBB4Hfi+OXxMIrjZVlOlvMNbSrnqv1k9zBzbd18nb2bLE4vx8NhP4njKDqUJZZNas9ytb4cz88m8/ejeVbYmgBA31xvPy/Hh/C6PCEqzQQAhmsf6n6oXvfjr0+6//T718NO9/u3F/PvFufLDVYy3SHE/ZBwz1NHBEEA2gOw6KilTB/hpaPBZnOq+FTzt/3+354evzg+arWSIPBl0k+I/WlWcmVFvlxv0rwsyoocaaNpz4MaRAQiZtZKe8Z4Whkj/ZgPmtTu2QkRZVk2XSyvpov5YlVbq7QmIlbsed59PYtSih3z9mFNK46O+93To0G7nfz6lVzQVELxTBLH7SSKQ9/zjC4tIDq454oV25QBROtomRXzxWq5XOVFEUXh/T6R2DdmrqpqsVpfTefj+SLNC+ccoNJaK20c4P46JSmFoef120mv1WrF8VMq+sbblY8IiFEUxkm8yItJmo5ceTVLcW+LiYh4k5fnV5N33dbJsNdptzzPHEgQtqnaM1ssL8ez8/F8NJ1X1jIj7GHbENE3+njQ+vb16d99c/q3334VB+F0sY7CXCI9QtwjCfc8MlaXAMC47UjlEEgphwCI15+maZqFbWdpbARaK0eJhZ4yrwD62eZNVXyl3Ldx9O1g8Ob0dDgcRGGgn9AlXIiDZa3L8nKT29xBBdpqp30s649K9pim2d+u4/dtEaDtOKn57Sa7h4CsYucrDIzyDfpaP4nD/en2gkVgQAZFYBiIwDhwasfOO8/EtonMcnUxW30/Xpwt05INesZaAMDARPW2zx0BACEgfmZ+D6J2ithByBxq7MTmqJP028muy1sQ0RjTisNBO+pGvq/Zh9qhuq9gT1NM+vZ3HNEqK98vVq8X69dp1mm3ZGrncSGiLCsuxrPLeXY2z+aVK9Cz2mjPsEYiZ9l6sFtks8nRaK4ajAAMpLbHhWZQ7BRYjRQyd3x8kUSDJIke80ou1YRTkQAZWAESo7KMVUVMoLUe9jrfluVitVquFsV8VqvYuv2Ee5jLyn43LVoX01b3stPpxFHY1nqnqPGeWGs3aXY5XbyfrkbLfJ7ZChWDJlDY/A+aTOSdOWgqjSIyaawBIALbDfjNMP796+HfffvmzYuTqqyVpzWSq8p7fVlCPGsS7nnqmLGqVFWDc63AP1XwQtHftoJ/aoXfvn51NOgncfQlm6zfniqRsaZ4boioLKvlOt1kuXXc3JUppeC+Z/U/gZmZtda+Z8LA930p3HO4mhNyU31VTpO/qJmOns8X83U6ni2X6w2D1trUlpjI803tqnt5optrFiKEvt/rtIb9biuJP6PUndY6ieOTo8HJYNI6X05W5T5OAXiNGcraTubLyXyxWK2Hva4x5hDuLcWvse06t1y+vxiN56v5alNbSwRoUGvNWjmm/SUDKsTQ99pJFEXh413JdSfmqqqtc8QcReHL48F8k042mx8mo3VmiGrazxtriRbrzeVk/t3Z1bCTDLqtJmr8sG8vEZVVNZ0v3l9cnV1NFuvUEbHaS0MuRDRK91rBm5OjNy9PXp4cdzutxWLdnLJsXcvFT4j78mTDPdZaAGhmyH9LJfld3XUHtXOe7R2Ps05KaFbANv+OZntCJIJ8A9ZB0up2ej7q1WpVTeZf5/VpZIad9gA4Ws5fcPlPvdYfX528enHa7bR9/8ut3mrWJljnrLUIoLX+TUWCEABuXQuYiYiI7mup9Z0b9nSTCcS+NYU211k+W6XjRTpPc9AaNK3TlH+63uTzeusyAgCBwuvSPwqIgdDZOAxsvgl77VA5X3EUPoVy7OrpFu9RShMzIbY67VGxyrIsjmMqJbvnE5xzeVkusurdYrWqudZBVYMFhegT6yqv8HbJCQRA6z5rWFCU9cnJyWp8XhXZUff1USfpJVEcR58RN1FKxXF83OsM2/7XR92yrv/0dqTarc/Yqp+6Oa5vBh4OEZjezunrVTparE6GaRSFD35vKX4l59xmk52NZj9cLd5O0lkOOUZsmACKbRIEebt/kpoQABwqh4pucnwAWnFUFylVhdEYKo4Mtj3sh74PoPUj3mea2j2KyTa9DkERILKaL1fswNXWGN3vdV8P14vj3vLrF/PNxjHU1tV1rZTyPM85l+d5EAS/fWOYOYPwu2mq4tnxcPB6Mu+2257nPWAQtkmTXK3Ti8nifL45m67HmwJNbMEBb7cKWcH1vdXO2cdaKaUAPbbW1qUPqt01Xw9b3572TgfdXrfte55SgEyb1Tz0TGqlHbsQ9+PJhnuenaqCMARtoKqX5+dYOwyDXq/7x+OwVSyDYt2H8u8S/x9OTv/m5cmw343C8AsvwCaiPM/nq9UmzzSqVpL02u0wDCXFQDwTTbeLyWxxfjUZz1abrKiq2jmHqPYdkW7u+jxj4jDot5Nupx2Fjzgn/zlo+rBba8uyKssyr4mI4s+4pXvqmLmu7XK5vprMr6bzTVY4csxqT9PRZVkyURyFrSjstZNBt+N7/mdcSRHR971+r/vieNi7TMOpFwR+ff+bvMXMjmixTi8ms4vx9M3xsN/tPqUiLE9YkxM6ns5+eHdxPp7nNZW13UuTpGtVVTUTdHVtw8C04njYaXeTqN1uP72MMGYAQCYmIkQMg+Dl6XFeldN08/JqXNqZtQ6uF0g285T39tyIVW2ny80P56Ovjrunw36SxA8YhG2GKKPJ7N3F1furyWKdVtYBevdVRqepXchq+056SneS6OXx4GTYHw760fXtQFPaTGsNEu4R4p5IuOeRIb9JWHLAGlBtw+sE4CsPIGRrbAH1JiHqKfo2UP9pMm9B0QnVV4Pu1y+OjoaDTrv1JZN6Gk0q8ng2+Z8Xk8uyDLQ69f2/fXFycnT0lJp6CnGX624Xm/eXs3ej9Syr09oW1tXswChAC9d9tZCVvunMteMx2nS+4GZl/fZ3FQAD1UZx5Kl+ZE56ybDXjp5GpBWfZj1HZiZiYlTK83w/DuPaOM/zgO5nUdJT0rQrupzO309W75bZ0lIJmpQiMAC6mYvWzV6CAOAUX5e4Ath171HGT/MSgQbdzqATHnVb3U5rp55ctzXt2I/73WHb6/jYjbwp7as0LAA44qmFs/Xm3Wzxu8XyxfEwDO8hSUHs1U2bpB8uxn++mL5f1ZMScwiYmZCQABE026Yqza7ZFs3RgQiKgRld0yASoLSVh8RgqSqCMHrRDr8+7r4cdLudziMPEX4IHzAAgGFwjlFrRcxMBADG6E679fJ4+M148OqoGi02eWnRWriO+Bhj7usgtSbacK0LeL/c/DCav5nOB73uQyX4NNnH89X67dXk+9HibFktKqjBA2Oo/rCYSzHBzeBixx3BkgIAxw4Rfa06vhrG+mU/Oeq1263E8zwiYmaF4CnWj394IsThkHDPU2FMvSlsVQ59/9WL0yPEoMpOXdni7PcvB79/eXJ6NGglse97Wj9ARx7nXJplby/O/7SuxrXzyF2WhSsLQDwZDiXiI568Jrvtcjz9/v3V5XSZ5lVlbV3XrFEh7nzfufuzK6V8zyRh0GnFrTj2/EdfgoGIrXXWWmvtY38tH6lrW1ZVWdW1dcwQBEHsgzGmSiXc8yPMXFX1fLE6u5xcTpfT1aaq7Z5qbQCA7/tZliXGDHvdXivpd9tJvFtPrtsQMQiCQbc96La6rbDdiufr0u1t45tkjfl6czaZnY+nX50et5JE1nMdOCJKs+xiNP7r2/O3F5PLyXy8sQT7HS8553xPoVII4Ptev9M+HfaPBr0kSZ7kUA1RA2Bz3kBEz/ParVa7lXRbURT4nleVVeWca9J/7jEg22S75EU1X6dn49nlePrm9CiJowc5Kps97f3l+C/vLt5dzRbrtKgq5xjw3lJsmpIOBGSMCXyvnfhH3fZxv9vrdJr5pyYNGREQ99hpTohnSMI9jw0TAACrbe8tBiCKLJgyD4uy7+wbV33lyiNX9hR820n+8HevXr982et1gyB4qGkZIiqrcryY/XV29V86v/s3l4dk3yjYzFekz5RSx4NB4PtPchghBFzP0M7n87++n/z1fDZdVpnF2imLzKhYMfP1yIa352Rspll3rDDS/DThh/wFZEIkBNBKGY2+VpFnwsB71IV7mMERF1U9XRaj8TzLy0d39mjuGbZJJ4jA23orzffLsroYTSeL9Xy9Xq3TvHQ1g1PqsX5ge0NEeVFcTOZvr+aX62pe2hyQ0QPQCgB4W8TqerUkAQBty941V9Ldnk57xjElcdLtRL0kHHRaQfA5K7kaiGiM7nfaL496x534YpnrrCbm/SX4lMpcVnV/tXk/nf/NfHE06PmPP+z7hDXLuKbz5XdnV3+9mr9fFVepnaV12GoBADJrsMY5DWCYACDdMfDYHAqOFSASACMQMgAog4RsFCWxP2iFJ53ktN/tthPPe9wF/nGb/3T7JSgG1ugBM/D29KuU8oyJjAk84xlttFZKuabuJKK19r7Wc2UMxIYsTbL6apWP5uvZcj3odr98R3ZmrqpqOl/+5Wz054vp29lmllWZ4woUWEJ1+/WS+tzsY9bGWkvOGWMiX5+0g29Oui+P+r1u53bHGIWgyFFVwn0V4xTi2ZNwz1OAAJ5WJ73uV1odV9lRXfw+8v/hxfHXJ8eDfjeOoibQ8yCjuuvab+uzyWiWr/9C6x8Wy64x3XYydsV/vxoHCArgaDDwJeIjnq66rmez2fur2cVkudzkZV03U4VfpveEUspobbT2tAp87/Efa1xW9dV89SdcFwxhEDy6W9ZtWAcA4P/P3p82OZIl6ZqY6jnHdgMMO+BrLLlVZnX37emZSxHKCClCCn8Bfy8/8cOQvFeGtzlzu7qqq3KJjAhfsG+2n6PKDwb38MiMyAqPdESEh9sjKZ5wBBwwAGZnUX31VVAAWNlO8+5filIvNsnzi+kmyYqi1IY1MTO7tv1Rj/qTwxiz2cbnk/nZeD5dbvJCE8NeusgAAAAzC8TA9xqeG4Ve1Gj8ziSKlDIMgn6n1YnCYLJVMi33aleBGKfpZLk+ny0vp/PjYT/w/Xs+FHye8M46p1xtNi/OL358fn42XWzSHFDcpXfMW5BSGl1YAO2oeTToHQw6B/1uIww/P+OeCv5V3FcI4bqOb0lLgJKopDRm5+Bzh9HYSsBCzEmWLzbxxWxxOZn125Hj2Lb9/nHk98AYs91un48nP56NL2bL5WabpjkzVv+kxN2cckopYwwwSETPsXpRcDToDnudm6PQ7k0jGGNAfJ7nW03Nh6cO99wzRNWYhRGAJINtwAXoaNFH6zAvjqg4UfBlv/3lwWDY64bhLhvzEfdCRJRm6cVi+vNysnTgh0IunNZMkC4pFlbBpbycMOI3hnrdjuM4776eYIa9JUFrau4SY0yWZfP5fLrKl1u9zcvcGAOAUpECxOvVJgEQIADJq85ct937CYBqfHjtkldCWlIGjmp4dhi4jv2Rh4XfCREnefHT+SxLGv8+v5D3tspfswYwEtAiI8AoAsHMDIao1GaW5tvMMKIjJSis9hs11+xMmrfxfBXPt9limxaEhFIACBYEIFmInc7t5lRBgLwTz93yFcuyVFJGod/w7W4YBO/Vk+smQgjHsduNsNMMm77tWCov9B7ruSwrzvNFUizjbL6ON9u4227dczeWvfPhPxoi0lpnWbbebF9cjv/2/OXfzsfPF6t5RkYoy3UJEAAQtCQhGWw2it5H3bPrsgSCdy3rrvSGUhbb3LFMP2ocD7ono/6w13Hd+xdV/w1op6Z8pakU+KqlnZQyDMPDyO83vTgvy1IT0Z1fliQsIQTpPNf5bJu8mCx+eHHRa0WB5ymlPlhwbSftmc1ejOcvZqtZnG+yMidAaQuU2tB18eDOVZBB3HplAgCgpFWiJiDHlr2Gd9JvHQ+6rWbjOrbFzEyMzAJBfMieyjU1nzt1uOe+ggAS0ZPYlvKxZz2S1E3WXwvrD8PuycGw3YrcT6DRMhFleXY5Hf/5+U9nqxl3vE0pTOhzvH5+di4c1e9FU1P8x8VFNp99+/XXo+HQf4dkY1XwTEy8b8uTmprfDRHleT6fz8fj8Xpb5qUpS10azYKFEPRB1jRSStuyGr7bbzc6UfO+u2URc17o2TpO4lwKcY/3IJIAjASwyEjWlgHJDABaG0CIieOSChCgdt/X/sp87iNElGXZbLGcrTZxmqdZznveH+lSe441aIUHndag07qTzpJKyagRHgy6B9NNa5bGWbG/ei5E1MZkRZlkxXITL5arYa97qyzLgwIREEXFh1lKMbMxJs/z5XJ5dnY2Xa2fXU5+fHF5Pl0s1pskFaVwhbL3EHn4JWVRSFu1GsGw2x72OlEj/PAVRh+GSvcub3zLlR9zK2p9fTJcJLntTP/6ckrM2jAxV/Vcd/XSSinJ2pS8TbKL+fKHF9YwCqPAc13H87wP84EbY5Ikuby8PJ8tl9ssL3WVjVJKAQi8uyVK5RxqI7ZD//Go+8Xx8LDfDXz/eqtSnf9aawSUUt7ae7ympuYtKHXjcqqu6SrrRTd+7vh1kuwD8rbhFU0BVwYX1c9q9VV5YSAzM2O1Sr4xQZZSADALAcxQGqlBpjoEECzKMk2FMTYZIBCGG8GNFxO7tjdvH+rJej3mzVc/ReW2A79ouUzNHADA2KAlGBuMRKNsI/M4sV3XdmSp0zxbARTQcP0wTM63YMC3zIkrTm0xLON+sn1sy6jInxwdHQwGvW7X9/xPocS6KuNartb/cTH/r8b71+bX3ye5NEs7ZkI0w94zIc8BB5b7R9f+UmfFi3OU8mA4fJe9KBFrwtiyNLjANjAhWUh7Pz/NbeefW07W8r1yJg+HQgJUX3LVXufqmjICCMEIsK5GsI+Qk30TVd5svly/uJxdxPin5eKFzlIBjHJXl26Af3WwRvDtdT0AAC6vAcCAzERgoFXdKRl4sz124dARR6HsRoH7qXZhN+/8rRlgbUxm3v0vPn1ubrlVtd9ndTWi1dKe19lJe9bb81k8ztSFaZ6vZ154/RkaAAA09OvzgxWw2i1nhIbXDbIEv5qjxY11BVQerrh+2mgducWTbjAYdH+/8U2132s2/MfDaDMW/zZpXC6WaZ45QllKEFFZFkSklAWV6xArgwAgq3y75NudFfnaCp1jATSelWe9eLbJtmkWhrVh8w6tWiWzQogUWJj5aexkoc2PhZD7nlGqJFZZ6iRJJ9P5T2eX3/98/t9n+YvJcrzMZ2uRaN9IEkBgNtbVXxkBBkQO3vu9qJY5ABgUwFKCkEZagADgsGw1ekedoB+EncDvRA3nHhbM/ppMSgAgBEUgmQG1ADa2KB1T2AadV/lvKWWj0fjixEgoWphGtP2T3v7lfL0uUQXtYudeowUYi3MBuUUaAFJs3Op4bLEBAyQALFwyf79Myd70ZtvOfNXudGzb/gCFe1VDrtUmnqb8t1n5/TyfbKgEByTkugQAS8Gr1QgCAdDVKCtu7IVujrS7XeSN2xWp3qLNp6H9L8fOv4zsPwzDUTdyHPs6mcHM2nBsxJZFJiXX4Z6amjviIap7mK+2V68n0Kqx5jrGjALZst74DB+AoNEoyzJNU8vGoN02QmdFnEynMopCYbWF6ZgkyLddnT/xvW/7vSeDXidqea770au3rrky0Zz9aTL9S16+tCFmTczXqyZizg3PNf21KG3P+i/ThQNsKTXo9X7bW2Rn709En8ievqbmTRBRWZaL5erF2fn3Pz3//tmL6SovSv3hNRq2bbmO1Qr9XrvVCD+TPO1Nt+Oah8YukLraXEyX48Vmm+ZKWbdvRX0LEMC2VOC5w07rYNhvNhp3shlDRM/zut1uu92OgsK2lLyx+UHEO4zMSimJqCjKdawvZ8vL6Xy57nVbH8Ea9lOGmQF3P68+lj0OM7tdrtZpms+Xq7Px9PnZ+PufX/748/j/t+F1nGVFqQ3tr9/crxEIjcAbdqLjYed4NIiaDaXusbX/b4MAQqDciXuuOowjWpbVbrcAsdQ0T8qXi9h3soSM1nofnjLEnBV6utr8eHZ52A6OB90w8D6AQr/SSM6X68vp4nK+jrN8f9oxRLSljALvsNd+cnx0dHgQhr8cRY0xWptax1pTc7c8uHAPV64WxCAAmIAZkBhYIhhCYAaBLJBYgqXAu3245zdWmwjA8MtkY+wA7PKNwJVfJxTSSFeSZFMUBMY2IIBs0Noy/yKoWySuTtvZ+lTSH7vRV6P+Qb8fNRufTqAHrlsRLVffzxY/lrxAd86YG4lcKgYtJCMYAAbIgGZGnEkVON5/W22tn18IIX7buRl3vtNsVZ+mAEDQQmnBAOUHfZ81H5Yq377LGr3pX6tUEn8CFwERFUUxXyx/fH7279+/+PefLv92sYozNPvX4QsA5qrXCghgCRjYMvLsftPvtpuVdnrfx1BTs1eMMXGSTJfr2Tqer+IkyxzPIU7394pCYMNVUWB1o7D1+3py3QQRpZSO49i2HVoiUKJQstRGa0JEISTsenBWHTmr7mws8H0CW0opIspLsyZabNLJYrNYrrNB9snK/T48kgGJWTILQQAGUTPsI9RSbWiJyBjKsmyzjS9ni5/Oxs/Oxi/Hy+eXk5fT5Ti3Sm14b1WcGi0AIBSAACwAWDAoQE/wQcv98rD91VH/eNgNg+DznjJw1/v7tctZCGHbdtRsjAa9o/7sx4tFO0q3OtuUuags85GvWuVKeq/rEXcOfSAYAMkQxWl2PlucT1eT+bLXbtm2vddPnpmLopyvNhez+cVkMVtv80Lf1dm+e2831mwOm0DJjm+Posaw12lF0VscqetYT03NHfMAwz3VsomvCkJ2fr+IyEyVqKd6pJQSff9jaQmLopBSKtfN8m2xXKHiIGqc9FvOeB1kaYezf/Dtfxh1nhyMuu3Ic91PzW2RiJIkPZ9Of16ut9LKWeaGDJH81TEyQEn04/llt91YKvvfx1Mk/a0xvV7P+c2qLiHE/jqw1NT8Hir/hels/uz52b//8POff7z44fn5ZL4uZOPDp60QwHXs0HPazbDVbNzHPlY1NTepLB42cTJdruereLbapFkhVVVRsRcqlU3UCHut5qDbaobB3SbepZS2bfuOFXhOnJfaZFobKWW12SO6m1K+qp+00bogs47T2XIzW67iJG00wo/u9PfpUGmHGYGRidiQMYbefdz+u4+snr9SMeRFsd5sF6v1+eXs54vJzxfTl9PFfJlOlotVnBd26wPPF4iopGwG7tGg8/iwf3o47LZbv79o8Z6y0/hE0eGwf3S5PNvkixTidb6nr4SY81LPVpvxYnU5mR0NeoHv7TWJa4yJ4+T8cvrTi4ufz8eLbaHNHpU1tmU1A78fNQbtqN1sOG/pPvYwT7aamr3yUMM9QEBX7hikBYFAMlwyohRWKRhYG8u1m63bhnvEW7yQ4C1+ImHqAkstwSBoASyIlQYALYwgLU3RQu17GBA38vhoFn9xcfk49B/1209H/dGg12w0LEt9OqKeiipjMF0s/jpZ/Kxx4npzLbbaMECLMwAgFgBkEICVAZEq2KL8U6rL0N1aXjzfEL74FrHf7TpvSW5U9qxXTm4IiBqVrp38P3d2/lz4WuvUay0P4dV/ey3q+E2uZPnZdL7867MXf/rh7M/PJz9cxOdrXhmHxIc5RVly9TGAAHJRNl3ZDexeK2yGQTVifJDDqKnZC9UUs97G81U8i5M4SYuCXLnH9QwCWEr2Gs5RJxi0oyD4vT25XntyRKVUGIa9QPV9O08lZaakQkhPCGQW1WhHKACp6tmH77UlY+Qq556TiQuaxtl0GS+3206nVddzQSXtMWyq+ioSRokCRGIoy4q8KG6utRBxFxX61RdR1Zhe3803fieiSs6TF0WcpJs4ma83L8ez6XJ7NludL1aXy2S8Wm8TXWijpbPvWE8hHACo2rwCsiSwZNl28VHX+fqw9eVRf9hrP3Dll5QyCLxhr3XSj84my9kq3iaccwkAfGOVwfA+H9H1ipUQBAsA0MRxqmeb/GKxnq/W7VZzfy26Kvuz5Xrz4nL649nsh8vlNpO3Uh+/wRkNAKoOBL+4B0AgRhYeh9bTXuug22kE/huz1JV0H7FqxPLQR6SamrviwYV7oJp3uRpKdkof5t3kzddxZWYpZRAEq490kJZllevYlFk79B81oqjM3OXsD6b89rD/9eHwaNBrRQ3X+fi9t96IMWa92TwbT35ebxdGrvIiM4JBCiHetgvvDwab87PvNwuv3Qod9d/H01Lrb4viYDBwvTcvrIVAgfDpvfuah8i1n1RRFJvt9mI8e/by4s8/vfzr89lPk+XlMkvznPgjdFZCRKWE71iDdnPQbTfCz1yWX/MQMMZsk2S2WM2Wm9UmLrQGlFrr/e0OhEDPsVth0O+0Oq2mY9+xRM6yrFarddRr/9Sar7J8tdlcjxVEdxa7NsZUtWPIIi/K9TaezJezxWrQ6dz3bn13yJW6B4m50HqzTc7Hk0YjaDQCSykpRaW6YuY8L4qivPkF8SuAmJiZiBmYmY0hY6jUOi+KbZKuNvFik8zWm4v5+nK+XiXFpii2md5maVmiUGhZVvFh/dkFom2pXqv59Gjw5enh8cGwMqj6BBeZH4xK4NNpRYfD3sF49fMimW51mZV7slJigFLr2XJ9OVteTGa9duTY9p4SupVrz3S5upwtL2fL5TrWIthfT0ClZMNzDzrR8aA37LZ9z3u7XcM+DqGm5kHz4MI9SIwAhgwIADDADKyBGQE1FAKUFC4IC1AY29NB67bPXzXmohsGItVCYLcc+FWns1JYAGAEGWFYEIAGMBZpLysaumib7HiZnG7gCPnId77q9o6H3U677fueUp9oip6Isjwfzxd/mW9eKu9MOS+zcs4Ano2WhfECACQTMEoUBoAQCaGw7DzqbrbLvIBl4B5J++Vym/EZMh0cHP6iISUiCkABqIgQqk9SFUISvi3ZUPOZIBmgUuUx4FVnLgAgAEJgAEYgBBYIH0TxVq2NKll+WZZJls0WqxeX0x/PJn97Pv5hvH4x3063Ji5lCQFIsKDY+zFV3TOQJIAkkGiakjsejjp+v93w7qJ1dE3NR6QKqi7Xm4vZ6sV0MV3HJEApQaT34KAKsLPXEc3AO4icUS+KGsHdOtdW6p4oik57rWfdYLlZTi2wgJEJAA0AVUu1arirPLmY4Pr+d0brwrIsYUsoZFya8SZ9Od+cT5cH/U3UfOgbe6jUFsyCDCMaCTnBOqcfJgvN9HKbhL7rWMq2pOc4nmszc7wtN9skL4qbW2S6CvBU9spVrIeItTGaQWuTapNk+Sottmm2zfJValZJGhc6K8oSJNohOkjMRGTv+f0aUFCtx0ArRk/qvmc/7QdfH3UfH/T7nZbr1pW/IKUMg+Bo0DkdTJ+Pp4sN5lmeE9JuIiXC91TtXv3Zq0/YAORGnC1W3Yug15p0Ws0wCJRSd96i68q1Z/1yPH8x20zW5dZcWwm9Pze7Gd48b5TAjuM+arrfDdtfHPY7UfPvyQlJgAbY9xVQU/NQeHDhnldct4i68u6BqiHXlboHEK2P0ZlLIFiInGbHzeaXbqu/3T4qsz92Wo8OR71O2/ecT8qS+RdUlSyr1fqH5y9frjZFo6NJpXlcColS/sbnuZhMBp2ecOT6/OLHInZ6fa2c/xZn4X/8h+d5la/B6xGfnbfeB3lbNfeJSmovhPj9cY3fyHRVCVxjTFnqNE2Xq/V8tZ6v1ufT5fPL2dl8+/Pl4nyZLpI8J2lQIX6EwXaXUvPdXqsx7HaajdCyHvqOrua+UxnDTeeLy9lysljFaYrCt5WdZdmeXhEBLKWi0Ot3Wv1uZx9m50II13UH3fbRoHex3AaT5XId7yrPiQDv5uWIqAotcVloreM0m6220+Xqcjrtd9u2bX2Axs/3BSJC4G2S/fDyMs3LF/OlrYQQbEkIXDf0XSEwXfOqCvfcUG4ysLi5/0dExOoBSV4Y4pxMVpSbrIiTNMmytKT+aGSozIsClOP7npQyTpIkSWz3do293xtEVAKj0D8dtb55cvz0+HDY63qe+8kuNT8kiGjbVq/dPj0cvpitLzfFbJOWGdF+HIUN8Xy5ugz8n87cfuR1o6bn3r2Q3xiz3W5fnI9/fHHxcrJcxwkR70/aYynVaQZPDgdfPTo+Gg5+YwjFq1YsNTU1d8i9n9orGS3eiChXcHU/MTNjtW0jBoBqB2iEAtCAAKhRGMlQJKmthLS9FAhKDbYl2h1reHTr4/nVIFVN+57nlWVZlGW12JJSErAxJndLKaWAgtMYk62iYhg4X3juQFidxeUp0Xfd9tPTx6NBr9kILfvTDfRUENE2Tn4+u/hzQX9h+2VG56RT27EcpwTOkti7UeGMXCl7AECy44zjjWCW7e6U9X/NdGSJNIz+kE6+P78QSrWiyLZu9OpCREBFJIAQEYRgUFIAvGWHf1d6+LdFEO5Qb1/zG1Q14YhAsKvIrE4gY4xt2wglCvBCn5lLXRKwMaS1fsdLhn9hugD4agX/KyqJQZJm6zieLFbnk/nFfDNZbs+Xm8tFfLnJN5nJNBTSJ8FXsqS9d5ywbZXnuc4LhcJxLN+RkSNOO953J8PjYefO/WVraj4wVYx1m6Tj+frlbDnepDmBtCwDVOrcuSP7njzPfd9XKLIsI21cx/Fcp90IOoHVaTX34VyLiLZtd1rNw27z50CNGsF6G4/niSl12OrG2a7jZDWOSILKmeu2clbLksaUBCSVILDiEi63yfPJ5vQgX262VQOmhzw+CDIgAGWlKSDDkBgqEl6dTYUQKBkBhQRLSc+1BKJJVFYU+oaR842gz2u/AgMDEyMza2AiNoDElrEdsPhimwEAeyEAbPMSoCQUdqMJ5X4nDORSa81gAkuNIv/LYfMfHw2/PR0eDzrNRlCrva6pHHyOBp1Hg2g8W86mUBaZyakgcHzP9/1cl9vt1rrl8CMZAYB2q+LK+ZNKAKX881UcTpeH0/XRbNGKmpZ1l6HYK3vN1fPx4ufJ5k/PLi9nG7fRBr6d1/3NFqjIIAkYQTBorSUBCqGUUkJIIQLLHjbDr4atR8Nep9l44xB6HegRCBJYVf3ifv+7ramp+QzCPXdFFUYhIlM5+0h5twZp8WZT3WAhtNbGGEZgIlDSJAlR5gXeYNjtQunHq2GePoq337abf+j3Tof9qNlw9xDdv3OIKMvy8Xz+57OLKVuxsDLi0piqpwkzQ/FOlSxVr66NpmdxutLFj+fnhsovDo973a5jv/IXqD6MV2qsSpy1p/dWcx+oNPNEpIniNB8vVqF36fv+O144RESGK8MFeBX92e0wK5dNbaqbRmuTpOkmz6fL9WS+PJuuputkuS3maZ4UZlNAScIAMH7QyqmyLAHAsiwJiAASoRX6X5wcPD4aDvu9upKr5r5T2Yuut/FstV6st2leGEOCuRL03dWrXKdVhBAgWEohEV1bBb732/0if+eLBr4/6ncH3U7jYuXYtpI5V1PnHmBmY3gTZ+eT+flkfjzs9FrRg+3B9DaIudSmNBpgV0bHSEKg2CICysL5tSbiN76tSlJOCMxMKOC1lgMfgUo0LRhC3x20m0+PBt88Pn58fDjo9/d3nt9HdtHYduvJ8eFklVyuNjHNSpOUZbUuuEXjtnehKMstleP58tnZ5ajTHHRalRzmrq5NY0ycJBeT2bOz8cvxPCu0kJKI7sr7rKp+rbZUhdaWUq4TDLqtbqvZaUW/bf6NV17Nd3MoNTU1APAQwz274tQSeGeUI1lLBiEIQJVUGlYgFQTNshEZO7jt05evB4jEVS5OgpBSWkICABORIURULCmfijL2UBxb2fFqO9RZr8geu85Xh8PjwbDXaQeB/8na9NykKuNartffX0z/NTbjbv8M9TjN55SjJZU0FpmSslIJAKhmFQEgWBsQAFTlNxjBIFS+SjliyfT/Uc2u1M9my9TQd8yDXt+xbYCrih1gwQREwCDIMNYSm88cea3jQwACc7UkYGaNIICRKdXlNi+ezxb/5c9/Ox9PvXfuO27IaA3G0NVT7v5HxMRsiIwhQ0TE2pAhyvNyU2aLTbyI0+k6XecUFybXkBMyyquVE0kgAP1+nXRuS6UykwIsJEdwpPC47T856B4Puq1mWDffqbnvEFGSprP15mKxmWdFbkAjImgAJeVdtkWv9hxCCKFQSikl2kq4Siq5L4GtEMK2rVYjGLUbndBquHLuSjLCEHK1VENiBgQDqAW/l4tvFbAAJhCEwICo+WyTvFxsHs1Xh4Oe73sPeZS46bcI1ZoEBPNV3yUGqBRVBGgQAHbq5Hf+tCpzlGoqoNfuv3o9AEKGV3LQ/SJNbkPhW3DaUt+OGt+d9L88GY2uyrg+xBHcH4QQvuceDrtfLlfLzXodp0WRaaOJTWGIWALe2vzh1ffO6kqpJwyCJkyYppvi2SIejRcH00W7Fd1VrWUl7Zkt188v5z+PlxfropAeu17BDLdcQl8/+vp0RQbBIITc3aOJmR0BobJ6oR+5tuc676Iau+qwWlNTczc8vHDPWxBCMCMZAqPBscDzPM+7Q/GkZVlSSglozE4eIIQQQvme147ctpTtdD3Yrv5oyz8MB6f9Xr/dDjzf/uSrt64hojTLzsfTP11MF6BeLNdLzVlpSJBgUb1f5bqgk3d9QuaM6D/OXz457Bjf/n/Pp2hIgOj3ukqpaoX1akVUq3seNteuW8aYouBVkuiLcjydHDdCS8p3yhQxM4ApX/NcvHEbqwxeFfHZBX20WWbbuMhzTUlJGmwjFKOSUuqPtFBRSpVlqY22lPBcZ9CJTg8GJ6P+oNt23znsVVPzabLzhttsL2eLy9lynaSaquZHBIKFEHel/a+EvdWoIpW0lLIE+rZy9twNU0rZCIJ+p9NvR81g6a7yokAi2lNDYmbWxqy36XixvpguFqvasPldqbIBeM8bRZdF4TryoNv45vTwu6fHXz06ORj0K/Og+hz4BVWLrnbUPBz2TlebnyfrWZxuM5MSGWP4TntDSCmZKCvL2WrzYjx7cTE57HVC378TR0IiStN0MptfTJfj+Wq1iRlsKUWWZeKOnJGllFSUxhgFwnWdpu+1QjcKnFaz6bzuwvlrqjj73RxHTU3NFQ8o3MNYVbwD7Dw5CMAI1oKNhKp7AmkWwAx2CGEDgoaRt1b37D7Rqz7vBIAMjGCKkrlkBtSkiBWg7YgAxX/mchgXIZcDMKdR88thf9TvNsLQtu17NONWAvv5cv395ezPSXneGP51vV1LMIFtS0lcGp24ki3LSowFsPMdEEQAoOBVltJUO3bcKX2A4X9ttH5k/8CyHlmWWa3x5+d/ROy020RETCyqVhoEjJYxhdFQ95j+rNllwxCYgRHwSkBXEkkpUQgyXBgdZ1CU+YzMxboU73wRMYPWu4vu+tK7MnEHBq7KuIwhY8yuc4oiZi5BGSFBOSBtYbmWlOv1GgAEawlGUSlBS9YAkIjozj+Tm9i2WxRZWeSBtFuBc9xvPT3oH/Q7dT/dms8AZs6LYrHZTtbb6TbbplqTAMEGGCsHjDsKsyIiX9XoKCmVFI4lm57dbIR7NTMWQriO04kavShs+Y7rOFtNBb2qCSXUFgMyvG/q+6q5105fCznjpuTzTXw+X40Xy0HvQXdkN68NkJWiBwEA2bq+Z7fEYAEAWt2u06JVec9B9bOSj1Yv8ksnpiup8j5PNkRflidN+w+H7f/0ZPgPT48fHQ6rM7yeKd5IdXkOOu3j/uqk37xcrOabPC8NERmhWFrAt7sqb7bC4putSBwPjSyBFmn5crl9Np4dTWedqFF1Wvg9386uGHazPZ8szhfb8abY5FQqQUJlLHy4nWawyq/ir2LsEkVZ6jIvPD9oh41h0z/qNo96zW6n/U7iQWQEvrPRvKam5kGFe34bYwzunFkRLAuCwHXdO3x+RARmRHQcx1GWjYhMbWQ/3bZAfxG4Xw+Gx8N+O4pcx7530y0RxUny/HL65/lqQWJjGJRlWWi5tmVZpc6SJNdav8dCudvvL6ezOE1lLzqwrP8+m9uGn54alMIQVXPCTtjDXKt7HjJCCCkkAZRlmSOxJYgoSba3ehKjFVyFeK4LyKu9H7zu5bxrwU674ggGZiJijVBqfTu/wztkp0cQwrWtdiM4GnSOR4Neu+6nW/M5YIzZxslsuZou18tNnOUFoJBCGmYy71Xc9HboquZaSuk5VitwD6KwseewadXJIQz8TtRsBJ7n2DIrSBPsqcN8VdlR0my5vpj5k/lyG8dRI7xHqaaa9wARBaJtycOo+8VB+9snJ18/Pjk9HEXNxkMu5fu7VJdnsxEOeu1BO4rCWeBliUnN9YLhjkrwhBACJGmTZvlkuX4+nv10Pj7otH3P/Z3XJhFleTZfraeL1Wy1SbOiLLUGzRKllHDLcM9vvAozCyGUko6tmoF70I9OB50oiv6uI2rdlqumZh882HAPAhAC4VW1qjFGCsnIICW4tvBCdLzi11Hrv8t1sJ6gCk8zABI4ti0LYwE2HK9hKZFl5Xp5jPofi80/PT56fDjqddvXZfP3a7Rj5rIsF8vV99PFBOw4CC8MUCNK80RnpYtso7GlFkXKeVmKCAAkg2QANJJA7fIhBACq6rKEwmDVdElOGh0AS8ezPxcmtGwPOTqf5YZaob/aJllTGeYq5INE4oPYo9R8RHYXBgMiIIO4ysdWawsppRGiMJRrQiEBIHPCWz2/RgtuhHt+ofT5NVSuAYBYGFDGoGagMgMASwlJILBUxliQWWQkl7B/dU+apo6Qfis6aIWHnehk2Dnod+7W5bGm5qNQ5aVX2/hysXk5Xc63aaq1ERYKIQyUrAFA3JG8c5c9MEZJaSkZhf5Rv308aDcajTtvwf4LpJSB73dbUbfZCL2N3Gi4SnMLAABVbcl2RafvdU1fJc0RAQwKzWYdl/NNPl9v15u41y4e7J7/9U5DBCwRAFgJIGAFALt7rridtueqs+RODnFD11OpQq7UPa9EDXoPX4JAlFL4jt1tBt+MnP/06OAfnhw9rmM97wYiOo7dboQH3daoG002WVKadYkM8B7uwuLKDao6CYBldQ6QIYVIjCbXyzi7WMbPLmfH/Wm74f8eC+3KtWc+X45ny8vZarFJNAEopQ2BkJbjgLntGQ0AUKVdd/2Rq4IGYyyplLIajtOwZafhnA46vW7r3Q8eAQR8tLRZTc3nx4MN9/wSY4zYDVcIUlZWO+XdhQ+M1kIbgdgAPRTgQjaC5FtX/o+nj758+qjVatrWvbHp+QVElCTp+eXk+WSee23hBflyA75fJgmk27TpydCWUgKiMQZuO09NJtDrsQvF+NmP2fK0EW1y89P5LFv95SJLV384NkxMVC3TanXPTfi6GuFhUDWA2/XXAzYGEBFv6d56s4brZuC1ai133YK9Sl4xsy8REQUqKW0NAlkSVB/7x9EhZ1nWbIWHg+43B93vHvW/ODnsd9qOYz/Y6oyazwYiyvN8sVrNVpvpcpWkWakNXm1Qd2PdHU0A1WXORKhU4NmHvejL48HBoOt63r4vJSGEY9vtqNlvRw1/bqkUizsWLv0CBkizbJOk4/ny/HLca0eu69Q7/8+Pa1FPK/APetEXB70/HjX+cDL84viw02rV3/i7UMn9mmF4fDD8wzbbFrApKF0XhiqPrbuhLEuUwESmLJOMltvk+WTx/GJ61AqiKHpvgaHWerPdPH95djaeno1nq01imIUQbBgBbNvW6d0cvzHGtazQc/utxpPD7h+/PPridNRsBu9y5LvlVd2BvabmTlGltQEABgGoACWwApC7MBABAAEBgpYMVSeI4papLXHLARBN+eb73zKSssjhqh69+nn1OLq6j5GBq3bKAM1MAoCxyaBgKQwgGoEa+m6ryDHJDPgedEZwejo+OgChAXMAgBJAgyjB0+CWwtO7qDwDGAGlEIUERigkZHwCeQ46BaHBk5YyUm/9bBOZ9AvbeorcW8e9Mh+5br/fHA66o4N+FEX3d6LdpQtW679tNj+3mj/0en+Ki8skAbcBQVd6bcmg0wLQMsoDAMFV8YvQCABO+ffOpihfwcsVAAC4iev9PzT+1ygceY6niySMfrosEulHtoNSsMgtxfnbIphveSF9y2yGessc9NYtwH4X6gCgAeiGqKySOjER7WqRqhq3+3l2/ZpY/cJLcGe24Eips0wDADiO4wBAUZX55bd7fnn9hb3+RV+PPgggASTuesMZcHb1hKQR4JedOVhpVBqc9H0FATbfXH+J6+yf53lJXBhjlHS01mmaSssOwzApX4aOetJzvjtq/sOj4aNRLwz8fesRPiIOvU828tOBRQIABBawIrQMSgIFfPV9oQHQAgygxurMJO/jHezHpHJJ38bp5Xwz2ZbjLefsAxrWUhTgMLgsACC2b7dJcEsAAIOCBJRCwNWAIqiMfGc5OfPI+rbT/7Ylvh01+73I/ns+o7+fquVzFPg93zluujPfnk6nKmjClSWgQQEgyvfK1TXLDQCUQhE4JUoCh1AkCEbwzwtsnme91qrfWzTCB2rgQjfzUXjtSlItSt8wl9+2f1b+hi9N3Pj5e9FUKqVAWcZAWpDWWkqplOq1G8n8stwuu4H6ajT46jh4dND58rA3akdHR4etVqtOCbw7QgjPc4/67e1mtVovl7G/yrLVtgDE0jJww8umOj3kVX9euBblXd0oxKsTAhkAzO7Ble+BpaTl5YjPVwyOPlqZb7ZplheVYfxtD7taq0/mq4tN8b8/n/5lnp+lRarZSHYkMBZlvsBbjiqSFFTqJJYMQgtAIBuMRKFNGjn4zXH/f/im84eng9PDbhD8nXZvNzJqmJjW2rS4tu+pqbkjHqK655Xk4Yb2geiq9t+2wfNs284RwZhbf0KLBfg+tFoABRRbvd2SiaWhUei3sqSp81Pf+WZwdNLvRY3Q81zHuU+WzL+AmY0x2zh+fjl+tonn0jlfrNYlQbO5j5cj5oJ5URSJNkZaKKVxpCMlIFaCC2MM3F0v3vvOQ1P3PCjyPNfaCCEsy9ptzIS0LWvQ6w677eNe58nx6NHRYbvO2dZ8FjBzURTz1fpytrycLZMsJ9rj4FbJ91zHbvh+qxkeHQyOjw6qIMj+XvQaKWUYBP1uu9WYeO7Gtu3yyj5sTxjiJCvPZ8ufXlqPhq2jYS8I/Hr/f+/I89wYg8RESISVFEVKOZ1Om5Y4Ojl+Omx+ddD98rD35Gh0ejDstqKqBW39Xb87VYuuVhQdHYwulpsfJivP3ihp9N2pe27CzFrTOk4v5uvZwttut4HvvUcpgDEmjpOLyfzHFxfPzseLTVxqw7yXLlhM5Ltut9U8GvWenp6cnhy3Wu+a1b5ST9/9UdXUPGQUXlWAc5U5unf7gjcNCoKBACRVBqq798RQde8gQEBiFMCMwAgsNXBSyXgcCVHTa7Uc198IDQkC28oAAEiumkBBLjmXwAgGwQgwCFR1/EIAgMBGVcZmNbe0VlBEUHYEnHhWYzX7QuG37ebXh8PRoNcIQ+veVm9dUzVfv5jM/jybX0pnYbkv13HG7LYPsqQEAAEkGECUgAawAIBdAfz7wgCa2LARtoOIxExX3ZJ27e09507eWk3Np4BBCbDrAgNQDTICAEoNjIAoAEmglFISEZJ+ejT4qh99MeqejgaddmTbn3nO9vbx+E8LEhYAVIoeBgmsBL+etq1W47zL/j/YXKcxJk6SyWJ1uVhfzFZJYTS83p78PYz23gwBAJBGthq+N+g0u81gNOi2W1HVFueOXuW3EEI4jt1shJHvBo5o+va6IA1Mv/vFX+/9JJB5p7dlrVEkRTHfxIv1Nk6yjjH3NxH1YGk6lhCCJREIGxgAlNK2hVkWHzU73zwafHPY/fKo/8XR6HDYbzWb1QRRf8u3RQhh23Ynah72OgetSTdcrbfFkrWuOodePWznD8HwO10GDFGSFWez1fnUnswXUdS8bRbnWoZ/Ppk9O59eLuKkKIhfbfl+z/ApGAGoGjmRWRIgl91G87DfOuy3h/1usxHWaaeamo/L/V4rvwfXXXWq8i64UkBorZmlcBw/isIoko4DwEB02/iXUirfrItsHdn2oB2MhA7j+UgX33nWHw+Hj0eDTjvy3N/rrv8pUDk0z2aLv7y8OIvTrdvYlGVaFKzUHdYwv+F1K2OFG0Yq1f1V3rX2dqt5CFRabiYuigJBEhECuxaeDjp/eHTw1aOjg37Pc/+Odrqm5l5QyUhXm+14sZwsN9PFqtSaGfeanmKiMAgG3XavE/U6bc9zP2RRpFKqEQZRIwg9Jwz8bZnBnvPdxFxqk2bFepts4zjPH65h8/2lKmTWQEyICMCMzAL4ycnhl4P218f9b44HX50cHg17lVStniDeGyml73mDTvuo3xlerqebIk1Yl3spLmaAtCjHi814KseT2bDf81z3VpenMSaO48lsfj5ZjOfLTZIZQ3ydDL9TBKJtqV47Ohx0D/q9VvPWwamampo7R6kqlYVgbvYQfNO64hN1wf3VoQp+06+7ft2gsQQABAY2QAwgDWKGwILAdrDZEqOuFbW0ElAaIHGw9QBACyokFJJzCVoSSACpX5V2X30ySNB48W8nhhuBc2DpQbxpF0lf0DedxuOj00G/GwZBVfz/GYx9RLSNtz++vPzXZfyz3fjeiB+yxLgu2E6RxSAVGgA0IDRgCagBDcCt1T1vixtZllXp7ZEZqr6VQgghtvkdec3V1HwCFOgAgEAAgN2pXtX2SwnEmjUV2rIs33VCz35y0Pl21PqnJydPDkeN8J1sEe87hPfblqhAD3bTiBQsAEQ1D4tdv0INYADo7qQr9xJmzvNiud7OV/EizjZZYbgyHBRQiXTfNzvNKKCS/eJrKwdEJF00PG/QDgftZtQMbfvD7Vh2/Z7DYNCJ+g2/E+SzzYY0mp0dNREKfC+lV6USY5CEgEzXvm+CAQG0pnVaTBbxeL7q97q1YfO9g5MFMyMKG9ERaCvpKdkLre+e9L8Ydb85PXh6fDjodX3fewizw16pSro6UfNk0H3amy8Xq7zAssDrMqQqkIY3jHuQX1VQ3EqpVzmXbdLsfJ1czFfHq03UbFRleu/451rr5XpzNl28mC0vV3GmiQgYJfJrrlHvl6fFq3FJEgg0tuBuQ41a/mEvGnRbvv9BA+U1NTVv5MGpe+CVp8lVBGj3G6NSjUYjiiLHcXJjAAluU6iPAArxZDg4FCIQJtgsD8vsj53GF0fDYb/bCHzbtj4DUU9F5aQwmU7+dDE9y/Sm4c63SVJmohUYx4FVDN6HOLWu2yRVvyLiHTke1tR80hhjBKIQQqLwXLfXbo66ze8eDb5+1H18fNi+z+7vNTU3qfYq6208ni/G8+VivdW0n6z0DRzH4TxxbavVCFpRIwz8D+Pac82uHXu71W2FwTSxpMz1fiv5iCgvzWJtzqeL88vZqN9pPoyQ8ecEEUkhhKV8122GQSv0B1HwaNA9Peh8eTR6fDjstCLXdeoCrjuhukgPep3TYfdstpom+bIwpTb7sNligKLUk/nifDI7H0+iZmgp9Y7fY+W6sFitp/PVbLXZJikR3ZU7+E0QUQr0LGvUax3220eDXr0Uqan5RFAWAQAYBEQwCFQFn1838WGEvYwNd811dk4SIIGoqrXg6sZO3VMAArMRDAQCkAElCAnKcCvMBz3qdEvbTksDBEo6lXN+rqiUkFsaBIAlADUAABogAq0FcWDAQh6x+s9iNihMwDT07S96h4ejQasVua4r5Wc1v1bzx9ls9kJ5f3PdGasL28ttG5QFKMCzAQ0gIFcNZTSAIdRw+7PobZqyvCzg2skf+LoxtnLsN/9BTc09RAsFAIJBMABqyUDIkoF0ZkvLs0AhdhritOOcDv0/HLa+OBp1WpFtWw9EpY9vVKLeH5B32gpJDGAEm13rNWQAYCBgMIKAsdKhPECYOU2zyWzx7GLy/cX0crUqETUjoAAAyUBYWedV0ZDbfUpX/TuFIDCvLDe069qQm4ZttRt+M/A/QEOuX4CIjmO3m0EvCtoONCQXUBZAwOpKr/w+5wOBAgBGEFy1LKUrlQExc1aYDfHFavt8ujqYrfqddhUauLN3VbNPELHpSldZYeD2o+aoFx12O0fD7vGwN+p1uu1WsxF+9m5uH5LqIu23oy8OB8vFYrvabDKz1qn+lYv8Te3hlV3q7WDmUptn8yy6nDfb42azGfq+ZVl/VzhzFS5PLhfr80U8WafbAgxURQoCoFL5wW4svPUgV/0VSQJkspEDSxxEzpfD9pNR52jYbTbCWtpTU/Mp8BDVPRWvylavI/FSOo7jOI4B0FoDCuX8/c+nEvV4EtuW+MZ2/Yv1SRR92escDfutKPJcVyn5mc2vRJTn+Wy5+OniwjSebrP1Ok6E60jbNkRgDAQBJMlejwERrwu48KprCTOnxS07b9fU3EMQ0bYs31GBrU4Gra9OBl+dDP74+GA07D+clrpVTey9DqJXZXritRtVuAcYAPbcj+leUDV/PB9Pnp1dvriYrLYxqPDNBed3BCI6ti1du99qDrrtZiNU6kPvWKqeSo0g6Hfa/fYs9JaLrNy3U7cxJiO9jtPxbHV2OT0ddltRoxb43AsEolIytNxeszHst56Mhsej7mG/O+p1+u2o0Qjt6x6ONXfE7iINw5ODwWobj1fpywySrDC0l6aohni52VzMFj+eXfYafjcKPdd13b+TSCaiNM2m88XlZH45Wy7X2zwvhH33aVFElBKbgff4cPDtF4++eHTU77QfzmqkpuYTR8Xz5WA4LIg3aeF4IQuVlUZ5ti4KgD0upd/m5vu2VRW/zf1XVB24rgq0mJGYGKoKH6RX91fjr3QMF5oNIaKUFrDSZAAIggCePvG/fpo0goSJpRNKBwhmgQZgYA3AIDRIBiAwpmEpni28JD503Ede2M4zsV195ap/7oVfPnnc63Z936tEjJ/Z/FrlClbrzU/j2YXj/WtO59LeCpVKMKoa1hl0BkyCQREDEgMYBBICAIS53cu9rcKZiMAQ3PLZPjxvm+r26mZd89kghaW1Ji4tZbm2J9kUaVJmsYvgO3AUtk6GrS9PBl+dHnxxeng46IeB/0CSadX2pmdpeZ8LE7QAuFJvSSLJgIwMXGoAgIw5znVuGJRDEononouZbg0za202cTJdxbNtPk+KdcG2AnMd5yNQN7PTt9a8/OrxSJJ5u1j80+nwqB91mkHUCD/KPllKGQb+qBt1Q6fftMYLnZclWihsRUbkmojotmEoEggAVMUVGQCMZAIAJVCAAKE0600hztfZT5fLx9Nlv9uybfuBDCn3CGNMVcl+3WrdtlQ79P/xsPv4oHc07B4NusNuu9uKGmHouk5lI3B/x8lPlkrg0201TwedybD9t0m8WaeZMFmex1lqjLFt23EcrTW8HqJ+j4GcmTP0fpzFypsPOu2j2bLTalmW+o06U2YuS71Yb15M5j+O58/Gi3lSCifQwMASdtajAq7GQXPLEwQRy7IUWksJEkoHxSAIvz7pn/Sax6N+GAb10FFT84mgwJgqMFJB11VPny9CiOs4FjMDIqCATsdvNGzb1kKwoaq3N4MWwkJLCcsryYCJQWdgNJJJzs+6rfY3j05HRvuLZTvbfNtyvj0cPDnpdVptx/lsq6OJKE6SF5eXP1xcjE05oaJk/kRtvGtq7jlEJIQQIMuyLPNUsrEEBr7fDf1h5H111P/mdPTVo4NHR6N+p+V7n0PLv3dBIDq26jSCJy27cW+dIBGhYELEKtwjmAQDMBJxaagozTxNLxfbdVHkBNWUJPBhCXIrJelqs11utnGWaW1QCCkl0B57MArEhh+0GkG/3er3Or7/cUKoiOg4TqfdGnRanajheatEk7nKXVX61jt8uV2fS11u43S6kM89+9n59GTYDsPwc13M3F+azWZRFMYYy7Icx5ECFZijbvj1o/53T0+PDwaDdisMfNveKXrqr29PVJeh57qddqvbag47rZeT1SbJiMi27epSLYriri5VFKLQZrrePLucHPeao27b97zfmPSNMXGSvLyc/Pji/PnldLGJze3bDf8G1foEEYmMslW7GQ667WGndTgaRM1G7dpTU/PpoCAvsTRKKotRMhAz8I1C0xsmPp+mGqFaKFdBKqwKs3h3/83HMO/eAEGBFjpaMAtNCISgJDgOPHpCRwerToPATjLNjJZmNGXWNKAUg5EmM3kS5EUoeAiiE3onrEfzi06e923x+LR3fDDodtuN0Lesz9Y4g4jyvJjMlv8+nv8biZ/84XgLseRSgJGw+4gZwIAgcgxIBmClhTAAjO9zBr1V3fOW+8VnHquseVgILpVSgqDUWZFuESiI/JNO67AXPuq1/vDk4JvHp4cHg1az+aAMOIXAwHWeHvb+uUuPj488172vbxwBEfDaeZiAmQ1xlhfTVfK3F5dZnG+ystSUMDNDYH3cw/2gVNKe9Ta5nK3G63iZ6hyQhUXCujY4YoSqM9f7DfzVB381mzAACGAlodcKBq1w2I2ixgftyXUTRFRKNgO/324MW0Hbt5M8yzQVJZOwUCgEgF+5hPw2dF2/jwS8U+PK3ZIJCwRDyLmZx9TYluNtuVqthsOBvYfSj5rfg9a6KAqtNSLatmU0u5IeN92vT4++eXzS77a9q9D/fR0Y7w/VdRoGXjt0uqHvIqIxksFRFgDkZVkUBbi/vILeL0WqLV9DucjFi2X8bLI8mS0qq743xqOJqCjK6WL14/nkbxeLF4t8kVPGCoUwIKopRxIAkKT3rOQwWluWhQpkqUPbPe43vhh1j/qtbjtyapeomppPCSWK3Bgj5E6rzJ+7uoe1ZikBBBEZw8AIygbf7/V6EIYkpdFXjb2vZd55TmUGphSIgeu2LTxAcaqgtVodcP7dqP34cNjttgPfsyzrM7NkvgkzG2PWm82P5xd/W26WUq61KUlyPaTX1OyHypeKmZVSlu95Sgw6jSdHwycHnW9ORl+eHh2N+s1G+BmHmN8IItqWGrQaXx/6//THPzQa4T19+7+eLJiZiLMsP7ucFsb8NN7YWanYqEqXSp98/erdwcxZll1MZz+fXZ6N54v1tihKRmXMfj8EKUTUCDvNsBM1A9/7iNoxIYTrON2o2W6GzdCfp0W+zY0xgEpIAQB0R+cDMwOglFLaNhGlWb7NiiQrszQz2lRiojt5oZo7IUkSYwwRFUXBzEDGCyzfsUPHDnzPcRwpPzfLyE8ZIYRjO2EQhK7l2spSUhAwQNVC5G4HEEOUFeViE5/PlhfT+fGwFwb+rzM9lVYvTpIX4+nPF9PxYrPaxkVRGgKBBHd0buzUPSykwND3Rr3O0bA/6HZCvy7jqqn5tFBNlC4KRKERuDJaYUbiX9jHV80vPkHwRkdWZLiWJvHVPcy7+3dBLMpBKCYgDaxZCYuCJnV70IxSz0uVIiawpAOWaywi8rNFqTOn0D2pRrbTL/POensqucPl02778fDxaNhvXPU7+LyXRJXl29l4+m/T1V9U8DfX/znVefVxI+0augEAszJgabAIkCUAlMLiKm8KAJB+rOOvqbl3WFCwYcXkOaIdNIcN/6Qbfvfo4KvT0eOTw363E/j+w5RMS4GOrVqR3+u2m83m57S3YeaiKJm532p0mt6swEzqkqRSymynH/voPhzGmM02fnE5+/Fy9ny2mm7THAQJmWtTOcFJYKo6hzLI99If/zrHjiAcxNC2mr7bDHz7o15clXaj3Qx7Tb/XcOYba52WVGoBAEIyM9zSvo53vXgAAATQ9W0gNoKVUmi5ZWFSonVKq5y00drspbF0ze9BSll5KhGR1hqZQdlzwmeTZbszl1I2wuDh9Gf8FFBKtqJGN/S6oRfHxTYtN3ma57m0lOu6mS7hyhnn91gfJACMNhiap/p8lYwX68V602u1fr0GqIrIZsv1j+fTZ9PlxSZdZDohJlCSUdwYBypVvnivtK0QgpkloO+qfsM76kZH/W6nHTnOh25lWFNT89uof/ny8TZsrjXnJtWIzPR5q3vgyiK30jHZtu212/LwUCgFAKQ1aAKwpJRISERMZFsqsp2eUj3idl4O0TxtBt/1j44OhlGzcW2D91Hf1d6pHJqXq/X3l9NnSb723XVRbuLUcv2PfWg1NZ8niCgQAcGRqtPwHw/bTwftp6PON4+OHh0Ouu2249gPYfB5GwgohKicSj+zjQ0zO47t2ralFCLkeZ6UYFnWwymqYeayLFfbeDJfzlab5XqTZbm0HJAy13ofnWUqhEDHVp6tWo0w9P2P28wIEZVSUdQcdNv9VvN8lcpVdv2vRG+raX4fKiWCZSlgUeRZmuXrOCmKoiwLIqpz9Z8UzFwNfZWlFwLkBON1/pdnF4JpmySPD0fdTqvqi/RgJ4gPxu46bUVPhu0fRp04M5q2mywhIglgWVYV7vn9VIFXYk7SfLVNLufLi8ls0G45jm3br0VYiCiO45fT2c+X0/Fis9zEeZ4DwN1eyJZlGWNsAa1GOOq2R93WsN9tBOFDXpbU1HyaqP/7/+3/9Ofl5k9n07Qw2ypNRvfJeFe+JTbF1T/dqOnfRbIVgcUSQZcohPK8oNPtuacnF7bMlAAkYAmIzFgYbSdFG9OWwaahXl4c6OKxK/8w6j49HAz7vSDwlFKf2TbjbRBRkqZn0/lP6/RCuRfCf4aydISUWgLsug4wAQAakAyKAVkii8r/n0C9n7qn9u6peYBUgR7bkq5FTdcKffcwCr487H53evDFyehw0I8aYeUH/7GP9CMjPtPkRHUCCAFsTFaUSVpuS3BI2A/GqZmIkiSZzJfj5XadFnFREgjLcVlJs81eP+9vaEtvjdg9AwBUvYQFNAOn0/B67cj/qJVcFZURbLvZaDcC37FsicjMIBiBgG97cDubVsGSgBAkAVa5fSWyVBvDCDZLNKBSIxaJTpI03saNRuPzi6jea7TWVVuu6/5c2zj7/uUlrlQSx6s4zdLsSXHQ67R936vdmj8AQoggCI5HvS+PB/N1mhc6yXM2RIhVWy64KpbYyelu/vLuSFsIoU2e6XS6SX6eLAYvLobtVhWYvh6sdv1zl6sXk/mL+XqapOu8yA0L2xPKYZJEtNs6MQgQggHfq82t4zhxHFsKh5328bBzOOh2W7W0p6bmU0T9z//jP7nPXsYGpussTXOAXeOHj31ge0NKkFKQkBIRpOu6zWYz7HTG6QYAdp7PwMwEZBwwJ4E7kiIqih7q79zG18Pe0ahfiXoeTtqEiLIsG4+n3788Hyd56jVjbTSycF3Q2499dDVvpl7h3VMQUQr0HbsXhcOWczJo91uNXuCeDlpfHA6HvU7gP6BA88OkavgihFCWch3HcbSnhO/7kGV//4/vP5W0Z7lcPj+7PJvMt0lWlCWAsCyLlUTM9/S6COBYqt0IB+2o12q6jgNXGfWPiJTSs23PsR3bkvLVVV9pB+4EIYQxpigKKWzpWICoiddJsVgsLi4vPd9TSv1CPlDzEVFKGWOMMVLKKu5vjJkuVmJRmDxhFKx1ksTHo/6w32u3W657X9sX3hcQ0bbtTif65vFhnGuplBGACHFRlOXdSHsAQAihlGLQpuA4yy/nyx9eWAftZqsReJ57vSXZjZ+LxWS1WcZZmpdVyEkpJS3LaCS6m9Y7Silgcm131GufjPqDTifw/VraU1PzCaKOh708S5LpZPFcbBZxKX1ywyIz4HoAAKCBNYBWZCzK4fbOKyzfMqy8pZGq4avuTr/4Sa/qzCsFRzWcFLIqJjI7JQ8zoAHWwnWKPAGdATMoBCGASij06UVgqcaZ5NwW8PWXF//H/3z56JEWBJYAxwGpFBb+Zt0ul0PP+59G7v95+szzPMdxWgetwWDQ6XQ872HttZjZGFpv4h/mi/+V9f+zJX52yo1UrDUnZSEcAEAGZaDqzgUABiG2CKD6TwOAgBzK6p9ut+aQ7Lzx/rfNJoTFrZ5fqDd/j2+dDm+78n/b6X9Hs2EzBwBYeFDKXWW4p2GQY0+AdADkLupzNy/2CWDfGDdufoa7oeLGPdW/OuaWyxp68/n2Nhwt4LrrHDIAENL1PdVPQr6+nYQjZmYg1ESkweSkteDStxVSKYEsZEuhb9uDrv3tafQ/P+p2Oh3f933fb7fbURR5v9l49b7zNrXmrx8mGcSn2i/ybkCWElDodDOVhG23kafrT9RC765h5rLUs8XmeSb/uqIf15DINgtOYg2gQ7RAvzpRCBQh6PfazKbSAgABRrCWQDZSIO2O5/oCbMvKy5ITFigAgJlglw/aV/QHEYWohuudQXuVesuLIs/K7SabzXNbRb1WY5vpeFOSQfBudzCSb1gbsjAIlb5JG7ACzwIwQMbkIIGFma5m/8tLe2vNhd90veCmfOBzxTUCEXMbMqQSGQBsgQFLlRtPgyICgEJCKSGXYARkuQQAQaVkI5AtZCVACFEUhW3bwnI1yLzQuWEWUkpp0Y302M4t5cbP3QT26h4j3hwpqKqHqttEVC1XUMi17/z3JHv27OUP2/SrpHy6yr/a5F+f0LDf8X3v4axaPzxVgL4Rek9PusBxYKeWXBeF2U7TUjiGLWBFIABAEAgGxQQAhbpdJEgWKy5AMAvEpDA/z7aW5RxNt/3JstPuWNauRRcRZVm+SYoXS3qxKCcb0OwpG0gb0jEASNjtoIwEs/sdAIARAQB3XpvV7Ho1x7IyAoClEQCsDCIAOMn22JdPRtHTTnDUCXut5sfqY1hTU/PbqDAM+r3u0aB32J/9tE3XMRhjQN4ryfj18uvGOoy0BiJABCFAohCCkECyEGKz2ejQVY8eeY8ekesmxoDrQLcDqxXOZlAa15Jd1+5Q3il1t9s9OTmJosj3fcdxqkDPgxrOmDnP8vFk9pfLyYuy3IROrHVeZoAIlgV3lreouWMQ39D3p+bjkqZpFe5RjEKArZTlOLbkMtkqKQPHabpWFLrtwD/st//x6aOnJ/3RaOT7vlLKsqxall/zEGDmLM/Xm3iy3K6TrNAf1C1YGzObzr//8ef5bGYpWUVgGNgYMtrcVWL8FyCilNJSUkrJDERsyBABEee5PpsuXk5X2zTbbou45KQwmvY7DhhDqyT94eXYgaIb+Qfddhh8/NK2DwPzrgOIEAKBBaJAlohSIAAoCSCBJAgB0nOICA2gASYNbNiwNsBEuizJQMlCGyaUUllKKbhdNurWJGlKRHmem8IkeTGfrTfbWAh0bMuyrHorvleEELZtd9odQGFYLOL8+TSebcoso32E6Yk5L8rpcv3T2eVBOzwe9q4jelqbzTZeLJbj5SZOc3PbpNc7IxACz+23GqNeezQYhGHdkKum5hNFKaWaYdhvt0+Hne/nm4t0JkpDtqpEGQBUNbXCV52VbsltV2m/fjiCYCCAa53QzZHTyTO4klsjV4tCZmYmTQQAlhDCIgEExoAh8VPPAncLXc//7nH05VFsqTzeeKR5uRLadMicKjgwRT9Zd4X5F7/19OnTwWDgujud5EObLHc1wJvNT9PZ39Ji0YpKz8k3W84IlIOOA/tev9TU3ODXqqibA8auK9/OngIAwMAtI9e3HOQW3lv+CF8dz80BKwJNTIZK1ARAjgRLCt9G25cNx+pEjYN2eNhrjbrtQbf16GB02Gs3m83rvhsPbfypeYBU0p7NNl6sk/k6zvJyf7EeyQYAAEmwqBQ8eclns42y1Pavz11L2lIoiZYAQCCiuBSG9tLOAhEtpULUSkliNIYLQ4agJCo0Xy7WZ/M4KaFkYUwhpbRsaVlWbpI7P5IKYs4LPVmuB017ttqsttt+2f78mwAKSZWkilEIEkC+xK6tDvrNAKQLDAgsWQvQkg0ysQPMwBqJmTUSMJMhmq02aWq22qAhBoHAAlDw+xpMvTNMyISl5jgrp6staxaCOs2g5bu+70pZe+julyri02o2D/q9w970qLeYb9J1tmYoAcR1XQLje+pS8bXlDhHhNi3PJ8sX4/nlbNFpRZalACDNstlsOZ4uJ8ttVpT0zuMVvuGRAqp1107CjMgCQFfCcVfCoBkcdVsHvXan1az9BGtqPlnUzggwavRazXYjtKdLzg2KO5uY9p2Uq55/9yo71yGGq2KcamIzxhCRMSUTAQNEER4ddTodx3EylMhgdGnieNBsPHac4yzuxunXgfqHg9GTo4NuJ6piPXt9F58szJxl+fnl5KfVdk6cSakZDBGAACHqQH5Nza2ouqjYlrIsIQXYgj1bdgK71270m/5Bt33cbx8NusNOq91sNELfc92P2xuopuYDU006s/lyPFtcLtZpURJ9IGkPMWd5eTlflaY8u5xYCApZSbSQhQRmiLVF+/E2FIhCYMSZEIIYiYUm0ICaoSSMC7PNKS+JmBFRoLAsy7Ks/H38Vd8VYo6TNMn1cpPMV+ssy6o+pHt8yU8DZgZAIYQlReio0177i16r5/uuEgIRJLBEg8wIpUZmEIIFAyAjIzPlpZ6uthfT9eV6u0jKbcaZpoKoKAprzwO553m75S5RkuVo2AL9YyNoe04j9JWSge8/NH36BwYRLctqNRujXvewvzxbJC/niTFvCqT8boi5KPVsub6Yrs7Hs6Nhz/c9IlosV2cX44vxdLqKi3Jf0kglROh5w27reNAd9jpVQ659vFBNTc3vRwkhLEs1Q7/bDIYNt+OoVZpnJsvxVccKZGAEfq+GXW/rlPTW9Rv/8vYvAi2/eEK/TACqw+PqCKvGYiUJRKwq7bVhMgQggBFGAr58Ip4+3YZuCWnX8g4YgjgbKDlYjoc6O3LE027z9GA0GPTCMLSsB2TT8wsqv7fFav39ePzMtp5J9ZzFNDZgHFCeCx7mWN7azamm5v15l2UL3vD2olt6RQHebv+0dRheqXio6nQjaXe76oCDggEAgS1EN5n7thO6fif0wsDyLbsZuqNuq930+53WoNvutqN21Ax837atKhNbL81rHg6VnnS93Z5PZi9mqyo7vb9gj6xUzMyAwEDEmDOYHBYXKwAQYIAZgRCrixjWMtxfAgsR+jpBRAIGAAPMrBiBSTACAJaARKQJAEqmQhcC9rm9YuYEzDIvz9fx5XS5PozDMPi8gwWM0pApGRhIgrAktzz59UHrH0ad08OR59hSIgoE3E0wTNeVXzuLXG3MNklWcfbsbPLj+fR8kV5us/lWr5OiLEuw93v8ApERGUAzccGsGZn++nLc9NzAd21LHQzkA4nZfUSklL7nDTrtw240ihbnvj3epAWjQQEsq40U4/tsK5AFAAggwVXZAxhjNlk526QXi/V8tWmGYVmW5+P5T2eTF9P1NtXmNsWn4obqiG7uvao1FRKAsdgAgAD2pH3Q8k8HzdNRt9euG3LV1HzSKACQUoaB32+3DrrRsBONC4pTDdbdzEsfoOSeX1VwVb8CXLUlqrpUktbADEqilByFMBz2ej0gKRkcgSFDV/KAskc2/EOv/eXhcNDvhEFwvd3a9/F/shDRdhv/fDn+63KzjlqJMdssS3MD0rIcW5Aoy/KBWIfW1Lw7WNkmISCCkigkCEQpoOHYj4fDKPCjpt9rhM3QCR27EXrDbrvbbrYaYSMMXMepQsx1oKfmAcLMRVGu1tvpfDVfbbZpbgx9SOMeZjbMnucDALJmZmDDzEjMzNK6nZX7bZGo4aoRASIAK0YAltskRkSQSkqJUlSrHSICue9cFK428WSxmizXs+Wq044+e8NmvlpKCiGURNeyulH41aPjp09OPM/99bDMzNVSs7pNRHlRLDdxv9PutJvfv5ipyzlxWphqMbxPORZAmqZwbW6AQjOmRTlebH44m3gKPEu6ttXrdj7vmN1HZ9elqxUdDXoHl4uXnfUq05pumUp6NxigKPU6Ti6my5/PLhVgmuc/PHv54uzycjzLSv/dK7neHUSUEpuBe9jrnIz6h4Nes9F44NulmppPHAUAVT1XvxWddKMn3eZktV4sl0L4hPLKKYORBLACgFtv798i43nb09wcmq4XMpIA6ZWu56bAp5QJABAwsDLAVR6MSRoBCBYDAAuwJAgHPNdx3ey7Rxj5kqgvrWahW+NJr8i+8J3HDffLw+HRqN+Koir78cBHLmbO83yyWPx5tfi+Efwb03PLWuqq8tiWxhYkudCw39VvTc1rmBu7m10l/A0tz837Kxhvu9Z5g/f4bwwF7pXmURIIYEmgBNhCWEgKhSXRU8pzbE8q27Z6zcbXo8ag3Q5DP2qEUTPwPc/z3MD3fd+r4sv1QrzmIUNEcZJeLlZni/XFNstLvddIT+Xds1vnsCAARmEQypwQkcCCymiDiAmZucQ3dxS9K1i7ADubjKtxjACM7YfMXN2jcFeuzrz3Tgms5GW2ba6cy01yOV+N+rHnup/zGIWSgZhZEUrWioUrOLRl2PDDMHBd5+++cWYOiBph0Ikag3az1zx3LUHlpcmzBXO85y/MGIOIAiUAEKBhLgwsUvO38YyZHMcOAs9xHCllXSO8V6SUzTA8GvQeD6eTxWqxmlOZIwmNgkARvq1j8d+hWv4gC6Td0kczZISTbfrDxZyZn7+4zA1N5puf5/E4KYxwb/X8CBoAGASwqhrH0ZUNoSQQDIK1ALaBfMd93HG+Pug8PugNOq13uTRqamo+Igquak3bUeNo0Hu6Tn5ebv96OWfE+9Xj9rqZwiuRTzX4VM25bDsIgjAMvC+/TDcbWRZHzfahj01YfYH2twf9R6Net9P2XFcp+WCrt25ijNnG8fOLy++XKxoejV+e50ETpc0KgZCItGZjzOec5qu55yCiuO0S5PXHV6lcIYQUb34mtgUiIKBiEAg2CNeWkev4lvCk5dqy5XvtZthwHdexe1Fz2LAHvU4YBL7veq5rWZaU4jrKUy+Yah4ylUnzcrV+cTE+G8+Wm9iYDyEQfuORAAAgCCG4+kkIAPS26vS7Qr912UVEptREhFchbCIS7u22c7dFCJEk6Wy1ma/j6WK13my77ZZlWXt90Y9O5d1Ttdaufkoh3nGI3j1eSsuyHNu2LNsAxiXHmUlJp/oWvrnvwa51LIhKZ2SMYWO41KbY2sY0HdkJvSj0Xcfx/dp7cY8IIRzH7ndaj48PFklxOV9v83mR6ve0aP5NiHibZi/Hy8vLsclSS0rfbyRZkWY5eXsylZftRnA66j8+Go0G/TAI6gR5Tc0nzq5tjZQy8P2DbutoPDltOv94OvhhuthqzkEIYQshWbDRYIyBt3nNvaVAlN/SAvBtIRWmnVsQXPl0iMp8mYiu8l03p8vYITAGiMFmsCxABWUJeQaaWSFICQoBpWj63aPRaDjoGTdsuZ1SR5PpgcBvuq3T0bDf6zQagWVZn3Pa6jZUguTxfP7nNDnrdP9fcXzRiHK2wViglCBgQhZge46BN3cGedv3u6cutnf+um8N+Zm7EeS+9fnvV5D1g2OqrlsAcP1RIUiCvCiafkBEyXrDzFEU2bZlyiL04HZhlNcr6gUKS0nPdRxLyV99ZUKga3uKEQWhYSHYRtHwnF7UjBqBaynHUoHnNkLPcx2llO95nU4zDAPLek3IU485NTUAQERpml0uVmeL7cttdhZnhvZsdgKVjkbA1XhCCMCSBQArBjC0y28zCADwzGKvR5OrNgAIJgDA3QqIJENGWjKgkhJk1dUHgQDEfrVGAIXAWJt5lk23+WybLzdxnheO89lm8osyU0o5yip1VmTs28qSobUbp2+xc0ZEKaXve4Nu+8ssmy9W2+XKVvhvFzorSq01AAh85S95Vx8ngmDaGa0AWqyAgDRBrPl8lQTz1Wi66k8WzUbTtuvl7n6RUoZBcDIazOaL9ZPD5Wq73iyJHWELAKGREfG29V2SBABgNT6wrPrRAENcYpJumUrJDKx5WaCU0m/dtuOOZAYAg2QEUeU0VI17jl2mMelSSXClbjjWUeR8c9AdRH4nalhWrRSrqfnU2YV7ENG2rV6n/cXJ4SzNtixmcZpuMzLE/MrXXQhBe28leTuk45iigFJDZdCDDMYAM1gWWBYQQZ4jkWx1D4eD//TtH+bpZZfxEMUfBp2vhv1Rt9MIa5ue19g1X1+vf7wcf58kYw9XuTZy38vumprfy3VkBwFsgUHTf3TYcWwl3s0WEREAypvjgBDCc+wo9B1L2b9Sv6NAJLaEFAIsIaQUrmWFntNqNnzHdhzbsS3PdV3HrtbWUkqlZG3AXFPzRowx2zi5nM7PJovFJs6KgvAzF5J84iCi7TggxGqbzJbr2XK9jRPf9z7/juy/m0rm43nuaND/5kkWp0X243lz4xZlee31g4hCSAAwZs/rasS8LKfL9bPzy0ErGLSjRuh/9jZMH5ddi66oeXp0MN9kB4PpMivKdVESoUREJKK7+vQrwyltSGvDAFJKJe5SF1kURaVzAyDbUoNO6/Hh8GjQOxwNfc+vz6Kamk8fdX1LShl43vGgn+Z5Uer1ZlkWOSVlYbAUgKBASBQC+HbFx2/bZr21LSG96nSzK8ZiYACkV4Kf6/sBoAAAREAAZiAAYQAFSLY7rWI8xTiV3e4fvv3jV0+fDJqNnl5/Q/mToHvYaQ273XYr8ry6z/EvYeY0y16MJ/97HP/QaPwZ5SJXGjwAKUhIBgACLEpJACD26zxYU/MatKsnB0EACPJqFMGdNzsoIRHAlqIdON8eD/75y3Y7agrxrsHcymT5+lch0LaswPcd21K/ighX/XqkkEKiklJKqaS0bcu27apK65pXcah6qKmpeRNVJddqG49X22lWzggTw7b6+3/4+/iVZI8BwBAjQFH1wcHdYwwAeJTt9Wi0eLW+qlQ8AggQkPnKSHF3NPzWtdVdkgtG3yfESZK9mK/OZ+vT5TpqNj7XVZNkkkySmZhwJ6OgW+l6boKISqlG6D86HBRZmibJRVokWbGJU8M7qmJhc0eq4QpGAFZXJ4wwEiwUqYFFZl4sk9540etMGo3QsizXcWrvgv0hhHAdp99uHQ9aXx521km6LWeLuGTLRSHKspDi1heRqEaFG3cYqASJkpRiWclzUFfSrVtGfCrvHmRLADCgAVF1EMvy3HekRLRMEbnho170xUHneNDqtFu2XUd+a2ruAa8WU1cOPs1HerTNy1mSzFId88qkXLBhJgAUQrz3zPcL3hp43jU4uHnPq/vhRpHX7n9xfN2La/cfIAgsZzOptTMafvfHP/7nP/7DoBFguh0p/uPRk5PBoBU1XMetJIj1UHWTas09Xyz/ejGeKLVW1nS1FlZrzw0lamp+F1W+FKt2KkoGrjPqNP7lq+P/6Y+Put22Uu+6a3xDQEegUkqKN0ty8BWvbt8UydfDS03Nu0BEaZZNF6vparvNikLrvbqc1LwLxhjbtlmb1WZ7OZMX0+XldN5vR5Uzaz24/V2qdXWnFZ0eHUwWm59iczlfZ0UJ2hjNxhhm/ADuVEII0pwX5XS5+fliEnl26LuuY/fashZq7RWlVCMMB93O0bB/vspeLpNVsjGvurndzSdfhQurtQdcdYgDALijWF5RFL7jKaVsYbUawfGwdzIa9judyrj9Tl6ipqZmr7y2CxJCOLbdjaJHg2S23rycLTdprik3BeQlAVvEAm6p2rttYxz8lX7npl+PuHH/jtwCKUEpAAQykBs0Gomt1eLRd3/8P/zzP532upFOOyk96ndOhv1+JwrDsJrh6knu1+wcmi/Hf0qLi8PRD9ssjckadWCTWwYsIgAwwpRKk2UAQKQf+4hrHhLX1kbVtcvXowQiEBMzInqW3fa9o27ryWH/6HDYbDZ/j9j43VU59XhSU/N+VBXEm21yMVueLzeTtNgYKNWejYgB6EaeXFSjCwMASHjlIVj1yapuG/b2ejwOx9e3b+p36EY5KrGASsFx+/XVbUmIGNEw6zS7XFtns/XLy8VBr9tsfp59lyUbyYigEYwARpDMbBiumrO/D5Vrb7vVPOg2O82l51i2pYjBaK21BiAhhFJ3UyxfuVCZ3eSIcHWelCBAqhgM5oW9WEtbWa7TCHzfsRuNRl2Msz8Q0XWdTis67LVH00W34Yy3aUmmZMny1oESZQRcdxIEgKvvVxuqmkogIoNgYGJi5tuKIyvXMCMMgCIQjIKuziLDLAU0HeegHZz02we9TtQI61hhTc194ZejgRDCc51Bu/14lDydLi7W6dpsCsoLo5mYmd8qDrzlNf+2yfO1+28Eeqr7X9P1VDgOKAVCgCEstSRSiI5j/eP/9f/y5cHBF6PeMAwO283H/e6g1wl8v7bp+Q2q/OrFZPbn8WQBYq7NIo7rBGvNp0+VK6tyXMpSge+2GkGzEbiuW69Iamo+cZi5KMrVZjtfbZabZB2nWhvbtqGs55+PjNYajUHiJMsu58uXl9PTYaffbTmO/RAS+9Wa93cKcKqinlaz0fId35KWlEaB0ay1JiJmfmf56XtijFGITJzlxWLNZxID1zrutUethu/7tWfz/qgK+pqNsN9tDzrRoB29XBfxKiuptG0biuIOX+tql/Q7YpNvwbIsIhKIvVbjeNA76He77egztmyvqfn8+OUkUzUUaITBca/zzWF/vNzmJIg2xpSZZpZv153eUbgHfhXWqR5IwJJfK+aq8lqWdtEgAkHJirHlRQfd6KjV/s/fftvz3YNu83jUH/W7UbPh1IGe34SZ8zyfLZZ/Obv8saRzL/i31TYhi9utPC0tIywDNhGgzmVBwoCqJirnIx93zUOCry5ffj2zXRkfsjFgSAphW5ZSUsp6FVtTcw8wxiRpNpsvp8vtIsnWaZqxcDwbynyvr1u1RHrNSRC1IIBqvYEaAETVLRQAAGLs7/V4fJrAVfb+lQMLS0ADLAkBWFVjYJV1l3tunSGUNEwC2LIcTThdxWfj5WS+WiyWzc/RwQdBI4BkKhmvV5o3fr7v0yIqJVtR81GnedRt5oaWcc4Exhhj+M4/Q2QwCIwMVfMmAEMlKgGoMp2v0sJ1istNerlcr1arVqt1s01kzZ2DiK7j9Nqtg07raNB+scrO1onRJgxD817hnqriwdxoFydsh5kZxdUmCRgFIoK+nfwer56fAQAJGCvvHsvxIE9toYad6LDf6bWjRhB8fpd/Tc1nzBtyCkII27J6rdZ3pycFiaAxdZzLP79cTrdZiVDuu/PnLdFaI7NC8oToNJrfPjr956+ffHk4cpEeH46Oht1Wo+G6jlLyIWSifg9a69Vq9dfvf/zfnl9u2lGC4vLyEjsHUaezej4FsV8Re82d8/tzkveLSt0jGIQQQkhEcVeF8TU1NfuDmYuiWCyWZxeTi9lyuYnTLDPKUUoB7DfcU/PbKKXKsqxuIFOcZMtNvFon0+l8NBpUDj4f+xjvB1LKKGp+8+gwzouoOf7+YvEzLI0xRUkf4DM0xqBlAaIhKjXlZbnN8rPZ4uXLl0EQCCF836+/yj2xE/iE4UG/e7qKXyyyv5xP45KUUndliamUYmZ9teRDRJQKEfmO9mu2bVGRNjzncNA96HdbzYZdi6Zrau4Vb5aQSil9zz3p9xRzV4k+ch/h3569/Nt4ZayQYOdQWrX/A6RdB3TYVbnfrLYy6i3RZaI33i3LKtotAAB29eoCAJiMvqpN3Y0yLCxAN099hG7Y+PbR0Xenx18dj44HnX47areiKGp6nlfb9LwLzJznxdnF5P+7pv/FHbx0+vNCW45llTaM40BaJRdaQm4BgGDwhAYyAAAGb7ccl+LN8w+95XzYO7etbub9Hie/JSb5ts9n4QIADLa7iu5CwtrV5w71UJYswOBV+4/P5PwPSoLK1YKVEbscvEZwlMjTzLFtS3CeLkkHjuoL/EgnVU1NzTtDRGmaXS7WZ+v0Yp1v1kVQuh4oscxv1wf09ij+1fzFlYQQNQKABfBa0FjBeq/HU+DratkrZQkyApBkACheE/S8bcJ4CwZvN9/1lwLAISQAyIQ0Sv6UmYPFepD3kiwPQ/OZZdGMTFEqhoBBEFlkfKQq0cXw+4RUQgjf904O+hK564qIc9zOdEozpgxE1XFSMAsGi3LBxmICgBRul2ajaknPILmy2dzFExxpQQkA0lYNBljEkqfkOnnb1+hNLduzLLtusbQ/KgefYSd61A5mXXc+bH3/4nw2ucjtCAAEGMEAoCWD2H1l1dJFwJW/WKVDLORrF//1LSp2+yy8Hq70+5yvGTZgpy6UkoQE40Ap2KTTxR8eH/zDYfvQU4MwiBqfobKvpubz5s1z/65/ZCNUlnJcT9iORplrnYH6OYUsL8gYZoaqEY4UQgit77IG9dc4nmeMMYa4sqAHUNJ2LXU6PBk1/NN+99vTgy8ORqcH/VGv2wgDx7Frheq7Q0RJmp5NJn/W1o/SLgHToizKEhARWWsNqjbzu2fwb5RMfu4IRBRYx3lraj5xKlFenKTj2eJ8Ml9utqWmyqX4wQ5fnybMbAxt02yyXI/ny/U27rSietf3jux8EhrhkRCAuIizs+VmnOqVTnWuhf1B11eGaJPkzy7nPYmBMJ0oaIRBJYGvv819UG2pombzYDgYzVaDznq62mzWOvvYB/YuIGIYBKHntBv+wbA3GPQ8r27IVVNzz3hrqqcannzPG/awSiHZYGwh+HJ2OTexyTUZRpAoJVhKKA0a4LrYuWoaAQAg8jdPHkRvvt+AFFdrvKuK+lIySGKpDRExkUT0XScKneNO+49H/S8PB48Oh6fDfr/Tihqh73nVEqSet96RyiZzudm82MYvw0dMMjeUFYUhQwhSCqb6o6y5Z1SN0T/2UdTU1PwWzFyWepuky/V2GadJQQYAZJVh3nu4h9++BLoXIOxbwFipKa9+Yc4KM15tLxeb6XI96nWq3hd7PobPhCriE/her9M+GvVOJvOLTTZPsk1q8KZqGAl4v8JUYi5KvdomP0+hHarBoNVpNW3bqqvz9gciOo7TbbdGvfZRdzlbbFbFKik0M++6iwLz1fUsfqXaww8Y+xaMAFRd+wjsgOh0G8OWd9xrHQ37rahZB3lrau4df2etI6X0XHfY60ghpZSs7NS2JE7n2yQtysIYw2yMIaK3dez6bUvmN//B9b/w9V2QxbElpGvbvutHQXDQbR0Pel8fjr4ctJ4cDofdTrvZcB2nTlDclqoD7nqzeTmezEq9RplqivOiLEuhlOu6aCtELPUnZtpUU1NTU3PPIaIkSWfL1XSx3iZZqTUgCiEI0RhTz+SfFMxclLTcxJPlZjxbbA8GQeDX4Z535zriM+x1j4a9n5fJxSZdpFrj27ug7Adizks9XW1ejO3+y8t+q9EIPMuqE6X7YlczEQTDbueov7yYr8/jYqpLbT5pDSMi2lKOep2TYef0cDDsdzy3lvbU1Nw//n5qq4r4DLpCSnQk2g70bLpYimVczDbb2TbbJElmQNmWQclYPeerjgaufvO48DarlmLn2kOSAIAEgwKyAA2Uw0Zw1G8P2+1Rp/nkYHgyGhz3+4N21I4i33OVUnWg5z24KuOa/vt8+aIRXZRmXZaZKcGSyrHBksRcas2/+lzFjS5pNTU1NTU1t4KZtTbrzfZitno+W8+2WVaUxIxSIAAzIe53X2Hwfocq1J695G5SzfWG9TbDeZzOVpt1nHRKbdWmrbcBER3H7rWiR6PeeLFebFZxmi6yXBu8ucraee/sE0M8LUp7Gzcm88H5ZbfV9D2vbl+7P6qvvt9unQw74/n8crGcxmmqiZjhqh9f5cZ186oWN36rfHz2fpy7a50kgyWo4YqDln06aB2Peq1mQ6n6DKmpuX+8k5JZSum6Tr/TsZTyAv+g3T5frs7n679djP/j5cQYreP8WojziwjA21IWb73/qo0gMyACAighAsfpDVrfnhx/+/j4ZNDrtxoHvW6vFTWDwHMd27LqQM/7UZVxTWeLP59dnG3ibauTaiAAIYRSSlqWMaYoyyLLLM/92AdbU1NTU/P5wMxFWSzWm9lyvVhvt0laFAWBEAoRPrTeoeZdYIBSm1WcTJbr2XI57HVc16kFPu/OzsQnDA4HvSfr7Xgbz1PaXk6NIf6w6bOqjnK9Tc+my2dNf9SOokZgWcq27Xo5vQ+uHHwaR8Pe5Xz5fLp5timKMmNDn+ZYh4iWUq1GMGg3j4a9frtVu/bU1NxT3rVwXQhh21anFbmOPWxHi+3mYrY4bDpdG/7q0flsNc91WuicWYMxCMCq8u7R+OZWg/SW2mRhjGBAZMVgS/Ad0fO9o27r6+PRP3319JtHp8NuJ/C9wPNcx6kVPb8TY8xmG/80nv5vcfHvjv+zVplAowQLAClZYFWnh5a6abW0S0J8pGOuqampqfkMMMbESTZdbS4Wm1lSrguKiVGyg4jIDAb2rXG4ZWerhwYhA1StysAIAAACzAxP1unZfH0+XR4Nt2Hg18uwW7FTeXTaj0bbyWI532zmc5gXRcG7VqGEkpFu3Tb09qQguCytVdI8n0XNsB01Ku/LOn63JxDR89xht/34oDeZLy83G0qTdZoZEgaRUBkkALnbHSFLAkKQBICVnYK918MjsaurkAySyRLcsuyTtn/SaxwPO81GUIu/amruKbeYToQQlmU1pPQ9r9VsdqIoajQGnc6j8eT7s8nfLifni80qKxINOZHWyHjrzhqIKISwUFgKfWVFvn3YaT4d9v9wevjl0cGTo4Nht9MIfEupatCpx53fAxFlWT6eTP98dnGpxdaxxstVGQVVz+/q4xVCOI4jpYyz9GMfb01NTU3NZ8KuRcB6/fJyej5drOO0KAuttRJVcVCdUPhEIaJNks7X2/FsMV8se+2WZdWGzbfg2sFn1O0+OtxOk/zn8WJbaF0a+uBnfV6Uy23ycsp/C+xRFLSboec6ruvWq+t9UH31YRAcDvpfrJOX63SxztKyfJu7xUdEIDqWbDfD04PByajf7bTc2rWnpubecrvsQTVUVZU+lpKebfca4aNB9+tB74eL7o/nk8l6s0iKyXa72MSbJE+SlLzd6LDL/yDClaYRmImIiIwxzCwAbCWbhjuBN+y1Bs1o2G48HvUej4bHw0GvFbWaDc/defTc/SfxwKhME1bb7Q/j2b8V8LIznNr+tJznbKrq4JK0uBZmlbv/35z/75fG563njHmz+uy2z0Of4HRdU1NT86lCRGmanU8Xl5vs53kyz3UhleU6hJCUOQApJe5qgmFm27aVUsaYPM+11kopy7LKJH/j4+/LGiMrsyAIfN/XWsdxbIzxPC8IgtVqdUevsOsUZMSVtwgIBN7k2cvJYn56NFttt9vY971a4HMrqjVwK2qc9DuT2fRx14/jjUy5ICstdJ6XQtm+75n8zefnXSEdl5k10krLi1X64+Vi1JtFjaZSqu6+tCcQ0batTqvZb7rfHHQm01mWLJdM29yUmqTjOkEYJ1WLdmI0CACoJTMAmH1/IYYEStKmLDLXVwftztNRdDJojNqNKAxq156amvvL+4hFK92H4zhVp/Z21Oy324fDwdOjxWy9uVxtXswXk1U8Xcez1WaWb0qtmZmJmQmYgRmIINe76I8QKC1lWaHndhvhaRAOW9HJsDeMon6rcdBt99utqBF6rqtk3XXrzmDmLMsuxtN/ny3GLGa5XmQbqayPfVw1NTU1NZ8/xpg4SSbzxeVsuVzHRan3Z2BBREmSwFXKqnInqYIjb3z8fQn3GMqKotBaM3OVNsuyrCzLvS6TKmPF9Ta5mM4n89Zm2+90Wkrd75b2Hx4hhOs4w1736fHRPE7XBecvp8mmMIYdxwGh9Adph0pEuSlWZC6g/MFRB62g22o6thWGQa3Y2hNSysAPjg4OFkl5PFgstnFSbrCgaoNTluXff4q9UQ3CllKe63SajaNB9/RwNOj3XNe5L6NiTU3Nr3n/GbrKTkgpLaU8x2k3Gyf9Xpymq812ulwt1tvJYvlyOn0eb4qyJCZmMER8JYIotUYEKaSUQqBwHbvTbJ4O+qeNRr/V6rZbURj6nhd4bu3Rc+cwszZmtdn8NJn+RTmrzujFFqaJUQ0feH39MHr9lnx9KV515qo1LTU1NTU1t4KIylKv4mS6iieJWWrOwdLIgCCYAADvdG7xPC/LsqIolFK2bVtVF4KiMMnyjY/ne7KxcRynLMuyLKWUVdm1Mab69U6ef9crCgkAeNc1FTQqJeWqSC/Wm8U2W8bJKC8cx6lXaLeiWkI3w+B42Nsk8WK9XW+T5SoGEsp1AVWapnLPp6EgaTQVGtJcz1C9mCV/O5sPevPQ9x3HqVfdewIRLUu1W9FBL3rUb8xmwWy5WZlCWYqESLNCKAsAkKFqT4wMCFXsz9n3gTExCvIs2Qu9Udt7MuocDTtR1KzVXjU195rfm5C5DvrYtt0IAq1N0StP8yxOssVmM10s53mmta5856/d5xGAmRGxmkwQUUkZeu6g1e6FQSMIfM+1LUsKUXv07AMiiuPkxcX4T4vV1u+UllNSZspSEEH9SdfU1NTU7BMiStJ0tlhNV5t1nBXa7LUuuFqoVKuOSgVTFZK77ps7Tt6XJUfKfF1iXwEA1Xvc6+sKIQzxcrOdrTaL1WYbJ77v1R3Zb4sQwrbtThQd9nsnw+XZMr5cpNkqZWYANsbIPYcdqyU5IjJzofVym7y4nL24mPSjoBU1Lav2bN4XQgjPdXut1qjfHUyWzcl6uslYSgIoy9L5SEL7yjRNCuF7Tq/VPOp3jg+G3XbbcepmbTU195u70d9WCw4ppVLKcezA91oN3e+0Ho0GRakNEXNVzkXAVat1RAAhBDMLgQAohVBKuo7j2LZ11Vi9Hl/2wa75+nz+l8X8L7bzg+udlSYhF5SvY4TmzYdeefNg9YcAv9L41NTU1NTUvDtV5dE2SS7ny7P5ZrbNUlIlUOUOIwEk6N3cc0fTzXw+tyzLsqyq3ImZLaVs23LKzdsWGZ/48qPaqBeWrxwbAA1RURREVC3G7u51CAAIBeFO6VN9RwWRpdRqm53NVhez5eFyFTUbdf7/Pdht+6Pmca99Pl+fzTerjJKyNAJZqn3rp2UpJNhSWEoQACQpnU03P72c9lvNXrfj2Lbr1gKfvbAzb2o2jge9yWxxNp5Pt8kyh5QMAVZ5V4GCWEgoAbjSPO47HVuFeyypWoE9jPyTYXfU6wS+Vzfkqqm579xxufV1O6dK7xP4fhXogavVyc3i/OvhA6/8m6u/rYeVvWKM2cbb55PJ92mW+s1VqafJUkDXcZw8yz720dXU1NTUfM4wc1GW6812sYkXm2S52dKeOxKVZWnbtuu6UkpdlggceE6rEbTN2zyG70Fag4g3wikNl4byooiTNC8KZhBC7Lt1QFmWnm2nefZyPB3Pu9P5ctjt1O4e7wEiWpbVbDQG3c6wvRh2WhfLPJ7GpSlt2wazX6tmZhZCKCmVIGCdFeV0mf3w/HzQDka9Vuh7lcCnXpPvAyGE53mjfu90sXo5XZ2t080kJk2W9TE9NIUQrqNazeCw3zka9tutyLbt+rquqbnv7Mtd723anDeGe2o+GMaYNE0vxpP/WK9+QnhuW7MUNUthOR6FRigNNzp6/KoZLuEuuyDpxmNqampqamreDSJKknS2Ws/W23WmV0lRKoevFgeMhhjutobE9/1KNWxZKvSchqs6gTtohV91jmxlCfHLaawq+7rTQ7hjmNkQvVjni028TbO0sLOGt8mKTZJlebmPcM91Zy4AyAttN5pZmZ5PFmez5eV0cdBfVea+9c7wtiCi57rDdmvUiQ7b6/N2OlkXWQ7SsnnP4R7BoAgYiTQAMxEwwcV8/WK6PLwYd1pN13E8z61LuvZBJfCJGuGg2z7qd0azzctZBlQ6tlsQAwCBUVea+g82GCkpmp47iJoH/Va/E/meV3/7NTWfAR+6mcInvoT6vCGiIs/Hk8mf//rXC4ETTYss0+yCbVdhHMuyPkQriJqampqaBwkzF0WxWC7PLsfjxWqTZGmWQWDv9UWVUlrreLsF3+13o68OO9+NOk8G7W7Dcd6Uu/701ynMbAylmuNtvNkmsyR/sUl/nqz+9nK83cYo5P7anAGA1tqyrBxwud5M5qvzy2mr4TcCXylZawFuy9W2v/Fo0LtcJpeb4sUsSXUupdz3eqxakBtj2DBTKcEoAVmp5+v4pxfnTc+xlez3up7n1d/pPhBCOLbdiZqHw97JIv7bi8U41bZtFVnxUY4HARzb6kaN42H3aNhvNRuWVVdo1tR8DtS9Mx8KzKy1WSw3//Fi9l9S9/vu6F8JZpYwzGyB5mWSL0GBoDctuxGIdsYKpvp5Fe5/m5XPW7OLb3n8r9KrOwzcctq75RL3bXmLW2dH3/I82tzu+NXbPp+3rbVIAADs6rqrYxbAIIQA4ntRklBTU/NwIKIkzabLzctZ/P3F9nKVOUEzZwEAErQAY3EuWFtcAkCKt+tE4xgNAAYFCShvDJq+a2XLhafoq9D7Q9/6H05bf/zm8cFwEATu2xxnPvFNzs5t2hitdVEUy/Xmh+dn/w3yeKFmwkmEda2WklBK1oIr8+bbLflia/d4ySAJJAMySIYo6G3GMZWO64ZnKye4MGSvm+Hc992qj+qdvtfPH0T0PPeg3/1muVpPJmnHDeLF2Wyd+20AEGAEA4CWDAKq7/FqrgcgEADAlSXCLWN8ZE0JwIAiyzEogX1gsSS5PdeZ0MJZWe7UdtzK9+oTvyLuI1cOPs2DqPGo6fzL6cAG+OuLl6ETEihCAaAIVH7VrxDgdj3aTfWVIUsCQJJ0o+MhKwAAliQAWFVuQT7GjyPnSdc9bfkH3SgMglraU1PzeVCHex4KlUXl5WT+l7PJywRfuvnWtgxjHQ+oqampqfkwGGOSNB3PF2eT2WITF6U2KAD33oFICGEr0fC9o2Hviycnjx8dt6LmvS4+uvZDJKKw0SwJXkzXvjtzbCvZv0y32vyTofU2fX65IJOPmv7xqBGGYe32clsqg+1mo3E4Gh5Nly8m8flsIeP4oxyMNjSer34S2JDU8kS/FTbDoPbh3hNCCM9zh8P+6WpzucyeTde+56WMHz5VJxA912kGzqjbenQ86nc7jlMr9WpqPhPqcM+DgJm11uv19tlk/qLkuN29AE7EVSaIATQ4GgAgr8+Impqampo9UIlM4yRdrreLdZIWuSEyYIT65aaC7mxrSQCAzJZAz1ZN3+m2Gt1OKwyC+y5YuD54IYTrOo3ADz3Xs4XvyLgoS+Df/xn+/9u7s+bGkWRR0O4RgZX7piWVlVXVy+lr51ybO/dh5n3+67zPTxibl7Exu+vps/XprqquXJSSKO4btgj3eQCpZGaKXWJWavfPulUUEgJBEiAAh4f71QI+uS+EiIDICgvnsryYL9ORj/3pcrFY3nYP+KeqbMreajaOe+2j7vhsULuYJQla4vVNOQXMm9wMxZ8NP/yisXvGAQAoBAcAqAAYmIBhlSbTpd9f+KeXk/PBqNdplq24H/X+8jCta3VXq0e9zlFneNCIL8aRWyTZ1/gGVMCwSftWDIwArGC9O5cp4ahIAVgFYJRu18JOLXrZax31OpLaI8RTIoHbZ4GZsyzvD4bvhtMVes4LCrrNkf1CCCHEx4goSdPJdDaczOartLCOP27gcEucc57nVStRoxa36rVqHBvzdK5kypamURTWq3G9ElUrkb7le/JltlR557+wtrCucJzlLs1SCfd8Ma11JY4POu0XvfZRt9msV42+n0612pissKPp4mwwfnd22R+MkiS97XZvzxYihmHQbjZODrrH3Ua3VfPuPJdKIQae7rXqJwftl0fdTqvh+56k9gjxZEgux9PHzEVRTKbT14Pxa6LXcfiDpotIgzbreJ8DU0BcWADIPrvLKoQQQvxK5ZFoOl+87w/f9cejxaqwrEBr5E0tElJMBIiomL/kaoexrGMChKC2g0hMka/b1fCwVT1o1yuV6IkNONJax1HYazePW7U3w+TcWJuzK+NoSITqQ82OfVy9h+U7dZXj45gUKKUUgXNEGVMBkBKnuXWOHn5fs4cJET3PdFqNb4+7o+l0NFtM+9P50lomuMp347Jezwdq6zfa8w6u7xQAWNSIAIqAqVxCGEYpFf3ZrOLTQb/eO7+sVivGmDAMJATw1a1rdderxwftVwet89H8cjSxhU0ZgHX5RchfdG9+vdcjAACVGWEImoERNAEhaXYAzgBqDd04eNGMvj9unxx0KnEsH7QQT4mEe54+IkqS9Oxy+G48W4BOACfzBdfi+14vIYQQz0WZ2tMfDE8vLi+G49li5RwqZTTirSeEMPueqVfibrPeajaiMHxiFzOIGPhBu1HrNGvVaOoZjfkt1u+x1nrG11qDcqXCUpoXWZYX1t5ButZTpbWuVasnR73xfDGYpa8Tm2ZzR3zHb6nv+9kynWfLS01vL0btalCJgtD3uqb92IdAPkyIGAZBr908OewcXU7fXE4W6TKzd5SErxDj0Dvutl4etl+9OJTUHiGeHgn3PHHMnOfFcDz5YTj5EfBNrfFW+5e5ZS8A0OvbQo4N7eyxJYQQQvwaZf246Xx5ejl+fTnuT9NZmhOE6IMBtR4nglfFSYC+6OSk/GMGpQjc1dUKWoWuEuhOLW43KrVK/PS6CyOiMbpWibvNWidWTQ9TtMwOeN3s8cuyA8rsnvLndvkeay1rT2mNWjnncmfTPJ+s0skqX67Ses0pdT+jkB47RAwCv9tqfHPYHgwnP49Gq+l0UqTWKYdIaBwSgF7vJMiagBA0AWAZ3buus+pumgwAAAIiIDPhJoWIrAOyBKPU/XU0Ub5i38RxHIVhvV6Tki5fXZngU6tUXvRar7r1961oMV1YmxeAjo3DL3zDFVgoc7746usUHYJiJgTNoNkqhEBxOwp/d1j5/qh1cihVe4R4giTc85Stz7Bns59P3/9wcTlSYa4MIYKRz10IIcQdIaI0TYfjyfnlaDiZL5PMFhYNI6JCBbdZFgQRFWKjEh91Wr1OqxJHT/JiRikVR+Fhp3XcbbXPp6Mkz2+tRF8Znruq3eusW6X5cLq4HE/G02mrUXt6AbW7UbboiuOo126/PF68miwux8tVkZPjuyyJZK0FAK01MUzmi5/ZaqTDRr1bq0RRKLG821BG+jqt5jfHvfej2cVoNcudzem2W3QhgO+ZZq1yfNA5Pui0Gg3ZeYV4euSy/ykjolWSvr0Y/HEweRvV30XVfynsJRLHDSANAEAADoyzCC77qifAOxNB9zxn2bWc264auHv9H1YdynI91yurQClQiiUJVwjxcDCzc26xWF6MF+8Gs/NpmqEJolpqyWYJK0+rskDF+lvdwXrC/pc6n333IRlm5dxhu96pRa3q+nr1V72eB0kpFYbhQad53Kp+06sM5/N8ks7SRPkmCmsOOE3TfetT663D7DrHBwAAoiAsyBW5AwRltEVYFvlwvupP87PLca/TjqLI86SR05coEz2ajfpRq/K7g8Zo1HBudXq5sAX7XsTaJIXbVLYiRocAgFYzA4Db8/12ZbQOVbnjKHZlGycqWAOC8XKywyXklMbV4v10dTmatVpNz/OeZMD0fq0r+NSqR63aq17j/KCeueLtxTzLM+VFqP2c9h6eqahMzyMAcqiANaECAEe26hkDDtKi4tOLRvVlKzqsh71GLZLyTEI8RRLuebLK1J7xePLnd2c/j6aDuDk1tiBk3wOlvqhuoxBCCLE352g+X5z1h6f90XyVWloX9FWIoLXlWzwgKYWNuNqoxt1Wo1GveeZpFh8pC/3Wq9XDbuuwPa6eTUaL1BhiRGaG23zFzGwtTZfJaX943IpeHHbrtaoxT6oY9l0q+6wdHR7+NrPDebrK0unCpi51zhEDEcOXju75AgyQF3Y0X7y9GL6vx712IwiCpxozvV9KqTAMDnrdV/PV++FsuMyG0yKx5JiBGRH5K6X6EJFzDtgGWlUj/6jX+t2r429fHrVaTXPnTcGEEHdAwj1PFhGtVqv3/eG/L5M39fa7av0tqDErMBGQDzkBg+cYAFipQnHiWwBQJIdwIYQQX5O1drFYDZbFaMXzXBeWCmBAVkorXGdr0tZVBsGXXNCWUY3NchgAFLCnsdeudmtRr1WvVmKtn+wxTikVR0GnXu3W41bsDQLMcswACYEYvyBGsKui33bbtDKjhJBXaf5uvDzoz4/7o16rGQa+7+9XSkaU1okejfqr42KxXKWrxXi6WmX5tMgL5ztSyihYV1MixYAMCGXqR7DXE2X66hKAygQf3AooIAGAQQZr1XThfjqbH8XDeqMexpExxvefZtj0Hl197ieHnd8MhoPxdDxdrHK9tMjMqHDfYE9Zu4fZcwgECKjKGl7MaK0zaAOPDxuV37/o/Ifvjk+OutWqVO0R4mmScM+TVRTFaDz+y+XwjSVbCQvAwjrlGVIK8lw+eiGEEHeAiPI8X66S2SpN8qKwtrDWlRk9qG51ZC4CeMa0G/VmrdKs14PgKQ9VQETf85v1artRbdWrtflqmXFhHRF9Wanmm2PmwrrRdHE+mp1eDF8dtJuNmnRx+mJKqSAIuq3mty+OhtP5u9FisMiW86xwcPdvKTGv0vz9YPJzrJqxV6/FcRQaoyU08NUppYLA77Zb37487k+Wo2UxSSid5wWxUpq+UgUnrTWQVUrVq8Fxr/3Ni4NvXhy1m03f92WHFeJJkmv+p4mZsyzrX/b/jPi6Wu9HwXuHE8+AiYAiSLOIje8AgQDd0ofCB4gAAGBxz2suhBDiyWBma91iuZpM55cLO8loWVBuGRUgIpIFAFQIm7o76+5aCMBm3ysP/uwPlNIVozvVuFWNG9XK065CWvbnatRrh+3GSad6Pp6OF/nKkSMiVKD2vjj/PJ3g83f4apYMeJzS+Sx7N5yfDcfHh90g8I30hfhSSqkwCLqtxsuD9jfdwbvhdJxSAqDWadmgUBErDQUAq3XwdL+nSDdZQpoBgPRVpZerxC7W5X7pCGdz9+N46Z1eNtqNTqtRFnl5wnvTfdFaVyvxi8PubyezwWR+NlpME2sLRMR9x3KVjQ4RnGJjlQJWZf6j9oy2HAbqoFl9ddR6ddzrtJpBILEeIZ6sJ3ub6zkrq/Ysl8vReJwF8VKbRZZleQ7MwAxE8HRvbwohhHg4mDnLs/F4OhiM+6PZKs2tdVA2zFIKbrPuPiIareuVqFuvdhr1p9qTa5tSqhLH3Vaj06jX4tAzGpiJiJlvO62JmfPCjufL8+H0YjCezObWOr6l3mDPwLo5d7XSa7cO2o1GNY4CX99ThIWZi8Jdjqdnw8mb88vzy+EqSW67Y8bzhIie57Xq9aNep9duNGtRFPhGf81KWFqpwPca1fiw3Xxx0Ol22tVKrL/qUwghHhS58fLUbHqgLAajYZKlpzXvtKAhcRZEoHzIGYgqXjVObNloA5AWAYAGkFH2Qgghvp7y3sNivuxfDi6H4/N5srBktTGMvqeUUtalzIzMAMCIAMCoAIBAAe6sHbPbdoYQKMTQV51mpdOodBvVMAye/PVMea1Yr1TajWqjEkaeUsCOADWgwn1bNKit97+siFTm+5Q5PtsfTvmvGcF4lp+PFufD6XA87bVbnmeefIjt9iCi73mteu2g1Thq1y+mxSRfFilv9hFnGABAf2kl7lwjACCzLhN8CDQTICgGxQYAgMsdypQf+Wgxe18P3o5n7/qDw24rDMJQGjndgrJmc6dZP+o0TrrNWUpqmi8KaymnfeKn5f7r1nsuESCh0gqNUe3Q/6YXf/eie3LYbTdrUolJiKdNwj1PDREtl8u3p+9+fvMmK/LLNFvmmUPlV/ycNeQZKC+uxjCb3feailuG0gdXCHGfmDlNs8vB6Oy8P5nO5yvLXCbdGM/TSilAS0Rf0HH9FyGiUlgJg8NW46DdaD7dnlzbEFFrVYmjTrPeadYr0VgpBW79T7f97ES0SrPxbNkfTfuD0TdHvUollnDPFysTfOrV6jfHh3+YJ9MEBqlbZRki3EvWVJplSVaM5qvXZ/1eoxIFQafd8n1fIj5f16Ype+3bk6OVxUq98dP57Of+2E2KNCv2ivh8ulitQt8c1MI/HLb+/lX7P3zbOzk6qFaqMi5PiKdNwj1PCjPnRdEfjP/xdPyPrvFP1Rf/FfQiiB0zJBaAIFAAyWX2M7QUwNWtGwAHMA0AwLPq6oZe+aC8m1fOh1tTSrmye63hvoXmsvrm9gQDOAAGcAoAQG02XQWACtR61DoVyV7LL3i/0xQN+71e0NfvYruyoAO33+3XblIAwCKAXIPVBACRpeOMjzUFPgGWMR85igsh7lqZ2jOdL96OZj9Mi/8+g2WydGQRIWebZ5vvUtxklm4dX74sQrBIFtVqNdSezXIukmbon4S175q1nm+eT2twpVQUhp1q/E01fleN/rqaV7xKtXnQ7/f3raST7/kxOOWnSs1Z9Zf2cp4s09w5Moafw9t+SxAxisKXvfZiOl0OxtlAm+HK6ShxlDGDMdoLETGxaZ7n+57QV/LtwME6q44QAK8qPbitn2Dc4Xzqv9YUmyyujKK4FgRB0xgJ93x1SqlKJfr+xUHFwHd1fdGkN5Xx//W6eT6aLlepdU4pZYzxfd8YM5/Pr11IWv2WiLjIjM19m8U6aXn6pBb/x++bvz2pfveyfXLcO+g0fd+TT1CIp03CPU+Kc24xX75+f/Gvg9k7Uz3zi6XCL74VIIQQQnwZZi4KO57NTy8G7/rD0Wxp3e0Wc9Fal8OZicgo5fte5HvVOGw1G+GT7sm1DRE9zzRqtUa92qjG1ThKc8qy7A5iLkopBsiLYplk08VqtlgWRSHjRH6NdYJPvXZyfHQxnL8+n9QGq+HSFoW1AMzECOWGfQebNyJa68az5V+hCEzWrvjdVq0siSUf8de1ruDTalbi+OCg1+60lVL/oHKt9XixyvIiy4s8LxbzuSNqt1vXLuR0NDJax76JwiD2w06kXrbiv3vZ/f2L3t99//Lk+LBeq/q+L/l3Qjx5Eu55Opg5z4vLwehPl5O+Vz0N45EKuFC4adfweSvWz0d8Z8H6H7Znuvr18+we2vcEY88/0Av0HACAJjCkNINep78QADhUToFDIrUenxxm+x239l19z+67y1yfrbOrxKG/Z22FZaBzBZkB0gAKAcAhLIiWSmUKAYGZmeXmqhDirjFzkqbDyex8PLmczpdpumfy4t6MMcxcWAuce56uRX6zEjQrUb1WfT4toq5K/LaatU6j2mnXx2fTPF342rhbGDS3zRhjrc2sXSTZaLYcTefLVSLlXX4lpZTv+81G7cVB60WvcT6Yz5KhcgRMTGiBwNOgUCm188TiK0GDqS2KtFCYV4bwpj98NRq3Gw3P857P/nVnlFJKKa2153ta6yzL/hNfesViNMO0sMsknS14scyzPFfLy2uXcOSpMAjqVa9dDVpVv1eJvj1q/eHVi1cveieHB/V6zUhmlhDPg3xBPx3Oudls9vr0bJhTFkTzrOA4ksSe54mBAUC6oggh7kU5kmu+XF6Oxv3xbLZKs9wy3+5t5DK7h5zTCGEQNKrxUafZazfq9ecykquklIrjqNWoteqVTqv5drCa5Hkc1ZM8u+3nLUeUT5ery/HsYjCeHM/qtYox5vm8+bdBa12J4163/fKo+64/v1xmmU2ttQUTEbEDvKs2u0TkqEgQJ4vV24vB27OLg1YzDPwyNnE36/CsKKU8z6tWqycnJ/8J/HrkD2eLZVbMV9l0mazSPC8sIvJnp3uIYEl5nqlGQbcatGvhYaP68rD9zWGv225WK7HslUI8HxLueSKIKE2zi8vRnyery3r7J4j+meZxUNGr/JM5y54a5T2gddaP+nBHiD0C+Cy7Z/Nz/XBryt7nGHuWvgFap+2AAqdI00dFHZwih8AIDtdliHjPm1v7dn6x+3bAwF3ZPddPt7xfdaNMebkG0gBYpl1ZUuAUFRosMqgv7dghhBC/QpltOpsvB7PlZJEus4IIbv/riBURkzOKIgPN2Dvu1A7azTiKntW16Lo/VzVu1eKDZrVRDZajqW8o+fR04CtjdohsLczT4nKxOh2OvxtPe51WEAQyYOTXQETf9zrNxrcveheXk0Wa2txxQplVGZAj4jspxZ1rKgyzhUTRMLdvJ4sfzy6Pe91aHPu+L4UCb0m5O7fbLT8IXh60FqtksUpmi+V8sUzzvLAOAIj5k3gPIgKg0SoK/Wa10qzXyv9VK3E5gEs+LCGeDwn3PAXlfdTJZPrz+4up5UT781VOzjm3b2VkIYQQ4tcioiRNh5PpcDKfLlZZXtxBqiERGUSttTEq8EyzVum26q36s2szjIjG6Hqt2qpXO41aq169mF5fzPXrurqNkebFdLHqj6aXw/Hi+LBWldY/v5bWulqtnBwdfD+aT9NiMM0WxIxcuMKRu5sh29back2IiyTNL8fTn8/8Vwftdr1arVYqEkG4NUqpIAiMMbVq1TmXF0WW5Uma5tZa65yzn6dyI6LSRisVeCaOwjAIgiAwRkselhDPkIR7ngIiWq2S0/7wx0X+Jmz+cwE/ex43DlaON52htnJJ1vkm5c/PzsDzT2//XVXq0fTRrdmyb1eGxV6rGhT73V7MInYAgFTA1coSwOZVrH/C1c9c7btJ73m70+4ZQVM7kud3jbHfM7vncKoAYOURIThFAFCx3LTc0lwJUIHCO7npJ4QQV8o7ELPlqj+ZnU3mw0W2zAunNNzyYC7rcqO90FcVBa3YO25XjnvtauU5lpJVSsVR1K5X29WgWwsr2hVZctunfLnLERE1W0ezNB8u8/5kPl0s282GjBz5lcoEn3az9t1JdzKdvj+7mK/IOucRMSqr4A66s6cuMcYorRzrhc1xaavD2c/9SacxaDeavuc/t7jqXSprcmnNAF4YBlShUhnouXbkfplvpTYk/UqIZ0vCPY8eM2dZdjkY/vnt2SCxi4o+H4253TGNhr0YAQT3vYK35UMgYyvcA3sfzPYdnPWV5t+5nP2WrxDXPxHKu3sa0Sjlae0b86zKVQghHggiSrNsMp0NxrPpYrVMU2sdeuaWKwWDcw6Nb7QKfV2vRIedVqfZCMPwGd7NRkTf91uNarfZ6DRqUeAnaQF4u6d8RFRG1og5y4v5Kr0Yjs8vh71m49cUbJaDWEkpFUXhYa/z6mTxrj/pz9O5XWnHyIAISqnbTuh2znmep7UG5/K8SCyN5su3/WEjMN16LQz8ZqP+DEOrd6l8b8sgDtysPqN8HEIICfc8buubqPPlT2eXf8n553rrX1ANKvFKe7BMIfRBa8gtWAuFBSDQHvhaaeNppqwobA6WlAYPFAGho8NldnU40bC+F4CI4IiZy/HB5U9kBgBn9ju9CO1+xXvGOQAAl6lE67weiwBALgTlG/KVMXr9VihitNefUBJdf1BUfP367DoxRbdfNhPgfu+P2vP23IskA4DEQqGBEQEgdtwF8w+N9rGielyTiglCiLtUtkJfrNLBdH46ml1M5gV6OsYs3bv02b46zVa6WBHYRqt23Ig79aheiZ5n1Lscz9Vo1Hut2kEjfHXYmv98kar4Vuv3K40MxADO4Czjd5eTdr3yu9lqPJk2GvUvS/BZ1+7bXN8+Z+uSTLXqca/525fdyXw5X6WT+RyMD2CSJPF9/1ZXoBoGAOyKFACMFxLQZUL//KbvGa/X6tdqtTAI4jh6hrvbfZG3WghxExLuedyYOU2zi4vLny6G72bZuFnJjAHEdV6vc0AMqCAIIAiBHViCIqc0y4oECEAxaM8Yz4By7IChXq8TkbU2z/Msy4qicNY656pRzMzr6v+/ItwDO8I9WXb9oKeEffgs3OMpVdXqwNNNz4tRKWQEYGYEQnP9GaHacVBUvCM8tKPms/H2PZ3a7wwVdzRu36XrEABzDVYDAyBCxNBCXVd82Gq2mg1pjyqEuEtlkebxdHp2OTwfjGfLJC/YMSHibZfvKYpCKRUFXrtePe51DrvdSiV+tiFvpVQljg86rW6rUYujShwtstvOr/qAiFZpfjmeXgxHw27jKMuCwP+yz0Kuaa8gYhgGvW7n25er/mR5Pk1mBY0yW7i7qN3zCWa2lqaL1euz/nE1btfiahgYY4LAl49MCCEeDrkUfMSYuSjseDr/4ezyT6j/vdG4qFffEyTkQDNkBWQ5RB4gAQMwAheKCK3VtgiN8cl6qDwFQV6ApTRbuTT3ByPnHGUZpKlNkixNbZY75yajMQAAAzCvB4iX+TJ6z4M678iO2VHLppIiADB+qDSkkaPQ/Obk8D///tXvXhy2axXfaIWIZTjKXL8+u04+cMfZ765boLvCRl/PPmfjCBwSqHJEG5Yp71opT3udRqPXarabTamYIIS4S0S0SpL+aPZ2ML2Yp5OcUle2UNS3He/JsqzmqUYt7jUqR91Gu1EL/Od75amU8n2vVa8dNGuH9fi8GQ8GRPuWn9sPAQAhISuneMVqmORn40V/tny1WFYrsRRs/pXKAi61avXksDsYTQaTxSwvkovZssjvIPspcA5g3SyVUBEoYpoX+vU4ab4f1Wvn1UolikJjGjKkSwghHg4J9zxizrn5fP76/cW/XI6ncT03flrYnBi0AkQALMdzAxFYC5YAHSjj+34Y+PU44mRZJMlisZiuVsl8mS9nsErf//QzEIFzYO3mfw6IIC8ANuGe0pc1Yt8V7tlxZrBcOICr8tIMAL6nlFc77jT+02+//c9/+E2n1fC9p1QdcO/rIVSfvnaFSmvt+57neZIAL4S4M+VNiMlsftq/PB+NZ6s0y621xFoBf/ZV9bVZa00Y1atxt1nrtZu1auWZx7u11rVq5bjXeXHQeb/IfprOnCO67Yq+ALAe00eLVfr+ctwfzQaTqRRs/irKms2tZv34sHvYn7Snq3ejFSTZfb2xlmg8X70fBD9Vg1690mnU4iiKolA+aCGEeCAk3PNYEVGeF5fD8b/2hz954b/F0b95/god5wSAhm0jc0Vhl9NUKWU0RqA90p4tPOsCa/Of37nZtBiM8skom8/dIoEshTQHnqwDDniV+IIA4PkeIpZjqso7tAoQAIq9Sxdfn8tdFNeHgTpxOb8CAEDS4KLQ/OGg9r99/+Lvvzt5eXxYq1Weba7+33BVgOm+V0QI8YyUqT1nw/Gb/vh8mozzoiB2qAgQAG+3sggAMgXG1EKvXY3bjVoljp55vFspFYVhr908Oei8Hc4qYZrllm6xoG95Y0Y5BAfAgLOC3k+XZ5P5xWjyotf5NQWbxZWyZvNBu3HUrXfOR5XIMyu9oz7h1xQVBABWgUNjFZYpewWAc+ZsmddGy975sNdptRp1zzPek7oPJ4QQj5iEex4r59xisXx7MTidr4pqe1bYFJAVAShAJCJmpbV2izmHYRRGofaxsMl0PhqN3WyWvTuF+QwmM1jOwVogBAQgAKPWNWBgnWiCZVhntVpPKfOGNuEe2vfUDXeUat5xArpKylOYclVIg1NACrFWjavVOAh8uVsohBAPATNb66az+flg9LY/vJzMVknqiJT2GME5glu+1Pc9L/C90DPVOKxVYs889wvOsmBzs1477LZ7zWGzupqtUrtp3nzbmNk6mi5W7/uj815j8mLRqH9hwWaxrRzS1WzUTw67JxeTw9F8nLnZMnXMd/PJfkIbs0qyi8HkbcU/6TSOu61KHGmt5VacEEI8BBLueZQ2qT2Tn8fzc/TPw/g1rZyPoD1QClJWWRFnoLVG8DAjk0xskifTadK/tJcDmE1hlcNqBUnqFUWoIEYToAKAU88ysGLgq/MGZk2gjIIykoSgsWzZhQCQ73naRmpHTRx1/aa4am+HhxARMYBFV2VVZA8/H8ckHohy67mPM08AWG8pclFx6+7vHf7QNFDcmn0vHZk5y7PRZDqYLYeLZLxM0jS3oLTxkNlxdqunHAqxWo3bjWqnUek0qtX4mfbk+kSZ4NNtNXuNuFWLz8ezLLe3+sXM67dcMUPOvMjc28vx+bA9GE8OOq0vLtgstiml4jh+edD9/mR6sUgXpN9cDJdpbt0txvJ8IgBQrHINTOC0KZ8pisJkMTtfLE6n0bvR5EV/2KjXPM/8mlJNfHdVxYHLmpT3FCwTQojbJuGex6dsczubzd69v7iYLZdedVEUTjEggjGgNRSO2RKx1rrZbM7n8+Hl0L47g4sLGI5glUCeQbUJiNoYH9EAIaG11jmHNbwatFVCAE1Xg63WV9FKKWb+gk4ru46muzpzbUpBbzpzIbEja4sky5I0LYoi8OXc8cEpT5uovId85+dPZc1qpSTic7vKN1fdR8j16lvoCX/Emz3oPldgr+sfZi6KYjKdvX1/NpzOF2mW5UVhLRhfKcW3nFGCiL5nDtv13708/MO3vZdHB9VKRcYNwbp7t6lVK61GvV2bh55ZKiR3RxsWM+eFuxiO3vdb7y76x51WJd6vYPMmdH9vu/nDjAKUH2urWf/9N8dLCxhUjMI3/fF0kRS3OFjvekEQJHNeJcl4vjwbTn5+e1aJA0+rVqvp718rvfzqI2K6k3eeARxRXtiisA/wg35umMtjhXwQ4kae7jngVybhnseHmZM0PRsO/ylb/j9H3T9WG+OFz5UWFEUwSmppUSNqWmBw1eVk+d/+P5hO8+EwSZLyz4ui4DzHxQIAWGGqMYUP2TrWMWz9Wj4oB1ABwOcnEbuycnbbsV/qHcvJNbDZFIQmQHRg0kUw6K9miyxJ0jiKpNnHg8NABXuZCbOoUqgVOes82HRYM5xpNpotoNNmudeCy0AkIQA4BlWWitAMlNuoKCJE31ofyc+TgEmhZH/cFkRQAAFz4NhjrUldnaAhgAZYf74wv4WnRgPaR8/XRRnqfXqfcnmZw4zAitgwK4uRRVXh4a0+70K/ZBUopALIkUcENz/zJqLVKj29GP+ln/3xbfZ+ZDLb8E0NwerEarZVBcT7bQ/GRlePGRUAMCgAyK1jZkZljNFeoBQiQD0I/o9v/d+cRH/3qvfysBNHoYR7AAARtdZx4Dc01atRGAZ+7tC6gqy11lLBzMbXAEBbLRHK7+pGtmPw9Q7sfGDtUDnUFj3HBhhWiJHy3o3x59Plq8NZt9O5eQgAUSmjFXNYzCLKGdNbTfqYmwNjjGbyIPGgqFPqs4+AAGrnqcv9QcQoCl8edTy0L4L8Dyb6PxNYrZDAoVIE4JzLbeacC+MAAFx5BlcmaRtAxOpistczpuH6gYLMB/BhfSfOT9Oazi3jaJn+OJiDP86Mp7VvjGk2G8bsfaHBRODAJxO62CMDRQCbrDHDVrPTTAA292d7LdaQAdBExqEm8AA8RETWbhWs5jZd5dba8kbmvissvgre1IvwIQ84s5zeXtwHAbVz2oUEH4pU3CJSxvmaYl2EiGwNkCJAS1w4bS0VAFDo6zsU71wk1+BDNuUHqE2hjdGGQRXK+NrkqIif7NHQELRTdTxXSeQVSz80ni0gAVcEe55/utpes1PUGHn+IvUe5gHiExLueWTKO6jjyeTHy8G/pdk4aiy1cYg8HILWAMrzPMiyyWQyHPVheIY//bRcLu1yCc6B76Pn8abl+dUh7ZNj267pDwEzF86O54t3/cHlePLdQVduAjxMzOyInHPW2qKwlgA2lxDMBbEjtoCu2NWpbYcP4R5mRgVMAEAMvm9Ak9aokRUCqvLKcO/RKOLmEFFhOaBS3uSnaa+9pyzSfNof/vx+8P5yukxzd2vFYz3PAwDUxhjDqIFJI3fq4YuDg99+/+2335y0mk2pEXMFEYMgqNfrzUpSi4LpMk3TdJmsmDmMg0qlslh9/bDsFWJepflounzfH/cH4+9eHlUq8Q0jcYhYVg8kYudcURS3+pVu0SIik2PnkJ212llHd1AD+Yusm7LXasaYIAiyLPvtaDVZ5WejUbrMLbPW2g+9KIrSPGFmQmLm9c0zAgAIdrTI2NdisQAArXWWF+fDcZ7nSbKKw6BV8SuVeN+m7Otg953k9pS5JGlWLFdpkqbOOTlnuHd3+REg3uOQ9Dv1VAe/b44R97nb4mO4tSzhnkfGObdYrt4Nhv8+TyZ+xarQzB2uco+NV1A1y02ySKb9+dnb5dlruDxTy4LyHIjAGOX7vu8TkTOm/D79JLKDiAjbd8s/yvG5dn3K+fewY4/YuausO8p/+GNHnGb5eL5YJUl5K2a/FRC3jwFAYaEp1TYx+dIUKii3EwIABiJwjgkALTT3WrICAgACBaAZoPwGswCOmZQHiJmyIbgVBIlT7q4GLDxTCnLNuaHUszmwA1UGkjUUmh2CBQCg/e6W3BChSSHOyHPOQRn6e/DH2r2UX7hagQbSQArIp5Qpy9StvJ9XfEp9YkXWACgkpW56HlOOv0izfLxaDvNkmE/nWBSG4NdtD6m/dbdznXtCAOAbzzlHlIMDo9H3TKsWf/+qcXLYOzk6bDYavu9Jas8VRPQ8r9VqfducnbcC3wWXmF9kLk1TnbFndOAcADgEWOdOAqMC1hlGf3PBn2KdAJADcmgsQnko0ARTyidEIxuMVqtVmjrnbh6MQwQE0pQBsTXNWz3ih0BGOYXsiELEUIFvwDzgiHYZ8YnjuNlstlqt3x/jch5FKhhN8yQrEB1o0mi1cgDggAGAytfiAACsaX6V1cjyled5XhA4z5sVmM9Rh/x+Zoej0fHRURAEe38/IzgNuebUFIlf2K0hw5adZqvYAYDF/b5PrLcEIAZ2SG79PIxIqYeFYZJakPcNEaCsSwFWAWZYu71sPkSsgDNQqK1rjFuk2DIXyNYUVJ7D4jqPkoELTb6Do+l+2zMrC5tuiNs5PqHBYmUrnvZQFUtbLWxV6YYGiB9BVOILWMULQ7PIzWM7staruIIInDsZ73m+tKuV0A7vNQArnz28zzqWNyXhnseEiNI0vbgc/OWi38/tMo4mSboskMg7ODxYDifj84v89A1cvoPxJSzHkGW+HzqtKQi01mVfTOccfZzgsx3NuZpy7fTP7X8U3285ZT1W3gr3AAAxO+fcXbUXEV9GIWqjtVbGmO2OPAqUAqWYy8f7LfPDf8tEn/UEm2VkrUXU4ByuU4rkTt2tWmf33MdTl7lj1hE93S8BpZTRWmtdjlW9yxeJiKhAK220vnnQxDlK8zwtrAMs7O2WHSIia21RFArBhF4jrn5/3PvDqxe9bqfZrAe+L7GebWVQoN1u//2rPCtsszF+P5pXK/HFYDRaLCfjsV/ZL6yzL+ucdS63tEyzZZJY63z/5iFaVEobz/gOlFO3ur87R1clq66eRm3KhN3e8/4a5WC9MAx7vd73S7R53mlULsaLwXQ5ms0ny+V8uQzjKjNvdooPL1F/pd2kVquVp4tERAArcvNlOpotk7SS5XmF6AuqL91N2gUiKoWh79UqURyFkhJ475RS+q4qApY3MzZlHm/3OaUc0S0p66zf4wqsexfJYC7xtZTDuEaT2V8uBv+4yF+r8NwZXLqeU77PlTev8f3l6sd/gzc/waIPnIBfQKSzpV7XVGYiWzCzsxacU/562DUiAiJvAi6MeO30nfnMuG8j9uuLCNKee8r97t7iFyCgBvYQAoLQeWQzWsLWYC4GW+bpFBzvtWDN5faJAMrhVg8Y7QEhGaPYEhZOeRYVsQzmui3lWTJ4zB6DzpksssF1doBFtusAhb2VQwyVxUGe9OertQ4Cvx6ZiuEKpEDMAPnetdL241NWJUuY1HRYj0wY+FrdKH+Tma2zyzxLbJ5zZtUKkcqDw6/ZHjKvAADFBACArAkAWIHNyfoe1yu6FkXtWuVFp/F3J80/nDQOu504iqV4/+e01rVa7TevIPD1cSP6+fTiMHCnEb67cIPxAqgoWDkEh+bDvWLGvbc3VQAAoAMkRAdsoLymckkGtHLhOF1Olos0z6Io/IVFlRBQoW+gEkCd1Nmy/PK/LZgvPIsAoIpUAYEfKYo0stYPOnqIiL7v93q9/1Xpo4Z/Phy9OR+8PrdnXnLOdkaWaWYtOgQGw4TM7JiYeRUEX2UFojDMsjxLMwDwPc8zZpbm56NZktaXy2W1Wgn3LM6ttGINZBx5FnSKysEmw4qZGByDBQDi+l7ricoCgEMHYHCdFUHK87wwjmMvivx9x52Jr6iMuWitPA2+tiEXrMLbu22AAIaTEKynbr3jBAMDggLrcRZblzN5BFYTACAUm1pU8L7m7bdcncDVWfDWK9AGnF9EnvJAZV5R9XUDqcsM7D/FUyYGYKdtrl1hMjCZNRmQBYD3tT1vY+hkr9krll9lUPF8ow3eSdDw15Bwz6PhnFssFm/Pz//YH1wUNPV4ZS2Dh4hZlr35r/8DxgsYnsF8DkxgNGgCpbgoQCneHGsR0XgeG3M1/OFqA71mYNfHmT7XrtVtZ/eUA063s3ue6gDUpwQRjTaB70e+Fwd+YjHyAgBgIABQUBhwyGW4p7LXksuDIoNi1hYYeH1dx7Zgo2Pf02BDNJUoDANfSxvm26S19rQJfT/0XY7IbGgzBlQzqvKuC97KIcbTKvRM4JmnenZejr6pV6udVrNVr85ztmhyADS3G8hQBKGv0QStRrXbatar1TIn9Bf/sBzJNV8lqywvisI3mn3tft32wMxgiAEUrw9CmhGANSIwdqrxy177Rbt92KweN+u/Per+5vio22l7ntyfv0a5RTUbDd/z69VKo1ZpNWrd9rTbrr+/nJ4NJ/PMWSYLuhwCywjACtR++xcaD9bRWONAleEexeDrUAFnebFMsvkqSfOciG4SlUNAY0zge3EUxKyayrvVixX0bKwVM4M1voJ6JahXK1EYaK0f+Dalta5Wq0EQdlqt416312732s3zwbQ/mc2X+fvLy/kyT5wDMMyKiMpwj+/vd/zd+exKKSZkp5WO4yjw/dDTmePFYjVfLFutPPD9vRaolPKNF/he4JnANw4NbMI9ismgUoAAoGi/44vRHgI6RAbNYAAAgULPi4MgCgLPGMkKvF9lqloQ+IHvhYEfkLnFcA9C5Plh4PueZ275XHF9GbUZpq0QFYJaT0QFuN6ev+h66vNwj0JkRIWooPwJChE/L+n8JCCiUkoBrF8sIiIyInxBPZ095988qdJKPfyvDgn3PA7MbK0djsZ/Gs7/yZmzuDLMKE8KROv64/Ff38G//TtkCdgEdYGeZVUwZWAJ/d52u+L1jqFUmqZl5s66mc4mkYfBXE0vU9TWv+L1FeP5Zvd+t//i+qk7lo8AgLzJIcLN/5/m19aToRDjKOzVot92aiHSxNdeGAIAY3nAI4NYZvdY8oj3aEZQlochACLlAImxvFlRWMWFjkMwqCOtv+/6x80gDqV+x21RSgW+361Uft+p1zxvmVti4vUILwIAZGIGdpq/dg5OeQOw29Qvazres6nzY4GIxuhGLX7Za/7+uBFodqxWuXW3vDkzm9gPPPRe9Rove816Lb7JeXBZuMdaC85VFB/5ym/XrHXl6eUXbw/EvFA1ZkBFikEDGySNYJBi3/Tq8bdHBycHnaNu66Dd7rWajVotCALZ5XcpN6pqNfZ9U61ER+1Gfzg+69ffNgdv6uFgukxza1kRkwVN5eWHsjeusIOIALpRFt9g0JaZWZU3lowKPHLtqmkHJlDqajjRLy5ca1WJwl4j/u6gVVvmDYu32KkHEV3N0waAwTlPq1ocvDroHLTqYRA88O+Zcrye1trzTBQF9Wp83GmMxtPhZDpbrC4ua/3hdLJIbMEEQITETITZnskEu57aWkvkMRiFyhitNfqe6tSRyJVDqvf61BDRaN2sxK+a9XSZtpWyiMxcnj0oIIO4rt0DN/0+KbdPrRpKISMwKdpc/ca+97IZ9yph6Hl3NYpIXA8Rfd9r1+KX3cb4aNq0WI6vvI0n0gpbQePVQb1TjwLfU/uOVNjr6QADrXvO/tZxy/ECXKHA4SZrlVV5ZXShs70Wqy3BpzXXAAA8JOs4UmQQCuKYOWZqI3jmSZ0Slxe2RpuaMS/I/d4xreh85arKWmudw7m/3/sJbr/OaL2cXxAfBlD1jNYP+gABEu55LJg5y/J+//LP89UYvVybZTpP0hwALt+9g//yX6DeBKWU1koTaiblHCIwly0wmdk5R84BM35cjuGTpJ5fnC7ELzLGNOr1716+AFDjxWqZ2s0AAUZArVijUsjMbEndvNMzrGvbAQGTg4KYGJiBmLRW7Cj0tFYQGdVtN14e9Rr12lPN/rh3Wut6vfbq+PB/t38/SYrMWtpUf9/cRWImdk7T167iUo63r8bRt8e9Vqv+BV1+H4VyJ/r2mxNG/Wq6tAxZ7ljv1y1rL4jAzL42noZeo/LdyVGzftO3t0weaTcav//2m0qlljt2RAz8a7YHR7RkzcCIrAE0olFgFHoKqlFQj/yjdvOo02o16pU4DoNA71Np6HnCTZ0XY0ylErc77cODbrfTf/XNcjRbprktiInYAToGBFTK3biFVjkGhxQiAxKjYyLCcth1YLRhikN91G4edDvBjRuxa62bjfp335wAwDzJJ2lxS32yynCAAjLrWllsFEah323UTl4c1WrVRzFCsAz6KKU849UqlV67vVwli+VqsUouR5PZIsmywhIRgSNiRvurd5fyfXPOIZYN3svC+aCVqlTiV51Kq9m4YYbg9jLDMDw+6P0vf7Cdbne2zCzDpkk3KmSNSgExs2V1w1ASYlkNzSlERmBGZiwDjp7WrWr87fFBrVqRL5D7hYhRGL44OnAE9UZrWpB17jYOeYigla4HqtuIv3lxVKtWby/Sh4ieZzrNVl7kK+A5qATYKqByOCGSYkBwADAO9ituoCCHTaBnO9xjtHZZERhPI9pKHnheiOoFeG0VP7HqVEqpKAyP2l1L2UoD1SrfRJUorDrniDDZNxsa871mr+fcZe/7qHLgR19SkP5uPc0T5SeGma11s/n8dDSbdU+yWbLI8jRNL1+/hjdvYTKFeojLYaWwPltULrOF9ZwLAJQp8nIsPYJCULo8KSMALs+ZNsXwPlTFYwUAXObCbQ3pgh1j14n3rd2zY/5dY+Pd9ZXStdYPvjDWM1VeUdTrNUSsh2GeF59c4G2yzZCZife+9QebRq3lYq+qTq6vNxQao8MgqNfrtVrlUZyjPzrlRUWz0QCGmu9lWb5dN32TSAhERPShMPzX5ft+vV7vdTv7Xks8CuU7XKtWXvTalcAkSbbpQngrb+bWE6vydlkUBY1GvVat3OTssNzlK1F01G7GnvebXnddRLs8uAAAgEIkJnI33R4YmIgKZxgYN18aSqFGUAo9parVSqNRr1Riz/PKqO7T2wxuyVUmSOD7lShs1qrj6Wy1SorClvXOygg8ImrlEG9ahBIRlTJKlVfgTJuawFCG6ozWWgW+16jXK1F0k0B8uZ6NRl1r3ahEaZo5uq3q+5tvLQXlTgCICFrrKApr1VoUhY9o6yozuMvu7LVq1Vqb5/k3RwdpmpUdDD7cYtmRVX1zV3njRERE5VdBOU0pFUdRvb536XREDHz/sNM2AMeNalFY3jTnLrfG8v/MTLxHb1allNam/Ii3TzqUwsD3G416s1Z7YhfDj45SKorCw27H1/qwVcuLvGy+eUvP5ft+FEWNer1Rv92PPgzCV71DePcuYJNby0wAanPR9WHsAix3ZKPsKoLhtgZzwYfFABCwLkNAyBqRA0+12vWX3xyH4c2Kpj0SSqlqpfLbg8Pg/fsOh3ZcMAWQFMBlvtSOTlu7Puhd17M7vmQwQM9gU5mXvV618tCDxRLueQTKqj3vLy5H89WspleOxpNp//wc+n2YzSDLyk0aN+1bPv3Ouvp1x/Rd88thT3wZpVQQBOWZ+ue51tvbFe8zkmtbucjPT/UQrwr+abnbf3uUUnEc+77f7XY+z88qP+IvCOTdXDmkq7zUv6WnuF9KqSiKfN9vt9t33ICsHPNrzB57ECIGgd/ptJvNZhnQ2V7hL9seyrmZ1zv1ejnMmx1clTELOU59gfI9LOMCZeS03MaYAXDrs7vBeKtPFnv1+KMNoAzxw4cP7oaLLRM9PM9rNht3shd8UsdwHTp54CO5rnV1EPQ8LwzDarV69RFvvY1f8/3crghZPlZKld8h+25FxpharRpFoXP0eYD4amn7bg9XQeFP/rDMjDdSu+e+lVmiZRzfOffVR4J/8lzlDq61vtVYT7k9d9rtSqVird1sz1dR9K0XuOu13vw9uHoRH/9JGf8Nw+CJBTSVUmEYHvZ6zUZjc0vsBr7WNqUAET3jBb7veQ99oJyEex46Isqy/OJy8GN/MAlr2XCen/cnr/8K71/D6ALSFAAAMGRn2OoydZo9ZB9oK2lnK+Jz1XXr6lfYqt2zjjSXR8St2j0712/v8a77XZshOviwAlKq+dEoT5HvsQuAbCS3qryWuPfD2xP+lK/eYc/7GgU2vnQdbj7nZm2/5gp8kjL2+ZN+zSd7lq4yQe7ySffarsqowX0dSp7ANnb1HsL+8ZFf/9Rf/IdlMPfrrs8vPuldPp241pP86MvjuOd5935K/PQ28vLuchAE93uMePhvrIR7HjRmdo5m8/lP5/1xkq84+Je//PjzxUV69g4mA3AFGAOI4AiRrnLZ13cwNr9s/8StuA9sbaafTIfPpu/elPfdxPeb/+MdScI9j4x8Uk+bfL637XG9w199bR/Xy3+8Hv77/PDX8FF4XG/j41pb8RU9vY/+sQQFHiN5b29Cwj0PGjOnWXp2efnzfPnamD+fDsf/9b+pxRLSEXAGIYABcAXkBRmvQCDyEDynfAsG2AcAUIv1shDXuTzlLsGbH5vpm11FX83PW9N3Rk33rt2zX/z1QefGCSGEEEIIIYQQD5KEex40a+10Nns9HI2y4u10/o//+qfBcOSsBYPgB+AzcAFFAWkKkYebrJ6r/wIA8FbCzlYEtKzECFspPPjZ/DeLmO4ZT90z/irZPUIIIYQQQgghxL4k3PNwEVGWZ/3x+DxN3iTJ//vDT6fvfmovVgBu6ZMFyo0DLkBbqGChC7Qhowb2LAQWQ+AAEAEXAJ9WX8atxlufTF+Hb66Zvsu9hXvubwysEEIIIYQQQgjxoMlYmQeKma210+nszeXlJC/ejyf9H36k6RQ2/U2stVAUAIBRFDabV3/4aRWerX/4dIaPf73h9IeAmYmYie64YY0QQgghhBBCCPEoSHbPA8XMaZpdTGeLAk9Px//9//4vxdthcHg0LoYABhENhlwAWuQcHYDjKFHlvxBDgpgqRAAg9OHjEVvlA7VuwPXpdGc3wZ11o+t11Z5dLXj2nW7tfu9DpisAAFh2LiQAcECFMuz5DhRJrEcIIYQQQgghhPiMZPc8RMxcFMVkPrsYjv7y9u2//uXH+SqhIMiyDPbPx3nIeTpfhpmp/J9k9wghhBBCCCGEEJ+R7J6HiIiSNO2PJ28Hwz/++cd//tc/ZQlz4EOWrkM2ChmAEXDTPKvsu74d2SFc1+IpZ+DN9PWvu6IkakdIaM/pvCuytGs5u2zWu/zP+peratNCCCGEEEIIIYT4jIR7Hpx1as9sdtq//PHd6V9ev52PRs6rKT+kq4DOx82z4LrpVw+3f/5igs+uGfadvu/yf/HPyv8AAMIvvwohhBBCCCGEEOI5k3DPg1Om9gymsx9P3//Lj2/H82XhR4wMuVXKABYAsM7cQWQA3sqXWWfuqHUKDwCQwqvpgFvTieFDc/YPD3ZlzOxKpNk3wUYScoQQQgghhBBCiNsm4Z6HZd2Qa744Hwz/9Neff3j9Js3yKI6TwjlLqDVscnpwO1QDAMwfEnl2NFm/QVf1r5bds2uk1VfJ7tm3m7sQQgghhBBCCPGsSLjnYWHmLC8G09kPp2c/ng+Hi9Uid8oYX/s5kysyDnWZlvOhdk8Z+qF1pg/DeryT+7x2z1atHy5bXeFVPZz1A94VvtlVo2fH9DJ76Br7Rmpo+69kMJcQQgghhBBCCPHLpDPXA8LMRWGn8/lpv/9Pf/6hP54QQJEXaZpqrY0xkOdXM0t/LpaRYUIIIYQQQgghxHUku+cBIaIkyy7Gk7+cXvzUH11Ml0vHJogBIM9zBRhWKzkSbErt8FVuDgCVD9S6JdaHxlhEAMCI/HF1Z1Rq89snQ72ujwCW83/OEV3/YnY18tqxHNq1nOsws0JV9heT/lxCCCGEEEIIIcQnJLvnoWBma91svnhzdvHnN2/PBqM0L8pQxr75O58OmNox/bHn+0igRwghhBBCCCGEuJZk9zwURJRkaX88+eH9+V/OBpeJTUgTaEAwgJpZASNirrZq8QAgIm3V6NkUb/7QkX2dZrM1/Wq2q+XgVk2fXSV3dtlV62fny9xzOZuiPVK7RwghhBBCCCGEuCkJ9zwIZWrPfLF8d9H/4e37s+EozYurtBv80Cf9Q5ut9S9XoY/tLl2In2b0fDZ9ezlbS9nb14q87FyOhHuEEEIIIYQQQog9SbjnQSCiNM8ux5Mf3p//eDEYrlzKQMoAgGJEcMCgysrEqOAqMafsw7XdGwu3Yjrq4yo+nzRu30rwgU1qD8DO9Bu+pwCLxHWEEEIIIYQQQoh9Sbjn/jGzdW6xXJ72L//85u3p5SDNi6shV+vUHvwQ+PiQmLMdvmEum7JfFWNeD4/iT1N78LMEH/x4+l72/atdBXd2LUcGcwkhhBBCCCGEEPuScM/9I6Isywbjyc/vz346vRjMFjkbBh8ANkEeRuUU8VVopszrKWM7rowG0VZRnk3tHtxqyFU+F14X8flo+o5Ayq7sHlR7hnt2FAfatZy/Ee6RSs1CCCGEEEIIIcS1pDPXPWPmoigm0+nbs/O/vHl72r9cJul21/Nf7MN1w+mw7/wPGzFJE3YhhBBCCCGEEOJakt1zn9YVmlfJ68H4f759/z9Oz/qrxCFpmypwAACoGDEDhajSMhxTBACA6sMILHQIAJ5WUI7ogjLxZ52ok68DIusWXQzIZQIPEcBHBX3W4R6zXwSQWO/3mpW7fjk7Zg8AASAxABpAM4B1zDkrjZ4mJEcS8RFCCCGEEEIIIT4h2T33iZnTLDsfDP/4w0//8y8/vesPVll2FXnZO39nl0+6dP3i9MegzO2RWI8QQgghhBBCCPE5Cffcm/UwrsXih9P3/+PHn/709t3lbFkwMSjU1xTZIQRWHihkhE0x5nX7rWvjPgxwVc3nmr7sf7Omz6MgsR4hhBBCCCGEEOJaMpjr3hBRkiTno/Gf3r3/4fTscjLNC8sMWim4irxsjbS6erj9r7/cpOqT5uufx31uthghhBBCCCGEEEI8FhLuuTdElCTp6XD0w+nF6XC6zCyBQqUIlQMEVADAoBCRleYyxMP6Kq+n7LrFsE7woavWVQhl1suml9ZWfGcrVHSVGfMrG7ELIYQQQgghhBDioZFwz71h5jwvJotlfzJdpqkjUqoM8YBzrqzGXEZjtsI0H9ql46cDsnYPbfq4GPOmiznjx6k9+IvLEUIIIYQQQgghxGMg4Z77wcyOKCvsPMkXaZ4VjkGB1gBAxERkPI8ByrweAFU24EJUqAAAsMzrwXVNH0QsqxbTdghoO7vnsxo911aDRkQJ9wghhBBCCCGEEI+dlGq+H0SU5/l8sbwcT+arlXUEiEqpdeCGPvQl/1r9ub5any8hhBBCCCGEEEI8bJLdcz+YOUmzy8ns9WA0XqUFA6EiR4iojQ8GLBOAAQZgDXCVrkN6nd4DgJtcHgQAWMeHcF2yh69CN2XkCJHXRX/WeT3lwLHt9Vl3Nd8RAPxk/q2/3DNiiPtmD10/v9YaAJjp2n8VQgghhBBCCCGeM8nuuR/OUZplo8n0YjydJ6kj4s/6in9It7lhPs6O+XdOF0IIIYQQQgghxFMk2T33gJkLayfzxdloOpov08I6BgBkUADICAAGkJn1phKPuirJwwr449o9rizbzNsN17f6rG9P/6SP+3VkSJcQQgghhBBCCPHYSbjnHhBRmmWD0eT0YjCczfPCXjNTOUzrKmpzbbwGr2oub8V3Pp5/e/rW3DtJuEcIIYQQQgghhHjsJNxzD5g5L+xksbqYz+dJbgmIERGpbL6OCFym82yq9qAGAFAfGqXzuh078icBmjKvR22FfvhDnOgXU3uEEEIIIYQQQgjxBEi45x4QsXWuKGxhnXXu86o9JURkXMdpAIDX4Rv+ELXZzuhhBsR1uGcrrMNbCUHb4Z6/8aTXTt81vxBCCCGEEEIIIR4aCffctbIHlnNkiS0DIwEoxjKaowCQQQMCoGKgMq+HcWs0FmyK+AAwrkNCiAj0aY2e7VDRJ9Wd1+3eryPhHiGEEEIIIYQQ4rGTzlz3ZKu6zi/MuKMP1y9Ov2k/LyGEEEIIIYQQQjwtkt1z18pBVUqh8znxCwQfsCzVjFD20UICAGACAEDLsC7FXD6wFF0tZZ3dU85A68UzlstBAAQA1h6U9X6uMACA0t61q+d2JfGU9YOumX6jV71lx3J2YEMAAAZAAygGVEDMoBys85mEEEIIIYQQQgjxCcnuuQeIoJRSSiF+NALr04SfX5r+aZ7OrvmFEEIIIYQQQgjxnEi4536UNXgUfNxAffvXvzn981o8O+cXQgghhBBCCCHEMyODue4NfhLZuXqMm8fwWfRnO74Dm4Fhu+aXUI8QQgghhBBCCPEsSbjn3iiGTe8sLNur8ychm9LHmTsf5fV83HD92vlBGmoJIYQQQgghhBDPjIR77l+ZoPPJgCzeaqZ+owFc64ARfBo2EkIIIYQQQgghxDMjtXvuR9lPS/PHg7O2QjmfVHH+ZPqu+a8JCQkhhBBCCCGEEOKZkXDP/UBEhWq7vs4nkZqvPl0IIYQQQgghhBDPhAzmugdlIEYxh9pjJiKizdAtRgRAAEB1fSSOiGCTzrMeugUIAEopAOBNhIeZgcqyPRo+Tv8pH5TLEUIIIYQQQgghxNMj2T33QyEarQPf00qpq8jOzfJxJH9HCCGEEEIIIYQQf4OEe+4BIhptalHUqdaqUaiVunnNHanRI4QQQgghhBBCiL9Nwj33ABGDwGvWa4edVqta8T2jlMItf/tvP3dnay6EEEIIIYQQQoiHT2r33INNdk/crlZrcWS0BiiuGqvjphH7rr/98OAG8wshhBBCCCGEEOK5kXDPPUBErZXv+5HvxYHvG6MQGTa1e8rwDe8M4HwYwHWz+YUQQgghhBBCCPGsyGCu+6GUCnyvGkWdejUOfGN0OV1q9wghhBBCCCGEEOJXknDP/UDEMAg6zfrLbrtZrXhaX/XnumHsRvpzCSGEEEIIIYQQ4loymOt+lNk97dD7TatyEnoDV6jCWi8C5ReIBSNrBABQzIgAjhERGACKMg0IATYDucpoD5WTERFRbR4AgFXryj6bsV6bhxLoE0IIIYQQQgghnii56L8fiOh5Xr1eP2w1T7qtbrMehYFCdM4R0X2vnRBCCCGEEEIIIR4xye65N1rrWq32zUHnP35/Ml8uyJ2fzdPc5gxGaUVl6WUuc3jud02FEEIIIYQQQgjxmEi4594gYhRFL7qdv//+1WSZjJb5KLuE3BKR0ve9ckIIIYQQQgghhHi0ZDDXvUFEY0ytUvnmoPv748Nvu81OYCrIgXPaFZp5U2eHgQEZAAlQxnkJIYQQQgghhBDiF0i45z4ppXzfa9VrLw973x0fHLYacRRqpZj5l/9YCCGEEEIIIYQQ4joS7rlnWutKFB13m7896v7moHFYCaqatbUe5ZoJmRQTAG1abwkhhBBCCCGEEEL8Agn33DNE9DzTqNVevTj83auTF91OFPpSmlkIIYQQQgghhBBfTEo13z+tdSUKD1vN3x71zi6G0+UiLyaZ0gmABXIIaBQiOiYmki5dQgghhBBCCCGE+Nsk3HP/ENHzvHar8f3LF4PZcphkS6fejqfsyDE5BEZWSgECoAR7hBBCCCGEEEII8Qsk3PMgKKWiMDzudf7h+yTNc4cqzVO7SJxliwzExIRGK6VIijgLIYQQQgghhBDib5Jwz4NQNmWv16qvXhynllYOpkmWnPbtKi2oICYAYCLU+r7XVAghhBBCCCGEEA+dhHseinJIV7Ne+/7kaJXls1UyydJs4GyGqS0cOWCWBu1CCCGEEEIIIYT4RRLueUCUUkHgd5qN748PBvPl6/FkVli7SCnhtJBIjxBCCCGEEEIIIW5Ewj0Pi1IqDPyDdusPJ8lwtiDQwXj8dgSD1aJwhAoc3fcqCiGEEEIIIYQQ4mGTcM/Dgoha62ol/vb4wDFX6rWji8Ef373/09nFaLWyxBLuEUIIIYQQQgghxN8m4Z4HBxE9zzTrtd8jNkL/m9g/MfhCqz+9Pf1pNEp8nYKyDMA+oGYMWHvABnwPAJiZwQJZZKeZAAD3HQOGxS28pi9HsNV7Htc/CYClJb0QQgghhBBCCLGDhHseorKIj9b1MAwqlYofhSrw/cCLBvV3y/kwLRZ5YZ1iVKA0GAPog3RoF0IIIYQQQgghBABIuOfBKht1KaW01lpj5Hu9Wvxdf/jny/5fh5Oz8WycFIvCWSRbZKA8MOVHycCAYDU4hQQARE+udzuDuu9VEEIIIYQQQgghHjIJ9zxoWuswDHrtdhSEnWbjqNs5GnZeXAz+7d35j4Npf5FN04Jyy85yrXbfKyuEEEIIIYQQQogHQcI9D105sMsYHYVBq1Z50Wl+22q8jINX8fnPo+nFdDlYprOkcLTMmRwCM/O6YA8BgIJwv+d7YGPCPkrk4XX5HuT9axIJIYQQQgghhBDPhoR7HgFENMYopXzfq8RxvVKpVyvdVutkMH47nJ7PlpeL9Hy2nOZZ5px1ZNkRSzhECCGEEEIIIYR4piTc82gopcpSPkbrKAi6tdr3vfHFeHo5m50OJ29G47PpYpyko1U2y2xiC0tkQT/2uE+ZxYMEjJvOXAyKQX1B0zEhhBBCCCGEEOJ5kHDPI6OU8n3fGBOFQbvZODlKpovV2Wj8+8XyfDI7n87fDMc/D0en48k8SS3d9+oKIYQQQgghhBDizkm45/FBRK11GfeJo6hZq3UbtclsNut1hovlm8vhv7wL/ifhX+0gXyTFMgfP84JAa83MzIyISqmiKHYs/mG1vUKHAMDrlWJAAmIGIOcQAPFhra0QQgghhBBCCPEQSLjnsULEMnCjtfZ9v1atFtaukrRbrSSz+T8u/jw9O1vNl1A/LufnRz6qSwghhBBCCCGEEDf0/wOZnkSwCr41IAAAAABJRU5ErkJggg==' style="width: 77%;" />
                                                        </td>
                                                    </tr>
                                                </table>
                                                <!-- column -->

                                                <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->

                                            </td>
                                        </tr>
                                    </table>
                                    <!-- Logo & Webview -->
                                </td>
                            </tr>
                            <tr>
                                <td height="40" style="font-size:40px;line-height:40px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center" class="center-text">
                                    %{--                                    <img style="width:190px;border:0px;display: inline!important;" src="images/Email-16_Intro.png" width="190" border="0"  alt="intro">--}%
                                </td>
                            </tr>
                            <tr>
                                <td height="40" style="font-size:40px;line-height:40px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td class="center-text" align="center"
                                    style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:42px;line-height:52px;font-weight:400;font-style:normal;color:#FFFFFF;text-decoration:none;letter-spacing:0px;">

                                    <div>
                                        Thanks for your order!
                                    </div>

                                </td>
                            </tr>
                            <tr>
                                <td height="15" style="font-size:15px;line-height:15px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td class="center-text" align="center"
                                    style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:16px;line-height:26px;font-weight:300;font-style:normal;color:#FFFFFF;text-decoration:none;letter-spacing:0px;">

                                    <div>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod dolore lorem tempor incididunt ut labore et dolore.
                                    </div>

                                </td>
                            </tr>
                            <tr>
                                <td height="40" style="font-size:40px;line-height:40px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <!-- Header Button -->
                                    <table border="0" cellspacing="0" cellpadding="0" role="presentation" align="center"
                                           class="center-float">
                                        <tr>
                                            <td align="center" bgcolor="#d6df58" style="border-radius: 6px;">
                                                <!--[if (gte mso 9)|(IE)]>
        <table border="0" cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td align="center" width="50"></td>
            <td align="center" height="50" style="height:50px;">
            <![endif]-->

                                                %{--                                                <a href="#" target="_blank"    style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:16px;line-height:19px;font-weight:700;font-style:normal;color:#000000;text-decoration:none;letter-spacing:0px;padding: 20px 50px 20px 50px;display: inline-block;"><span>MY ACCOUNT</span></a>--}%

                                                <!--[if (gte mso 9)|(IE)]>
            </td>
            <td align="center" width="50"></td>
          </tr>
        </table>
      <![endif]-->
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- Header Button -->
                                </td>
                            </tr>
                            <tr>
                                <td height="40" style="font-size:40px;line-height:40px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-arrow-divider -->
                <tr>
                    <td align="center" bgcolor="#FFFFFF">
                        %{--                        <img style="width:50px;border:0px;display: inline!important;" src="images/Arrow.png" width="50" border="0"       alt="arrow">--}%
                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-content-16-1 -->
                <tr>
                    <td align="center" bgcolor="#FFFFFF" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            <tr>
                                <td height="20" style="font-size:20px;line-height:20px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <!--[if (gte mso 9)|(IE)]><table border="0" cellpadding="0" cellspacing="0"><tr><td><![endif]-->
                                    <!-- column -->
                                    <table border="0" align="left" cellpadding="0" cellspacing="0" role="presentation"
                                           class="row" width="270" style="width:270px;max-width:270px;">
                                        <tr>
                                            <td class="center-text" align="left"
                                                style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#000000;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    Invoice Number <span
                                                        style="color: #f16c7a;">${saleBillDetail?.invoiceNumber}</span>
                                                </div>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="25" style="font-size:25px;line-height:25px;">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td class="center-text" align="left"
                                                style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#000000;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                  Order Date
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="10" style="font-size:10px;line-height:10px;">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td class="center-text" align="left" style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:16px;line-height:26px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">
                                                <div>
                                                    ${saleBillDetail?.orderDate?.split("T")[0]}
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- column -->
                                    <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->
                                    <!-- gap -->
                                    <table border="0" align="left" cellpadding="0" cellspacing="0" role="presentation"
                                           class="row" width="40" style="width:40px;max-width:40px;">
                                        <tr>
                                            <td height="20" style="font-size:20px;line-height:20px;">&nbsp;</td>
                                        </tr>
                                    </table>
                                    <!-- gap -->
                                    <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->
                                    <!-- column -->
                                    <table border="0" align="left" cellpadding="0" cellspacing="0" role="presentation"
                                           class="row" width="270" style="width:270px;max-width:270px;">
                                        <tr>
                                            <td class="center-text" align="left"
                                                style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#000000;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    Bill to Address
                                                </div>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="15" style="font-size:15px;line-height:15px;">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td class="center-text" align="left"
                                                style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:16px;line-height:26px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    <b>${customer?.entityName}</b><br>
                                                    ${customer?.addressLine1}<br>${customer?.addressLine2}
                                                </div>

                                            </td>
                                        </tr>
                                    </table>
                                    <!-- column -->
                                    <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->
                                </td>
                            </tr>
                            <tr>
                                <td height="20" style="font-size:20px;line-height:20px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-content-16-2 -->
                <tr>
                    <td align="center" bgcolor="#FFFFFF" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            <tr>
                                <td height="15" style="font-size:15px;line-height:15px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center" bgcolor="#f16c7a">
                                    <!-- Invoice Titles -->
                                    <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation"
                                           width="100%" style="width:100%;max-width:100%;">
                                        <tr>
                                            <td colspan="4" height="15"
                                                style="font-size:15px;line-height:15px;">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center" width="8.25%" style="width:8.25%;max-width:8.25%;"></td>
                                            <td align="left" width="41.75%"
                                                style="width:41.75%;max-width:41.75%;font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:500;font-style:normal;color:#FFFFFF;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    Product
                                                </div>

                                            </td>
                                            <!-- <td align="center" width="20%" style="width:20%;max-width:20%;"></td> -->
                                            <td align="center" width="25%"
                                                style="width:25%;max-width:25%;font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:500;font-style:normal;color:#FFFFFF;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    s.qty ( f.qty )
                                                </div>

                                            </td>
                                            <td align="center" width="25%"
                                                style="width:25%;max-width:25%;font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:500;font-style:normal;color:#FFFFFF;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    Price
                                                </div>

                                            </td>
                                            <!-- <td align="center" width="9%" style="width:9%;max-width:9%;"></td> -->
                                        </tr>
                                        <tr>
                                            <td colspan="4" height="15"
                                                style="font-size:15px;line-height:15px;">&nbsp;</td>
                                        </tr>
                                    </table>
                                    <!-- Invoice Titles -->
                                </td>
                            </tr>
                            <tr>
                                <td height="30" style="font-size:30px;line-height:30px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-content-16-3 -->
                <tr>
                    <td align="center" bgcolor="#FFFFFF" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            %{--                            --}%
                            <tr>
                                <td height="25" style="font-size:25px;line-height:25px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center" height="1" bgcolor="#ebebeb"
                                    style="font-size:1px;line-height:1px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="25" style="font-size:25px;line-height:25px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-content-16-4 -->
                <tr>
                    <td align="center" bgcolor="#FFFFFF" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            <tr>
                                <td align="center">
                                    <g:each var="sp" in="${saleProductDetails}">
                                    <!-- Invoice Content -->
                                    <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation"
                                           width="100%" style="width:100%;max-width:100%;">
                                        <tr>
                                            <td align="center" valign="top" width="50%"
                                                style="width:50%;max-width:50%;">
                                                <table border="0" align="center" cellpadding="0" cellspacing="0"
                                                       role="presentation" width="100%"
                                                       style="width:100%;max-width:100%;">
                                                    <tr>
                                                        <td align="left"
                                                            style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:700;font-style:normal;color:#343e9e;text-decoration:none;letter-spacing:0px;">

                                                            <div>
                                                               ${sp.product.productName}
                                                            </div>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td height="10"
                                                            style="font-size:10px;line-height:10px;">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left"
                                                            style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:16px;line-height:26px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">

                                                            <div>
%{--                                                                Lorem ipsum dolor sit amet, consectetur adipisicing--}%
                                                            </div>

                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td align="center" valign="top" width="25%"
                                                style="width:25%;max-width:25%;font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:700;font-style:normal;color:#343e9e;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    ${sp.sqty.toString()+"  ("+sp.freeQty.toString()+")"}
                                                </div>

                                            </td>
                                            <td align="center" valign="top" width="25%"
                                                style="width:25%;max-width:25%;font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:500;font-style:normal;color:#343e9e;text-decoration:none;letter-spacing:0px;">

                                                <div>
                                                    &#x20b9; ${sp.amount}
                                                </div>

                                            </td>
                                        </tr>
                                    </table>
                                    <!-- Invoice Content -->
                                    <hr>
                                    </g:each>
                                </td>
                            </tr>
                            <tr>
                                <td height="25" style="font-size:25px;line-height:25px;">&nbsp;</td>
                            </tr>
                            <tr>
%{--                                <td align="center" height="1" bgcolor="#ebebeb"--}%
%{--                                    style="font-size:1px;line-height:1px;">&nbsp;</td>--}%
                            </tr>
                            <tr>
                                <td height="25" style="font-size:25px;line-height:25px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-content-16-5 -->
                <tr>
                    <td align="center" bgcolor="#FFFFFF" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            <tr>
                                <td align="center">
                                    <!--[if (gte mso 9)|(IE)]><table border="0" cellpadding="0" cellspacing="0"><tr><td><![endif]-->
                                    <!-- column -->
                                    <table border="0" align="left" cellpadding="0" cellspacing="0" role="presentation"
                                           class="row" width="240" style="width:240px;max-width:240px;">
                                        <tr>
                                            <td class="center-text" align="left"
                                                style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">

%{--                                                <div>--}%
%{--                                                    Payment method--}%
%{--                                                </div>--}%

                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center-text" align="left"
                                                style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:16px;line-height:30px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">

%{--                                                <div>--}%
%{--                                                    Mastercard ending in 4097--}%
%{--                                                </div>--}%

                                            </td>
                                        </tr>
                                    </table>
                                    <!-- column -->
                                    <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->
                                    <!-- column -->
                                    <table border="0" align="right" cellpadding="0" cellspacing="0" role="presentation"
                                           class="row m-padding-top20">
                                        <tr>
                                            <td align="center">
                                                <!-- row -->
                                                <table border="0" align="center" cellpadding="0" cellspacing="0"
                                                       role="presentation" class="center-float">
                                                    <tr>
                                                        <td align="center">
                                                            <!--[if (gte mso 9)|(IE)]><table border="0" cellpadding="0" cellspacing="0"><tr><td><![endif]-->
                                                            <!-- column -->
                                                            <table border="0" align="left" cellpadding="0"
                                                                   cellspacing="0" role="presentation">
%{--                                                                <tr>--}%
%{--                                                                    <td align="right"--}%
%{--                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">--}%

%{--                                                                        <div>--}%
%{--                                                                            Subtotal:--}%
%{--                                                                        </div>--}%

%{--                                                                    </td>--}%
%{--                                                                </tr>--}%
%{--                                                                <tr>--}%
%{--                                                                    <td align="right"--}%
%{--                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">--}%

%{--                                                                        <div>--}%
%{--                                                                            Shipping:--}%
%{--                                                                        </div>--}%

%{--                                                                    </td>--}%
%{--                                                                </tr>--}%
%{--                                                                <tr>--}%
%{--                                                                    <td align="right"--}%
%{--                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">--}%

%{--                                                                        <div>--}%
%{--                                                                            Tax:--}%
%{--                                                                        </div>--}%

%{--                                                                    </td>--}%
%{--                                                                </tr>--}%
%{--                                                                <tr>--}%
%{--                                                                    <td height="20"--}%
%{--                                                                        style="font-size:20px;line-height:20px;">&nbsp;</td>--}%
%{--                                                                </tr>--}%
                                                                <tr>
                                                                    <td align="right"
                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:700;font-style:normal;color:#343e9e;text-decoration:none;letter-spacing:0px;">
                                                                        <div>
                                                                            Total:
                                                                        </div>

                                                                    </td>
                                                                </tr>
                                                            </table>
                                                            <!-- column -->
                                                            <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->
                                                            <!-- gap -->
                                                            <table border="0" align="left" cellpadding="0"
                                                                   cellspacing="0" role="presentation" width="20"
                                                                   style="width:20px;max-width:20px;">
                                                                <tr>
                                                                    <td height="20"
                                                                        style="font-size:20px;line-height:20px;">&nbsp;</td>
                                                                </tr>
                                                            </table>
                                                            <!-- gap -->
                                                            <!--[if (gte mso 9)|(IE)]></td><td><![endif]-->
                                                            <!-- column -->
                                                            <table border="0" align="left" cellpadding="0"
                                                                   cellspacing="0" role="presentation">
%{--                                                                <tr>--}%
%{--                                                                    <td align="left"--}%
%{--                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">--}%

%{--                                                                        <div>--}%
%{--                                                                            $290.99--}%
%{--                                                                        </div>--}%

%{--                                                                    </td>--}%
%{--                                                                </tr>--}%
%{--                                                                <tr>--}%
%{--                                                                    <td align="left"--}%
%{--                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">--}%

%{--                                                                        <div>--}%
%{--                                                                            $0.00--}%
%{--                                                                        </div>--}%

%{--                                                                    </td>--}%
%{--                                                                </tr>--}%
%{--                                                                <tr>--}%
%{--                                                                    <td align="left"--}%
%{--                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:400;font-style:normal;color:#00000c;text-decoration:none;letter-spacing:0px;">--}%

%{--                                                                        <div>--}%
%{--                                                                            $4.32--}%
%{--                                                                        </div>--}%

%{--                                                                    </td>--}%
%{--                                                                </tr>--}%
%{--                                                                <tr>--}%
%{--                                                                    <td height="20"--}%
%{--                                                                        style="font-size:20px;line-height:20px;">&nbsp;</td>--}%
%{--                                                                </tr>--}%
                                                                <tr>
                                                                    <td align="left"
                                                                        style="font-family:'Roboto Slab',Arial,Helvetica,sans-serif;font-size:20px;line-height:26px;font-weight:700;font-style:normal;color:#343e9e;text-decoration:none;letter-spacing:0px;">

                                                                        <div>
                                                                            &#x20b9; ${saleBillDetail?.totalAmount}
                                                                        </div>

                                                                    </td>
                                                                </tr>
                                                            </table>
                                                            <!-- column -->
                                                            <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->
                                                        </td>
                                                    </tr>
                                                </table>
                                                <!-- row -->
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- column -->
                                    <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->
                                </td>
                            </tr>
                            <tr>
                                <td height="20" style="font-size:20px;line-height:20px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" width="100%"
                   style="width:100%;max-width:100%;">
                <!-- lotus-footer-16 -->
                <tr>
                    <td align="center" bgcolor="#f0f0f0" class="container-padding">

                        <!-- Content -->
                        <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation" class="row"
                               width="580" style="width:580px;max-width:580px;">
                            <tr>
                                <td height="50" style="font-size:50px;line-height:50px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <!-- Social Icons -->
                                    <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation"
                                           width="100%" style="width:100%;max-width:100%;">
                                        %{--                                        <tr>--}%
                                        %{--                                            <td align="center">--}%
                                        %{--                                                <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation">--}%
                                        %{--                                                    <tr>--}%
                                        %{--                                                        <td   class="rwd-on-mobile" align="center" valign="middle" height="36" style="height: 36px;">--}%
                                        %{--                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation">--}%
                                        %{--                                                                <tr>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                    <td align="center">--}%
                                        %{--                                                                        <img style="width:36px;border:0px;display: inline!important;" src="images/Facebook.png" width="36" border="0"       alt="icon">--}%
                                        %{--                                                                    </td>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                </tr>--}%
                                        %{--                                                            </table>--}%
                                        %{--                                                        </td>--}%
                                        %{--                                                        <td   class="rwd-on-mobile" align="center" valign="middle" height="36" style="height: 36px;">--}%
                                        %{--                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation">--}%
                                        %{--                                                                <tr>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                    <td align="center">--}%
                                        %{--                                                                        <img style="width:36px;border:0px;display: inline!important;" src="images/Instagram.png" width="36" border="0"       alt="icon">--}%
                                        %{--                                                                    </td>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                </tr>--}%
                                        %{--                                                            </table>--}%
                                        %{--                                                        </td>--}%
                                        %{--                                                        <td   class="rwd-on-mobile" align="center" valign="middle" height="36" style="height: 36px;">--}%
                                        %{--                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation">--}%
                                        %{--                                                                <tr>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                    <td align="center">--}%
                                        %{--                                                                        <img style="width:36px;border:0px;display: inline!important;" src="images/Twitter.png" width="36" border="0"       alt="icon">--}%
                                        %{--                                                                    </td>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                </tr>--}%
                                        %{--                                                            </table>--}%
                                        %{--                                                        </td>--}%
                                        %{--                                                        <td   class="rwd-on-mobile" align="center" valign="middle" height="36" style="height: 36px;">--}%
                                        %{--                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation">--}%
                                        %{--                                                                <tr>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                    <td align="center">--}%
                                        %{--                                                                        <img style="width:36px;border:0px;display: inline!important;" src="images/Pinterest.png" width="36" border="0"       alt="icon">--}%
                                        %{--                                                                    </td>--}%
                                        %{--                                                                    <td width="10"></td>--}%
                                        %{--                                                                </tr>--}%
                                        %{--                                                            </table>--}%
                                        %{--                                                        </td>--}%
                                        %{--                                                    </tr>--}%
                                        %{--                                                </table>--}%
                                        %{--                                            </td>--}%
                                        %{--                                        </tr>--}%
                                    </table>
                                    <!-- Social Icons -->
                                </td>
                            </tr>
                            <tr>
                                <td height="30" style="font-size:30px;line-height:30px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td class="center-text" align="center"
                                    style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">

                                    <div>
                                        Address name St. 12, City Name, State, Country Name
                                    </div>

                                </td>
                            </tr>
                            <tr>
                                <td class="center-text" align="center"
                                    style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">

                                    <a href="tel:(738) 479-6719" style="color:#6e6e6e;"><span>(738) 479-6719</span>
                                    </a> - <a href="tel:(369) 718-1973"
                                              style="color:#6e6e6e;"><span>(369) 718-1973</span></a>

                                </td>
                            </tr>
                            <tr>
                                <td class="center-text" align="center"
                                    style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">

                                    <a href="mailto:info@website.com"
                                       style="color:#6e6e6e;"><span>info@website.com</span></a> - <a
                                        href="https://wwww.website.com"
                                        style="color:#6e6e6e;"><span>www.website.com</span></a>

                                </td>
                            </tr>
                            <tr>
                                <td height="30" style="font-size:30px;line-height:30px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <!-- Buttons -->
                                    <table border="0" align="center" cellpadding="0" cellspacing="0" role="presentation"
                                           class="row" width="100%" style="width:100%;max-width:100%;">
                                        <tr>
                                            <td align="center">
                                                <!-- column -->
                                                <table border="0" align="center" cellpadding="0" cellspacing="0"
                                                       role="presentation">
                                                    <tr>
                                                        <td align="center">
                                                            %{--                                                            <img style="display:block;width:100%;max-width:117px;border:0px;"    width="117" src="images/App-Store.png" border="0"  alt="icon">--}%
                                                            %{--                                                        </td>--}%
                                                            %{--                                                        <td width="10" style="width: 10px;"></td>--}%
                                                            %{--                                                        <td align="center">--}%
                                                            %{--                                                            <img style="display:block;width:100%;max-width:117px;border:0px;"    width="117" src="images/Google-play.png" border="0"  alt="icon"></td>--}%
                                                    </tr>
                                                </table>
                                                <!-- column -->
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- Buttons -->
                                </td>
                            </tr>
                            <tr>
                                <td height="30" style="font-size:30px;line-height:30px;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <table border="0" align="center" cellpadding="0" cellspacing="0"
                                           role="presentation">
                                        <tr class="center-on-mobile">
                                            <td class="rwd-on-mobile center-text" align="center"
                                                style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">
                                                <a href="#"
                                                   style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;font-weight:400;line-height:24px;color:#6e6e6e;text-decoration:none;">UNSUBSCRIBE</a>
                                            </td>
                                            <td class="hide-mobile" align="center" valign="middle">
                                                <table border="0" align="center" cellpadding="0" cellspacing="0"
                                                       role="presentation">
                                                    <tr>
                                                        <td width="5"></td>
                                                        <td class="center-text" align="center"
                                                            style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">|</td>
                                                        <td width="5"></td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td class="rwd-on-mobile center-text" align="center"
                                                style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">
                                                <a href="#"
                                                   style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;font-weight:400;line-height:24px;color:#6e6e6e;text-decoration:none;">WEB VERSION</a>
                                            </td>
                                            <td class="hide-mobile" align="center" valign="middle">
                                                <table border="0" align="center" cellpadding="0" cellspacing="0"
                                                       role="presentation">
                                                    <tr>
                                                        <td width="5"></td>
                                                        <td class="center-text" align="center"
                                                            style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">|</td>
                                                        <td width="5"></td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td class="rwd-on-mobile center-text" align="center"
                                                style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;line-height:24px;font-weight:400;font-style:normal;color:#6e6e6e;text-decoration:none;letter-spacing:0px;">
                                                <a href="#"
                                                   style="font-family:'Poppins',Arial,Helvetica,sans-serif;font-size:14px;font-weight:400;line-height:24px;color:#6e6e6e;text-decoration:none;">SEND TO A FRIEND</a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="50" style="font-size:50px;line-height:50px;">&nbsp;</td>
                            </tr>
                        </table>
                        <!-- Content -->

                    </td>
                </tr>
            </table>

        </td>
    </tr><!-- Outer-Table -->
</table>

</body>
</html>
