
## WhisukiQuestーお気に入り１本を探しにー

## はじめに
このリポジトリは、Javaを学習中の私が作成したウイスキー記録管理アプリのプロジェクトです。
### 制作背景
私は現在酒屋で働いており、ハイボール好きとして様々なウイスキーを飲み漁ってきました。
その中で、ウイスキーの名前や味を記録していなかった為、以前飲んだ美味しかったウイスキーを思い出せないことが多々ありました。
ウイスキー名、味、値段などを記録できればいいな思い作成しました。


## 使用技術
- **Java 17**
- **Spring Boot**
- **MyBatis**
- **MySQL**
- **H2 Database**
- **Lombok**
- **Apache Commons Lang**
- **Spring Validation**
- **Springdoc OpenAPI**
- **JUnit / Spring Boot Test**

## 機能一覧

## ログイン画面

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
| Post   | /registerWhisky   | ウイスキー詳細と評価の新規登録   |
| Put  | /updateWhiskyInfo/{WhiskyId}/{RatingId}   | ウイスキー詳細と評価の更新   |
| Put   | /deleteWhisky/{userId}/{whiskyId}   | ウイスキー情報削除（論理削除）   |
| Put   | /deleteRating/{userId}/{ratingId}   | 評価情報削除（論理削除）   |

## ER図


## インフラ構成図

## テスト結果

## 工夫した点

## こだわった所

## 今後の開発計画


## 制作期間
8/13~9/
　週間

## リンク

## おわりに

