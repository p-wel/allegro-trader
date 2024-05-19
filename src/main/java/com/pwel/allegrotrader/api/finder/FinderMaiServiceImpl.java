package com.pwel.allegrotrader.api.finder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinderMaiServiceImpl {

    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        var message = new SimpleMailMessage();
        message.setFrom("allegrotradermail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        if (text != null) {
            message.setText(text);
            javaMailSender.send(message);
            log.info("FinderMaiServiceImpl: Mail sent to: '%s.'".formatted(to));
        } else {
            log.info("FinderMaiServiceImpl: Mail message empty. Mail to: '%s' not sent.".formatted(to));
        }
    }
}
