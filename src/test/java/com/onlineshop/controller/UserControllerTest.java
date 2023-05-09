package com.onlineshop.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.onlineshop.entity.UserInfo;
import com.onlineshop.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private UserServiceImpl userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
    public void testAddNewUser() throws Exception {
        // Arrange
        UserInfo userInfo = new UserInfo();
        userInfo.setId("123");
        userInfo.setName("John Doe");
        userInfo.setEmail("john@example.com");
        userInfo.setPassword("password");
        userInfo.setRoles("USER_ROLE");
        
        when(userService.addUser(any(UserInfo.class))).thenReturn("User added successfully.");
        
        // Act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"123\",\"name\":\"John Doe\",\"email\":\"john@example.com\",\"password\":\"password\",\"roles\":\"USER_ROLE\"}"))
                .andReturn();
        
        // Assert
        MockHttpServletResponse response = mvcResult.getResponse();
        verify(userService).addUser(any(UserInfo.class));
    }
}
