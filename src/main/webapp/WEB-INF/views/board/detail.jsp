<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="path" id="path" content="${pageContext.request.contextPath}">
<title>글 상세 보기</title>
<style type="text/css">
	a
	{
		padding: 3px;
	}
	.cta
	{
		border: none;
		height : 50px;
	}
	
	textarea
	{
		resize: none;
		
		width: 100%;
	}
	th
	{
		width: 100px;
	}
	div
	{
		text-align: center;
	}
	table 
	{
		margin: auto;
		margin-top: 10%;
		width: 1000px;
	}
	img
	{
		width: 150px;
		height: 150px;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/detail.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form id="detailform" action="/anwritego" method="get">
		<input type="hidden" id="bno" 	 	name="bno" 		  value="${vo.bno}">
		<input type="hidden" id="uno" 	 	name="uno" 		  value="${user.uno}">
		<input type="hidden" id="cno" 	 	name="cno" 		  value="">
		<input type="hidden" id="id"  	 	name="id" 		  value="${user.id}">
		<input type="hidden" id="fname"  	name="fname" 	  value="${fvo.fname}">
		<input type="hidden" id="origino"	name="origino" 	  value="${vo.origino}" >
		<input type="hidden" id="groupord"	name="groupord"   value="${vo.groupord}" >
		<input type="hidden" id="grouplayer"name="grouplayer" value="${vo.grouplayer}" >
		<table id="tb">
			
			<tr>
				<th>작성자</th>
				<td>${vo.writer}</td>
				<th>작성일</th>
				<td>${vo.bdate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					${vo.title}
				</td>
				<th>조회수</th>
				<td>
					${vo.hit}
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" disabled="disabled" >${vo.btext}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					첨부파일
				</th>
				<td colspan="3" align="left">
					<a href="#" id="file">${fvo.fname}</a><br>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
				<c:if test="${vo.uno == user.uno}">
					<input type="button" value="수정하기" onclick="location.href='/mody?bno=${vo.bno}'">
				</c:if>
				<c:if test="${vo.uno == user.uno}">
					<input type="button" value="삭제하기" id="delete">
				</c:if>
					<input type="button" value="답글" onclick="bloginCheck()">
					<input type="button" value="메인으로" onclick="location.href='/'">
				</td>
			</tr>
			<tr>
				<th>댓글</th>
				<td colspan="3">
					<textarea rows="7" placeholder="댓글을 입력하세요" name="ctext" id="ctext"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="등록" onclick="commentwrite()">
				</td>
			</tr>
			<tbody id="nocoment">
				<tr>
					<td></td>
					<td align="center" colspan="3">
						등록된 댓글이 없습니다.
					</td>
				</tr>
			</tbody>
		</table>
		<table style="margin: auto; width: 1000px;" id="comment">
		
		</table>
		<table style="margin: auto; width: 1000px;">
		<tr>
			<td></td>
				<td colspan="3" align="center" id="commentpaginglist">
				
				</td>
			</tr>
		</table>
	</form>
</body>
</html>