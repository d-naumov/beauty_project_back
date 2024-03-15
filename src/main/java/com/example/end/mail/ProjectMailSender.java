package com.example.end.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectMailSender {

    private final JavaMailSender javaMailSender;
    @Async
    public void sendEmail(String email, String subject, String text) {

        MimeMessage message = javaMailSender.createMimeMessage(); // создаем сообщение
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8"); // делаем Spring-обертку, чтобы было удобнее

        try {
            // задаем данные для письма
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }
        // отправляем это сообщение на почту
        javaMailSender.send(message);
    }

    @Async
    public void sendMasterConfirmationRequest(String adminEmail, String masterName) {
        String subject = "Запрос на подтверждение нового мастера";
        String text = String.format("Уважаемый администратор,\n\n" +
                "Пользователь %s зарегистрировался как мастер и ожидает вашего подтверждения.\n" +
                "Пожалуйста, выполните подтверждение в системе.\n\n" +
                "С уважением,\nВаша система управления.", masterName);
        sendEmail(adminEmail, subject, text); // Используем метод sendEmail для отправки сообщения
    }
}
