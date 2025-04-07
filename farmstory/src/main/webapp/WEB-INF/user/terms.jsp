<%@page import="farmstory.dto.TermDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<TermDTO> termsList = (List<TermDTO>) request.getAttribute("terms");
pageContext.setAttribute("terms", termsList);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>이용약관</title>
<link rel="stylesheet" href="/farmstory/style/common/header.css" />
<link rel="stylesheet" href="/farmstory/style/common/footer.css" />
<link rel="stylesheet" href="/farmstory/style/terms.css" />
<script type="text/javascript" src="/farmstory/js/terms/terms.js"></script>
</head>
<body>
	<div id="wrapper">
		<header>
			<section class="links">
				<img src="/farmstory/images/head_top_line.png" alt="" />
				<div>
					<p>
						<a href="">HOME | </a>
						<a href="">로그인 | </a>
						<a href="">회원가입 | </a>
						<a href="">나의정보 | </a>
						<a href="">로그아웃 | </a>
						<a href="">관리자 | </a>
						<a href="">고객센터</a>
					</p>
				</div>
			</section>
			<section class="logo">
				<div>
					<a href="#">
						<img src="/farmstory/images/logo.png" />
					</a>
					<a href="#">
						<img src="/farmstory/images/head_txt_img.png" />
					</a>
				</div>
			</section>
			<section class="services">
				<article>
					<div>
						<a href="#"> </a>
					</div>
					<div>
						<a href="#">
							<img src="/farmstory/images/head_menu_badge.png" alt="30%" />
						</a>
					</div>
					<div>
						<a href="#"> </a>
					</div>
					<div>
						<a href="#"> </a>
					</div>
					<div>
						<a href="#"> </a>
					</div>
				</article>
			</section>
		</header>
		<main>
			<section class="container">
				<c:forEach var="term" items="${requestScope.terms}">
					<div class="terms-area">
						<form action="#">
							<table>
								<tr>
									<td>
										<h3 class="title">${term.title}</h3>
									</td>
								</tr>
								<tr>
									<td>
										<textarea name="terms" id="terms" readonly>
${term.getContent()}</textarea>
									</td>
								</tr>
								<tr>
									<td>
										<label> <input type="checkbox" /> &nbsp;동의합니다.
										</label>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</c:forEach>
				<div class="btn-area">
					<button id="sendBtn">다음</button>
					<button>
						<a href="/farmstory/signin">취소</a>
					</button>
				</div>
			</section>
		</main>
		<footer>
			<div>
				<img src="/farmstory/images/footer_logo.png" alt="" />
				<p>
					(주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-부산진구-123호 / 벤처기업확인
					서울 지방중소기업청 제 012345678-9-01234호<br /> 등록번호 팜스토리01234 (2013.04.01)
					/ 발행인 : 홍길동 <br /> 대표: 김철수 / 이메일 : abc123@example.com / 전화 : 01)
					234-5678 / 부산광역시 부산진구 부전동 123 <br />
					<span>copyrigt© 김철수 All rights reserved.</span>
				</p>
			</div>
		</footer>
	</div>
</body>
</html>
</TermDTO>
</TermDTO>
