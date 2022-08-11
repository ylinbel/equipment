package com.mechanicaleng.item;

import com.mechanicaleng.user.UserEntity;
import com.mechanicaleng.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    @Autowired
    public LogRepository logRepository;

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public UserRepository userRepository;

    //create log

    public Boolean addLog(Long userId, Long itemId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<ItemEntity> item = itemRepository.findById(itemId);
        if (user.isPresent() && item.isPresent() && item.get().getStatusEnum().equals(StatusEnum.AVAILABLE)) {
            LogEntity logEntity = new LogEntity();
            logEntity.setUser(user.get());
            logEntity.setItem(item.get());
            logEntity.setOverDue(Boolean.FALSE);
            logEntity.setStartTime(LocalDateTime.now());
            logRepository.save(logEntity);
            item.get().setStatusEnum(StatusEnum.NOT_AVAILABLE);
            itemRepository.save(item.get());
            return true;
        }
        return false;
    }






    //find all current borrow list with user id
    public List<LogDto> findBorrowList(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            return Collections.emptyList();
        } else {
            UserEntity user = userEntityOptional.get();
            List<LogEntity> entities = logRepository.findLogEntitiesByUserEquals(user);
            return getLogDtos(entities);
        }
    }

    //check overdue
    //暂时只写了24小时overdue的
    @Scheduled(initialDelay = 60_000, fixedDelayString = "${task.checkDiskSpace:PT4H0M0S}")
    public void checkOverDue() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<ItemEntity> unAvailableItems = itemRepository.findAllByStatusEnumEqualsAndReturnTypeEnumEquals(StatusEnum.NOT_AVAILABLE, ReturnTypeEnum.DAILY);
        List<LogEntity> currentEntities = new ArrayList<>();
        unAvailableItems.forEach(item -> {
            List<LogEntity> itemLog = logRepository.findLogEntitiesByItemEquals(item);
            currentEntities.addAll(itemLog);
        });
        currentEntities.forEach(log -> {
            if (Duration.between(log.getStartTime(), currentTime).compareTo(Duration.ofHours(24)) > 0)
            log.setOverDue(Boolean.TRUE);
            logRepository.save(log);
        });
    }




    private List<LogDto> getLogDtos(List<LogEntity> entities) {
        List<LogDto> logDtoList = new ArrayList<>();
        entities.forEach(logEntity -> {
            logDtoList.add(logEntity.toDto());
        });
        return logDtoList;
    }
}
