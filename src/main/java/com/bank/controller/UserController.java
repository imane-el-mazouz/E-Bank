package com.bank.controller;

import com.bank.dto.UserDto;
import com.bank.model.User;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/all")
//    public List<User> getAllUsers(){
//        return userService.getAllUsers();
//    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users =userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null){
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public UserDto saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if (userService.getUserById(id) != null) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("up/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user2) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            existingUser.setName(user2.getName());
            existingUser.setProfession(user2.getProfession());
            existingUser.setEmail(user2.getEmail());
            existingUser.setPassword(user2.getPassword());
            existingUser.setAccounts(user2.getAccounts());
            UserDto updatedUser = userService.saveUser(existingUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}