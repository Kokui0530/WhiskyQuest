## WhisukiQuest-お気に入り１本を探しに-

## はじめに
このリポジトリは、Javaを学習中の私が作成したウイスキー記録管理アプリのプロジェクトです。
### 制作背景
私は現在、酒屋で働いており、またハイボール好きとして日頃からさまざまなウイスキーを楽しんでいます。  
これまでに飲んだり人から勧められたりしたウイスキーは数多くありますが、名前や味を記録していなかったため、「あのとき美味しかったウイスキーが思い出せない」という場面が度々ありました。  
そこで、お酒を飲んでいるときでも簡単に記録できる仕組みがあれば便利だと考えました。  
まずはその場でウイスキーの名前だけを登録し、後から味や値段、評価を追記できるようなアプリケーションを制作しました。

## 使用技術
### バックエンド
- **Java 21**
- **Spring Boot 3.5.4**
- **MyBatis 3.0.5**
- **MySQL**
- **H2 Database**
- **Lombok**
- **Apache Commons Lang 3.18.0**
- **Spring Validation**
- **Springdoc OpenAPI 2.8.9**
- **JUnit / Spring Boot Test**

### フロントエンド
- **TypeScript: 5.2.2**
- **Next.js: 15.5.3**
- **React: 19.1.0**
- **React DOM: 19.1.0**
- **Tailwind CSS: 4.0.1** 
（フロントに関してはAIを活用して効率的に開発を推進）

## WhiskyQuest 公開URL
[WhiskyQuest](http://whiskyquestfrontalb-2012089750.ap-northeast-1.elb.amazonaws.com/)

## 機能一覧
### ユーザー新規登録 
![ユーザー新規登録 (1)](https://github.com/user-attachments/assets/91b8efcf-d065-497a-af09-a3db42f0b852)

### ユーザー更新
![ユーザー更新 (1)](https://github.com/user-attachments/assets/22886d62-b84c-49aa-8b1a-b3a5b1b56f1e)

### ユーザー削除
![ユーザー削除 (1)](https://github.com/user-attachments/assets/2b937f11-1662-4a8d-a49f-640436be3cba)

### ウイスキー・評価登録
![ウイスキー登録 (1)](https://github.com/user-attachments/assets/21b9652c-8b9d-40cf-98fa-4a58e16886dc)

### ウイスキー・評価更新
![ウイスキー更新 (1)](https://github.com/user-attachments/assets/7f9f9eb9-2ce2-43c9-93ac-92346d4b766a)

### ウイスキー・評価削除
![ウイスキー削除 (1)](https://github.com/user-attachments/assets/67e6100a-776c-4836-a32f-bc4f9a0d46f1)

### ランキング機能
![無題のビデオ ‐ Clipchampで作成](https://github.com/user-attachments/assets/410f2d7f-982d-482d-afb9-6f0858f741a9)


## APIエンドポイント一覧
### ユーザー情報に関する管理
| HTTPメソッド | URL | 処理内容 |
|----------|----------|----------|
| Get  | /user/{userId}   | ユーザー詳細検索   |
| Post   | /registerUser   | ユーザー新規登録   |
| Put  | /updateUser/{userId}   | ユーザー情報更新   |
| Put   | /deleteUser/{userId}   | ユーザー情報削除（論理削除）   |


### ウイスキー情報と評価情報に関する管理
| HTTPメソッド | URL | 処理内容 |
|----------|----------|----------|
| Get  | /whisky/{whiskyId}   | ウイスキー詳細情報   |
| Get  | /whiskyRanking   | ウイスキーランキング詳細   |
| Post   | /Whisky   | ウイスキー詳細と評価の新規登録（重複チェックあり）   |
| Post   | /registerWhisky   | ウイスキー詳細と評価の新規登録   |
| Put  | /updateWhiskyInfo/{WhiskyId}/{RatingId}   | ウイスキー詳細と評価の更新   |
| Put   | /deleteWhisky/{userId}/{whiskyId}   | ウイスキー情報削除（論理削除）   |
| Put   | /deleteRating/{userId}/{ratingId}   | 評価情報削除（論理削除）   |


## ER図

![IMG_0875](https://github.com/user-attachments/assets/0681a98d-c4ef-4aed-9361-93404f8abb8e)

## テスト結果
<img width="1890" height="788" alt="スクリーンショット 2025-09-28 232917" src="https://github.com/user-attachments/assets/bcc3f2c9-dbeb-475b-92c4-bd664762c33e" />


## 大変だったこと、学んだ事
最初の要件定義で実装内容を固めたつもりでしたが、開発を進める中で仕様変更が多く発生し、スムーズに実装できない場面がありました。
その経験から、ユーザー視点で「どのように操作し、どのように処理が進むのか」をイメージしながら進めることの大切さを学びました。

また、アプリケーション開発はコードを書くだけではなく、ユースケースの想定すること、
セキュリティ、運用面など多角的な観点を意識する必要があると実感しました。

フロントエンドはAIを活用して作成しましたが、アプリケーションが複雑になるにつれ、AIに対する指示の出し方も難しくなりました。
そこで、ドキュメントを丁寧に作成することで、AIから的確な回答を得られることを体感し、ドキュメント作成の重要性も学びました。

### こだわった所
手軽に簡単に登録できるように、
新規登録後、ログイン後ともに1番最初の画面でウイスキー登録画面を表示させました。

ランキング機能の実装にあたり、SQLとJava（Converter）の処理を適切に分担するよう工夫しました。
データベース内で完結できる処理はDB側に任せることで、通信コストやメモリ消費を抑えられることを学び、
また、MyBatisを用いてCRUD処理だけでなく集計や並び替えなどのSQLも積極的に学習・実装し、より効率的な仕組みを構築しました。


## 今後の開発計画
### 短期目標
-入力バリデーションの強化  
-入力チェックの精度向上。必須項目の確認や形式チェックを徹底  
-ログイン機能の実装（Spring Security）

### 中期目標
-キーワード検索機能の追加  
-ウイスキー画像の管理機能の追加  

### 長期目標
-ウイスキー情報の共有機能の追加  
-TOPページへの、新作ウイスキーや注目トピックを表示  

## 制作期間
2025/8/13~2025/9/30  
制作時間　180時間

## リンク
デプロイ版：  http://whiskyquestfrontalb-2012089750.ap-northeast-1.elb.amazonaws.com/  
フロントエンドリポジトリ：https://github.com/Kokui0530/WhiskyQuest_Front.git   

## おわりに
今回が初めての「ゼロから自分で作ったアプリケーション」でしたが、学習を通じて得られなかった多くの気づきや学びを得ることができました。  
開発中は数多くの壁に直面しましたが、それを乗り越えられたときの達成感や楽しさは、それ以上に大きなものでした。  
今後はさらにJavaの理解を深め、より良いアプリケーションを作れるよう成長していきたいと考えています。  

最後までお読みいただき、ありがとうございました。
