<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서검색</title>
</head>

<style>
table{
	border: 1px solid;
	border-collapse: collapse;
	margin-bottom:10px;
	font-size:13px;
	height:25px;
	width:95%;
	text-align:center;
}
th, td {
    border: 1px solid #444444;
 }
th{
width:7%;
} 
</style>

<body>
<jsp:include page="Header.jsp"/>

<div style="height:600px">
<form action="SearchBook" method="post" style='margin:20px' >
	<input type="hidden" name="mode" id="mode" value="signin">
	책 제목 : <input type="text" name="searchfield" value="${searchfield }">
	<input type="submit" value="검색">
	
	<div name="result" style="margin-top:20px;margin-left:10px;overflow:scroll; width:100%; height:550px; ">
		<c:if test="${setcheck eq '' }" >
		검색된 책이 없습니다.
		</c:if>
		
		<c:if test="${setcheck eq 'Y' }" >
			<c:forEach var="bookitems" items="${bookitem}" varStatus="status">
				<table>
					<tr>
						<th>제목</th>
						<td colspan="3">${bookitems.title }</td>
						<td rowspan="3" width="10%"><img src="${bookitems.image }"></td>
					</tr>
					<tr>
						<th >작가</th>
						<td width="30%">${bookitems.author }</td>
						<th>가격</th>
						<td width="30%">${bookitems.price }원</td>
					</tr>
					<tr>
						<th>출판사</th>
						<td>${bookitems.publisher }</td>
						<th>출판일</th>
						<td>${bookitems.pubdate }</td>
					</tr>
					<tr>
						<th>상세내용</th>
						<td colspan="4">${bookitems.description }</td>
					</tr>
				</table>
			</c:forEach>
		</c:if>
	</div>
</form>
</div>

<jsp:include page="Tail.jsp"/>
</body>
</html>