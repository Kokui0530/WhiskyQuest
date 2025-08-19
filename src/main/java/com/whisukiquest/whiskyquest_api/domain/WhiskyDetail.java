package com.whisukiquest.whiskyquest_api.domain;


import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiskyDetail {

  //1ウイスキー情報：多評価
  //ウイスキー詳細と評価を表示
  private Whisky whisky;
  private List<Rating> rating;

}
