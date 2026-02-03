-- =============================================
-- Hiphadi Menu Data (로컬 개발용)
-- =============================================

-- =============================================
-- 1. Admin 계정 (username: hiphadi, password: hiphadi123)
-- =============================================
INSERT IGNORE INTO admin (id, username, password, created_at, updated_at)
VALUES (1, 'hiphadi', '$2a$10$LADWdd2kPz4A0Iteeeeh5OTLUILN1BrRohs6cL02M/pLM5UA3BSFa', NOW(), NOW());

-- =============================================
-- 2. Site Settings (업장 정보)
-- =============================================
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('intro_ko', '주문은 카운터에서 선불입니다. 바틀 주문 시 주스 1L or 캔음료 3캔 제공!');
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('intro_en', 'Pay in advance at the counter. Thx :)');
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('bathroom_password', '화장실 비밀번호: 5456*');
INSERT IGNORE INTO site_setting (setting_key, setting_value) VALUES ('bottle_keep_info', '바틀 킵 기간 2개월, 3만원 킵차지 (음료포함)');

-- =============================================
-- 3. Category (메뉴 카테고리)
-- =============================================
INSERT IGNORE INTO category (id, category_name, category_eng_name, priority, created_at, updated_at) VALUES
(1, '시그니처 칵테일', 'Signature Cocktail', 1, NOW(), NOW()),
(2, '논알콜 칵테일', 'Non-alcohol Cocktail', 2, NOW(), NOW()),
(3, '칵테일', 'Cocktail', 3, NOW(), NOW()),
(4, '세트', 'SET', 4, NOW(), NOW()),
(5, '샷', 'Shot', 5, NOW(), NOW()),
(6, '맥주', 'Beer', 6, NOW(), NOW()),
(7, '사이드메뉴', 'Sides', 7, NOW(), NOW()),
(8, '위스키', 'Whiskey', 8, NOW(), NOW()),
(9, '꼬냑', 'Cognac', 9, NOW(), NOW()),
(10, '데킬라', 'Tequila', 10, NOW(), NOW()),
(11, '진', 'Gin', 11, NOW(), NOW()),
(12, '보드카', 'Vodka', 12, NOW(), NOW()),
(13, '리큐르', 'Liqueur', 13, NOW(), NOW()),
(14, '샴페인', 'Champagne', 14, NOW(), NOW());

-- =============================================
-- 4. Product (메뉴)
-- single_price: 싱글(1잔/glass) 가격
-- bottle_price: 바틀 가격
-- 가격 단위: 원 (만원 표시를 원으로 변환)
-- =============================================

