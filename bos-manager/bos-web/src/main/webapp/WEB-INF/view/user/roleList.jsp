<%--
  功能:角色管理
  User: yu
  Date: 2017/11/2
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html >
<head>
    <title>角色管理</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="<%=basePath%>assert/plugin/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/jquery/select2/select2.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-style2.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-media.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />

    <style>
        .controls input {
            width: 100%;
        }
        .error {
            color: #b94a48;
        }
    </style>
</head>
<body>

<div id="content">
    <div id="content-header">
        <h1>用户信息管理</h1>
    </div>

    <%--数据表格展示--%>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <button id='btn_tree' class='btn btn-success glyphicon glyphicon-tree-conifer btn-sm'
                            data-toggle='modal' data-target='#add_user'
                            style="margin: 10px;">
                        <i class="icon-edit"> 添加</i>
                    </button>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-hover table-striped " id="data_table" width="100%">
                            <thead>
                            <th>角色ID</th>
                            <th>角色名</th>
                            <th>描述</th>
                            <th>序列编号</th>
                            <th>是否可用</th>
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

    <%--添加角色信息 modal--%>
    <div class="modal fade" id="add_user" style="display: none;">
        <div class="modal-dialog" role="document">
            <form  id="add_role_form" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" >添加角色</h4>
                </div>
                <div class="modal-body">
                    <div class="widget-box">
                        <div class="widget-content nopadding">
                                <div class="control-group">
                                    <input type="hidden" id="roleId" name="roleId">
                                </div>
                                <div class="control-group">
                                    <label for="roleName" class="control-label" style="width: 100px">角色名</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="roleName" required name="roleName" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="description" class="control-label" style="width: 100px">角色描述</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="description" required name="description" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="code" class="control-label" style="width: 100px">角色序列号</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="code" name="code" class=" mask text">
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-info" >添加</button>
                </div>
            </div>
            </form>
        </div>
    </div>

    <%--修改角色信息 modal--%>
    <div class="modal fade" id="modify_user" style="display: none;">
        <div class="modal-dialog" role="document">
            <form id="modify_role_form" class="form-horizontal">
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
                                <div class="control-group">
                                    <input type="hidden" id="edit_roleId" name="roleId">
                                </div>
                                <div class="control-group">
                                    <label for="edit_roleName" class="control-label" style="width: 100px">角色名</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="edit_roleName" required name="roleName" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="edit_description" class="control-label" style="width: 100px">角色描述</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="edit_description" required name="description" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="edit_code" class="control-label" style="width: 100px">角色序列号</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="edit_code" name="code" class=" mask text">
                                    </div>
                                </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-info" >保存修改</button>
                </div>
            </div>
            </form>
        </div>
    </div>

    <%--确认删除操作modal--%>
    <div id="confirm_delete" class="modal hide fade" style="display: none;">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title">删除角色</h4>
        </div>
        <div class="modal-body alert-info">
            <h5>确认要删除该角色？删除后将无法恢复！！！</h5>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-danger" onclick="deleteRole()">删除</button>
        </div>
    </div>

    <%--权限分配 modal--%>
    <div class="modal fade" id="dtreeModal" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <form role="form" action="" method="post">
                    <div class="modal-header">
                        <button data-dismiss="modal" class="close" type="button">×</button>
                        <h3>权限分配</h3>
                    </div>
                    <div class="modal-body">
                        <div>
                            <ul id="permissionTree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="btn_closeTree" data-dismiss="modal" class="btn btn-default" type="button">
                            关闭
                        </button>
                        <button id="btn_inputTree" class="btn btn-info" type="button" onclick="savePermission()">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<script src="<%=basePath%>assert/plugin/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>assert/plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/select2/select2.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/select2/select2_locale_zh-CN.js"></script>
<script src="<%=basePath%>assert/plugin/matrix/js/matrix.js"></script>
<script src="<%=basePath%>assert/plugin/matrix/js/matrix.tables.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/dataTables/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/jquery.validate.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/validate-methods.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/localization/messages_zh.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/ztree/js/jquery.ztree.core.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/ztree/js/jquery.ztree.excheck.js"></script>

