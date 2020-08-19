package com.shree.ecommerce_m_v.utils.email.templates;

import com.shree.ecommerce_m_v.utils.email.mailJetSender.MailJetSenderClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationConfirmationTemplate {

    @Autowired
    private MailJetSenderClass mailJetSender;

    public void emailService(String to, int otp, String name) throws Exception {

        String BODY = String.join(
                System.getProperty("line.separator"),
                "<p>Dear</p> "+name,
                "<p>",
                "<p>Your signup verification code is : "+otp,
                "<p>",
                "<p>Regards",
                "<p>- SulabBazaar"
        );
        mailJetSender.sendMail(to,BODY,"Activate your account");

    }


}
