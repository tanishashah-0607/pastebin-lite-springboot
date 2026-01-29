package com.example.pastebin.controller;

import com.example.pastebin.entity.Paste;
import com.example.pastebin.service.PasteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PasteApiController {

    private final PasteService service;

    public PasteApiController(PasteService service) {
        this.service = service;
    }

    @GetMapping("/healthz")
    public Map<String, Boolean> health() {
        return Map.of("ok", true);
    }

    @PostMapping("/pastes")
    public Map<String, String> create(@RequestBody Map<String, Object> body) {

        String content = (String) body.get("content");
        if (content == null || content.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Integer ttl = body.get("ttl_seconds") == null ? null :
                ((Number) body.get("ttl_seconds")).intValue();

        Integer maxViews = body.get("max_views") == null ? null :
                ((Number) body.get("max_views")).intValue();

        Paste paste = service.create(content, ttl, maxViews);

        return Map.of(
                "id", paste.getId(),
                "url", "https://YOUR-DOMAIN/p/" + paste.getId()
        );
    }

    @GetMapping("/pastes/{id}")
    public Map<String, Object> getPaste(@PathVariable String id) {

        Paste paste = service.fetch(id);

        Map<String, Object> response = new HashMap<>();
        response.put("content", paste.getContent());
        response.put("remaining_views", paste.getRemainingViews());
        response.put("expires_at", paste.getExpiresAt());

        return response;
    }


}

