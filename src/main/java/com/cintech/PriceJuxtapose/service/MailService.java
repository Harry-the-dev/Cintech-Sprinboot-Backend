package com.cintech.PriceJuxtapose.service;


import com.cintech.PriceJuxtapose.entity.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentService mailContentService;

    void sendMail(NotificationEmail notificationEmail)
    {
        MimeMessagePreparator messagePreparator = MimeMessage ->{
            MimeMessageHelper messageHelper = new MimeMessageHelper(MimeMessage);
            messageHelper.setFrom("support@pricejuxtapose.live");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentService.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent ");
        } catch ( MailException e )
        {
            throw new MailSendException("Error occured when sending mail to " + notificationEmail.getRecipient());
        }

    }

}
