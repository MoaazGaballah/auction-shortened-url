package com.auctionshortenedurl.model.url;

import com.auctionshortenedurl.repository.url.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    private static final int NUM_CHARS_SHORT_LINK = 7;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private Random random = new Random();

    public Url shortenUrl(Url url) {

        char[] result = new char[NUM_CHARS_SHORT_LINK];
        while (true) {
            for (int i = 0; i < NUM_CHARS_SHORT_LINK; i++) {
                int randomIndex = random.nextInt(ALPHABET.length() - 1);
                result[i] = ALPHABET.charAt(randomIndex);
            }
            String shortLink = new String(result);

            // make sure the short link isn't already used
//            url = urlRepository.getById(url.getUrlId());


            // Bu kod tekrar yazilacak
            boolean exist = false;
            urlRepository.findAll().forEach(url1 -> {
//                if(url1.getShortUrl().equals(shortLink)){
//                    exist = true;
//                    break;
//                }
            });


//            if (!urlRepository.findAll().contains(shortLink)){
//                url.setShortUrl(shortLink);
//                return url;
//            }
        }
    }
}
