package com.shubham.springsecurityuseraccess.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "userdetail")
public class UserDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "user_address")
  private String userAddress;

  @Column(name = "user_phone")
  private Long phoneNumber;

  @Column(name = "is_deleted")
  private boolean deleted;

  public UserDetail() {
    super();
  }

  public UserDetail(Long userId, String userAddress, Long phoneNumber, boolean deleted) {
    super();
    this.userId = userId;
    this.userAddress = userAddress;
    this.phoneNumber = phoneNumber;
    this.deleted = deleted;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}
