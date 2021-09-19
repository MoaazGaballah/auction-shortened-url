package com.auctionshortenedurl.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserInfo {
    private String email;
    private String password;
}
