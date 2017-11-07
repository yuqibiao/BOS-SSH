<%--
  功能:用户登录
  User: yu
  Date: 2017/10/23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统平台</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="<%=basePath%>assert/plugin/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/matrix/css/matrix-login.css"/>
    <link rel="stylesheet" href="<%=basePath%>assert/plugin/jquery/gritter/jquery.gritter.css"/>
    <link href="<%=basePath%>assert/plugin/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <style type="text/css">
        input {
            font-family: "Microsoft Yahei";
        }

        .control-label {
            color: #B2DFEE;
            padding-left: 4px;
        }

        .error {
            color: #b94a48;
        }
    </style>
</head>
<body>
<div id="loginbox">
    <div class="control-group normal_text">
        <h2 style="color:#B2DFEE;font-size:28px;">信息管理系统平台</h2>
        <span style="font-size:14px;color:gray;">版权所有 &copy; yyyu网络科技有限公司 2015-2018</span>
    </div>

    <%--登录--%>
    <form id="login_form" class="form-vertical" action="<%=basePath%>user/checkUser.action" method="post">
        <div class="control-group">
            <label class="control-label">登陆账号</label>
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user" style="font-size:16px;"></i></span>
                    <input name="username" type="text" required value="admin"/>
                </div>
            </div>
        </div>
        <div class="control-group2">
            <label class="control-label">登陆密码</label>
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span>
                    <input name="password" type="password" required minlength="6"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <span class="pull-left"><a class="flip-link btn btn-info" id="to-register">注册账号&raquo;</a></span>
            <span class="pull-right">
                <input type="submit" id="checkBtn" class="btn btn-success" style="width:335px;"
                       value=" 登&nbsp;&nbsp;&nbsp;&nbsp;录"/>
            </span>
        </div>
        <div class="control-group normal_text">
            <div style="font-size:14px;color:gray;">推荐使用webkit内核浏览器，如chrome等</div>
        </div>
    </form>


    <%--注册--%>
    <form id="register-form" class="form-vertical" style="padding-top:10px;"hidden >
        <label class="control-label">登陆账号</label>
        <div class="controls">
            <div class="main_input_box">
                <span class="add-on bg_lg"><i class="icon-user" style="font-size:16px;"></i></span>
                <input type="text" name="username" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">密码</label>
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span>
                    <input  id="re_password" type="password" name="password"/>
                </div>
            </div>
        </div>
        <div class="control-group" style="padding-top:0px;">
            <label class="control-label">确认密码</label>
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span>
                    <input  id="confirm_password" type="password" name="confirm_password"  />
                </div>
            </div>
        </div>
        <div class="form-actions">
            <span class="pull-left"><a class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a></span>
            <span class="pull-right"><button type="submit" class="btn btn-info" style="width:310px;">注册</button></span>
        </div>
        <div class="control-group normal_text">
            <div style="font-size:14px;color:gray;">推荐使用webkit内核浏览器，如chrome等</div>
        </div>
    </form>

</div>

<script src="<%=basePath%>assert/plugin/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/jquery.validate.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/validate-methods.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/validate/localization/messages_zh.min.js"></script>
<script src="<%=basePath%>assert/plugin/jquery/gritter/jquery.gritter.min.js"></script>
<script src="<%=basePath%>assert/plugin/matrix/js/matrix.login.js"></script>
<script src="<%=basePath%>assert/js/text-utils.js"></script>


<script>

    $('#to-register').click(function () {
        toRegisterView();
    });

    $('#to-login').click(function () {
        toLoginView();
    });

    function toRegisterView(){
        $("#login_form").slideUp();
        $("#register-form").fadeIn();
    }

    function toLoginView(){
        $("#register-form").hide();
        $("#login_form").fadeIn();
    }

    /*表单验证*/
    $().ready(function () {
        console.log("============v表单验证==");
        $("#register-form").validate({
            errorElement: "span",
            messages: {
                username: {
                    required: " 用户名不能为空",
                },
                password: {
                    required: " 密码不能为空",
                    minlength: "密码不能少于6位",
                },
                confirm_password: {
                    required: " 密码不能为空",
                    minlength: "密码不能少于6位",
                    equalTo:"两次密码输入结果不符",
                }
            },
            rules: {
                username: {
                    required:true,
                },
                password:{
                    required:true,
                    minlength: "6",
                },
                confirm_password:{
                    required:true,
                    minlength: "6",
                    equalTo: "#re_password",
                }
            },
            submitHandler: function () {//表单验证通过后回调
                addUser();
            }
        });
    });

    /*添加用户*/
    function addUser() {
        $.get("<%=basePath%>user/addUser", $("#register-form").serialize(), function (data) {
            var code = data.code;
            var msg = data.msg;
            if (code == 200) {
                $.gritter.add({
                    title: '注册信息',
                    text: '注册成功，快去登陆吧！',
                    sticky: false,
                    time: 2000,
                    speed: 500,
                });
                toLoginView();
            } else {
                $.gritter.add({
                    title: '注册信息',
                    text: '注册失败：'+msg,
                    sticky: false,
                    time: 2000,
                    speed: 500,
                });
            }
        });
    }

    /*显示错误信息*/
    $(document).ready(function () {
        var loginTip = '${loginFailedTip}';
        if (!isEmpty(loginTip)) {
            $.gritter.add({
                title: '登录失败',
                text: loginTip,
                sticky: false,
                time: 2000,
                speed: 500,
            });
        }
    });
    /*初始化gritter*/
    $.extend($.gritter.options, {
        position: 'center',
        fade_in_speed: 'medium',
        fade_out_speed: 2000,
        time: 6000
    });


</script>
</body>

</html>
