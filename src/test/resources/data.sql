INSERT INTO users (user_name, mail, password, is_deleted) VALUES
('山田 太郎', 'taro@example.com', 'pass1234', false),
('鈴木 花子', 'hanako@example.com', 'secure5678', false),
('田中 健二', 'kenji@example.com', 'mypassword', false),
('小林 由紀', 'yuki@example.com', 'abc12345', false),
('伊藤 聡', 'satoshi@example.com', 'qwerty987', false);

INSERT INTO whisky (user_id, name, taste, drinking_style, price, memo, is_deleted) VALUES
(1, '山崎12年', 'フルーティーで華やか', 'ストレート', 12000, '銀座のバーで初めて飲んだ。香りが素晴らしい。', false),
(2, '白州', '爽やかでスモーキー', 'ロック', 8000, '友人の誕生日にプレゼントで購入。森を思わせる香り。', false),
(3, '響 ジャパニーズハーモニー', '甘くまろやか', 'ハイボール', 10000, '自宅で晩酌に。贅沢な気分。', false),
(4, 'マッカラン12年', 'リッチでシェリー樽の甘み', 'ストレート', 11000, '大阪のホテルバーで飲んだ。余韻が長い。', false),
(5, 'ボウモア12年', 'ピート香が強い', '水割り', 6000, 'アイラモルトの入門におすすめ。新宿で購入。', false),
(1, 'ラフロイグ10年', '薬品のような強烈なピート', 'ストレート', 7000, '好みが分かれる味だがクセになる。', false),
(2, 'グレンフィディック12年', '洋梨のような香り', 'ロック', 5000, '軽くて飲みやすくコスパ良し。', false),
(3, 'タリスカー10年', '海のような塩気とスモーク', 'ストレート', 6500, '湘南の海を見ながら飲んだ。最高の体験。', false),
(4, '竹鶴ピュアモルト', '力強いモルト感', 'ハイボール', 4500, 'ニッカらしい骨太な味わい。', false),
(5, '知多', '軽やかで穏やか', '水割り', 4000, '居酒屋でよく頼む定番。食事に合う。', false);


INSERT INTO rating (user_id, whisky_id, rating,is_deleted) VALUES
(1, 1, 5, false),
(2, 2, 4, false),
(3, 3, 5, false),
(4, 4, 4, false),
(5, 5, 3, false),
(1, 6, 5, false),
(2, 7, 4, false),
(3, 8, 5, false),
(4, 9, 4, false),
(5, 10, 3, false);
