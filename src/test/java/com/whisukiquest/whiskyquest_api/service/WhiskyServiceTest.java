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
import com.whisukiquest.whiskyquest_api.repository.WhiskyRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    Mockito.when(repository.searchWhisky(whiskyId)).thenReturn(whisky);
    Mockito.when(repository.searchRatingByWhiskyId(whiskyId)).thenReturn(ratingList);
    Mockito.when(converter.converterWhiskyDetail(whisky, ratingList)).thenReturn(expected);

    WhiskyDetail actual = sut.searchWhiskyDetail(whiskyId);

    verify(repository, times(1)).searchWhisky(whiskyId);
    verify(repository, times(1)).searchRatingByWhiskyId(whiskyId);
    verify(converter, times(1)).converterWhiskyDetail(whisky, ratingList);

    assertEquals(expected, actual);
  }
}
