<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-style2.css"/>
</head>
<body>

<div id="content">
    <div id="content-header">
        <h1>405错误页面</h1>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-info-sign"></i> </span>
                        <h5>Error 405</h5>
                    </div>
                    <div class="widget-content">
                        <div class="error_ex">
                            <h1>405</h1>
                            <h3>Something is wrong here. Method not allowed!</h3>
                            <p>Access to this page is forbidden</p>
                            <a class="btn btn-warning btn-big" href="index.html">Back to Home</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=basePath%>assert/js/jquery.min.js"></script>
<script src="<%=basePath%>assert/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>assert/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assert/js/maruti.html"></script>
</body>
</html>
