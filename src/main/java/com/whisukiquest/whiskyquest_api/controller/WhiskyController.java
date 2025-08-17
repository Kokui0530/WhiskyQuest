package com.whisukiquest.whiskyquest_api.controller;

import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.service.WhiskyService;
import java.util.List;
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

@GetMapping("/whisky/{userId}")
  public List<WhiskyDetail> getWhiskyList(
      @PathVariable  int userId){
   return service.searchWhisky(userId);
  }



}
