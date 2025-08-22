package com.whisukiquest.whiskyquest_api.data;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Rating {

  private int id;
  private int userId;
  private int whiskyId;
  private int rating;
  private LocalDateTime ratingAt;

}
