<%@ page import="book.vo.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<jsp:useBean id="book" scope="session" class="book.vo.Book"/>

<div style="background-color:#A9BCF5;color:#FFFFFF;height:25px;padding:5px;">
	<b>책책책을 읽읍시다!</b>&nbsp;
	<a style="color:white;" href="../book/main">목록으로</a>
	<span style="float:right;">
		${book.name }님 
		<a style="color:white;" href="../auth/logout">로그아웃</a>
	</span>
</div>