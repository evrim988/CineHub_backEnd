package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String alici, String token){
        String url = "http://localhost:9090/v1/dev/user/verify-account?token=" + token;
        String body = "Hesabınızı onaylamak için lütfen aşağıdaki linke tıklayınız. \n\n" + url;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(alici);
        message.setSubject("Mail Onay linkiniz");
        message.setText(body);
        mailSender.send(message);
    }
}
