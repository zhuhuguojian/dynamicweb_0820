<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>尚硅谷会员注册页面</title>
    <%@ include file="/WEB-INF/include/base.jsp"%>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" />
    <link rel="stylesheet" href="static/css/register.css" />
    <style type="text/css">
      .login_form {
        height: 420px;
        margin-top: 25px;
      }
    </style>
    <script type="text/javascript" >
      $(function () {
        /**
         * a)用户名、密码：只能是字母（大小写）、数字、_。6-18位。
         * b)邮箱：API中的标准验证。
         * c)验证码进行非空验证
         *
         *  /**
         * visibility【占位】
         *    hidden:隐藏
         *    visible:显示
         * display【不占位】
         *    none:隐藏
         *    block:显示
         */
        //定义用户名&密码正则规则
        var uapReg = /^\w{6,18}$/;
        //定义邮箱正则规则
        var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

        //定义验证用户名函数
        function checkUserName () {
          $("#unMsg").css("visibility","hidden");
          //验证用户名
          var unValue = $("#username").val();
          if(uapReg.test(unValue) == false){
            $("#unMsg").css("visibility","visible");
            return false;
          }
        }

        //验证密码
        function checkPwd(){
          $("#pwdMsg").css("visibility","hidden");
          //获取密码数据
          var pwdValue = $("#pwd").val();
          if(uapReg.test(pwdValue) == false){
            $("#pwdMsg").css("visibility","visible");
            return false;
          }
        }

        //验证两次密码是否一致
        function checkPwdTwo(){
          $("#pwdReMsg").css("visibility","hidden");
          //获取一次密码
          var pwdValue = $("#pwd").val();
          //获取二次密码
          var pwdReValue = $("#pwdRe").val();
          if(pwdValue != pwdReValue){
            $("#pwdReMsg").css("visibility","visible");
            return false;
          }

        }

        //验证邮箱是否合法
        function checkEmail(){
            $("#emailMsg").css("visibility","hidden");
            //获取邮箱数据
            var emailValue = $("#email").val();
            //验证邮箱是否合法
            if(emailReg.test(emailValue) == false){
                $("#emailMsg").css("visibility","visible");
                return false;
            }
        }


        //输入用户名后，验证用户名是否合法
        $("#username").change(checkUserName);
        $("#pwd").change(checkPwd);
        $("#pwdRe").change(checkPwdTwo);
        $("#email").change(checkEmail);

        //单击注册按钮，验证注册中信息
        $(".btn").click(checkUserName);
        $(".btn").click(checkPwd);
        $(".btn").click(checkPwdTwo);
        $(".btn").click(checkEmail);


        //注册失败提示信息【visibility: visible;】
        var msg = "<%=request.getAttribute("msg")%>";
        if(msg != "null"){
          $("#unMsg").css("visibility","visible");
        }
        
        $("#codeImg").click(function () {
            $(this).prop("src","KaptchaServlet?random="+Math.random())
        })

          var msgcode = "<%=request.getAttribute("msgcode")%>";
          if(msgcode != "null"){
              $("#codeMsg").css("visibility","visible");
          }
      });

    </script>
  </head>
  <body>
    <div id="login_header">
      <a href="index.jsp">
        <img class="logo_img" alt="" src="static/img/logo.gif" />
      </a>
    </div>

    <div class="login_banner">
      <div class="register_form">
        <h1>注册尚硅谷会员</h1>
        <form action="UserServlet?method=regist" method="post">
<%--          <input type="hidden" name="method" value="regist">--%>
          <div class="form-item">
            <div>
              <label>用户名称:</label>
<%--              <input value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>" id="username" name="username" type="text" placeholder="请输入用户名" />--%>
              <input value="${param.username}" id="username" name="username" type="text" placeholder="请输入用户名" />
            </div>
            <span id="unMsg" class="errMess">
<%--              <%=request.getAttribute("msg")==null?"用户名只能是字母（大小写）、数字、_。6-18位":request.getAttribute("msg")%>--%>
                ${requestScope.msg==null?"用户名只能是字母（大小写）、数字、_。6-18位":requestScope.msg}
            </span>
          </div>
          <div class="form-item">
            <div>
              <label>用户密码:</label>
              <input id="pwd" name="pwd" type="password" placeholder="请输入密码" />
            </div>
            <span id="pwdMsg" class="errMess">密码只能是字母（大小写）、数字、_。6-18位。</span>
          </div>
          <div class="form-item">
            <div>
              <label>确认密码:</label>
              <input id="pwdRe" type="password" placeholder="请输入确认密码" />
            </div>
            <span id="pwdReMsg" class="errMess">密码两次输入不一致,请重新输入！</span>
          </div>
          <div class="form-item">
            <div>
              <label>用户邮箱:</label>
              <input value="${param.email}" id="email" name="email" type="text" placeholder="请输入邮箱" />
            </div>
            <span id="emailMsg" class="errMess">请输入正确的邮箱格式</span>
          </div>
          <div class="form-item">
            <div>
              <label>验证码:</label>
              <div class="verify">
                <input name="code" type="text"  placeholder="" />
                <img  id="codeImg" src="KaptchaServlet" alt="" style="width: 110px; height: 45px"/>
              </div>
            </div>
            <span id="codeMsg" class="errMess">${requestScope.msgcode==null?"请输入正确的验证码":requestScope.msgcode}</span>
          </div>
<!--          <button class="btn">注册</button>-->
          <input type="submit" value="注册" class="btn"/>
        </form>
      </div>
    </div>
    <div id="bottom">
      <span>
        尚硅谷书城.Copyright &copy;2015
      </span>
    </div>
  </body>
</html>
