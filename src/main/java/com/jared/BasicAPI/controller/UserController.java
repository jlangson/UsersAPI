/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jared.BasicAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.jared.BasicAPI.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.jared.BasicAPI.model.User;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jared
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }
    
    @GetMapping (value = "/users/{id}")
    public Optional<User> getUserById(@PathVariable(value ="id") Long id){
        return userRepository.findById(id);
    }
    
    @PostMapping("users")
    public void createUser(@RequestBody User user){
        userRepository.save(user);
    }
    
    @PutMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user){
        userRepository.save(user);
    }
    
    @DeleteMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id){
    userRepository.deleteById(id);
    }
}
