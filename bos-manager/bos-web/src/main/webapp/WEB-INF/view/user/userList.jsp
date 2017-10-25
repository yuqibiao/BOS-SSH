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
    <link rel="stylesheet" href="<%=basePath%>css/uniform.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/select2.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/matrix-style2.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/matrix-media.css"/>
    <link href="<%=basePath%>font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <style>
        .controls input {
            width: 100%;
        }
    </style>
</head>
<body>

<div id="content">
    <div id="content-header">
        <h1>用户信息管理</h1>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>用户信息</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-hover table-striped " id="data_table" width="100%">
                            <thead>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>薪水</th>
                            <th>电话</th>
                            <th>角色</th>
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

    <%--修改用户信息 modal--%>
    <div class="modal fade" id="modify_user" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
         aria-hidden="true"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改客户信息</h4>
                </div>
                <div class="modal-body">
                    <div class="widget-box">
                        <div class="widget-content nopadding">
                            <form action="#" class="form-horizontal">
                                <div class="control-group">
                                    <label for="username" class="control-label" style="width: 100px">用户名</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="username" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="salary" class="control-label" style="width: 100px">薪水</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="salary" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="tel" class="control-label" style="width: 100px">电话</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="tel" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="role" class="control-label" style="width: 100px">角色</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="role" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="role" class="control-label" style="width: 100px">用户等级</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="" class=" mask text">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateCustomer()">保存修改</button>
                </div>
            </div>
        </div>
    </div>

    <%--确认删除操作modal--%>
    <div id="confirm_delete" class="modal hide fade">
        <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button">×</button>
            <h3>删除操作</h3>
        </div>
        <div class="modal-body alert-info">
            <h5 >确认要删除该用户？删除后将无法恢复！！！</h5>
        </div>
        <div class="modal-footer">
            <a data-dismiss="modal" class="btn btn-primary" href="#">Confirm</a>
            <a data-dismiss="modal" class="btn" href="#">Cancel</a>
        </div>
    </div>

</div>

<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/jquery.uniform.js"></script>
<script src="<%=basePath%>js/select2.min.js"></script>
<%--<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>--%>
<script src="<%=basePath%>/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/matrix.js"></script>
<script src="<%=basePath%>js/matrix.tables.js"></script>
<script>
    $(document).ready(function () {
        $("#data_table").dataTable({
            "aLengthMenu": [[10, 15, 20], [10, 15, 20]],//搜索栏显示
            "order": [[2, "desc"]],//第2列的数据倒序排序 此条会通过参数传给服务器
            "processing": true,//代码没加载完成时 会显示加载中…
            "searching": true,//关闭datatables自带搜索功能（没什么用）
            "bLengthChange": true, //改变每页显示数据数量
            "bFilter": true, //过滤功能
            "bSort": true, //排序功能
            "serverSide": true,//服务器端处理数据
            "bPaginate": true,// 分页按钮
            "bStateSave": true,//状态保存
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "sDom": '<""l>t<"F"fp>',
            //"sAjaxSource": "/orderCenter/dataTable",
            //"fnServerData":retrieveData, //与后台交互获取数据的处理函数
            "language": {//国际化
                "processing": "正在玩命加载中。。。。请稍后！",//这里设置就是在加载时给用户的提示
                "lengthMenu": "_MENU_ 条/页",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                "search": "搜索　",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "paginate": {
                    "sFirst": "首页",
                    "sPrevious": " 上一页 ",
                    "sNext": " 下一页 ",
                    "sLast": " 尾页 "
                }
            },
            ajax: {//通过ajax访问后台获取数据
                "url": "userManager/getUserByPage.action",//后台地址
                "dataSrc": function (json) {//获取数据之后处理函数，jason就是返回的数据
                    var dataSet = json.data;
                    console.log("------------dataSet-----------" + dataSet);
                    //对数据处理过程
                    return dataSet;//再将数据返回给datatable控件使用
                }
            },
            columns: [
                {"data": "id" , "orderable": true}, //各列对应的数据列
                {"data": "username"},
                {"data": "salary"},
                {"data": "telephone" ,"orderable": false},
                {"data": "gender" , "orderable": false},
                {"data": "remark" , "orderable": false},
                {"data": null}],
            "columnDefs": [
                {
                    "targets": [6],
                    "data": "username",
                    "render": function (data, type, full) {
                        var userId = data.id;
                        console.log("===userId=" + userId)
                        return "<div > " +
                            "<a class='tip' data-toggle='modal' href='#modify_user' onclick='edit(" + userId + ")' title='修改' ><i class='icon-pencil'></i></a> " +
                            "<a class='tip' data-toggle='modal'  href='#confirm_delete' title='删除'><i class='icon-remove'></i></a> " +
                            "</div>";
                    }
                }
            ],
            "fnInitComplete": function (oSettings, json) {

            }
        });
    });

    function edit(userId) {
        console.log("------edit-------");
    }

</script>

</body>
</html>
