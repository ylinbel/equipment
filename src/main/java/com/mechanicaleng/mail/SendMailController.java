package com.mechanicaleng.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-mail")
public class SendMailController {
    @Autowired
    private SendMailServiceImpl sendMailService;

    @PostMapping
    public void sendMail(@RequestBody MailRequest mailRequest) {
        sendMailService.sendSimpleMail(mailRequest);
    }
}
