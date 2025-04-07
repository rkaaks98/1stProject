<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>어드민 - 회원목록</title>
<link rel="stylesheet" href="/farmstory/style/admin.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_header.jsp"%>
		<main>
			<aside id="manu">
				<h3>주요기능</h3>
				<ul>
					<li><span>상품관리</span>
						<ol>
							<li class="${pageName == 'product-list' ? 'active' : ''}"><a
								href="/farmstory/admin/product-list.do">상품목록</a></li>
							<li class="${pageName == 'product-enroll' ? 'active' : ''}"><a
								href="/farmstory/admin/product-enroll.do">상품등록</a></li>
						</ol></li>
					<li><span>주문관리</span>
						<ol>
							<li class="${pageName == 'order-list' ? 'active' : ''}"><a
								href="/farmstory/admin/order-list.do">주문목록</a></li>
						</ol></li>
					<li><span>회원관리</span>
						<ol>
							<li class="${pageName == 'user-list' ? 'active' : ''}"><a href="/farmstory/admin/user-list.do">회원목록</a></li>
							<li><a href="#">회원등록</a></li>
						</ol></li>
				</ul>
			</aside>

			<section id="userList">
				<nav>
					<h3>회원목록</h3>
				</nav>
				<article>
					<form
						action="${pageContext.request.contextPath}/admin/order-delete.do"
						method="post"></form>
					<table border="0">
						<tr>
							<th><input type="checkbox" id="select_all"
								onclick="selectAll(this)"></th>
							<th>아이디</th>
							<th>이름</th>
							<th>별명</th>
							<th>이메일</th>
							<th>휴대폰</th>
							<th>등급</th>
							<th>가입일</th>
							<th>확인</th>
						</tr>
						<c:choose>
							<c:when test="${not empty articles}">
								<c:forEach var="user" items="${articles}">
									<tr>
										<td><input type="checkbox" name="userIds"
											value="${user.id}"></td>
										<td>${user.id}</td>
										<td>${user.name}</td>
										<td>${user.nickname}</td>
										<td>${user.email}</td>
										<td>${user.phoneNum}</td>
										<td>${user.level}</td>
										<td>${user.registerDate}</td>
										<td><a href="/farmstory/user/view.do?no=${user.id}">[상세확인]</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="10" style="text-align: center; padding: 20px;">회원이
										없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>

					<div class="pagination">
						<%-- 이전 페이지 --%>
						<c:if test="${currentPage > 1}">
							<a href="/farmstory/admin/user-list.do?pg=${currentPage - 1}">이전</a>
						</c:if>

						<%-- 페이지 번호 출력 --%>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${i == currentPage}">
								<span class="current-page">${i}</span>
							</c:if>
							<c:if test="${i != currentPage}">
								<a href="/farmstory/admin/user-list.do?pg=${i}">${i}</a>
							</c:if>
						</c:forEach>

						<%-- 다음 페이지 --%>
						<c:if test="${currentPage < lastPageNum}">
							<a href="/farmstory/admin/user-list.do?pg=${currentPage + 1}">다음</a>
						</c:if>
					</div>

				</article>
			</section>
		</main>
		<%@ include file="./_footer.jsp"%>
	</div>
</body>
</html>

<script>
	// 전체 선택/해제 처리
	function selectAll(source) {
		checkboxes = document.getElementsByName('userIds');
		for ( var i in checkboxes)
			checkboxes[i].checked = source.checked;
	}
</script>