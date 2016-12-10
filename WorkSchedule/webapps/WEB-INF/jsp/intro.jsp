<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="./css/reset.css" rel="stylesheet" type="text/css">
    <link href="./css/intro.css" rel="stylesheet" type="text/css">
    
  </head>
  <body>
    <section>
      <div class="logo"><img src="./img/logo.jpg" style="width: 250px; height: 160px"/></div>
      
      <div class="sign-form">
        
        
       <!--  ////////////login//////////// -->
        <form action="login.inc" method="post">
        <input type="text" placeholder="사번" name="empid" id="empid" required/>
        <input type="password" placeholder="PASSWORD" name="emppwd" id="emppwd" required/>
      	
      	
      	  <!--  ----------------intro.jsp--------------------- -->
      	 <!--  //////////////////remember id/////////////////// -->
      	<div class="checkbox-layout1">
      		<label class="chk"> 
          <input type="checkbox" id="savechk" onClick="javascript:saveid(document.new_user_session);"
          style="margin: 20px 0;display: inline-block; vertical-align: middle;width: 20px; height: 20px; border-radius: 0; border: solid 1px #999; background: #fff;"/>
       			   아이디 기억하기 </label>
        </div>
        <!--  //////////////////remember id/////////////////// -->
        
        
             
        
        <input type="submit" value="LOGIN" id="login" style="margin-bottom: 10px">
        </form> 
          
        
          <form action="joinForm.inc" >
	   	  <input type="submit" value="SIGN UP" style="background-color: #3e3f44">
	      </form>
   </div>
    </section>
  
  	<script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">
	
    
    
  /*   ----------------intro.jsp--------------------- */
    ////////////////2016-12-09/////////////////
    
    ///////empid & emppwd validation///////
    
 
	    if("${chk}"=="noneId"){
	    	alert("사번이나 패스워드를 확인해주세요!");
	    }    

    
    
	///////////////////remeber id////////////////////
    $(function(){
    	 getid();
    	  $("#savechk").click(function(){
    	   saveid();
    	  }); //#chkuser_id.click
    	 }); //function()
    	  
    	 function saveid() {
    	   var expdate = new Date();
    	   // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    	   if($("#savechk").prop("checked")){
    	    expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    	   } else {
    	    expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    	   }
    	   setCookie("saveid", $("#empid").val(), expdate);
    	 } //saveid()
    	 
    	 function setCookie (name, value, expires) {
    	    document.cookie = name + "=" + escape (value) +"; path=/; expires=" + expires.toGMTString();
    	  } //setCookie(name,value,expires)

    	  function getCookie(Name) {
    	    var search = Name + "=";
    	    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
    	      offset = document.cookie.indexOf(search);
    	      if (offset != -1) { // 쿠키가 존재하면
    	        offset += search.length;
    	        // set index of beginning of value
    	        end = document.cookie.indexOf(";", offset);
    	        // 쿠키 값의 마지막 위치 인덱스 번호 설정
    	        if (end == -1)
    	          end = document.cookie.length;
    	        return unescape(document.cookie.substring(offset, end));
    	      }
    	    }
    	    return "";
    	  } //getCookie(Name)

    	 function getid() {
    	  var saveId = getCookie("saveid");
    	  if(saveId != "") {
    	   $("#empid").val(saveId);
    	   $("#savechk").prop("checked",true);
    	  }
    	 } //getid()
   /*   ----------------intro.jsp--------------------- */
   
   
   
   
    </script>
  
  
  </body>
  
</html>
