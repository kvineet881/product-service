package com.onlineshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.entity.UserInfo;
import com.onlineshop.service.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Tag(name = "User Registration", description = "Online Shopping APIs")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl service;
	
	@Operation(summary = "Register User in DB", description = "Save a User object by specifying User details. The response is User in string format.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = UserInfo.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	
	@ResponseStatus(value = HttpStatus.CREATED)
	 @PostMapping("/create")
	    public String addNewUser(@RequestBody UserInfo userInfo){
		logger.info("user controller is calling userservice");
	        return service.addUser(userInfo);
	    }
	
	
}
