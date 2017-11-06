<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/7/20
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
<div class="wrapper">

    <header class="main-header">
        <a href="#" class="logo">ITcat</a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
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
                查看资料
                <small>${activity}</small>
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
                        <h4><i class="icon fa fa-info"></i>修改成功</h4>

                        <p id="ss-msg-success-p"></p>
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

                            <h3 class="box-title">基本信息</h3>
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
                                        <input type="text" class="form-control" id="name" autocomplete="off" value="${user.name}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">学号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="studentid"
                                               autocomplete="off" value="${user.studentid}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">QQ</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="qq" autocomplete="off" value="${user.qq}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">手机号</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="phone" autocomplete="off" value="${user.phone}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">邮箱</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="email" autocomplete="off" value="${user.email}">
                                    </div>
                                </div>

                            </div>

                            <div class="box-footer">
                                <button type="button" id="updateuser" class="btn btn-primary">
                                    更新用户
                                </button>
                                <button type="button" id="deleteuser" class="btn btn-primary">
                                    删除用户
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>

                <div class="col-md-6" id="userinfo">

                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-link"></i>

                            <h3 class="box-title">其他信息</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="form-horizontal">

                                <div id="systempair">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">希望配对的学院</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" id="college">
                                                <option>工商管理学院</option>
                                                <option>软件学院</option>
                                                <option>生命科学和健康学院</option>
                                                <option>江河建筑学院</option>
                                                <option>文法学院</option>
                                                <option>马克思主义学院</option>
                                                <option>计算机科学与工程学院</option>
                                                <option>中荷生物医学与信息工程学院</option>
                                                <option>任意</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group" id="showtime1">
                                        <label class="col-sm-3 control-label">希望的时间段</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" id="time1">
                                                <option>5:00-5:30</option>
                                                <option>5:30-6:00</option>
                                                <option>6:00-6:30</option>
                                                <option>6:30-7:00</option>
                                                <option>7:00-7:30</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group" id="showtime2">
                                        <label class="col-sm-3 control-label">希望的时间段</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" id="time2">
                                                <option>第一、二节课</option>
                                                <option>第三、四节课</option>
                                                <option>第五、六节课</option>
                                                <option>第七、八节课</option>
                                                <option>第九、十节课</option>
                                                <option>第十一、十二节课</option>
                                                <option>随意</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">学院</label>

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
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">配对要求</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" id="sex">
                                                <option>同性优先</option>
                                                <option>异性优先</option>
                                                <option>随意</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
    </div>
    </section>
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

<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/pair.js"></script>
<script type="text/javascript">
    var type = ${type};
    var studentid = ${user.studentid};

    $(document).ready(function () {
        var show = ${show};

        if (show == 0) {
            $("#userinfo").hide();
        } else {
            var sex = "${userinfo.sex}";

            if(type == 1){
                $("#showtime2").hide();
                $("#time1").val("${userinfo.time}");
            } else {
                $("#showtime1").hide();
                $("#pairtype").hide();
                $("#time2").val("${userinfo.time}");
            }

            $("#college").val("${userinfo.college}");
            $("#selfcollege").val("${userinfo.selfcollege}");
            $("#sex").val(sex == "1" ? "同性优先" : sex == "0" ? "异性优先" : "随意");
        }
    });

    $(document).on("click", "#deleteuser", function (e) {
        if (window.confirm('确实删除该用户吗？')) {
            checkdelete(type, ${user.studentid});
        }
    });
</script>
</body>
</html>
