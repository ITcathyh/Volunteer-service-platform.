<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/7/20
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="hyh.entity.User" %>
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
</head>
<body class="skin-blue">
<!-- Site wrapper -->
<div class="wrapper">

    <header class="main-header">
        <a href="/admin" class="logo">ITcat</a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id="menu">
                <span class="sr-only">Toggle navigation</span>
                <%=Variable.title%>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="/logout">
                            <img src="image/logo.jpg"
                                 class="user-image" alt="User Image"/>
                            <span class="hidden-xs">退出</span>
                        </a>

                    </li>
                </ul>
            </div>
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
                    <p>智慧东大
                    </p>
                    <a href="#">Online</a>
                </div>
            </div>
            <ul class="sidebar-menu">
                <li>
                    <a href="/admin">
                        <i class="fa fa-desktop"></i> <span>主页</span>
                    </a>
                </li>

                <li>
                    <a href="/adminqueryuser">
                        <i class="fa fa-user"></i> <span>查询用户</span>
                    </a>
                </li>

                <li>
                    <a href="/admineditor">
                        <i class="fa fa-envelope"></i> <span>修改邮箱</span>
                    </a>
                </li>
            </ul>
        </section>
    </aside>


    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                修改资料
                <small>Profile Edit</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div id="msgerror" class="alert alert-warning alert-dismissable" style="display:none">
                        <button type="button" class="close" id="close1" data-dismiss="alert"
                                aria-hidden="true">&times;
                        </button>
                        <h4><i class="icon fa fa-warning" id="errormsg"></i> 出错了!</h4>

                        <p id="msg-error-p"></p>
                    </div>
                    <div id="msgsuccess" class="alert alert-success alert-dismissable" style="display:none">
                        <button type="button" class="close" id="close2" data-dismiss="alert"
                                aria-hidden="true">&times;
                        </button>
                        <h4><i class="icon fa fa-info"></i> 修改成功!</h4>

                        <p id="ss-msg-success-p"></p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-key"></i>

                            <h3 class="box-title">邮箱1</h3>
                        </div>
                        <!-- /.box-header --><!-- form start -->

                        <div class="box-body">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">账号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="账号" id="address1"
                                               value="${add1}" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">授权码</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="授权码" id="pwd1"
                                               value="${pwd1}" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">主机</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="主机" id="host1"
                                               value="${host1}" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="col-md-6">

                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-key"></i>

                            <h3 class="box-title">邮箱2</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">账号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="账号" id="address2"
                                               value="${add2}" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">授权码</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="授权码" id="pwd2"
                                               value="${pwd2}" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">主机</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="主机" id="host2"
                                               value="${host2}" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="col-md-6">
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-key"></i>

                            <h3 class="box-title">邮箱3</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">账号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="账号" id="address3"
                                               value="${add3}" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">授权码</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="授权码" id="pwd3"
                                               value="${pwd3}" autocomplete="off">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">主机</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="主机" id="host3"
                                               value="${host3}" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <button id="alteremailinfo" class="btn btn-primary">修改
                            </button>
                        </div>

                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div><!-- /.content-wrapper -->
    <footer class="main-footer">
        <div align="center">

        </div>
        <div class="pull-right hidden-xs">
            Code is beautiful
        </div>
        <strong>Powered by <a href="#">ITcathyh</a> </strong>
    </footer>
</div>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/pair.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript">
    $("#alteremailinfo").click(function () {
        var add1 = $("#address1").val();
        var add2 = $("#address2").val();
        var add3 = $("#address3").val();
        var pwd1 = $("#pwd1").val();
        var pwd2 = $("#pwd2").val();
        var pwd3 = $("#pwd3").val();
        var host1 = $("#host1").val();
        var host2 = $("#host2").val();
        var host3 = $("#host3").val();

        if (add1 == "" || add2 == "" || add3 == "" ||
            pwd1 == "" || pwd2 == "" || pwd3 == "" ||
            host1 == "" || host2 == "" || host3 == "") {
            showerror("请不要留空");
        } else {
            updateemail(add1, add2, add3, pwd1, pwd2, pwd3, host1, host2, host3);
        }
    });
</script>
</body>
</html>
