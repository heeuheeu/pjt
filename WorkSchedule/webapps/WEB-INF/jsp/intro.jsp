<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="./css/reset.css" rel="stylesheet" type="text/css">
    <link href="./css/intro.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <section>
      <div class="logo"><img src="./img/logo.jpg" style="width: 250px; height: 160px"/></div>
      
      <div class="sign-form">
        <form action="login.inc" method="post">
        <input type="text" placeholder="사번" name="empid"/>
        <input type="password" placeholder="PASSWORD" name="emppwd"/>
        <div class="checkbox-layout">
          <input type="checkbox"/>
          <label>로그인 상태 유지</label>
        </div>
        <input type="submit" value="LOGIN" style="margin-bottom: 10px">
        </form>   
          <form action="joinForm.inc" >
	   	  <input type="submit" value="SIGN UP" style="background-color: #3e3f44">
	      </form>
   </div>
    </section>
  </body>
</html>
