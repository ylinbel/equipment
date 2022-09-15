package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationService;
import com.mechanicaleng.login.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	ItemService itemService;

	@Autowired
	LocationService locationService;

	@Autowired
	BorrowLogService borrowLogService;

	@PostMapping
	public ResponseEntity<String> addItem(@RequestBody ItemDto itemDto) {
		itemService.addItem(itemDto);
		return ResponseEntity.ok("Success");
	}

	@PutMapping
	public ResponseEntity<String> updateItem(@RequestBody ItemDto itemDto) {
		Boolean result = itemService.updateItem(itemDto);
		if (result) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
		}
	}

	@PutMapping("/borrow/{itemId}")
	public ResponseEntity<String> borrowItem(@PathVariable(value = "itemId") Long item) {
		Boolean result = itemService.borrowItem(item, UserInfoUtil.CURRENT_USER.get());
		if (result) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
		}
	}

	@PutMapping("/{id}/{isBroken}/return")
	public ResponseEntity<String> returnItem(@PathVariable Long id, @PathVariable Boolean isBroken) {
		Boolean result = itemService.returnItem(id, isBroken);
		if (result) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
		}
	}

	@PutMapping("/{id}/restore")
	public ResponseEntity<String> retoreItem(@PathVariable Long id) {
		Boolean result = itemService.restoreItem(id);
		if (result) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
		}
	}

	//update item information

	// delete item
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemEntity(@PathVariable Long id) {
		itemService.deleteItemWithId(id);
		return ResponseEntity.ok("Deleted");
	}

	//search item
	@GetMapping("/{id}")
	public ResponseEntity<ItemDto> findItemWithId(@PathVariable Long id) {
		ItemDto itemDto = itemService.findItemsWithId(id);
		return itemDto != null ? ResponseEntity.ok(itemDto) : ResponseEntity.notFound().build();
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<List<ItemDto>> findItemWithName(@PathVariable String name) {
		List<ItemDto> itemsWithName = itemService.findItemsWithName(name);
		return ResponseEntity.ok(itemsWithName);
	}

	@GetMapping("/find-by-overdue-and-isReturn/{overdue}/{isReturn}")
	public ResponseEntity<List<BorrowLogDto>> findByOverdueAndIsReturn(@PathVariable(value = "overdue")Boolean overdue, @PathVariable(value = "isReturn")Boolean isReturn) {
		List<BorrowLogDto> list = borrowLogService.getLogsByOverDueAndIsReturn(overdue, isReturn);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/find-by-serial-like/{serial}")
	public ResponseEntity<List<ItemDto>> findItemWithSerialLike(@PathVariable String serial) {
		List<ItemDto> itemsWithSerial = itemService.findItemsWithSerialLike(serial);
		return ResponseEntity.ok(itemsWithSerial);
	}

	@GetMapping("/find-by-serial-start-with/{serial}")
	public ResponseEntity<List<ItemDto>> findItemWithSerialStartWith(@PathVariable String serial) {
		List<ItemDto> itemsWithSerial = itemService.findItemsWithSerialStartWith(serial);
		return ResponseEntity.ok(itemsWithSerial);
	}

	@GetMapping("/find-by-set/{set}")
	public ResponseEntity<List<ItemDto>> findItemsBySetName(@PathVariable String set) {
		List<ItemDto> itemsBySetName = itemService.findItemsBySetName(set);
		return ResponseEntity.ok(itemsBySetName);
	}

	// find all damaged items
	@GetMapping("/find-by-status/{status}")
	public ResponseEntity<List<ItemDto>> findAllDamagedItems(@PathVariable String status) {
		List<ItemDto> itemsByStatus = itemService.findItemsByStatus(StatusEnum.valueOf(status));
		return ResponseEntity.ok(itemsByStatus);
	}

	//find all items in the same location
	@GetMapping("/find-by-location/{location-id}")
	public ResponseEntity<List<ItemDto>> findByLocation(@PathVariable(value = "location-id") long location) {
		List<ItemDto> itemsByLocation = itemService.findByLocation(location);
		return itemsByLocation != null ? ResponseEntity.ok(itemsByLocation) : ResponseEntity.notFound().build();
	}

	//find all items under the same location
	@GetMapping("/find-by-category/{category-id}")
	public ResponseEntity<List<ItemDto>> findByCategory(@PathVariable(value = "category-id") long category) {
		List<ItemDto> itemsByCategory = itemService.findByCategory(category);
		return itemsByCategory != null ? ResponseEntity.ok(itemsByCategory) : ResponseEntity.notFound().build();
	}

	@GetMapping("log/get-borrow-list")
	public ResponseEntity<List<BorrowLogDto>> getBorrowList() {
		List<BorrowLogDto> borrowList = borrowLogService.findBorrowList(UserInfoUtil.CURRENT_USER.get());
		return borrowList != null ? ResponseEntity.ok(borrowList) : ResponseEntity.notFound().build();
	}

	@GetMapping("log/check-overdue")
	public ResponseEntity<String> checkOverdue() {
		borrowLogService.checkOverDue();
		return ResponseEntity.ok("success");
	}
}
