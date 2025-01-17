package org.example.cinehub_backend.service;

import ch.qos.logback.core.net.SMTPAppenderBase;
import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.UserLoginRequest;
import org.example.cinehub_backend.dto.UserRegisterRequest;
import org.example.cinehub_backend.entity.User;
import org.example.cinehub_backend.exception.CineHubException;
import org.example.cinehub_backend.exception.ErrorType;
import org.example.cinehub_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.utility.JwtManager;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final JwtManager jwtManager;


    public String login(UserLoginRequest dto) {

        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(dto.username(), dto.password());
        if(optionalUser.isPresent()) {
           return jwtManager.createUserToken(optionalUser.get().getId());
        }
        throw new CineHubException(ErrorType.ADMIN_NOT_FOUND);
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