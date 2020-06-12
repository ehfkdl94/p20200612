<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
		rel="stylesheet" />
</head>
<body>
	<div  class="container">
		<h4>아이템 게시판</h4>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제품명</th>
					<th>가격</th>
					<th>수량</th>
					<th>내용</th>
					<th>가입일자</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.itemno}</td>
					<td><a href="/item/content?no=${tmp.itemno}">${tmp.itemname}</a></td>
					<td>${tmp.itemprice}</td>
					<td>${tmp.itemqty}</td>
					<td>${tmp.itemdes}</td>
					<td>${tmp.itemdate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#">Previous</a></li>
				<c:forEach var="i" begin="1" end="${cnt}" step="1">
				    <li class="page-item"><a class="page-link" href="/item/itemlist?page=${i}">${i}</a></li>
			    </c:forEach>
			    <li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
			</nav>
		<div align="right">
			<button type="button" class="btn btn-sm btn-primary"  onclick="location.href='/'" id="btnList">홈</button>
			<button type="button" class="btn btn-sm btn-primary"  onclick="location.href='/board/insert'" id="btnList">글쓰기</button>
		</div>
		
	</div>
</body>
</html>