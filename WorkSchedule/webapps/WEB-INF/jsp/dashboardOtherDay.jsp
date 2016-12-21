<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/dashboard.css" rel="stylesheet" type="text/css">
      <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
   
  </head>
 <body OnLoad="restart()"> 

	<div class="dash-container"> 
	  
	<!--상단-->
	<section id="top-bar">
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
	
	
	<div id="dash-carousel" class="dashoth-carousel">
	        <div id="carousel-example-generic" class="carousel slide">
	                 
	             <div class="carousel-inner" id="carouselinner">     
	                 
	          	</div>
	          	
	        </div> 
	  </div>
	  
	  
	</div>
	
	  
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
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

		var date2 = caleYear + "-" + caleMonth + "-" + caleDay;
		$("#currDate").html(date2);

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


		location.href='calDayDash.inc?currDate='
			+ $("#currDate").text();
	}
	

	//////////////////////////////////////////////////////////////////////// carousel
	
    $('.carousel').carousel({
  	  interval: 8000
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
		  	var empimg = "${fav.empimg}"
		  	var obj = {empid : empid , empname : empname , amloc : amloc , pmloc : pmloc, amlocdetail : amlocdetail, pmlocdetail : pmlocdetail, empimg : empimg };
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
			    	var color = "status1";
			  		
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
			    	//txt +="<article><div class='item-lay status1'>";
			    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"'></div>";
			    	
			    	
			    	
			    	if(lists[i].amloc == "기타"){
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>"; 
			    	}
			    	
			    	
			    	if(lists[i].pmloc == "기타"){
			    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
			    	}
			    	
			    	txt +="</div></div></article>";
			    }
    			
    	   }
		   txt +="</section></div>" ;
    	   
    	   $("#carouselinner").append(txt);
    	   
    	   
   		/////////////////////////////////////////////////////////////////////////////////////////////	   	   
    	   if(lists.length > 9 && lists.length < 20) {
    		   
    		 var txt="<div class='item'>"; 
    			 txt +="<section id='card-item'>";
    		 for(var i=10 ; i < lists.length ; i++) {   
    			 
    			 
    			 	var color = "status1";
		  		
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
		    	//txt +="<article><div class='item-lay status1'>";
		    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"' /></div>";
		    	/* txt +="<div class='name'>"+lists[i].empname+"</div>"; */
		    	
		    	
		    	if(lists[i].amloc == "기타"){
		    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
		    	}else{
		    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>"; 
		    	}
		    	
		    	
		    	if(lists[i].pmloc == "기타"){
		    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
		    	}else{
		    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
		    	}
		    	
		    	txt +="</div></div></article>";
    		 }
    		 txt +="</section></div>" ;
     	    
     	     $("#carouselinner").append(txt);
     		/////////////////////////////////////////////////////////////////////////////////////////////	    	    
    	   }else if(lists.length > 19 && lists.length < 30) {
    		 var txt="<div class='item'>"; 
    			 txt +="<section id='card-item'>";
    		 for(var i=20 ; i < lists.length ; i++) {      			
    			
    			  
     			var color = "status1";
		  		
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
		    	//txt +="<article><div class='item-lay status1'>";
    		
		    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"' /></div>";
		    	/* txt +="<div class='name'>"+lists[i].empname+"</div>"; */
		    	
		    	
		    	if(lists[i].amloc == "기타"){
		    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
		    	}else{
		    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>"; 
		    	}
		    	
		    	
		    	if(lists[i].pmloc == "기타"){
		    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
		    	}else{
		    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
		    	}
		    	
		    	txt +="</div></div></article>";
    		 }
    		 txt +="</section></div>" ;
     	    
     	     $("#carouselinner").append(txt);
  /////////////////////////////////////////////////////////////////////////////////////////////	   	     
    	   }else if(lists.length > 29 && lists.length < 40) {
      		 var txt="<div class='item'>"; 
      			 txt +="<section id='card-item'>";
        		 for(var i=30 ; i < lists.length ; i++) {          			
        			 
         		 	var color = "status1";
   		  		
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
   		    	//txt +="<article><div class='item-lay status1'>";
        			 
  		    	txt +="<div class='profile'><img src='resources/"+lists[i].empimg+"' /></div>";
			    	/* txt +="<div class='name'>"+lists[i].empname+"</div>"; */
			    	
			    	
			    	if(lists[i].amloc == "기타"){
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='ampm'><div class='am'><span>AM</span><div class='loc-text'>"+lists[i].amloc+"</div></div>"; 
			    	}
			    	
			    	
			    	if(lists[i].pmloc == "기타"){
			    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmlocdetail+"</div></div>"; 
			    	}else{
			    		txt +="<div class='pm' style='border-left: solid 1px #ddd;'><span>PM</span><div class='loc-text'>"+lists[i].pmloc+"</div></div>"; 
			    	}
			    	
			    	txt +="</div></div></article>";
        		 }
        		 txt +="</section></div>" ;
         	    
         	     $("#carouselinner").append(txt);
        	   }
    	
      }) 

        
	  function restart() {  
     	 setTimeout("location.href='calDayDash.inc?currDate='+pageDate",1000000);
    } 
    
	
</script>
  </body>
</html>
