INSERT INTO users (user_name, mail, password, is_deleted) VALUES
('山田 太郎', 'taro@example.com', 'pass1234', false),
('鈴木 花子', 'hanako@example.com', 'secure5678', false),
('田中 健二', 'kenji@example.com', 'mypassword', false),
('小林 由紀', 'yuki@example.com', 'abc12345', false),
('伊藤 聡', 'satoshi@example.com', 'qwerty987', false);

INSERT INTO whisky (user_id, name, taste, drinking_style, price, is_deleted) VALUES
(1, '山崎12年', 'フルーティーで華やか', 'ストレート', 12000, false),
(2, '白州', '爽やかでスモーキー', 'ロック', 8000,  false),
(3, '響 ジャパニーズハーモニー', '甘くまろやか', 'ハイボール', 10000, false),
(4, 'マッカラン12年', 'リッチでシェリー樽の甘み', 'ストレート', 11000, false),
(5, 'ボウモア12年', 'ピート香が強い', '水割り', 6000,  false),
(1, 'ラフロイグ10年', '薬品のような強烈なピート', 'ストレート', 7000, false),
(2, 'グレンフィディック12年', '洋梨のような香り', 'ロック', 5000, false),
(3, 'タリスカー10年', '海のような塩気とスモーク', 'ストレート', 6500,  false),
(4, '竹鶴ピュアモルト', '力強いモルト感', 'ハイボール', 4500,  false),
(5, '知多', '軽やかで穏やか', '水割り', 4000, false);


INSERT INTO rating (user_id, whisky_id, rating, comment, is_deleted) VALUES
(1, 1, 5,'大阪で飲んでおいしかった',false),
(2, 2, 4,'スモーキーで少し癖が強い' ,false),
(3, 3, 5,'甘みがあって飲みやすい' ,false),
(4, 4, 4,'香りが華やかでフルーティー' ,false),
(5, 5, 3,'余韻が長くて心地よい' ,false),
(1, 6, 5,'バランスが良く食事にも合う' ,false),
(2, 7, 4,'ストレートよりロック向き' ,false),
(3, 8, 5,'力強い味わいで印象的' ,false),
(4, 9, 4,'後味がすっきりして飲みやすい',false),
(5, 10, 3,'初心者にもおすすめできる' ,false);
