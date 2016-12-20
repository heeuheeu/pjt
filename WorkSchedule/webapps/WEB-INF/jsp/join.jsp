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
	<link href="./css/jasny-bootstrap.min.css " rel="stylesheet">
 
    <script  src="https://use.fontawesome.com/b7a0d3c992.js"></script>
    
  </head>
  <body>
    <section>
        
      <div class="sign-form">
        <form action="join.inc" method="post" role="form" id="joinBtn" enctype="multipart/form-data">
        	<div style="font-size: 15px;"><i class="fa fa-cog fa-spin fa-3x fa-fw" style="color:#d3492c; " aria-hidden="true"></i>
        	<b style="font-size: 40px; color: #3e3f44; font-weight: bold; letter-spacing: -2px; ">회원가입</b></div>
        	
        	
        	<hr>
        	<br>
        	
        	<div class="fileinput fileinput-new" data-provides="fileinput">
			  <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
			  <div>
			    <span class="btn btn-default btn-file"><span class="fileinput-new">사진 등록</span><span class="fileinput-exists">사진 변경</span><input type="file" name="file" id="empimg"></span>
			    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">사진 삭제</a>
			  </div>
			</div>
        	
	        <input type="text" placeholder="사번" name="empid" id="empid" required maxlength='10'/><br>
	        <input type="password" placeholder="비밀번호" name="emppwd" required maxlength='12'/><br>
	        <input type="text" placeholder="이름" name="empname" required/><br>
	        <input type="text" onkeydown="mphon(this);" onkeyup="mphon(this);" placeholder="핸드폰 번호" name="empphone" maxlength="13" required/><br>
	        <input type="email" placeholder="이메일" name="empmail" required/><br>
	           

	           <!--  /////////////사업부 이름 조회////////////////////// --> 
	         <select class="form-control" name="divname" id="divname" required>
			      <option selected disabled value="">사업부명</option>
			      <c:forEach items="${divlist}" var="member"> 
			     <option value="${member.divname}"> ${member.divname} </option>    
	         </c:forEach>        
	       </select><br>               
             
	       <select class="form-control" name="deptname" id="deptname" required>
	         <option selected disabled value="">팀명</option>
	       </select><br>       
       
       
			<select class="form-control" name="emploc" required>
				<c:forEach items="${locList}" var="locList"> 
     				<option <c:if test="${locList.locname eq mydeptdiv.emploc}"> selected</c:if>> ${locList.locname} </option>    
         		</c:forEach>
			</select><br>   
			                   
	        <input type="submit" value="JOIN" id="join">
        </form>
      </div>
      
    </section>
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">

    
 
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
    
    
    
    /////////////////phone number format////////////////////
	function mphon( obj ) { 
		 obj.value =  PhonNumStr( obj.value ); 
	  } 
	function _mphon( val ) { 
		 document.write(PhonNumStr( val ));
	  } 

	
	function PhonNumStr( str ){ 
	
		 var RegNotNum  = /[^0-9]/g; 
		 var RegPhonNum = ""; 
		 var DataForm   = ""; 
	
		 // return blank     
		 if( str == "" || str == null ) return ""; 
	
		 // delete not number
		 str = str.replace(RegNotNum,''); 
	
		 /* 4자리 이하일 경우 아무런 액션도 취하지 않음. */
		 if( str.length < 4 ) return str; 
	
		 /* 지역번호 02일 경우 10자리 이상입력 못하도록 제어함. */
		 if(str.substring(0,2)=="02" && str.length > 10){
			 str = str.substring(0, 10);
		 } 
	
		 if( str.length > 3 && str.length < 7 ) { 
			 if(str.substring(0,2)=="02"){
				 DataForm = "$1-$2"; 
	
				 RegPhonNum = /([0-9]{2})([0-9]+)/; 
			 
			 } else {
				 DataForm = "$1-$2"; 
	
				 RegPhonNum = /([0-9]{3})([0-9]+)/; 
			 }
		 } else if(str.length == 7 ) {
			 if(str.substring(0,2)=="02"){
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/; 
			 } else {
				 DataForm = "$1-$2"; 
	
				 RegPhonNum = /([0-9]{3})([0-9]{4})/; 
			 }
		 } else if(str.length == 9 ) {
			  if(str.substring(0,2)=="02"){
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/; 
			 } else {
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{3})([0-9]{3})([0-9]+)/; 
			 }
		 } else if(str.length == 10){ 
			 if(str.substring(0,2)=="02"){
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{2})([0-9]{4})([0-9]+)/; 
			 }else{
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{3})([0-9]{3})([0-9]+)/;
			 }
		 } else if(str.length > 10){ 
			 if(str.substring(0,2)=="02"){
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{2})([0-9]{4})([0-9]+)/; 
			 }else{
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{3})([0-9]{4})([0-9]+)/; 
			 }
		 } else {	 
			 if(str.substring(0,2)=="02"){
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/; 
			 }else{
				 DataForm = "$1-$2-$3"; 
	
				 RegPhonNum = /([0-9]{3})([0-9]{3})([0-9]+)/;
			 }
		 }
	
		 while( RegPhonNum.test(str) ) {  
			 str = str.replace(RegPhonNum, DataForm);  
		 } 
		 return str; 
	
	} 
	
	 $("#divname").change(function() {
		 var divname = $('#divname option:selected').val();
		 
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
