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
            <a href="#"><i class="fa fa-wrench fa-fw"></i><span>访问配置信息控制</span></a>
          </li>
          <li>
            <a href="/User/jump.do"><i class="fa fa-wrench fa-fw"></i><span>用户注册</span></a>
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
        <h1 class="page-header">访问控制信息配置</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            信息配置
          </div>
          <!-- /.panel-heading -->
          <div class="panel-body">
            <div class="row">

              <div class="col-xa-12 col-md-12">
                <div id="storage-total" style="margin: 10px">
                  <form class="form-horizontal" id="NMform" action="/configController/addAcl.do" method="POST">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">目录:</label>
                      <div class="col-sm-10">
                        <input type="text" id="NdiskName" class="form-control" placeholder="必填" name="disk_dirname">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">起始IP地址:</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="必填" name="acl_ip_start" id="Nstartip">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">截止IP地址:</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="选填，非ip区段不用填写"  name="acl_ip_end" id="Nendip">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">用户名:</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="必填" name="general_user" id="Nuser">
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label">分配空间(G):</label>
                      <div class="col-sm-10">
                        <input type="text" id="Ndiskcap" class="form-control" placeholder="必填" name="disk_capacity">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">即刻允许挂载:</label>
                      <div class="col-sm-10">
                        <input type="checkbox" name="acl_allowmount" id="Nallow">
                      </div>
                    </div>
                    <input type="button" class="btn btn-success form-control" id="submitButton" value="添加">
                  </form>
                </div>
              </div>

            </div>

          </div>
        </div>

        <!-- /.panel -->
      </div>

      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            配置信息
          </div>
          <!-- /.panel-heading -->
          <div class="panel-body">
            <div class="dataTable_wrapper">
              <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                <thead>
                <tr>
                  <th>目录</th>
                  <th>起始IP地址</th>
                  <th>截止IP地址</th>
                  <th>用户名</th>
                  <th>分配空间(G)</th>
                  <th>已用空间(G)</th>
                  <th>是否可挂载</th>
                  <th>修改</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${aclList}" var="acl">
                  <c:out value="<tr id=\"${acl.acl_id}\">
                                    <td>${acl.disk_dirname}</td>
                                    <td class=\"ip\">${acl.acl_ip_start}</td>
                                    <td class=\"ip\">${acl.acl_ip_end}</td>
                                    <td class=\"center\">${acl.general_user}</td>
                                    <td class=\"center\">${acl.disk_capacity/1014/1024/1024}</td>
                                    <td class=\"center\">${acl.disk_usage/1014/1024/1024}</td>
                                    <td>${acl.acl_allowmount}</td>
                                    <td><a data-toggle=\"modal\"
                                           data-target=\".myModal\" style=\"cursor: pointer;\">修改</a>
                                           <a href=\"/configController/delete/${acl.acl_id}.do\">删除</a>
                                           </td>
                                    </tr>" escapeXml="false">
                  </c:out>
                </c:forEach>
                <%--<c:forEach items="${aclList}" var="acl">--%>
                  <%--<c:out value="<tr id=\"${acl.acl_id}\">--%>
                                    <%--<td>${acl.disk_dirname}</td>--%>
                                    <%--<td class=\"ip\">${acl.acl_ip_start}</td>--%>
                                    <%--<td class=\"ip\">${acl.acl_ip_end}</td>--%>
                                    <%--<td class=\"center\">${acl.general_user}</td>--%>
                                    <%--<td class=\"center\">${acl.disk_capacity/1024/1024/1024}</td>--%>
                                    <%--<td class=\"center\">${acl.disk_usage/1024/1024/1024}</td>--%>
                                    <%--<td>${acl.acl_allowmount}</td>--%>
                                    <%--<td>--%>
                                      <%--<a href=\"/configController/delete/${acl.acl_id}.do\">删除</a>--%>
                                    <%--</td>--%>
                                    <%--</tr>" escapeXml="false">--%>
                  <%--</c:out>--%>
                <%--</c:forEach>--%>
                </tbody>
              </table>
            </div>
          </div>
          <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
      </div>
      <!-- /.col-lg-12 -->
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

