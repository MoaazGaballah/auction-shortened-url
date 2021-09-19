package com.auctionshortenedurl.controller.url;

import com.auctionshortenedurl.model.url.Url;
import com.auctionshortenedurl.model.url.UrlService;
import com.auctionshortenedurl.repository.url.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlRepository urlRepository;
    private final UrlService urlService;

    @PostMapping("/shortenurl")
    public ResponseEntity<Object> shortenUrl(@RequestBody Url url){
        try {

            // this save called here to save the url into DB first to
            // generate a new ID by @GeneratedValue annotation
            urlRepository.save(url);

            url = urlService.shortenUrl(url);

            urlRepository.save(url);
            return new ResponseEntity<>( "URLınız başarılıyla kısaltılmıştır.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("URL kısaltma işleminiz başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
