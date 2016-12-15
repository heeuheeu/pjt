<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="./css/reset.css" rel="stylesheet" type="text/css">
    <link href="./css/listView.css" rel="stylesheet" type="text/css">
    <link href="./css/cardView.css" rel="stylesheet" type="text/css">
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
				<div class="profile">
					<img src="resources/${myinfo.empimg}" />
				</div>
				<div class="name">${myinfo.deptname}</div>
				<div class="name">${myinfo.empname} <%-- ${myinfo.empgrade} --%></div>
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

	<form role="form" method="post" name='dateForm'>
		<!--상단-->
		<section id="top-bar"> <!--날짜-->
		<div class="date">
			<input type="image" src="img/date-prev.png" onclick="prevDay()">
			<a href="calMove.inc" id="currDateView" class="date-text" style="color: black;"></a>
			<input type="image" src="img/date-next.png" onclick="nextDay()">
			<input type="hidden" id="currDate">
		</div>
		<!--날짜--> 
			
		<!----view 버튼---->
			<div class="view-btn">
				<ul>
					<li style="display: inline;"><a href="#listView" data-toggle="tab" onclick="listImgOn()"> 
						<img src="img/list-icon-off.png" id="listIcon" /></a></li>
					<li style="display: inline;"><a href="#cardView" data-toggle="tab" onclick="cardImgOn()"> 
						<img src="img/card-icon-off.png" id="cardIcon" /></a></li>
				</ul>
			</div>
		<!----view 버튼---->
		
		</section>
		<!--상단-->
	

	<!--카드-->
	
<div class="tab-content"> 
 	<div class="tab-pane fade" id="cardView"> 
	
	<section id="card-item"> 
	
		
		<article>
			<div class="item-lay">
				<div class="profile">
					<img src="resources/${myinfo.empimg}" onclick="show('${myinfo.empimg}','${myinfo.empid}','${myinfo.empname}','${myinfo.deptname}',
					      '${myinfo.amloc}','${myinfo.amlocdetail}','${myinfo.pmloc}','${myinfo.pmlocdetail}',
					      '${myinfo.workdate}','${myinfo.empphone}','${myinfo.empmail}')">
				</div>
				<div class="name">${myinfo.empname} <%-- ${myinfo.empgrade} --%></div>
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
					<img src="resources/${fav.empimg}" 
					onclick="show('${fav.empimg}','${fav.empid}','${fav.empname}','${fav.deptname}',
					      '${fav.amloc}','${fav.amlocdetail}','${fav.pmloc}','${fav.pmlocdetail}',
					      '${fav.workdate}','${fav.empphone}','${fav.empmail}')">
					
				</div>
				<div class="name">${fav.empname} <%-- ${fav.empgrade} --%></div>
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
	</div>
	<!--카드-->
	
	<!-- 리스트 뷰 -->
  	<div class="tab-pane fade" id="listView">

      <section id="list-item">
       <article onclick="show('${myinfo.empimg}','${myinfo.empid}','${myinfo.empname}','${myinfo.deptname}',
					      '${myinfo.amloc}','${myinfo.amlocdetail}','${myinfo.pmloc}','${myinfo.pmlocdetail}',
					      '${myinfo.workdate}','${myinfo.empphone}','${myinfo.empmail}')">
           <div class="item-lay">
             <div class="name"> <b> ${myinfo.empname}<%--  ${myinfo.empgrade}  --%></b>
              <br/>신세계아이앤씨<br/>
              ${myinfo.deptname}</div>
             <div class="ampm">
               <div class="am"><span>AM</span>${myinfo.amloc}</div>
               <div class="pm"><span>PM</span>${myinfo.pmloc}</div>
             </div>
           </div>
         </article>
	
	  <c:forEach items="${myfav}" var="fav">
		<article onclick="show('${fav.empimg}','${fav.empid}','${fav.empname}','${fav.deptname}',
					      '${fav.amloc}','${fav.amlocdetail}','${fav.pmloc}','${fav.pmlocdetail}',
					      '${fav.workdate}','${fav.empphone}','${fav.empmail}')"> 
			<div class="item-lay">
             <div class="name"> <b> ${fav.empname}<%--  ${fav.empgrade} --%> </b>
              <br/>신세계아이앤씨<br/>
              ${fav.deptname}</div>
             <div class="ampm">
               <div class="am"><span>AM</span>${fav.amloc}</div>
               <div class="pm"><span>PM</span>${fav.pmloc}</div>
             </div>
           </div>
		</article>
	  </c:forEach>
        
	</section>
  </div>
</div>

<input type="hidden" id="view" name="view">

