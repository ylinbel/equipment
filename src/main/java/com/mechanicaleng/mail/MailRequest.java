package com.mechanicaleng.mail;

import lombok.Data;

@Data
public class MailRequest {
    private String sendTo;

    private String subject;

    private String text;

}
