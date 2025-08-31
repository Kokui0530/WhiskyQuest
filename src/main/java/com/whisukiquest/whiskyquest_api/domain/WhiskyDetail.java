package com.whisukiquest.whiskyquest_api.domain;


import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import jakarta.validation.Valid;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class WhiskyDetail {

  //1ウイスキー情報：多評価
  //ウイスキー詳細と評価を表示

  @Valid
  private Whisky whisky;

  @Valid
  private List<Rating> ratingList;

}
