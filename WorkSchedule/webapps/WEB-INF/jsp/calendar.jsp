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
	    <link href="./css/flatpickr.css" rel="stylesheet">
	    
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
          <div class="name">${myinfo.empname }<%--  ${myinfo.empgrade } --%></div>
      </div>
      
      <ul class="nav navmenu-nav btns">
      <div class="btns">
        <li><a href="calMove.inc" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>나의일정</a></li>
        <li><a href="updateview.inc" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>내 정보수정</a></li>
        <li><a href="logout.inc" class="btn2" style="color: white;"><img src="img/side-icon2.png"/>로그아웃</a></li>
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
	 <div id="workplace">
	       <a class="navbar-brand" href="user.inc" style="color: white;">SSG워크플레이스</a>
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
								<img src="img/close-icon.png"/></button>
								
								<button type="button" class="close" aria-hidden="true" 
									id="editbtn" style="float: left;" onclick="update()">
								<img src="img/edit-icon.png"/></button>
								
						</div >		
						<div class="modal-body" >
						<!---- 모달 내용---->		
						
					      <div class="popup-content">
					        <div class="user-info">
					          <div class="profile"><img id='empprofile' /></div>
					          <div class="name" id="empdept"></div>
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
					            
					            <select class="form-control" name="amloc" id="amloc" class="amloc" style="background-color: transparent">
									<option value="본사 10F">본사 10F</option>
									<option value="본사 13F">본사 13F</option>
									<option value="본사 14F">본사 14F</option>
									<option value="성수">성수</option>
									<option value="메사">메사</option>
									<option value="기타">기타</option>
									<option value="휴무">휴무</option>
								</select>
					            
					            </center>
					            <center><input type="text" name="amlocdetail" id="amlocdetail" class="amlocdetail" 
					            style="background-color: transparent"></center>
					          </div>
					          
					          <div class="pm">
					            <span>PM</span>
					            <center>
					            
					            <!-- <input type="text" name="pmloc" id="pmloc" class="pmloc" style="background-color: transparent"> -->
					            
					            <select class="form-control" name="pmloc" id="pmloc" class="pmloc" style="background-color: transparent;">
									<option value="본사 10F">본사 10F</option>
									<option value="본사 13F">본사 13F</option>
									<option value="본사 14F">본사 14F</option>
									<option value="성수">성수</option>
									<option value="메사">메사</option>
									<option value="기타">기타</option>
									<option value="휴무">휴무</option>
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
		<div id="dncalendar-container" data-toggle="modal" data-target="#myModal">
		</div>
		<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="./js/dncalendar.js"></script>
		<script src="./js/flatpickr.js"></script>
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
    	                		var img = workmodal.empid;
    	                			
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
    	    				    $("#empprofile").prop("src","img/"+img+".png");
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
		    				showNotes : false
		    		}
		    		
    				my_calendar.update(notes);
		    		
					/////////////////////////////////
    			}
    		});
			
			
		});
		
		function update() {
			var con_test = confirm("일정을 등록/수정 하시겠습니까?");
			if(con_test==true){
				$('#updateForm')
				.prop("action","updateCal.inc")
				.prop("method","post").submit();
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