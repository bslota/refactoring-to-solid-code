package com.bslota.refactoring.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;

@Service
public class NotificationSender {

    private static final Logger log = LoggerFactory.getLogger(NotificationSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String[] recipients, String from, String subject, String content) {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setTo(recipients);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, false);
            javaMailSender.send(mimeMessage);
            log.info("Email successfully sent. \n From: {} \n To: {} \n Subject: {} \n Content: {}",
                mimeMessage.getFrom(),
                StringUtils.arrayToCommaDelimitedString(mimeMessage.getAllRecipients()),
                mimeMessage.getSubject(),
                mimeMessage.getContent().toString());
        } catch (Exception e) {
            log.error("Error occurred while sending email", e);
            throw new RuntimeException(e);
        }
    }

}
