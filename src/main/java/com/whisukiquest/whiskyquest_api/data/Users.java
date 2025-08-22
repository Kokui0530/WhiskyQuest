package com.whisukiquest.whiskyquest_api.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Users {

  private int id;
  private String userName;
  private String mail;
  private String password;

}
