package com.whisukiquest.whiskyquest_api.domain;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {

  //ユーザー詳細
  // ユーザーが登録している、ウイスキー情報と評価詳細を表示
  private Users users;
  private List<Whisky> whiskyList;
  private List<Rating> ratingList;

}
