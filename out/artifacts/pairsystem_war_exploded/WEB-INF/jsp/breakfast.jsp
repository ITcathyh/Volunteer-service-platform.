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
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link rel="bookmark"  type="image/x-icon"  href="image/logo.ico"/>
    <link rel="shortcut icon" href="image/logo.ico">
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
                    <li>
                        <a>
                            <i class="fa fa-coffee"></i> <span>学霸养成：早餐叫醒</span>
                        </a>
                    </li>
                </ul>
        </section>
    </aside>


    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                填写资料
                <small>学霸养成：早餐叫醒活动</small>
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
                                    <label class="col-sm-3 control-label">配对类型</label>

                                    <div class="col-sm-9">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="type" id="optionsRadios1"
                                                       value="systempair" checked> 系统匹配
                                            </label>
                                            <label>
                                                <input type="radio" name="type" id="optionsRadios2"
                                                       value="pairself">
                                                自行匹配
                                            </label>
                                        </div>
                                    </div>
                                </div>

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
                                    <label class="col-sm-3 control-label">您的性别</label>

                                    <div class="col-sm-9">
                                        <select class="form-control" id="selfsex">
                                            <option>男</option>
                                            <option>女</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>

                <div class="col-md-6">

                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-link"></i>

                            <h3 class="box-title">其他信息</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="form-horizontal">
                                <div id="pairself">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">如匹配失败，是否调剂？</label>

                                        <div class="col-sm-9">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="fail"
                                                           value="agree" checked>接受
                                                </label>
                                                <label>
                                                    <input type="radio" name="fail"
                                                           value="disagree">不接受
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">对方的姓名</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="anothername" autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">对方的学号</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="anotherstudentid"
                                                   autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">对方的QQ</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="anotherqq" autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">对方的手机号</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="anotherphone" autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">对方的邮箱</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="anotheremail"
                                                   autocomplete="off">
                                        </div>
                                    </div>
                                </div>

                                <div id="systempair">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">希望配对的学院？</label>

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
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">您想哪个时间段被叫醒？</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" id="time">
                                                <option>5:00-5:30</option>
                                                <option>5:30-6:00</option>
                                                <option>6:00-6:30</option>
                                                <option>6:30-7:00</option>
                                                <option>7:00-7:30</option>
                                            </select>
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
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">您想与谁配对？</label>

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

                        <div class="box-footer">
                            <button id="submitpair" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">提交信息</span>
                            </button>
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
        <strong>Powered by <a href="<%=Variable.myweb%>">ITcathyh</a> </strong>
    </footer>
</div>
<input type="hidden" name="CSRFToken" id = "token" value="${csrftoken}" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/pair.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript">
    var breurl = "${breurl}";
    var la = Ladda.create(document.querySelector("#submitpair"));
    var headers = {};
    headers['requesttoken'] = $("#token").val();

    $(document).ready(function () {
        $("#pairself").hide();
    });

    $(document).on("click", ":radio", function (e) {
        if ($("input[type='radio'][name ='type']:checked").val() == "systempair") {
            $("#pairself").hide();
            $("#systempair").show();
            type = 1
        } else {
            $("#pairself").show();

            if ($("input[type='radio'][name ='fail']:checked").val() == "agree"){
                $("#systempair").show();
                type = 3;
            } else {
                type = 2;
                $("#systempair").hide();
            }
        }
    });
</script>
</body>
</html>
