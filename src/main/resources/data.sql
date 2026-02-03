-- =============================================
-- Hiphadi Mock Data (로컬 개발용)
-- =============================================

-- =============================================
-- 1. Admin 계정 (username: hiphadi, password: hiphadi123)
-- =============================================
INSERT IGNORE INTO admin (id, username, password, created_at, updated_at)
VALUES (1, 'hiphadi', '$2a$10$LADWdd2kPz4A0Iteeeeh5OTLUILN1BrRohs6cL02M/pLM5UA3BSFa', NOW(), NOW());

-- =============================================
-- 2. Site Settings (업장 정보)
-- =============================================
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('intro_ko', '선택한 상품은 카운터에서 보여주세요 결제는 카운터에서!');
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('intro_en', 'Please show your selected items at the counter. Payment is also at the counter!');
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('bathroom_password', '화장실 비밀번호: 5456*');

-- =============================================
-- 3. Category (메뉴 카테고리)
-- =============================================
INSERT IGNORE INTO category (id, category_name, category_eng_name, priority, created_at, updated_at) VALUES
(1, '위스키', 'Whisky', 1, NOW(), NOW()),
(2, '와인', 'Wine', 2, NOW(), NOW()),
(3, '칵테일', 'Cocktail', 3, NOW(), NOW()),
(4, '맥주', 'Beer', 4, NOW(), NOW()),
(5, '논알콜', 'Non-Alcohol', 5, NOW(), NOW()),
(6, '안주', 'Food', 6, NOW(), NOW());

