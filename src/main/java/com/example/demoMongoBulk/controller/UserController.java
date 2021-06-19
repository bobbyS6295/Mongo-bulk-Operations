package com.example.demoMongoBulk.controller;

import com.example.demoMongoBulk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    UserService userService;



    @GetMapping("/save/all")
    public String saveAll() {
        userService.saveAllUserBulkWritesTest();
        return "Record inserted";
    }

    @GetMapping("/upsert/all")
    public String upsertAll() {
        userService.upsertUserBulkWritesTest();
        return "Upsert success";
    }

    @GetMapping("/replace/all")
    public String replace() {
        userService.replaceBulkWritesTest();
        return "Replace sucess";
    }
}
