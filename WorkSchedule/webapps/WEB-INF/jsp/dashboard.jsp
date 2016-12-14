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
    
    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body OnLoad="restart()"> 


  
	
	<form role="form" name='dateForm' method="post">
	    <!--상단-->
	    <section id="top-bar">
	      <!--날짜-->
	      <div class="date">
	        <!-- <div>팀원 현재 상태보기</div> -->
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
	
	  <div class="container"> 
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
	  
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.min.js"></script>
    <script>
      $('.carousel').carousel();

      $(document).ready(function(){
		  var lists = [] ; 
		  <c:forEach items="${myfav}" var="fav">	
		  	var	empid = "${fav.empid}";
		  	var	empname = "${fav.empname}";
		  	var	amloc = "${fav.amloc}";
		  	var	pmloc = "${fav.pmloc}";
		  	var obj = {empid : empid , empname : empname , amloc : amloc , pmloc : pmloc };
		  	lists.push(obj);
		  </c:forEach>
		  console.log(lists);
		  
		  
		  var txt="<div class='item active'>";
		  txt +="<section id='card-item'>";
		  for(var i=0 ; i < lists.length ; i++) {
			   
			    if(i<10) {
			    	
			    	txt +="<article><div class='item-lay status1'>";
			    	txt +="<div class='profile'><img src='img/"+lists[i].empid+".png' /></div>";
			    	txt +="<div class='name'>"+lists[i].empname+"</div>";
			    	txt +="<div class='ampm'><div class='am'><span>AM</span>"+lists[i].amloc+"</div><div class='pm'><span>PM</span>"+lists[i].pmloc+"</div>";
			    	txt +="</div></div></article>";
			    }
      			
      	   }
		   txt +="</section></div>" ;
      	   
      	   $("#carouselinner").append(txt);
      	   
      	   if(lists.length > 9 && lists.length < 20) {
      		 var txt="<div class='item'>"; 
      			 txt +="<section id='card-item'>";
      		 for(var i=10 ; i < lists.length ; i++) {      			
		    	txt +="<article><div class='item-lay status1'>";
		    	txt +="<div class='profile'><img src='img/"+lists[i].empid+".png' /></div>";
		    	txt +="<div class='name'>"+lists[i].empname+"</div>";
		    	txt +="<div class='ampm'><div class='am'><span>AM</span>"+lists[i].amloc+"</div><div class='pm'><span>PM</span>"+lists[i].pmloc+"</div>";
		    	txt +="</div></div></article>";
      		 }
      		 txt +="</section></div>" ;
       	    
       	     $("#carouselinner").append(txt);
       	    
      	   }else if(lists.length > 19 && lists.length < 30) {
      		 var txt="<div class='item'>"; 
      			 txt +="<section id='card-item'>";
      		 for(var i=20 ; i < lists.length ; i++) {      			
		    	txt +="<article><div class='item-lay status1'>";
		    	txt +="<div class='profile'><img src='img/"+lists[i].empid+".png' /></div>";
		    	txt +="<div class='name'>"+lists[i].empname+"</div>";
		    	txt +="<div class='ampm'><div class='am'><span>AM</span>"+lists[i].amloc+"</div><div class='pm'><span>PM</span>"+lists[i].pmloc+"</div>";
		    	txt +="</div></div></article>";
      		 }
      		 txt +="</section></div>" ;
       	    
       	     $("#carouselinner").append(txt);
       	     
      	   }else if(lists.length > 29 && lists.length < 40) {
        		 var txt="<div class='item'>"; 
        			 txt +="<section id='card-item'>";
          		 for(var i=30 ; i < lists.length ; i++) {          			
    		    	txt +="<article><div class='item-lay status1'>";
    		    	txt +="<div class='profile'><img src='img/"+lists[i].empid+".png' /></div>";
    		    	txt +="<div class='name'>"+lists[i].empname+"</div>";
    		    	txt +="<div class='ampm'><div class='am'><span>AM</span>"+lists[i].amloc+"</div><div class='pm'><span>PM</span>"+lists[i].pmloc+"</div>";
    		    	txt +="</div></div></article>";
          		 }
          		 txt +="</section></div>" ;
           	    
           	     $("#carouselinner").append(txt);
          	   }
    	
      }) 
    	  
  
      
      
      
      
    </script> 


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
	
	
	            
    function restart() {  
    
      setTimeout("location.href='dashboard.inc'",700000);
    } 
  
    
</script> 
  </body>
</html>
