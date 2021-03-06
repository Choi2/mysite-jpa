<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath}/board" method="post">
					<input type="text" id="word" name="word" value="${pager.word}">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">				
						<tr>
							<td>${vo.no}</td>
	
							<td style="text-align:left; padding-left:${(vo.depth) * 20}px">
							<c:if test="${vo.depth > 0}">
								<img src="${pageContext.servletContext.contextPath}/assets/images/reply.png"/>
							</c:if>
								<c:choose>
									<c:when test="${vo.boardDelete eq true}">
										<span style="font-weight:bold;">지워진 글입니다.</span>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.servletContext.contextPath}/board/${vo.no}">${vo.title}</a>
									</c:otherwise>
								</c:choose>
							</td>

							<td>${vo.user.name}</td>
							<td>${vo.readCount}</td>
							<td><fmt:formatDate value="${vo.regDate}" pattern="yyyy-MM-dd"/></td> 

							<td>
								<c:if test="${(sessionScope.authUser.no eq vo.user.no) and vo.boardDelete eq false}">
									<a href="${pageContext.servletContext.contextPath}/board/delete/${vo.no}" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>


				<div class="pager">
					<ul>
						<c:if test="${pager.leftArrow eq true}">
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${pager.startPage - 1}&word=${pager.word}">◀</a></li>
						</c:if>
						
						<c:forEach begin="${pager.startPage}" end="${pager.endPage}" varStatus="status">
							<li>
								<c:if test="${param.page == status.index}">	
									<a style="color:red;" href="${pageContext.servletContext.contextPath}/board?page=${status.index}&word=${word}">${status.index}</a>
								</c:if>
									
								<c:if test="${param.page != status.index}">
									<a href="${pageContext.servletContext.contextPath}/board?page=${status.index}&word=${word}">${status.index}</a>
								</c:if>
							</li>
						</c:forEach>
						
						<c:forEach begin ="${pager.endPage + 1}" end = '5'  varStatus="status">
							<li style="color:gray;">${status.index}</li>
						</c:forEach>
						
						<c:if test="${pager.rightArrow eq true}">
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${pager.endPage + 1}&word=${pager.word}">▶</a></li>
						</c:if>
					</ul>
				</div>
			
				<c:if test="${sessionScope.authUser ne null}">
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/write" id="new-book">글쓰기</a>
				</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>