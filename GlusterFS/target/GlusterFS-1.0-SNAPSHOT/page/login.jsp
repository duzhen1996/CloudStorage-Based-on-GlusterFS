<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="glusterFS login page">
  <meta name="author" content="zhang huanhuan">
  <title>Gluster FS</title>

  <!-- Bootstrap Core CSS -->
  <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

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

</head>

<body style="font-family: Microsoft YaHei">

<div class="container">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="login-panel panel panel-default">
        <div class="panel-heading">
          <h3>Sign In to GlusterFS</h3>
        </div>
        <div class="panel-body">
          <form role="form" id="loginForm" action="/Login/IfNotLogin.do" method="post">
            <fieldset>
              <div class="form-group">
                <input class="form-control" placeholder="Username" name="userName" id="username">
              </div>
              <div class="form-group">
                <input class="form-control" placeholder="Password" name="passWord" type="password" id="password" value="">
              </div>
              <%--<div class="checkbox">--%>
                <%--<label>--%>
                  <%--<input name="remember" type="checkbox" value="Remember Me">Remember Me--%>
                <%--</label>--%>
              <%--</div>--%>
              <!-- Change this to a button or input when using this as a form -->
              <%--<a href="overview.jsp" class="btn btn-lg btn-success btn-block">Login</a>--%>
              <input type="submit"  id="submitButton" value="登陆" class="btn btn-lg btn-success btn-block" style="font-family: Microsoft YaHei">
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>
<script src="/js/publicFunction.js"></script>
<script>
  $().ready( function()
  {
    $("#loginForm :input").focus(function()
    {
      $("#submitButton").attr("class" , "btn btn-lg btn-success btn-block");
      $("#submitButton").val("登陆");
    });

    var judge = ${judge};

    if( judge == 0 )
    {
      $("#submitButton").attr("class" , "btn btn-lg btn-danger btn-block");
      $("#submitButton").val("密码错误");
    }
    if( judge == "-1" || judge == -1 )
    {
      $("#submitButton").attr("class" , "btn btn-lg btn-danger btn-block");
      $("#submitButton").val("用户名不存在");
    }

    $(window).bind('beforeunload',function(){
      $.ajax({
        url:"/nodeController/close.do",
      })
    });
  });
</script>

</body>

</html>
