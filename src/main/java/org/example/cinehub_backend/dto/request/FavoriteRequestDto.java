package org.example.cinehub_backend.dto.request;

public record FavoriteRequestDto(
        Long userId,
        Long movieId
) {

    public Long getUserId() {
        return 0L;
    }

    public Long getMovieId() {
        return 0L;
    }
}
