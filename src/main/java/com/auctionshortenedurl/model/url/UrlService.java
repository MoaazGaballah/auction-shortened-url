package com.auctionshortenedurl.model.url;

import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.model.user.UserInfo;
import com.auctionshortenedurl.repository.url.UrlRepository;
import com.auctionshortenedurl.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    private static final int NUM_CHARS_SHORT_LINK = 7;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private Random random = new Random();

    @Transactional
    public void kaydet(Url url) {

        User user = userRepository.getById(url.getUser().getUserId());
        url.setUser(user);

        urlRepository.save(url);
    }

    public Url shortenUrl(Url url) {

        char[] result = new char[NUM_CHARS_SHORT_LINK];
        while (true) {
            for (int i = 0; i < NUM_CHARS_SHORT_LINK; i++) {
                int randomIndex = random.nextInt(ALPHABET.length() - 1);
                result[i] = ALPHABET.charAt(randomIndex);
            }
            String shortLink = new String(result);

            List<String> allShortenURLs = new ArrayList<>();
            urlRepository.findAll().forEach(url1 -> {

                // save all shortURLs exits into allShortenURLs list
                allShortenURLs.add(url1.getShortUrl());
            });

            // make sure the short link isn't already used
            if (!allShortenURLs.contains(shortLink)) {
                url.setShortUrl(shortLink);
                return url;
            }
        }
    }

    public String openBrowser(String strUrl) {
        String status = "Cihaz??n??z sa??layamd??????ndan, URL y??nlendirme i??leminiz ba??ar??s??zd??r! \\n\\nL??tfen farkl?? cihazdan deneyiniz...";
        try {

            Url url = urlRepository.findByShortUrl(strUrl);

            if (url == null) return "G??ndermi?? oldu??unuz url yi tekrar kontrol ediniz";
            // ????letim sistemi kontrol etme
            String os = this.whichOperatingSystem();

            // d??nece??imiz durum
            // ilk ba??ta cihaz desteklemiyor olacak
            // alltakilerden herhangi bir cihaza sahipse, o durum de??i??mi?? olacak

            if (os.indexOf("win") >= 0)
                status = this.openBrowserInWindows(url);
            else if (os.indexOf("mac") >= 0)
                status = this.openBrowserInMac(url);
            else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0)
                status = this.openBrowserInLinux(url);
            return status;
        } catch (Exception e) {
            return "G??ndermi?? oldu??unuz url yi tekrar kontrol ediniz";
        }
    }

    private String whichOperatingSystem() {
        return System.getProperty("os.name").toLowerCase();
    }

    private String convertExceptionIntoString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        return sStackTrace;
    }

    private String openBrowserInWindows(Url url) {
        try {

            String longUrl = url.getLongUrl();
            Runtime rt = Runtime.getRuntime();
            rt.exec("rundll32 url.dll,FileProtocolHandler " + longUrl);
            return "URL y??nlendirme i??leminiz ba??ar??l??d??r, " + longUrl + " 'ye Y??nlendiriliyorsunuz!";

        } catch (IOException e) {
            return convertExceptionIntoString(e);
        }
    }

    private String openBrowserInMac(Url url) {
        try {

            String longUrl = url.getLongUrl();
            Runtime rt = Runtime.getRuntime();
            rt.exec("open " + longUrl);
            return "URL y??nlendirme i??leminiz ba??ar??l??d??r, " + longUrl + " 'ye Y??nlendiriliyorsunuz!";

        } catch (Exception e) {
            return convertExceptionIntoString(e);
        }
    }

    private String openBrowserInLinux(Url url) {
        try {

            String longUrl = url.getLongUrl();
            Runtime rt = Runtime.getRuntime();
            String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                    "netscape", "opera", "links", "lynx"};

            StringBuffer cmd = new StringBuffer();
            for (int i = 0; i < browsers.length; i++)
                if (i == 0)
                    cmd.append(String.format("%s \"%s\"", browsers[i], longUrl));
                else
                    cmd.append(String.format(" || %s \"%s\"", browsers[i], longUrl));
            // If the first didn't work, try the next browser and so on

            rt.exec(new String[]{"sh", "-c", cmd.toString()});

            return "URL y??nlendirme i??leminiz ba??ar??l??d??r, " + longUrl + " 'ye Y??nlendiriliyorsunuz!";
        } catch (Exception e) {
            return convertExceptionIntoString(e);
        }
    }
}
