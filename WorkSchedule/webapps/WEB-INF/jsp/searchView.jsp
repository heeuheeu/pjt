<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="./css/reset.css" rel="stylesheet" type="text/css">
    <link href="./css/searchView.css" rel="stylesheet" type="text/css">
    
         <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.css" rel="stylesheet">
    <link href="./css/jasny-bootstrap.min.css" rel="stylesheet">
	<link href="./css/sideMenu.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="./css/navmenu-push.css" rel="stylesheet">
  </head>
  <body> 

	<div class="navmenu navmenu-default navmenu-fixed-left offcanvas">
      
	  <div class="side-menu-content">
      <div class="user-info">
      <br>
          <div class="profile"><img src="img/profile.png"/></div>
          <div class="name">${myinfo.deptname}</div>
          <div class="name">${myinfo.empname} ${myinfo.empgrade}</div>
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
      
      <!-- check 버튼 -->      
      <a id="addBtn" href="addfavorite.inc"><img src="img/check.png" style="width: 42px; height: 36px; float: right; margin-top: 7px; margin-right: 8px"></a>
    </div>

    <!--상단-->
    
    <section id="search-bar">
      <form action="search.inc" method="post">
	      <input type="text" placeholder="이름 검색" name = "searchKeyword"/>
	      <input type="submit" value="검색"/>
	      <div class="path">
	        <div><a href="">신세계아이앤씨</a><span>></span></div>
	        <div><a href="">지원담당</a><span>></span></div>
	        <div><a href="">인사팀</a></div>
	      </div>
      </form>
    </section>
    <!--상단-->

    <!--리스트-->
    <section id="list-item">
    
	<div class="path" style="float: right; margin-top: 0px" >       
        <div><input type="checkbox" id="allChk">전체 선택/해제</div>
      </div> 
    

    <c:forEach items="${lists}" var="member">	   
		   <article>	        
		        <div class="item-lay">
		          <div class="profile"><img src="img/${member.empid}.png"/></div>
		          <div class="name">
		            ${member.empname} ${member.empgrade}<br/>
		            ${member.deptname}<br/>	       
		          </div>
       
		          <div class="check">	          
		            <input onchange="checkbox()" type="checkbox" value="${member.empid}" name="chk">
		            <label class="checkbox" for="Option"></label>
		          </div>
		        </div>
		      </article>	     
	     
	      </c:forEach>
      
    </section>
    
    <!--리스트-->
    
<!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">
    $("#allChk").on("click", function() {
		//prop() checked 속성

		if ($(this).prop("checked")) {
			$("input[name='chk']").each(function() {//배열에 같은 이름 여러개
				$(this).prop("checked", true);
			});
		} else {
			$("input[name='chk']").each(function() {//배열에 같은 이름 여러개
				$(this).prop("checked", false);
			});
		}

	});//allchk
	
	
	
	$("#addBtn").on("click", function(){	
		
		var checkArr = [];
		
		$("input[name='chk']:checked").each(function(i){
			checkArr.push($(this).val());
		});
		
		$.ajax({
			url: "addfavorite.inc",
			type: "post",
			dataType: "text",
			data: {
				valueArrTest: checkArr
			}
		});	
		
	});	
	

	
    </script>
  </body>
</html>
