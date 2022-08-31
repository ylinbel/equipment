package com.mechanicaleng.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteWithId(id);
        return ResponseEntity.ok("Deleted");
    }

    //update user information
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
        Boolean result = userService.updateUser(userDto);
        if (result) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<UserDisplayDto>> findUserWithName(@PathVariable String name) {
        List<UserDisplayDto> user = userService.findUserWithName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserDisplayDto> findUserWithEmail(@PathVariable String email) {
        UserDisplayDto user = userService.findUserWithEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-by-user-type/{userType}")
    public ResponseEntity<List<UserDisplayDto>> findUserWithUserType(@PathVariable String userType) {
        List<UserDisplayDto> userList = userService.findUsersWithUserType(UserTypeEnum.valueOf(userType));
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestParam("email") String email,@RequestParam("password") String password) {
        return (userService.userLogin(email, password))? ResponseEntity.ok("Success") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
    }
}
