package org.example.cinehub_backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.request.FavoriteRequestDto;
import org.example.cinehub_backend.dto.response.BaseResponse;
import org.example.cinehub_backend.dto.response.FavoriteResponseDto;
import org.example.cinehub_backend.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<BaseResponse<FavoriteResponseDto>> addFavorite(@RequestBody FavoriteRequestDto request) {
        FavoriteResponseDto response = favoriteService.addFavorite(request);
        return ResponseEntity.ok(BaseResponse.<FavoriteResponseDto>builder()
                .code(200)
                .success(true)
                .message("Favori başarıyla eklendi.")
                .data(response)
                .build());
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Void>> removeFavorite(@RequestParam Long userId, @RequestParam Long movieId) {
        favoriteService.removeFavorite(userId, movieId);
        return ResponseEntity.ok(BaseResponse.<Void>builder()
                .code(200)
                .success(true)
                .message("Favori başarıyla silindi.")
                .data(null)
                .build());
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<FavoriteResponseDto>>> getUserFavorites(@RequestParam Long userId) {
        List<FavoriteResponseDto> response = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(BaseResponse.<List<FavoriteResponseDto>>builder()
                .code(200)
                .success(true)
                .message("Kullanıcının favorileri başarıyla getirildi.")
                .data(response)
                .build());
    }
}

