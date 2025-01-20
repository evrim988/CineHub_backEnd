package org.example.cinehub_backend.dto.request;

public record UserRegisterRequestDto(
        String username,
        String firstName,
        String lastName,
        String password,
        String email
) {

}
