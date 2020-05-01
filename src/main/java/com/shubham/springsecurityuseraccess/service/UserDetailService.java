package com.shubham.springsecurityuseraccess.service;

import com.shubham.springsecurityuseraccess.dto.UserDetail;
import java.util.List;
import org.springframework.stereotype.Service;


public interface UserDetailService {

  public List<UserDetail> getAllUser();

  public Long saveUser(UserDetail userDetail);

  public UserDetail getSingleUser(Long userId);

}
