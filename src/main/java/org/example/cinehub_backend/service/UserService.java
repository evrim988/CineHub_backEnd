package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.request.UserLoginRequestDto;
import org.example.cinehub_backend.entity.User;
import org.example.cinehub_backend.exception.CineHubException;
import org.example.cinehub_backend.exception.ErrorType;
import org.example.cinehub_backend.repository.UserRepository;
import org.example.cinehub_backend.utility.JwtManager;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final JwtManager jwtManager;


    public String login(UserLoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(dto.username(), dto.password());
        if(optionalUser.isPresent()) {
           return jwtManager.createUserToken(optionalUser.get().getId());
        }
        throw new CineHubException(ErrorType.ADMIN_NOT_FOUND);
    }

    public User getUserProfile(String token) {
        Optional<Long> optionalUserId = jwtManager.validateToken(token, "USER");
        if (optionalUserId.isEmpty()){
            throw new CineHubException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUser = userRepository.findById(optionalUserId.get());
        if (optionalUser.isEmpty()){
            throw new CineHubException(ErrorType.USER_NOT_FOUND);
        }
        return optionalUser.get();
    }


}