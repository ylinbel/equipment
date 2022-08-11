package com.mechanicaleng.item;

import com.mechanicaleng.user.UserEntity;
import com.mechanicaleng.user.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (user.isPresent() && item.isPresent()) {
            LogEntity logEntity = new LogEntity();
            logEntity.setUser(user.get());
            logEntity.setItem(item.get());
            logRepository.save(logEntity);
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


    private List<LogDto> getLogDtos(List<LogEntity> entities) {
        List<LogDto> logDtoList = new ArrayList<>();
        entities.forEach(logEntity -> {
            logDtoList.add(logEntity.toDto());
        });
        return logDtoList;
    }
}
