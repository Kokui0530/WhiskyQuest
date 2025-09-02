package com.whisukiquest.whiskyquest_api.controller.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WhiskyConverterTest {

private WhiskyConverter sut;

  @BeforeEach
  void 事前準備() {
    sut = new WhiskyConverter();
  }

  @Test  //converterUserDetail
  void ユーザーのウイスキー情報と評価情報が紐づけ出来る事(){
    int id = 999;
    Users users = new Users();
    users.setId(id);

    Whisky whisky = new Whisky();
    whisky.setId(id);
    whisky.setUserId(id);
    List<Whisky> whiskyList = List.of(whisky);

    Rating rating = new Rating();
    rating.setWhiskyId(id);
    rating.setUserId(id);
    List<Rating> ratingList = List.of(rating);

    UserDetail expected = new UserDetail();
    expected.setUsers(users);
    expected.setWhiskyList(whiskyList);
    expected.setRatingList(ratingList);

    UserDetail actual = sut.converterUserDetail(users,whiskyList,ratingList);

    assertEquals(expected.getUsers(),actual.getUsers());
  }


  @Test //converterWhiskyDetail
  void ウイスキー情報と評価情報の紐づけ(){
    int whiskyId = 999;
    Whisky whisky = new Whisky();
    whisky.setId(whiskyId);

    Rating rating = new Rating();
    rating.setWhiskyId(whiskyId);
    List<Rating> ratingList = List.of(rating);

    WhiskyDetail expected = new WhiskyDetail();
    expected.setWhisky(whisky);
    expected.setRatingList(ratingList);

    WhiskyDetail actual = sut.converterWhiskyDetail(whisky,ratingList);

    assertEquals(expected.getWhisky(),actual.getWhisky());

  }

}
