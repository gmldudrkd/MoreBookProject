<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<div style='text-align:center; top-margin:100px'>
☆회원가입을 환영합니다☆ <br>
<h3>사용자 회원가입</h3>
</div>

<form action="signup" method="post" style='text-align:center' >
	<input type="hidden" name="mode" id="mode" value="signin">
	아이디 : <input type="text" name="userid"><br>
	암호 : <input type="password" name="password"><br>
	이름 : <input type="text" name="name"><br>
	<input type="submit" value="회원가입">
</form>

</body>
</html>