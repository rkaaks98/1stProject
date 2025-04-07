<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>찾아오시는길</title>
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <link rel="stylesheet" href="/farmstory/style/common/page-common.css" />
    <link rel="stylesheet" href="/farmstory/style/map.css" />
    <script
      type="text/javascript"
      src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=910ad734157852d29f3fa52158df35ec&libraries=services"
    ></script>
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
        <section class="page-banner">
          <div>
            <img src="/farmstory/images/sub_top_bg.jpg" alt="" />
            <img src="/farmstory/images/sub_top_tit1.png" alt="" />
          </div>
        </section>
        <section class="container">
          <article>
            <aside class="side-menu">
              <div class="side-title">
                <img src="/farmstory/images/sub_aside_cate1_tit.png" alt="" />
              </div>
              <div class="side-content">
                <a href="/farmstory/greeting.do"> </a>
                <a href="/farmstory/map.do"> </a>
              </div>
              <img src="/farmstory/images/sub_aside_bg_line.png" class="line" />
            </aside>
            <div class="content-area">
              <div class="content-title">
                <img src="/farmstory/images/sub_nav_tit_cate1_tit2.png" alt="" />
                <span>
                  <a href="#">HOME </a>
                  <a href="#">> 팜스토리소개 > </a>
                  <a href="#">인사말 </a>
                </span>
              </div>
              <div class="content">
                <h4>팜스토리</h4>
                <p>
                  주소: 경기도 이천시 잘한다구 신난다동 123 <br />
                  전화: 01-234-5678 <br />
                  <br />
                </p>
                <h4>찾아오시는길</h4>
                <div id="map" style="width: auto; height: 400px"></div>
              </div>
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
    <script>
      let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      //지도를 생성할 때 필요한 기본 옵션
      let options = {
        center: new kakao.maps.LatLng(35.17992598569, 129.07509523457), //지도의 중심좌표.
        level: 5, //지도의 레벨(확대, 축소 정도)
      };

      let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      let geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(
        "부산광역시 연제구 중앙대로 1001",
        (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            let marker = new kakao.maps.Marker({ map: map, position: coords });

            let infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;font-weight:bolder;font-size:16px;">팜스토리</div>',
            });
            infowindow.open(map, marker);

            map.setCenter(coords);
          }
        }
      );
    </script>
  </body>
</html>