</form>

	<!--btn-->
	<div class="add-btn" style="position: fixed;">
		<a href="searchview.inc"><img src="img/add-user-btn-on.png" /></a>
	</div>
	<!--btn-->



	<!-- Modal -->
	<!-- Modal -->

 <!-- 1207수정:모달팝업 -->
 	<form role="form" id='updateForm'>
		<div class="modal fade popup" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="modalClose()">
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
				          	<!-- <span id="empgrade"></span> -->
				          </div>
				        </div>
				        
				        <div class="user-contact">
				          <a href="" id="mail"><img src="img/contact-icon1.png" id="mailImg"/></a>
				          <a href="" id="tel"><img src="img/contact-icon2.png" id="telImg"/></a>
				          <a href="" id="sms"><img src="img/contact-icon3.png" id="smsImg"/></a>
				        </div>
				        
				        <div class="date">
				          <center><input type="text" name="workdate" id="workdateView" class="date-text" readonly="readonly" 
				          			style="background-color: transparent"></center>
				        </div>
				        
				        <div class="ampm">
				          <div class="am">
				            <span>AM</span>
				            <center>
				            
				            <!-- <input type="text" name="amloc" id="amloc" class="amloc" style="background-color: transparent"> -->
				            
				            <select name="amloc" id="amloc" class="amloc" style="background-color: transparent">
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
				            
				            <select name="pmloc" id="pmloc" class="pmloc" style="background-color: transparent;">
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



	<!--btn-->
	<!-- Bootstrap core JavaScript ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./js/jquery-1.10.2.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jasny-bootstrap.min.js"></script>
	<script src="./js/flatpickr.js"></script>
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

		///////////////////////////////////////////// show modal 
		// 20161207추가
		
		function show(img, id,name,dept,am,amdetail,pm,pmdetail,workdate,tel,mail){
			
			var strArray = workdate.split('-');
			var workdateForm = strArray[0]+"년 "+strArray[1]+"월 "+strArray[2]+"일";
			
			$("#empname").html(name);
		   /*  $("#empgrade").html(grade); */
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
		    $("#workdateView").val(pageDateView);	
		    $("#empprofile").prop("src","resources/"+img);
		    $("#empid").val(id);
		    $("#currDateUpdate").val(pageDate);
		    $("#mail").attr("href", "mailto: "+mail);
		    $("#tel").attr("href", "tel: "+tel);
		    $("#sms").attr("href", "sms: "+tel);

			if(id=="${myinfo.empid}") {
				document.getElementById("editbtn").style.visibility = "visible";
				//$("#workdateView").removeAttr("disabled");
				$("#amloc").removeAttr("disabled");
				$("#amlocdetail").removeAttr("disabled");
				$("#pmloc").removeAttr("disabled");
				$("#pmlocdetail").removeAttr("disabled");
				/* flatpickr('#workdateView',{
					// 팝업달력
					defaultDate: workdate
				}); */
				$("#mail").hide();
				$("#tel").hide();
				$("#sms").hide();
				
				
			}else{
				document.getElementById("editbtn").style.visibility = "hidden";
				//$("#workdateView").prop("disabled","true");
				$("#amloc").prop("disabled","true");
				$("#amlocdetail").prop("disabled","true");
				$("#pmloc").prop("disabled","true");
				$("#pmlocdetail").prop("disabled","true");
				$("#mail").show();
				$("#tel").show();
				$("#sms").show();
			}
		
			$("#myModal").modal('show');
		}
		
		function update() {
			var con_test = confirm("일정을 등록/수정 하시겠습니까?");
			if(con_test==true){
				$('#updateForm')
				.prop("action","update.inc")
				.prop("method","post").submit();
			}else{
				// cancel
			}
		}
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
			document.dateForm.action = 'calDay.inc?currDate='
					+ $("#currDate").text();
			document.submit();
		}
		
		
		//$("#cardView").addClass("in active");
		//$("#listView").removeClass("in active");
    
    	//$("#cardView").removeClass("in active");
    	//$("#listView").addClass("in active");
    	
    	if("${view}"=="cardView"){
    		document.getElementById("listIcon").src="img/list-icon-off.png";
            document.getElementById("cardIcon").src="img/card-icon-on.png";
          	$("#cardView").addClass("in active");
    		$("#listView").removeClass("in active");
            $("#view").val("cardView");
    	}else{
    		document.getElementById("listIcon").src="img/list-icon-on.png";
            document.getElementById("cardIcon").src="img/card-icon-off.png";
          	$("#cardView").removeClass("in active");
        	$("#listView").addClass("in active");
            $("#view").val("listView");
    	}
		
    	 function listImgOn(){
             document.getElementById("listIcon").src="img/list-icon-on.png";
             document.getElementById("cardIcon").src="img/card-icon-off.png";
             $("#view").val("listView");
         }
            
         function cardImgOn(){
             document.getElementById("listIcon").src="img/list-icon-off.png";
             document.getElementById("cardIcon").src="img/card-icon-on.png";
             $("#view").val("cardView");
         }
         
         function modalClose(){
        	 confirm("저장하시겠습니까?");
         }
	</script>

</body>
</html>

