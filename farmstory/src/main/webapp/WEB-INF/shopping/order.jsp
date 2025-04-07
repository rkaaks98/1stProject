<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/farmstory/style/index.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/order.css" />
    <title>팜스토리</title>
    <style></style>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <section class="links">
          <div>
            <p>
              <a href="/farmstory/index.html">HOME | </a>
              <a href="/farmstory/user/login.html">로그인 | </a>
              <a href="/farmstory/user/register.html">회원가입 | </a>
              <a href="/farmstory/community/qna.html">고객센터</a>
            </p>
          </div>
        </section>
        <section class="logo">
          <div>
            <a href="#">
              <img src="/farmstory/images/logo.png" alt="로고" />
            </a>
            <a href="#">
              <img src="/farmstory/images/head_txt_img.png" />
            </a>
          </div>
        </section>
        <section class="services">
          <article>
            <div>
              <a href="/farmstory/intro/greeting.html">
                <img src="/farmstory/images/head_menu1.png" alt="팜스토리소개" />
              </a>
            </div>
            <div>
              <a href="/farmstory/shopping/list.html">
                <img src="/farmstory/images/head_menu_badge.png" alt="30%" />
                <img src="/farmstory/images/head_menu2.png" alt="장보기" />
              </a>
            </div>
            <div>
              <a href="/farmstory/story/story.html">
                <img src="/images/head_menu3.png" alt="농작물이야기" />
              </a>
            </div>
            <div>
              <a href="/farmstory/event/event-calendar.html">
                <img src="/farmstory/images/head_menu4.png" alt="이벤트" />
              </a>
            </div>
            <div>
              <a href="/farmstory/community/notice.html">
                <img src="/farmstory/images/head_menu5.png" alt="커뮤니티" />
              </a>
            </div>
          </article>
        </section>
      </header>

      <main class="shopping">
        <nav class="background">
          <div><img src="/farmstory/images/sub_top_bg.jpg" alt="메인배경" /></div>
          <div><img src="/farmstory/images/sub_top_tit2.png" alt="MARKET" /></div>
        </nav>
        <aside>
          <a href="#">
            <img src="/farmstory/images/sub_aside_bg_line.png" alt="사이드선" />
          </a>
          <a href="#">
            <img src="/farmstory/images/sub_aside_cate2_tit.png" alt="장보기" />
          </a>
          <a href="#">
            <img src="/farmstory/images/sub_aside_bg_lnb.png" alt="사이드메뉴" />
          </a>
          <a href="/farmstory/shopping/list.html">
            <img
              src="/farmstory/images/sub_cate2_lnb1_ov.png"
              alt="장보기선택2"
              width="175px"
              height="29px"
            />
          </a>
        </aside>
        <section>
          <div>
            <a href="#">
              <img src="/farmstory/images/sub_nav_tit_cate2_tit1.png" alt="장보기" />
            </a>
            <span>장보기</span>
            <a href="#">HOME > 장보기 > </a>
          </div>
          <article>
            <h2>장바구니 전체(10)</h2>
            <table width="762" height="332" class="basketList">
              <thead>
                <tr>
                  <th>
                    <input type="checkbox" />
                  </th>
                  <th>이미지</th>
                  <th>종류</th>
                  <th>상품명</th>
                  <th>수량</th>
                  <th>할인</th>
                  <th>포인트</th>
                  <th>가격</th>
                  <th>소계</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td colspan="9" class="none">장바구니에 상품이 없습니다.</td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>
                    <img src="/farmstory/images/market_item1.jpg" alt="상품" />
                  </td>
                  <td>과일</td>
                  <td>사과 500g</td>
                  <td>1</td>
                  <td>10%</td>
                  <td>40p</td>
                  <td>4,000</td>
                  <td>3600원</td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>
                    <img src="/farmstory/images/market_item1.jpg" alt="상품" />
                  </td>
                  <td>과일</td>
                  <td>사과 500g</td>
                  <td>1</td>
                  <td>10%</td>
                  <td>40p</td>
                  <td>4,000</td>
                  <td>3600원</td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>
                    <img src="/farmstory/images/market_item1.jpg" alt="상품" />
                  </td>
                  <td>과일</td>
                  <td>사과 500g</td>
                  <td>1</td>
                  <td>10%</td>
                  <td>40p</td>
                  <td>4,000</td>
                  <td>3600원</td>
                </tr>
              </tbody>
            </table>
          </article>
          <div>
            <article>
              <table width="290.8" height="201px" class="total">
                <thead>
                  <tr>
                    <th>최종결제정보</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>상품수</td>
                    <td>1</td>
                  </tr>
                  <tr>
                    <td>상품금액</td>
                    <td>27,000</td>
                  </tr>
                  <tr>
                    <td>할인금액</td>
                    <td>5,000원</td>
                  </tr>
                  <tr>
                    <td>포인트사용</td>
                    <td>2000p</td>
                  </tr>
                  <tr>
                    <td>배송비</td>
                    <td>5,000원</td>
                  </tr>
                  <tr>
                    <td>포인트적립</td>
                    <td>4000원</td>
                  </tr>
                  <tr>
                    <td>전체주문금액</td>
                    <td class="totalPrice">22,000</td>
                  </tr>
                </tbody>
              </table>
              <a href="#" class="btn Order">결제하기</a>
            </article>
            <article>
              <h2>주문정보 입력</h2>
              <table>
                <tbody>
                  <tr class="order">
                    <td>주문자</td>
                    <td>
                      <input type="text" name="name" value="홍길동" />
                    </td>
                  </tr>
                  <tr class="phone1">
                    <td>휴대폰</td>
                    <td>
                      <input type="text" name="phone" value="010-1234-1001" />
                    </td>
                  </tr>
                  <tr class="point">
                    <td>포인트사용</td>
                    <td>
                      <input type="text" name="point" />
                      <p class="btnUse">사용하기</p>
                      <p class="pointInfo">사용가능 2,000</p>
                    </td>
                  </tr>
                  <tr class="receiver">
                    <td>받는분</td>
                    <td>
                      <input type="text" name="point" />
                    </td>
                  </tr>
                  <tr class="phone2">
                    <td>연락처</td>
                    <td>
                      <input type="text" name="point" />
                    </td>
                  </tr>
                  <tr class="address">
                    <td>배송주소</td>
                    <td>
                      <input type="text" name="zip" />
                      <button type="buttonSearch">
                        <img
                          src="/farmstory/images/user/btn_post_search.gif"
                          alt="우편번호 검색"
                        />
                      </button>
                      <input
                        type="text"
                        name="addr1"
                        placeholder="기본주소 검색"
                      />
                      <input
                        type="text"
                        name="addr2"
                        placeholder="상세주소 입력"
                      />
                    </td>
                  </tr>
                  <tr class="payment">
                    <td>결제방법</td>
                    <td>
                      <input type="radio" />계좌이체
                      <input type="radio" />신용카드
                      <input type="radio" />체크카드
                      <input type="radio" />휴대폰
                    </td>
                  </tr>
                  <tr class="etx">
                    <td>기타</td>
                    <td>
                      <textarea name="etc" id="etc"></textarea>
                    </td>
                  </tr>
                </tbody>
              </table>
            </article>
          </div>
        </section>
      </main>

      <footer>
        <a href="#">
          <img src="/farmstory/images/footer_top_line.png" alt="" /><br />
          <img src="/farmstory/images/footer_logo.png" alt="푸터로고" />
          <p>
            (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제
            2013-팜스토리구-123호 / 벤처기업 확인 서울지방중소기업청 제
            012345678-9-01234호<br />
            등록번호 팜스토리01234 (2013.04.01)/발행인 : 홍길동<br />
            대표 : 홍길동 / 이메일 : email@mail.mail / 전화 : 01)234-5678 /
            경기도 성남시 잘한다구 신난다동 345<br />
            <span>Copyright(C)홍길동 All right reserved.</span>
          </p>
        </a>
      </footer>
    </div>
  </body>
</html>
