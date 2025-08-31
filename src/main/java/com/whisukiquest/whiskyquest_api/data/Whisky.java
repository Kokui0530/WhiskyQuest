package com.whisukiquest.whiskyquest_api.data;


import com.whisukiquest.whiskyquest_api.validation.Deleted;
import com.whisukiquest.whiskyquest_api.validation.Update;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Whisky {

  @NotNull(message = "IDは必須です" , groups = Update.class)
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = Update.class)
  private int id;

  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private  int userId;

  @NotBlank
  private String name;

  private String taste;

  private String drinkingStyle;

  @Pattern(regexp = "^\\d+$")
  private int price;

  private String memo;


  private boolean isDeleted;

}
