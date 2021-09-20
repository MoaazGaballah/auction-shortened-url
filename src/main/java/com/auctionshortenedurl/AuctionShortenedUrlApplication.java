package com.auctionshortenedurl;

import com.auctionshortenedurl.controller.user.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

// tekrar yailacak *****************************************
@ComponentScan(value = {"com.auctionshortenedurl.controller", "com.auctionshortenedurl.model", "com.auctionshortenedurl.repository"}) // this added to cover "Consider defining a bean of type ..."
@SpringBootApplication
public class AuctionShortenedUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionShortenedUrlApplication.class, args);
    }

}
