package br.com.mark.coursesapi.utils;

import br.com.mark.coursesapi.usecases.interfaces.EmailUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSender implements EmailUseCase {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setFrom(sender);
        emailMessage.setTo(to);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);
        javaMailSender.send(emailMessage);

        log.info("Email sent successfully !");

    }



}