<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/searchView.css" rel="stylesheet" type="text/css">
    
         <!-- Bootstrap core CSS -->
    <link href="bootstrap.css" rel="stylesheet">
    <link href="jasny-bootstrap.min.css" rel="stylesheet">
	<link href="css/sideMenu.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="navmenu-push.css" rel="stylesheet">
  </head>
  <body>

	<div class="navmenu navmenu-default navmenu-fixed-left offcanvas">
      
	  <div class="side-menu-content">
      <div class="user-info">
      <br>
          <div class="profile"><img src="img/profile.png"/></div>
          <div class="name">플랫폼서비스팀</div>
          <div class="name">홍길동 담당</div>
      </div>
      
      <ul class="nav navmenu-nav btns">
      <div class="btns">
        <li><a href="calendar.jsp" class="btn1" style="color: white;"><img src="img/side-icon1.png"/>나의일정</a></li>
        <li><a href="intro.jsp" class="btn2" style="color: white;"><img src="img/side-icon2.png"/>로그아웃</a></li>
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
      <a class="navbar-brand" href="cardView.jsp" style="color: white; margin-left: 60px">SSG 워크플레이스</a>
      <a href="cardView.jsp"><img src="img/check.png" style="width: 42px; height: 36px; float: right; margin-top: 7px; margin-right: 8px"></a>
    </div>

    <!--상단-->
    
    <section id="search-bar">
      <form>
      <input type="text" placeholder="이름, 사번, 장소 검색"/>
      <input type="submit" value="검색"/>
      <div class="path">
        <div><a href="">신세계아이앤씨</a><span>></span></div>
        <div><a href="">지원담당</a><span>></span></div>
        <div><a href="">인사팀</a></div>
      </div>
      </form>
    </section>
    <!--상단-->

    <!--리스트-->
    <section id="list-item">
    
	<div class="path" style="float: right; margin-top: 0px" >       
        <div><a href="#" id="allChk">전체 선택</a></div>
      </div> 
    
   <!--   <div class="all-check"><input type="checkbox" id="Option1">전체선택<label class="all-check" for="Option1"></label></div>
   -->    <article>
        <div class="item-lay">
          <div class="profile"><img src="img/hyun.PNG"/></div>
          <div class="name">
            손정현 상무<br/>
            신세계아이앤씨<br/>
            지원담당
          </div>
          <div class="check">
          
            <input type="checkbox" id="Option" name="chk">
            <label class="checkbox" for="Option"></label>
          </div>
        </div>
      </article>
      <article>
        <div class="item-lay">
          <div class="profile"><img src="img/min.PNG"/></div>
          <div class="name">
            한훈민 팀장<br/>
            신세계아이앤씨<br/>
     	인사팀
          </div>
          <div class="check">
            <input type="checkbox" id="Option2" name="chk">
            <label class="checkbox" for="Option2"></label>
          </div>
        </div>
      </article>
      <article>
        <div class="item-lay">
          <div class="profile"><img src="img/chul.PNG"/></div>
          <div class="name">
            손승철 담당<br/>
            신세계아이앤씨<br/>
            인사팀
          </div>
          <div class="check">
            <input type="checkbox" id="Option3" name="chk">
            <label class="checkbox" for="Option3"></label>
          </div>
        </div>
      </article>
      <article>
        <div class="item-lay">
          <div class="profile"><img src="img/eun.PNG"/></div>
          <div class="name">
           박정은 담당<br/>
            신세계아이앤씨<br/>
            인사팀
          </div>
          <div class="check">
           <input type="checkbox" id="Option4" name="chk">
            <label class="checkbox" for="Option4"></label>
          </div>
        </div>
      </article>
      <article>
        <div class="item-lay">
          <div class="profile"><img src="img/ah.png"/></div>
          <div class="name">
           오상아 담당<br/>
            신세계아이앤씨<br/>
            인사팀
          </div>
          <div class="check">
           <input type="checkbox" id="Option5" name="chk">
            <label class="checkbox" for="Option5"></label>
          </div>
        </div>
      </article>
      <article>
        <div class="item-lay">
          <div class="profile"><img src="img/hwan.png"/></div>
          <div class="name">
           김재환 담당<br/>
            신세계아이앤씨<br/>
            인사팀
          </div>
          <div class="check">
           <input type="checkbox" id="Option6" name="chk">
            <label class="checkbox" for="Option6"></label>
          </div>
        </div>
      </article>
      <article>
        <div class="item-lay">
          <div class="profile"><img src="img/man.png"/></div>
          <div class="name">
           안석만 담당<br/>
            신세계아이앤씨<br/>
            인사팀
          </div>
          <div class="check">
           <input type="checkbox" id="Option7" name="chk">
            <label class="checkbox" for="Option7"></label>
          </div>
        </div>
      </article>
      
    </section>
    
    <!--리스트-->
    
<!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="bootstrap.min.js"></script>
    <script src="jasny-bootstrap.min.js"></script>
    <script type="text/javascript">
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
    </script>
  </body>
</html>
