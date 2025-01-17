package org.example.cinehub_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AdminLoginRequest(
        String username,
        String password
) {

}