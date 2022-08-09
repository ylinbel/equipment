package com.mechanicaleng.log;

import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;

    @PostMapping
    public ResponseEntity<String> addLog(@RequestBody LogDto logDto) {
        logService.addLog(logDto);
        return ResponseEntity.ok("Success");
    }

    //update log, mainly for changing isCurrent after item is returned
    @PutMapping
    public ResponseEntity<String> updateLog(@RequestBody LogDto logDto) {
        Boolean result = logService.updateLog(logDto);
        if (result) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @GetMapping("/find-borrow-list/{user-id}")
    public ResponseEntity<List<ItemEntity>> findAllBorrowItems(@PathVariable(value = "user-id") Long id) {
        List<ItemEntity> itemDtoList = logService.findBorrowList(id);
        return itemDtoList != null ? ResponseEntity.ok(itemDtoList) : ResponseEntity.notFound().build();
    }
}
