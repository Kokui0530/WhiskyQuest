package com.whisukiquest.whiskyquest_api.domain;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "ユーザーの登録詳細情報　1ユーザー：多ウイスキー情報：多評価")
@Getter
@Setter
@EqualsAndHashCode
public class UserDetail {

  @Schema(description = "ユーザー情報")
  @Valid
  private Users users;

  @Schema(description = "ウイスキー情報一覧")
  @Valid
  private List<Whisky> whiskyList;

  @Schema(description = "評価情報一覧")
  @Valid
  private List<Rating> ratingList;

}