-- 시그니처 칵테일 (Signature Cocktail) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(1, 'Signature 001', NULL, '위스키베이스/상콤달콤하지만 겁나 묵직한 칵테일', 18000, NULL, 1, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(2, 'Signature 002', NULL, '피트위스키 아드벡 베이스/부드럽지만 날카로운 매력', 18000, NULL, 1, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(3, 'Signature 003', NULL, '데킬라/쌉싸름/새콤달콤한 변신 칵테일', 18000, NULL, 1, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(4, 'Signature 004', NULL, '베일리스, 깔루아, 리큐르가 베이스인 밀크칵테일', 18000, NULL, 1, 'SALE', 'RECOMMEND', 4, NOW(), NOW());

-- 논알콜 칵테일 (Non-alcohol Cocktail) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(5, '버진피나콜라다', 'Virgin Pina Colada', NULL, 18000, NULL, 2, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(6, '레몬스쿼시', 'Lemon Squash', NULL, 15000, NULL, 2, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(7, '셜리템플', 'Shirley Temple', NULL, 15000, NULL, 2, 'SALE', 'NORMAL', 3, NOW(), NOW()),
(8, '샌프란시스코', 'San Francisco', NULL, 15000, NULL, 2, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(9, '신데렐라', 'Cinderella', NULL, 15000, NULL, 2, 'SALE', 'NORMAL', 5, NOW(), NOW());

-- 칵테일 (Cocktail) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(10, '헤네시콕', 'Hennessy Coke', NULL, 18000, NULL, 3, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(11, '피나콜라다', 'Pina Colada', NULL, 18000, NULL, 3, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(12, '롱아일랜드 아이스티', 'Long Island Iced Tea', '10%', 16000, NULL, 3, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(13, '갓파더', 'God Father', NULL, 16000, NULL, 3, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(14, 'AMF', 'Adios Mother Fucker', NULL, 16000, NULL, 3, 'SALE', 'NORMAL', 5, NOW(), NOW()),
(15, '블루하와이', 'Blue Hawaii', NULL, 16000, NULL, 3, 'SALE', 'NORMAL', 6, NOW(), NOW()),
(16, '미도리샤워', 'Midori Sour', '8%', 16000, NULL, 3, 'SALE', 'NORMAL', 7, NOW(), NOW()),
(17, '준벅', 'June Bugs', NULL, 16000, NULL, 3, 'SALE', 'NORMAL', 8, NOW(), NOW()),
(18, '데킬라선라이즈', 'Tequila Sunrise', '8%', 14000, NULL, 3, 'SALE', 'NORMAL', 9, NOW(), NOW()),
(19, '제임슨 진저', 'Jameson Ginger', '10%', 14000, NULL, 3, 'SALE', 'NORMAL', 10, NOW(), NOW()),
(20, '보드카토닉', 'Vodka Tonic', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 11, NOW(), NOW()),
(21, '보드카오렌지', 'Vodka Orange', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 12, NOW(), NOW()),
(22, '위스키진저', 'Whiskey Ginger', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 13, NOW(), NOW()),
(23, '핸드릭스 진토닉', 'Hendrick''s Gin Tonic', '9%', 15000, NULL, 3, 'SALE', 'RECOMMEND', 14, NOW(), NOW()),
(24, '진토닉', 'Gin Tonic', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 15, NOW(), NOW()),
(25, '아마레또 토닉', 'Amaretto Tonic', NULL, 13000, NULL, 3, 'SALE', 'NORMAL', 16, NOW(), NOW()),
(26, '예거밤', 'Jager Bomb', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 17, NOW(), NOW()),
(27, '말리부오렌지', 'Malibu Orange', '4%', 13000, NULL, 3, 'SALE', 'NORMAL', 18, NOW(), NOW()),
(28, '말리부콕', 'Malibu Coke', '4%', 13000, NULL, 3, 'SALE', 'NORMAL', 19, NOW(), NOW()),
(29, '잭콕', 'Jack Coke', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 20, NOW(), NOW()),
(30, '버번콕', 'Bourbon Coke', NULL, 13000, NULL, 3, 'SALE', 'NORMAL', 21, NOW(), NOW()),
(31, '럼콕', 'Rum Coke', '9%', 13000, NULL, 3, 'SALE', 'NORMAL', 22, NOW(), NOW());

-- 세트 (SET) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(32, '데킬라 6샷 + 나쵸', 'Tequila 6 shots + Nacho', NULL, 35000, NULL, 4, 'SALE', 'RECOMMEND', 1, NOW(), NOW());

-- 샷 (Shot) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(33, '호세 쿠엘보 1샷', 'Jose Cuervo 1 shot', NULL, 8000, NULL, 5, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(34, '호세 쿠엘보 6샷', 'Jose Cuervo 6 shots', NULL, 40000, NULL, 5, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(35, '데킬라 1샷', 'Tequila 1 shot', NULL, 5000, NULL, 5, 'SALE', 'NORMAL', 3, NOW(), NOW()),
(36, '데킬라 6샷', 'Tequila 6 shots', NULL, 25000, NULL, 5, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(37, '데킬라슬래머 1샷', 'Tequila Slammer 1 shot', NULL, 6000, NULL, 5, 'SALE', 'NORMAL', 5, NOW(), NOW()),
(38, '데킬라슬래머 6샷', 'Tequila Slammer 6 shots', NULL, 30000, NULL, 5, 'SALE', 'NORMAL', 6, NOW(), NOW()),
(39, '레몬드랍 1샷', 'Lemon Drop 1 shot', NULL, 5000, NULL, 5, 'SALE', 'NORMAL', 7, NOW(), NOW()),
(40, '레몬드랍 6샷', 'Lemon Drop 6 shots', NULL, 25000, NULL, 5, 'SALE', 'NORMAL', 8, NOW(), NOW());

-- 맥주 (Beer) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(41, '버드와이저', 'Budweiser', NULL, 10000, NULL, 6, 'SALE', 'NORMAL', 1, NOW(), NOW());

-- 사이드메뉴 (Sides) - 싱글만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(42, '나쵸칩', 'Nacho Chips', NULL, 12000, NULL, 7, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(43, '닭강정과 새우칩', 'Chicken & Shrimp Chips', '매운달콤맛 or 마늘간장맛', 20000, NULL, 7, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(44, '바닐라아이스크림', 'Vanilla Ice Cream', NULL, 10000, NULL, 7, 'SALE', 'NORMAL', 3, NOW(), NOW());

-- 위스키 (Whiskey) - 싱글 + 바틀
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(45, '조니워커 블루', 'Johnnie Walker Blue', NULL, NULL, 790000, 8, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(46, '달모어 12Y', 'Dalmore 12Y', NULL, 25000, 360000, 8, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(47, '맥캘란 12Y', 'Macallan 12Y', NULL, 23000, 350000, 8, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(48, '발베니 12Y', 'Balvenie 12Y', NULL, 21000, 330000, 8, 'SALE', 'RECOMMEND', 4, NOW(), NOW()),
(49, '아드벡 10Y', 'Ardbeg 10Y', NULL, 18000, 290000, 8, 'SALE', 'NORMAL', 5, NOW(), NOW()),
(50, '글렌 리벳 12Y', 'Glenlivet 12Y', NULL, 17000, 270000, 8, 'SALE', 'NORMAL', 6, NOW(), NOW()),
(51, '글렌 드로낙 12Y', 'Glendronach 12Y', NULL, 16000, 260000, 8, 'SALE', 'NORMAL', 7, NOW(), NOW()),
(52, '글렌 피딕 12Y', 'Glenfiddich 12Y', NULL, 15000, 240000, 8, 'SALE', 'NORMAL', 8, NOW(), NOW()),
(53, '글렌 모렌지 10Y', 'Glenmorangie 10Y', NULL, 14000, 230000, 8, 'SALE', 'NORMAL', 9, NOW(), NOW()),
(54, '조니워커 블랙', 'Johnnie Walker Black', NULL, 12000, 180000, 8, 'SALE', 'NORMAL', 10, NOW(), NOW()),
(55, '몽키숄더', 'Monkey Shoulder', NULL, 12000, 180000, 8, 'SALE', 'NORMAL', 11, NOW(), NOW()),
(56, '메이커스 마크', 'Maker''s Mark', NULL, 12000, 180000, 8, 'SALE', 'NORMAL', 12, NOW(), NOW()),
(57, '잭다니엘', 'Jack Daniel''s', NULL, 12000, 170000, 8, 'SALE', 'NORMAL', 13, NOW(), NOW()),
(58, '짐빔', 'Jim Beam', NULL, 10000, 150000, 8, 'SALE', 'NORMAL', 14, NOW(), NOW()),
(59, '제임슨', 'Jameson', NULL, 10000, 150000, 8, 'SALE', 'NORMAL', 15, NOW(), NOW());

-- 꼬냑 (Cognac) - 싱글 + 바틀
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(60, '헤네시 VSOP', 'Hennessy VSOP', NULL, 15000, 240000, 9, 'SALE', 'RECOMMEND', 1, NOW(), NOW());

-- 데킬라 (Tequila) - 싱글 + 바틀
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(61, '패트론 실버', 'Patron Silver', NULL, 18000, 290000, 10, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(62, '시에라', 'Sierra', NULL, 11000, 170000, 10, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(63, '호세 쿠엘보', 'Jose Cuervo', NULL, 8000, 130000, 10, 'SALE', 'NORMAL', 3, NOW(), NOW());

-- 진 (Gin) - 싱글 + 바틀
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(64, '핸드릭스', 'Hendrick''s', NULL, 15000, 190000, 11, 'SALE', 'RECOMMEND', 1, NOW(), NOW());

-- 보드카 (Vodka) - 바틀만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(65, '그레이 구스', 'Grey Goose', NULL, NULL, 200000, 12, 'SALE', 'RECOMMEND', 1, NOW(), NOW());

-- 리큐르 (Liqueur) - 싱글 + 바틀
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(66, '아구아', 'Agwa', NULL, 12000, 180000, 13, 'SALE', 'NORMAL', 1, NOW(), NOW()),
(67, '예거마이스터', 'Jager Meister', NULL, 10000, 130000, 13, 'SALE', 'NORMAL', 2, NOW(), NOW()),
(68, '말리부', 'Malibu', NULL, 10000, 130000, 13, 'SALE', 'NORMAL', 3, NOW(), NOW());

-- 샴페인 (Champagne) - 바틀만
INSERT IGNORE INTO product (id, name, eng_name, description, single_price, bottle_price, category_id, status, is_recommend, custom_order, created_at, updated_at) VALUES
(69, '아르망 드 브리냑 골드', 'Armand de Brignac Gold', NULL, NULL, 1500000, 14, 'SALE', 'RECOMMEND', 1, NOW(), NOW()),
(70, '돔페리뇽', 'Dom Perignon', NULL, NULL, 700000, 14, 'SALE', 'RECOMMEND', 2, NOW(), NOW()),
(71, '모엣 샹동', 'Moet & Chandon', NULL, NULL, 210000, 14, 'SALE', 'RECOMMEND', 3, NOW(), NOW()),
(72, '샹동가든 스프릿', 'Chandon Garden Spritz', NULL, NULL, 170000, 14, 'SALE', 'NORMAL', 4, NOW(), NOW()),
(73, '오페라 프리마', 'Opera Prima', NULL, NULL, 79000, 14, 'SALE', 'NORMAL', 5, NOW(), NOW()),
(74, '오페라 모스카토', 'Opera Moscato', NULL, NULL, 79000, 14, 'SALE', 'NORMAL', 6, NOW(), NOW()),
(75, '빌레벨보 모스카토', 'Vallebelbo Moscato', NULL, NULL, 79000, 14, 'SALE', 'NORMAL', 7, NOW(), NOW());

-- =============================================
-- 5. Suggestion (건의사항 샘플)
-- =============================================
INSERT IGNORE INTO suggestion (id, content, created_at, updated_at) VALUES
(1, '음악 볼륨 조금만 낮춰주시면 대화하기 더 편할 것 같아요!', NOW(), NOW()),
(2, '칵테일 종류가 더 다양했으면 좋겠습니다.', NOW(), NOW()),
(3, '분위기 너무 좋아요. 자주 올게요!', NOW(), NOW());

-- =============================================
-- 6. ProductClickDaily (클릭 통계 샘플)
-- =============================================
INSERT IGNORE INTO product_click_daily (id, product_id, click_date, click_count) VALUES
(1, 1, CURDATE() - INTERVAL 6 DAY, 15),
(2, 1, CURDATE() - INTERVAL 5 DAY, 22),
(3, 1, CURDATE() - INTERVAL 4 DAY, 18),
(4, 1, CURDATE(), 12),
(5, 11, CURDATE() - INTERVAL 3 DAY, 25),
(6, 11, CURDATE() - INTERVAL 2 DAY, 30),
(7, 11, CURDATE() - INTERVAL 1 DAY, 28),
(8, 11, CURDATE(), 15),
(9, 47, CURDATE() - INTERVAL 2 DAY, 20),
(10, 47, CURDATE() - INTERVAL 1 DAY, 18),
(11, 47, CURDATE(), 10);
