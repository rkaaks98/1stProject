USE farmstory;

SHOW TABLES;

DESCRIBE company;
DESCRIBE product;
DESCRIBE product_image;

INSERT INTO company VALUES (1, "화북과수원", "홍길동", "010-1234-5677", "경상북도 상주시 화북면");
INSERT INTO product VALUES (1, 1, "사과 500g", "과일", 8000, 8, 5.0, 2500, 53, 1, NOW());
INSERT INTO product_image VALUES (1, 1, "/home/images/thumbnail", "/home/images/info", "/home/images/detail");

SELECT * FROM company;
SELECT * FROM product;
SELECT * FROM product_image;

DELETE FROM product WHERE id = 1; -- product의 특정 row를 지우면 해당 product_id 외래키를 가진 row도 삭제됨

DESCRIBE USER;
INSERT INTO USER VALUES ("abc123", SHA2("abc@123", 256), "김철수", "뭘봐", 0, 0, "abc123@example.com", "010-1111-2222", "12345", "부산광역시 남구 대연동 유엔평화로 17번길 16 ", "426호", NOW(), NULL);
SELECT * FROM user;

DESCRIBE article;
INSERT INTO article VALUES (1, "abc123", "글쓰기 테스트", "뭘봐", "글쓰기 테스트 입니다", 1, 1, 0, NOW());
SELECT * FROM article;

DESCRIBE article_file;
INSERT INTO article_file VALUES (1, 1, "/home/images/article");
SELECT * FROM article;

DESCRIBE `comment`;
INSERT INTO `comment` VALUES (1, 1, "뭘봐", "댓글 쓰기 테스트 입니다", NOW());
SELECT * FROM `comment`;

DELETE FROM article WHERE `id`=1;
SELECT * FROM article;
SELECT * FROM article_file;
SELECT * FROM `comment`;
