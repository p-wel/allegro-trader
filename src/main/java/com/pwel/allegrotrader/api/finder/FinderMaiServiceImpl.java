package com.pwel.allegrotrader.api.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinderMaiServiceImpl {

    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        var message = new SimpleMailMessage();
        message.setFrom("allegrotradermail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text == null ? "Null results!" : text);
        javaMailSender.send(message);
    }
}
