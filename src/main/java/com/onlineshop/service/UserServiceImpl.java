package com.onlineshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineshop.entity.UserInfo;
import com.onlineshop.repository.UserInfoRepository;

@Service
public class UserServiceImpl {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoRepository repository;
	
	  public String addUser(UserInfo userInfo) {
			logger.info("user service is calling addUser() method");
	        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	        repository.save(userInfo);
	        logger.info("user service is calling addUser() method done");
	        return "User added successfully";
	    }

}
