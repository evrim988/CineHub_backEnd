package org.example.cinehub_backend.dto;

import lombok.Data;

public record UserRegisterRequest (
        String username,
        String firstName,
        String lastName,
        String password,
        String email,
        String avatarUrl
) {

}
