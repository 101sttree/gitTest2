<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메인화면</title>
<style type="text/css">
	td, th 
	{
		border-bottom: 1px solid black;
	}
	div
	{
		text-align: center;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/main.js"></script>
</head>
<body>
<%@ include file="./header.jsp" %>
	<table style="width: 60%; height: 40%; margin: auto; margin-top: 15%;">
		<c:if test="${user eq null}">
			<tr>
				<td colspan="5" align="right"><input type="button" value="로그인"
					onclick="location.href='/login'"></td>
			</tr>
		</c:if>
		<c:if test="${user ne null}">
			<tr>
				<td colspan="5" align="right"><input type="button" value="로그아웃" id="logout"></td>
			</tr>
			<tr>
				<td colspan="5" align="left">${id}님환영합니다.</td>
			</tr>
		</c:if>

		<tr>
			<td colspan="5" align="center">
				<form action="/" method="get">
					<select name="searchType">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="btext">내용</option>
						<option value="tt">제목+내용</option>
					</select>
					<input type="text" placeholder="검색어를 입력하십시오" name="searchText">
					<input type="submit" value="검색">
				</form>
				
			</td>
		</tr>

		<tr>
			<th>글번호</th>
			<th width="60%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${list }" begin="0" end="${fn:length(list)}">
			<c:if test="${list.grouplayer < 4}">
			<tr>
				<th>${list.bno }</th>
				<c:choose>
					<c:when test="${list.grouplayer eq 0}">
						<td><a href="/board/detail?bno=${list.bno }">${list.title }</a></td>
					</c:when>
					<c:when test="${list.grouplayer eq 1}">
						<td>
							&nbsp;&nbsp;ㄴ
							<a href="/board/detail?bno=${list.bno }">${list.title }</a>
						</td>
					</c:when>
					<c:when test="${list.grouplayer eq 2}">
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ
							<a href="/board/detail?bno=${list.bno }">${list.title }</a>
						</td>
					</c:when>
					<c:when test="${list.grouplayer eq 3}">
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ
							<a href="/board/detail?bno=${list.bno }">${list.title }</a>
						</td>
					</c:when>
				</c:choose>
				
				<th>${list.writer }</th>
				<th>${list.bdate }</th>
				<th>${list.hit }</th>
			</tr>
			</c:if>
		</c:forEach>
		
		<tr>
			<td align="center" colspan="5">
				<c:if test="${paging.startPage != 1}">
				<!-- 5개의 페이지 번호의 시작 번호가 1이 아니면 -->
					<a href="/?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
					<!-- < 문자에  nowpage의 값에 시작번호에서 -1을 한 값과 페이지당 출력갯수5(고정)을 링크로 설정해준다-->
				</c:if> 
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
				<!-- < 다음의 숫자들이다. 반복문의 시작 값은  startPage이고 종료 값은 endPage 이다.-->		
					<c:choose>
						<c:when test="${p == paging.nowPage}">
						<!-- 현재 페이지가 nowPage 즉 객체의 nowPage값과 같다면 링크를 주지 않는다. -->
							<b>${p}</b>
						</c:when>
						<c:when test="${p != paging.nowPage}">
						<!-- 현재 페이지가 아닌 숫자에는 링크를 준다. -->
							<a href="/?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
							<!-- 해당숫자와 출력 게시글 갯수를 가진 링크를 준다. -->
						</c:when>
					</c:choose>
				</c:forEach> 
				<c:if test="${paging.endPage != paging.lastPage}">
				<!-- 
					현재 페이지에 표시된 5개의 숫자중 마지막 숫자가 전체 페이지의 끝과 같지 않을때
					다음 5개를 보여주는 창으로 이동하는 링크가 달린 > 문자를 생성한다.
				 -->
					<a href="/?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
				</c:if></td>
		</tr>
		<tr>
			<td align="right" colspan="5"><input type="button" value="글쓰기"
				onclick="loginCheck()"></td>
		</tr>
	</table>
</body>
</html>