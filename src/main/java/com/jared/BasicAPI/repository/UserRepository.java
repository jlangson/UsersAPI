/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jared.BasicAPI.repository;

import org.springframework.data.repository.CrudRepository;
import com.jared.BasicAPI.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 *
 * @author jared
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findUsersByState(String state);
    
}
