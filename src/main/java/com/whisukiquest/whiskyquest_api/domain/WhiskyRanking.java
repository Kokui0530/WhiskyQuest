package com.whisukiquest.whiskyquest_api.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "ウイスキーランキング情報")
@Getter
@Setter
@EqualsAndHashCode
public class WhiskyRanking {

  @Schema(description = "ウイスキーID")
  @Valid
  private int whiskyId;

  @Schema(description = "ウイスキー名")
  @Valid
  private String name;

  @Schema(description = "平均評価")
  @Valid
  private double averageRating;

  @Schema(description = "評価件数")
  @Valid
  private int ratingCount;

}
