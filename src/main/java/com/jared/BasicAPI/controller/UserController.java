/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jared.BasicAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import com.jared.BasicAPI.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.jared.BasicAPI.model.User;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;

/**
 *
 * @author jared
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(value="state", required=false) String state){
        if(state!=null){
            return new ResponseEntity<List<User>>((List<User>) userRepository.findUsersByState(state), HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>((List<User>) userRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping (value = "/users/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value ="id") Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@Valid @PathVariable(value="id") Long id, @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> userTemp = userRepository.findById(id);
        if(!userTemp.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value="id") Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
