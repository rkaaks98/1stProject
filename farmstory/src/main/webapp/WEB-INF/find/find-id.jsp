<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>아이디 찾기</title>
    <link rel="stylesheet" href="/farmstory/style/find-id.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <script src="/farmstory/js/find/findId.js"></script>
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
                    <h1>아이디 찾기</h1>
                    <table>
                        <tbody>
                            <tr>
                                <td>이름</td>
                                <td>
                                    <input type="text" name="name" placeholder="이름 입력" required>
                                </td>
                            </tr> 
                            <tr>
                                <td>이메일</td>
                                <td>
                                    <input type="email" name="email" id="email" placeholder="이메일 입력" required><br>
                                    <input type="text" name="authCode" id="authCode" placeholder="인증번호 입력"></td>
                                </td>
                                <td><a href="#" id="sendCodeBtn">인증번호 받기</a><br>
                                <a href="#" id="verifyCodeBtn">확인</a></td>
                        </tbody>
                    </table>
                    <section class="maintext">
                        <p>
                            회원가입시 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.<br>
                            인증번호를 입력 후 확인 버튼을 누르세요.
                        </p>
                    </section>
                    <section class="mainbtn">
                        <a href="#">취소</a>
                        <a href="/farmstory/find/findIdResult.do" id="nextBtn">다음</a>
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