<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>어드민 - 상품목록</title>
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
							<li class="${pageName == 'user-list' ? 'active' : ''}"><a
								href="/farmstory/admin/user-list.do">회원목록</a></li>
							<li><a href="#">회원등록</a></li>
						</ol></li>
				</ul>
			</aside>

			<section id="productList">
				<nav>
					<h3>상품목록</h3>
				</nav>
				<article>

					<form
						action="${pageContext.request.contextPath}/admin/product-delete.do"
						method="post">
						<table border="0">
							<tr>
								<th><input type="checkbox" id="select_all"
									onclick="selectAll(this)"></th>
								<th>사진</th>
								<th>상품번호</th>
								<th>상품명</th>
								<th>구분</th>
								<th>가격</th>
								<th>재고</th>
								<th>등록일</th>
							</tr>
							<c:choose>
								<c:when test="${not empty articles}">
									<c:forEach var="product" items="${articles}">
										<tr>
											<td><input type="checkbox" name="productIds"
												value="${product.id}"></td>
											<td><img
												src="${pageContext.request.contextPath}${product.image.thumbnailLocation}"
												alt="${product.name}" class="thumb"></td>
											<td>${product.id}</td>
											<td>${product.name}</td>
											<td>${product.category}</td>
											<td>${product.price}원</td>
											<td>${product.stock}개</td>
											<td>${product.registerDate.substring(0,10)}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="10" style="text-align: center; padding: 20px;">상품이
											없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>

						<input type="submit" value="선택 삭제"
							style="border: none; background: none; cursor: pointer; padding: 10px;"
							onclick="return confirm('선택한 주문을 삭제하시겠습니까?');" /> <a
							href="/farmstory/admin/product-enroll.do" class="productRegister">상품등록</a>

						<div class="pagination">
							<%-- 이전 페이지 --%>
							<c:if test="${currentPage > 1}">
								<a href="/farmstory/admin/product-list.do?pg=${currentPage - 1}">이전</a>
							</c:if>

							<%-- 페이지 번호 출력 --%>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i == currentPage}">
									<span class="current-page">${i}</span>
								</c:if>
								<c:if test="${i != currentPage}">
									<a href="/farmstory/admin/product-list.do?pg=${i}">${i}</a>
								</c:if>
							</c:forEach>

							<%-- 다음 페이지 --%>
							<c:if test="${currentPage < lastPageNum}">
								<a href="/farmstory/admin/product-list.do?pg=${currentPage + 1}">다음</a>
							</c:if>
						</div>
					</form>

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
		checkboxes = document.getElementsByName('productIds');
		for ( var i in checkboxes)
			checkboxes[i].checked = source.checked;
	}
</script>
