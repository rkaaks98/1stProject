<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>오늘의 식단</title>
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/common/footer.css" />
    <link rel="stylesheet" href="/farmstory/style/common/page-common.css" />
    <link rel="stylesheet" href="/farmstory/style/diet.css" />
      <style>
    .side-content {
  	display: flex;
  	flex-direction: column; 
  	align-items: center; 
  	justify-content: center; 
  	height: 100%; 
	}

	.side-content a {
  	text-align: center;
	}
    </style>
    
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
              <a href="/farmstory/signout">로그아웃 | </a>
              <a href="/farmstory/admin">관리자 | </a>
              <a href="">고객센터</a>
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
              <a href="#" style = "display: block; width: 100%; height: 100%;"> </a>
            </div>
            <div>
              <a href="#" style = "display: block; width: 100%; height: 100%;">
                <img src="/farmstory/images/head_menu_badge.png" alt="30%" />
              </a>
            </div>
            <div>
              <a href="/farmstory/listStory" style = "display: block; width: 100%; height: 100%;"> </a>
            </div>
            <div>
              <a href="/farmstory/events" style = "display: block; width: 100%; height: 100%;"> </a>
            </div>
            <div>
              <a href="/farmstory/notice.do" style = "display: block; width: 100%; height: 100%;"> </a>
            </div>
          </article>
        </section>
      </header>
      <main>
        <section class="page-banner">
          <div>
            <img src="/farmstory/images/sub_top_bg.jpg" alt="" />
            <img src="/farmstory/images/sub_top_tit5.png" alt="" />
          </div>
        </section>
        <section class="container">
          <article>
            <aside class="side-menu">
              <div class="side-title">
                <img src="/farmstory/images/sub_aside_cate5_tit.png" alt="" />
              </div>
              <div class="side-content">
                <a href="/farmstory/notice.do">공지사항</a>
                <a href="/farmstory/diet.do">오늘의식단</a>
                <a href="/farmstory/iam-cooker.do">나도요리사</a>
                <a href="/farmstory/qna.do">1:1고객문의</a>
                <a href="/farmstory/faq.do">자주묻는질문</a>
              </div>
              <img src="/farmstory/images/sub_aside_bg_line.png" class="line" />
            </aside>
            <div class="content-area">
              <div class="content-title">
                <img src="/farmstory/images/sub_nav_tit_cate5_tit2.png" alt="" />
                <span>
                  <a href="#">HOME </a>
                  <a href="#">> 커뮤니티 > </a>
                  <a href="#">오늘의식단 </a>
                </span>
              </div>
              <div class="content">
                <h3>글수정</h3>
                <form action="https://naver.com" method="POST">
                  <table>
                    <tbody>
                      <tr>
                        <td>제목</td>
                        <td>
                          <input
                            type="text"
                            name="diet-title"
                            placeholder="제목을 입력하세요"
                            id="diet-title"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>내용</td>
                        <td>
                          <textarea
                            id="diet-content"
                            name="diet-content"
                          ></textarea>
                        </td>
                      </tr>
                      <tr class="file-area">
                        <td>파일</td>
                        <td>
                          <p>
                            최대 2개 파일 첨부 가능, 각 파일당 최대 10MB까지
                            가능
                          </p>
                          <div>
                            <input type="file" />
                          </div>
                          <div>
                            <input type="file" />
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <input type="submit" value="작성완료" class="btn" />
                  <button class="btn">
                    <a href="#"> 취소 </a>
                  </button>
                </form>
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
          </p>
        </div>
      </footer>
    </div>
  </body>
</html>
