  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  <spring:eval expression="@environment.getProperty('app.static-min-url')" var="staticMinUrl" />
  <spring:eval expression="@environment.getProperty('server.servlet.context-path')" var="appPath" />
  <spring:eval expression="@environment.getProperty('google.gg-captcha-site-key')" var="reCapSiteKey" />
  <spring:eval expression="@environment.getProperty('app.security-service')" var="securityUrl" />
  <c:set var = "requestPath" scope = "request" value = "${requestScope['javax.servlet.forward.request_uri']}"/>


  <script>
    var appPath         = '<spring:eval expression="@environment.getProperty('server.servlet.context-path')" />';
    var reqPath         = '${requestScope['javax.servlet.forward.request_uri']}';
    var maxFileSize     = <spring:eval expression="@environment.getProperty('spring.servlet.multipart.max-file-size')" />;
    var staticMinUrl    = '${staticMinUrl}';
    var pageCollection  = '${requestScope['collection']}';
    var pageTitle       = '${requestScope['collectionName']}';
    var pageMetaData    =  {};
    try{ pageMetaData   = JSON.parse('${requestScope['metaData']}'); }catch(e){ pageMetaData =  {}; }
    try{
      let at = "${requestScope['access_token']}";
      let rt = "${requestScope['refresh_token']}";

      if(at && at !=null && at!='' && rt && rt !=null && rt!=''){
        localStorage.setItem("access_token", at);
        localStorage.setItem("refresh_token", rt);
        window.location.href = appPath;
      }
    }catch(e){ }
    var serverError     =  '${requestScope['serverError']}';
    var pageParentID    = '${param.pid}';
  </script>

   <!-- ### AdminLTE [START] -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ZSECURITY</title>

  <!-- Google Font: Montserrat -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.2.0/css/adminlte.min.css" integrity="sha512-IuO+tczf4J43RzbCMEFggCWW5JuX78IrCJRFFBoQEXNvGI6gkUw4OjuwMidiS4Lm9Q2lILzpJwZuMWuSEeT9UQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/6.2.1/css/tabulator.min.css" integrity="sha512-RYFH4FFdhD/FdA+OVEbFVqd5ifR+Dnx2M7eWcmkcMexlIoxNgm89ieeVyHYb8xChuYBtbrasMTlo02cLnidjtQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <!-- ### AdminLTE [END] -->

  <link rel="stylesheet" href="${staticMinUrl}/css/app.css">
  <link rel="icon" type="image/x-icon" href="${staticMinUrl}/images/jm.svg">



