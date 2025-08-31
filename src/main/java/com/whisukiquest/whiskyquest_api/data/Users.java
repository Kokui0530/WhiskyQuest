package com.whisukiquest.whiskyquest_api.data;

import com.whisukiquest.whiskyquest_api.validation.Deleted;
import com.whisukiquest.whiskyquest_api.validation.Update;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Users {

  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private int id;

  @NotBlank
  private String userName;

  @NotBlank
  @Email
  private String mail;

  @NotBlank
  private String password;


  private boolean isDeleted;

}
