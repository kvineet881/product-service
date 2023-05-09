package com.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product API", version = "2.0", description = "Product Information"))
@SecurityScheme(name = "shopping-app", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OnlineShoppingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingAppApplication.class, args);
	}

}
