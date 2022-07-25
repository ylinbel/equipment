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

    //delete item


    public void deleteWithName(String name) {
        itemRepository.deleteByNameAfter(name);
    }

    public void deleteWithId(Long id) {
        itemRepository.deleteById(id);
    }

    public void deleteWithSerialNumber(String serial) {
        itemRepository.deleteBySerial(serial);
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

    //search item
    public ItemEntity findItemsWithId(long id) {
        ItemEntity item = itemRepository.findItemEntityById(id);
        return item;
    }

    public List<ItemEntity> findItemsWithSerial(String serial) {
        List<ItemEntity> list = itemRepository.findItemEntitiesBySerial(serial);
        return list;
    }

    public List<ItemEntity> findItemsWithName(String name) {
        List<ItemEntity> list = itemRepository.findItemEntitiesByName(name);
        return list;
    }

    //find all damaged items
    public List<ItemEntity> findAllDamagedItems() {
        List<ItemEntity> damagedList = itemRepository.findAllByStatusEquals(Status.Damaged);
        return damagedList;
    }











}
