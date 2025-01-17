package org.example.cinehub_backend.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.cinehub_backend.dto.UserLoginRequest;
import org.example.cinehub_backend.dto.UserRegisterRequest;
import org.example.cinehub_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/user")

public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest loginRequest) {
        boolean isAuthenticated = userService.login(loginRequest);
        if (isAuthenticated) {
            return ResponseEntity.ok("Giriş Başarılı!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz kimlik bilgileri!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Kullanıcı Kaydı Başarıyla Gerçekleşti!");

    }

    @PostMapping("/username")
    public ResponseEntity<?> getUserDetails(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserDetails(username));

    }

}
