<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>주문내역</title>
    <link rel="stylesheet" href="/farmstory/style/order-list.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    </head>
    <body>
        <div id="wrapper">
            <header>
                <section class="links">
                  <img src="/farmstory/images/head_top_line.png" alt="" />
                  <div>
                    <p>
                      <a href="/farmstory/index.do">HOME | </a>
                      <a href="/farmstory/signin">로그인 | </a>
                      <a href="/farmstory/signup">회원가입 | </a>
                      <a href="/farmstory/my/shopbasket.do">나의정보 | </a>
                      <a href="/farmstory/signout.do">로그아웃 | </a>
                      <a href="/farmstory/admin.do">관리자 | </a>
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
                <section class="background">
                    <a href="#"><img src="/farmstory/images/myinfo/myinfo_top_bg.jpg" alt="banner"></a>
                    <a href="#"><img src="/farmstory/images/myinfo/myinfo_top_tit.png " alt="background"></a>
                </section>
                <section class="side">
                    <h1><img src="/farmstory/images/myinfo/myinfo_menu_tit.png" alt=""></h1>
                        <article>    
                            <ul>
                                <li><a href="/farmstory/my/shopbasket.do"><img src="/farmstory/images/myinfo/myinfo_menu1.png" alt="장바구니"></a></li>
                                <li><a href="/farmstory/my/orderList.do"><img src="/farmstory/images/myinfo/myinfo_menu2.png" alt="주문내역"></a></li>
                                <li><a href="/farmstory/my/modifyInfo.do"><img src="/farmstory/images/myinfo/myinfo_menu3.png" alt="정보수정"></a></li>
                            </ul>
                        </article>
                </section>

                <section class="order">
                    <nav>
                        <h1><a href="#"><img src="/farmstory/images/myinfo/myinfo_nav_tit2.png" alt="장바구니"></a></h1>
                        <p>주문내역</p>
                        <p>= HOME>나의정보></p>
                    </nav>
                </section>

                <section class="whole">
                    <table>
                        <tbody>
                            <tr>
                                <th>주문번호</th>
                                <th>이미지</th>
                                <th>상품명</th>
                                <th>판매가격</th>
                                <th>수량</th>
                                <th>합계</th>
                                <th>주문자</th>
                                <th>주문일</th>
                                <th>확인</th>
                            </tr>
                            <c:choose>
                           		<c:when test="${empty selectAllOrder}">
	                            <tr>
	                               	<td>상품 구매 내역이 없습니다.</td>
	                            </tr>
	                            </c:when>
	                            <c:otherwise>
                                	<c:forEach var="product" items="${selectAllOrder}">
		                            <tr>
		                                <th>${product.id}</th>
		                                <td><img src="/farmstory/images/${product.image}" alt="{product.name}"></td>
		                                <td>${product.name}</td>
		                                <td>${product.price}<br> 원</td>
		                                <td>${product.amount}</td>
		                                <td>${product.price * product.amount}<br>원</td>
		                                <td>${product.userId}</td>
		                                <td>${product.placedDate}</td>
		                                <td><button class="detail-btn" data-id="${product.id}">상세확인</button></td>
		                            </tr>
                            		</c:forEach>
                           		</c:otherwise>
                        	</c:choose>
                        </tbody>
                    </table>
                </section>
                <section class="page">
                    <a href="#">이전</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">다음</a>

                </section>

                
            </main>




            <footer>
                <div>
                  <img src="/farmstory/images/footer_logo.png" alt="" />
                  <p>
                    (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제
                    2013-부산진구-123호 / 벤처기업확인 서울 지방중소기업청 제
                    012345678-9-01234호<br />
                    등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동 <br />
                    대표: 김철수 / 이메일 : abc123@example.com / 전화 : 01) 234-5678 /
                    부산광역시 부산진구 부전동 123 <br />
                    <span>copyright© 김철수 All rights reserved.</span>
                    <span>farmstory ver1.0.1</span>
                  </p>
                </div>
              </footer>



        </div>
        
    </body>
</html>