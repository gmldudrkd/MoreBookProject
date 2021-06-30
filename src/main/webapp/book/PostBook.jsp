<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서관리</title>
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

<input type="hidden" name="type" value="${type }">
<div style="height:600px;margin:10px">
	
	<h3>☆나의 독후감☆</h3>
	
	<div style="font-size:13px;margin-bottom:5px">▶ <a href="../book/PostBookUpdate?type=new">신규등록</a></div>
	<table>
		<tr style='background-color:#BABC88;height:20px'>
			<th>No.</th>
			<th>제목</th>
			<th>평점</th>
			<th>등록일</th>
			<th >수정일</th>
		</tr>
		
		<c:if test="${empty bookDao }" >
		<tr style='height:13px'>
			<td colspan="6">등록된 독후감이 없습니다.</td>
		</tr>
		</c:if>
		
		<c:if test="${!empty bookDao }" >			
			<c:forEach var="bookposts" items="${bookDao}" varStatus="status">
			<tr style='height:20px'>
				<td width="10%">${status.count }</td>
				<td width="40%"> <a href="../book/PostBookUpdate?type=update&num=${bookposts.no }">${bookposts.posttitle }</a></td>
				<td width="10%">${bookposts.evaluate }점 / 5점</td>
				<td width="10%">${bookposts.postDate }</td>
				<td width="10%" >${bookposts.updateDate }</td>
			</tr>
			</c:forEach>	
		</c:if>
		
	</table>
</div>

<jsp:include page="Tail.jsp"/>

</body>
</html>


<script>
function btn_click(str, num){
	sendinfo.action="PostBookUpdate?type=del&num="+num;
}
</script>

