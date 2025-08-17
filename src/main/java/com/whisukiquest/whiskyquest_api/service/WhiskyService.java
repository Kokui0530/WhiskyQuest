package com.whisukiquest.whiskyquest_api.service;

import com.whisukiquest.whiskyquest_api.domain.WhiskyDetail;
import com.whisukiquest.whiskyquest_api.repository.WhiskyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhiskyService {

private final WhiskyRepository repository;

@Autowired
public WhiskyService(WhiskyRepository repository){
this.repository = repository;
}
public List<WhiskyDetail>searchWhisky(int userId){
return repository.searchWhisky(userId);
}


}
