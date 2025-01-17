package org.example.cinehub_backend.dto;

import lombok.Data;

public record UserLoginRequest(
        String username,
        String password
) {

}
