package com.vaszily.WorkoutPlanner.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
public class AccountController {
    @GetMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public void dummy(){

    }
}
