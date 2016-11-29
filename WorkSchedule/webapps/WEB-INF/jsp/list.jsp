<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>신세계 I&C 최강 신입사원</title>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/custom2.css" rel="stylesheet">

<!-- sliding drawer -->
<link rel="stylesheet" href="styles/app.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

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
 
   	<div class="ms_menu_bar__wrapper">
        <ul class="ms_menu">
            <li class="ms_menu__trigger"><i class="fa fa-bars"></i></li>
        </ul>
    </div>
    <div class="ms_menu_body__wrapper hide-menu">
        <ul>
        	<li><a href="#"><img src="img/${login.empid}.png"></a></li>
         	<li>${login.deptid} ${login.empname} 담당</li> 
         	<li>${login.empid}</li>
            <li class="ms_menu__item"><i class="ms_menu__item-icon fa fa-circle-thin"></i> 나의 일정</li>
            <li class="ms_menu__item"><i class="ms_menu__item-icon fa fa-circle-thin"></i> 로그아웃</li>
        </ul>
    </div>
 
     <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
   <hr /> 
   
   <div align='center'>
   	<button type="button" class="btn btn-default"> 이전 </button>
    
    <script type="text/javascript">
	   	var date = new Date();
		var hour = date.getHours();
		var ampm;
		if(hour<12) {
			ampm="오전";
		}
		else{ 
			ampm="오후";
		}
		document.write(date.getFullYear()+"년 "+date.getMonth()+"월 "+date.getDate()+"일 "+ampm);
	</script>
	
    <button type="button" class="btn btn-default"> 다음 </button>
   </div> 
   
<br/><br/><br/> 
<!---------------------------------------------- 내 아이디 --------------------------------------------->
<div align='center'>
		   <ul>
		   <li><a href="#"><img src="img/${login.empid}.png"></a></li>
		   <li>${login.deptid} ${login.empname} 담당</li> 
		   <li>${login.empid}</li>
		   </ul>
</div>
<!--------------------------------------------------------------------------------------------------->
   
   
			<div class="loginform" align='center'>

				<c:if test="${login != null}">
	    	<p>${login.empname}님 환영합니다.</p>
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
	
    <script src="js/menu.js"></script>
    <script src="js/app.js"></script>
    
</body>
</html>