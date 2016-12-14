<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="./css/reset.css" rel="stylesheet" type="text/css">
    <link href="./css/searchView.css" rel="stylesheet" type="text/css">
    
         <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.css" rel="stylesheet">
    <link href="./css/jasny-bootstrap.min.css" rel="stylesheet">
	<link href="./css/sideMenu.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="./css/navmenu-push.css" rel="stylesheet">
  </head>
  <body> 

	<div class="navmenu navmenu-default navmenu-fixed-left offcanvas">
      
	  <div class="side-menu-content">
      <div class="user-info">
      <br>
          <div class="profile"><img src="img/profile.png"/></div>
          <div class="name">${myinfo.deptname}</div>
          <div class="name">${myinfo.empname}<%--  ${myinfo.empgrade} --%></div>
      </div>
      
      <ul class="nav navmenu-nav btns">
      <div class="btns">
        <li><a href="calMove.inc" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>나의일정</a></li>
         <li><a href="updateview.inc" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>내 정보수정</a></li>
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
      <a class="navbar-brand" href="user.inc" style="color: white; margin-left: 60px">SSG 워크플레이스</a>
      
      <!-- check 버튼 -->      
      <a id="addBtn" href="user.inc"><img src="img/check.png" style="width: 30px; height: 30px; float: right; margin-top: 10px; margin-bottom: 8px; margin-right: 12px"></a>
    </div>

    <!--상단-->
    <section id="search-bar">
      <form action="search.inc" method="post">
	      <input type="text" placeholder="이름 또는 부서 검색" name = "searchKeyword"/>
	      <input type="submit" value="검색"/>
<%-- 	      <div class="path">
	        <div><a href="">신세계아이앤씨</a><span>></span></div>
	        <div><a href="searchteam.inc">지원담당</a><span>></span></div>
	        <div><a href="searchdept.inc">${myinfo.deptname}</a></div>
	      </div> --%>
      </form>
    </section>
    
    <!--상단-->

    <!--리스트-->
    <section id="list-item">
	<div class="path" style="float: right; margin-top: 0px" >    
	<!-- 	<button type="submit" id="transbtn" style="display: none">전송</button>    -->
<!--         <div><input type="checkbox" id="allChk">전체 선택/해제</div> -->
      </div> 
    
    <c:forEach items="${lists}" var="member">	   
		   <article>	        
		        <div class="item-lay">
		          <div class="profile"><img src="resources/${member.empimg}"/></div>
		          <div class="name">
		            ${member.empname}<%--  ${member.empgrade} --%><br/>
		            ${member.deptname}<br/>	       
		          </div>
		          <div class="check">	          
		          		<a onclick="onCheck('${member.empid}')" id="${member.empid}" name="onchk"></a>
		          </div>	          		          		
		        </div>
		    </article>	      
	 </c:forEach>
    </section>
    
    <!--리스트-->
    
<!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-1.10.2.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jasny-bootstrap.min.js"></script>
    <script type="text/javascript">

    		var favid = new Array();
    		var list = new Array();

	    	function onCheck(chk) {
		    	
	    		var chkid = chk;
	    		var flag = 0;
		
	    		// favid가 없을때는 안 들어감
	    		if (favid.length!=0) {
			    	for (var i=0; i<favid.length; i++) { 
			    		 if(favid[i] == chkid ) { // favlist에 있는 id와 클릭id 같을 경우 delete
			    			flag=1;
			    			break;
			    		 }
			    		 else { // favlist에 있는 id와 클릭id 다를 경우 add
			    			flag=2;
							continue;
			    		 }
			    	}
	    		}
	    		
	    		switch(flag) {
				case 0:
					document.getElementById(chkid).style.background = "url('./img/redCheck.PNG')";
		    		//alert("flag : "+flag+" chkid : "+chkid+" 다른 아이디 선택. addfav"); 
					addFav(chkid);					
					break;
				case 1:
					document.getElementById(chkid).style.background = "url('./img/grayCheck.PNG')";
	    			//alert("flag : "+flag+" chkid : "+chkid+" 같은 아이디 선택. deletefav");	 
					deleteFav(chkid);	
					break;
				case 2:
					document.getElementById(chkid).style.background = "url('./img/redCheck.PNG')";
		    		//alert("flag : "+flag+" chkid : "+chkid+" 다른 아이디 선택. addfav"); 
					addFav(chkid); 
					break;
				}
	    	};

	    	
	    	function addFav(chkid) {
		    	var chkid = chkid;
	
		    	$.ajax({
					url : "addfavorite.inc",
					type : "post",
					data : {
						checkid : chkid
					},
					dataType : "json",
					success : function(jarr) {
						alert("success favorite insert");
						$.each(jarr, function(idx, value) {
							favid[idx] = value.empidfav;
						});
					},
					error: function(json) {
						alert("error favorite insert");
						
					}
				});
	    	};
	    	
	    	function deleteFav(chkid) {
		    	var chkid = chkid;
	
				$.ajax({
					url : "deletefavorite.inc",
					type : "post",
					data : {
						checkid : chkid
					},
					dataType : "json",
					success : function(jarr2) {
						alert("success favorite delete");
						if(jarr2.length != 0) {
							$.each(jarr2, function(idx, value2) {
								favid[idx] = value2.empidfav;
							});
						} else if (jarr2.length == 0) {
							favid = jarr2;
						}	
					},
					error: function(json) {
						alert("error favorite delete");
					}
				});
	    	};
    
    	$(document).ready(function() {

	 		// 내 즐겨찾기 리스트
    		<c:forEach items="${myfav}" var="item">
	    		favid.push("${item.empidfav}");
    		</c:forEach>

    		// 리스트에 뿌려지는 사람들
    		<c:forEach items="${lists}" var="item2">
    			list.push("${item2.empid}");
			</c:forEach>
		
    		// 내 즐겨찾기 리스트 없으면 안들어감
    		for(var j=0 ; j<list.length ; j++) {
    			for (var i=0; i<favid.length; i++) {
    			    if(list[j] == favid[i]) {
    			    	//alert(list[j] +" "+ favid[i] + "같음 >>>> j,i = "+j+i);
    			    	document.getElementById(list[j]).style.background = "url('./img/redCheck.PNG')";
    			    	break;
    			    }
    			    else {
    			    	//alert(list[j] +" "+ favid[i] + "다름 >>>> j,i = "+j+i);
    			    	document.getElementById(list[j]).style.background = "url('./img/grayCheck.PNG')";
    			    }
    			}
    		};
	 	});
    
    /* 
	        $("#allChk").on("click", function() {
	    		//prop() checked 속성

	    		if ($(this).prop("checked")) {
	    			$("input[name='chk']").each(function() {//배열에 같은 이름 여러개
	    				$(this).prop("checked", true);
	    			});
	    		} else {
	    			$("input[name='chk']").each(function() {//배열에 같은 이름 여러개
	    				$(this).prop("checked", false);
	    			});
	    		}

	    	});//allchk
	    	 */
	    	
/* 	   	 	//transbtn
	   	 	function favbtn(){
	   		 	
	   		};
	    	 */

    </script>
  </body>
</html>

















