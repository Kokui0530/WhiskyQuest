package com.whisukiquest.whiskyquest_api.data;

import com.whisukiquest.whiskyquest_api.validation.Deleted;
import com.whisukiquest.whiskyquest_api.validation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "ユーザー情報")
@Getter
@Setter
@EqualsAndHashCode
public class Users {

  @Schema(description = "ID、自動採番", example = "1")
  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private Integer id;

  @Schema(description = "名前", example = "田中　太郎")
  @NotBlank
  private String userName;

  @Schema(description = "メールアドレス", example = "taro@gmail.com")
  @NotBlank
  @Email
  private String mail;

  @Schema(description = "パスワード", example = "pass123")
  @NotBlank
  private String password;

  @Schema(description = "削除フラグ", example = "退会")
  private boolean isDeleted;

}
