package br.unisinos.encodedecodestepbystep.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SyncController {

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/sync")
    @ResponseStatus(HttpStatus.OK)
    public boolean syncronize() throws IOException {
        return true;
    }
}
