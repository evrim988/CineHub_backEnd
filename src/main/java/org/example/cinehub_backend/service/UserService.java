package org.example.cinehub_backend.service;

import ch.qos.logback.core.net.SMTPAppenderBase;
import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.UserLoginRequest;
import org.example.cinehub_backend.dto.UserRegisterRequest;
import org.example.cinehub_backend.entity.User;
import org.example.cinehub_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    // Kullanıcı Giriş İşlemi
    public boolean login(UserLoginRequest loginRequest) {
        // Kullanıcı adıyla kullanıcıyı bul
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // Şifre kontrolü
        return user.getPassword().equals(loginRequest.getPassword());
    }


    // Kullanıcı Kayıt İşlemi
    public void register(UserRegisterRequest registerRequest) {
        // Kullanıcı adı veya e-posta zaten kayıtlı mı kontrol et
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Bu e-posta zaten kayıtlı.");
        }

        User newUser = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword()) // Şifreyi düz metin olarak kaydetmek güvenli değil
                .avatarUrl(registerRequest.getAvatarUrl())
                .isEmailVerified(false)
                .build();
        // Kullanıcıyı kaydet
        userRepository.save(newUser);
    }

    // Kullanıcı Detaylarını Getirme
    public User getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }
}