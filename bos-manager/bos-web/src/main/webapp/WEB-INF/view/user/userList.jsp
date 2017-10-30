<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <link rel="stylesheet" href="<%=basePath%>assert/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/css/uniform.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/css/select2.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/css/matrix-style2.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/css/matrix-media.css"/>
    <link href="<%=basePath%>assert/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath%>assert/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
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
                    <button type="button" class="btn btn-primary" >保存修改</button>
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
            <h5>确认要删除该用户？删除后将无法恢复！！！</h5>
        </div>
        <div class="modal-footer">
            <a data-dismiss="modal" class="btn btn-primary" href="#">Confirm</a>
            <a data-dismiss="modal" class="btn" href="#">Cancel</a>
        </div>
    </div>

    <%--权限分配 modal--%>
    <div class="modal fade" id="dtreeModal" tabindex="-1" role="dialog" aria-labelledby="preModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <form role="form" action="" method="post">
                    <div class="modal-header">
                        <button data-dismiss="modal" class="close" type="button">×</button>
                        <h3>权限分配</h3>
                    </div>
                    <div class="modal-body">
                        <div>
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="btn_closeTree" data-dismiss="modal" class="btn btn-default" type="button">
                            关闭
                        </button>
                        <button id="btn_inputTree" class="btn btn-primary" type="button">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<script src="<%=basePath%>assert/js/jquery.min.js"></script>
<script src="<%=basePath%>assert/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>assert/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assert/js/jquery.uniform.js"></script>
<script src="<%=basePath%>assert/js/select2.min.js"></script>
<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<%--<script src="<%=basePath%>assert//js/jquery.dataTables.min.js"></script>--%>
<script src="<%=basePath%>assert/js/matrix.js"></script>
<script src="<%=basePath%>assert/js/matrix.tables.js"></script>
<script src="<%=basePath%>assert/ztree/js/jquery.ztree.core.js"></script>
<script src="<%=basePath%>assert/ztree/js/jquery.ztree.excheck.js"></script>
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
                "url": "<%=basePath%>userManager/getUserByPage.action",//后台地址
                "dataSrc": function (json) {//获取数据之后处理函数，jason就是返回的数据
                    var dataSet = json.data;
                    //对数据处理过程
                    return dataSet;//再将数据返回给datatable控件使用
                }
            },
            columns: [
                {"data": "id", "orderable": true}, //各列对应的数据列
                {"data": "username"},
                {"data": "salary"},
                {"data": "telephone", "orderable": false},
                {"data": "gender", "orderable": false},
                {"data": "remark", "orderable": false},
                {"data": null}],

            "columnDefs": [
                {
                    "targets": [6],
                    "data": "username",
                    "render": function (data, type, full) {
                        var userId = data.id;
                        return "<div > "
                            <shiro:hasRole name="admin">
                            +
                            "<a class='tip' data-toggle='modal' href='#modify_user' onclick='edit(" + userId + ")' title='修改' ><i class='icon-pencil'></i></a> " +
                            "<a class='tip' data-toggle='modal'  href='#dtreeModal' title='删除'><i class='icon-remove'></i></a> " +
                            "<button id='btn_tree' class='treeBtn btn btn-success glyphicon glyphicon-tree-conifer btn-sm'data-toggle='modal' data-target='#dtreeModal'> 树形分配 </button>"
                            </shiro:hasRole>
                            +
                            "</div>";
                    }
                }
            ],
            "fnInitComplete": function (oSettings, json) {
                var setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };

                $("button").click(function(){
                    console.log("====button=====");
                });

                $("#btn_tree").click(function () {
                    var userId = 1;
                    console.log("=========" + userId);
                    $.ajax({
                        url: "<%=basePath%>userManager/getUserPermissions.action",
                        data: "userId=" + userId,
                        type: "GET",
                        success: function (result) {
                            if (result.code == 200) {
                                var zNodes = result.data;
                                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                                setCheck();
                            } else if (result.code == 250) {
                                $("#btn_closeTree").click();
                            }
                        }
                    });
                });

                function setCheck() {
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                        type = {"Y": "ps", "N": "ps"};
                    zTree.setting.check.chkboxType = type;
                }
            }
        });
    });

    function edit(userId) {
        console.log("------edit-------");
    }

</script>

</body>
</html>
