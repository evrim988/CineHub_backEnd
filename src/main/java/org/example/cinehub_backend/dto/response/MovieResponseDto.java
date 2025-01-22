package org.example.cinehub_backend.dto.response;

public record MovieResponseDto(
        String title,
        String description,
        Integer year,
        String genre,
        String posterUrl
){


}
