package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
// Đảm bảo dự án khác thì vẫn gọi được đến API của mình.
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;
    // Find all users
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable allUser() {
        return iUserService.findAll();
    }

    // Create users
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return iUserService.save(user);
    }

    // Delete users
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<User> user = iUserService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iUserService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Update users
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User editSmartphone(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return iUserService.save(user);
    }

    // User detail
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User userDetail(@PathVariable Long id) {
        return iUserService.findById(id).get();
    }

}
