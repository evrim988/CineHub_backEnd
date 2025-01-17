package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.AdminLoginRequest;
import org.example.cinehub_backend.entity.Admin;
import org.example.cinehub_backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Qualifier("adminRepository")
    private final AdminRepository adminRepository;
    public boolean login(AdminLoginRequest request) {
        // Admin'i kullanıcı adıyla veritabanında arayın.
        Admin admin=adminRepository.findByUsername(request.getUsername());
        throw new RuntimeException("Admin not found");

        //Parola kontrolü

    }

}
