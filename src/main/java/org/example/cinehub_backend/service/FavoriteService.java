package org.example.cinehub_backend.service;

import org.example.cinehub_backend.dto.request.FavoriteRequestDto;
import org.example.cinehub_backend.dto.response.FavoriteResponseDto;

import java.util.List;

public interface FavoriteService {
    FavoriteResponseDto addFavorite(FavoriteRequestDto request);
    void removeFavorite(Long userId, Long movieId);
    List<FavoriteResponseDto> getUserFavorites(Long userId);
}
