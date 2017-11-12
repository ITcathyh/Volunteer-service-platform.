<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/7/20
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="hyh.entity.Teacher" %>
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
        List<Object> result = null;

        if (request.getAttribute("result") != null) {
            result = (List<Object>) request.getAttribute("result");
            pages = result.size() / MAX_SIZE + 1;
        }
    %>
</head>
<body class="skin-blue">
<!-- Site wrapper -->
<div class="wrapper">
    <header class="main-header">
        <a class="logo"><%=Variable.logo%>
        </a>
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
                查询辅学名单
                <small>Query assist</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="input-group" style="width: 350px;">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default
                        dropdown-toggle" data-toggle="dropdown" id="showselect">搜索内容<span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a id="all">全部</a>
                                </li>
                                <li>
                                    <a id="breakfast">早餐叫醒</a>
                                </li>
                                <li>
                                    <a id="studying">相约自习</a>
                                </li>
                                <li>
                                    <a id="teach">辅学</a>
                                </li>
                            </ul>
                        </div><!-- /btn-group -->
                        <input type="text" class="form-control" id="searchtext" placeholder="请输入学号" name="stuid"/>
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="search">搜索全部</button>
                    </span>
                    </div><!-- /input-group -->
                    <div class=" box
                        ">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tr>
                                    <th>类别</th>
                                    <th>姓名</th>
                                    <th>学号</th>
                                </tr>
                                <%
                                    if (result == null) {
                                        out.println("<tr>");
                                        out.println("<th>无相关信息</th>");
                                        out.println("</tr>");
                                    } else {
                                        String genre;

                                        if (result.get(0) instanceof User) {
                                            if (((User) result.get(0)).getType() == 1) {
                                                genre = "早餐叫醒";
                                            } else {
                                                genre = "相约自习";
                                            }
                                        } else if (result.get(0) instanceof Teacher) {
                                            genre = "辅学";
                                        }

                                        for (int i = nowpage * MAX_SIZE, len1 = (nowpage + 1) * MAX_SIZE,
                                             len2 = result.size(); i < len1 && i < len2; i++) {
                                            if (result.get(i) instanceof User) {
                                                User user = (User) result.get(i);

                                                if (user.getType() == 1) {
                                                    out.println("<tr onclick=\"javascrtpt:window.location.href='/admingetuser?type=1&studentid=" + user.getStudentid() + "'\">");
                                                    out.println("<th>早餐叫醒</th>");
                                                } else {
                                                    out.println("<tr onclick=\"javascrtpt:window.location.href='/admingetuser?type=2&studentid=" + user.getStudentid() + "'\">");
                                                    out.println("<th>相约自习</th>");
                                                }

                                                out.println("<th>" + user.getName() + "</th>");
                                                out.println("<th>" + user.getStudentid() + "</th>");
                                                out.println("</tr>");
                                            } else if (result.get(i) instanceof Teacher) {
                                                Teacher teacher = (Teacher) result.get(i);

                                                out.println("<tr onclick=\"javascrtpt:window.location.href='/admingetassist?studentid=" + teacher.getStudentid() + "'\">");
                                                out.println("<th>辅学</th>");
                                                out.println("<th>" + teacher.getName() + "</th>");
                                                out.println("<th>" + teacher.getStudentid() + "</th>");
                                                out.println("</tr>");
                                            }
                                        }
                                    }
                                %>
                            </table>
                            <ul class="pagination">
                                <li id="pre"><a href="/adminqueryuser?page=<%=nowpage == 0?0:nowpage - 1%>
<%=session.getAttribute("searchtext") == null ? "" : "&searchtext=" + session.getAttribute("searchtext")%>"
                                                id="preurl" rel="prev" title="第<%=nowpage == 0?1:nowpage%>页">&laquo;</a>
                                </li>
                                <li class="active"><span><%=nowpage + 1%></span></li>
                                <li id="next"><a
                                        href="/adminqueryuser?page=<%=nowpage == (pages - 1)?nowpage:nowpage + 1%>
<%=session.getAttribute("searchtext") == null ? "" : "&searchtext=" + session.getAttribute("searchtext")%>"
                                        id="nexturl" rel="next"
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
        <strong>Powered by <a href="<%=Variable.myweb%>">ITcathyh</a> </strong>
    </footer>
</div>

<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    var nowpage = <%=nowpage%>;
    var maxpage = <%=pages - 1%>;

    $(document).ready(function () {
        if (nowpage == 0) {
            $("#pre").addClass("disabled");
            $("#preurl").attr("href", "javascript:void(0)");
        }

        if (nowpage == maxpage) {
            $("#next").addClass("disabled");
            $("#nexturl").attr("href", "javascript:void(0)");
        }
    });

    $(document).on("click", "#search", function (e) {
        var searchtext = $("#searchtext").val();

        if (searchtext.length > 0) {
            window.location.href = "/adminqueryuser?searchtext=" + searchtext + "&genre=" + $("#search").text().replace("搜索", "");
        } else {
            window.location.href = "/adminqueryuser?genre=" + $("#search").text().replace("搜索", "");
        }
    });

    $(document).on("click", "#breakfast", function (e) {
        $("#search").text("搜索早餐叫醒");
    });

    $(document).on("click", "#studying", function (e) {
        $("#search").text("搜索相约自习");
    });

    $(document).on("click", "#teach", function (e) {
        $("#search").text("搜索辅学");
    });

    $(document).on("click", "#all", function (e) {
        $("#search").text("搜索全部");
    });
</script>
</body>
</html>
