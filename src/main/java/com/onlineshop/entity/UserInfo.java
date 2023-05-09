package com.onlineshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "UserInfo Entity class")
@Document(collection = "UserInfo")
public class UserInfo {
	
	@Schema(description = "id in UserInfo collection ")
    @Id
    private String id;
	@Schema(description = "name of user  in UserInfo collection")
    private String name;
	@Schema(description = "email of user  in UserInfo collection")
    private String email;
	@Schema(description = "password of user  in UserInfo collection")
    private String password;
	@Schema(description = "roles of user  in UserInfo collection")
    private String roles;
    
	public UserInfo() {}
	public UserInfo(String id, String name, String email, String password, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
    
    
}
