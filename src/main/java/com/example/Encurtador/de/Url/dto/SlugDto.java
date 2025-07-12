package com.example.Encurtador.de.Url.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlugDto {

    String url;

    public SlugDto(String url) {
        this.url = url;
    }

}
