package com.mechanicaleng.user;

import com.mechanicaleng.login.CookiesUtil;
import com.mechanicaleng.login.ServerConstants;
import com.mechanicaleng.login.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/find-by-name")
    public ResponseEntity<UserDisplayDto> findUserWithName() {
        UserDisplayDto user = userService.findUserWithName(UserInfoUtil.CURRENT_USER.get());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-list-by-name/{name}")
    public ResponseEntity<List<UserDisplayDto>> findUserWithName(@PathVariable String name) {
        List<UserDisplayDto> user = userService.findUserListWithName(name);
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
    public void userLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) throws IOException {

        String userLogin = userService.userLogin(email, password);
        if (userLogin != null) {
            CookiesUtil.createCookie(response, userLogin, ServerConstants.USER_COOKIE_KEY, true);
            CookiesUtil.createCookie(response, "true", ServerConstants.LOGIN_FLAG_KEY, false);
            response.setStatus(HttpStatus.OK.value());
            response.getOutputStream().write("Success".getBytes());
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getOutputStream().write("Bad Request".getBytes());
        }
    }

    @PostMapping("/logout")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) {
        CookiesUtil.deleteCookie(request, response, ServerConstants.USER_COOKIE_KEY);
        CookiesUtil.deleteCookie(request, response, ServerConstants.LOGIN_FLAG_KEY);
        response.setStatus(HttpStatus.OK.value());
    }
}
