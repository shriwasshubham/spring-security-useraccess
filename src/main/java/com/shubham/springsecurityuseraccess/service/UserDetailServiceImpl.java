package com.shubham.springsecurityuseraccess.service;

import com.shubham.springsecurityuseraccess.Exception.InvaliIdException;
import com.shubham.springsecurityuseraccess.dao.UserDetailRepository;
import com.shubham.springsecurityuseraccess.dto.UserDetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailService {

  @Autowired
  private UserDetailRepository userDetailRepository;

  @Override
  @Transactional
  public List<UserDetail> getAllUser() {
    return userDetailRepository.findAll();
  }

  @Override
  public Long saveUser(UserDetail userDetail) {
    return userDetailRepository.save(userDetail).getUserId();
  }

  @Override
  public UserDetail getSingleUser(Long userId) {
    return userDetailRepository.findById(userId)
        .orElseThrow(() -> new InvaliIdException("Supplied id : " + userId + " is invalid."));
  }
}
