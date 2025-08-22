package com.whisukiquest.whiskyquest_api.domain;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class WhiskyInfo {

  //ウイスキーの詳細
  //1ウイスキー：1評価
  private Whisky whisky;
  private Rating rating;

}

