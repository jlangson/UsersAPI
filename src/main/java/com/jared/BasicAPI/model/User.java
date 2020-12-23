/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jared.BasicAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jared
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @ApiModelProperty(notes = "The user id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "First name")
    @Size(max=20)
    private String firstName;
    @ApiModelProperty(notes = "Last Name")
    @Size(min=2)
    private String lastName;
    @ApiModelProperty(notes = "State person lives in")
    @Size(min=4, max=20)
    private String state;
    
}
