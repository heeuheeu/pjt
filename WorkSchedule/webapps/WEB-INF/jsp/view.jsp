<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="./css/reset.css" rel="stylesheet" type="text/css">
    <link href="./css/cardView.css" rel="stylesheet" type="text/css">
    <link href="./css/popup.css" rel="stylesheet" type="text/css">
        <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.css" rel="stylesheet">
    <link href="./css/jasny-bootstrap.min.css" rel="stylesheet">
	<link href="./css/sideMenu.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="./css/navmenu-push.css" rel="stylesheet">
    
    <style>

		.modal-header {
		    height: 40px;
		}
		
	</style>
    
  </head>
  <body>
	
	<div class="navmenu navmenu-default navmenu-fixed-left offcanvas">
      
	  <div class="side-menu-content">
      <div class="user-info">
      <br>
          <div class="profile"><img src="./img/${myinfo.empid}.gif"/></div>
          <div class="name">${myinfo.deptname}</div>
          <div class="name">${myinfo.empname} 담당</div>
      </div>
      
      <ul class="nav navmenu-nav btns">
      <div class="btns">
        <li><a href="calendar.jsp" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>나의일정</a></li>
        <li><a href="intro.jsp" class="btn2" style="color: white;"><img src="img/side-icon2.png"/>로그아웃</a></li>
      </div>
      </ul>

      </div>
    </div>
      
	
    <div class="navbar navbar-default navbar-fixed-top" style="background-color: #3e3f44; text-align: center">
      <button type="button" class="navbar-toggle" data-toggle="offcanvas" data-target=".navmenu" data-canvas="body">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="cardView.jsp" style="color: white; margin-left: 60px">SSG 워크플레이스</a>
    </div>
    
	<form name='dateForm' method='post'>
	    <!--상단-->
		<section id="top-bar">
			<!--날짜-->
				<div class="date">
					<input type="image" src="img/date-prev.png" onclick="prevDay()">
					<a href="#" id="currDateView" class="date-text" style="color: black;"></a> <input type="image"
						src="img/date-next.png" onclick="nextDay()">
					<input type="hidden" id="currDate">
				</div>
				<!--날짜-->
	
			<!--view 버튼-->
			<div class="view-btn">
				<a href="listView.jsp"><img src="img/list-icon-off.png" /></a> <a
					href="cardView.jsp"><img src="img/card-icon-on.png" /></a>
			</div>
			<!--view 버튼-->
		</section>
		<!--상단-->
	</form>
	
	<!--카드-->
	<section id="card-item">
		<article>
			<div class="item-lay">
				<div class="profile">
					<img src="./img/${myinfo.empid}.gif">
				</div>
				<div class="name">${myinfo.empname} 담당</div>
				<div class="ampm">
					<div class="am">
						<span>AM</span>${myinfo.amloc}</div>
					<div class="pm">
						<span>PM</span>${myinfo.pmloc}</div>
				</div>
			</div>
		</article>
	
	  <c:forEach items="${myfav}" var="fav">
		<article>
			<div class="item-lay">
				<div class="profile" >
					<img src="img/hyun.PNG" data-toggle="modal" data-target="#myModal" />
				</div>
				<div class="name">${fav.empname} ${fav.empgrade}</div>
				<div class="ampm">
					<div class="am">
						<span>AM</span>${fav.amloc}
					</div>
					<div class="pm">
						<span>PM</span>${fav.pmloc}
					</div>
				</div>
			</div>
		</article>
	  </c:forEach>
	  
	</section>
	<!--카드-->

	<!--btn-->
	<div class="add-btn" style="position: fixed;">
		<a href="searchView.jsp"><img src="img/add-user-btn-on.png" /></a>
	</div>
	<!--btn-->



<!-- Modal -->
<!-- Modal -->

<div class="modal fade popup" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"><img src="img/close-icon.png"/></button>
					
					<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" style="float: left;"><img src="img/edit-icon.png"/></button>
					
			</div >
			
			<div class="modal-body" >
			<!-- 모달 내용-->		
			
		      <div class="popup-content">
		        <div class="user-info">
		          <div class="profile"><img src="img/hyun.PNG"/></div>
		          <div class="name">손정현 상무</div>
		        </div>
		        
		        <div class="user-contact">
		          <a href=""><img src="img/contact-icon1.png"/></a>
		          <a href=""><img src="img/contact-icon2.png"/></a>
		          <a href=""><img src="img/contact-icon3.png"/></a>
		        </div>
		        
		        <div class="date">
		          <a href=""><img src="img/date-prev.png"/></a>
		          <div class="date-text"><a href="calendar.jsp" style="text-decoration: none; color: black;">2016년 12월 1일</a></div>
		          <a href=""><img src="img/date-next.png"/></a>
		        </div>
		        
		        <div class="ampm">
		          <div class="am">
		            <span>AM</span>
		            <b>성수</b>
		            <font>이마트팀 미팅</font>
		          </div>
		          
		          <div class="pm">
		            <span>PM</span>
		            <b>본사 10F</b>
		            <font>본사 근무</font>
		          </div>
		        </div>
		      </div>
      	</div>
      </div>
    </div>
  </div>
    
    <!--btn-->
	    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    
    
    <script type="text/javascript">
		var currDate = new Date();
		var pageDate = currDate;

		var draw = function() {
			var y = pageDate.getFullYear();
			var m = pageDate.getMonth();
			var d = pageDate.getDate();

			var date = y + "-" + (m + 1) + "-" + d;
			var dateView = y + "년 " + (m + 1) + "월 " + d + "일";
			$("#currDate").html(date);
			$("#currDateView").html(dateView);
		}

		draw();

		function prevDay() {
			var caleMonth, caleDay, caleYear;
			var loadDt = new Date(pageDate);

			var v = new Date(Date.parse(loadDt) - 1 * 1000 * 60 * 60 * 24);
			caleYear = v.getFullYear();
			if (v.getMonth() < 9) {
				caleMonth = '0' + (v.getMonth() + 1);
			} else {
				caleMonth = v.getMonth() + 1;
			}
			if (v.getDate() < 10) {
				caleDay = '0' + (v.getDate());
			} else {
				caleDay = v.getDate();
			}

			var date = caleYear + "-" + caleMonth + "-" + caleDay;
			$("#currDate").html(date);
			document.dateForm.action = 'calDay.inc?currDate='
					+ $("#currDate").text();
			document.dateForm.submit();
		}

		function nextDay() {
			var caleMonth, caleDay, caleYear;
			var loadDt = new Date(pageDate);

			var v = new Date(Date.parse(loadDt) + 1 * 1000 * 60 * 60 * 24);
			caleYear = v.getFullYear();
			if (v.getMonth() < 9) {
				caleMonth = '0' + (v.getMonth() + 1);
			} else {
				caleMonth = v.getMonth() + 1;
			}
			if (v.getDate() < 10) {
				caleDay = '0' + (v.getDate());
			} else {
				caleDay = v.getDate();
			}

			var date = caleYear + "-" + caleMonth + "-" + caleDay;
			$("#currDate").html(date);
			document.dateForm.action = 'calDay.inc?currDate='
					+ $("#currDate").text();
			document.dateForm.submit();
		}
	</script>
    
  </body>
</html>
