<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서검색</title>
</head>
<body>
<jsp:include page="Header.jsp"/>

<div style="height:500px">
<form action="SearchBook" method="post" style='margin:20px' >
	<input type="hidden" name="mode" id="mode" value="signin">
	책 제목 : <input type="text" name="searchfield">
	<input type="submit" value="검색">
	
	<div name="result" style="border:1px;margin:20px">
		검색된 책이 없습니다.
		
		<c:forEach var="member" items="${bookitem}">
			${bookitem.title},
		</c:forEach>

	</div>
</form>
</div>

<jsp:include page="Tail.jsp"/>
</body>
</html>