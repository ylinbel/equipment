package com.mechanicaleng.item;

import com.mechanicaleng.category.CategoryEntity;
import com.mechanicaleng.category.CategoryRepository;
import com.mechanicaleng.location.LocationEntity;
import com.mechanicaleng.location.LocationRepository;
import com.mechanicaleng.mail.SendMailService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ItemService {
	@Autowired
	public ItemRepository itemRepository;

	@Autowired
	public LocationRepository locationRepository;

	@Autowired
	public BorrowLogService borrowLogService;

	@Autowired
	public SendMailService sendMailService;

	@Autowired
	public CategoryRepository categoryRepository;

	//add item

	public void addItem(ItemDto itemDto) {
		ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
		itemEntity.setLocation(locationRepository.findById(itemDto.getLocation().getId()).get());
		itemEntity.setCategory(categoryRepository.findById(itemDto.getCategory().getId()).get());
		itemEntity.setStatusEnum(StatusEnum.AVAILABLE);
		itemRepository.save(itemEntity);
	}

    //delete item

	public void deleteItemWithId(Long id) {
		itemRepository.deleteById(id);
	}

	public Boolean updateItem(ItemDto itemDto) {
		Optional<ItemEntity> optItemEntity = itemRepository.findById(itemDto.getId());
		if (optItemEntity.isEmpty()) return false;
		ItemEntity itemEntity = optItemEntity.get();
		itemEntity.updateFromDto(itemDto);
		itemEntity.setLocation(locationRepository.findById(itemDto.getLocation().getId()).get());
		itemRepository.save(itemEntity);
		return true;
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
		List<ItemEntity> entities2 = itemRepository.findItemEntitiesByNameStartingWith(name);
		Set<ItemEntity> set = new LinkedHashSet<>();
		set.addAll(entities);
		set.addAll(entities2);
		return getItemDtos(new ArrayList<>(set));
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

	//find all items under the same category

	public List<ItemDto> findByCategory(long categoryId) {
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryId);
		if (categoryEntityOptional.isEmpty()) {
			return Collections.emptyList();
		} else {
			CategoryEntity category = categoryEntityOptional.get();
			List<ItemEntity> entities = itemRepository.findItemEntitiesByCategoryEquals(category);
			return getItemDtos(entities);
		}
	}

	//find all damaged items

	public List<ItemDto> findItemsByStatus(StatusEnum status) {
		List<ItemEntity> entities = itemRepository.findAllByStatusEnumEquals(status);
		return getItemDtos(entities);
	}


	public List<ItemDto> findItemsWithSerialStartWith(String serial) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith(serial);
		return getItemDtos(entities);
	}

	@Transactional
	public Boolean borrowItem(Long id) {
		Optional<ItemEntity> optItemEntity = itemRepository.findItemEntityByIdAndStatusEnum(id, StatusEnum.AVAILABLE);
		if (optItemEntity.isEmpty()) return false;
		ItemEntity itemEntity = optItemEntity.get();
		itemEntity.setStatusEnum(StatusEnum.NOT_AVAILABLE);
		itemRepository.save(itemEntity);
		return borrowLogService.handleBorrowLog(2L, itemEntity);
	}

	@Transactional
	public Boolean returnItem(Long id, Boolean isBroken) {
		Optional<ItemEntity> optItemEntity = itemRepository.findItemEntityByIdAndStatusEnum(id, StatusEnum.NOT_AVAILABLE);
		if (optItemEntity.isEmpty()) return false;
		ItemEntity itemEntity = optItemEntity.get();
		itemEntity.setStatusEnum(isBroken ? StatusEnum.DAMAGED : StatusEnum.AVAILABLE);
		itemRepository.save(itemEntity);
		if(isBroken) sendMailService.sendBrokenEmail(itemEntity);
		return borrowLogService.handleReturnLog(itemEntity);
	}

	public Boolean restoreItem(Long id) {
		Optional<ItemEntity> opItemEntity = itemRepository.findById(id);
		if(opItemEntity.isEmpty()) return false;
		opItemEntity.get().setStatusEnum(StatusEnum.AVAILABLE);
		itemRepository.save(opItemEntity.get());
		return true;
	}
}
