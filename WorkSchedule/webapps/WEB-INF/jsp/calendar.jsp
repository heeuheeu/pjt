<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>일정</title>
		
		<link href="./css/dncalendar-skin.css" rel="stylesheet" type="text/css" >
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
          <div class="profile"><img src="./img/${myinfo.empid}.png"/></div>
          <div class="name">${myinfo.deptname }</div>
          <div class="name">${myinfo.empname } 담당</div>
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
		          <div class="profile"><img src="img/profile.png"/></div>
		          <div class="name">홍길동 담당</div>
		        </div>
		        
		        <div class="user-contact">
		          <a href=""><img src="img/contact-icon1.png"/></a>
		          <a href=""><img src="img/contact-icon2.png"/></a>
		          <a href=""><img src="img/contact-icon3.png"/></a>
		        </div>
		        
		        <div class="date">
		          <a href=""><img src="img/date-prev.png"/></a>
		          <div class="date-text">2016년 12월 12일</div>
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
	
	<br>
		<div id="dncalendar-container" data-toggle="modal" data-target="#myModal">
		</div>
		<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="./js/dncalendar.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			$.ajax({
    			url : "calendar.inc",
    			type : "post",
    			dataType : "json",
    			success : function(scheduleAry){
    				
    				
    				var my_calendar = $("#dncalendar-container").dnCalendar({
    					
    					monthNames: [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ], 
    					monthNamesShort: [ 'Jan', 'Feb', 'Mar', 'Apr', 'Mey', 'Jun', 'Jul', 'Agu', 'Sep', 'Oct', 'Nov', 'Dec' ],
    					dayNames: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
    	                dayNamesShort: [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
    	                
    	                startWeek: 'sunday',
    	                
    	                // 해당 날짜 클릭 시 이벤트
    	                 dayClick: function(date, view) {
    	                	$('#dncalendar-container').dialog();
    	                }
    				});
    				
    				my_calendar.build();
    				
    				///////////////////////////////// 스케쥴 있는 날 표시

		    		alert(JSON.stringify(scheduleAry));
		    		
		    		var notes = {
		    				notes : scheduleAry,
		    				showNotes : false
		    		}
		    		
    				my_calendar.update(notes);
		    		
					/////////////////////////////////
    			}
    		});
			
			
		});
		</script>
		<!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
	</body>
</html>