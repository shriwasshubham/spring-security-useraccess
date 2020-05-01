package com.shubham.springsecurityuseraccess.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userlogin")
@Getter
@Setter
public class UserLogin implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "user_name", unique = true)
  private String username;

  @Column(name = "user_password")
  private String password;

  @Column(name = "user_role")
  private String role;

}

