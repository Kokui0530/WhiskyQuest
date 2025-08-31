package com.whisukiquest.whiskyquest_api.data;

import com.whisukiquest.whiskyquest_api.validation.Deleted;
import com.whisukiquest.whiskyquest_api.validation.Update;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Rating {

  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private int id;

  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private Integer userId;


  @NotNull(message = "IDは必須です" , groups = { Update.class , Deleted.class })
  @Min(value = 1 , message ="IDは数値で１以上です" , groups = { Update.class , Deleted.class })
  private int whiskyId;

  @NotBlank
  @Min(value = 1 , message = "1～5で入力して下さい")
  @Max(value = 5, message = "1～5で入力して下さい")
  private int rating;


  private LocalDateTime ratingAt;


  private boolean isDeleted;
}
