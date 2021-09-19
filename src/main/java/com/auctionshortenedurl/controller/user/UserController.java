package com.auctionshortenedurl.controller.user;

import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.model.user.UserInfo;
import com.auctionshortenedurl.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup (@RequestBody User usr){
        try{

            userRepository.save(usr);
            return new ResponseEntity<>("Üye kaydınız başarılıyla alınmıştır.", HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>("Üye kaydınız başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/login")
    public ResponseEntity<Object> login (@RequestBody UserInfo userInfo){
        try {
            User user = userRepository.findByEmail(userInfo.getEmail());

            if (user.getPassword().equals(userInfo.getPassword()))
                return new ResponseEntity<>("Giriş Başarıyla yapıldı!", HttpStatus.OK);

            return new ResponseEntity<>("Hatalı email yada şifre, Lütfen Tekrar Kontrol ediniz.", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Lütfen Parametrelerinizi Doğrulayın!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
