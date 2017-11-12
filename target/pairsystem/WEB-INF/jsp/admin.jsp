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
    <title>后台管理</title>
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
        <section class="content-header">
            <h1>
                后台管理
                <small>System management daemon</small>
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-sm-12">
                    <div id="msg-info" class="alert alert-info alert-dismissable">
                        <h4>
                            <i class="icon fa fa-info"></i>
                            说明：
                            1.早餐问卷链接会在每日0点进行自动更换
                            2.管理员支持查询、修改和删除用户
                        </h4>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div id="msgerror" class="alert alert-warning alert-dismissable" style="display:none">
                        <h4><i class="icon fa fa-warning" id="errormsg"></i> 出错了!</h4>

                        <p id="msg-error-p"></p>
                    </div>
                    <div id="msgsuccess" class="alert alert-success alert-dismissable" style="display:none">
                        <h4><i class="icon fa fa-info"></i>提交成功</h4>

                        <p id="ss-msg-success-p"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-bullhorn"></i>

                            <h3 class="box-title">报名人数</h3>
                        </div>
                        <div class="box-body">
                            <dl class="dl-horizontal">
                                <dt>早餐未匹配人数</dt>
                                <dd>
                                    <span id="brenotpairednum">${brenotpairednum}人</span>
                                    <span id="bretotal">(总${bretotal}人)</span>
                                </dd>
                                <dt>自习未匹配人数</dt>
                                <dd>${stunotpairednum}人(总${stutotal}人)
                                </dd>
                                <dt>辅学者人数</dt>
                                <dd>${teachernum}人
                                </dd>
                                <dt>被辅学者人数</dt>
                                <dd>${studentnum}人
                                </dd>
                            </dl>
                            <button type="submit" id="donwloadexcel" class="btn btn-primary ladda-button"
                                    data-style="zoom-in">
                                <span class="ladda-label">下载excel</span>
                            </button>
                            <button type="submit" id="donwloadauthdata" class="btn btn-primary ladda-button"
                                    data-style="zoom-in">
                                <span class="ladda-label">下载辅学认证资料</span>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-link"></i>

                            <h3 class="box-title">问卷链接</h3>
                        </div>
                        <div class="box-body">
                            <dl class="dl-horizontal">
                                <dt>相约自习链接</dt>
                                <dd>${assiturl}
                                </dd>
                            </dl>
                            <dl class="dl-horizontal">
                                <dt>早餐叫醒链接</dt>
                                <dd id="urlvalue">${breurl}
                                </dd>
                            </dl>
                            <button type="submit" id="beginpair" class="btn btn-primary ladda-button"
                                    data-style="zoom-in">
                                <span class="ladda-label">开始配对</span>
                            </button>
                            <button type="submit" id="sendpairinfo" class="btn btn-primary ladda-button"
                                    data-style="zoom-in">
                                <span class="ladda-label">发送邮件</span>
                            </button>
                            <button type="submit" id="changebreurl" class="btn btn-primary">变换链接
                            </button>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">

                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-inbox"></i>

                            <h3 class="box-title">其他信息</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">错误信息数量</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="errornum" autocomplete="off"
                                               value="${errornum}" disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">早餐限制人数</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="bremaxnum" autocomplete="off"
                                               value="${bremaxnum}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">设置抬头</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="title" autocomplete="off"
                                               value="${title}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">设置Logo</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="logo" autocomplete="off"
                                               value="${logo}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <button id="submitinfo" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">提交信息</span>
                            </button>
                            <button id="downloaderrormsag" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">下载错误日志</span>
                            </button>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </section>
    </div>
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
    var brenotpairednum = ${brenotpairednum};

    $(document).ready(function () {
        if (brenotpairednum  < 2) {
            $("#beginpair").addClass("disabled");
        }
    });
</script>
</body>
</html>
