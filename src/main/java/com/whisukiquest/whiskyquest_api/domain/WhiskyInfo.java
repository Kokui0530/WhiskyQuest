package com.whisukiquest.whiskyquest_api.domain;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "ウイスキー単一の詳細情報　1ウイスキー：1評価")
@Getter
@Setter
@EqualsAndHashCode
public class WhiskyInfo {

  @Schema(description = "ウイスキー情報")
  @Valid
  private Whisky whisky;

  @Schema(description = "評価情報")
  @Valid
  private Rating rating;

}

