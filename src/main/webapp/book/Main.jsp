<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>나의 서재</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div style="height:600px">
	<table style="margin:20px">
		<tr><td>▶ <a href="../book/SearchBook">도서검색</a></td></tr>
		<tr><td>▶ <a href="../book/PostBook?type=list">도서관리(독후감)</a></td></tr>
		<tr><td>▶ <a href="../book/MemberBook">회원정보</a></td></tr>
	</table>
	<img src="../img/bookmain.jpg" style="margin:20px"><br>
</div>

<jsp:include page="Tail.jsp"/>
</body>
</html>