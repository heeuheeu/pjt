<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>일정</title>
		
		<link href="./css/dncalendar-skin.css" rel="stylesheet" type="text/css" >
		<link href="./css/popup.css" rel="stylesheet" type="text/css">
		<link href="./css/view.css" rel="stylesheet">
		
			      <!-- Bootstrap core CSS -->
	    <link href="./css/bootstrap.css" rel="stylesheet">
	    <link href="./css/jasny-bootstrap.min.css" rel="stylesheet">
		<link href="./css/sideMenu.css" rel="stylesheet" type="text/css">
	
	    <!-- Custom styles for this template -->
	    <link href="./css/navmenu-push.css" rel="stylesheet">
	    <link href="./css/flatpickr.css" rel="stylesheet">
	    <link href="./css/font-awesome.css" rel="stylesheet" type="text/css" media="screen">
	
	    
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
          <div class="profile"><img src="resources/${myinfo.empimg}"/></div>
          <div class="dept">${myinfo.deptname }</div>
          <div class="name">${myinfo.empname }<%--  ${myinfo.empgrade } --%></div>
      </div>
      
      <ul class="nav navmenu-nav btns">
      	<div class="btns">
		        <li><a href="calMove.inc" class="btn1" style="color: white;"><i class="fa fa-user-circle" style="font-size: 25px;  margin-right:15px"></i><span style="font-size: 1.1em">나의일정</span></a></li>
		        <li><a href="updateview.inc" class="btn1" style="color: white"><i class="fa fa-calendar-check-o" aria-hidden="true" style="font-size: 25px;margin-right:15px"></i><span style="font-size: 1.1em">정보수정</span></a></li>
		        <li><a href="logout.inc" class="btn2" style="color: white"><i class="fa fa-sign-out" aria-hidden="true" style="font-size: 25px; margin-right:15px"></i><span style="font-size: 1.1em">로그아웃</span></a></li>
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
	 <div class="actionbar">
	       <div class="workplace"><a class="navbar-brand" href="user.inc" style="color: white;">SSG워크플레이스</a></div>
	       <div class="refresh"><a href="#" onclick="refresh()"><i class="fa fa-refresh fa-spin fa-3x fa-fw" style="color:gray; font-size: 25px; margin-top:5px"></i></a></div>
	 </div>
    </div>
  
    
    <!-- Modal -->
	<!-- Modal -->

	<!-- 1207수정:모달팝업 -->
	 	<form role="form" id='updateForm'>
			<div class="modal fade popup" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								<i class="fa fa-times" aria-hidden="true"></i></button>
								
								<button type="button" class="close" aria-hidden="true" 
									id="editbtn" style="float: left;" onclick="update()">
								<i class="fa fa-check" aria-hidden="true"></i></button>
								
						</div >		
						<div class="modal-body" >
						<!---- 모달 내용---->		
						
					      <div class="popup-content">
					        <div class="user-info">
					          <div class="profile"><img id='empimg' /></div>
					          <div class="dept" id="empdept"></div>
					          <div class="name"> 
					          	<span id="empname"></span>
					          <!-- 	<span id="empgrade"></span> -->
					          </div>
					        </div>
					        
					        <div class="user-contact">
					          <!-- <a href=""><img src="img/contact-icon1.png"/></a>
					          <a href=""><img src="img/contact-icon2.png"/></a>
					          <a href=""><img src="img/contact-icon3.png"/></a> -->
					        </div>
					        
					        <div class="date">
					          <center><input type="text" name="workdate" id="workdateView" class="date-text" 
					          			style="background-color: transparent" readonly="readonly"></center>
					        </div>
					        
					        <div class="ampm">
					          <div class="am">
					            <span>AM</span>
					            <center>
					            
					            <!-- <input type="text" name="amloc" id="amloc" class="amloc" style="background-color: transparent"> -->
					            
					            <select name="amloc" id="amloc" class="amloc" style="background-color: transparent">
									<c:forEach items="${locList}" var="locList"> 
							         <option value="${locList.locname}">${locList.locname}</option>
							        </c:forEach>
							        <option value="교육">교육</option>
			        				<option value="DAY OFF">DAY OFF</option>
								</select>
					            
					            </center>
					            <center><input type="text" name="amlocdetail" id="amlocdetail" class="amlocdetail" 
					            style="background-color: transparent"></center>
					          </div>
					          
					          <div class="pm">
					            <span>PM</span>
					            <center>
					            
					            <!-- <input type="text" name="pmloc" id="pmloc" class="pmloc" style="background-color: transparent"> -->
					            
					            <select name="pmloc" id="pmloc" class="pmloc" style="background-color: transparent;">
									<c:forEach items="${locList}" var="locList"> 
							         <option value="${locList.locname}">${locList.locname}</option>
							        </c:forEach>
							        <option value="교육">교육</option>
			        				<option value="DAY OFF">DAY OFF</option>
								</select>
					            
					            </center>
					            <center><input type="text" name="pmlocdetail" id="pmlocdetail" class="pmlocdetail" 
					            style="background-color: transparent"></center>
					          </div>
					        </div>
					        
					        	<!-- 컨트롤러 VO에 넘길 값 -->
					        
					        <input type="hidden" name="empid" id="empid">
					        <input type="hidden" name="currdate" id="currDateUpdate">
					        	
					        	<!-- 컨트롤러 VO에 넘길 값 끝 -->
					        	
					      </div>
			      	</div>
			      </div>
			    </div>
			  </div>
		</form>
	    
	 <!-- 1207수정:모달팝업 끝-->
	
	<br>
		<div id="dncalendar-container" data-toggle="modal" >
		
		</div>
		
		
		
		<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="./js/dncalendar.js"></script>
		<script src="./js/flatpickr.js"></script>
		<script src="https://use.fontawesome.com/492f5222e8.js"></script>
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
    	                	var clickedDay = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    	                	var empid = "${myinfo.empid}";
    	                	
    	                	$.ajax({
    	                		url :"calModal.inc",
    	                		type : "post",
    	                		dataType : "json",
    	                		data : {"clickday":clickedDay, "empid":empid},
    	                		success : function(workmodal){
    	                			
    	                		// id,name,grade,dept,am,amdetail,pm,pmdetail,workdate,img
    	                		
    	                		// JSON으로 받아온 값 변수 설정 
    	                		
    	                		var id = workmodal.empid;
    	                		var name = workmodal.empname;
    	                		/* var grade = workmodal.empgrade; */
    	                		var dept = workmodal.deptname;
    	                		var am = workmodal.amloc;
    	                		var amdetail = workmodal.amlocdetail;
    	                		var pm = workmodal.pmloc;
    	                		var pmdetail = workmodal.pmlocdetail;
    	                		var workdate = workmodal.workdate;
    	                		var img = workmodal.empimg;
    	                			
    	                		// 모달에 값 심기
    	                		
    	                		var strArray = workdate.split('-');
    	    					var workdateForm = strArray[0]+"년 "+strArray[1]+"월 "+strArray[2]+"일";
    	    					
    	    					$("#empname").html(name);
    	    				    /* $("#empgrade").html(grade); */
    	    				    $("#empdept").html(dept);
    	    				    
    	    				    var amRow = document.getElementById("amloc").options.length;
    	    				    var pmRow = document.getElementById("pmloc").options.length;
    	    				    
    	    				    for(var i=0;i<amRow;i++){
    	    				    	if (document.getElementById("amloc").options[i].value == am) {
    	    				            document.getElementById("amloc").options[i].selected = "selected";
    	    				        }
    	    				    }
    	    				    
    	    				    for(var i=0;i<pmRow;i++){
    	    				    	if (document.getElementById("pmloc").options[i].value == pm) {
    	    				            document.getElementById("pmloc").options[i].selected = "selected";
    	    				        }
    	    				    }
    	    				    
    	    				    $("#amlocdetail").val(amdetail);
    	    				    $("#pmlocdetail").val(pmdetail);	
    	    				    $("#workdateView").val(workdateForm);
    	    				    $("#empimg").prop("src","resources/"+img); //////////////////////////////////////////// 이미지 삽입
    	    				    $("#empid").val(id);
    	    				    $("#currDateUpdate").val(clickedDay);
    	    		
    	    					$("#myModal").modal('show');
    	    					
    	    					if(id=="${myinfo.empid}") {
    	    						document.getElementById("editbtn").style.visibility = "visible";
    	    						$("#workdateView").removeAttr("disabled");
    	    						$("#amloc").removeAttr("disabled");
    	    						$("#amlocdetail").removeAttr("disabled");
    	    						$("#pmloc").removeAttr("disabled");
    	    						$("#pmlocdetail").removeAttr("disabled");
    	    						
    	    						/* flatpickr('#workdateView',{
    	    							// 팝업달력
    	    							defaultDate: workdate
    	    						}); */
    	    						
    	    					}else{
    	    						document.getElementById("editbtn").style.visibility = "hidden";
    	    						$("#workdateView").prop("disabled","true");
    	    						$("#amloc").prop("disabled","true");
    	    						$("#amlocdetail").prop("disabled","true");
    	    						$("#pmloc").prop("disabled","true");
    	    						$("#pmlocdetail").prop("disabled","true");
    	    					}
    	    		
    	                		}
    	                	})
    	                	
    	                }
    				});
    				
    				my_calendar.build();
    				
    				///////////////////////////////// 스케쥴 있는 날 표시

		    		//alert(JSON.stringify(scheduleAry));
		    		
		    		var notes = {
		    				notes : scheduleAry,
		    				showNotes : true
		    		}
		    		
    				my_calendar.update(notes);
		    		
					/////////////////////////////////
    			}
    		});
			
			
		});


 		function update() {
 			   var con_test = confirm("일정을 등록/수정 하시겠습니까?");
 			   if(con_test){
 			    
 			    if( ($("#amloc").val() == "기타" && $("#amlocdetail").val() == "") 
 			    		|| ($("#pmloc").val() == "기타" && $("#pmlocdetail").val() == "")){
 			     	alert("기타 입력칸의 상세 일정을 입력해주세요.");   
 			     return false ;
 			    }
 			    
 			    $('#updateForm')
 			    .prop("action","update.inc")
 			    .prop("method","post").submit();
 			   alert("일정이 등록되었습니다.");
 			    
 			   }else{
 			    // cancel
 			   }
 		}
 		
		</script>
		<!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
	</body>
</html>