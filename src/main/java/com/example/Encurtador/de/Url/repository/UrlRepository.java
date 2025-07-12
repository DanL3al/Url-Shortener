package com.example.Encurtador.de.Url.repository;

import com.example.Encurtador.de.Url.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, UUID> {

    Optional<UrlMapping> findBySlug(String slug);
    Optional<UrlMapping> findByOriginalUrl(String originalUrl);
}
