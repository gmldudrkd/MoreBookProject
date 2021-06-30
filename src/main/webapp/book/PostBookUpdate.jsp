<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<c:if test="${type eq 'new'}">독후감 등록</c:if>
	<c:if test="${type eq 'update'}">독후감 수정</c:if>
</title>
</head>

<style>
table{
	border: 1px solid;
	border-collapse: collapse;
	margin-bottom:10px;
	font-size:13px;
	height:500px;
	width:95%;
	text-align:center;
}
th, td {
    border: 1px solid #444444;
 }
th{
width:15%;
background-color:#BABC88;
} 
.date{
width:20%
}
.text{
width:80%
}
</style>

<body>

<jsp:include page="Header.jsp"/>

<div style="height:600px;margin:10px">

<h3>☆
	<c:if test="${type eq 'new'}">독후감 등록</c:if>
	<c:if test="${type eq 'update'}">독후감 수정</c:if>
☆</h3>

<form method="post" action="PostBookUpdate" style='text-align:center'>
<input type="hidden" name="type" value="${type }">
<c:if test="${type eq 'update'}">
<input type="hidden" name="no" value="${bookDao.no }">
</c:if>
<table>
	<tr >
		<th style="height:30px">제목</th>
		<td colspan="3"><input type="text" value="${bookDao.posttitle }" name="posttitle"  class="text"></td>
	</tr>
	<tr>
		<th style="height:30px;">평점</th>
		<td style="width:200px">
			<select  name="evalsel" id="evalsel" >
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5" selected>5</option>
			</select>점 / 5점
		</td>
		<th>독서기간</th>
		<td><input type="text" value="${bookDao.readDatest }" name="startdate" class="date" > 
		~ <input type="text" value="${bookDao.readDateen }" name="enddate"  class="date"  ></td>
	</tr>
	<tr>
		<th>이책에 <br>대하여<br>...</th>
		<td colspan="3"><textarea cols="80" rows="30" name="postdesc">${bookDao.postdesc }</textarea></td>
	</tr>
</table>

<input type="submit" value="저 장"  >
</form>
</div>

<jsp:include page="Tail.jsp"/>

</body>
</html>