package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationEntity;
import com.mechanicaleng.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
	@Autowired
	public ItemRepository itemRepository;

	@Autowired
	public LocationRepository locationRepository;

	//add item

	public void addItem(ItemDto itemDto) {
		ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
		itemRepository.save(itemEntity);
	}

    //delete item

	public void deleteItemWithId(Long id) {
		itemRepository.deleteById(id);
	}

	//search item
	public ItemDto findItemsWithId(long id) {
		Optional<ItemEntity> item = itemRepository.findItemEntityByIdEquals(id);
		return item.isPresent() ? item.get().toDto() : null;
	}

	public List<ItemDto> findItemsWithSerialLike(String serial) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialLike(serial);
		return getItemDtos(entities);
	}

	public List<ItemDto> findItemsWithName(String name) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesByNameLike(name);
		return getItemDtos(entities);
    }

	public List<ItemDto> getItemDtos(List<ItemEntity> entities) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		entities.forEach(itemEntity -> {
			itemDtoList.add(itemEntity.toDto());
		});
		return itemDtoList;
    }

	public List<ItemDto> findItemsBySetName(String set) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySetNameLike(set);
		return getItemDtos(entities);
	}

	//find all items in the same location
	public List<ItemDto> findByLocation(long locationId) {
		Optional<LocationEntity> locationEntityOptional = locationRepository.findById(locationId);
		if (locationEntityOptional.isEmpty()) {
			return Collections.emptyList();
		} else {
			LocationEntity location = locationEntityOptional.get();
			List<ItemEntity> entities = itemRepository.findItemEntitiesByLocationEquals(location);
			return getItemDtos(entities);
		}
	}


	//find all damaged items
	public List<ItemDto> findItemsByStatus(StatusEnum status) {
		List<ItemEntity> entities = itemRepository.findAllByStatusEnumEquals(status);
		return getItemDtos(entities);
	}


	public Boolean updateItem(ItemDto itemDto) {
		Optional<ItemEntity> optItemEntity = itemRepository.findById(itemDto.getId());
		if (optItemEntity.isEmpty()) return false;
		ItemEntity itemEntity = optItemEntity.get();
		itemEntity.updateFromDto(itemDto);
        itemRepository.save(itemEntity);
		return true;
    }

	public List<ItemDto> findItemsWithSerialStartWith(String serial) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith(serial);
		return getItemDtos(entities);
	}

}
