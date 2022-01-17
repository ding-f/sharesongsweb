<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/favicon.ico">
    <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/signin/">

    <title>登录到歌曲共享站</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/examples/signin/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
      <script type="text/javascript" src="js/jquery.1.11.1.min.js"></script>


      <script type="text/javascript">


        //检查用户名是否有效
        $(function () {
          $("[name=yourname]").blur(function (){
				var yourname=$(this).val();
				if(yourname==null||yourname==""){
					$("#yourname_span").text("* 你的用户名不能为空").css("color","red");
				}else {
					$.post("useraction",{"yourname":yourname,"flag":"checkYourName"},function(obj){
						//obj是servlet返回的数据，是boolean
						//true代表填写的电话号码没有被注册，能用
						//false代表填写的电话号码被注册了，不能用
						if(obj){
							$("#yourname_span").text("已注册用户 √").css("color","green");

							// yourname_check=true;
						}else{
							$("#yourname_span").text("未注册用户 ×").css("color","red");

						}
					},"json");

				}
			});

          //检查密码
          $("#submitt").click(function() {
            var username=$("#yourname").val();
            var passwd=$("#passwd").val();
            if (username == "" || username == null) {
              $("#yourname_span").text("* 你的用户名不能为空").css("color", "red");

            } else if (passwd == "" || passwd== null) {
              $("#passwd_span").text("* 你的密码不能为空").css("color", "red");

            }else{
              $.post("useraction",{"username":username,"passwd":passwd,"flag":"checkYourNameAndPasswd"},function(obj){
                //obj是servlet返回的数据，是boolean
                //true代表填写的电话号码没有被注册，能用
                //false代表填写的电话号码被注册了，不能用
                if(obj){
                  window.location.assign("${ctx}/sharesongs_web/songlist.jsp");

                  // yourname_check=true;
                }else{
                  $("#error_info").text("密码错误，请检查 ×").css("color","red");

                }
              },"json");
              <%--$.ajax({--%>
              <%--  type: "POST",--%>
              <%--  url: "${ctx}/sharesongs_web/useraction",--%>
              <%--  data: {--%>
              <%--    userId: $("#input_text").val(),--%>
              <%--    password: $("#input_password").val()--%>
              <%--  },--%>
              <%--  success: function(res) {--%>
              <%--    if (res.code == 0) {--%>
              <%--      //记住用户名密码--%>
              <%--      remember_name();        //执行记住密码的操作--%>
              <%--      window.location.assign("${ctx}/pc/index/goForum.action");       //重新加载当前页面--%>
              <%--    } else {--%>
              <%--      $(".password_msg").text(res.msg);--%>
              <%--    }--%>
              <%--  },--%>
              <%--  error: function(res) {--%>
              <%--    $(".password_msg").text("网络错误");--%>
              <%--  }--%>
              <%--});--%>

            }
          });

        });


        // function checkuseradpasswd() {
        //   var user=document.getElementById("yourname").value;
        //   var passwd=document.getElementById("inputPassword").value;
        //
        //   if(user!=null&&passwd!=null&&user!=""&&passwd!=""){
        //     submit.setAttribute("flag","login");
        //
        //   }
        // }
      </script>


  </head>

  <body>

    <div class="container">

      <form   class="form-signin" method="post" action="#">

        <h2 class="form-signin-heading">欢迎登录</h2>
        <label  class="sr-only">Email address</label>
        <input type="text" id="yourname" name="yourname" class="form-control" placeholder="请输入用户名" required autofocus>
        <span id="yourname_span"></span>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="passwd" name="passwd" id="inputPassword" class="form-control" placeholder="请输入密码" required><span id="passwd_span"></span>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我（放心，它记不住你的~）
          </label>
        </div>
        <span id="error_info"></span>
        <button @click="add()" id="submitt" class="btn btn-lg btn-primary btn-block" type="button"  >点击登录</button>

      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
