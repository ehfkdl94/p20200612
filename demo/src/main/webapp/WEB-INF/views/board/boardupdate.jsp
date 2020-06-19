<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 수정</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
		<form action="/board/boardupdate" method="post">
			
			<table class="table table-sm">
				<thead>
					<tr>
						<th>번호</th>
						<th>글제목</th>
						<th>글내용</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>날짜</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td><input type="text" name="brd_no" value="${obj.brd_no}" readonly/></td>
						<td><input type="text" name="brd_title" value="${obj.brd_title}"/></td>
						<td><input type="text" name="brd_content" value="${obj.brd_content}"/></td>
						<td><input type="text" name="brd_id" value="${obj.brd_id}" readonly/></td>
						<td><input type="text" name="brd_hit" value="${obj.brd_hit}" readonly/></td>
						<td><input type="text" name="brd_date" value="${obj.brd_date}" readonly/></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" name="btn" class="btn btn-success" value="게시글 수정" />
		</form>
	</div>
</body>
</html>