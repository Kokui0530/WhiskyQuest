package com.whisukiquest.whiskyquest_api.domain;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class WhiskyInfo {

  //ウイスキーの詳細
  //1ウイスキー：1評価
  @Valid
  private Whisky whisky;

  @Valid
  private Rating rating;

}

