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
          <div class="profile"><img src="./img/${myinfo.empid}.gif"/></div>
          <div class="name">${myinfo.deptname}</div>
          <div class="name">${myinfo.empname} 담당</div>
      </div>
      
      <ul class="nav navmenu-nav btns">
      <div class="btns">
        <li><a href="calMove.inc" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>나의일정</a></li>
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
      <a class="navbar-brand" href="cardView.jsp" style="color: white; margin-left: 60px">SSG 워크플레이스</a>
    </div>

	<form role="form" method="post" name='dateForm'>
	    <!----상단---->
		<section id="top-bar">
			<!----날짜---->
				<div class="date">
					<input type="image" src="img/date-prev.png" onclick="prevDay()">
					 <a href="#" id="currDateView" class="date-text" style="color: black;"></a> 
					<input type="image" src="img/date-next.png" onclick="nextDay()">
					<input type="hidden" id="currDate">
				</div>
			<!----날짜----> 
				
			<!----view 버튼---->
			<div class="view-btn">
				<ul>
					<li style="display: inline;"><a href="#listView" data-toggle="tab" onclick="listImgOn()"> 
						<img src="img/list-icon-off.png" id="listIcon" /></a></li>
					<li style="display: inline;"><a href="#cardView" data-toggle="tab" onclick="cardImgOn()"> 
						<img src="img/card-icon-on.png" id="cardIcon" /></a></li>
				</ul>
			</div>
			<!----view 버튼---->

		</section>
		<!----상단---->
	</form>
	
	<!----카드---->

<div class="tab-content"> 
 	<div class="tab-pane fade in active" id="cardView"> 
	<section id="card-item">
	
		<article>
			<div class="item-lay">
				<div class="profile">
					<img src="./img/${myinfo.empid}.gif" onclick="show('${myinfo.empid}','${myinfo.empname}','${myinfo.empgrade}','${myinfo.deptname}',
					      '${myinfo.amloc}','${myinfo.amlocdetail}','${myinfo.pmloc}','${myinfo.pmlocdetail}','${myinfo.workdate}','${myinfo.empid}')">
				</div>
				<div class="name">${myinfo.empname} ${myinfo.empgrade}</div>
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
					<input type="image" src="img/${fav.empid}.png" 
					onclick="show('${fav.empid}','${fav.empname}','${fav.empgrade}','${fav.deptname}',
					      '${fav.amloc}','${fav.amlocdetail}','${fav.pmloc}','${fav.pmlocdetail}','${fav.workdate}','${fav.empid}')">
					
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
 	</div>

	<!----카드---->

	<!-- 리스트 뷰 -->
  	<div class="tab-pane fade" id="listView">

      <section id="list-item">
       <article onclick="show('${myinfo.empid}','${myinfo.empname}','${myinfo.empgrade}','${myinfo.deptname}',
					      '${myinfo.amloc}','${myinfo.amlocdetail}','${myinfo.pmloc}','${myinfo.pmlocdetail}','${myinfo.workdate}','${myinfo.empid}')">
           <div class="item-lay">
             <div class="name"> <b> ${myinfo.empname} ${myinfo.empgrade} </b>
              <br/>신세계아이앤씨<br/>
              ${myinfo.deptname}</div>
             <div class="ampm">
               <div class="am"><span>AM</span>${myinfo.amloc}</div>
               <div class="pm"><span>PM</span>${myinfo.pmloc}</div>
             </div>
           </div>
         </article>
	
	  <c:forEach items="${myfav}" var="fav">
		<article onclick="show('${fav.empid}','${fav.empname}','${fav.empgrade}','${fav.deptname}',
					      '${fav.amloc}','${fav.amlocdetail}','${fav.pmloc}','${fav.pmlocdetail}','${fav.workdate}','${fav.empid}')"> 
			<div class="item-lay">
             <div class="name"> <b> ${fav.empname} ${fav.empgrade} </b>
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
	<!--btn-->
	<div class="add-btn" style="position: fixed;">
		<a href="searchview.inc"><img src="img/add-user-btn-on.png" /></a>
	</div>
	<!----btn---->



