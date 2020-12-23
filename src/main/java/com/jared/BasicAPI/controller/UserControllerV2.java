/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jared.BasicAPI.controller;

import com.jared.BasicAPI.model.User;
import com.jared.BasicAPI.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jared
 */
@Api(value="users", description = "CRUD operations on users")
@RestController
@RequestMapping("/v2")
public class UserControllerV2 {
    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Get all users, optionally by state", response = User.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved users")
    })
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(value="state", required=false) String state){
        if(state!=null){
            return new ResponseEntity<List<User>>((List<User>) userRepository.findUsersByState(state), HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>((List<User>) userRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get a user by id", response = User.class)
    @GetMapping (value = "/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value ="id") Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "create a new user", response = User.class)
    @PostMapping("users")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 201, message = "User created")
    })
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value="Update a user by ID", response = User.class)
    @PutMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 200, message = "User updated")
    })
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

    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 200, message = "User deleted")
    })
    @ApiOperation(value="Delete user by id", response = User.class)
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
