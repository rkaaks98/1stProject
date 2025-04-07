<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>어드민 - 주문목록</title>
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
					<li class="${pageName == 'user-list' ? 'active' : ''}"><span>회원관리</span>
						<ol>
							<li class="${pageName == 'user-list' ? 'active' : ''}"><a href="/farmstory/admin/user-list.do">회원목록</a></li>
							<li><a href="#">회원등록</a></li>
						</ol></li>
				</ul>
			</aside>

			<section id="orderList">
				<nav>
					<h3>주문목록</h3>
				</nav>
				<article>
					<form
						action="${pageContext.request.contextPath}/admin/order-delete.do"
						method="post">
						<table border="0">
							<tr>
								<th><input type="checkbox" id="select_all"
									onclick="selectAll(this)"></th>
								<th>주문번호</th>
								<th>상품명</th>
								<th>판매가격</th>
								<th>수량</th>
								<th>배송비</th>
								<th>합계</th>
								<th>주문자</th>
								<th>주문일</th>
								<th>확인</th>
							</tr>
							<c:choose>
								<c:when test="${not empty articles}">
									<c:forEach var="order" items="${articles}">
										<tr>
											<td><input type="checkbox" name="orderIds"
												value="${order.id}"></td>
											<td>${order.id}</td>
											<td>${order.product.name}</td>
											<!-- 상품명 -->
											<td>${order.product.price}원</td>
											<!-- 판매가격 -->
											<td>${order.amount}</td>
											<!-- 수량 -->
											<td>${order.product.deliveryFee}원</td>
											<!-- 배송비 -->
											<td>${order.totalPrice}원</td>
											<!-- 합계 (판매가격 * 수량 + 배송비) -->
											<td>${order.user.name}</td>
											<!-- 주문자 -->
											<td>${order.placedDate}</td>
											<!-- 주문일 -->
											<td><a href="/farmstory/order/view.do?no=${order.id}">[상세확인]</a></td>
											<!-- 확인 링크 -->
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="10" style="text-align: center; padding: 20px;">게시물이
											없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>

						<input type="submit" value="선택 삭제"
							style="border: none; background: none; cursor: pointer; padding: 10px;"
							onclick="return confirm('선택한 주문을 삭제하시겠습니까?');">


						<div class="pagination">
							<%-- 이전 페이지 --%>
							<c:if test="${currentPage > 1}">
								<a href="/farmstory/admin/order-list.do?pg=${currentPage - 1}">이전</a>
							</c:if>

							<%-- 페이지 번호 출력 --%>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i == currentPage}">
									<span class="current-page">${i}</span>
								</c:if>
								<c:if test="${i != currentPage}">
									<a href="/farmstory/admin/order-list.do?pg=${i}">${i}</a>
								</c:if>
							</c:forEach>

							<%-- 다음 페이지 --%>
							<c:if test="${currentPage < lastPageNum}">
								<a href="/farmstory/admin/order-list.do?pg=${currentPage + 1}">다음</a>
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
		checkboxes = document.getElementsByName('orderIds');
		for ( var i in checkboxes)
			checkboxes[i].checked = source.checked;
	}
</script>