package com.example.Encurtador.de.Url.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {

    String originalUrl;

    public UrlRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}
