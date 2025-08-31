package com.whisukiquest.whiskyquest_api.controller;

import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyInfo;
import com.whisukiquest.whiskyquest_api.service.WhiskyService;
import com.whisukiquest.whiskyquest_api.validation.Update;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ウイスキーの記録、評価を管理するシステムです。"
**/

@Validated
@RestController
public class WhiskyController {

  private final WhiskyService service;

  @Autowired
  public WhiskyController(WhiskyService service) {
    this.service = service;
  }

  /**ウイスキー情報
   *
   * **/
  //ユーザー詳細、登録したウイスキー詳細、評価詳細が取れてくる
  @GetMapping("/user/{userId}")
  public UserDetail getUserList(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int userId) {
    return service.searchUserDetail(userId);
  }


  //ウイスキー詳細と、そのウイスキーに対しての評価一覧が取れてくる
  @GetMapping("/whisky/{whiskyId}")
  public WhiskyDetail getWhiskyList(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int whiskyId) {
    return service.searchWhiskyDetail(whiskyId);
  }

  //ユーザー情報新規登録
  @PostMapping("/registerUser")
  public ResponseEntity<Users> registerUser(@RequestBody Users users) {
    Users responseUser = service.registerUsers(users);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
  }

  //ウイスキー情報と評価情報の新規登録
  @PostMapping("/registerWhisky")
  public ResponseEntity<WhiskyInfo> registerWhiskyInfo(@RequestBody @Valid WhiskyInfo whiskyInfo) {
    WhiskyInfo responseWhiskyInfo = service.registerWhiskyInfo(whiskyInfo);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseWhiskyInfo);
  }

  //ユーザー情報の更新
@PutMapping("/updateUser/{userId}")
  public ResponseEntity<Users> updateUser(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int userId ,
    @Validated(Update.class) @RequestBody  Users users){
    Users responseUser = service.updateUser(users);
    return ResponseEntity.ok(responseUser);
}

//ウイスキー情報の更新
  @PutMapping("/updateWhiskyInfo/{WhiskyId}/{RatingId}")
  public ResponseEntity<WhiskyInfo> updateWhisky(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int WhiskyId,
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int RatingId,
      @RequestBody @Validated(Update.class) WhiskyInfo whiskyInfo){
    WhiskyInfo responseWhiskyInfo = service.updateWhiskyInfo(whiskyInfo);
    return ResponseEntity.ok(responseWhiskyInfo);
  }

  //ユーザー情報の論理削除
  @PutMapping("/deleteUser/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int userId) {
    service.deleteUser(userId);
    return ResponseEntity.ok().build();
  }

  //ウイスキー情報の論理削除
  @PutMapping("/deleteWhisky/{userId}")
  public ResponseEntity<Void> deleteWhisky(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int userId) {
    service.deleteWhisky(userId);
    return ResponseEntity.ok().build();
  }

  //評価情報の論理削除
  @PutMapping("/deleteRating/{userId}")
  public ResponseEntity<Void> deleteRating(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") int userId) {
    service.deleteRating(userId);
    return ResponseEntity.ok().build();
  }


}




