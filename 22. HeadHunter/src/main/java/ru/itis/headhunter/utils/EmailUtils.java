package ru.itis.headhunter.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.itis.headhunter.exceptions.EmailSendingException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailUtils {
    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String templateName, Map<String, String> data) {
        try {
            StringWriter stringWriter = new StringWriter();
            configuration.getTemplate(templateName).process(data, stringWriter);
            String mailText = stringWriter.getBuffer().toString();

            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject(subject);
                messageHelper.setText(mailText, true);
                messageHelper.setTo(to);
                messageHelper.setFrom(from);
            };

            mailSender.send(preparator);
        } catch (TemplateException | IOException exception) {
            throw new EmailSendingException("sending confirm code exception, try another");
        }
    }

}
