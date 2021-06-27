<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>

<div style='text-align:center; top-margin:100px'>
☆책은 마음의 양식☆ <br>
<h3>도서관리 사용자 로그인</h3>
</div>

<form method="post" name="sendinfo" style='text-align:center'>
	<input type="hidden" name="mode" id="mode" value="loginform">
	아이디 : <input type="text" name="userid"><br>
	패스워드 : <input type="password" name="password"><br><br>
	<input type="submit" value="로그인" onclick='btn_click("login");' >
	<input type="submit" value="회원가입" onclick='btn_click("signup");' >
</form>

</body>
</html>

<script>
function btn_click(str){
	if(str=="login"){                                 
		sendinfo.action="login";      
	} else if(str=="signup"){  
		sendinfo.action="signup";      
	}
}
</script>
