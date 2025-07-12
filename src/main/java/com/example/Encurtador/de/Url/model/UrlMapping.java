package com.example.Encurtador.de.Url.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "url")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {

    @Id
    @GeneratedValue
    private UUID id;
    private String originalUrl;
    @Column(unique = true)
    private String slug;
    private LocalDateTime createdAt;
    private int clicks = 0;

    public UrlMapping(String originalUrl, String slug, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.slug = slug;
        this.createdAt = createdAt;
    }

    public void setClicks() {
        this.clicks++;
    }


}
