<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/farmstory/style/index.css" />
    <link rel="stylesheet" href="/farmstory/style/common/header.css" />
    <link rel="stylesheet" href="/farmstory/style/school.css" />
    <title>팜스토리</title>
    <style></style>
  </head>

  <body>
    <div id="wrapper">
      <%@ include file="./_header.jsp" %>

      <main class="article">
        <nav class="background">
         <div class="subtop"><img src="/farmstory/images/sub_top_bg.jpg" alt="메인배경" /></div>
         <div class="croptalk"><img src="/farmstory/images/sub_top_tit3.png" alt="CropTalk" /></div>
        </nav>
        <aside>
          <a href="#">
            <img src="/farmstory/images/sub_aside_bg_line.png" alt="사이드선" />
          </a>
          <a href="#">
            <img src="/farmstory/images/sub_aside_cate3_tit.png" alt="농작물이야기" />
          </a>
          <a href="/farmstory/listStory">
            <img src="/farmstory/images/sub_cate3_lnb1.png" alt="농작물이야기" />
          </a>
          <a href="/farmstory/listGardening">
            <img src="/farmstory/images/sub_cate3_lnb2.png" alt="텃밭가꾸기" />
          </a>
          <a href="/farmstory/listSchool">
            <img
              src="/farmstory/images/sub_cate3_lnb3_ov.png"
              alt="귀농학교"
              width="175px"
              height="29px"
            />
          </a>
          <a href="#">
            <img src="/farmstory/images/sub_aside_bg_lnb.png" alt="사이드메뉴" />
          </a>
        </aside>
        <section>
          <div class="sub">
            <a href="#">
              <img
              	class="subnav"
                src="/farmstory/images/sub_nav_tit_cate3_tit3.png"
                alt="농작물이야기"
              />
            </a>
            <span>귀농학교</span>
            <a href="#">HOME > 농작물이야기 > </a>
          </div>
          <div class="write">
            <nav>
              <h5>글보기</h5><br>
            </nav>
				<table border=0>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${articleDTO.title}" readonly></td>
					</tr>
					
					<tr>
			            <th>작성자</th>
			            <td><input type="text" name="author" value="${articleDTO.author}" readonly></td>
			        </tr>
					
					<tr>
						<th>내용</th>
						<td>
							<textarea name="content" readonly>${articleDTO.content}</textarea>
						</td>
					</tr>
					
				</table>
				
				<div class="btn">
					<a href="/farmstory/deleteSchool" class="btn btnRemove">삭제</a>
					<a href="/farmstory/modifySchool?id=${articleDTO.id}" class="btn btnModify">수정</a>
					<a href="/farmstory/listSchool" class="btn btnList">목록</a>
				</div>
			
				<!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>               

					<c:forEach var="comment" items="${comments}">
	                    <article>
	                    	<span class="date">${comment.registerDate}</span>
	                        <span class="author">${comment.author}</span>
	                        <p class="content">${comment.content}</p>                        
	                        <div>
	                            <a href="#" class="remove">삭제</a>
	                            <a href="#" class="modify">수정</a>
	                        </div>
	                    </article>
					</c:forEach>
					
					<c:if test="${empty comments}">
                    	<p class="empty">등록된 댓글이 없습니다.</p>
					</c:if>
                </section>
				
                <!-- 댓글쓰기 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form name="formComment" action="#">
                    	<input type="hidden" name="parent" value="${commentDTO.articleId}">
                    	<input type="hidden" name="writer" value="${commentDTO.author}">
                        <textarea class="textarea" name="content" placeholder="댓글 내용 입력"></textarea>
                    	<div class="btnComment">
                            <a href="#" class="btn btnCancelComment">취소</a>
                            <input type="submit" value="작성완료" class="btn btnCompleteComment"/>
                        </div>
                    </form>
                </section>
          </div>
        </section>
      </main>

      <%@ include file="./_footer.jsp" %>
    </div>
  </body>
</html>
