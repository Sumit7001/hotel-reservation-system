package com.wipro.training.hotel_reservation.controller;
/**
*Author  :Sumit Kumar Pal
*Date    :17-Sept-2024
*Time    :9:02:57 pm
*Project :
*/



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.training.hotel_reservation.model.User;
import com.wipro.training.hotel_reservation.service.UserService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login/{username}")
    public  ResponseEntity<User> login(@PathVariable String username) {
        User foundUser = userService.findByUsername(username);
        if (foundUser != null ){
          
           
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
    	return userService.findById(id);
    }
}

