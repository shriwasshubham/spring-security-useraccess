package com.shubham.springsecurityuseraccess.dao;


import com.shubham.springsecurityuseraccess.dto.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

  public UserLogin findByUsername(String username);
}

