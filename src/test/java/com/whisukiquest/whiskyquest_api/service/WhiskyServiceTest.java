package com.whisukiquest.whiskyquest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.whisukiquest.whiskyquest_api.controller.converter.WhiskyConverter;
import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyInfo;
import com.whisukiquest.whiskyquest_api.repository.WhiskyRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class WhiskyServiceTest {

  @Mock
  private WhiskyRepository repository;

  @Mock
  private WhiskyConverter converter;

  private WhiskyService sut;

  @BeforeEach
  void 事前準備() {
    sut = new WhiskyService(repository, converter);
  }


  @Test//searchUserDetail
  void ユーザー詳細検索＿リポジトリとコンバーターが適切に呼び出せていること() {
    int userId = 999;
    List<Whisky> whiskyList = new ArrayList<>();
    List<Rating> ratingList = new ArrayList<>();
    Users users = new Users();
    users.setId(userId);
    UserDetail expected = new UserDetail();
    expected.setUsers(users);
    expected.setWhiskyList(whiskyList);
    expected.setRatingList(ratingList);

    Mockito.when(repository.searchWhiskyList(userId)).thenReturn(whiskyList);
    Mockito.when(repository.searchRatingList(userId)).thenReturn(ratingList);
    Mockito.when(repository.searchUserById(userId)).thenReturn(users);
    Mockito.when(converter.converterUserDetail(users, whiskyList, ratingList)).thenReturn(expected);

    UserDetail actual = sut.searchUserDetail(userId);

    verify(repository, times(1)).searchWhiskyList(userId);
    verify(repository, times(1)).searchRatingList(userId);
    verify(repository, times(1)).searchUserById(userId);
    verify(converter, times(1)).converterUserDetail(users, whiskyList, ratingList);

    assertEquals(actual, expected);
  }


  @Test //searchWhiskyDetail
  void ウイスキー詳細検索＿リポジトリとコンバーターが適切に取れてくる事() {
    int whiskyId = 999;
    Whisky whisky = new Whisky();
    whisky.setId(whiskyId);
    List<Rating> ratingList = new ArrayList<>();
    WhiskyDetail expected = new WhiskyDetail();
    expected.setWhisky(whisky);
    expected.setRatingList(ratingList);

    Mockito.when(repository.searchWhiskyById(whiskyId)).thenReturn(whisky);
    Mockito.when(repository.searchRatingByWhiskyId(whiskyId)).thenReturn(ratingList);
    Mockito.when(converter.converterWhiskyDetail(whisky, ratingList)).thenReturn(expected);

    WhiskyDetail actual = sut.searchWhiskyDetail(whiskyId);

    verify(repository, times(1)).searchWhiskyById(whiskyId);
    verify(repository, times(1)).searchRatingByWhiskyId(whiskyId);
    verify(converter, times(1)).converterWhiskyDetail(whisky, ratingList);

    assertEquals(expected, actual);
  }

  @Test //registerUsers
  void ユーザーの新規登録＿適切にリポジトリが呼び出せてること(){
    int id = 999;
    Users users = new Users();
    users.setId(id);
    sut.registerUsers(users);

   verify(repository,times(1)).registerUser(users);
  }

  @Test //registerWhiskyInfo
  void ウイスキー情報と評価情報の登録＿適切にリポジトリが呼び出せている事(){
    int id = 999;
    Whisky whisky = new Whisky();
    whisky.setId(id);
    Rating rating = new Rating();
    rating.setId(id);
    WhiskyInfo whiskyInfo = new WhiskyInfo();
    whiskyInfo.setWhisky(whisky);
    whiskyInfo.setRating(rating);

    sut.registerWhiskyInfo(whiskyInfo);

    verify(repository,times(1)).registerWhisky(whisky);
    verify(repository,times(1)).registerRating(rating);

  }

  @Test //updateUser
  void ユーザー情報の更新＿適切にリポジトリが呼び出されている事(){
    Users users = new Users();
    users.setId(1);
    users.setUserName("山田　太郎");
    sut.updateUser(users);

    Mockito.when(repository.searchUserById(1)).thenReturn(users);
    Users actual = repository.searchUserById(1);

    verify(repository, times(1)).updateUser(users);
    assertEquals(users.getId(), actual.getId());
    assertEquals(users.getUserName(), actual.getUserName());
  }

  @Transactional
  @Test //updateWhiskyInfo
  void ウイスキー情報と評価情報の更新_リポジトリが適切に呼び出されている事() {
    Whisky whisky = new Whisky();
    whisky.setId(1);
    Rating rating = new Rating();
    rating.setId(1);
    WhiskyInfo whiskyInfo = new WhiskyInfo();
    whiskyInfo.setWhisky(whisky);
    whiskyInfo.setRating(rating);
    Mockito.when(repository.searchWhiskyById(1)).thenReturn(whisky);
    Mockito.when(repository.searchRatingById(1)).thenReturn(rating);

    WhiskyInfo actual = sut.updateWhiskyInfo(whiskyInfo);

    verify(repository, times(1)).updateWhisky(whisky);
    verify(repository, times(1)).updateRating(rating);
    assertEquals(actual.getWhisky().getId(), whisky.getId());
    assertEquals(actual.getRating().getId(), rating.getId());
  }

  @Test //deleteUser
  void ユーザー情報の倫理削除が出来る事＿適切にリポジトリが呼び出せている事() {
    int id = 999;
    Users user = new Users();
    user.setId(id);
    sut.deleteUser(id);

    verify(repository, times(1)).deleteUser(user);

  }

  @Test //deleteWhisky
  void ウイスキー情報の論理削除が出来る事＿適切にリポジトリが呼び出されている事() {
    int userId = 999;
    Whisky whisky = new Whisky();
    whisky.setUserId(userId);
    sut.deleteWhisky(userId);

    verify(repository, times(1)).deleteWhisky(whisky);
  }

  @Test //deleteRating
  void 評価情報の論理削除が出来る事＿適切にリポジトリが呼び出せている事(){
    int useId = 999;
    Rating rating = new Rating();
    rating.setUserId(useId);
    sut.deleteRating(useId);

    verify(repository, times(1)).deleteRating(rating);
  }
}
