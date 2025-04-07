<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/farmstory/style/index.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/event.css" />
    <title>팜스토리</title>
    
    <script>
    	async function loadEvents(){
    		try{
    			let response = await fetch("/farmstory/events");
    			let events = await response.json();
    			
    			events.forEach(event => {
    				console.log("이벤트 제목: ", event.title);
    			});
    			
    		}catch (e) {
				console.e(error);
			}
    	}
    	document.addEventListener("DOMContentLoaded", loadEvents);

    </script>
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
              <a href="#">나의정보 | </a>
              <a href="#">로그아웃 | </a>
              <a href="#">관리자 | </a>
              <a href="#">고객센터</a>
            </p>
          </div>
        </section>
        <section class="logo">
          <div>
            <a href="/farmstory/index.do">
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
      <main class="event">
        <nav class="background">
          <div><img src="/farmstory/images/sub_top_bg.jpg" alt="메인배경" /></div>
          <div>
            <img src="/farmstory/images/sub_top_tit3.png" alt="CropTalk" />
          </div>
        </nav>
        <aside>
          <a href="#">
            <img src="/farmstory/images/sub_aside_bg_line.png" alt="사이드선" />
          </a>
          <a href="#">
            <img src="/farmstory/images/sub_aside_cate4_tit.png" alt="이벤트" />
          </a>
          <a href="/farmstory/events">
            <img
              src="/farmstory/images/sub_cate4_lnb1_ov.png"
              alt="이벤트"
              width="175px"
              height="29px"
            />
          </a>
          <a href="#">
            <img src="/farmstory/images/sub_aside_bg_lnb.png" alt="사이드메뉴" />
          </a>
        </aside>
        <section>
          <div>
            <a href="#">
              <img
                src="/farmstory/images/sub_nav_tit_cate4_tit1.png"
                alt="이벤트"
              />
            </a>
            <span>이벤트</span>
            <a href="#">HOME > 이벤트 > </a>
          </div>
          <article>
            <div class="controls">
              <span id="monthYear"></span>
              <button onclick="goToToday()">today</button>
              <button onclick="prevMonth()">◀</button>
              <button onclick="nextMonth()">▶</button>
            </div>

            <table id="calendar">
              <tr>
                <th>Sun</th>
                <th>Mon</th>
                <th>Tue</th>
                <th>Wed</th>
                <th>Thu</th>
                <th>Fri</th>
                <th>Sat</th>
              </tr>
            </table>
          </article>
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
    <script src="/farmstory/js/calendar.js"></script>
  </body>
</html>
