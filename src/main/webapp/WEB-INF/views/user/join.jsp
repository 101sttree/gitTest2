<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/user.js"></script>
<style type="text/css">
	div
	{
		text-align: center;
	}
	#no
	{
		width: 150px;
	}
	#no
	{
		color: red;
	}
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form id="joinfrm" name="joinfrm" method="post" action="/joinok">
		<table style="height: 40%; margin: auto; margin-top: 20%;">
			<tr>
				<td style="width: 110px;">아이디 :</td>
				<td style="width: 150px;"><input type="text" name="id" id="jid"></td>
				<td style="width: 90px;"><input type="button" value="중복확인" onclick="idck()"></td>
			</tr>
			<tbody id="idno">
			</tbody>
			<tr>
				<td>비밀번호 :</td>
				<td><input type="password" name="pw" id="jpw"></td>
				<td></td>
			</tr>
			<tbody id="pwno">
			</tbody>
			<tr>
				<td>비밀번호확인 :</td>
				<td><input type="password" name="pwck" id="pwck"></td>
				<td></td>
			</tr>
			<tbody id="pwckno">
			</tbody>
			<tr>
				<td>이름 :</td>
				<td><input type="text" name="name" id="name"></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="button" value="가입" onclick="join()">
					<input type="button" value="취소" onclick="location.href='/'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>