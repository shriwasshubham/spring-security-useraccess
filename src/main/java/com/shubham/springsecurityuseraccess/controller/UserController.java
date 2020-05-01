package com.shubham.springsecurityuseraccess.controller;


import com.shubham.springsecurityuseraccess.dto.ResponseBuilder;
import com.shubham.springsecurityuseraccess.dto.UserDetail;
import com.shubham.springsecurityuseraccess.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserDetailService userService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(value = "/fetch-users", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<?> fetchAllUser() {
    try {
      // This will return all users from the database

      return new ResponseEntity<>(new ResponseBuilder.Builder().status(1)
          .data(userService.getAllUser()).build(), HttpStatus.OK);
    } catch (Exception ex) {
      String errorMessage;
      errorMessage = ex + " <== error";
      return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> saveUser(@Valid @RequestBody UserDetail userDetail) {
    try {

      // This will save user data into db
      return new ResponseEntity<>(new ResponseBuilder.Builder().status(1).build(),
          HttpStatus.OK);
    } catch (Exception ex) {
      String errorMessage;
      errorMessage = ex + " <== error";
      return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<?> getSingleuserData(@PathVariable(value = "userId") @NotNull Long userId) {
    try {

      // This will return single data with the supplied id
      return new ResponseEntity<>(new ResponseBuilder.Builder().status(1)
                    .data(userService.getSingleUser(userId)).build(), HttpStatus.OK);
    } catch (Exception ex) {
      String errorMessage;
      errorMessage = ex + " <== error";
      return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

