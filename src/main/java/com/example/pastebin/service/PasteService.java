package com.example.pastebin.service;

import com.example.pastebin.entity.Paste;
import com.example.pastebin.exception.PasteExpiredException;
import com.example.pastebin.exception.PasteNotFoundException;
import com.example.pastebin.exception.ViewLimitExceededException;
import com.example.pastebin.repository.PasteRepo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PasteService {

    private final PasteRepo repository;

    public PasteService(PasteRepo repository) {
        this.repository = repository;
    }

    public Paste create(String content, Integer ttlSeconds, Integer maxViews) {

        Paste paste = new Paste();
        paste.setContent(content);
        paste.setCreatedAt(Instant.now());

        if (maxViews != null) {
            paste.setMaxViews(maxViews);
            paste.setRemainingViews(maxViews);
        }

        if (ttlSeconds != null) {
            paste.setExpiresAt(Instant.now().plusSeconds(ttlSeconds));
        }

        return repository.save(paste);
    }

    @Transactional
    public Paste fetch(String id) {

        Paste paste = repository.findById(id)
                .orElseThrow(() -> new PasteNotFoundException("Paste not found"));

        // Expiry check
        if (paste.getExpiresAt() != null &&
                paste.getExpiresAt().isBefore(Instant.now())) {
            throw new PasteExpiredException("Paste expired");
        }

        Integer rv = paste.getRemainingViews();
        if (rv != null) {
            if (rv <= 0) {
                throw new ViewLimitExceededException("View limit exceeded");
            }
            paste.setRemainingViews(rv - 1);
            repository.save(paste);
        }
        return paste;
    }



    // TEST_MODE time handling
    public Instant resolveNow(HttpServletRequest request) {
        if ("1".equals(System.getenv("TEST_MODE"))) {
            String header = request.getHeader("x-test-now-ms");
            if (header != null) {
                return Instant.ofEpochMilli(Long.parseLong(header));
            }
        }
        return Instant.now();
    }
}
