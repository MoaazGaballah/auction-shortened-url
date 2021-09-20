package com.auctionshortenedurl.model.user;

import com.auctionshortenedurl.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

//    public UserService (){
//
//    }
    @Transactional
    public void kaydet(User user){
        this.userRepository.save(user);
    }

    public User getUser(UserInfo userInfo){
        return this.userRepository.findByEmail(userInfo.getEmail());
    }
}
