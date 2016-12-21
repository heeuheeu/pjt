<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/dashboard.css" rel="stylesheet" type="text/css">
     <script  src="https://use.fontawesome.com/b7a0d3c992.js"></script>
    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
    <link href="./css/font-awesome.css" rel="stylesheet" type="text/css" media="screen">
  </head>
  
  <body OnLoad="restart()"> 
	<div class="dash-container"> 
	  
	<!--상단-->
	<section id="top-bar">
	
	
	<!--PM-->
	<i class="fa fa-moon-o" aria-hidden="true" style="font-size:100px; float:left" ></i>	
	<!--AM-->
	<i class="fa fa-sun-o" aria-hidden="true" style="font-size:100px; float:left" ></i>
	
	
		<div class="group-title">${myinfo.deptname}</div>
		
	    <!--날짜-->
	    <div class="date">
	    	<a class="prev-text" onclick="prevDay()"> < </a> 
	        <a class="date-text" id="currDateView"></a>
	        <a class="next-text" onclick="nextDay()"> > </a> 
			<input type="hidden" id="currDate">
	    </div>
	    <!--날짜-->
	

	</section>
	<!--상단-->
	
	
	
	<div id="dash-carousel">
	        <div id="carousel-example-generic" class="carousel slide">   
	             <div class="carousel-inner" id="carouselinner">
	              	  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
		                <span class="icon-prev"></span>
		              </a>
		              <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
		                <span class="icon-next"></span>
		              </a>    
	          	</div>	          	
	        </div> 
	  </div>	  
	</div>
	
	
	  
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.min.js"></script>
    <script>
      $('.carousel').carousel({
    	  interval: 7000
    	});
      
      $(document).ready(function(){
		  var lists = [] ; 
		  <c:forEach items="${myfav}" var="fav">	
		  	var	empid = "${fav.empid}";
		  	var	empname = "${fav.empname}";
		  	var	amloc = "${fav.amloc}";
		  	var	pmloc = "${fav.pmloc}";
		  	var amlocdetail = "${fav.amlocdetail}";
		  	var pmlocdetail = "${fav.pmlocdetail}";
		  	var empimg = "${fav.empimg}";
		  	var obj = {empid : empid , empname : empname , amloc : amloc , pmloc : pmloc, amlocdetail : amlocdetail, pmlocdetail : pmlocdetail, empimg : empimg};
		  	lists.push(obj);
		  </c:forEach>
		  console.log(lists);

		  
		  //AM PM
		  	var now = new Date();
		  	var nowHour = now.getHours();
		  	var AMPM = ""; 
		  	
		  	if(nowHour <12) { AMPM = "AM"; }
		  	else { AMPM = "PM"; }

		  var txt="<div class='item active'>";
		 	  txt +="<section id='card-item'>";
		  for(var i=0 ; i < lists.length ; i++) {
		/////////////////////////////////////////////////////////////////////////////////////////////	 
			  //background color
			    if(i<10) {		    			  		
			     	var color = "status7";
			  		
			  		//now: AM
			  		if(AMPM == "AM"){
				    	if(lists[i].amloc=='본사 9F' || lists[i].amloc=='본사 10F' || lists[i].amloc=='본사 14F' || lists[i].amloc=='본사 13F'){
				    		color = "status1"; //베이지
				    	}else if(lists[i].amloc=='메사' || lists[i].amloc=='성수' || lists[i].amloc=='스타벅스 본사' || lists[i].amloc=='SI 본사' || lists[i].amloc=='건설'){
				    		color = "status2"; //초록
				    	}else if(lists[i].amloc=='교육'){
				    		color = "status3"; //연두
				    	}else if(lists[i].amloc=='기타'){
				    		color = "status4"; //노랑
				    	}else if(lists[i].amloc=='구로'){
				    		color = "status5"; //빨강
				    	}else if(lists[i].amloc=='DAY OFF'){
				    		color = "status6"; //휴무
				    	}
				    
			  		}
			  		//now: PM
			  		else{
			  			if(lists[i].pmloc=='본사 9F' || lists[i].pmloc=='본사 10F' || lists[i].pmloc=='본사 14F' || lists[i].pmloc=='본사 13F'){
				    		color = "status1"; //베이지
				    	}else if(lists[i].pmloc=='메사' || lists[i].pmloc=='성수' || lists[i].pmloc=='스타벅스 본사' || lists[i].pmloc=='SI 본사' || lists[i].pmloc=='건설'){
				    		color = "status2"; //초록
				    	}else if(lists[i].pmloc=='교육'){
				    		color = "status3"; //연두
				    	}else if(lists[i].pmloc=='기타'){
				    		color = "status4"; //노랑
				    	}else if(lists[i].pmloc=='구로'){
				    		color = "status5"; //빨강
				    	}else if(lists[i].pmloc=='DAY OFF'){
				    		color = "status6"; //휴무
				    	}
			  		}		
			  		
			    	txt +="<article><div class='item-lay "+color+ "'>";
			    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"'></div>";
			   	
					///////////////////////////수정 은비///////////////////
			    	if(AMPM == "AM"){
				    	if(lists[i].amloc == "기타"){
				    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
				    	}else{
				    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>";
				    	}
			    	}
			    	
			    	if(AMPM == "PM"){
				    	if(lists[i].pmloc == "기타"){
				    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
				    	}else{
				    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
				    	}
			    	}
			    	/////////////////////수정 은비////////////////////////

			    	 
			    	txt +="</div></div></article>";
			    }
      			
      	   }
		   txt +="</section></div>" ;
      	   
      	   $("#carouselinner").append(txt);
      	   
      	   
  /////////////////////////////////////////////////////////////////////////////////////////////////////    	   
      	   if(lists.length > 9 && lists.length < 20) {
      		   
      		 var txt="<div class='item'>"; 
      			 txt +="<section id='card-item'>";
      		 for(var i=10 ; i < lists.length ; i++) {   
      			 
      			 
      			var color = "status7";
		  		
		  		//now: AM
		  		if(AMPM == "AM"){
		  			if(lists[i].amloc=='본사 9F' || lists[i].amloc=='본사 10F' || lists[i].amloc=='본사 14F' || lists[i].amloc=='본사 13F'){
			    		color = "status1"; //베이지
			    	}else if(lists[i].amloc=='메사' || lists[i].amloc=='성수' || lists[i].amloc=='스타벅스 본사' || lists[i].amloc=='SI 본사' || lists[i].amloc=='건설'){
			    		color = "status2"; //초록
			    	}else if(lists[i].amloc=='교육'){
			    		color = "status3"; //연두
			    	}else if(lists[i].amloc=='기타'){
			    		color = "status4"; //노랑
			    	}else if(lists[i].amloc=='구로'){
			    		color = "status5"; //빨강
			    	}else if(lists[i].amloc=='DAY OFF'){
			    		color = "status6"; //휴무
			    	}
			    
		  		}
		  		//now: PM
		  		else{
			  			if(lists[i].pmloc=='본사 9F' || lists[i].pmloc=='본사 10F' || lists[i].pmloc=='본사 14F' || lists[i].pmloc=='본사 13F'){
				    		color = "status1"; //베이지
				    	}else if(lists[i].pmloc=='메사' || lists[i].pmloc=='성수' || lists[i].pmloc=='스타벅스 본사' || lists[i].pmloc=='SI 본사' || lists[i].pmloc=='건설'){
				    		color = "status2"; //초록
				    	}else if(lists[i].pmloc=='교육'){
				    		color = "status3"; //연두
				    	}else if(lists[i].pmloc=='기타'){
				    		color = "status4"; //노랑
				    	}else if(lists[i].pmloc=='구로'){
				    		color = "status5"; //빨강
				    	}else if(lists[i].pmloc=='DAY OFF'){
				    		color = "status6"; //휴무
				    	}
			  		}		
      			
		    	txt +="<article><div class='item-lay "+color+ "'>";
		    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"' /></div>";
		    	
				
		    	if(AMPM == "AM"){
			    	if(lists[i].amloc == "기타"){
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>";
			    	}
		    	}
		    	
		    	if(AMPM == "PM"){
			    	if(lists[i].pmloc == "기타"){
			    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
			    	}
		    	}
		    	
		    	txt +="</div></div></article>";
      		 }
      		 txt +="</section></div>" ;
       	    
       	     $("#carouselinner").append(txt);
       	/////////////////////////////////////////////////////////////////////////////////////////////////    
      	   }else if(lists.length > 19 && lists.length < 30) {
      		 var txt="<div class='item'>"; 
      			 txt +="<section id='card-item'>";
      		 for(var i=20 ; i < lists.length ; i++) {      			
      			
      			 
       			var color = "status7";
 		  		
 		  		//now: AM
 		  		if(AMPM == "AM"){
 		  			if(lists[i].amloc=='본사 9F' || lists[i].amloc=='본사 10F' || lists[i].amloc=='본사 14F' || lists[i].amloc=='본사 13F'){
			    		color = "status1"; //베이지
			    	}else if(lists[i].amloc=='메사' || lists[i].amloc=='성수' || lists[i].amloc=='스타벅스 본사' || lists[i].amloc=='SI 본사' || lists[i].amloc=='건설'){
			    		color = "status2"; //초록
			    	}else if(lists[i].amloc=='교육'){
			    		color = "status3"; //연두
			    	}else if(lists[i].amloc=='기타'){
			    		color = "status4"; //노랑
			    	}else if(lists[i].amloc=='구로'){
			    		color = "status5"; //빨강
			    	}else if(lists[i].amloc=='DAY OFF'){
			    		color = "status6"; //휴무
			    	}
 			    
 		  		}
 		  		//now: PM
 		  		else{
			  			if(lists[i].pmloc=='본사 9F' || lists[i].pmloc=='본사 10F' || lists[i].pmloc=='본사 14F' || lists[i].pmloc=='본사 13F'){
				    		color = "status1"; //베이지
				    	}else if(lists[i].pmloc=='메사' || lists[i].pmloc=='성수' || lists[i].pmloc=='스타벅스 본사' || lists[i].pmloc=='SI 본사' || lists[i].pmloc=='건설'){
				    		color = "status2"; //초록
				    	}else if(lists[i].pmloc=='교육'){
				    		color = "status3"; //연두
				    	}else if(lists[i].pmloc=='기타'){
				    		color = "status4"; //노랑
				    	}else if(lists[i].pmloc=='구로'){
				    		color = "status5"; //빨강
				    	}else if(lists[i].pmloc=='DAY OFF'){
				    		color = "status6"; //휴무
				    	}
			  		}		
 		  		
 		    	txt +="<article><div class='item-lay "+color+ "'>";
      		
		    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"' /></div>";
		    	
		    	if(AMPM == "AM"){
			    	if(lists[i].amloc == "기타"){
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>";
			    	}
		    	}
		    	
		    	if(AMPM == "PM"){
			    	if(lists[i].pmloc == "기타"){
			    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
			    	}
		    	}
		    	
		    	txt +="</div></div></article>";
      		 }
      		 txt +="</section></div>" ;
       	    
       	     $("#carouselinner").append(txt);
       	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
      	   }else if(lists.length > 29 && lists.length < 40) {
        		 var txt="<div class='item'>"; 
        			 txt +="<section id='card-item'>";
          		 for(var i=30 ; i < lists.length ; i++) {          			
          			 
           			var color = "status7";
     		  		
     		  		//now: AM
     		  		if(AMPM == "AM"){
     		  			if(lists[i].amloc=='본사 9F' || lists[i].amloc=='본사 10F' || lists[i].amloc=='본사 14F' || lists[i].amloc=='본사 13F'){
				    		color = "status1"; //베이지
				    	}else if(lists[i].amloc=='메사' || lists[i].amloc=='성수' || lists[i].amloc=='스타벅스 본사' || lists[i].amloc=='SI 본사' || lists[i].amloc=='건설'){
				    		color = "status2"; //초록
				    	}else if(lists[i].amloc=='교육'){
				    		color = "status3"; //연두
				    	}else if(lists[i].amloc=='기타'){
				    		color = "status4"; //노랑
				    	}else if(lists[i].amloc=='구로'){
				    		color = "status5"; //빨강
				    	}else if(lists[i].amloc=='DAY OFF'){
				    		color = "status6"; //휴무
				    	}     			    
     		  		}
     		  		//now: PM
     		  		else{
			  			if(lists[i].pmloc=='본사 9F' || lists[i].pmloc=='본사 10F' || lists[i].pmloc=='본사 14F' || lists[i].pmloc=='본사 13F'){
				    		color = "status1"; //베이지
				    	}else if(lists[i].pmloc=='메사' || lists[i].pmloc=='성수' || lists[i].pmloc=='스타벅스 본사' || lists[i].pmloc=='SI 본사' || lists[i].pmloc=='건설'){
				    		color = "status2"; //초록
				    	}else if(lists[i].pmloc=='교육'){
				    		color = "status3"; //연두
				    	}else if(lists[i].pmloc=='기타'){
				    		color = "status4"; //노랑
				    	}else if(lists[i].pmloc=='구로'){
				    		color = "status5"; //빨강
				    	}else if(lists[i].pmloc=='DAY OFF'){
				    		color = "status6"; //휴무
				    	}
			  		}		
     		  		
     		    	txt +="<article><div class='item-lay "+color+ "'>";
    		    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"' /></div>";
			        	
			    	
			    	if(AMPM == "AM"){
				    	if(lists[i].amloc == "기타"){
				    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
				    	}else{
				    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>";
				    	}
			    	}
			    	
			    	if(AMPM == "PM"){
				    	if(lists[i].pmloc == "기타"){
				    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
				    	}else{
				    		txt +="<div class='ampm'><div class='pm'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
				    	}
			    	}
			    	
			    	txt +="</div></div></article>";
          		 }
          		 txt +="</section></div>" ;
           	    
           	     $("#carouselinner").append(txt);
          	   }
    	
      }) 
    	  
  
      
    </script> 

	<script src="https://use.fontawesome.com/492f5222e8.js"></script>
	
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
		
		location.href='calDayDash.inc?currDate='
			+ $("#currDate").text();
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
		
/* 		document.dateForm.action = 'calDayDash.inc?currDate='
				+ $("#currDate").text();
		document.submit();
		 */
		location.href='calDayDash.inc?currDate='
			+ $("#currDate").text();
	}

	            
    function restart() {  
    
      setTimeout("location.href='dashboard.inc'",700000);
    } 
  
    function colorchange(){
    	$("#currDate").style.backgroundColor="#010101"
    }
    

    	
</script> 
  </body>
</html>