-- =============================================
-- 4. Product (메뉴)
-- =============================================
-- 위스키
INSERT IGNORE INTO product (id, name, eng_name, description, price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(1, '글렌피딕 12년', 'Glenfiddich 12Y', '스페이사이드의 대표 싱글몰트', 15000, 1, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(2, '맥캘란 12년', 'Macallan 12Y', '셰리 캐스크 숙성의 진한 풍미', 18000, 1, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(3, '조니워커 블랙', 'Johnnie Walker Black', '스모키하고 깊은 맛의 블렌디드', 12000, 1, 'SALE', 'NORMAL', 3, NOW(), NOW()),
(4, '발베니 12년', 'Balvenie 12Y', '더블우드 숙성, 달콤한 바닐라 노트', 17000, 1, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(5, '라프로익 10년', 'Laphroaig 10Y', '아일라 피트 위스키, 강렬한 스모키', 16000, 1, 'SOLD_OUT', 'NORMAL', 5, NOW(), NOW());

-- 와인
INSERT IGNORE INTO product (id, name, eng_name, description, price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(6, '하우스 레드', 'House Red', '부드러운 미디엄 바디 레드와인', 8000, 2, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(7, '하우스 화이트', 'House White', '상큼한 드라이 화이트와인', 8000, 2, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(8, '샴페인', 'Champagne', '축하를 위한 스파클링', 15000, 2, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(9, '상그리아', 'Sangria', '과일향 가득한 스페인 전통 와인', 10000, 2, 'SALE', 'NORMAL', 4, NOW(), NOW());

-- 칵테일
INSERT IGNORE INTO product (id, name, eng_name, description, price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(10, '모히또', 'Mojito', '민트와 라임의 상쾌한 조화', 12000, 3, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(11, '마가리타', 'Margarita', '테킬라 베이스, 새콤달콤', 13000, 3, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(12, '올드패션드', 'Old Fashioned', '버번 베이스 클래식 칵테일', 14000, 3, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(13, '네그로니', 'Negroni', '진, 캄파리, 베르무트의 씁쓸한 매력', 13000, 3, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(14, '에스프레소 마티니', 'Espresso Martini', '커피와 보드카의 완벽한 만남', 14000, 3, 'SALE', 'RECOMMEND', 5, NOW(), NOW()),
(15, '피나콜라다', 'Pina Colada', '코코넛과 파인애플의 트로피컬', 12000, 3, 'SOLD_OUT', 'NORMAL', 6, NOW(), NOW());

-- 맥주
INSERT IGNORE INTO product (id, name, eng_name, description, price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(16, '하이네켄', 'Heineken', '청량한 라거', 7000, 4, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(17, '기네스', 'Guinness', '아일랜드 스타우트', 9000, 4, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(18, '아사히', 'Asahi', '깔끔한 일본 라거', 7000, 4, 'SALE', 'NORMAL', 3, NOW(), NOW()),
(19, '호가든', 'Hoegaarden', '벨기에 밀맥주', 8000, 4, 'SALE', 'NORMAL', 4, NOW(), NOW());

-- 논알콜
INSERT IGNORE INTO product (id, name, eng_name, description, price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(20, '버진 모히또', 'Virgin Mojito', '알콜 없는 상쾌한 모히또', 8000, 5, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(21, '애플 주스', 'Apple Juice', '100% 사과주스', 5000, 5, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(22, '탄산수', 'Sparkling Water', '페리에 / 산펠레그리노', 4000, 5, 'SALE', 'NORMAL', 3, NOW(), NOW()),
(23, '아메리카노', 'Americano', '진한 에스프레소', 5000, 5, 'SALE', 'NORMAL', 4, NOW(), NOW());

-- 안주
INSERT IGNORE INTO product (id, name, eng_name, description, price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(24, '믹스넛', 'Mixed Nuts', '다양한 견과류 모음', 8000, 6, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(25, '치즈 플래터', 'Cheese Platter', '4종 치즈와 크래커', 18000, 6, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(26, '감바스', 'Gambas', '마늘 새우 오일 파스타', 16000, 6, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(27, '소시지 모둠', 'Sausage Platter', '훈제 소시지 3종', 15000, 6, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(28, '올리브', 'Olives', '절임 올리브', 6000, 6, 'SALE', 'NORMAL', 5, NOW(), NOW()),
(29, '과일 플래터', 'Fruit Platter', '제철 과일 모음', 20000, 6, 'SOLD_OUT', 'NORMAL', 6, NOW(), NOW());

-- =============================================
-- 5. Suggestion (건의사항 샘플)
-- =============================================
INSERT IGNORE INTO suggestion (id, content, created_at, updated_at) VALUES
(1, '음악 볼륨 조금만 낮춰주시면 대화하기 더 편할 것 같아요!', NOW(), NOW()),
(2, '칵테일 종류가 더 다양했으면 좋겠습니다. 특히 진토닉 추가 부탁드려요.', NOW(), NOW()),
(3, '분위기 너무 좋아요. 자주 올게요!', NOW(), NOW());

-- =============================================
-- 6. ProductClickDaily (클릭 통계 샘플 - 최근 7일)
-- =============================================
INSERT IGNORE INTO product_click_daily (id, product_id, click_date, click_count) VALUES
(1, 1, CURDATE() - INTERVAL 6 DAY, 15),
(2, 1, CURDATE() - INTERVAL 5 DAY, 22),
(3, 1, CURDATE() - INTERVAL 4 DAY, 18),
(4, 1, CURDATE() - INTERVAL 3 DAY, 25),
(5, 1, CURDATE() - INTERVAL 2 DAY, 30),
(6, 1, CURDATE() - INTERVAL 1 DAY, 28),
(7, 1, CURDATE(), 12),
(8, 2, CURDATE() - INTERVAL 6 DAY, 10),
(9, 2, CURDATE() - INTERVAL 5 DAY, 14),
(10, 2, CURDATE() - INTERVAL 4 DAY, 12),
(11, 2, CURDATE() - INTERVAL 3 DAY, 18),
(12, 2, CURDATE() - INTERVAL 2 DAY, 20),
(13, 2, CURDATE() - INTERVAL 1 DAY, 16),
(14, 2, CURDATE(), 8),
(15, 10, CURDATE() - INTERVAL 6 DAY, 20),
(16, 10, CURDATE() - INTERVAL 5 DAY, 25),
(17, 10, CURDATE() - INTERVAL 4 DAY, 30),
(18, 10, CURDATE() - INTERVAL 3 DAY, 35),
(19, 10, CURDATE() - INTERVAL 2 DAY, 40),
(20, 10, CURDATE() - INTERVAL 1 DAY, 38),
(21, 10, CURDATE(), 15),
(22, 12, CURDATE() - INTERVAL 3 DAY, 12),
(23, 12, CURDATE() - INTERVAL 2 DAY, 18),
(24, 12, CURDATE() - INTERVAL 1 DAY, 22),
(25, 12, CURDATE(), 10),
(26, 14, CURDATE() - INTERVAL 2 DAY, 15),
(27, 14, CURDATE() - INTERVAL 1 DAY, 20),
(28, 14, CURDATE(), 8),
(29, 25, CURDATE() - INTERVAL 1 DAY, 12),
(30, 25, CURDATE(), 6),
(31, 26, CURDATE() - INTERVAL 1 DAY, 14),
(32, 26, CURDATE(), 9);

-- =============================================
-- 7. ImageFile (이미지 라이브러리)
-- (이미지는 실제 파일이 필요하므로 비워둠 - 관리자에서 업로드)
-- Product.image_file_id는 NULL로 시작 (이미지 없음)
-- =============================================
