package com.auctionshortenedurl.model.user;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad")
    private String soyad;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
