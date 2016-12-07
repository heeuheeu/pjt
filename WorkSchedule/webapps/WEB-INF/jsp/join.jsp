<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/join.css" rel="stylesheet" type="text/css">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/custom2.css" rel="stylesheet">    
    
  </head>
  <body>
    <section>
        
      <div class="sign-form">
        <form action="join.inc" method="post" role="form">
        
	        <input type="text" placeholder="EMPID" name="empid" required/><br>
	        <input type="password" placeholder="EMPPWD" name="emppwd" required/><br>
	        <input type="text" placeholder="EMPNAME" name="empname" required/><br>
	        <input type="radio" name="empgrade" value="팀장" >팀장&emsp;&emsp;
	        <input type="radio" name="empgrade" value="담당" >담당 <br><br>
	        
	        <input type="text" placeholder="EMPPHONE" name="empphone" required/><br>
	        <input type="text" placeholder="EMPMAIL" name="empmail" required/><br>
	            
		     <select class="form-control" name="deptname" required>
		  		<option selected disabled value="">EMPDEPT</option>
		  		<c:forEach items="${lists}" var="member">	
					<option value="${member.deptname}"> ${member.deptname}	</option>    
		      	</c:forEach>	      	
		     </select><br>	      
	          
			<select class="form-control" name="emploc" required>
				<option selected disabled value="">EMPLOC</option>
				<option value="본사 10F">본사 10F</option>
				<option value="본사 13F">본사 13F</option>
				<option value="본사 14F">본사 14F</option>
				<option value="성수">성수</option>
				<option value="메사">메사</option>
			</select><br>   
			                   
	        <input type="submit" value="JOIN" id="join">
        </form>
      </div>
      
    </section>
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">

    
    </script>
    
    
  </body>
</html>
