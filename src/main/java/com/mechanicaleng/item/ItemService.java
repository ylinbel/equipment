package com.mechanicaleng.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    public ItemRepository itemRepository;

    public void addItem(ItemDto itemDto) {
        ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
        itemRepository.save(itemEntity);
    }

    // Question: why add itemDto? how about addItem with information

//    @Override
//    public void addItemWithInfo(String name, String password, String serial) {
//        ItemDto itemDTo = new ItemDto(name, password, serial);
//    }

    public void deleteWithName(String name) {
        itemRepository.deleteByNameAfter(name);
    }

    public ItemEntity findItemWithName(String name) {
        ItemEntity item = itemRepository.findItemEntitiesByNameEquals(name);
        return item;
    }

    public void borrowItem(ItemDto itemDto) {
        itemDto.setStatus(Boolean.FALSE);
        ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
        itemRepository.save(itemEntity);
    }

    public void returnItem(ItemDto itemDto) {
        itemDto.setStatus(Boolean.TRUE);
        ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
        itemRepository.save(itemEntity);
    }

    public void returnDamagedItem(ItemDto itemDto) {
        itemDto.setStatus(Boolean.FALSE);
        itemDto.setDamaged(Boolean.TRUE);
        ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
        itemRepository.save(itemEntity);
    }









}
