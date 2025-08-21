package com.whisukiquest.whiskyquest_api.controller.converter;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WhiskyConverter {

  //1ユーザー：多ウイスキー：多評価　紐づけ
  public UserDetail converterUserDetail(
      Users users, List<Whisky> whiskyList, List<Rating> ratingList) {
    List<Whisky> converterWhiskyList = new ArrayList<>();
    List<Rating> converterRatingList = new ArrayList<>();
    UserDetail userDetail = new UserDetail();
    userDetail.setUsers(users);
    for (Whisky whisky : whiskyList) {
      if (users.getId() == whisky.getUserId()) {
        converterWhiskyList.add(whisky);
        for (Rating rating : ratingList) {
          if (whisky.getId() == rating.getWhiskyId()) {
            converterRatingList.add(rating);
          }
        }
      }
    }
    userDetail.setWhiskyList(converterWhiskyList);
    userDetail.setRatingList(converterRatingList);

    return userDetail;
  }

  //1ウイスキー：多評価　紐づけ
  public WhiskyDetail converterWhiskyDetail (
      Whisky whisky, List<Rating> ratingList){
    WhiskyDetail whiskyDetail = new WhiskyDetail();
    List<Rating> converterRatingList = new ArrayList<>();
    for (Rating rating : ratingList){
      if (rating.getWhiskyId() == whisky.getId()){
        converterRatingList.add(rating);
      }
    }
    whiskyDetail.setWhisky(whisky);
    whiskyDetail.setRatingList(converterRatingList);

  return whiskyDetail;
  }

}