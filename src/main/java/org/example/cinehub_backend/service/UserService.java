package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.request.UserLoginRequestDto;
import org.example.cinehub_backend.dto.request.UserRegisterRequestDto;
import org.example.cinehub_backend.entity.User;
import org.example.cinehub_backend.exception.CineHubException;
import org.example.cinehub_backend.exception.ErrorType;
import org.example.cinehub_backend.mapper.UserMapper;
import org.example.cinehub_backend.repository.UserRepository;
import org.example.cinehub_backend.utility.JwtManager;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final JwtManager jwtManager;
    private final EmailService emailService;


    public String login(UserLoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(dto.username(), dto.password());
        if(optionalUser.isPresent()) {
            if(optionalUser.get().getIsEmailVerified()){
                return jwtManager.createUserToken(optionalUser.get().getId());
            }
            else{
                throw new CineHubException(ErrorType.MAIL_NOT_VERIFIED);
            }
        }
        throw new CineHubException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
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

    public void verifyAccount(String token) {
        Optional<Long> optionalUserId = jwtManager.validateToken(token, "USER");
        if (optionalUserId.isEmpty()){
            throw new CineHubException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUser = userRepository.findById(optionalUserId.get());
        if (optionalUser.isEmpty()){
            throw new CineHubException(ErrorType.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        user.setIsEmailVerified(true);
        userRepository.save(user);
    }

    public void register(UserRegisterRequestDto dto) {
        User user = UserMapper.INSTANCE.fromUserRegisterRequestDto(dto);
        if(userRepository.existsByUsername(dto.username())){
            throw new CineHubException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        if (userRepository.existsByEmail(dto.email())){
            throw new CineHubException(ErrorType.EMAIL_ALREADY_EXIST);
        }
        userRepository.save(user);
        emailService.sendEmail(dto.email(), jwtManager.createUserToken(user.getId()));
    }

}