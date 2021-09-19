package com.auctionshortenedurl.repository.url;

import com.auctionshortenedurl.model.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByShortUrl(String shortUrl);
}
