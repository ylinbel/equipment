package com.mechanicaleng.mail;

import com.mechanicaleng.item.BorrowLogEntity;
import com.mechanicaleng.item.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private static final Logger logger = LoggerFactory.getLogger(SendMailService.class);

    public void sendBrokenEmail(ItemEntity item) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo("2025381928@qq.com");
        message.setSubject("Item Broken");
        message.setText(
                "Dear manager, \n" +
                        "\n" +
                        "An item is reported broken: \n" +
                        "\n" +
                        "item name: " +  item.getName() + "\n" +
                        "item serial: " +  item.getSerial() + "\n" +
                        "\n" +
                        "Dynamics Lab Email Assistant\n" +
                        "\n" +
                        "_________________________________________________________________________________\n" +
                        "\n" +
                        "Please do not reply this email address directly. \n"
        );
        javaMailSender.send(message);
        logger.info("Successfully send the email");

    }

    public void sendOverdueEmail(BorrowLogEntity log) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(log.getUser().getEmail());
        message.setSubject("Reminder: Please Return Overdue Items");
        message.setText("Dear user, \n" +
                "\n" +
                "This is a reminder that you need to return the following items to the lab as soon as possible: \n" +
                "\n" +
                "Item name: " + log.getItem().getName() + ", overdue time: " + log.getReturnTime().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "\n" +
                "\n" +
                "Thank you,\n" +
                "Dynamics Lab Email Assistant\n" +
                "\n" +
                "_________________________________________________________________________________\n" +
                "\n" +
                "Please do not reply this email address directly. \n"
        );
        javaMailSender.send(message);
        logger.info("Successfully send the email");
    }

}
