<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>어드민 - 상품등록</title>
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
			<section id="productRegister">
				<nav>
					<h3>상품목록</h3>
				</nav>
				<article>
					<form action="/farmstory/admin/product-enroll.do" method="post"
						enctype="multipart/form-data">
						<table border="0">
							<tr>
								<td>상품명</td>
								<td><input type="text" name="prodName" /></td>
							</tr>
							<tr>
								<td>종류</td>
								<td><select name="cateNo">
										<option value="종류">종류</option>
										<option value="과일">과일</option>
										<option value="야채">야채</option>
										<option value="곡류">곡류</option>
								</select></td>
							</tr>
							<tr>
								<td>가격</td>
								<td><input type="text" name="prodPrice" id="price" /></td>
							</tr>
							<tr>
								<td>포인트</td>
								<td><input type="text" name="point" id="point" value="100" />
									포인트는 가격의 1%</td>
							</tr>
							<tr>
								<td>할인</td>
								<td><select name="prodDiscount">
										<option value="5">5%</option>
										<option value="12">12%</option>
										<option value="15">15%</option>
										<option value="18">18%</option>
										<option value="20">20%</option>
								</select></td>
							</tr>
							<tr>
								<td>배송비</td>
								<td><label><input type="radio" name="delivery"
										value="2000">2,000원</label> <label><input type="radio"
										name="delivery" value="3000">3,000원</label> <label><input
										type="radio" name="delivery" value="5000">5,000원</label> <label><input
										type="radio" name="delivery" value="0">무료</label></td>
							</tr>
							<tr>
								<td>재고</td>
								<td><input type="text" name="prodStock" /></td>
							</tr>
							<tr>
								<td>상품이미지</td>
								<td>
									<p>
										<span>상품목록 이미지(약 120 x 120)</span> <input type="file"
											name="multImage1" />
									</p>
									<p>
										<span>기본정보 이미지(약 240 x 240)</span> <input type="file"
											name="multImage2" />
									</p>
									<p>
										<span>상품설명 이미지(약 750 x Auto)</span> <input type="file"
											name="multImage3" />
									</p>
								</td>
							</tr>
							<tr>
								<td>기타</td>
								<td><textarea name="etc"></textarea></td>
							</tr>
						</table>
						<p>
							<a href="/farmstory/admin/product-list.do" class="btnCancel">취소</a>
							<input type="submit" id="btnSubmit" value="상품등록" />
						</p>
					</form>

				</article>
			</section>
		</main>
		<%@ include file="./_footer.jsp"%>
	</div>
</body>
</html>