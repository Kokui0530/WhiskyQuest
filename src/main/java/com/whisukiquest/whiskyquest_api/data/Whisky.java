package com.whisukiquest.whiskyquest_api.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Whisky {

  private int id;
  private  int userId;
  private String name;
  private String taste;
  private String drinkingStyle;
  private int price;
  private String memo;
  private boolean isDeleted;

}
