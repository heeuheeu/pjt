<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/update.css" rel="stylesheet" type="text/css">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    
    <style type="text/css">
		#empimg { display:none; } 
	</style>
 
  </head>
  <body>
    <section>
        
      <div class="sign-form">
        <form id="modify" role="form" enctype="multipart/form-data">
        	
        	<div class="fileinput fileinput-new" data-provides="fileinput">
			  <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;">
			  	<img src="resources/${myinfo.empimg}" id="srcimg">
			  </div>
			  <div>
			    <span class="btn btn-default btn-file">
			   	 <input type="file" placeholder="사진" name="file" id="empimg" value="${mydeptdiv.empimg}"/>
			   	 <button type="button" id="btn-upload" id="cngimg" style="background-color: transparent; border: none;">변경하기</button>
			    </span>
			  </div>
			</div>
        	
        	
        	
        	<!-- <div>
				<input type="file" id="empimg" name="file" onchange="changeValue(this)"/>
				<button type="button" id="btn-upload">변경하기</button>
			</div> -->
        	
        	
        	<%-- <img src="resources/${myinfo.empimg}" style="width: 150px; height: auto;">
        	<input type="file" placeholder="사진" name="file" id="empimg" value="${mydeptdiv.empimg}"/> --%>
        		
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
				<c:forEach items="${locList}" var="locList">	
					<option <c:if test="${locList.locname eq member.deptname}"> selected</c:if>> ${locList.locname}	</option>    
		      	</c:forEach>
			</select><br>   
			                   
	        <button type="button" value="UPDATE" onclick="updateEmp()" style="display: block; width: 100%; background:#d3492c; border: 0; color: #fff;line-height: 50px;height: 50px;margin-bottom: 10px;"> UPDATE </button>
	        <button type="button" value="DELETE" onclick="deleteEmp()" style="display: block; width: 100%; background:#3e3f44; border: 0; color: #fff;line-height: 50px;height: 50px;margin-bottom: 10px;"> DELETE </button>
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
	 
	 
	 function updateEmp(){
		 $('#modify')
			.prop("action","modify.inc")
			.prop("method","post").submit();
	 }
	 
	 function deleteEmp(){
		 
		 var con_test = confirm("회원정보를 모두 삭제하시겠습니까?");
			if(con_test){
					$('#modify')
					.prop("action","delete.inc")
					.prop("method","post").submit();
				}
	 }
	 
	 
	 $('#deptname').on("change", function(){
	        $('#divname').focus();
	 });
	 
    
	 $(function () {
		 $('#btn-upload').click(function (e) {
			e.preventDefault();
			
		 	$('#empimg').click();
		 });
 	 });
	 
	     
	 
    </script>
    
    
  </body>
</html>
