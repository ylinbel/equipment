package com.mechanicaleng.log;

import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import com.mechanicaleng.item.ItemRepository;
import com.mechanicaleng.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    @Autowired
    public LogRepository logRepository;

    @Autowired
    public ItemRepository itemRepository;

    //add log
    public void addLog(LogDto logDto) {
        LogEntity logEntity = LogEntity.fromDto(logDto);
        logRepository.save(logEntity);
    }


    //find all current borrow list with user id
    public List<ItemDto> findBorrowList(Long id) {
        List<LogEntity> entities = logRepository.findLogEntitiesByUserEqualsAndIsCurrentEquals(id, Boolean.TRUE);
        List<ItemEntity> itemEntitiesList = new ArrayList<>();
        entities.forEach(logEntity -> {
            itemEntitiesList.add(logEntity.getItem());
        });
        return ItemService.getItemDtos(itemEntitiesList);
    }

    //update log, mainly for changing isCurrent
    public Boolean updateLog(LogDto logDto) {
        Optional<LogEntity> opLogEntity = logRepository.findById(logDto.getId());
        if (opLogEntity.isEmpty()) return false;
        LogEntity logEntity = opLogEntity.get();
        logEntity.updateFromDto(logDto);
        logRepository.save(logEntity);
        return true;
    }



    private List<LogEntity> getLogDtos(List<LogEntity> entities) {
        List<LogDto> logDtoList = new ArrayList<>();
        entities.forEach(logEntity -> {
            logDtoList.add(logEntity.toDto());
        });
        return getLogDtos(entities);
    }
}
