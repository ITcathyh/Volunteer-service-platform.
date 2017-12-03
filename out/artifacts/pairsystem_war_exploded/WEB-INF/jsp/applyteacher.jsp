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
        <a class="logo"><%=Variable.logo%>
        </a>
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

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的性别</label>

                                    <div class="col-sm-9">
                                        <select class="form-control" id="selfsex">
                                            <option>男</option>
                                            <option>女</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的学院</label>

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
                                    <label class="col-sm-3 control-label">擅长的基础课</label>

                                    <div class="col-sm-9">
                                        <select class="form-control" id="basecourse">
                                            <option>近现代史纲要</option>
                                            <option>毛泽东思想与社会主义特色理论</option>
                                            <option>马克思主义原理</option>
                                            <option>高等数学</option>
                                            <option>文科数学</option>
                                            <option>线性代数</option>
                                            <option>概率统计</option>
                                            <option>数值分析</option>
                                            <option>大学物理</option>
                                            <option>大学物理实验</option>
                                            <option>大学英语课程</option>
                                            <option>四六级</option>
                                            <option>托福雅思</option>
                                            <option>无</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">擅长的专业课</label>

                                    <div class="col-sm-9">
                                        <select class="form-control" id="professional">
                                            <option>工商管理</option>
                                            <option>中央银行业务</option>
                                            <option>国际结算</option>
                                            <option>计算机辅助CAD/CAM</option>
                                            <option>金融学</option>
                                            <option>金融时间序列分析(2)</option>
                                            <option>行为金融与有效市场</option>
                                            <option>生产系统建模与仿真</option>
                                            <option>当代中国经济热点问题</option>
                                            <option>社会保障学</option>
                                            <option>土地管理学</option>
                                            <option>资本论</option>
                                            <option>基础工业工程</option>
                                            <option>金融工程</option>
                                            <option>技术经济学</option>
                                            <option>计算机安全与保密</option>
                                            <option>财务报表阅读与分析(双语)</option>
                                            <option>现代制造系统</option>
                                            <option>企业文化</option>
                                            <option>管理信息系统(二)</option>
                                            <option>网站设计</option>
                                            <option>计量经济学导论</option>
                                            <option>金融法规</option>
                                            <option>工业应用数理统计</option>
                                            <option>市场研究方法</option>
                                            <option>固定收益证券分析(双语)</option>
                                            <option>投资经济学</option>
                                            <option>当代世界经济与政治</option>
                                            <option>企业战略管理</option>
                                            <option>投资管理</option>
                                            <option>营销策划</option>
                                            <option>服务计算概论</option>
                                            <option>先进制造技术及应用</option>
                                            <option>多媒体技术</option>
                                            <option>管理信息系统(3)双语</option>
                                            <option>运筹学(一)</option>
                                            <option>服务管理</option>
                                            <option>人力资源管理</option>
                                            <option>物流工程进展</option>
                                            <option>管理统计学</option>
                                            <option>管理运筹学</option>
                                            <option>公共关系学</option>
                                            <option>我国的货币政策</option>
                                            <option>成本会计学(二)</option>
                                            <option>高级财务会计</option>
                                            <option>税务会计与纳税筹划(2)</option>
                                            <option>无</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <button id="submitaddteacher" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">提交信息</span>
                            </button>
                        </div>
                    </div>
                    <!-- /.box-body -->
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
        <strong>Powered by <a href="<%=Variable.myweb%>">ITcathyh</a> </strong>
    </footer>
</div>
<input type="hidden" name="CSRFToken" id="token" value="${csrftoken}"/>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/pair.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript">
    var headers = {};
    headers['requesttoken'] = $("#token").val();

    $("#selfcollege").change(function () {
        alterChoice($("#selfcollege").find("option:selected").text());
    });
</script>
</body>
</html>
