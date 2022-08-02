package com.mechanicaleng.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//	public void borrowItem(Long id) {
//		ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
//		itemEntity.setStatusEnum(StatusEnum.NotAvailable);
//		itemRepository.save(itemEntity);
//	}
//
//	public void returnItem(Long id) {
//		ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
//		itemEntity.setStatusEnum(StatusEnum.Available);
//		itemRepository.save(itemEntity);
//	}
//
//	public void reportDamaged(Long id) {
//		ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
//		itemEntity.setStatusEnum(StatusEnum.Damaged);
//		itemRepository.save(itemEntity);
//	}

	//search item
	public ItemDto findItemsWithId(long id) {
		Optional<ItemEntity> item = itemRepository.findItemEntityByIdEquals(id);
		return item.isPresent() ? item.get().toDto() : null;
	}

	public List<ItemDto> findItemsWithSerial(String serial) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialLike(serial);
		return getItemDtos(entities);
	}

	public List<ItemDto> findItemsWithName(String name) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesByNameLike(name);
		return getItemDtos(entities);
    }

	private List<ItemDto> getItemDtos(List<ItemEntity> entities) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		entities.forEach(itemEntity -> {
			itemDtoList.add(itemEntity.toDto());
		});
		return itemDtoList;
    }

	public List<ItemDto> findItemsBySetName(String set) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySetNumLike(set);
		return getItemDtos(entities);
	}


	//find all damaged items
	public List<ItemDto> findItemsByStatus(StatusEnum status) {
		List<ItemEntity> entities = itemRepository.findAllByStatusEnumEquals(status);
		return getItemDtos(entities);
	}

	// update item information

//	public void updateName(long id, String name) {
//		ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
//		itemEntity.setName(name);
//		itemRepository.save(itemEntity);
//	}
//
//	public void updateSerial(long id, String serial) {
//		ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
//		itemEntity.setSerial(serial);
//		itemRepository.save(itemEntity);
//	}
//
//	public void updateSet(long id, String set) {
//		ItemEntity itemEntity = itemRepository.findItemEntityByIdEquals(id);
//		itemEntity.setSetNum(set);
//		itemRepository.save(itemEntity);
//	}

	public Boolean updateItem(ItemDto itemDto) {
		Optional<ItemEntity> optItemEntity = itemRepository.findById(itemDto.getId());
		if (optItemEntity.isEmpty()) return false;
		ItemEntity itemEntity = optItemEntity.get();
		itemEntity.updateFromDto(itemDto);
        itemRepository.save(itemEntity);
		return true;
    }
}
