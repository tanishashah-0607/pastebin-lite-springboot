package com.example.pastebin.controller;

import com.example.pastebin.entity.Paste;
import com.example.pastebin.service.PasteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PasteViewController {

    private final PasteService service;

    public PasteViewController(PasteService service) {
        this.service = service;
    }

    @GetMapping("/p/{id}")
    public String view(@PathVariable String id, Model model) {

        Paste paste = service.fetch(id);

        model.addAttribute("content", paste.getContent());
        model.addAttribute("remainingViews", paste.getRemainingViews());
        model.addAttribute("expiresAt", paste.getExpiresAt());

        return "view";
    }
}
