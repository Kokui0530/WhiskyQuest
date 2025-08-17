package com.whisukiquest.whiskyquest_api.domain;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiskyDetail {

  private Users users;
  private List<Whisky> whiskyList;
  private List<Rating> ratingList;

}
