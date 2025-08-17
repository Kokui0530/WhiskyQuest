package com.whisukiquest.whiskyquest_api.repository;

import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WhiskyRepository {

  //userId検索してWhisky情報とrating情報が全件取れてくる
  List<WhiskyDetail> searchWhisky (int userId);


}