<!---- Modal ---->
<!---- Modal ---->

 <!-- 1207수정:모달팝업 -->
 	<form role="form" id='updateForm' action = "update.inc">
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
				          	<span id="empgrade"></span>
				          </div>
				        </div>
				        
				        <div class="user-contact">
				          <a href=""><img src="img/contact-icon1.png"/></a>
				          <a href=""><img src="img/contact-icon2.png"/></a>
				          <a href=""><img src="img/contact-icon3.png"/></a>
				        </div>
				        
				        <div class="date">
				          <center><input type="text" name="editable" id="workdate" class="date-text" 
				          			style="background-color: transparent"></center>
				        </div>
				        
				        <div class="ampm">
				          <div class="am">
				            <span>AM</span>
				            <center><input type="text" name="editable" id="amloc" class="amloc" style="background-color: transparent"></center>
				            <center><input type="text" name="editable" id="amlocdetail" class="amlocdetail" 
				            style="background-color: transparent" placeholder="상세일정 입력칸"></center>
				          </div>
				          
				          <div class="pm">
				            <span>PM</span>
				            <center><input type="text" name="editable" id="pmloc" class="pmloc" style="background-color: transparent"></center>
				            <center><input type="text" name="editable" id="pmlocdetail" class="pmlocdetail" 
				            style="background-color: transparent" placeholder="상세일정 입력칸"></center>
				          </div>
				        </div>
				        
				      </div>
		      	</div>
		      </div>
		    </div>
		  </div>
	</form>
    
 <!-- 1207수정:모달팝업 끝-->

    
    <!----btn---->
	    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
     <script src="./js/flatpickr.js"></script>
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
		
		// 20161207추가
		
		function show(id,name,grade,dept,am,amdetail,pm,pmdetail,workdate,img){
			
			var strArray = workdate.split('-');
			var workdateForm = strArray[0]+"년 "+strArray[1]+"월 "+strArray[2]+"일";
			
			$("#empname").html(name);
		    $("#empgrade").html(grade);
		    $("#empdept").html(dept);
		    
		    $("#amloc").val(am);
		    $("#amlocdetail").val(amdetail);
		    $("#pmloc").val(pm);
		    $("#pmlocdetail").val(pmdetail);	
		    $("#workdate").val(workdateForm);	
		    $("#empprofile").prop("src","img/"+img+".png");	

			$("#myModal").modal('show');
		    
			
			if(id=="${myinfo.empid}") {
				document.getElementById("editbtn").style.visibility = "visible";
				//$("#workdate").removeAttr("disabled");
				$("#workdate").removeAttr("disabled");
				$("#amloc").removeAttr("disabled");
				$("#amlocdetail").removeAttr("disabled");
				$("#pmloc").removeAttr("disabled");
				$("#pmlocdetail").removeAttr("disabled");
				flatpickr('#workdate',{
					// 팝업달력
					defaultDate: workdate
				});
				
			}else{
				document.getElementById("editbtn").style.visibility = "hidden";
				//$("#workdate").prop("readyonly" , "readonly");
				$("#workdate").prop("disabled","true");
				$("#amloc").prop("disabled","true");
				$("#amlocdetail").prop("disabled","true");
				$("#pmloc").prop("disabled","true");
				$("#pmlocdetail").prop("disabled","true");
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
		
        function listImgOn(){
            document.getElementById("listIcon").src="img/list-icon-on.png";
            document.getElementById("cardIcon").src="img/card-icon-off.png";
        }
           
        function cardImgOn(){
            document.getElementById("listIcon").src="img/list-icon-off.png";
            document.getElementById("cardIcon").src="img/card-icon-on.png";
        }
             
	</script>
    
  </body>
</html>
