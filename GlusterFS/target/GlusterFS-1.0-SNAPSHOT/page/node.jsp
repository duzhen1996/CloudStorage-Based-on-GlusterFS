<%--
  Created by IntelliJ IDEA.
  User: DZ
  Date: 2015/12/20
  Time: 15:29
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
    <link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="/js/publicFunction.js"></script>
    <![endif]-->
    <style>
        .footer.fixed {
            background: #fff none repeat scroll 0 0;
            border-top: 1px solid #e7eaec;
            bottom: 0;
            left: 0;
            margin: 20px 0 0 250px;
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
                    <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
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
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
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
                            <ul class="nav nav-second-level" id="nodeMenu">
                                <c:forEach var="i" items="${nodes}">
                                    <c:out value="<li><a href=\"/nodeController/${i.node_name}.do\"> ${i.node_name} </a></li>"
                                           escapeXml="false"/>
                                </c:forEach>
                            </ul>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="/configController/ShowAclInformation.do"><i class="fa fa-wrench fa-fw"></i><span>访问控制信息配置</span></a>
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
                <h1 class="page-header">${cnode.node_name} 概览</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <!-- /.col-lg-12 -->
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        存储容量(${cnode.node_usage/1024/1024/1024}G/${(cnode.node_capacity - cnode.node_usage)/1024/1024/1024}G)
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-pie-chart"></div>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        吞吐量/CPU使用率
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-line-chart-multi"></div>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>

            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        节点访问统计
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        节点信息表
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>日期</th>
                                                    <th>次数</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="i" begin="0" end="8">
                                                    <c:out value="<tr><td>${mountCount.get(0).get(i)}</td><td>${mountCount.get(1).get(i)}</td></tr>"
                                                           escapeXml="false"></c:out>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>


                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.col-lg-4 (nested) -->
                            <div class="col-lg-8">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        节点访问图
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="panel-body">
                                        <div class="flot-chart">
                                            <div class="flot-chart-content" id="flot-bar-chart"></div>
                                        </div>
                                    </div>
                                    <!-- /.panel-body -->
                                </div>
                            </div>
                            <!-- /.col-lg-8 (nested) -->
                        </div>
                    </div>
                </div>


            </div>

            <!-- /.col-lg-6 -->
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        挂载用户信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>IP</th>
                                    <th>已用空间</th>
                                    <th>分配空间</th>
                                </tr>
                                </thead>
                                <tbody id="iptable">
                                <c:forEach items="${gidInfos}" var="gidInfo">
                                    <c:out value="<tr><td>${gidInfo.get('general_user')}</td>
                  <td class=\"ip\">${gidInfo.get('gidinfo_ip')}</td>
                  <td>${gidInfo.get('disk_usage')/1024/1024/1024}</td>
                  <td>${gidInfo.get('disk_capacity')/1024/1024/1024}</td></tr>" escapeXml="false"></c:out>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
            <!-- /.panel -->
            <!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
    <div class="panel">
        <div class="footer fixed ng-scope">
            <div>
                <strong>Copyright</strong> GlusterFS Inc &copy; 2014-2015
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Flot Charts JavaScript -->
<script src="../bower_components/flot/excanvas.min.js"></script>
<script src="../bower_components/flot/jquery.flot.js"></script>
<script src="../bower_components/flot/jquery.flot.pie.js"></script>
<script src="../bower_components/flot/jquery.flot.resize.js"></script>
<script src="../bower_components/flot/jquery.flot.time.js"></script>
<script src="../bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
<script src="../js/node.js"></script>
<!-- DataTables JavaScript -->
<script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>
<script src="/js/publicFunction.js"></script>
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function () {
        //这里是node界面的饼图
        $(function () {

            var data = [{
                label: "已使用",
                data: 3
            }, {
                label: "未使用",
                data: 7
            }];

            var plotObj = $.plot($("#flot-pie-chart"), ${data}, {
                series: {
                    pie: {
                        show: true
                    }
                },
                colors: ["#CB4B4B", "#5196D7"],
                grid: {
                    hoverable: true
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                    shifts: {
                        x: 20,
                        y: 0
                    },
                    defaultTheme: false
                }
            });

        });

        //这里是访问量折线图的东西，暂且认为X轴上的值是距离1970年的秒数
        $(function () {

            var barOptions = {
                series: {
                    bars: {
                        show: true,
                        barWidth: 60000000//这是半天的毫秒数
                        //barWidth: 86400000//这个是1天的毫秒数
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: "%m/%d",
                    minTickSize: [1, "day"]
                },
                grid: {
                    hoverable: true
                },
                legend: {
                    show: false
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%y次"
                }
            };
            //这里是控制的是条形图的
            var barData = {
                label: "bar",
                data:${secondData}//传的值都是距离1970年的毫秒数，但是在x轴上会出现一天的偏差
            };
            $.plot($("#flot-bar-chart"), [barData], barOptions);

        });

        <%--var ip = "";--%>

        <%--//在这个地方使用ajax来获取节点的ip地址--%>
        <%--$.ajax({--%>
        <%--url:"/nodeController/getip/${currentNode}",--%>
        <%--dataType:"json",--%>
        <%--success:function(data)--%>
        <%--{--%>
        <%--ip = data;--%>

        <%--}--%>
        <%--});--%>

        //下面这个函数是要定时运行的,所以在函数的上面就要加一些相对全局的变量
        var bandWideArr = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        var cpuArr = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        //下面进行定时ajax
        setInterval(function () {
            //这个通过ajax从后台接收消息
            $.ajax({
                url: "/nodeload/getnodeload/${currentNodeIp}.do",

                success: function (data) {

                    var data = eval("(" + data + ")")
                    bandWideArr.shift();
                    bandWideArr.push(data[1]);
                    cpuArr.shift();
                    cpuArr.push(data[0]);
                }
            })

            //这里进行重新绘图
            $(function () {
                var bandwideStr = "[";
                var cpuStr = "[";
                //这里生成两个新的json字符串来
                for (var i = 0; i < bandWideArr.length; i++) {
                    bandwideStr = bandwideStr + "[" + (i + 1) + "," + bandWideArr[i] + "] , ";
                    cpuStr = cpuStr + "[" + (i + 1) + "," + cpuArr[i] + "] , ";
                }
                bandwideStr = bandwideStr + "]";
                cpuStr = cpuStr + "]";

                function euroFormatter(v, axis) {
                    return v.toFixed(axis.tickDecimals);
                }

                function doPlot(position) {
                    $.plot($("#flot-line-chart-multi"), [{
                        data: eval("(" + bandwideStr + ")"),
                        label: "带宽"
                    }, {
                        data: eval("(" + cpuStr + ")"),
                        label: "CPU占用",
                        yaxis: 2
                    },], {
                        xaxes: [{
                            mode: 'time',
                        }],
                        y2axes: [{
                            autoscaleMargin: 0.6,
                        }],
                        yaxes: [{
                            autoscaleMargin: 0.6,
                        }, {
                            // align if we are to the right
                            alignTicksWithAxis: position == "right" ? 1 : null,
                            position: position,
                            tickFormatter: euroFormatter
                        }],
                        legend: {
                            position: 'sw'
                        },
                        chart: {
                            defaultSeriesType: 'spline',
                        },
                        grid: {
                            hoverable: true //IMPORTANT! this is needed for tooltip to work
                        },
                        tooltip: true,
                        tooltipOpts: {
                            content: "%s %y",

                            onHover: function (flotItem, $tooltipEl) {
                                // console.log(flotItem, $tooltipEl);
                            }
                        }

                    });
                }

                doPlot("right");

                $("button").click(function () {
                    doPlot($(this).text());
                });
            });
        }, 1200)


        //这里处理所有的ip地址格式转化，首先获取对应tbody所有的子标签
        var ipArray = $("body").find("td.ip");
        for (var i = 0; i < ipArray.length; i++) {
            ipArray[i].innerHTML = ipTranslate(ipArray[i].innerHTML);
        }

        $(window).bind('beforeunload', function () {
            $.ajax({
                url: "/nodeController/close.do",
            })
        });

        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>
</body>

</html>
