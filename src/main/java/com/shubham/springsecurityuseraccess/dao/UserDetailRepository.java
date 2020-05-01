package com.shubham.springsecurityuseraccess.dao;

import com.shubham.springsecurityuseraccess.dto.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

  public UserDetail getUserByUserId(String userId);

}
