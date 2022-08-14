package com.mechanicaleng.mail;

import com.mechanicaleng.item.BorrowLogEntity;
import com.mechanicaleng.item.BorrowLogService;
import com.mechanicaleng.item.ItemEntity;

public interface SendMailService {
    void sendSimpleMail(MailRequest mailRequest);

    void sendBrokenEmail(ItemEntity item);

    void sendOverdueEmail(BorrowLogEntity log);
}
