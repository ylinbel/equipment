package com.mechanicaleng.log;

import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LogController {
    @Autowired
    LogService logService;

    @PostMapping("/log")
    public ResponseEntity<String> addLog(@RequestBody LogDto logDto) {
        logService.addLog(logDto);
        return ResponseEntity.ok("Success");
    }

    //update log, mainly for changing isCurrent after item is returned
    @PutMapping("/log")
    public ResponseEntity<String> updateLog(@RequestBody LogDto logDto) {
        Boolean result = logService.updateLog(logDto);
        if (result) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @GetMapping("users/{user-id}/borrow")
    public ResponseEntity<List<ItemDto>> findAllBorrowItems(@PathVariable(value = "user-id") Long user_id) {
        List<ItemDto> itemDtoList = logService.findBorrowList(user_id);
        return itemDtoList != null ? ResponseEntity.ok(itemDtoList) : ResponseEntity.notFound().build();
    }
}
