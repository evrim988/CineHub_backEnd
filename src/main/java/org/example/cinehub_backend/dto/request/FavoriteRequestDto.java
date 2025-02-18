package org.example.cinehub_backend.dto.request;

public record FavoriteRequestDto(
        Long userId,
        Long movieId
) {

}
