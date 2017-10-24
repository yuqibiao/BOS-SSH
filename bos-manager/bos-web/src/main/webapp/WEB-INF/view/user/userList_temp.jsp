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
                            <th>等级</th>
                            <th>操作</th>
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
<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/matrix.js"></script>
<script src="<%=basePath%>js/matrix.tables.js"></script>
<script>
    $(document).ready(function() {
        console.log("------------before-----------");

        $('#data_table').DataTable(//创建一个Datatable
            {
                ajax : {//通过ajax访问后台获取数据
                    "url": "userManager/getUserByPage.action",//后台地址
                    "dataSrc": function (json) {//获取数据之后处理函数，jason就是返回的数据
                        var dataSet = new Array();
                        dataSet=json.data;
                        console.log("------------dataSet-----------"+dataSet);
                        //对数据处理过程
                        return dataSet;//再将数据返回给datatable控件使用
                    }
                },
                serverSide: true,//如果是服务器方式，必须要设置为true
                processing: true,//设置为true,就会有表格加载时的提示
                columns : [
                    {"data" : "id"}, //各列对应的数据列
                    {"data" : "username"},
                    {"data" : "salary"},
                    {"data" : "telephone"},
                    {"data" : "gender"},
                    {"data" : "remark"},
                    {"data" : null} ],
                columnDefs : [ {//列渲染，可以添加一些操作等
                    targets : 6,//表示是第7列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    render : function(data, type, row) {//渲染函数
                        var html = '&nbsp;<button type="button" class="btn btn-info btn-sm" ">修改</button><br><br>&nbsp;<button type="button" class="btn btn-danger btn-sm" ">删除</button>';//这里加了两个button，一个修改，一个删除
                        return html;//将改html语句返回，就能在表格的第8列显示出来了
                    }
                },{
                    orderable:false,//禁用排序
                    targets:[1,2,3,5,6]   //指定的列
                }
                ],
                "language": {//国际化
                    "processing":"<p style=\"font-size: 20px;color: #F79709;\">正在玩命加载中。。。。请稍后！</p>",//这里设置就是在加载时给用户的提示
                    "lengthMenu": "_MENU_ 条记录每页",
                    "zeroRecords": "没有找到记录",
                    "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                    "infoEmpty": "无记录",
                    "infoFiltered": "(从 _MAX_ 条记录过滤)",
                    "paginate": {
                        "previous": "上一页",
                        "next": "下一页"
                    }
                },
                "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +"t" +"<'row'<'col-xs-6'i><'col-xs-6'p>>",//给表格改样式
                initComplete : function() {//表格完成时运行函数
                    $("#mytool").append('<button type="button" class="btn btn-warning btn-sm" id="importfilebutton" onclick="jumpimportfilepage();">添加</button>');//这里在表格的上面加了一个添加标签
                }
            });

        console.log("-----------after------------");
    });
</script>

</body>
</html>
