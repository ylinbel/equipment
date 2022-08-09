package com.mechanicaleng.user;

import com.mechanicaleng.item.ItemDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    //add user
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.ok("Success");
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemEntity(@PathVariable Long id) {
        userService.deleteWithId(id);
        return ResponseEntity.ok("Deleted");
    }

    //update user information
    @PutMapping
    public ResponseEntity<String> updateItem(@RequestBody UserDto userDto) {
        Boolean result = userService.updateUser(userDto);
        if (result) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<UserDto>> findItemWithName(@PathVariable String name) {
        List<UserDto> user = userService.findUserWithName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-all-manager")
    public ResponseEntity<List<UserDto>> findAllManager() {
        List<UserDto> user = userService.findAllManagers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-all-superuser")
    public ResponseEntity<List<UserDto>> findAllSuperUser() {
        List<UserDto> user = userService.findAllSuperUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-all-standard-user")
    public ResponseEntity<List<UserDto>> findAllStandardUser() {
        List<UserDto> user = userService.findAllStandardUsers();
        return ResponseEntity.ok(user);
    }






}
