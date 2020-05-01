package com.shubham.springsecurityuseraccess.custom.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFailureResponse implements Serializable {

	private static final long serialVersionUID = 838456177496021672L;

	private String message;

}
