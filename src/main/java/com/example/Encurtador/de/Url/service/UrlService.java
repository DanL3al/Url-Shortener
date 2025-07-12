package com.example.Encurtador.de.Url.service;

import com.example.Encurtador.de.Url.model.UrlMapping;
import com.example.Encurtador.de.Url.repository.UrlRepository;
import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    private final Slugify slugify = Slugify.builder().underscoreSeparator(true)
            .lowerCase(false).build();
    private final UrlCacheService urlCacheService;
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository, UrlCacheService urlCacheService) {
        this.urlRepository = urlRepository;
        this.urlCacheService = urlCacheService;
    }

    public String shortenUrl(String originalUrl) {
        if(!isValidUrl(originalUrl)) {
            throw new IllegalArgumentException("Invalid URL or Not Supported");
        }

        String cacheQuery = urlCacheService.getSlugFromCache(originalUrl);

        if(cacheQuery != null) {
            return cacheQuery;
        }

        Optional<UrlMapping> urlQuery = urlRepository.findByOriginalUrl(originalUrl);
        if(urlQuery.isPresent()) {
            urlCacheService.putSlugToCache(originalUrl, urlQuery.get().getSlug());
            return urlQuery.get().getSlug();
        }

        UrlMapping url = new UrlMapping(originalUrl, generateSlug(), LocalDateTime.now());
        urlRepository.save(url);
        urlCacheService.putSlugToCache(originalUrl, url.getSlug());

        return url.getSlug();
    }


    public String findOriginalUrl(String slug){
        Optional<UrlMapping> urlQuery = urlRepository.findBySlug(slug);
        if(urlQuery.isPresent()) {
            urlCacheService.putSlugToCache(urlQuery.get().getOriginalUrl(), slug);
            return urlQuery.get().getOriginalUrl();
        }
        return null;
    }

    public Optional<UrlMapping> findUrlMapping(String slug){
        return urlRepository.findBySlug(slug);
    }

    public Optional<UrlMapping> findUrlMappingByOriginalUrl(String originalUrl){
        return urlRepository.findByOriginalUrl(originalUrl);
    }

    public void save(UrlMapping urlMapping) {
        urlRepository.save(urlMapping);
    }

    private boolean isValidUrl(String originalUrl) {
        try{
            URL parsedUrl = new URL(originalUrl);
            String protocol = parsedUrl.getProtocol();
            return protocol.equals("http") || protocol.equals("https");
        }catch (MalformedURLException e){
            return false;
        }
    }

    private String generateSlug() {
        String base = UUID.randomUUID().toString().substring(0, 6);
        return slugify.slugify(base);
    }

}
