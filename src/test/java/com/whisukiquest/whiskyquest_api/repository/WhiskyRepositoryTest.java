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

}
