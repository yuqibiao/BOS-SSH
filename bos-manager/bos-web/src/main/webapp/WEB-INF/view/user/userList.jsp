<%--
  功能:用户管理
  User: yu
  Date: 2017/11/2
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="<%=basePath%>assert/plugin/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/jquery/select2/select2.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-style2.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-media.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/jquery/loading/waitMe/waitMe.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/jquery/jcrop/jquery.Jcrop.css"/>

    <style>
        .controls input {
            width: 100%;
        }

        .error {
            color: #b94a48;
        }

        /*a input file 样式*/
        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

    </style>
</head>
<body>

<div id="content">
    <div id="content-header">
        <shiro:hasRole name="role:admin">
        <h1>用户信息管理</h1>
        </shiro:hasRole>
    </div>

    <%--数据表格展示--%>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <button id='btn_tree' class='btn btn-success glyphicon glyphicon-tree-conifer btn-sm'
                            data-toggle='modal' data-target='#add_user' onclick="showRole('role_select' , -1)"
                            style="margin: 10px;">
                        <i class="icon-edit"> 添加</i>
                    </button>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-hover table-striped " id="data_table" width="100%">
                            <thead>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>薪水</th>
                            <th>电话</th>
                            <th>性别</th>
                            <th>等级</th>
                            <th>头像</th>
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

    <%--添加户信息 modal--%>
    <div class="modal fade" id="add_user" style="display: none;">
        <div class="modal-dialog">
            <form class=" form-horizontal" id="add_user_form" method="get" action="">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="">修改客户信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="widget-box">
                            <div class="widget-content nopadding">
                                <div class="control-group">
                                    <label for="username" class="control-label" style="width: 100px">用户名</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="username" required name="username" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="password" class="control-label" style="width: 100px">密码</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="password" required minlength="6" id="password" name="password"
                                               class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="salary" class="control-label" style="width: 100px">薪水</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="number" id="salary" number name="salary" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="tel" class="control-label" style="width: 100px">电话</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input id="tel" type="tel" name="tel" required class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group" style="z-index: 10050 !important;">
                                    <label class="control-label" style="width: 100px">拥有角色</label>
                                    <div class="controls" style="margin-left: 160px;">
                                        <select id="role_select" name="roleId" multiple
                                                style="width: 214px; z-index: 10050 !important;">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-info">添加</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <%--修改用户信息 modal--%>
    <div class="modal fade" id="modify_user" style="display: none;">
        <div class="modal-dialog" role="document">
            <form id="modify_user_form" class="form-horizontal">
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
                                    <input type="hidden" id="edit_id" name="userId">
                                </div>
                                <div class="control-group">
                                    <label for="edit_username" class="control-label" style="width: 100px">用户名</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="text" id="edit_username" required name="username" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="edit_salary" class="control-label" style="width: 100px">薪水</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="number" id="edit_salary" number name="salary" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="edit_tel" class="control-label" style="width: 100px">电话</label>
                                    <div class="controls" style="margin-left: 160px;width: 200px;">
                                        <input type="tel" id="edit_tel" name="tel" class=" mask text">
                                    </div>
                                </div>
                                <div class="control-group" style="z-index: 10050 !important;">
                                    <label class="control-label" style="width: 100px">拥有角色</label>
                                    <div class="controls" style="margin-left: 160px;">
                                        <select id="edit_role_select" name="roleId" multiple
                                                style="width: 214px; z-index: 10050 !important;">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-info">保存修改</button>
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
            <h4 class="modal-title">删除用户</h4>
        </div>
        <div class="modal-body alert-info">
            <h5>确认要删除该用户？删除后将无法恢复！！！</h5>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-danger" onclick="deleteUser()">删除</button>
        </div>
    </div>

    <%--头像上传--%>
    <div id="upload_icon" class="modal hide fade" style="display: none;">
        <form id="modify_icon_form" >
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title">头像上传</h4>
        </div>
        <div class="modal-body alert-info">
               <a href="javascript:;" class="file">选择图片
                   <input id="pic_choice" type="file" name="file" >
               </a>
               <div style="height: 200px;width: 100%">
                   <img  width="400px" id="target" style="background-color: pink" alt="没有选择图片" />
               </div>
            <input id="x" type="hidden" name="x" >
            <input  id="y"type="hidden" name="y">
            <input id="x2"type="hidden" name="x2">
            <input id="y2"type="hidden" name="y2">
            <input id="boundx"type="hidden" name="boundx">
            <input id="boundy"type="hidden" name="boundy">
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-success" onclick="modifyIcon()">确认</button>
        </div>
        </form>
    </div>

