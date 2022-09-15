package com.mechanicaleng.item;

import com.mechanicaleng.mail.SendMailService;
import com.mechanicaleng.user.UserEntity;
import com.mechanicaleng.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowLogService {
    @Autowired
    public BorrowLogRepository borrowLogRepository;

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public SendMailService sendMailService;
    //create log


    public Boolean handleBorrowLog(String userName, ItemEntity itemEntity) {
        Optional<UserEntity> user = userRepository.findUserEntityByName(userName);
        if (user.isPresent()) {
            BorrowLogEntity borrowLogEntity = new BorrowLogEntity();
            borrowLogEntity.setUser(user.get());
            borrowLogEntity.setItem(itemEntity);
            borrowLogEntity.setBorrowTime(LocalDateTime.now());
            borrowLogEntity.setOverDueTime(getOverDueTime(itemEntity.getBorrowTermEnum()));
            borrowLogRepository.save(borrowLogEntity);
            return true;
        }
        return false;
    }

    public Boolean handleReturnLog(ItemEntity itemEntity) {
        Optional<BorrowLogEntity> optionalBorrowLogEntity = borrowLogRepository.findFirstByItemAndIsReturn(itemEntity, false);
        if (optionalBorrowLogEntity.isPresent()) {
            BorrowLogEntity borrowLogEntity = optionalBorrowLogEntity.get();
            borrowLogEntity.setIsReturn(true);
            borrowLogEntity.setReturnTime(LocalDateTime.now());
            borrowLogRepository.save(borrowLogEntity);
        }
        return false;
    }

    private LocalDateTime getOverDueTime(BorrowTermEnum borrowTermEnum) {
        if (BorrowTermEnum.DAILY == borrowTermEnum) {
            return LocalDateTime.now().plusDays(1);
        } else if (BorrowTermEnum.WEEKLY == borrowTermEnum) {
            return LocalDateTime.now().plusDays(7);
        } else if (BorrowTermEnum.TWOWEEK == borrowTermEnum) {
            return LocalDateTime.now().plusDays(14);
        } else if (BorrowTermEnum.MONTHLY == borrowTermEnum) {
            return LocalDateTime.now().plusMonths(1);
        } else {
            return LocalDateTime.now().plusMonths(3);
        }
    }


    //find all current borrow list with user id
    public List<BorrowLogDto> findBorrowList(String userName) {
        Optional<UserEntity> userEntityOptional = userRepository.findUserEntityByName(userName);
        if (userEntityOptional.isEmpty()) {
            return Collections.emptyList();
        } else {
            UserEntity user = userEntityOptional.get();
            List<BorrowLogEntity> entities = borrowLogRepository.findLogEntitiesByUserEquals(user);
            List<BorrowLogEntity> currentLogs = entities.stream().filter(item -> !item.getIsReturn()).collect(Collectors.toList());
            return getLogDtos(currentLogs);
        }
    }

    private List<BorrowLogDto> getLogDtos(List<BorrowLogEntity> entities) {
        List<BorrowLogDto> borrowLogDtoList = new ArrayList<>();
        entities.forEach(borrowLogEntity -> {
            borrowLogDtoList.add(borrowLogEntity.toDto());
        });
        return borrowLogDtoList;
    }

    public List<BorrowLogDto>  getLogsByOverDueAndIsReturn(Boolean overdue, Boolean isReturn) {
        List<BorrowLogEntity> entities = borrowLogRepository.findAllByOverDueAndIsReturn(overdue, isReturn);
        return getLogDtos(entities);
    }

    @Scheduled(initialDelay = 60_000, fixedDelayString = "${task.checkDiskSpace:PT4H0M0S}")
    public void checkOverDue() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<BorrowLogEntity> borrowLogEntities = borrowLogRepository.findAllByOverDueAndIsReturn(false, false);
        borrowLogEntities.forEach(borrowLog -> {
                    if (currentTime.isAfter(borrowLog.getOverDueTime())) {
                        borrowLog.setOverDue(true);
                        borrowLogRepository.save(borrowLog);
                        //send a reminding email
                        sendMailService.sendOverdueEmail(borrowLog);
                    }
                }
        );
    }


}
