<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로그인</title>
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <link rel="stylesheet" href="/farmstory/style/login.css" />
    <script type="text/javascript" src="/farmstory/js/signin/signin.js"></script>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <section class="links">
          <img src="/farmstory/images/head_top_line.png" alt="" />
          <div>
            <p>
              <a href="/farmstory/index.jsp">HOME | </a>
              <a href="/farmstory/signin">로그인 | </a>
              <a href="/farmstory/signup">회원가입 | </a>
              <a href="/farmstory/user/info">나의정보 | </a>
              <a href="/farmstory/signout">로그아웃 | </a>
              <a href="/farmstory/admin">관리자 | </a>
              <a href="#">고객센터</a>
            </p>
          </div>
        </section>
        <section class="logo">
          <div>
            <a href="/">
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
          <div class="login-area">
            <form id="login-form" action="/farmstory/signin" method="POST">
              <table>
                <tbody>
                  <tr>
                    <td>
                      <img src="/farmstory/images/user/login_ico_id.png" alt="" />
                      <img src="/farmstory/images/user/login_ico_pw.png" alt="" />
                    </td>
                    <td>
                      <input
                        id="idInput"
                        class="login-input"
                        type="text"
                        name="id"
                        placeholder="아이디 입력"
                      />
                      <input
						id="passwordInput"
                        class="login-input"
                        type="password"
                        name="pass"
                        placeholder="비밀번호 입력"
                      />
                    </td>
                    <td>
                      <input id="login-btn" type="submit" value="로그인" />
                    </td>
                  </tr>
                </tbody>
              </table>
            </form>
          </div>
          <div class="info-area">
            <p>
              회원 로그인 안내 <br />
              <br />
              아직회원이 아니시면 회원으로 가입하세요 <br />
            </p>
            <span>
              <a href="/farmstory/find/id">아이디 찾기 | </a>
              <a href="/farmstory/find/password">비밀번호 찾기 | </a>
              <a href="/signup">회원가입</a>
            </span>
          </div>
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
          </p>
        </div>
      </footer>
    </div>
  </body>
</html>
