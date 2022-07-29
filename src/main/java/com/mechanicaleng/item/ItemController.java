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

    @PutMapping("items/{id}")
    public ResponseEntity<String> borrowItem(@RequestBody Long id) {
        ItemEntity itemEntity = itemService.findItemsWithId(id);
        itemEntity.setStatusEnum(StatusEnum.NotAvailable);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("items/{id}")
    public ResponseEntity<String> returnItem(@RequestBody Long id) {
        ItemEntity itemEntity = itemService.findItemsWithId(id);
        itemEntity.setStatusEnum(StatusEnum.Available);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("items/{id}")
    public ResponseEntity<String> reportDamage(@RequestBody Long id) {
        ItemEntity itemEntity = itemService.findItemsWithId(id);
        itemEntity.setStatusEnum(StatusEnum.Damaged);
        return ResponseEntity.ok("Success");
    }
    





//    @GetMapping ("/items/{id}")
//    @GetMapping ("/items/bowwer")
//   @PathVariable
//
//    @PathParam()
}
