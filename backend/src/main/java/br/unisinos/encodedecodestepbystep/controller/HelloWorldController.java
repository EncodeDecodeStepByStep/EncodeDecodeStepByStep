package br.unisinos.encodedecodestepbystep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @CrossOrigin("http://localhost:3000")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld(){
        return "Encode e Decode de forma didática vindo direto do back java =)";
    }
}
