package com.whisukiquest.whiskyquest_api.controller.converter;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.RatingAverage;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyRanking;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
      if (users != null && users.getId() == whisky.getUserId()) {
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

  /**
   * ウイスキーに紐づく評価情報をマッピングし、表示用ランキングDTOへ変換します。 多ウイスキー：多評価
   *
   * @param whiskyList        ウイスキー一覧
   * @param ratingAverageList 評価の平均値と件数の一覧
   * @return 評価順(降順)に並んだ、ウイスキーランキング情報
   */
  public List<WhiskyRanking> converterWhiskyRanking(
      List<Whisky> whiskyList, List<RatingAverage> ratingAverageList) {
    Map<Integer, RatingAverage> ratingMap = new HashMap<>(); //ウイスキーID:評価情報のMap

    for (RatingAverage average : ratingAverageList) {
      if (ratingMap.put(average.getWhiskyId(), average) != null) { //MapにウイスキーID、平均評価を登録
        throw new IllegalStateException("ウイスキーIDが重複しています。");
      } //もしウイスキーIDが重複した場合処理を停止
    }

    List<WhiskyRanking> whiskyRankingList = new ArrayList<>();
    for (Whisky whisky : whiskyList) { //ウイスキーリストを回して
      RatingAverage ratingAverage = ratingMap.get(whisky.getId());
      //ratingMapから同じウイスキーIDに対する評価情報を取得

      WhiskyRanking whiskyRanking = new WhiskyRanking();
      whiskyRanking.setWhiskyId(whisky.getId());
      whiskyRanking.setName(whisky.getName());

      if (ratingAverage != null) { //平均評価があれば値をセット
        whiskyRanking.setAverageRating(ratingAverage.getAverageRating());
      } else { //もし平均評価が0の場合は0をセット
        whiskyRanking.setAverageRating(0);
      }

      if (ratingAverage != null) { //評価件数があれば値をセット
        whiskyRanking.setRatingCount(ratingAverage.getRatingCount());
      } else { //もし評価が0件の場合は0をセット
        whiskyRanking.setRatingCount(0);
      }

      //表示用DTO WhiskyRankingへ値をセット
      whiskyRankingList.add(whiskyRanking);
    }
    // 降順に並び替え
    whiskyRankingList.sort(Comparator.comparingDouble(
        (WhiskyRanking whiskyRanking) -> whiskyRanking.getAverageRating()).reversed());

    return whiskyRankingList;
  }
}