package com.example.Encurtador.de.Url.controller;

import com.example.Encurtador.de.Url.dto.ClickDto;
import com.example.Encurtador.de.Url.dto.SlugDto;
import com.example.Encurtador.de.Url.dto.UrlRequest;
import com.example.Encurtador.de.Url.model.UrlMapping;
import com.example.Encurtador.de.Url.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlRequest request, HttpServletRequest servletRequest) {
        try{
            String slug = urlService.shortenUrl(request.getOriginalUrl());
            String domain = servletRequest.getRequestURL()
                    .toString()
                    .replace(servletRequest.getRequestURI(), "");
            String shortenedUrl = domain + "/api/" + slug;
            return ResponseEntity.ok(new SlugDto(shortenedUrl));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> redirect(@PathVariable String slug) {
        Optional<UrlMapping> url = urlService.findUrlMapping(slug);
        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        url.get().setClicks();
        urlService.save(url.get());
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.get().getOriginalUrl())).build();
    }

    @GetMapping("/{slug}/clicks")
    public ResponseEntity<?> clicks(@PathVariable String slug) {
        Optional<UrlMapping> url = urlService.findUrlMapping(slug);
        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new ClickDto(url.get().getSlug(),url.get().getClicks()));
    }


}
