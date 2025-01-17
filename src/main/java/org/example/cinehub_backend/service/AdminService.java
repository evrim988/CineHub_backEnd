package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.AdminLoginRequest;
import org.example.cinehub_backend.entity.Admin;
import org.example.cinehub_backend.exception.CineHubException;
import org.example.cinehub_backend.exception.ErrorType;
import org.example.cinehub_backend.repository.AdminRepository;
import org.example.cinehub_backend.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final JwtManager jwtManager;

    public String login(AdminLoginRequest dto) {

        Optional<Admin> optionalAdmin = adminRepository.findByUsernameAndPassword(dto.username(), dto.password());
        if (optionalAdmin.isPresent()) {
            return jwtManager.createAdminToken(optionalAdmin.get().getId());
        }
        throw new CineHubException(ErrorType.ADMIN_NOT_FOUND);

    }

}
