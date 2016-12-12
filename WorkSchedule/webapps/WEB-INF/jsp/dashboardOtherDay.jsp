<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/dashboard.css" rel="stylesheet" type="text/css">
  </head>
  <body>

<form role="form" name='dateForm' method="post">
    <!--상단-->
    <section id="top-bar">
      <!--날짜-->
      <div class="date">
        <div>팀원 현재 상태보기</div>
        <div class="group-title">${myinfo.deptname}</div>
        <input type="image" src="img/date-prev.png" onclick="prevDay()">
        <a id="currDateView" class="date-text" style="color: black;"></a>
		<input type="image" src="img/date-next.png" onclick="nextDay()">
		<input type="hidden" id="currDate">
      </div>
      <!--날짜-->
    </section>
    <!--상단-->
</form>

    <!--카드-->
    <section id="card-item">
    
      <c:forEach items="${myfav}" var="fav">
		<article>
		
			<div class="item-lay status1">
	          <div class="profile"><img src="img/${fav.empid}.png" /></div>
	          <div class="name">${fav.empname}<%--  ${fav.empgrade} --%></div>
	          <div class="status">
	            <div>
	            <c:choose>
	            	<c:when test="${date.hours<=12}"> ${fav.amloc} </c:when>
	            	<c:when test="${date.hours>12}"> ${fav.pmloc} </c:when>	   
	            </c:choose>   	
	          	</div>
	          </div>
	        </div>
				
		</article>
	  </c:forEach>
    
    
  
    </section>
    <!--카드-->
	<script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">
	
    var pageDate = "${day}";
	var strArray = pageDate.split('-');
	var y = strArray[0];
	var m = " ";
	var d = " ";

	if (strArray[1].substring(0, 1) == '0') {
		m = strArray[1].substring(1, 2);
	} else {
		m = strArray[1];
	}

	if (strArray[2].substring(0, 1) == '0') {
		d = strArray[2].substring(1, 2);
	} else {
		d = strArray[2];
	}

	var pageDateView = y + "년 " + m + "월 " + d + "일";

	var draw = function() {
		$("#currDateView").html(pageDateView);
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
		document.dateForm.action = 'calDayDash.inc?currDate='
				+ $("#currDate").text();
		document.submit();
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
		document.dateForm.action = 'calDayDash.inc?currDate='
				+ $("#currDate").text();
		document.submit();
	}

        
    
	
</script>
  </body>
</html>
