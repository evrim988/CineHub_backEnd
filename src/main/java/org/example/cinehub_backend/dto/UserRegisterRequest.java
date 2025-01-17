package org.example.cinehub_backend.dto;

import lombok.Data;

@Data

public class UserRegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String avatarUrl;
}
