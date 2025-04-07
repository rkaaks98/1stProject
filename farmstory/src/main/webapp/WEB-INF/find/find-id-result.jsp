<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>아이디 변경 결과</title>
    <link rel="stylesheet" href="/farmstory/style/find-id-result.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <script src="/farmstory/js/find/findIdResult.js"></script>
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
                <section class="idfind">
                    <h1>아이디 찾기 결과</h1>
                    <table>
                        <tbody id="userInfo">
                            <tr>
                                <td>이름</td>
                                <td id="userName">${dto.name}</td>
                            </tr>
                            <tr>
                                <td>아이디</td>
                                <td id="userId">${dto.id}</td>
                            </tr>
                            <tr>
                                <td>이메일</td>
                                <td id="userEmail">${dto.email}</td>
                            </tr>
                            <tr>
                                <td>가입일</td>
                                <td id="userRegisterDate">${dto.registerDate}</td>
                            </tr>
                        </tbody>
                    </table>
                    <section class="maintext">
                        <p>
                            고객님의 정보와 일치하는 아이디 입니다.
                        </p>
                    </section>
                    <section class="mainbtn">
                        <a href="/farmstory/user/login.do">로그인</a>
                        <a href="/farmstory/find/findPass.do">비밀번호 찾기</a>
                    </section>

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