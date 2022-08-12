package com.mechanicaleng.item;

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

@Service
public class BorrowLogService {
    @Autowired
    public BorrowLogRepository borrowLogRepository;

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public UserRepository userRepository;

    //create log

//    public Boolean handleBorrowLog(Long userId, Long itemId, BorrowTermEnum borrowTermEnum) {
//        Optional<UserEntity> user = userRepository.findById(userId);
//        Optional<ItemEntity> item = itemRepository.findById(itemId);
//        if (user.isPresent() && item.isPresent() && item.get().getStatusEnum().equals(StatusEnum.AVAILABLE)) {
//            BorrowLogEntity borrowLogEntity = new BorrowLogEntity();
//            borrowLogEntity.setUser(user.get());
//            borrowLogEntity.setItem(item.get());
//            borrowLogEntity.setOverDue(Boolean.FALSE);
//            borrowLogEntity.setBorrowTime(LocalDateTime.now());
//            borrowLogRepository.save(borrowLogEntity);
//            item.get().setStatusEnum(StatusEnum.NOT_AVAILABLE);
//            itemRepository.save(item.get());
//            return true;
//        }
//        return false;
//    }

    public Boolean handleBorrowLog(Long userId, ItemEntity itemEntity) {
        Optional<UserEntity> user = userRepository.findById(userId);
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
        } else if (BorrowTermEnum.MONTHLY == borrowTermEnum) {
            return LocalDateTime.now().plusMonths(1);
        } else {
            return LocalDateTime.now().plusMonths(3);
        }
    }


    //find all current borrow list with user id
    public List<BorrowLogDto> findBorrowList(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            return Collections.emptyList();
        } else {
            UserEntity user = userEntityOptional.get();
            List<BorrowLogEntity> entities = borrowLogRepository.findLogEntitiesByUserEquals(user);
            return getLogDtos(entities);
        }
    }

    private List<BorrowLogDto> getLogDtos(List<BorrowLogEntity> entities) {
        List<BorrowLogDto> borrowLogDtoList = new ArrayList<>();
        entities.forEach(borrowLogEntity -> {
            borrowLogDtoList.add(borrowLogEntity.toDto());
        });
        return borrowLogDtoList;
    }

    @Scheduled(initialDelay = 60_000, fixedDelayString = "${task.checkDiskSpace:PT4H0M0S}")
    public void checkOverDue() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<BorrowLogEntity> borrowLogEntities = borrowLogRepository.findAllByOverDueAndIsReturn(false, false);
        borrowLogEntities.forEach(borrowLog -> {
                    if (currentTime.isAfter(borrowLog.getOverDueTime())) {
                        borrowLog.setOverDue(true);
                        borrowLogRepository.save(borrowLog);
                    }
                }
        );
//        List<ItemEntity> unAvailableItems = itemRepository.findAllByStatusEnumEqualsAndReturnTypeEnumEquals(StatusEnum.NOT_AVAILABLE, BorrowTermEnum.DAILY);
//        List<BorrowLogEntity> currentEntities = new ArrayList<>();
//        unAvailableItems.forEach(item -> {
//            List<BorrowLogEntity> itemLog = borrowLogRepository.findLogEntitiesByItemEquals(item);
//            currentEntities.addAll(itemLog);
//        });
//        currentEntities.forEach(log -> {
//            if (Duration.between(log.getBorrowTime(), currentTime).compareTo(Duration.ofHours(24)) > 0)
//            log.setOverDue(Boolean.TRUE);
//            borrowLogRepository.save(log);
//        });
    }


}