<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<style type="text/css">
	img
	{
		width: 150px;
		height: 150px;
	}
	table 
	{
		margin: auto;
		margin-top: 10%;
		width: 1000px;
	}
	textarea
	{
		resize: none;
		width: 98%;
	}
	div
	{
		text-align: center;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/write.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form 		action="/board/write" method="post" name="writefrm" id="writefrm"
				enctype="multipart/form-data" >
		<input 	type="hidden" value="${user.uno }" 	name="uno">
		<input 	type="hidden" value="${id }" 		name="writer">
		<c:if test="${vo ne null}">
			<input 	type="hidden" value="${vo.bno}" 	name="bno">
			<input 	type="hidden" value="${vo.origino}" 	name="origino">
			<input 	type="hidden" value="${vo.groupord}" 	name="groupord">
			<input 	type="hidden" value="${vo.grouplayer}" 	name="grouplayer">
		</c:if>
		<table>
			<tr>
				<th>작성자</th>
				<td>${id }</td>
				<th>작성일</th>
				<td>2020-01-12</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" placeholder="제목을 입력하세요" style="width: 98%;" name="title" id="title">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요" name="btext" id="btext"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<input type="file" name="file" id="file"><br>
					<img id="img">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="등록" onclick="writerok()">
					<input type="button" value="취소" onclick="location.href='/'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>