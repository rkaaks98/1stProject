<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>장바구니</title>
    <link rel="stylesheet" href="/farmstory/style/shopbascket.css" />
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

                <section class="basket">
                    <nav>
                        <h1><a href="#"><img src="/farmstory/images/myinfo/myinfo_nav_tit1.png" alt="장바구니"></a></h1>
                        <p>장바구니</p>
                        <p>= HOME>나의정보></p>
                    </nav>
                </section>
                
                <section class="whole">
                    <h1>장바구니 전체(10)</h1>
                    
                    <table>
                        <tbody>
                            <tr>
                                <th><input type="checkbox" id="selectAll"></th>
                                <th>이미지</th>
                                <th>종류</th>
                                <th>상품명</th>
                                <th>수량</th>
                                <th>할인</th>
                                <th>포인트</th>
                                <th>가격</th>
                                <th>소계</th>
                            </tr>
                            
                                    <tr>
                                        <td>장바구니에 상품이 없습니다.</td>
                                    </tr>
                                
                            <tr>
                                <th><input type="checkbox"></th>
                                <td><img src="/farmstory/images/market_item1.jpg" alt="사과"></td>
                                <td>과일</td>
                                <td>사과 500g</td>
                                <td>1</td>
                                <td>10%</td>
                                <td>40p</td>
                                <td>4,000</td>
                                <td>3,600원</td>
                            </tr>
                            <tr>
                                <th><input type="checkbox"></th>
                                <td><img src="/farmstory/images/market_item1.jpg" alt="사과"></td>
                                <td>과일</td>
                                <td>사과 500g</td>
                                <td>1</td>
                                <td>10%</td>
                                <td>40p</td>
                                <td>4,000</td>
                                <td>3,600원</td>
                            </tr>
                            <tr>
                                <th><input type="checkbox"></th>
                                <td><img src="/farmstory/images/market_item1.jpg" alt="사과"></td>
                                <td>과일</td>
                                <td>사과 500g</td>
                                <td>1</td>
                                <td>10%</td>
                                <td>40p</td>
                                <td>4,000</td>
                                <td>3,600원</td>
                            </tr>
                             
                        </tbody>
                    </table>
                    <a href="#">선택삭제</a>
                </section>

                <section class="total">
                    <h3>전체합계</h3>
                    <table>
                        <tbody>
                            <tr>
                                <td>상품수</td>
                                <td>?</td>
                            </tr>
                            <tr>
                                <td>상품금액</td>
                                <td>3600원</td>
                            </tr>
                            <tr>
                                <td>할인금액</td>
                                <td>50,000</td>
                            </tr>
                            <tr>
                                <td>배송비</td>
                                <td>50,000</td>
                            </tr>
                            <tr>
                                <td>포인트</td>
                                <td>4,000</td>
                            </tr>
                            <tr>
                                <td>전체주문금액</td>
                                <td>?원</td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="#">주문하기</a>
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