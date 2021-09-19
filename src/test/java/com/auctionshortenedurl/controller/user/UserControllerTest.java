package com.auctionshortenedurl.controller.user;

import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.model.user.UserInfo;
import com.auctionshortenedurl.repository.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;

    // bu nesneleri test amaçlıdır
    User user1 = User.builder().ad("Moaaz").email("test8@test.com").password("1").soyad("Yildiz").build();
    User user2 = User.builder().ad("Ayse").email("test9@test.com").password("1").soyad("Sari").build();
    User user3 = User.builder().ad("Ahmet").email("test10@test.com").password("1").soyad("Ak").build();

//    @Test
    public void login_success() {
        try {
            List<User> records = new ArrayList<>(Arrays.asList(user1, user2, user3));

            Mockito.when(userRepository.findAll()).thenReturn(records);


            UserInfo userInfo = UserInfo.builder()
                    .email("test@test.com")
                    .password("1")
                    .build();

            // bunu ekleyebilirsin
            // .contentType(MediaType.APPLICATION_JSON)
            this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/user/login")
                            .content(this.mapper.writeValueAsString(userInfo)))
                    .andExpect(status().isOk());
            System.out.println("login_success succeed");
        } catch (Exception e) {
            System.out.println("Error at login_success");
        }

    }
}
