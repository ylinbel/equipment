package com.mechanicaleng.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("/{id}/borrow")
    public ResponseEntity<String> borrowItem(@PathVariable Long id) {
        itemService.borrowItem(id);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<String> returnItem(@PathVariable Long id) {
        itemService.returnItem(id);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/{id}/report-damage")
    public ResponseEntity<String> reportDamage(@PathVariable Long id) {
        itemService.reportDamaged(id);
        return ResponseEntity.ok("Success");
    }

    // delete item
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteItemEntity(@PathVariable Long id) {
        itemService.deleteWithId(id);
        return ResponseEntity.ok("Deleted");
    }

    //search item

    @GetMapping("/{id}")
    public ResponseEntity<String> findItemWithId(@PathVariable Long id) {
        itemService.findItemsWithId(id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findItemWithName(@PathVariable String name) {
        itemService.findItemsWithName(name);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findItemWithSerial(@PathVariable String serial) {
        itemService.findItemsWithSerial(serial);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findItemsBySetName(@PathVariable String set) {
        itemService.findItemsBySetName(set);
        return ResponseEntity.ok("Success");
    }

    // find all demaged items

    @GetMapping("/lists")
    public ResponseEntity<String> findAllDamagedItems() {
        itemService.findAllDamagedItems();
        return ResponseEntity.ok("Success");
    }







//    @GetMapping ("/items/{id}")
//    @GetMapping ("/items/bowwer")
//   @PathVariable
//
//    @PathParam()
}
