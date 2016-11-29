<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>신세계 I&C 최강 신입사원</title>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/custom2.css" rel="stylesheet">

<script src="js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="js/pup-slider.js" type="text/javascript"></script>

<style>
.loginform {
	margin-top: 300px;
}
</style>

</head>
<body>
	<div class="container">
		<!-- 부트스트랩 container -->
 
				<div align='center'>
					<h1>
						<font color='blue'> 직원 근무지 현황 조회 </font>
					</h1>
				</div>

			<div class="loginform" align='center'>

					<!-- jsp 문법. 만약 로그인 정보가 없으면 아래를 출력 -->

					<form role="form" class="form-inline" action="login.inc"
						method="post">
						<div class="form-group">
							<label for="Name">아이디 </label> <input type="text"
								class="form-control" placeholder="아이디" name="id">
						</div>
						<br/><br/>
						<div class="form-group">

							<label for="Password">패스워드</label> <input type="password"
								class="form-control" placeholder="패스워드" name="pwd">
						</div>
						<br/><br/><br/> 
						<div class="form-group">
							<button type="submit" class="btn btn-primary">로그인</button>
							<a href="joinForm.inc" class="btn btn-default" role="button">
								회원가입 </a>
						</div>
					</form>
				
		</div> 
	</div>
	
    <script src="js/menu.js"></script>
    <script src="js/app.js"></script>

</body>
</html>