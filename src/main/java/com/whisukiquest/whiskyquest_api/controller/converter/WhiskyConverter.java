package com.whisukiquest.whiskyquest_api.controller.converter;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *ユーザー、ウイスキー、評価の関係性をもとに、 ユーザー詳細情報やウイスキー詳細情報を構築するための変換処理を行うconverterです。
 */
@Component
public class WhiskyConverter {

  /**
   *ユーザーに紐づくウイスキー情報と評価情報をマッピングします。
   *ウイスキーと評価はユーザーに対して複数存在するので、ループを回してユーザー詳細情報を組み立てます。
   * 1ユーザー：多ウイスキー：多評価　
   * @param users ユーザー情報
   * @param whiskyList ウイスキー情報一覧
   * @param ratingList 評価情報一覧
   * @return ユーザー詳細情報
   */
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

  /**
   * ウイスキーに紐づく評価情報をマッピングします。
   * 評価はウイスキーに対して複数存在するので、ループを回してウイスキー詳細情報を組み立てます。
   * 1ウイスキー：多評価
   * @param whisky ウイスキー情報
   * @param ratingList 評価情報一覧
   * @return ウイスキー詳細情報
   */
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