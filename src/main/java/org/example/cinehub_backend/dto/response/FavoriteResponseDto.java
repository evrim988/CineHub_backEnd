package org.example.cinehub_backend.dto.response;

public record FavoriteResponseDto(
        Long id,
        Long userId,
        Long movieId
) {

}