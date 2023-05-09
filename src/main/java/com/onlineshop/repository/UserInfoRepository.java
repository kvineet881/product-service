package com.onlineshop.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.entity.Product;
import com.onlineshop.entity.UserInfo;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {

	Optional<UserInfo> findByName(String username);
}