<script>

    /*保存角色对应的权限*/
    function savePermission() {
        if(currentRoleId==-1){
            return;
        }
        var perIdList = new Array();
        var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
        var nodes = treeObj.getCheckedNodes(true);
        for(var i=0 ; i<nodes.length ; i++){
            var node = nodes[i];
            var perId = node.id;
            perIdList.push(perId);
        }
        var perIdList = JSON.stringify(perIdList);
        $.post("<%=basePath%>rolePermission/addPermission2Role" , {
            roleId:currentRoleId,
            perIdListJson:perIdList
        },function (data) {
            var code = data.code;
            var msg = data.msg;
            if(code==200){
                $("#dtreeModal").modal('hide');
            }else{
                alert("异常："+msg);
            }
        });
    }

    /*修改用户（填充数据）*/
    function editRole(roleId){
        $.post("<%=basePath%>role/getRoleById",{
            roleId:roleId
        },function (data) {
            var code = data.code;
            var msg = data.msg;
            if(code==200){
                var role = data.data;
                $("#edit_roleId").val(role.roleId);
                $("#edit_roleName").val(role.roleName);
                $("#edit_description").val(role.description);
                $("#edit_code").val(role.code);
            }else{
                alert(""+msg);
            }
        })
    }

    /*修改用户*/
    function modifyRole(){
        $.post("<%=basePath%>role/modifyRole" , $("#modify_role_form").serialize(), function (data) {
            var code = data.code;
            var msg = data.msg;
            if(code==200){
                window.location.reload();
            }else{
                alert(""+msg);
            }
        });
    }

    /*删除角色*/
    var delete_role_id=-1;
    function onModalShow(roleId){
        delete_role_id = roleId;
    }
    function deleteRole() {
        if (delete_role_id!=-1){
            $.post("<%=basePath%>role/deleteRole" ,{
                roleId:delete_role_id
            },function (data) {
                var code = data.code;
                var msg = data.msg;
                if(code==200){
                    window.location.reload();
                }else{
                    alert(""+msg);
                }
            });
        }
    }

    /*添加角色*/
    function addRole() {
        $.post("<%=basePath%>role/addRole" , $("#add_role_form").serialize(), function (data) {
            var code = data.code;
            var msg = data.msg;
            if(code==200){
                window.location.reload();
            }else{
                alert(""+msg);
            }
        });
    }

    /*dataTable加载数据*/
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
                "url": "<%=basePath%>role/getRoleByPage.action",//后台地址
                type: "POST",
                "dataSrc": function (json) {//获取数据之后处理函数，jason就是返回的数据
                    var dataSet = json.data;
                    //对数据处理过程
                    return dataSet;//再将数据返回给datatable控件使用
                }
            },
            columns: [
                {"data": "roleId", "orderable": true}, //各列对应的数据列
                {"data": "roleName"},
                {"data": "description"},
                {"data": "code", "orderable": false},
                {"data": "available", "orderable": false},
                {"data": null}],

            "columnDefs": [
                {
                    "targets": [4],
                    "data": "available",
                    "render": function (data, type, full) {
                        var available = data;
                        if (available==0){
                            return '不可用';
                        }else{
                            return '可用'
                        }
                    }
                },
                {
                    "targets": [5],
                    "data": "roleId",
                    "render": function (data, type, full) {
                        var roleId = data.roleId;
                        return "<div > "
                            +
                            "<button class='btn btn-success btn-sm' onclick='editRole("+roleId+")' data-toggle='modal' data-target='#modify_user'>修改</button> " +
                            "<button id='btn_delete_role' class='btn btn-danger btn-sm'  onclick='onModalShow("+roleId+")'  data-toggle='modal' data-target='#confirm_delete'>删除</button> " +
                            "<button id='btn_tree' onclick='initTree("+roleId+")' class='treeBtn btn btn-success  btn-sm'data-toggle='modal' data-target='#dtreeModal'> 权限分配 </button>"
                            +
                            "</div>";
                    }
                }
            ],
            "fnInitComplete": function (oSettings, json) {

            }
        });

    });

    /*初始化树形菜单*/
    var currentRoleId = -1;
    function initTree(roleId) {

        currentRoleId = roleId

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

        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("permissionTree"),
                type = {"Y": "ps", "N": "ps"};
            zTree.setting.check.chkboxType = type;
        }

        $.ajax({
            url: "<%=basePath%>user/geAllPermissionsByUserId.action",
            data: "roleId=" + currentRoleId,
            type: "POST",
            success: function (result) {
                if (result.code == 200) {
                    var zNodes = result.data;
                    $.fn.zTree.init($("#permissionTree"), setting, zNodes);
                    setCheck();
                } else if (result.code == 250) {
                    $("#btn_closeTree").click();
                }
            }
        });
    }

    /*表单验证*/
    $(document).ready(function () {
        /*添加*/
        $("#add_role_form").validate({
            errorElement: "span",
            messages: {
                roleName: {
                    required: " 角色名不能为空"
                },
                description: {
                    required: " 角色描述不能为空"
                }
            },
            submitHandler: function () {//表单验证通过后回调
                addRole();
            }
        });
        /*修改*/
        $("#modify_role_form ").validate({
            errorElement: "span",
            messages: {
                roleName: {
                    required: " 角色名不能为空"
                },
                description: {
                    required: " 角色描述不能为空"
                }
            },
            submitHandler: function () {//表单验证通过后回调
                modifyRole();
            }
        });

    });


</script>

</body>
</html>

