package org.example.cinehub_backend.controller;


import lombok.RequiredArgsConstructor;
import static org.example.cinehub_backend.constant.RestApis.*;
import org.example.cinehub_backend.dto.request.AdminLoginRequestDto;
import org.example.cinehub_backend.dto.response.BaseResponse;
import org.example.cinehub_backend.entity.Admin;
import org.example.cinehub_backend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
@CrossOrigin("*")

public class AdminController {
    private final AdminService adminService;


    @PostMapping(ADMINLOGIN)
    public ResponseEntity<BaseResponse<String>> login(@RequestBody AdminLoginRequestDto adminLoginRequest) {
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .code(200)
                        .success(true)
                        .message("login işlemi başarılı")
                        .data(adminService.login(adminLoginRequest))
                .build());

    }

    @GetMapping("/get-admin-profile")
    public ResponseEntity<BaseResponse<Admin>> getAdminProfile(@RequestParam String token) {
        return ResponseEntity.ok(BaseResponse.<Admin>builder()
                        .code(200)
                        .success(true)
                        .message("Admin bilgisi başarıyla getirildi.")
                        .data(adminService.getAdminProfile(token))
                .build());
    }
}

