package com.whisukiquest.whiskyquest_api.controller;

import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhiskyController {

private final WhiskyService service;

@Autowired
public WhiskyController(WhiskyService service){
  this.service = service;
}

//ユーザー詳細、登録したウイスキー詳細、評価詳細が取れてくる
@GetMapping("/user/{userId}")
  public UserDetail getUserList(
      @PathVariable  int userId){
   return service.searchUserDetail(userId);
  }

@GetMapping("/whisky/{whiskyId}")
 public WhiskyDetail getWhiskyList(
  @PathVariable int whiskyId){
  return service.searchWhiskyDetail(whiskyId);
  }

}

