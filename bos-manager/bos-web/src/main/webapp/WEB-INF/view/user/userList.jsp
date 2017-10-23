<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/uniform.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/select2.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/matrix-style2.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/matrix-media.css"/>
    <link href="<%=basePath%>font-awesome/css/font-awesome.css" rel="stylesheet"/>
</head>
<body>

<div id="content">
    <div id="content-header">
        <h1>表格</h1>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>Data table</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped" id="data_table">
                            <thead>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>角色</th>
                            <th>薪水</th>
                            <th>电话</th>
                            <th>生日</th>
                            <th>性别</th>
                            <th>等级</th>
                            <th>身份</th>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/jquery.uniform.js"></script>
<script src="<%=basePath%>js/select2.min.js"></script>
<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/matrix.js"></script>
<script src="<%=basePath%>js/matrix.tables.js"></script>
<script>
    $(document).ready(function() {
        console.log("------------before-----------");
        $('#data_table').dataTable({
            "bPaginate": true,
            "bLengthChange": false,
            "bSort": true,
            "bInfo": true,
            "bAutoWidth": false,
            "bFilter": true,
            "ajax": '<%=basePath%>userManager/getUserByPage.action',
            "columns": [
                {"data": "id"},
                {"data": "username"},
                {"data": "roleNames"},
                {"data": "salary"},
                {"data": "telephone"},
                {"data": "birthdayString"},
                {"data": "gender"},
                {"data": "remark"},
                {"data": "station"}
            ],
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "对不起，查询不到任何相关数据",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoEmtpy": "找不到相关数据",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
                "sProcessing": "正在加载中...",
                "sSearch": "搜索",
                "sUrl": "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascrip{过滤}t/datatable/dtCH.txt
                "oPaginate": {
                    "sFirst": "第一页",
                    "sPrevious": " 上一页 ",
                    "sNext": " 下一页 ",
                    "sLast": " 最后一页 "
                }
            }
        });
        console.log("-----------after------------");
    });
</script>

</body>
</html>
