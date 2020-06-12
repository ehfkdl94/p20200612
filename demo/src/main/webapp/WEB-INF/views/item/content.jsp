<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		번호 : ${obj.itemno}<br />
		물품 : ${obj.itemname}<br />
		가격 : ${obj.itemprice}<br />
		재고 : ${obj.itemqty}<br />
		설명 : ${obj.itemdes}<br />
		날짜 : ${obj.itemdate}<br />
		
		<a href="/board/list" class="btn btn-success">목록</a>
		
	</div>
</body>
</html>