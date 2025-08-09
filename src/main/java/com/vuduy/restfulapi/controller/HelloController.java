package com.vuduy.restfulapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuduy.restfulapi.service.error.IdInValidException;

@RestController
public class HelloController {
    @GetMapping("/")
    public String getHelloWorld() throws IdInValidException {
        if (true)
            throw new IdInValidException("check");
        return "Hello world";
    }
}
