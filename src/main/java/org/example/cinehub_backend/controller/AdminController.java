package org.example.cinehub_backend.controller;


import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.AdminLoginRequest;
import org.example.cinehub_backend.dto.response.BaseResponse;
import org.example.cinehub_backend.entity.Admin;
import org.example.cinehub_backend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin("*")

public class AdminController {
    private final AdminService adminService;


    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(@RequestBody AdminLoginRequest adminLoginRequest) {
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .code(200)
                        .success(true)
                        .message("login işlemi başarılı")
                        .data(adminService.login(adminLoginRequest))
                .build());

    }
}

