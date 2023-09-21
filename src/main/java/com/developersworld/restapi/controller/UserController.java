package com.developersworld.restapi.controller;

import com.developersworld.restapi.entities.User;
import com.developersworld.restapi.exception.UserNotFoundException;
import com.developersworld.restapi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/hello")
    public String demo(){
       return "hello world";
    }


    @RequestMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepo.findAll();
        if(users.size() == 0){
            throw new UserNotFoundException("user is not found");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = userRepo.findById(id).orElseThrow(()
                -> new UserNotFoundException("user is not found with this id"+id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser  = userRepo.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id){
        User newUser = userRepo.findById(id).orElseThrow(()
                -> new UserNotFoundException("user is not found with this id"+id));
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        return new ResponseEntity<>(userRepo.save(newUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User newUser = userRepo.findById(id).orElseThrow(()
                -> new UserNotFoundException("user is not found with this id"+id));
        userRepo.delete(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
