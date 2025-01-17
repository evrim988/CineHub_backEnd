package org.example.cinehub_backend.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.cinehub_backend.dto.UserLoginRequest;
import org.example.cinehub_backend.dto.UserRegisterRequest;
import org.example.cinehub_backend.dto.response.BaseResponse;
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
    public ResponseEntity<BaseResponse<String>> login(@RequestBody UserLoginRequest dto) {
       return ResponseEntity.ok(BaseResponse.<String>builder()
                       .code(200)
                       .success(true)
                       .message("Giriş işlemi başarılı")
                       .data(userService.login(dto))
               .build());
    }

}
