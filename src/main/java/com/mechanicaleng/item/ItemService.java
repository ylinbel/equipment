package com.mechanicaleng.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    public ItemRepository itemRepository;


    //add item

    public void addItem(ItemDto itemDto) {
        ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
        itemRepository.save(itemEntity);
    }


    public void deleteWithName(String name) {
        itemRepository.deleteByNameAfter(name);
    }

    //set item status

    public void borrowItem(Long id) {
        ItemEntity itemEntity = itemRepository.findItemEntityById(id);
        itemEntity.setStatus(Status.NotAvailable);
        itemRepository.save(itemEntity);
    }

    public void returnItem(Long id) {
        ItemEntity itemEntity = itemRepository.findItemEntityById(id);
        itemEntity.setStatus(Status.Available);
        itemRepository.save(itemEntity);
    }

    public void reportDamaged(Long id) {
        ItemEntity itemEntity = itemRepository.findItemEntityById(id);
        itemEntity.setStatus(Status.Damaged);
        itemRepository.save(itemEntity);
    }

    //find item
    public ItemEntity findItemsWithId(long id) {
        ItemEntity item = itemRepository.findItemEntityById(id);
        return item;
    }


    public List<ItemEntity> findItemsWithName(String name) {
        List<ItemEntity> list = itemRepository.findItemEntitiesByName(name);
        return list;
    }












}
