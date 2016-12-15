<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/reset2.css" rel="stylesheet" type="text/css">
    <link href="css/adminForm.css" rel="stylesheet" type="text/css">
    
    <!-- Bootstrap -->
	<link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/custom2.css" rel="stylesheet">
    
  </head>
  <body>
    <section>
      <div class="form">
        <form role="form" class="form-inline" action="divAdd.inc"	method="post">
        <table>
          <thead>
            <tr>
              <td>사업부</td>
              <td>사용여부</td>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${divlist}" var="divlist">	
		  		<tr>
					<td> ${divlist.divname} </td>
					<td>  <input type="checkbox" name="divchk" id="divchk"> </td>	    
				</tr>
		      	</c:forEach>
          </tbody>
        </table>
        		<input type="text" class="form-control" placeholder="사업부 추가" size=90px id=divname name=divname>
				<button type="submit" class="btn btn-default" role="button">추가</button>	 
				<a href="" class="btn btn-primary" role="button">수정	</a>
        </form>
        
        
        <form role="form" class="form-inline" action="deptAdd.inc"	method="post">
        <table>
          <thead>
            <tr>
              <td>부서</td>
              <td>사업부</td>
              <td>사용여부</td>
            </tr>
          </thead>
          <tbody>
           <c:forEach items="${depList}" var="depList">	
			  		<tr>
						<td> ${depList.deptname} </td>
						<td>
						
	
							<select class="form-control" required>
			  						<c:forEach items="${divlist}" var="divlist">	
										<option> ${divlist.divname}	</option>    
			      					</c:forEach>	      	
		    				 </select>
						</td>
						<td>  <input type="checkbox" name="depchk" id="depchk"> </td>	    
					</tr>
			    </c:forEach>	
          </tbody>
        </table>
        
					<input type="text" class="form-control" placeholder="팀 추가" size=70px name="deptname" id="deptname"> 
					<select class="form-control" name="divname" id="divname" required>
			  						<c:forEach items="${divlist}" var="divlist">	
										<option > ${divlist.divname} </option>    
			      					</c:forEach>	      	
		    		</select>
					<button type="submit" class="btn btn-default" role="button">추가</button>	 
					<a href="" class="btn btn-primary" role="button">수정	</a>
		</form>
        
        <form role="form" class="form-inline" action="locAdd.inc"	method="post">
        <table>
          <thead>
            <tr>
              <td>근무지</td>
              <td>사용여부</td>
            </tr>
          </thead>
          <tbody>
          
          		<c:forEach items="${locList}" var="locList">	
			  		<tr>
						<td> ${locList.locname} </td>
						<td>  <input type="checkbox" name="locchk" id="locchk"> </td>	    
					</tr>
			    </c:forEach>	
			    
          </tbody>
        </table>
        			<input type="text" class="form-control" placeholder="근무지 추가" size=90px name="locname" id="locname">
					<button type="submit" class="btn btn-default" role="button">추가</button>	
					<a href="" class="btn btn-primary" role="button">수정	</a>
		</form> 
      </div>
    </section>
  </body>
</html>
