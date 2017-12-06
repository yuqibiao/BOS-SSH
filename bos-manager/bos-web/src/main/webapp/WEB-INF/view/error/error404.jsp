<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>

<html >
<head>
<title>Matrix Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-style2.css"/>
</head>
<body>

<div id="content">
  <div id="content-header">
      <h1>404错误页面</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
            <h5>Error 404</h5>
          </div>
          <div class="widget-content">
            <div class="error_ex">
              <h1>404</h1>
              <h3>Opps, You're lost.</h3>
              <p>We can not find the page you're looking for.</p>
              <a class="btn btn-warning btn-big"  href="index.html">Back to Home</a> </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