<!-- 模态框（Modal） -->
<div class="modal fade myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="myModalLabel">
          访问控制信息修改
        </h4>
      </div>
      <div class="modal-body" id="modelWindow">
        <!--在模态框中的这个表单不能直接传给后台一个对象，而是一个个传过去，只有四个信息可以编辑，第五个信息也就是acl的id是隐藏起来顺便提交的-->
        <!--还是要使用ajax进行提交，在按钮上出现两个返回值，要么就是这条记录已经存在，如果成功就是刷新页面-->
        <form class="form-horizontal" id="modelForm">
          <div class="form-group">
            <label class="col-sm-2 control-label">目录:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" placeholder="目录" name="disk_dirname" id="Mdirname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">起始IP:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" placeholder="起始IP" id="Mbeginip">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">截止IP:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" placeholder="截止IP" id="Mendip">
            </div>
          </div>
          <%--<div class="form-group">--%>
          <%--<label class="col-sm-2 control-label">Ip:</label>--%>
          <%--<div class="col-sm-10">--%>
          <%--<div class="input-group form-inline">--%>

          <%--<input type="text" class="form-control" placeholder="IP">--%>
          <%--<div class="input-group-btn">--%>
          <%--<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></button>--%>
          <%--<ul class="dropdown-menu dropdown-menu-right">--%>
          <%--<li><a href="#">any</a></li>--%>
          <%--</ul>--%>
          <%--</div><!-- /btn-group -->--%>
          <%--</div><!-- /input-group -->--%>
          <%--</div>--%>
          <%--</div>--%>
          <div class="form-group">
            <input type="text" class="hide" name="acl_id" id="aclName">
            <input type="text" class="hide" name="disk_usage" id="haveUsed"><!--这两个是隐藏的，不参与是否重复的比较，如果在已经出现了重复的记录那就不让加入，然后看这个用户名是不是已经存在的，如果不存在，那也不让修改-->
            <!--如果没有出现重复的记录，那就根据aclid在指定的记录上进行修改，在修改之前又要注意，要检查这个盘符是不是已经出现过，如果没有出现，就要在diskinfo上添加新的记录，在添加的过程中记得把已使用的空间加进去-->
            <!--如果符合要求就根据aclid在原有基础上修改-->
            <label class="col-sm-2 control-label">用户名:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userName" name="general_user" placeholder="用户名">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">可用空间(G):</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="disk_capacity" id="canBeUse" placeholder="可用空间">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">即刻允许挂载:</label>
            <div class="col-sm-10">
              <input type="checkbox" name="acl_allowmount" id="allow">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default"
                data-dismiss="modal">关闭
        </button>
        <button type="button" class="btn btn-primary" id="changeButton">
          提交更改
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>

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

    //在召唤出模态框之前把表格中的值移到表单里
    $(".myModal").on('show.bs.modal' , function(event)//JQuery函数数组的点调用好像会有点问题
    {
      //这里要找到修改那个链接所在的那一行的所有元素，也就是他的父标签的所有兄弟标签
      var a = $(event.relatedTarget);
      var parentArr = a.parent().siblings();//所有的父元素的兄弟取到，这个时候应该是一个数组

      $("#Mdirname").val( parentArr[0].innerHTML );
      $("#Mbeginip").val( parentArr[1].innerHTML );
      $("#Mendip").val( parentArr[2].innerHTML );
      $("#userName").val( parentArr[3].innerHTML );
      $("#canBeUse").val( parentArr[4].innerHTML );
      $("#haveUsed").val( parentArr[5].innerHTML );
      $("#aclName").val( parentArr.parent().attr('id') );
      //以上是将表单初始化，这里包括了几乎所有要提交的内容，还有一个checkbox没有囊括
      $("#changeButton").attr("class" , "btn btn-success");
      $("#changeButton").text("提交更改");
    });

    //在每一次修改Model表单之后初始化一下界面
    $("#modelForm :input").focus(function()
    {
      $("#changeButton").attr("class" , "btn btn-success");
      $("#changeButton").text("提交更改");
    });

    //每一次修改非模态框的表单的时候，初始化表单
    $("#NMform :input").change(function()
    {
      $("#submitButton").attr("class" , "btn btn-success form-control");
      $("#submitButton").val("添加");
    });

    //在添加新的acl信息部分，去找一下盘符的被分配的容量，方便用户使用，用户可以重新分配容量，也可以使用数据库中已有的值
    $("#NdiskName").change(function()
    {
      $.ajax({
        url:"/configController/ifNotHave.do",
        datatype:"json",
        data:{
          dirname:$("#NdiskName").val(),
        },
        success:function(data)
        {
          var returnNumber = eval("("+ data +")");//当传回的东西是Json格式（使用外部Jar写的Json，不是自己用String写的Json），就需要这样子解析一下
          if (returnNumber != 0)
          {
            $("#Ndiskcap").val(returnNumber/1024/1024/1024);
          }
          else
          {
            $("#Ndiskcap").val("");
          }
        }
      });
    });


    $("#submitButton").click(function()
    {
      $.ajax({
        url:"/configController/addacl.do",
        datatype:"json",
        data:
        {
          disk_dirname:$("#NdiskName").val(),
          acl_ip_start:$("#Nstartip").val(),
          acl_ip_end:$("#Nendip").val(),
          general_user:$("#Nuser").val(),
          disk_capacity:$("#Ndiskcap").val(),
          acl_allowmount:$("#Nallow").is(':checked')
        },
        success:function(data)
        {
          var data = eval("(" + data + ")");
          if( data == 0 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("权限信息已经存在");
          }

          if( data == 4 )
          {
            $("#submitButton").attr("class" , "btn btn-danger");
            $("#submitButton").text("目录不存在");
          }

          if( data == 1 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("用户不存在");
          }

          if( data == 5 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("ip地址格式不正确");
          }

          if( data == 3 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("输入信息不完整");
          }

          if( data == 6 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("目录已经被占用");
          }

          if( data == 7 )
          {
            $("#submitButton").attr("class" , "btn btn-danger form-control");
            $("#submitButton").val("起始ip必须小于截止ip");
          }

          if( data == 2 )
          {
            window.location.href = "/configController/ShowAclInformation.do";
          }
        }
      })
    });


    //这里处理模态框提交的过程
    $("#changeButton").click(function()
    {
      $.ajax({
        url:"/configController/change.do",
        datatype:"json",
        data:{
          acl_id:$("#aclName").val(),
          disk_usage:$("#haveUsed").val(),
          general_user:$("#userName").val(),
          disk_capacity:$("#canBeUse").val(),
          acl_allowmount:$("#allow").is(':checked'),//这个位置很好的展现了在JQuery中怎么判断一个表达是否被选中，返回的是bool值
          disk_dirname:$("#Mdirname").val(),
          acl_ip_start:$("#Mbeginip").val(),
          acl_ip_end:$("#Mendip").val(),
        },
        success:function(data)
        {
          var data = eval("(" + data + ")");
          if(data == 0)//这个错误代码代表的是，已经存在这条记录
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("此信息已经存在");
          }
          if( data == 1 )//这个代表了分配的用户名不存在，用户名是可以为空的，如果没有用户名，那就不用进行用户名的搜寻
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("不存在此用户");
          }

          if( data == 2 )
          {
            $('#modelWindow').modal('hide');
            window.location.href = "/configController/ShowAclInformation.do";
          }

          if( data == 3 )
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("输入信息有空缺");
          }

          if( data == 4 )
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("目录不存在");
          }

          if( data == 5 )
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("IP地址格式不正确");
          }

          if( data == 6 )
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("盘符已经被占用");
          }

          if( data == 7 )
          {
            $("#changeButton").attr("class" , "btn btn-danger");
            $("#changeButton").text("起始ip必须小于截止ip");
          }
        }
      });
    });

    //这里处理所有的ip地址格式转化，首先获取对应tbody所有的子标签
    var ipArray = $("body").find("td.ip");
    for( var i = 0 ; i < ipArray.length ; i++ )
    {
      ipArray[i].innerHTML = ipTranslate(ipArray[i].innerHTML);
    }

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
