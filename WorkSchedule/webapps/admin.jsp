<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/adminForm.css" rel="stylesheet" type="text/css">

<script src="https://use.fontawesome.com/b7a0d3c992.js"></script>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<section>
		<div class="form">
			<form role="form" name="divadd" class="form-inline"
				action="divAdd.inc" method="post" onsubmit="return checkdivname()">
				<div style="font-size: 20px;">
					<i class="fa fa-cog fa-spin fa-3x fa-fw"
						style="color: #d3492c; font-size: 20px" aria-hidden="true"></i>
					관리자 페이지
				</div>
				<table>
					<thead>
						<tr>
							<td>사업부 번호</td>
							<td>사업부</td>
							<td>사용여부</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${divlist}" var="divlist">
							<tr>
								<td>${divlist.divid}</td>
								<td>${divlist.divname}</td>
								<td><input type="checkbox" id="divchk"
									onclick="divCheck('${divlist.divid}',this)"
									<c:if test="${divlist.checkyn eq 'Y'}" > checked </c:if>>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="text" class="form-control" placeholder="사업부 추가"
					size=90px name=divname required> <input type="submit"
					class="btn btn-default" role="button" value="추가">
			</form>



			<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////     -->

			<form role="form" name="deptadd" onsubmit="return checkdeptname()"
				class="form-inline" action="deptAdd.inc" method="post">
				<table>
					<thead>
						<tr>
							<td>부서 번호</td>
							<td>부서</td>
							<td>사업부</td>
							<td>사용여부</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${deptList}" var="deptList">
							<tr>
								<td>${deptList.deptid}</td>
								<td>${deptList.deptname}</td>
								<td>${deptList.divname}</td>
								<td><input type="checkbox" name="deptchk" id="deptchk"
									onclick="deptCheck('${deptList.deptid}',this)"
									<c:if test="${deptList.checkyn eq 'Y'}" > checked </c:if>>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="text" class="form-control" placeholder="팀 추가" size=50px
					name="deptname" required> <select class="form-control"
					name="divname" required>
					<c:forEach items="${divlistY}" var="divlistY">
						<option>${divlistY.divname}</option>
					</c:forEach>
				</select> <input type="submit" class="btn btn-default" role="button"
					value="추가">
			</form>


			<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////     -->

			<form role="form" class="form-inline" action="locAdd.inc"
				name="locadd" method="post" onsubmit="return checklocname()">
				<table>
					<thead>
						<tr>
							<td>근무지 번호</td>
							<td>근무지</td>
							<td>사용여부</td>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${locList}" var="locList">
							<tr>
								<td>${locList.locid}</td>
								<td>${locList.locname}</td>
								<td><input type="checkbox" name="locchk"
									onclick="locCheck('${locList.locid}',this)"
									<c:if test="${locList.checkyn eq 'Y'}" > checked </c:if>></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<input type="text" class="form-control" placeholder="근무지 추가"
					size=90px name="locname" id="locname" required> <input
					type="submit" class="btn btn-default" role="button" value="추가">
				<a href="main.inc" class="btn btn-primary btn-lg" role="button">돌아가기</a>
			</form>
		</div>
	</section>

	<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////     -->

	<script src="./js/jquery-1.10.2.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jasny-bootstrap.min.js"></script>
	<script type="text/javascript">
		//divcheck
		function divCheck(chkid) {

			$.ajax({
				url : "adminDivModify.inc",
				type : "post",
				data : {
					div : chkid
				},
				dataType : "json",
				success : function(jarr) {

				},
				error : function(json) {
					alert("error");

				}
			});
		};

		//loccheck
		function locCheck(chkid) {

			$.ajax({
				url : "adminLocModify.inc",
				type : "post",
				data : {
					loc : chkid
				},
				dataType : "json",
				success : function(jarr) {

				},
				error : function(json) {
					alert("error");

				}
			});
		};

		//deptCheck
		function deptCheck(chkid) {

			$.ajax({
				url : "adminDeptModify.inc",
				type : "post",
				data : {
					dept : chkid
				},
				dataType : "json",
				success : function(jarr) {

				},
				error : function(json) {
					alert("error");

				}
			});
		};

		//duplication check : divname
		function checkdivname() {

			var truefalse = confirm("사업부를 추가하시겠습니까?");

			if (truefalse) {

			} else {
				return false;
			}

			var lists = [];

			<c:forEach items="${divlist}" var="divlist">
			var divid = "${divlist.divid}";
			var divname = "${divlist.divname}";
			var checkyn = "${divlist.checkyn}";
			var obj = {
				divid : divid,
				divname : divname,
				checkyn : checkyn
			};
			lists.push(obj);
			</c:forEach>

			for (var i = 0; i < lists.length; i++) {
				if (divadd.divname.value == lists[i].divname) {
					alert("이미 존재하는 사업부 입니다.");
					return false;
				}
			}
		}

		//duplication check : locname
		function checklocname() {

			var truefalse = confirm("근무지를 추가하시겠습니까?");

			if (truefalse) {

			} else {
				return false;
			}

			var lists = [];

			<c:forEach items="${locList}" var="locList">
			var locid = "${locList.locid}";
			var locname = "${locList.locname}";
			var checkyn = "${locList.checkyn}";
			var obj = {
				locid : locid,
				locname : locname,
				checkyn : checkyn
			};
			lists.push(obj);
			</c:forEach>

			for (var i = 0; i < lists.length; i++) {
				if (locadd.locname.value == lists[i].locname) {
					alert("이미 존재하는 근무지 입니다.");
					return false;
				}
			}
		}

		//duplication check : (deptname, divname)
		function checkdeptname() {

			var truefalse = confirm("부서를 추가하시겠습니까?");

			if (truefalse) {

			} else {
				return false;
			}

			var lists = [];

			<c:forEach items="${deptList}" var="deptList">
			var divname = "${deptList.divname}";
			var deptname = "${deptList.deptname}";
			var obj = {
				divname : divname,
				deptname : deptname
			};
			lists.push(obj);
			</c:forEach>

			for (var i = 0; i < lists.length; i++) {
				if (deptadd.deptname.value == lists[i].deptname
						&& deptadd.divname.value == lists[i].divname) {
					alert("이미 존재하는 부서 입니다.");
					return false;
				}
			}

		}
	</script>
</body>
</html>
