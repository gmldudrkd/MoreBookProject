<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ����</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div style="height:600px">
	<table style="margin:20px">
		<tr><td>�� <a href="../book/SearchBook">�����˻�</a></td></tr>
		<tr><td>�� <a href="../book/PostBook?type=list">��������(���İ�)</a></td></tr>
		<tr><td>�� <a href="../book/MemberBook">ȸ������</a></td></tr>
	</table>
	<img src="../img/bookmain.jpg" style="margin:20px"><br>
</div>

<jsp:include page="Tail.jsp"/>
</body>
</html>