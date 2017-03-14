<%--
Created by IntelliJ IDEA.
User: DZ
Date: 2015/12/20
Time: 15:27
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="Zhang huanhuan">

  <title>GlusterFS</title>

  <!-- Bootstrap Core CSS -->
  <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

  <!-- DataTables CSS -->
  <link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

  <!-- DataTables Responsive CSS -->

  <!-- Custom CSS -->
  <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style>
    .footer.fixed {
      background: #fff none repeat scroll 0 0;
      border-top: 1px solid #e7eaec;
      bottom: 0;
      left: 0;
      margin:20px 0 0 250px;
      position: fixed;
      right: 0;
      z-index: 1000;
      padding: 7px 20px 10px;
    }
  </style>
</head>

<body style="font-family: Microsoft YaHei">

<div id="wrapper">
  <!-- Navigation -->
  <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Gluster 管理系统</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
      <!-- /.dropdown -->
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
          <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-alerts">
          <li>
            <a href="#">
              <div>
                <i class="fa fa-comment fa-fw"></i> New Comment
                <span class="pull-right text-muted small">4 minutes ago</span>
              </div>
            </a>
          </li>
          <li class="divider"></li>
          <li>
            <a href="#">
              <div>
                <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                <span class="pull-right text-muted small">12 minutes ago</span>
              </div>
            </a>
          </li>
          <li class="divider"></li>
          <li>
            <a href="#">
              <div>
                <i class="fa fa-envelope fa-fw"></i> Message Sent
                <span class="pull-right text-muted small">4 minutes ago</span>
              </div>
            </a>
          </li>
          <li class="divider"></li>
          <li>
            <a href="#">
              <div>
                <i class="fa fa-tasks fa-fw"></i> New Task
                <span class="pull-right text-muted small">4 minutes ago</span>
              </div>
            </a>
          </li>
          <li class="divider"></li>
          <li>
            <a href="#">
              <div>
                <i class="fa fa-upload fa-fw"></i> Server Rebooted
                <span class="pull-right text-muted small">4 minutes ago</span>
              </div>
            </a>
          </li>
          <li class="divider"></li>
          <li>
            <a class="text-center" href="#">
              <strong>See All message</strong>
              <i class="fa fa-angle-right"></i>
            </a>
          </li>
        </ul>
        <!-- /.dropdown-alerts -->
      </li>
      <!-- /.dropdown -->
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
          <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
          <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
          </li>
          <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
          </li>
          <li class="divider"></li>
          <li><a href="/Login/Jump.do"><i class="fa fa-sign-out fa-fw"></i>登出</a>
          </li>
        </ul>
        <!-- /.dropdown-user -->
      </li>
      <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
      <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
          <li class="sidebar-search">
            <div class="input-group custom-search-form">
              <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                  <i class="fa fa-search"></i>
                                </button>
                            </span>
            </div>
            <!-- /input-group -->
          </li>
          <li>
            <a href="/Overview/Jump.do"><i class="fa fa-files-o fa-fw"></i>概览</a>
          </li>
          <li>
            <a href="#"><i class="fa fa-sitemap fa-fw"></i>节点信息<span class="fa arrow"></span></a>
            <ul class="nav nav-second-level">
              <c:forEach items="${nodes}" var="i">
                <c:out value="<li><a href=\"/nodeController/${i.node_name}.do\">${i.node_name}</a></li>" escapeXml="false"></c:out>
              </c:forEach>
            </ul>
            <!-- /.nav-second-level -->
          </li>
          <li>
            <a href="/configController/ShowAclInformation.do"><i class="fa fa-wrench fa-fw"></i><span>访问配置信息控制</span></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-wrench fa-fw"></i><span>用户注册</span></a>
          </li>
          <li>
            <a href="/nodeAdd/jump.do"><i class="fa fa-wrench fa-fw"></i><span>节点登记</span></a>
          </li>
        </ul>
      </div>
      <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
  </nav>

  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">用户添加</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            用户
          </div>
          <!-- /.panel-heading -->
          <div class="panel-body">
            <div class="row">

              <div class="col-xa-12 col-md-12">
                <div id="storage-total" style="margin: 10px">
                  <form class="form-horizontal" id="userForm" action="" method="POST">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">用户名：</label>
                      <div class="col-sm-10">
                        <input type="text" id="userName" class="form-control" name="general_user">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">密码：</label>
                      <div class="col-sm-10">
                        <input type="text" id="password" class="form-control" name="general_passwd">
                      </div>
                    </div>
                    <input type="button" class="btn btn-success form-control" id="submitButton" value="添加">
                  </form>
                </div>
              </div>

            </div>

          </div>
  <!-- /.col-lg-12 -->
        </div>
      </div>
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            用户信息
          </div>
          <!-- /.panel-heading -->
          <div class="panel-body">
            <div class="dataTable_wrapper">
              <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                <thead>
                <tr>
                  <th>用户名</th>
                  <th>密码</th>
                  <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userlist}" var="user">
                  <c:out value="<tr id=\"${user.userName}\">
                                    <td>${user.userName}</td>
                                    <td>${user.passWord}</td>
                                    <td><a href=\"/User/delete/${user.userName}.do\">删除</a></td>
                                    </tr>" escapeXml="false">
                  </c:out>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
      </div>
  </div>
</div>

  <div class="panel">
    <div class="footer fixed ng-scope">
      <div>
        <strong>Copyright</strong> GlusterFS Inc &copy; 2014-2015
      </div>
    </div>
  </div>
  <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->



<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>
<script src="/js/publicFunction.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
  $(document).ready(function() {
    $("#userForm :input").focus(function()
    {
      $("#submitButton").attr("class" , "btn btn-success form-control");
      $("#submitButton").val("添加");
    });

    $("#submitButton").click( function()
    {
      $.ajax({
        url:"/User/add.do",
        datatype:"json",
        data:
        {
          username:$("#userName").val(),
          password:$("#password").val(),
        },
        success:function( data )
        {
          var data = eval("("+ data +")");
          if(data==1)
          {
            //如果返回的是1就刷新页面
            window.location.href = "/User/jump.do";
          }
          if( data == 2 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("用户名或密码有空缺");
          }
          if( data == 0 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("用户名已存在");
          }
        }
      })
    });

    $(window).bind('beforeunload',function(){
      $.ajax({
        url:"/nodeController/close.do",
      })
    });

    $('#dataTables-example').DataTable({
      responsive: true
    });
  });
</script>

</body>

</html>
