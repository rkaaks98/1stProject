<%@page import="farmstory.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>정보수정</title>
        <link rel="stylesheet" href="/farmstory/style/modify-info.css" />
        <link rel="stylesheet" href="/farmstory/style/common/header.css" />
        <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    	<script src="/farmstory/js/my/modifyInfo.js"></script>
    	<% 
    		HttpSession sessionObj = request.getSession();
    		UserDTO user = (UserDTO) sessionObj.getAttribute("user");
    	%>
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
                        <h1><a href="#"><img src="/farmstory/images/myinfo/myinfo_nav_tit3.png" alt="정보수정"></a></h1>
                        <p>정보수정</p>
                        <p>= HOME>나의정보></p>
                    </nav>
                </section>
                
                <section class="setup">
                    <h1>회원정보 설정</h1>
                    <table>
                        <tbody>
                            <tr>
                                <td>아이디</td>
                                <td id="userId" value="<%= user.getId() %>" readonly>wnstj050505</td>
                            </tr>
                            <tr class="pass">
                                <td>비밀번호</td>
                                <td><input type="password" name="pass" id="password" placeholder="비밀번호 입력"></td>
                            </tr>
                            <tr class="passacc">
                                <td>비밀번호 확인</td>
                                <td><input type="password" name="passok" id="passwordConfirm" placeholder="비밀번호 입력 확인"></td>
                                <td><a href="#" id="updatePasswordBtn">비밀번호 수정</a></td>
                            </tr>
                            <tr>
                                <td>회원가입날짜</td>
                                <td><%= user.getRegisterDate() %></td>
                            </tr>
                        </tbody>
                    </table>
                </section>

                <section class="modify">
                    <h1>개인정보 수정</h1>
                    <table>
                        <tr>
                            <td>이름</td>
                            <td><input type="text" name="name" id="name" value="<%= user.getName() %>"></td>
                        </tr>
                        <tr class="nickname">
                            <td>별명</td>
                            <td>
                                공백없는 한글, 영문, 숫자 입력<br>
                                <input type="text" name="nickname" placeholder="별명 입력" id="nickname" value="<%= user.getNickname() %>">
                            </td>
                            <td><a href="#"><img src="/farmstory/images/user/chk_id.gif" alt=""></a></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                <input type="text" name="email" placeholder="이메일 입력" id="email" value="<%= user.getEmail() %>">
                            </td>
                            <td><a href="#"><img src="/farmstory/images/user/chk_auth.gif" alt=""></a></td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td><input type="text" name="phone" placeholder="휴대폰 입력" id="phone" value="<%= user.getPhoneNum() %>"></td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="mail" placeholder="우편번호" id="zipcode" value="" readonly><br>
                                <input type="text" name="address" placeholder="주소 검색" class="line23" id="address" value="<%= user.getAddress() %>"><br>
                                <input type="text" name="detailadd" placeholder="상세주소 입력" class="line23" id="detailAddress" value="<%= user.getAddressDetail() %>">
                            </td>
                            <td><a href="#"><img src="/farmstory/images/user/chk_post.gif" alt="" id="findZipcodeBtn"></a></td>
                        </tr>
                        <tr>
                            <td>회원탈퇴</td>
                            <td><a href="#" id="deleteAccountBtn">탈퇴하기</a></td>
                        </tr>
                    </table>
                    <div class="button">
                        <a href="#">취소</a>
                        <a href="#" id="updateInfoBtn">회원수정</a>
                    </article>
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