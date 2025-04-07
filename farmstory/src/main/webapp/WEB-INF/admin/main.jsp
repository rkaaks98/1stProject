<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<title>관리자 메인</title>
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
							<li><a href="/farmstory/admin/product-list.do">상품목록</a></li>
							<li><a href="/farmstory/admin/product-enroll.do">상품등록</a></li>
						</ol></li>
					<li><span>주문관리</span>
						<ol>
							<li><a href="/farmstory/admin/order-list.do">주문목록</a></li>
						</ol></li>
					<li><span>회원관리</span>
						<ol>
							<li><a href="/farmstory/admin/user-list.do">회원목록</a></li>
							<li><a href="#">회원등록</a></li>
						</ol></li>
				</ul>
			</aside>

			<section>
				<nav>
					<h3>관리자 메인</h3>
				</nav>

				<article>
					<h3>
						<a href="/farmstory/admin/product-list.do">상품현황</a> <a
							href="/farmstory/admin/product-list.do" class="more"> + 더보기</a>
					</h3>

					<table border="0">
						<tr>
							<th>상품번호</th>
							<th>상품명</th>
							<th>구분</th>
							<th>가격</th>
							<th>재고</th>
							<th>등록일</th>
						</tr>

						<c:forEach var="product" items="${products}">
							<tr>
								<td>${product.id}</td>
								<td>${product.name}</td>
								<td>${product.category}</td>
								<td>${product.price}원</td>
								<td>${product.stock}개</td>
								<td>${product.registerDate.substring(0,10)}개</td>
							</tr>
						</c:forEach>

					</table>

				</article>

				<Article>
					<h3>
						<a href="/farmstory/admin/order-list.do">주문현황</a> <a
							href="/farmstory/admin/order-list.do" class="more"> + 더보기</a>
					</h3>

					<table>
						<tr>
							<th>주문번호</th>
							<th>상품명</th>
							<th>판매가격</th>
							<th>수량</th>
							<th>배송비</th>
							<th>합계</th>
							<th>주문자</th>
							<th>주문일</th>
						</tr>
						<c:forEach var="order" items="${orders}">
							<tr>
								<td>${order.id}</td>
								<td>${order.product.name}</td>
								<td>${order.product.price}원</td>
								<td>${order.amount}</td>
								<td>${order.product.deliveryFee}</td>
								<td>${order.totalPrice}</td>
								<td>${order.user.name}</td>
								<td>${order.placedDate}</td>
							</tr>
						</c:forEach>

					</table>
				</article>
				<article>
					<h3>
						<a href="/farmstory/admin/user-list.do">회원현황</a> <a
							href="/farmstory/admin/user-list.do" class="more"> + 더보기</a>
					</h3>

					<table>
						<tr>
							<th>회원아이디</th>
							<th>이름</th>
							<th>닉네임</th>
							<th>휴대폰</th>
							<th>이메일</th>
							<th>등급</th>
							<th>회원가입일</th>
						</tr>

						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.id}</td>
								<td>${user.name}</td>
								<td>${user.nickname}</td>
								<td>${user.phoneNum}</td>
								<td>${user.email}</td>
								<td>${user.level}</td>
								<td>${user.registerDate}</td>
							</tr>
						</c:forEach>

					</table>
				</article>
			</section>
		</main>
		<%@ include file="./_footer.jsp"%>
	</div>
</body>
</html>
