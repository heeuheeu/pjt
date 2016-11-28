<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
img {
	width: 50px;
	height: 50px;
}
</style>
<title>신세계I&C 최강 신입사원</title>


<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/custom2.css" rel="stylesheet">

</head>

<h2>Example of using events of Modal Plugin</h2>

<body>

<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">Launch demo modal</button>


<!-- Modal -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">Ã</button>
				<h4 class="modal-title" id="myModalLabel">This Modal title</h4>
			</div>
			<div class="modal-body">
			<!-- 모달 내용-->
			
			
			
			
			
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					Close</button>
				<button type="button" class="btn btn-primary">Submit
					changes</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./common/index.js"></script>
<script type="text/javascript">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	    
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="../js/bootstrap.min.js"></script>

	    <script>
	        $(function () { $('#myModal').modal('hide')})});
	    </script>
	    <script>
	       $(function () { $('#myModal').on('hide.bs.modal', function () {
	          alert('Hey, I heard you like modals...');})
	       });
	    </script>    

</body>
</html>