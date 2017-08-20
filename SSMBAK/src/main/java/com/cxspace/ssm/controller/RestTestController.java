package com.cxspace.ssm.controller;

import com.cxspace.ssm.model.User;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujie on 2017/7/26.
 */

@RestController

public class RestTestController {



    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(){

        List<User> users = new ArrayList<User>();


        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }



    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id)
    {

        System.out.println(id);

        User user = new User();

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }



    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName() + "with Password :" +user.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id")long id , @RequestBody User user)
    {
        System.out.println("update user "+id+" with value"+user.getName()+":"+user.getPassword());

        User user1 = new User();

        return new ResponseEntity<User>(user1,HttpStatus.OK);

    }


    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id")long id){

        System.out.println("Fetching & Deleting User with id " + id);

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers(){

        System.out.println("Delete All Users!");

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }


}
