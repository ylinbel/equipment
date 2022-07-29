package com.mechanicaleng.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

    //delete functions

//    @DeleteMapping("items/{id}")
//    public ResponseEntity<String> deleteItem(@RequestBody Long id) {
//        ItemEntity itemEntity = itemService.findItemsWithId(id);
//        itemEntity.set(null);
//    }
    //需要加上

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
    





//    @GetMapping ("/items/{id}")
//    @GetMapping ("/items/bowwer")
//   @PathVariable
//
//    @PathParam()
}
