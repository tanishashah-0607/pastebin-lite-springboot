package com.example.pastebin.repository;

import com.example.pastebin.entity.Paste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasteRepo extends JpaRepository<Paste, String> {
}
