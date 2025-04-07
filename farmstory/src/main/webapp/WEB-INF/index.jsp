<%@page import="farmstory.DataTransferObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="farmstory.dto.ArticleDTO"%>
<%@page import="farmstory.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="farmstory.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%
	ArrayList<ProductDTO> prodList = (ArrayList<ProductDTO>) request.getAttribute("products");
	ArrayList<ArticleDTO> artList = (ArrayList<ArticleDTO>) request.getAttribute("articles");
	
	pageContext.setAttribute("products", prodList);
	pageContext.setAttribute("articles", artList);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/farmstory/style/index.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <title>팜스토리</title>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <section class="links">
          <img src="/farmstory/images/head_top_line.png" alt="" />
          <div>
            <p>
              <a href="/farmstory">HOME | </a>
              <a href="/farmstory/signin">로그인 | </a>
              <a href="/farmstory/signup">회원가입 | </a>
              <a href="/farmstory/my/shopbascket.do">나의정보 | </a>
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
        <section class="banner">
          <article>
            <div class="banner-content">
              <a href="#">
                <img
                  src="/farmstory/images/main_slide_img_tit.png"
                  alt="사람과 자연을 사랑하는 팜스토리"
                />
                <img src="/farmstory/images/main_slide_img1.jpg" alt="" />
              </a>
              <a href="#">
                <img src="/farmstory/images/main_banner_bg.jpg" alt="" />
                <img src="/farmstory/images/main_banner_tit.png" alt="" />
                <img src="/farmstory/images/main_banner_txt.png" alt="" />
                <img src="/farmstory/images/main_banner_img.png" alt="" />
              </a>
            </div>
          </article>
        </section>
        <section class="shopping">
          <div>
            <img src="/farmstory/images/main_market_tit.png" alt="" />
          </div>
          <article>
            <div class="items">
              <ul>
              <c:forEach var="product" items="${requestScope.products}">
                <li>
                  <a href="#">
                    <img src="/farmstory/images/market_item1.jpg" alt="사과" />
                    <p class="category">${product.category}</p>
                    <p>
                   	  ${product.name}
                    </p>
                    <s>${product.price}</s>
                    <span>${product.discountRate}%↓</span>
                    <p class="current-price">
                    	${Math.round(product.price - (product.price * (product.discountRate / 100)))}원
                    </p>
                  </a>
                </li>
              </c:forEach>
              </ul>
            </div>
          </article>
        </section>
        <section class="small-banner">
          <article>
            <div>
              <a href="#">
                <img src="/farmstory/images/main_banner_sub1_bg.jpg" alt="" />
                <img src="/farmstory/images/main_banner_sub1_tit.png" alt="" />
              </a>
            </div>
            <div>
              <a href="#">
                <img src="/farmstory/images/main_banner_sub2_bg.jpg" alt="" />
                <img src="/farmstory/images/main_banner_sub2_tit.png" alt="" />
              </a>
            </div>
          </article>
        </section>
        <section class="latest">
          <article class="first">
            <div>
              <img src="/farmstory/images/main_latest1_tit.png" alt="" />
            </div>
            <div>
              <img src="/farmstory/images/main_latest1_img.jpg" alt="" />
              <table border="0">
                <tbody>
                <c:forEach var="article" items="${requestScope.articles}">
                  <tr>
                    <td>
                      <a href="#">
                      ${article.content}
                      </a>
                    </td>
                    <td>${article.registerDate}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </article>
          <article class="first">
            <div>
              <img src="/farmstory/images/main_latest2_tit.png" alt="" />
            </div>
            <div>
              <img src="/farmstory/images/main_latest2_img.jpg" alt="" />
              <table border="0">
                <tbody>
                <c:forEach var="article" items="${requestScope.articles}">
                  <tr>
                    <td>
                      <a href="#">
                      ${article.content}
                      </a>
                    </td>
                    <td>${article.registerDate}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </article>
          <article class="first">
            <div>
              <img src="/farmstory/images/main_latest3_tit.png" alt="" />
            </div>
            <div>
              <img src="/farmstory/images/main_latest3_img.jpg" alt="" />
              <table border="0">
                <tbody>
                <c:forEach var="article" items="${requestScope.articles}">
                  <tr>
                    <td>
                      <a href="#">
                      ${article.content}
                      </a>
                    </td>
                    <td>${article.registerDate}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </article>
        </section>
        <section class="info">
          <article>
            <div class="sub2 customer-service">
              <img src="/farmstory/images/main_sub2_cs_tit.png" alt="" />
              <div>
                <img src="/farmstory/images/main_sub2_cs_img.png" alt="" />
                <img src="/farmstory/images/main_sub2_cs_txt.png" alt="" />
                <p>
                  평일: AM 09:00 ~ PM 06:00 <br />
                  점심: PM 12:00 ~ PM 01:00 <br />
                  토, 일요일, 공휴일 휴무
                </p>
              </div>
              <div class="btns">
                <img src="/farmstory/images/main_sub2_cs_bt1.png" alt="" />
                <img src="/farmstory/images/main_sub2_cs_bt2.png" alt="" />
                <img src="/farmstory/images/main_sub2_cs_bt3.png" alt="" />
              </div>
            </div>
            <div class="sub2 bank-info">
              <img src="/farmstory/images/main_sub2_account_tit.png" alt="" />
              <div>
                <p>
                  기업은행 123-456789-01-01-012 <br />
                  국민은행 01-1234-56789 <br />
                  우리은행 123-456789-01-01-012 <br />
                  하나은행 123-456789-01-01 <br />
                  예금주 (주)팜스토리 <br />
                </p>
              </div>
              <div></div>
            </div>
            <div class="sub2 notices">
              <img src="/farmstory/images/main_sub2_notice_tit.png" alt="" />
              <table>
                <tbody>
                <c:forEach var="article" items="${requestScope.articles}">
                  <tr>
                    <td>
                      <a href="#">
                      ${article.content}
                      </a>
                    </td>
                    <td>${article.registerDate}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
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
