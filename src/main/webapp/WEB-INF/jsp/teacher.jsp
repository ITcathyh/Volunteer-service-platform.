<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/7/20
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="hyh.entity.Teacher" %>
<%@ page import="hyh.global.Variable" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>智慧东大</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/ionicons.min.css" rel="stylesheet">
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="skin-blue">
<!-- Site wrapper -->
<div class="wrapper">

    <header class="main-header">
        <a class="logo"><%=Variable.logo%></a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">

            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id="menu">
                <span class="sr-only">Toggle navigation</span>
                <%=Variable.title%>
            </a>
        </nav>
    </header>
    <aside class="main-sidebar">
        <section class="sidebar">
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="image/logo.jpg" class="img-circle"
                         alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <p>菜单</p>
                    <a href="#">智慧东大</a>
                </div>
            </div>
            <ul class="sidebar-menu">
                <ul class="sidebar-menu">
                    <li>
                        <a href="/assist">
                            <i class="fa fa-book"></i> <span>学霸养成：相约自习</span>
                        </a>
                    </li>


                    <li>
                        <a href="/authenticate">
                            <i class="fa fa-cloud-upload"></i> <span>学霸养成：辅学认证</span>
                        </a>
                    </li>
                </ul>
            </ul>
        </section>
    </aside>


    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                填写资料
                <small>成为辅学者</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div id="msgerror" class="alert alert-warning alert-dismissable" style="display:none">
                        <h4><i class="icon fa fa-warning" id="errormsg"></i> 出错了!</h4>

                        <p id="msg-error-p"></p>
                    </div>
                    <div id="msgsuccess" class="alert alert-success alert-dismissable" style="display:none">
                        <h4><i class="icon fa fa-info"></i>提交成功</h4>

                        <p id="ss-msg-success-p">我们将会通过邮箱告知您的配对结果</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <!-- left column -->
                <div class="col-md-6">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-key"></i>

                            <h3 class="box-title">辅学人信息</h3>
                        </div>
                        <!-- /.box-header --><!-- form start -->

                        <div class="box-body">
                            <div class="form-horizontal">

                                <div id="msg-success" class="alert alert-info alert-dismissable" style="display:none">
                                    <button type="button" class="close" data-dismiss="alert"
                                            aria-hidden="true">&times;
                                    </button>
                                    <h4><i class="icon fa fa-info"></i> Ok!</h4>

                                    <p id="msg-success-p"></p>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">姓名</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="teachername" autocomplete="off"
                                               value="${teacher.name}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">手机号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="teacherphone" autocomplete="off"
                                               value="${teacher.phone}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">邮箱</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="teacheremail" autocomplete="off"
                                               value="${teacher.email}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">擅长领域</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="teacherskill" autocomplete="off"
                                               value="${teacher.skill}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">学院</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="teachercollege" autocomplete="off"
                                               value="${teacher.college}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>

                <div class="col-md-6">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-key"></i>

                            <h3 class="box-title">基本信息</h3>
                        </div>
                        <!-- /.box-header --><!-- form start -->

                        <div class="box-body">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的姓名</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="name" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的学号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="studentid"
                                               autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的QQ</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="qq" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的手机号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="phone" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的邮箱</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="email" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的学院？</label>

                                    <div class="col-sm-9">
                                        <select class="form-control" id="selfcollege">
                                            <option>工商管理学院</option>
                                            <option>软件学院</option>
                                            <option>生命科学和健康学院</option>
                                            <option>江河建筑学院</option>
                                            <option>文法学院</option>
                                            <option>马克思主义学院</option>
                                            <option>计算机科学与工程学院</option>
                                            <option>中荷生物医学与信息工程学院</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="box-footer">
                            <button id="submitaddstudent" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">申请辅学</span>
                            </button>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content -->
    <footer class="main-footer">
        <div align="center">

        </div>
        <div class="pull-right hidden-xs">
            Code is beautiful
        </div>
        <strong>Powered by <a href="#">ITcathyh</a> </strong>
    </footer>
</div><!-- /.content-wrapper -->
<input type="hidden" name="CSRFToken" id = "token" value="${csrftoken}" />
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/pair.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript">
    var headers = {};
    headers['requesttoken'] = $("#token").val();

    $(document).on("blur", "#teachername", function (e) {
        $("#teachername").val("${teacher.name}");
    });

    $(document).on("blur", "#teacherstudentid", function (e) {
        $("#teacherstudentid").val(${teacher.studentid});
    });

    $(document).on("blur", "#teacheremail", function (e) {
        $("#teacheremail").val("${teacher.email}");
    });

    $(document).on("blur", "#teacherskill", function (e) {
        $("#teacherskill").val("${teacher.skill}");
    });

    $(document).on("blur", "#teachercollege", function (e) {
        $("#teachercollege").val("${teacher.college}");
    });

    $(document).on("blur", "#teacherphone", function (e) {
        $("#teacherphone").val("${teacher.phone}");
    });
</script>
</body>
</html>
