<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/user.js"></script>
<style type="text/css">
	div
	{
		text-align: center;
	}
</style>
</head>
<body>

<%@ include file="../header.jsp" %>
<form id="loginfrm" name="loginfrm">
	<table style="height: 40%; margin: auto; margin-top: 20%;">
		<tr>
			<td>아이디 :</td>
			<td><input type="text" name="id" id="id"></td>
			<td rowspan="2"><input type="button" value="로그인" onclick="login()"  style="height: 100%;"></td>
		</tr>
		<tr>
			<td>비밀번호 :</td>
			<td><input type="password" name="pw" id="pw" onkeydown="pressEnter()"></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<a href="/join">회원가입</a>
				<a href="/">메인으로</a>
			</td>
		</tr>
	</table>
</form>
	
</body>
</html>