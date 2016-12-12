<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/update.css" rel="stylesheet" type="text/css">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/custom2.css" rel="stylesheet">    
 
  </head>
  <body>
    <section>
        
      <div class="sign-form">
        <form action="modify.inc" method="post" role="form">
        
	        <input type="text" placeholder="EMPID" name="empid" id="empid" value="${myinfo.empid}" readonly/><br>
	        <input type="password" placeholder="EMPPWD" name="emppwd" value="${myinfo.emppwd}"/><br>
	        <input type="text" placeholder="EMPNAME" name="empname" value="${myinfo.empname}" readonly/><br>
	        
	        <!-- <label class="leader">
	        <input class="form-check-input" type="radio" name="empgrade" value="팀장" >팀장</label>&emsp;&emsp;
	        
	        <label class="partner">
	        <input class="form-check-input" type="radio" name="empgrade" value="담당" checked >담당 </label><br><br> -->
	        
	        <input type="text" placeholder="EMPPHONE" name="empphone" value="${myinfo.empphone}"/><br>
	        <input type="text" placeholder="EMPMAIL" name="empmail" value="${myinfo.empmail}"/><br>
	            
		     <select class="form-control" name="deptname" >
		  		<c:forEach items="${lists}" var="member">	
					<option <c:if test="${myinfo.deptname eq member.deptname}"> selected</c:if>> ${member.deptname}	</option>    
		      	</c:forEach>	      	
		     </select><br>	      
	          
			<select class="form-control" name="emploc">
								
				<option <c:if test="${myinfo.emploc eq '본사 10F'}"> selected</c:if>>본사 10F</option>
				<option <c:if test="${myinfo.emploc eq '본사 13F'}"> selected</c:if>>본사 13F</option>
				<option <c:if test="${myinfo.emploc eq '본사 14F'}"> selected</c:if>>본사 14F</option>
				<option <c:if test="${myinfo.emploc eq '성수'}"> selected</c:if>>성수</option>
				<option <c:if test="${myinfo.emploc eq '메사'}"> selected</c:if>>메사</option>
				
			</select><br>   
			                   
	        <input type="submit" value="UPDATE" id="join">
        </form>
        
      </div>
      
    </section>
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">

    
//--------------------------join jsp-----------------------------------//  
    /////////////id validation//////////////////////
    $("#empid").on("change", function() {
		
    	var empid = $('#empid').val();
    	
		$.ajax({

			url : "idchk.inc",
			type : "post",
			data : {
				id : empid
			},
			dataType : "json",
			success : function(data) { 

			///////NOT duplicated id////
				if(data) {							
				}
				
			///////duplicated id////	
				else {
					alert("duplicated ID!!!!!!!!!");
					$('#empid').val("");
					$('#empid').focus();
				}			
			},
			
			error : function() {
				alert("error");
			}
			
		});//ajax
	});
    
    
        
    
    </script>
    
    
  </body>
</html>
