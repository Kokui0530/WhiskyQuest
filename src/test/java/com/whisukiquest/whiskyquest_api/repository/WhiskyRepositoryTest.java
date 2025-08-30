package com.whisukiquest.whiskyquest_api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
public class WhiskyRepositoryTest {

  @Autowired
  private WhiskyRepository sut;


  @Test//searchUserById
  void ユーザーの詳細１件の検索が出来る事() {
    Users actual = sut.searchUserById(1);
    assertThat(actual.getId()).isEqualTo(1);
  }

  @Test//searchWhiskyList
  void ウイスキー情報一覧の検索が出る事() {
    List<Whisky> actual = sut.searchWhiskyList(1);
    assertThat(actual.size()).isEqualTo(2);
  }

  @Test//searchWhiskyById
  void ウイスキー情報1件の検索が出来る事() {
    Whisky actual = sut.searchWhiskyById(1);
    assertThat(actual.getId()).isEqualTo(1);
  }

  @Test//searchRatingList
  void 評価情報一覧が検索出来る事() {
    List<Rating> actual = sut.searchRatingList(1);
    assertThat(actual.size()).isEqualTo(2);
  }

  @Test//searchRatingByWhiskyId
  void ウイスキーIDで評価情報が検索出来る事() {
    List<Rating> actual = sut.searchRatingByWhiskyId(1);
    assertThat(actual.size()).isEqualTo(1);
  }

  @Test//searchRatingById
  void 評価情報1件の検索が出来る事() {
    Rating actual = sut.searchRatingById(1);
    assertThat(actual.getId()).isEqualTo(1);
  }

  @Test//registerUser
  void ユーザー情報の新規登録が出来る事() {
    Users user = new Users();
    user.setUserName("高橋　杏里");
    user.setMail("anri@gmail.com");
    user.setPassword("qwe1");

    sut.registerUser(user);
    Users actual = sut.searchUserById(user.getId());

    assertThat(actual).isEqualTo(user);

  }

  @Test//registerWhisky
  void ウイスキー情報の新規登録が出来る事() {
    Whisky whisky = new Whisky();
    whisky.setName("メーカーズマーク");
    whisky.setTaste("甘い");
    whisky.setDrinkingStyle("ハイボール");
    whisky.setPrice(3000);
    whisky.setMemo("ボトルもかわいい");

    sut.registerWhisky(whisky);
    Whisky actual = sut.searchWhiskyById(whisky.getId());

    assertThat(actual).isEqualTo(whisky);
  }

  @Test//registerRating
  void 評価情報の新規登録が出来る事() {
    Rating rating = new Rating();
    rating.setRating(5);
    rating.setUserId(2);
    rating.setWhiskyId(2);

    sut.registerRating(rating);
    List<Rating> actual = sut.searchRatingList(rating.getUserId());
    //rating.getUserId()としているので、rating全件ではなく上記でsetしてるuserId(2)のratingを
    //取得してactualに代入してる
    assertThat(actual.size()).isEqualTo(3);
    //なのでサイズで「全件＋今追加した分」のisEqualTo(11)じゃなくて、isEqualTo(3)になる

  }

  @Test// updateUser
  void ユーザーIDでユーザー情報の更新が出来る事() {
    Users users = new Users();
    users.setId(1);
    users.setUserName("山田　陽介");
    users.setMail("taro@example.com");
    users.setPassword("pass1234");

    sut.updateUser(users);
    Users actual = sut.searchUserById(1);

    assertThat(actual).isEqualTo(users);
  }

  @Test//updateWhisky
  void ウイスキー情報の更新が出来る事() {
    Whisky whisky = new Whisky();
    whisky.setId(1);
    whisky.setName("山崎12年");
    whisky.setTaste("フルーティーで華やか");
    whisky.setDrinkingStyle("ストレート、ハイボール");
    whisky.setPrice(12000);
    whisky.setMemo("銀座のバーで初めて飲んだ。香りが素晴らしい");

    sut.updateWhisky(whisky);
    Whisky actual = sut.searchWhiskyById(1);

    assertThat(actual).isEqualTo(whisky);

  }

  @Test//updateRating
  void 評価情報の更新が出来る事() {
    Rating rating = new Rating();
    rating.setId(1);
    rating.setUserId(1);
    rating.setWhiskyId(1);
    rating.setRating(4);

    sut.updateRating(rating);
    Rating actual = sut.searchRatingById(1);

    assertThat(actual.getId()).isEqualTo(rating.getId());
    assertThat(actual.getRating()).isEqualTo(rating.getRating());
  }

  @Test //deleteUser
  void ユーザー情報が論理削除出来ること() {
    int id = 2;
    Users user = new Users();
    user.setId(id);

    sut.deleteUser(user);
    assertNull(sut.searchUserById(id), "論理削除されたユーザーは検索出来ない事");
  }

  @Test //deleteWhisky
  void ウイスキー情報が論理削除出来ること(){
    int id = 2;
    int userId = 2;
    Whisky whisky = new Whisky();
    whisky.setId(id);
    whisky.setUserId(userId);

    sut.deleteWhisky(whisky);
    assertNull(sut.searchWhiskyById(id), "論理削除されたウイスキー情報は検索出来ない事");

  }

  @Test //deleteRating
  void 評価情報が論理削除出来ること() {
    int id = 2;
    int userId = 2;
    Rating rating = new Rating();
    rating.setId(id);
    rating.setUserId(userId);

    sut.deleteRating(rating);
    assertNull(sut.searchRatingById(id), "論理削除されている評価情報は検索出来ない事");

  }
}
