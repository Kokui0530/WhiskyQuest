package com.whisukiquest.whiskyquest_api.repository;

import static org.assertj.core.api.Assertions.assertThat;

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


  @Test
    //searchUserById
  void ユーザーの詳細検索が出来る事() {
    Users actual = sut.searchUserById(1);
    assertThat(actual.getId()).isEqualTo(1);
  }

  @Test
    //searchWhiskyList
  void ウイスキー情報一覧の検索が出る事() {
    List<Whisky> actual = sut.searchWhiskyList(1);
    assertThat(actual.size()).isEqualTo(2);
  }

  @Test
    //searchRatingList
  void 評価情報一覧が検索出来る事() {
    List<Rating> actual = sut.searchRatingList(1);
    assertThat(actual.size()).isEqualTo(2);
  }

  @Test
    //searchWhisky
  void ウイスキーIDでウイスキー情報が検索出来る事() {
    Whisky actual = sut.searchWhisky(1);
    assertThat(actual.getId()).isEqualTo(1);
  }

  @Test
    //searchRatingByWhiskyId
  void ウイスキーIDで評価情報が検索出来る事() {
    List<Rating> actual = sut.searchRatingByWhiskyId(1);
    assertThat(actual.size()).isEqualTo(1);
  }

  @Test  //registerUser
  void ユーザー情報の新規登録が出来る事() {
    Users user = new Users();
    user.setUserName("高橋　杏里");
    user.setMail("anri@gmail.com");
    user.setPassword("qwe1");

    sut.registerUser(user);
    Users actual = sut.searchUserById(user.getId());

    assertThat(actual).isEqualTo(user);

  }

  @Test   //registerWhisky
  void ウイスキー情報の新規登録が出来る事() {
    Whisky whisky = new Whisky();
    whisky.setName("メーカーズマーク");
    whisky.setTaste("甘い");
    whisky.setDrinkingStyle("ハイボール");
    whisky.setPrice(3000);
    whisky.setMemo("ボトルもかわいい");

    sut.registerWhisky(whisky);
    Whisky actual = sut.searchWhisky(whisky.getId());

    assertThat(actual).isEqualTo(whisky);
  }

  @Test   //registerRating
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
}
