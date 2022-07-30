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

    public void deleteWithId(Long id) {
        itemRepository.deleteByIdEquals(id);
    }


    //set item status

    public void borrowItem(Long id) {
        ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
        itemEntity.setStatusEnum(StatusEnum.NotAvailable);
        itemRepository.save(itemEntity);
    }

    public void returnItem(Long id) {
        ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
        itemEntity.setStatusEnum(StatusEnum.Available);
        itemRepository.save(itemEntity);
    }

    public void reportDamaged(Long id) {
        ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
        itemEntity.setStatusEnum(StatusEnum.Damaged);
        itemRepository.save(itemEntity);
    }

    //search item
    public ItemEntity findItemsWithId(long id) {
        ItemEntity item = itemRepository.findItemEntityByIdEquals(id);
        return item;
    }

    public List<ItemEntity> findItemsWithSerial(String serial) {
        List<ItemEntity> list = itemRepository.findItemEntitiesBySerialLike(serial);
        return list;
    }

    public List<ItemEntity> findItemsWithName(String name) {
        List<ItemEntity> list = itemRepository.findItemEntitiesByNameLike(name);
        return list;
    }

    public List<ItemEntity> findItemsBySetName(String set) {
        List<ItemEntity> list = itemRepository.findItemEntitiesBySetLike(set);
        return list;
    }


    //find all damaged items
    public List<ItemEntity> findAllDamagedItems() {
        List<ItemEntity> damagedList = itemRepository.findAllByStatusEnumEquals(StatusEnum.Damaged);
        return damagedList;
    }

    // update item information

    public void updateName(long id, String name) {
        ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
        itemEntity.setName(name);
        itemRepository.save(itemEntity);
    }

    public void updateSerial(long id, String serial) {
        ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
        itemEntity.setSerial(serial);
        itemRepository.save(itemEntity);
    }

    public void updateSet(long id, String set) {
        ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
        itemEntity.setSet(set);
        itemRepository.save(itemEntity);
    }

}
