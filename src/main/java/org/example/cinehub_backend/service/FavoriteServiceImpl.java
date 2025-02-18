package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.request.FavoriteRequestDto;
import org.example.cinehub_backend.dto.response.FavoriteResponseDto;
import org.example.cinehub_backend.entity.Favorite;
import org.example.cinehub_backend.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;

    @Override
    public FavoriteResponseDto addFavorite(FavoriteRequestDto request) {
        Favorite favorite = new Favorite();
        favorite.setUserId(request.getUserId());
        favorite.setMovieId(request.getMovieId());
        Favorite savedFavorite = favoriteRepository.save(favorite);
        return new FavoriteResponseDto(savedFavorite.getId(), savedFavorite.getUserId(), savedFavorite.getMovieId());
    }

    @Override
    public void removeFavorite(Long userId, Long movieId) {
        favoriteRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public List<FavoriteResponseDto> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId).stream()
                .map(favorite -> new FavoriteResponseDto(favorite.getId(), favorite.getUserId(), favorite.getMovieId()))
                .collect(Collectors.toList());
    }
}

