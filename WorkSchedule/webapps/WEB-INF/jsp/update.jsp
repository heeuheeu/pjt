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
        <form action="modify.inc" method="post" role="form" enctype="multipart/form-data">
        
        	<%--  <img src="resources/${myinfo.empimg}"> --%>
        	<input type="file" placeholder="사진" name="file" id="empimg" value="${mydeptdiv.empimg}"/>
        		
	        <input type="text" placeholder="EMPID" name="empid" id="empid" value="${mydeptdiv.empid}" readonly/><br>
	        <input type="password" placeholder="EMPPWD" name="emppwd" value="${mydeptdiv.emppwd}"/><br>
	        <input type="text" placeholder="EMPNAME" name="empname" value="${mydeptdiv.empname}" readonly/><br>

	  
	        <input type="text" placeholder="EMPPHONE" name="empphone" value="${mydeptdiv.empphone}"/><br>
	        <input type="text" placeholder="EMPMAIL" name="empmail" value="${mydeptdiv.empmail}"/><br>
	            

		              <!--  /////////////사업부 이름 조회////////////////////// --> 
	         <select class="form-control" name="divname" id="divname" required>
			      <c:forEach items="${divlist}" var="member"> 
			     <option <c:if test="${mydeptdiv.divname eq member.divname}"> selected</c:if>> ${member.divname} </option>    
	         </c:forEach>        
	       </select><br>               

       		     <select class="form-control" name="deptname" id="deptname">
		  		<c:forEach items="${deptlist}" var="member">	
					<option <c:if test="${mydeptdiv.deptname eq member.deptname}"> selected</c:if>> ${member.deptname}	</option>    
		      	</c:forEach>	      	
		     </select><br>	      
		     
	          
			<select class="form-control" name="emploc">			
				<option <c:if test="${mydeptdiv.emploc eq '본사 10F'}"> selected</c:if>>본사 10F</option>
				<option <c:if test="${mydeptdiv.emploc eq '본사 13F'}"> selected</c:if>>본사 13F</option>
				<option <c:if test="${mydeptdiv.emploc eq '본사 14F'}"> selected</c:if>>본사 14F</option>
				<option <c:if test="${mydeptdiv.emploc eq '성수'}"> selected</c:if>>성수</option>
				<option <c:if test="${mydeptdiv.emploc eq '메사'}"> selected</c:if>>메사</option>
				
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
    
	 $("#divname").change(function() {
		 var divname = $('#divname option:selected').val();
		 //alert("divname ajax 들어옴");
		 
		   $.ajax({
			    url : "deptSelect.inc",
			    type : "post",
			    data : {
			    	div : divname
		   		},
			   dataType : "json",
			    
			   success : function(data) { 
			    	$('#deptname').empty();
			    	for(var i=0; i<data.length; i++){
			     		$('#deptname').append("<option>"+data[i]+"</option>")
			    	}
			   },   
			   error : function() {
			    	alert("error");
		   		} 
		   }); 
		  });
        
    
    </script>
    
    
  </body>
</html>
