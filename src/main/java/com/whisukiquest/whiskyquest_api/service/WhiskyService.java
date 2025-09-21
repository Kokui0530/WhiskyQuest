package com.whisukiquest.whiskyquest_api.service;

import com.whisukiquest.whiskyquest_api.controller.converter.WhiskyConverter;
import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.RatingAverage;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyInfo;
import com.whisukiquest.whiskyquest_api.domain.WhiskyRanking;
import com.whisukiquest.whiskyquest_api.repository.WhiskyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/****
 *ユーザー情報とウイスキー情報を取り扱うサービスです。検索、更新、登録処理を行います。
 */

@Service
public class WhiskyService {

  private final WhiskyRepository repository;
  private final WhiskyConverter converter;

  @Autowired
  public WhiskyService(WhiskyRepository repository, WhiskyConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /****
   *ユーザー詳細検索です。ユーザー情報、ユーザーIDに紐づくウイスキー情報、評価情報を取得します。
   * @param userId ユーザーID
   * @return ユーザー詳細
   */
  @Transactional
  public UserDetail searchUserDetail(int userId) {
    List<Whisky> whiskyList = repository.searchWhiskyList(userId);
    List<Rating> ratingList = repository.searchRatingList(userId);
    Users users = repository.searchUserById(userId);
    UserDetail userDetail = converter.converterUserDetail(users, whiskyList, ratingList);

    return userDetail;
  }

  /**
   * ウイスキーランキング検索です。 ウイスキー全件それぞれに紐づく評価情報を取得します。
   *
   * @return ウイスキー情報、評価情報
   */
  @Transactional
  public List<WhiskyRanking> searchWhiskyRanking() {
    List<Whisky> whiskyList = repository.searchWhisky();
    List<RatingAverage> ratingAverageList = repository.searchAverageRating();
    List<WhiskyRanking> whiskyRankingList =
        converter.converterWhiskyRanking(whiskyList, ratingAverageList);

    return whiskyRankingList;
  }

  /***
   * ウイスキー詳細の一覧検索です。ウイスキーIDに紐づく評価一覧を取得します。
   * @param whiskyId ウイスキーID
   * @return ウイスキー詳細一覧
   */
  @Transactional
  public WhiskyDetail searchWhiskyDetail(int whiskyId) {
    Whisky whisky = repository.searchWhiskyById(whiskyId);
    List<Rating> ratingList = repository.searchRatingByWhiskyId(whiskyId);
    WhiskyDetail whiskyDetail = converter.converterWhiskyDetail(whisky, ratingList);
    return whiskyDetail;
  }

  /***
   * ウイスキー名でウイスキー情報を検索し、重複がないかチェックします。
   * @param name ウイスキー名
   * @return ウイスキー情報
   */
  public Optional<Whisky> checkByWhiskyName(String name){
    return repository.searchWhiskyByName(name);
  }

  /****
   * ユーザー情報の登録を行います。
   * @param users ユーザー情報
   * @return ユーザー情報
   */
  @Transactional
  public Users registerUsers(Users users) {
    repository.registerUser(users);
    return users;
  }

  /***
   * ウイスキー情報と評価情報の新規登録を行います。whiskyIDは自動採番の為、Whiskyを登録 → whiskyIDが自動採番
   * 自動採番されたウイスキーIDを評価情報のウイスキーIDに設定します。
   * @param whiskyInfo ウイスキー詳細
   * @return ウイスキー詳細
   */
  @Transactional
  public WhiskyInfo registerWhiskyInfo(WhiskyInfo whiskyInfo) {
    repository.registerWhisky(whiskyInfo.getWhisky());
    int whiskyId = whiskyInfo.getWhisky().getId();
    whiskyInfo.getRating().setWhiskyId(whiskyId);
    repository.registerRating(whiskyInfo.getRating());
    return whiskyInfo;
  }

  /****
   * ユーザー情報の更新を行います。
   * @param users ユーザー情報
   * @return ユーザー情報
   */
  public Users updateUser(Users users) {
    repository.updateUser(users);
    return repository.searchUserById(users.getId());
  }

  /****
   * ウイスキー情報と評価情報の更新を行います。
   * @param whiskyInfo ウイスキー情報
   * @return ウイスキー情報
   */

  @Transactional
  public WhiskyInfo updateWhiskyInfo(WhiskyInfo whiskyInfo) {
    repository.updateWhisky(whiskyInfo.getWhisky());
    repository.updateRating(whiskyInfo.getRating());
    Whisky whisky = repository.searchWhiskyById(whiskyInfo.getWhisky().getId());
    Rating rating = repository.searchRatingById(whiskyInfo.getRating().getId());

    WhiskyInfo updateInfo = new WhiskyInfo();
    updateInfo.setWhisky(whisky);
    updateInfo.setRating(rating);
    return updateInfo;
  }

  /**
   * ユーザー情報の論理削除を行います。
   * @param id ユーザーID
   */
  public void deleteUser(int id){
    Users user = new Users();
    user.setId(id);
    repository.deleteUser(user);
  }

  /**
   * ウイスキー情報の論理削除を行います。
   * @param userId ユーザーID
   * @param whiskyId ウイスキーID
   */
  public void deleteWhisky(int userId , int whiskyId){
    Whisky whisky = new Whisky();
    whisky.setUserId(userId);
    whisky.setId(whiskyId);
    repository.deleteWhisky(whisky);
  }

  /**
   * 評価情報の論理削除を行います。
   * @param userId ユーザーID
   * @param ratingId 評価ID
   */
  public void deleteRating(int userId , int ratingId){
    Rating rating = new Rating();
    rating.setUserId(userId);
    rating.setId(ratingId);
    repository.deleteRating(rating);
  }
}
