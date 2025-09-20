package com.whisukiquest.whiskyquest_api.data;


import com.whisukiquest.whiskyquest_api.validation.Deleted;
import com.whisukiquest.whiskyquest_api.validation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "ウイスキー情報")
@Getter
@Setter
@EqualsAndHashCode
public class Whisky {

  @Schema(description = "ID、自動採番", example = "1")
  @NotNull(message = "IDは必須です" , groups = Update.class)
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = Update.class)
  private Integer id;

  @Schema(description = "ユーザーID", example = "1")
  @NotNull(message = "ユーザーIDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private Integer userId;

  @Schema(description = "商品名", example = "アードベック")
  @NotBlank
  private String name;

  @Schema(description = "味、風味", example = "スモーキー")
  private String taste;

  @Schema(description = "飲み方", example = "ロック")
  private String drinkingStyle;

  @Schema(description = "値段", example = "12000")
  private int price;

  @Schema(description = "削除フラグ" , example = "false")
  private Boolean isDeleted;

}
