package com.whisukiquest.whiskyquest_api.repository;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WhiskyRepository {

  //user検索 userId検索
  Users searchUserById(int id);

  //Whisky情報一覧検索
  List<Whisky> searchWhiskyList(int userId);

  //Whisky情報検索　whiskyIdで検索
  Whisky searchWhiskyById(int whiskyId);

  //Rating情報一覧検索
  List<Rating> searchRatingList(int userId);

  //Rating検索 whiskyIdで検索
  List<Rating> searchRatingByWhiskyId(int whiskyId);

  //Rating情報検索　1件
  Rating searchRatingById(int ratingId);

  //ユーザー情報の新規登録
  void registerUser(Users users);

  //ウイスキー情報の新規登録
  void registerWhisky(Whisky whisky);

  //評価情報の新規登録
  void registerRating(Rating rating);

  //ユーザー情報の更新
  void updateUser(Users users);

  //ウイスキー情報の更新
  void updateWhisky(Whisky whisky);
  //評価情報の更新
  void updateRating(Rating rating);

  //ユーザー情報の論理削除
  void deleteUser(Users users);

  //ウイスキー情報の論理削除
  void deleteWhisky(Whisky whisky);

  //評価情報の論理削除
  void deleteRating(Rating rating);
}
