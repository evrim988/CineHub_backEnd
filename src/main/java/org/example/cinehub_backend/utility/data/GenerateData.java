package org.example.cinehub_backend.utility.data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.entity.Admin;
import org.example.cinehub_backend.entity.User;
import org.example.cinehub_backend.repository.AdminRepository;
import org.example.cinehub_backend.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateData {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void generateData() {
        if(adminRepository.count() == 0) {
            Admin admin = Admin.builder()
                    .username("admin")
                    .password("admin123")
                    .build();
            adminRepository.save(admin);
        }

        if(userRepository.count() == 0) {
            User user = User.builder()
                    .firstName("Ahmet")
                    .lastName("Yılmaz")
                    .email("ahmet@gmail.com")
                    .username("ahmetyilmaz")
                    .password("sifre123")
                    .isEmailVerified(true)
                    .build();
            userRepository.save(user);
        }
    }
}
