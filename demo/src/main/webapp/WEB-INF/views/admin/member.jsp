<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
		rel="stylesheet" />
</head>
<body>
	<div  class="container">
		<h4>아이템 게시판</h4>
		<form action="/admin/member" method="post">
		<a href="/admin/iteminsert" class="btn btn-success">일괄추가</a>
		<input type="submit" name="btn" class="btn btn-success" value="일괄수정" />
		<input type="submit" name="btn" class="btn btn-success" value="일괄삭제" />
		
		<table class="table table-sm">
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th>아이디</th>
					<th>이름</th>
					<th>번호</th>
					<th>나이</th>
					<th>가입일자</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="tmp" items="${list}">
				<tr>
					<th><input type="checkbox" name="chk[]" value="${tmp.userid}"/></th>
					<td>${tmp.userid}</td>
					<td>${tmp.username}</td>
					<td>${tmp.phone}</td>
					<td>${tmp.userage}</td>
					<td>${tmp.joindate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#">Previous</a></li>
				<c:forEach var="i" begin="1" end="${cnt}" step="1">
				    <li class="page-item"><a class="page-link" href="/admin/item?page=${i}">${i}</a></li>
			    </c:forEach>
			    <li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
			</nav>
		<div align="right">
			<button type="button" class="btn btn-primary"  onclick="location.href='/'" id="btnList">홈</button>
		</div>
		</form>
	</div>
</body>
</html>