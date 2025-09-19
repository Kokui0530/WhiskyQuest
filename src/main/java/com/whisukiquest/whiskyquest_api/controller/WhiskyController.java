package com.whisukiquest.whiskyquest_api.controller;

import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyInfo;
import com.whisukiquest.whiskyquest_api.domain.WhiskyRanking;
import com.whisukiquest.whiskyquest_api.service.WhiskyService;
import com.whisukiquest.whiskyquest_api.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ウイスキーの検索や登録、更新などをREST APIとして実行されるcontrollerです。
**/
@CrossOrigin(origins = "http://localhost:3000")//異なるドメインからのリクエストを許可するアノテーション
@Validated
@RestController
public class WhiskyController {

  private final WhiskyService service;

  @Autowired
  public WhiskyController(WhiskyService service) {
    this.service = service;
  }


  /****
   * ユーザー情報検索です。ユーザーIDに紐づくウイスキー情報と評価情報の一覧を取得します。
   * @param userId ユーザーID
   * @return ユーザー詳細、登録しているウイスキー情報と評価の一覧
   */
  @Operation(summary = "ユーザー詳細の検索",
      description = "ユーザーIDをもとに、ユーザーが登録したウイスキー一覧、評価一覧の検索を行います。")
  @GetMapping("/user/{userId}")
  public UserDetail getUserList(
      @PathVariable  int userId) {
    return service.searchUserDetail(userId);
  }

  /**
   * ウイスキーランキング検索です。
   * 登録のあるウイスキー全件と、そのウイスキーに対する評価を降順で取得します。
   * @return ウイスキー情報全件、評価情報
   */
  @GetMapping("/whiskyRanking")
  public List<WhiskyRanking> whiskyRankings() {
    return service.searchWhiskyRanking();
  }

  /****
   * ウイスキー情報検索です。ウイスキーIDに紐づく評価一覧を取得します。
   * @param whiskyId ウイスキーID
   * @return ウイスキー詳細、評価詳細一覧
   */

@Operation(summary = "ウイスキー詳細と評価一覧検索",
description = "ウイスキーIDをもとに、ウイスキーの詳細、評価一覧の検索を行います。")
  @GetMapping("/whisky/{whiskyId}")
  public WhiskyDetail getWhiskyList(
      @PathVariable int whiskyId) {
    return service.searchWhiskyDetail(whiskyId);
  }

  /****
   * ユーザーの新規登録を行います。
   * @param users ユーザー詳細
   * @return 実行結果
   */

  @Operation(summary = "ユーザー新規登録",
  description = "ユーザー新規登録を行います。")
  @PostMapping("/registerUser")
  public ResponseEntity<Users> registerUser(@RequestBody Users users) {
    Users responseUser = service.registerUsers(users);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
  }

  /****
   * ウイスキー情報と評価情報の新規登録を行います。
   * @param whiskyInfo ウイスキー詳細
   * @return 実行結果
   */

  @Operation(summary = "ウイスキー詳細と、そのウイスキーに対しての評価の登録",
  description = "ウイスキー詳細と評価の登録を行います。")
  @PostMapping("/registerWhisky")
  public ResponseEntity<WhiskyInfo> registerWhiskyInfo(@RequestBody @Valid WhiskyInfo whiskyInfo) {
    WhiskyInfo responseWhiskyInfo = service.registerWhiskyInfo(whiskyInfo);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseWhiskyInfo);
  }

  /****
   *ユーザーIDに紐づく詳細の更新を行います。
   * @param userId ユーザーID
   * @param users ユーザー詳細
   * @return 実行結果
   */
  @Operation(summary = "ユーザー情報の更新",
description = "ユーザーIDをもとに、ユーザー情報の更新を行います。")
  @PutMapping("/updateUser/{userId}")
  public ResponseEntity<Users> updateUser(@PathVariable int userId ,
    @Validated(Update.class) @RequestBody  Users users){
    Users responseUser = service.updateUser(users);
    return ResponseEntity.ok(responseUser);
}

  /****
   * ウイスキーIDと、評価IDに紐づくウイスキー詳細の更新を行います。
   * @param WhiskyId ウイスキーID
   * @param RatingId 評価ID
   * @param whiskyInfo ウイスキー詳細
   * @return ウイスキー詳細
   */
  @Operation(summary = "ウイスキー情報の更新",
    description = "ウイスキーIDと評価IDをもとに、ウイスキー情報と評価情報の更新を行います。")
  @PutMapping("/updateWhiskyInfo/{WhiskyId}/{RatingId}")
  public ResponseEntity<WhiskyInfo> updateWhisky(
      @PathVariable int WhiskyId,
      @PathVariable int RatingId,
      @RequestBody @Validated(Update.class) WhiskyInfo whiskyInfo){
    WhiskyInfo responseWhiskyInfo = service.updateWhiskyInfo(whiskyInfo);
    return ResponseEntity.ok(responseWhiskyInfo);
  }

  /****
   * ユーザーIDに紐づくユーザー情報の論理削除を行います。
   * @param userId ユーザーID
   * @return 実行結果
   */
  @Operation(summary = "ユーザー情報の論理削除",
  description = "ユーザーIDをもとに、ユーザー情報の論理削除を行います。")
  @PutMapping("/deleteUser/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
    service.deleteUser(userId);
    return ResponseEntity.ok().build();
  }

  /****
   * ユーザーIDに紐づくウイスキー情報の論理削除を行います。
   * @param userId ユーザーID
   * @param whiskyId ウイスキーID
   * @return 実行結果
   */
  @Operation(summary = "ウイスキー情報の論理削除",
      description = "ユーザーIDとウイスキーIDをもとに、ユーザーが登録したウイスキー情報の論理削除を行います。")
  @PutMapping("/deleteWhisky/{userId}/{whiskyId}")
  public ResponseEntity<Void> deleteWhisky(@PathVariable int userId , @PathVariable int whiskyId) {
    service.deleteWhisky(userId , whiskyId);
    return ResponseEntity.ok().build();
  }

  /****
   * ユーザーIDに紐づく評価情報の論理削除を行います。
   * @param userId ユーザーID
   * @param ratingId 評価ID
   * @return 実行結果
   */
  @Operation(summary = "評価情報の論理削除",
      description = "ユーザーIDと評価IDをもとに、ユーザーが登録した評価情報の論理削除を行います。")
  @PutMapping("/deleteRating/{userId}/{ratingId}")
  public ResponseEntity<Void> deleteRating(@PathVariable int userId , @PathVariable int ratingId) {
    service.deleteRating(userId , ratingId);
    return ResponseEntity.ok().build();
  }


}




