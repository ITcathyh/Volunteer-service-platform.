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
    <!-- Bootstrap 3.3.2 -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/ionicons.min.css" rel="stylesheet">
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <%!
        private static final int MAX_SIZE = 12;
    %>
    <%
        int pages = 1;
        int nowpage = request.getAttribute("nowpage") == null ? 0 : (Integer) request.getAttribute("nowpage");
        List<Teacher> teachers = null;

        if (request.getAttribute("teachers") != null) {
            teachers = (List<Teacher>) request.getAttribute("teachers");
            pages = teachers.size() / MAX_SIZE + 1;
        }
    %>
</head>
<body class="skin-blue">
<!-- Site wrapper -->
<div class="wrapper">
    <header class="main-header">
        <a class="logo"><%=Variable.logo%></a>
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
                查询辅学名单
                <small>Query assist</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div>
                        <button id="addteacher" class="btn btn-primary"
                                onclick="javascrtpt:window.location.href='/applyteacher'">成为辅学者
                        </button>
                        <button id="needteacher" class="btn btn-primary"
                                onclick="javascrtpt:window.location.href='/self-studying'">约自习（无辅学要求）
                        </button>
                    </div>
                    <div class=" box
                        ">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tr>
                                    <th>姓名</th>
                                    <th>学院</th>
                                    <th>性别</th>
                                    <th>擅长领域</th>
                                </tr>
                                <%
                                    if (teachers == null) {
                                        out.println("<tr>");
                                        out.println("<th>辅学的小哥哥小姐姐都被抢走咯，快来申请吧~</th>");
                                        out.println("</tr>");
                                    } else {
                                        for (int i = nowpage * MAX_SIZE, len1 = (nowpage + 1) * MAX_SIZE,
                                             len2 = teachers.size(); i < len1 && i < len2; i++) {
                                            out.println("<tr onclick=\"javascrtpt:window.location.href='/teacher?studentid=" + teachers.get(i).getStudentid() + "'\">");
                                            out.println(teachers.get(i));
                                            out.println("</tr>");
                                        }
                                    }
                                %>
                            </table>
                            <ul class="pagination">
                                <li id="pre"><a href="/assist?page=<%=nowpage == 0?0:nowpage - 1%>"
                                                rel="prev" title="第<%=nowpage == 0?1:nowpage%>页">&laquo;</a></li>
                                <li class="active"><span><%=nowpage + 1%></span></li>
                                <li id="next"><a
                                        href="/assist?page=<%=nowpage == (pages - 1)?nowpage:nowpage + 1%>"
                                        rel="next"
                                        title="第<%=nowpage == (pages - 1)?nowpage + 1:nowpage + 2%>页">&raquo;</a></li>
                            </ul>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div>
            </div>

        </section><!-- /.content -->
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
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    var nowpage = <%=nowpage%>;
    var maxpage = <%=pages - 1%>;

    $(document).ready(function () {
        if (nowpage == 0) {
            $("#pre").addClass("disabled");
        }

        if (nowpage == maxpage) {
            $("#next").addClass("disabled");
        }
    });
</script>
</body>
</html>
