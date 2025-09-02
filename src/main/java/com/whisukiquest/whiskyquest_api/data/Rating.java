package com.whisukiquest.whiskyquest_api.data;

import com.whisukiquest.whiskyquest_api.validation.Deleted;
import com.whisukiquest.whiskyquest_api.validation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "評価情報")
@Getter
@Setter
@EqualsAndHashCode
public class Rating {

  @Schema(description = "ID、自動採番", example = "1")
  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private Integer id;

  @Schema(description = "ユーザーID", example = "1")
  @NotNull(message = "ユーザーIDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private Integer userId;

  @Schema(description = "ウイスキーID", example = "1")
  @NotNull(message = "ウイスキーIDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private Integer whiskyId;

  @Schema(description = "評価", example = "1")
  @Min(value = 1 , message = "1～5で入力して下さい")
  @Max(value = 5, message = "1～5で入力して下さい")
  private int rating;

  @Schema(description = "評価登録日", example = "2025.9.1")
  private LocalDateTime ratingAt;

  @Schema(description = "削除フラグ" , example = "false")
  private Boolean isDeleted;
}
