package org.example.cinehub_backend.dto.request;

public record UserLoginRequestDto(
        String username,
        String password
) {

}
