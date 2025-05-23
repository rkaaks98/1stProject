<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원가입</title>
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <link rel="stylesheet" href="/farmstory/style/register.css" />
  </head>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="/farmstory/js/register/register.js"></script>
  <script src="/farmstory/js/register/check.js"></script>
  <script src="/farmstory/js/register/auth.js"></script>
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
          <form action="">
            <h3 class="title">사이트 이용정보 입력</h3>
            <table>
              <tbody>
                <tr>
                  <td>아이디</td>
                  <td>
                    <input
                      class="register-input"
                      type="text"
                      name="id"
                      placeholder="아이디 입력"
                    />
                    <button type="button" name="id" class="btn idCheckBtn">
                      <img src="/farmstory/images/user/chk_id.gif" alt="" />
                    </button>
                    <span class="result idResult"></span>
                  </td>
                </tr>
                <tr>
                  <td>비밀번호</td>
                  <td>
                    <input
                      class="register-input"
                      type="password"
                      name="password"
                      placeholder="비밀번호 입력"
                    />
                    <span class="result passwordResult"></span>
                  </td>
                </tr>
                <tr>
                  <td>비밀번호 확인</td>
                  <td>
                    <input
                      class="register-input"
                      type="password"
                      name="password_confirm"
                      placeholder="비밀번호 입력 확인"
                    />
                    <span class="result passwordConfirmResult"></span>
                  </td>
                </tr>
              </tbody>
            </table>
            <h3 class="title">개인정보 입력</h3>
            <table>
              <tbody>
                <tr>
                  <td>이름</td>
                  <td>
                    <input
                      class="register-input"
                      type="text"
                      name="name"
                      placeholder="이름 입력"
                    />
                    <span class="result nameResult"></span>
                  </td>
                </tr>
                <tr>
                  <td>별명</td>
                  <td>
                    <p>공백없는 한글, 영문, 숫자 입력</p>
                    <input
                      class="register-input"
                      type="text"
                      name="nickname"
                      placeholder="별명 입력"
                    />
                    <button type="button" class="btn nicknameCheckBtn">
                      <img src="/farmstory/images/user/chk_id.gif" alt="" />
                    </button>
                    <span class="result nicknameResult"></span>
                  </td>
                </tr>
                <tr id="emailArea">
                  <td>이메일</td>
                  <td>
                    <div>
                      <input
                        class="register-input email-input"
                        type="email"
                        name="email"
                        placeholder="이메일 입력"
                      />
                      <button type="button" class="btn emailAuthBtn">
                        <img src="/farmstory/images/user/chk_auth.gif" alt="" />
                      </button>
                    </div>
                    <div id="emailCodeArea">
                      <input
                        id="emailCodeInput"
                        class="register-input email-input"
                        type="text"
                        placeholder="인증코드 입력"
                      />
                      <button
                        id="emailCodeBtn"
                        type="button"
                        class="btn emailAuthBtn"
                      >
                        인증하기
                      </button>
                    </div>
                    <span class="result emailResult"></span>
                  </td>
                </tr>
                <tr>
                  <td>휴대폰</td>
                  <td>
                    <input
                      class="register-input"
                      type="text"
                      name="phone_num"
                      placeholder="휴대폰 입력"
                    />
                    <span class="result phoneNumResult"></span>
                  </td>
                </tr>
                <tr id="addr-area">
                  <td>주소</td>
                  <td>
                    <div>
                      <input
                        class="register-input addr-input"
                        type="text"
                        name="zip"
                        placeholder="우편번호"
                        readonly
                      />
                      <button type="button" class="btn" id="findPostCode">
                        <img src="/farmstory/images/user/chk_post.gif" alt="" />
                      </button>
                    </div>
                    <input
                      class="register-input addr-input"
                      type="text"
                      name="address"
                      placeholder="주소"
                      readonly
                    />
                    <input
                      class="register-input addr-input"
                      type="text"
                      name="address_detail"
                      placeholder="상세주소"
                    />
                    <span class="result addressDetailResult"></span>
                  </td>
                </tr>
              </tbody>
            </table>
            <div id="form-btn">
              <button type="button">
                <a href="#">취소</a>
              </button>
              <input type="submit" value="회원가입" />
            </div>
          </form>
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
