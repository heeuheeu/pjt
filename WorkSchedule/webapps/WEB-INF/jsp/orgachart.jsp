<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>신세계I&C 최강 신입사원 자유게시판</title>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/custom2.css" rel="stylesheet">

</head>

<body>
	<form role="form" class="form-inline" action="searchBoard.inc"	method="post">
		<div align = "right" class="form-inline">
			<select  class=".col-xs-3" class="form-inline" name="searchCondition">
				<option >제목</option>
				<option >작성자</option>
			</select>
		<input type="text" class="form-control" placeholder="검색내용" name = "searchKeyword" size=50px>
		<button type="submit" class="btn btn-default" role="button">검색</button>
</div>

		<h3 class="text-primary" >즐겨찾기</h3>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>이름</th>
					<th>직급</th>
					<th>부서명</th>
					<th>추가하기<input type="checkbox" name="checkTotal" id="allChk"> 전체선택</th>					
				</tr>
			</thead>

			<c:forEach items="${lists}" var="member">
				<tr>
					<td>${member.empname}</td>
					<td>${member.empgrade}</td>
					<td>${member.deptname}</td>		
					<td><input type="checkbox" name="chk" value="${member.empname}">즐겨찾기</td>		
				</tr>				
			</c:forEach>
		</table>
		
		<a href="addfavorite.inc" class="btn btn-default" role="button" id="addBtn">즐겨찾기 추가하기</a>
	</form>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script src="./common/index.js"></script>
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
		
		
		$("#addBtn").on("click", function(){
			
			
			var checkArr = [];
			//checkArr.push("${member.id}");
			$("input[name='chk']:checked").each(function(i){
				checkArr.push($(this).val());
			});
			
			$.ajax({
				url: "addfavorite.inc",
				type: "post",
				dataType: "text",
				data: {
					valueArrTest: checkArr
				}
			});	
			
		});	
		
	</script>		
</body>
</html>
