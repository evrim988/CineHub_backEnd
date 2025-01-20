package org.example.cinehub_backend.controller;

import lombok.RequiredArgsConstructor;
import static org.example.cinehub_backend.constant.RestApis.*;
import org.example.cinehub_backend.dto.request.UserLoginRequestDto;
import org.example.cinehub_backend.dto.response.BaseResponse;
import org.example.cinehub_backend.entity.User;
import org.example.cinehub_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(USER)

public class UserController {
    private final UserService userService;

    @PostMapping(USERLOGIN)
    public ResponseEntity<BaseResponse<String>> login(@RequestBody UserLoginRequestDto dto) {
       return ResponseEntity.ok(BaseResponse.<String>builder()
                       .code(200)
                       .success(true)
                       .message("Giriş işlemi başarılı")
                       .data(userService.login(dto))
               .build());
    }

    @GetMapping("/user-get-profile")
    public ResponseEntity<BaseResponse<User>> getUserProfile(@RequestParam String token) {
        return ResponseEntity.ok(BaseResponse.<User>builder()
                        .code(200)
                        .success(true)
                        .message("Kullanıcı bilgisi başarıyla getirildi.")
                        .data(userService.getUserProfile(token))
                .build());
    }

}
