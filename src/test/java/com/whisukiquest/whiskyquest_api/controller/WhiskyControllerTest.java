package com.whisukiquest.whiskyquest_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.whisukiquest.whiskyquest_api.data.Rating;
import com.whisukiquest.whiskyquest_api.data.Users;
import com.whisukiquest.whiskyquest_api.data.Whisky;
import com.whisukiquest.whiskyquest_api.domain.UserDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.domain.WhiskyInfo;
import com.whisukiquest.whiskyquest_api.service.WhiskyService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WhiskyController.class)
public class WhiskyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private WhiskyService service;

@Test  //("/user/{userId}")
  void ユーザー詳細が正しく検索できてサービスも正しく呼ばれているか() throws Exception {
  //事前準備
  int userId = 999;
    Users users = new Users();
    users.setId(userId);
    List<Whisky> whiskyList = List.of(new Whisky());
    List<Rating> ratingList = List.of(new Rating());

    UserDetail userDetail = new UserDetail();
    userDetail.setUsers(users);
    userDetail.setWhiskyList(whiskyList);
    userDetail.setRatingList(ratingList);

    //service.searchUserDetailが呼ばれたらuserDetailが返ってくるように設定
    //モックの振る舞いを定義
    Mockito.when(service.searchUserDetail(userId)).thenReturn(userDetail);

   //GET /user/999 にアクセスするのと同じ動作をシミュレート
    //mockMvc を使うことで、Webサーバーを立ち上げずにコントローラーの動作を確認できる。レスポンスの検証
    mockMvc.perform(get("/user/{userId}",userId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.users.id").value(userId))
        .andExpect(jsonPath("$.whiskyList").isArray())
        .andExpect(jsonPath("$.ratingList").isArray());

    //serviceの呼び出し検証
        verify(service,times(1)).searchUserDetail(userId);
  }

  @Test  //("/user/{userId}")
  void ウイスキー詳細が正しく検索できてサービスも正しく呼ばれているか() throws Exception {
    int whiskyId = 999;
    Whisky whisky = new Whisky();
    whisky.setId(whiskyId);
    List<Rating> ratingList = List.of(new Rating());

    WhiskyDetail whiskyDetail = new WhiskyDetail();
    whiskyDetail.setWhisky(whisky);
    whiskyDetail.setRatingList(ratingList);

    Mockito.when(service.searchWhiskyDetail(whiskyId)).thenReturn(whiskyDetail);

    mockMvc.perform(get("/whisky/{whiskyId}",whiskyId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.whisky.id").value(whiskyId))
        .andExpect(jsonPath("$.ratingList").isArray());

    verify(service,times(1)).searchWhiskyDetail(whiskyId);
  }

  @Test//@PostMapping("/registerUser")
  void ユーザーの登録が正しく出来てるか() throws Exception {
    Users users = new Users();
    users.setUserName("谷口　彩");

    Mockito.when(service.registerUsers(any(Users.class))).thenReturn(users);

    mockMvc.perform(post("/registerUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                      {
                          "userName": "谷口　彩",
                          "mail": "aya@example.com",
                          "password": "asd1"
                      }
                    """
            ))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.userName").value("谷口　彩"));
    verify(service, times(1)).registerUsers(any(Users.class));
  }

  @Test // @PostMapping("/registerWhisky")
  void ウイスキー情報と評価情報の新規登録が正常に行えているか() throws Exception {
    Whisky whisky = new Whisky();
    whisky.setName("メーカーズマーク");

    Rating rating = new Rating();
    rating.setUserId(2);
    //setするのは比較する対象だけでOK。全項目比較したければ、全部setする

    WhiskyInfo whiskyInfo = new WhiskyInfo();
    whiskyInfo.setWhisky(whisky);
    whiskyInfo.setRating(rating);

    Mockito.when(service.registerWhiskyInfo(any(WhiskyInfo.class))).thenReturn(whiskyInfo);
    //registerWhiskyInfoの引数(whiskyInfo)だと内容完全一致じゃないとダメ。
    // (any(WhiskyInfo.class))ならwhiskyInfoの型ならどんなインスタンスでもOK

    mockMvc.perform(post("/registerWhisky")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                    {
                        "whisky": {
                            "name": "メーカーズマーク",
                            "taste": "甘い",
                            "drinkingStyle": "ハイボール",
                            "price": 3000,
                            "memo": "ボトルもかわいい",
                            "deleted": false
                        },
                        "rating": {
                            "userId": 2,
                            "rating": 5
                        }
                    }
                    """

            ))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.whisky.name").value("メーカーズマーク"))
        .andExpect(jsonPath("$.rating.userId").value(2));
    verify(service, times(1)).registerWhiskyInfo(any());
  }

  @Test //@PutMapping("/updateUser/{userId}")
  void ユーザー情報の更新ができる事() throws Exception {
    int userId = 1;
    Users users = new Users();
    users.setId(userId);
    users.setUserName("谷口　雪");

    Mockito.when(service.updateUser(any(Users.class))).thenReturn(users);

    mockMvc.perform(put("/updateUser/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                      {
                          "id": 1,
                          "userName": "谷口　雪",
                          "mail": "aya@example.com",
                          "password": "asd1"
                      }
                    """
            ))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userName").value("谷口　雪"));
    verify(service, times(1)).updateUser(any(Users.class));
  }

  @Test //@PutMapping("/updateWhiskyInfo/{WhiskyId}/{RatingId}")
  void ウイスキー情報と評価情報の更新() throws Exception {
    int whiskyId = 1;
    int ratingId = 1;

    Whisky whisky = new Whisky();
    whisky.setId(whiskyId);
    whisky.setName("山崎12年");

    Rating rating = new Rating();
    rating.setId(ratingId);
    rating.setRating(4);

    WhiskyInfo whiskyInfo = new WhiskyInfo();
    whiskyInfo.setWhisky(whisky);
    whiskyInfo.setRating(rating);
    Mockito.when(service.updateWhiskyInfo(any(WhiskyInfo.class))).thenReturn(whiskyInfo);

    mockMvc.perform(put("/updateWhiskyInfo/{WhiskyId}/{RatingId}", whiskyId, ratingId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                    {
                        "whisky": {
                            "id": 1,
                            "userId":1,
                            "name": "メーカーズマーク",
                            "taste": "甘い",
                            "drinkingStyle": "ハイボール",
                            "price": 3000,
                            "memo": "ボトルもかわいい",
                            "deleted": false
                        },
                        "rating": {
                            "id": 1,
                            "userId": 1,
                            "whiskyId" :1,
                            "rating": 5
                        }
                    }
                    """
            ))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.whisky.name").value("山崎12年"));
    verify(service, times(1)).updateWhiskyInfo(any(WhiskyInfo.class));

  }

  @Test //@PutMapping("/deleteUser/{userId}")
  void ユーザー情報の論理削除が出来ること() throws Exception {
    int userId = 999;

    mockMvc.perform(put("/deleteUser/{userId}", userId))
        .andExpect(status().isOk());

    verify(service, times(1)).deleteUser(userId);
  }

  @Test//@PutMapping("/deleteWhisky/{userId}{whiskyId}")
  void ウイスキー情報の論理削除ができること() throws Exception {
    int userId = 999;
    int whiskyId = 888;

    mockMvc.perform(put("/deleteWhisky/{userId}/{whiskyId}", userId , whiskyId))
        .andExpect(status().isOk());

    verify(service, times(1)).deleteWhisky(userId , whiskyId);

  }

  @Test// @PutMapping("/deleteRating/{userId}{ratingId}")
  void 評価情報の論理削除ができること() throws Exception {
    int userId = 999;
    int ratingId = 888;
    mockMvc.perform(put("/deleteRating/{userId}/{ratingId}", userId , ratingId))
        .andExpect(status().isOk());

    verify(service, times(1)).deleteRating(userId , ratingId);

  }

@Test
  void 存在しないIDで404が返ること() throws Exception{
  int id =987;

  Mockito.when(service.searchUserDetail(id)).thenReturn(null);

  mockMvc.perform(get("/users/{id}",id))
      .andExpect(status().isNotFound());

}

@Test
  void IDを指定せずにアクセスした時に404が返ること()throws Exception{
  mockMvc.perform(get("/users"))
      .andExpect(status().isNotFound());
}
}
