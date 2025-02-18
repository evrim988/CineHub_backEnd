package org.example.cinehub_backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.request.FavoriteRequestDto;
import org.example.cinehub_backend.dto.response.FavoriteResponseDto;
import org.example.cinehub_backend.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<FavoriteResponseDto> addFavorite(@RequestBody FavoriteRequestDto request) {
        return ResponseEntity.ok(favoriteService.addFavorite(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFavorite(@RequestParam Long userId, @RequestParam Long movieId) {
        favoriteService.removeFavorite(userId, movieId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponseDto>> getUserFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(userId));
    }
}
