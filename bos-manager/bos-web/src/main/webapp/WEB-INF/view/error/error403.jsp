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
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/matrix-style2.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/matrix-media.css" />
<link href="<%=basePath%>static/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<body>

<div id="content">
  <div id="content-header">
      <h1>403错误页面</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
            <h5>Error 403</h5>
          </div>
          <div class="widget-content">
            <div class="error_ex">
              <h1>403</h1>
              <h3>Opps, You're lost.</h3>
              <p>Access to this page is forbidden</p>
              <a class="btn btn-warning btn-big"  href="index.html">Back to Home</a> </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="<%=basePath%>static/js/jquery.min.js"></script>
<script src="<%=basePath%>static/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/js/maruti.html"></script>
</body>
</html>
