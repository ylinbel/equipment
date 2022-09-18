package com.mechanicaleng.mail;

import com.mechanicaleng.item.BorrowLogEntity;
import com.mechanicaleng.item.BorrowLogRepository;
import com.mechanicaleng.item.ItemEntity;
import com.mechanicaleng.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BorrowLogRepository borrowLogRepository;

    @Value("${spring.mail.username}")
    private String sender;

    private static final Logger logger = LoggerFactory.getLogger(SendMailService.class);

    public void sendBrokenEmail(ItemEntity item) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo("me-dynamics-dl@imperial.ac.uk");
        message.setSubject("Item Broken");
        message.setText(
                "Dear Dynamics Group Laboratory Manager, \n" +
                        "\n" +
                        "An item is reported broken: \n" +
                        "\n" +
                        "item name: " +  item.getName() + "\n" +
                        "item serial: " +  item.getSerial() + "\n" +
                        "\n" +
                        "Dynamics Lab Email Assistant\n" +
                        "\n" +
                        "………………………………………………………………………………………………\n" +
                        "\n" +
                        "Please do not reply this email address directly. \n" +
                        "\n" +
                        "Dr Luke Muscutt\n" +
                        "Dynamics Group Laboratory Technician \n" +
                        "562 and 003 City & Guilds Building\n" +
                        "Imperial College London\n" +
                        "Exhibition Road\n" +
                        "London SW7 2AZ\n" +
                        "Tel: +44 (0)20 7594 7188\n" +
                        "Mobile: +44 (0)7723 325 834\n" +
                        "Email: l.muscutt@imperial.ac.uk"
        );
        javaMailSender.send(message);
        logger.info("Successfully send the email");

    }

    public void sendOverdueEmail(BorrowLogEntity log) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(log.getUser().getEmail());
        message.setSubject("Reminder: Please Return Overdue Items");
        message.setText(
                "Dear" + log.getUser().getName() + "," +
                "\n" +
                "This is a reminder that you need to return the following items to the lab as soon as possible. " +
                        "If you want to keep it for longer, please check in and check out the item again. " +
                "\n" +
                "Item name: " + log.getItem().getName() + ", overdue time: " + log.getOverDueTime().toString() + "\n" +
                "\n" +
                "Thank you,\n" +
                "\n" +
                "Dynamics Lab Email Assistant\n" +
                "\n" +
                "………………………………………………………………………………………………\n" +
                "\n" +
                "Please do not reply this email address directly. \n" +
                "\n" +
                "Dr Luke Muscutt\n" +
                "Dynamics Group Laboratory Technician \n" +
                "562 and 003 City & Guilds Building\n" +
                "Imperial College London\n" +
                "Exhibition Road\n" +
                "London SW7 2AZ\n" +
                "Tel: +44 (0)20 7594 7188\n" +
                "Mobile: +44 (0)7723 325 834\n" +
                "Email: l.muscutt@imperial.ac.uk"
        );
        javaMailSender.send(message);
        logger.info("Successfully send the email");
    }

    public void sendLeavingEmailToUser(UserEntity user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(user.getEmail());
        message.setSubject("Reminder: Change To Your account");
        message.setText(
                "Dear" + user.getName() + "," +
                "\n" +
                "This is a reminder that your account will be deleted within 30 days. Please return all the items before you leave." +
                "If you want to change the date you leave the lab, please contact the manager.\n" +
                "\n" +
                        "Dynamics Lab Email Assistant\n" +
                        "\n" +
                        "………………………………………………………………………………………………\n" +
                        "\n" +
                        "Please do not reply this email address directly. \n" +
                        "\n" +
                        "Dr Luke Muscutt\n" +
                        "Dynamics Group Laboratory Technician \n" +
                        "562 and 003 City & Guilds Building\n" +
                        "Imperial College London\n" +
                        "Exhibition Road\n" +
                        "London SW7 2AZ\n" +
                        "Tel: +44 (0)20 7594 7188\n" +
                        "Mobile: +44 (0)7723 325 834\n" +
                        "Email: l.muscutt@imperial.ac.uk"
        );
        javaMailSender.send(message);
        logger.info("Successfully send the email");
    }

    public void sendLeavingEmailToManager(UserEntity user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo("me-dynamics-dl@imperial.ac.uk");
        message.setSubject("User " + user.getName() + "Is About To Leave the Lab");
        message.setText(
                "Dear Dynamics Group Laboratory Manager, \n" +
                "\n" +
                "A user is going to leave the lab within 30 days \n" +
                "\n" +
                "User name " +  user.getName() + "\n" +
                "Contact email: " +  user.getEmail() + "\n" +
                "Leaving Date: " +  user.getUtilDate().toString() + "\n" +
                "User Type: " +  user.getUserTypeEnum().toString() + "\n" +
                "Items to return:" + getItemsToReturn(user) +
                "\n" +
                "Dynamics Lab Email Assistant\n" +
                "\n" +
                "………………………………………………………………………………………………\n" +
                "\n" +
                "Please do not reply this email address directly. \n" +
                "\n" +
                "Dr Luke Muscutt\n" +
                "Dynamics Group Laboratory Technician \n" +
                "562 and 003 City & Guilds Building\n" +
                "Imperial College London\n" +
                "Exhibition Road\n" +
                "London SW7 2AZ\n" +
                "Tel: +44 (0)20 7594 7188\n" +
                "Mobile: +44 (0)7723 325 834\n" +
                "Email: l.muscutt@imperial.ac.uk"
        );
        javaMailSender.send(message);
        logger.info("Successfully send the email");
    }

    public String getItemsToReturn(UserEntity user) {
        StringBuilder stringBuilder = new StringBuilder();
        List<BorrowLogEntity> logs = borrowLogRepository.findLogEntitiesByUserEquals(user);
        List<BorrowLogEntity> currentLogs = logs.stream().filter(item -> !item.getIsReturn()).collect(Collectors.toList());
        currentLogs.forEach(log -> {
                stringBuilder.append("Item name: " + log.getItem().getName() + "  ");
                stringBuilder.append("Overdue time: " + log.getOverDueTime().toString() + "\n");
        });
        return stringBuilder.toString();
    }
}
