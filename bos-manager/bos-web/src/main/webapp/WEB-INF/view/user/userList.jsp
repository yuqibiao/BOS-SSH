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

    <style>
        .newClass {
            background: #bbffaa;
        }

        .page_magrin {
            margin: 50px;
        }
    </style>
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

    <%--pop--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true"
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
                    <form class="form-horizontal" id="edit_customer_form">
                        <input type="hidden" id="edit_cust_id" name="customer.custId"/>
                        <div class="form-group">
                            <label for="edit_customerName" class="col-sm-2 control-label">客户名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_customerName" placeholder="客户名称"
                                       name="customer.custName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_customerFrom" style="float:left;padding:7px 15px 0 27px;">客户来源</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="edit_customerFrom" placeholder="客户来源" name="customer.custSource">
                                    <option value="">--请选择--</option>
                                    <c:forEach items="${fromType}" var="item">
                                        <option value="${item.dictId}"<c:if
                                                test="${item.dictId == custSource}"> selected</c:if>>${item.dictItemName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_custIndustry" style="float:left;padding:7px 15px 0 27px;">所属行业</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="edit_custIndustry" name="customer.custIndustry">
                                    <option value="">--请选择--</option>
                                    <c:forEach items="${industryType}" var="item">
                                        <option value="${item.dictId}"<c:if
                                                test="${item.dictId == custIndustry}"> selected</c:if>>${item.dictItemName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_custLevel" style="float:left;padding:7px 15px 0 27px;">客户级别</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="edit_custLevel" name="customer.custLevel">
                                    <option value="">--请选择--</option>
                                    <c:forEach items="${levelType}" var="item">
                                        <option value="${item.dictId}"<c:if
                                                test="${item.dictId == custLevel}"> selected</c:if>>${item.dictItemName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_linkMan" class="col-sm-2 control-label">联系人</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_linkMan" placeholder="联系人"
                                       name="customer.custLinkman">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_phone" class="col-sm-2 control-label">固定电话</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_phone" placeholder="固定电话"
                                       name="customer.custPhone">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_mobile" class="col-sm-2 control-label">移动电话</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_mobile" placeholder="移动电话"
                                       name="customer.custMobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_zipcode" class="col-sm-2 control-label">邮政编码</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_zipcode" placeholder="邮政编码"
                                       name="customer.custZipcode">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit_address" class="col-sm-2 control-label">联系地址</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_address" placeholder="联系地址"
                                       name="customer.custAddress">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateCustomer()">保存修改</button>
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
                {"data": "id"}, //各列对应的数据列
                {"data": "username"},
                {"data": "salary"},
                {"data": "telephone"},
                {"data": "gender"},
                {"data": "remark"},
                {"data": null}],
            "columnDefs": [
                {
                    "targets": [6],
                    "data": "username",
                    "render": function (data, type, full) {
                        var userId = data.id;
                        console.log("===userId="+userId)
                        return "<div > " +
                            "<a class='tip' data-toggle='modal' href='#myModal' onclick='edit("+userId+")' title='修改' ><i class='icon-pencil'></i></a> " +
                            "<a class='tip' href='/delete?id=" + data.id + "' title='删除'><i class='icon-remove'></i></a> " +
                            "</div>";
                    }
                }
            ],
            "fnInitComplete": function (oSettings, json) {

            }
        });
    });

    function edit(userId){
        console.log("------edit-------");
        $(".myModal_tip").text("用户的id为："+userId);
    }

</script>

</body>
</html>
