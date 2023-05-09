package com.onlineshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.onlineshop.entity.UserInfo;
import com.onlineshop.repository.UserInfoRepository;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserInfoRepository infoRepository;

	@Mock
    private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	private UserInfo userInfo ;

	  @BeforeEach
	    public void setup(){
		  when(passwordEncoder.encode(any())).thenReturn("Fadatare");
	    	userInfo = new UserInfo("dufhd12", "Ramesh", "ramesh@gmail.com","Fadatare","Role_Admin");	
	    }
	  
	  @Test
	    public void testAddUser() {
		  given(infoRepository.save(userInfo)).willReturn(userInfo);
	  
	        String result = userServiceImpl.addUser(userInfo);
			
			assertThat(result).isNotNull();
			
	        verify(passwordEncoder).encode(userInfo.getPassword());
            verify(infoRepository).save(userInfo);
	        assertEquals("User added successfully", result);
	    }

}
