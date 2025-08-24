package com.whisukiquest.whiskyquest_api.service;

import com.whisukiquest.whiskyquest_api.controller.converter.WhiskyConverter;
import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyInfo;
import com.whisukiquest.whiskyquest_api.repository.WhiskyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WhiskyService {

  private final WhiskyRepository repository;
  private final WhiskyConverter converter;

  @Autowired
  public WhiskyService(WhiskyRepository repository, WhiskyConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  //ユーザー詳細、登録したウイスキー詳細、評価詳細が取れてくる
  public UserDetail searchUserDetail(int userId) {
    List<Whisky> whiskyList = repository.searchWhiskyList(userId);
    List<Rating> ratingList = repository.searchRatingList(userId);
    Users users = repository.searchUserById(userId);
    UserDetail userDetail = converter.converterUserDetail(users, whiskyList, ratingList);

    return userDetail;
  }

  //ウイスキー詳細、そのウイスキーに対しての評価一覧が取れてくる
  public WhiskyDetail searchWhiskyDetail(int whiskyId) {
    Whisky whisky = repository.searchWhiskyById(whiskyId);
    List<Rating> ratingList = repository.searchRatingByWhiskyId(whiskyId);
    WhiskyDetail whiskyDetail = converter.converterWhiskyDetail(whisky, ratingList);
    return whiskyDetail;
  }


  @Transactional //ユーザー情報の新規登録
  public Users registerUsers(Users users) {
    repository.registerUser(users);
    return users;
  }

  @Transactional //ウイスキー詳細と評価の新規登録
  public WhiskyInfo registerWhiskyInfo(WhiskyInfo whiskyInfo) {
    repository.registerWhisky(whiskyInfo.getWhisky());

    //whiskyIDは自動採番の為、Whiskyを登録→whiskyIDが自動採番→自動採番されたwhiskyIdをratingのwhiskyIdにセット
    int whiskyId = whiskyInfo.getWhisky().getId();
    whiskyInfo.getRating().setWhiskyId(whiskyId);
    repository.registerRating(whiskyInfo.getRating());
    return whiskyInfo;
  }

  //ユーザー情報更新
  public Users updateUser(Users users) {
    repository.updateUser(users);
    return repository.searchUserById(users.getId());
  }


  //ウイスキー情報と評価情報の更新
  @Transactional
  public WhiskyInfo updateWhiskyInfo(WhiskyInfo whiskyInfo) {
    repository.updateWhisky(whiskyInfo.getWhisky());
    repository.updateRating(whiskyInfo.getRating());
    Whisky whisky = repository.searchWhiskyById(whiskyInfo.getWhisky().getId());
    Rating rating = repository.searchRatingById(whiskyInfo.getRating().getId());

    WhiskyInfo updateInfo = new WhiskyInfo();
    updateInfo.setWhisky(whisky);
    updateInfo.setRating(rating);
    return updateInfo;
  }
}
