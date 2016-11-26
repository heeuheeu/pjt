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

<link href="style/pup-slider.css" rel="stylesheet" type="text/css" />
<link href="style/demo.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="js/pup-slider.js" type="text/javascript"></script>

<script type="text/javascript">
        $(document).ready(function() {
            $('#right-demo').pupslider({ stick: 'right', speed: 500, opacity: 0.9 });
            $('#left-demo').pupslider({ stick: 'left', speed: 500, opacity: 0.9 });

            $('#left-demo').css('display', 'none');

            $('#left-align').click(function() {
                $('#right-demo').css('display', 'none');
                $('#left-demo').css('display', '');
            });
            $('#right-align').click(function() {
                $('#left-demo').css('display', 'none');
                $('#right-demo').css('display', '');
            });

            $('.name').focus(function() {
                $(this).val('');
            }).blur(function() {
                if ($(this).val() == '') $(this).val('Name');
            });

        });
    </script>


</head>
<body>
	<div class="container">
		<!-- 부트스트랩 container -->

		<div id="wrapper">
			
			<div class="floatleft">
				<div align='center'>
					<h1>
						<font color='blue'> 직원 근무지 현황 조회 </font>
					</h1>
					<hr />
				</div>
			</div>

			<!--right demo-->
			<div id="right-demo" class="contents">
				<!--pushup content bar statrs here-->
				<div class="btn-show">Show</div>
				<div class="pushup-form">
					<div class="btn-close">Close</div>
					<div class="clear"></div>
					<div class="cform">
						<div class="address">
							<p>
								<img src="img/1.png" alt="" />
							</p>
							<p>
								<b>정서희 담당</b><br /> 플랫폼서비스팀<br />
							</p>
						</div>

						<br /> <br />
						<div class="contact-form">
							<form action="index.htm" method="get,post">
								<h4>Contact Form</h4>
								<p>
									<input type="text" class="name" value="Name" />
								</p>
								<p class="clear">
									<input type="submit" value="Send" class="submit" />
								</p>
							</form>
						</div>
					</div>
				</div>
				<!--pushup content bar ends here-->
			</div>

			<div align='right'>
				<c:if test="${login == null}">
					<!-- jsp 문법. 만약 로그인 정보가 없으면 아래를 출력 -->

					<form role="form" class="form-inline" action="login.inc"
						method="post">
						<div class="form-group">
							<label for="Name">아이디</label> <input type="text"
								class="form-control" placeholder="아이디" name="id">
						</div>
						<div class="form-group">

							<label for="Password">패스워드</label> <input type="password"
								class="form-control" placeholder="패스워드" name="pwd">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">로그인</button>
							<a href="joinForm.inc" class="btn btn-default" role="button">
								회원가입 </a>
						</div>
					</form>
				</c:if>
				<c:if test="${login != null}">
	    	${login.name}님 환영합니다.
	    	 <a href="logout.inc" class="btn btn-default" role="button">
						로그아웃 </a>
					<a href="updateForm.inc" class="btn btn-default" role="button">
						회원정보수정 </a>


					<hr />
					<a href="list.inc" class="btn btn-default" role="button"> 게시물
						보러가기 </a>
					<a href="book/list.inc" class="btn btn-default" role="button">
						책 목록 보러가기 </a>
				</c:if>
			</div>



		</div>
	</div>
</body>
</html>