</div>

<script src="<%=basePath%>assert/plugin/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>assert/plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/select2/select2.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/select2/select2_locale_zh-CN.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/dataTables/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/jquery.validate.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/validate-methods.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/localization/messages_zh.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/loading/waitMe/waitMe.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/loading/waitMe/waitMeCustomer.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/jcrop/jquery.Jcrop.min.js"></script>
<script src="<%=basePath%>assert/plugin/matrix/js/matrix.js"></script>
<script src="<%=basePath%>assert/plugin/matrix/js/matrix.tables.js"></script>
<script src="<%=basePath%>assert/js/pic-choice-crop.js"></script>

<script>

    /*修改头像*/
    function modifyIcon(){
        var formData = new FormData($( "#modify_icon_form")[0]);
        $.ajax({
            url: '<%=basePath%>upload/modifyUserIcon' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                var code = data.code;
                var msg = data.msg;
                console.log("==modify_icon_form=="+$("#modify_icon_form").serialize())
                if (code == 200) {
                    window.location.reload();
                } else {
                    alert("" + msg);
                }
            },
            error: function (msg) {
                alert("错误："+msg);
            }
        });
    }

    /*删除用户*/
    var delete_user_Id = -1;
    function onModalShow(userId) {
        delete_user_Id = userId;
    }
    function deleteUser() {
        if (delete_user_Id != -1) {
            $.post("<%=basePath%>user/deleteUser", {
                userId: delete_user_Id
            }, function (data) {
                var code = data.code;
                var msg = data.msg;
                if (code == 200) {
                    window.location.reload();
                } else {
                    alert("" + msg);
                }
            });
        }
    }

    /*编辑用户（填充数据）*/
    function editUser(userId) {
        $.post("<%=basePath%>user/geUserById",
            {
                userId: userId
            }, function (data) {
                var code = data.code;
                if (code == 200) {
                    var user = data.data;
                    $("#edit_id").val(user.userId);
                    $("#edit_username").val(user.username);
                    $("#edit_salary").val(user.salary);
                    $("#edit_tel").val(user.tel);
                }
            });
        showRole("edit_role_select", userId);
    }

    /*更新用户*/
    function modifyUser() {
        $.get("<%=basePath%>user/modifyUser", $("#modify_user_form").serialize(), function (data) {
            var code = data.code;
            var msg = data.msg;
            if (code == 200) {
                window.location.reload();
            } else {
                alert("" + msg);
            }
        });
    }

    /*添加用户*/
    function addUser() {
        $.get("<%=basePath%>user/addUser", $("#add_user_form").serialize(), function (data) {
            var code = data.code;
            var msg = data.msg;
            if (code == 200) {
                window.location.reload();
            } else {
                alert("" + msg);
            }
        });
    }

    /*dataTable加载数据*/
    $(document).ready(function () {
        show_waitMe($("#content") , 'win8');
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
                "url": "<%=basePath%>user/getUserByPage.action",//后台地址
                type: "POST",
                "dataSrc": function (json) {//获取数据之后处理函数，jason就是返回的数据
                    var dataSet = json.data;
                    //对数据处理过程
                    hidden_waitMe($("#content"));
                    return dataSet;//再将数据返回给datatable控件使用
                }
            },
            columns: [
                {"data": "userId", "orderable": true}, //各列对应的数据列
                {"data": "username"},
                {"data": "salary"},
                {"data": "tel", "orderable": false},
                {"data": "gender", "orderable": false},
                {"data": "remark", "orderable": false},
                {"data": "icon", "orderable": false},
                {"data": null}],

            "columnDefs": [
                {
                    "targets": [4],
                    "data": "gender",
                    "render": function (data, type, full) {
                        var gender = data;
                        if (gender == 0) {
                            return '男';
                        } else {
                            return '女'
                        }
                    }
                },{
                    "targets": [6],
                    "data":"icon",
                    "render": function (data, type, full) {

                        return"" +
                            "<div >"+
                            "<div class='thumbnail_img' style='position: relative;width='60px' " +
                            "<a> <img src='"+data+"' alt='用户头像' width='60px' '></a> " +
                            "<div class='thumbnail_actions'>" +
                            "<a class='chg_user_icon' >" +
                            "<i class='icon-edit'></i></a>" +
                            "<a class='lightbox_trigger' href='"+data+"' >" +
                            "<i class='icon-zoom-in'></i></a>" +
                            "</div>"+
                            "</div>"+
                            "</div>"
                    }
                },
                {
                    "targets": [7],
                    "data": "userId",
                    "render": function (data, type, full) {
                        var userId = data.userId;
                        return "<div > "
                            +
                            "<button class='btn btn-success btn-sm' onclick='editUser(" + userId + ")'data-toggle='modal' data-target='#modify_user'>修改</button> " +
                            <shiro:hasPermission name="user:delete">
                            "<button id='btn_delete_user' class='btn btn-danger btn-sm' onclick='onModalShow(" + userId + ")' data-toggle='modal' data-target='#confirm_delete'>删除</button> "
                            +
                            </shiro:hasPermission>
                            "</div>";
                    }
                },
            ],
            "fnInitComplete": function (oSettings, json) {
                initThumbnailClick();
            }
        });
    });

    function initThumbnailClick(){

        $(".chg_user_icon").click(function (e) {
            e.preventDefault();
            $("#upload_icon").modal();
        });

        $('.lightbox_trigger').click(function(e) {
            e.preventDefault();
            var image_href = $(this).attr("href");
            if ($('#lightbox').length > 0) {
                $('#imgbox').html('<img src="' + image_href + '" /><p><i class="icon-remove icon-white"></i></p>');
                $('#lightbox').slideDown(500);
            } else {
                var lightbox =
                    '<div id="lightbox" style="display:none;height: 100%">' +
                    '<div id="imgbox " style="height: 100%"><img src="' + image_href +'" style="margin: 0 auto;position:relative;top: 50%;transform: translateY(-50%);"/>' +
                    '<p><i class="icon-remove icon-white"></i></p>' +
                    '</div>' +
                    '</div>';
                $('body').append(lightbox);
                $('#lightbox').slideDown(500);
            }
        });
   /*     $('#lightbox').live('click', function() {
            $('#lightbox').hide(200);
        });*/
        $(document).on("click","#lightbox",function(){
            //$('#lightbox').hide();
            $('#lightbox').fadeOut(200);
        });
    }

    /*显示角色select*/
    function showRole(selectId, userId) {
        bindSelect(selectId, "<%=basePath%>userRole/getAllRoleByUserId?userId=" + userId);
    }

    /*绑定字典内容到指定的Select控件*/
    function bindSelect(selectId, url) {
        var control = $('#' + selectId);
        //绑定Ajax的内容
        $.getJSON(url, function (result) {
            control.empty();//清空下拉框
            var data = result.data;
            $.each(data, function (i, item) {
                var checked = item.checked;
                if (checked) {
                    control.append("<option selected value='" + item.roleId + "'>&nbsp;" + item.roleName + "</option>");
                } else {
                    control.append("<option  value='" + item.roleId + "'>&nbsp;" + item.roleName + "</option>");
                }
            });
            //设置Select2的处理
            control.select2({
                allowClear: true,
                escapeMarkup: function (m) {
                    return m;
                }
            });
        });
    }

    /*表单验证*/
    $().ready(function () {
        // 提交
        $("#add_user_form ").validate({
            errorElement: "span",
            messages: {
                username: {
                    required: " 用户名不能为空"
                },
                password: {
                    required: " 密码不能为空",
                    minlength: "密码不能少于6位"
                }
            },
            rules: {
                tel: {
                    isMobile: true
                }
            },
            submitHandler: function () {//表单验证通过后回调
                addUser();
            }
        });

        $("#modify_user_form").validate({
            errorElement: "span",
            messages: {
                username: {
                    required: " 用户名不能为空"
                }
            },
            rules: {
                tel: {
                    isMobile: true
                }
            },
            submitHandler: function () {//表单验证通过后回调
                modifyUser();
            }
        });

    });

</script>

</body>
</html>
