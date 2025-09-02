package com.whisukiquest.whiskyquest_api.repository;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * ユーザー情報、ウイスキー情報、評価情報と紐づくrepositoryです。
 */
@Mapper
public interface WhiskyRepository {

  /**
   *ユーザー情報の検索を行います。
   * @param id ユーザーID
   * @return ユーザー情報
   */
  Users searchUserById(int id);

  /**
   * ウイスキーの一覧検索を行います。
   * @param userId　ユーザーID
   * @return ユーザーに紐づくウイスキー一覧
   */
  List<Whisky> searchWhiskyList(int userId);

  /**
   * ウイスキー情報の検索を行います。
   * @param whiskyId ウイスキーID
   * @return ウイスキーIDに紐づくウイスキー情報
   */
  Whisky searchWhiskyById(int whiskyId);

  /**
   * 評価情報の一覧検索を行います。
   * @param userId ユーザーID
   * @return ユーザーIDに紐づく評価情報一覧
   */
  List<Rating> searchRatingList(int userId);

  /**
   * 評価情報の一覧検索を行います。
   * @param whiskyId ウイスキーID
   * @return ウイスキーIDに紐づく評価情報一覧
   */
  List<Rating> searchRatingByWhiskyId(int whiskyId);

  /**
   * 評価情報の検索を行います。
   * @param ratingId 評価ID
   * @return 評価IDに紐づく評価情報
   */
  Rating searchRatingById(int ratingId);

  /**
   * ユーザーの登録を行います。
   * @param users ユーザー情報
   */
  void registerUser(Users users);

  /**
   * ウイスキーの登録を行います。
   * @param whisky ウイスキー情報
   */
  void registerWhisky(Whisky whisky);

  /**
   * 評価の登録を行います。
   * @param rating 評価情報
   */
  void registerRating(Rating rating);

  /**
   * ユーザー情報の更新を行います。
   * @param users ユーザー情報
   */
  void updateUser(Users users);

  /**
   * ウイスキー情報の更新を行います。
   * @param whisky ウイスキー情報
   */
  void updateWhisky(Whisky whisky);

  /**
   * 評価情報の更新を行います。
   * @param rating 評価情報
   */
  void updateRating(Rating rating);

  /**
   * ユーザー情報の論理削除を行います。
   * @param users ユーザー情報
   */
  void deleteUser(Users users);

  /**
   * ウイスキー情報の論理削除を行います。
   * @param whisky ウイスキー情報
   */
  void deleteWhisky(Whisky whisky);

  /**
   * 評価情報の論理削除を行います。
   * @param rating 評価情報
   */
  void deleteRating(Rating rating);
}
