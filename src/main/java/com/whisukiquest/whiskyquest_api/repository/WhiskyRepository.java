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

  //Rating情報一覧検索
  List<Rating> searchRatingList(int userId);

  //Whisky情報検索　whiskyIdで検索
  Whisky searchWhisky(int whiskyId);

  //rating検索 whiskyIdで検索
  List<Rating> searchRatingByWhiskyId(int whiskyId);

}
