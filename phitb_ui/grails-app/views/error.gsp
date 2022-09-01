<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">
    <title>:: PharmIT Billing :: Error</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}">
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/color_skins.css"/>
</head>
<body class="theme-black">
<div class="authentication">
    <div class="container">
        <div class="col-md-12 content-center">
            <div class="row">
                <div class="col-lg-6 col-md-12">
                    <div class="company_detail">
%{--                        <h4 class="logo"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" alt="">PharmIT--}%
%{--                        Billing</h4>--}%
%{--                                                <h3>The ultimate <strong>Bootstrap 4</strong> Admin Dashboard</h3>--}%
%{--                                                <p>Alpino is fully based on HTML5 + CSS3 Standards. Is fully responsive and clean on every device and every browser</p>--}%
                        <div class="footer">
                            <ul  class="social_link list-unstyled">
                                <li><a href="#" title="ThemeMakker"><i class="zmdi zmdi-globe"></i></a></li>
                                <li><a href="#" title="Themeforest"><i class="zmdi zmdi-shield-check"></i></a></li>
                                <li><a href="#" title="LinkedIn"><i class="zmdi zmdi-linkedin"></i></a></li>
                                <li><a href="#" title="Facebook"><i class="zmdi zmdi-facebook"></i></a></li>
                                <li><a href="#" title="Twitter"><i class="zmdi zmdi-twitter"></i></a></li>
                                <li><a href="#" title="Google plus"><i class="zmdi zmdi-google-plus"></i></a></li>
                                <li><a href="#" title="Behance"><i class="zmdi zmdi-behance"></i></a></li>
                            </ul>
                            <hr>
                            <ul>
                                <li><a href="#" target="_blank">Contact Us</a></li>
                                <li><a href="#" target="_blank">About Us</a></li>
                                <li><a href="#" target="_blank">Services</a></li>
                                <li><a href="javascript:void(0);">FAQ</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-md-12 offset-lg-1">
                    <div class="card-plain">
                        <div class="header">
                            <h5>Error 500</h5>
                            <span>Internal Server Error</span>
                        </div>
                        <form class="form">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Enter your search term...">
                                <span class="input-group-addon"><i class="zmdi zmdi-search"></i></span>
                            </div>
                        </form>
                        <div class="footer">
                            <a href="/dashboard" class="btn btn-primary btn-round btn-block">Back To Home</a>
                        </div>
%{--                        <a href="javascript:void(0);" class="link">Need Help?</a>--}%
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/> <!-- Lib Scripts Plugin Js -->
</body>
</html>


%{--<!doctype html>--}%
%{--<html>--}%
%{--<head>--}%
%{--    <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>--}%
%{--    <meta name="layout" content="main">--}%
%{--    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>--}%
%{--</head>--}%
%{--<body>--}%
%{--<g:if env="development">--}%
%{--    <g:if test="${Throwable.isInstance(exception)}">--}%
%{--        <g:renderException exception="${exception}" />--}%
%{--    </g:if>--}%
%{--    <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">--}%
%{--        <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />--}%
%{--    </g:elseif>--}%
%{--    <g:else>--}%
%{--        <ul class="errors">--}%
%{--            <li>An error has occurred</li>--}%
%{--            <li>Exception: ${exception}</li>--}%
%{--            <li>Message: ${message}</li>--}%
%{--            <li>Path: ${path}</li>--}%
%{--        </ul>--}%
%{--    </g:else>--}%
%{--</g:if>--}%
%{--<g:else>--}%
%{--    <ul class="errors">--}%
%{--        <li>An error has occurred</li>--}%
%{--    </ul>--}%
%{--</g:else>--}%
%{--</body>--}%
%{--</html>--}%
