package com.mechanicaleng.item;

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

	// delete item
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemEntity(@PathVariable Long id) {
		itemService.deleteWithId(id);
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

	@GetMapping("/find-by-serial/{serial}")
	public ResponseEntity<List<ItemDto>> findItemWithSerial(@PathVariable String serial) {
		List<ItemDto> itemsWithSerial = itemService.findItemsWithSerial(serial);
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

	//update item information

//    @PutMapping("/{id}/update")
//    public ResponseEntity<String> updateItemName(@PathVariable Long id, String name) {
//        itemService.updateName(id, name);
//        return ResponseEntity.ok("Success");
//    }
//
//    @PutMapping("/{id}/update")
//    public ResponseEntity<String> updateItemSerial(@PathVariable Long id, String serial) {
//        itemService.updateSerial(id, serial);
//        return ResponseEntity.ok("Success");
//    }
//
//    @PutMapping("/{id}/update")
//    public ResponseEntity<String> updateItemSet(@PathVariable Long id, String set) {
//        itemService.updateSet(id, set);
//        return ResponseEntity.ok("Success");
//    }


//    @GetMapping ("/items/{id}")
//    @GetMapping ("/items/bowwer")
//   @PathVariable
//
//    @PathParam()
}
