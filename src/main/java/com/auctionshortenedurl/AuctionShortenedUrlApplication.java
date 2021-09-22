package com.auctionshortenedurl;

import com.auctionshortenedurl.controller.user.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

// tekrar yailacak *****************************************
// eğer tamamen kaldırırsan test çalışmaz (ama requestleri alır)
// eğer sadece repository koyarsan test çalışır ama diğer sınıfları kapattığı için request ler almaz
// birden fazla sınıflara taraması sağlarsan belki olur.

// test lerden vazgectiiim
@ComponentScan(value = {"com.auctionshortenedurl.repository"}) // this added to cover "Consider defining a bean of type ..."
@SpringBootApplication
public class AuctionShortenedUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionShortenedUrlApplication.class, args);
    }

}
