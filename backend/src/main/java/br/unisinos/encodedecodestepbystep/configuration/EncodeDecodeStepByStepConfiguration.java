package br.unisinos.encodedecodestepbystep.configuration;

import br.unisinos.encodedecodestepbystep.controller.*;
import br.unisinos.encodedecodestepbystep.service.codification.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncodeDecodeStepByStepConfiguration {

    @Bean
    public GoulombService goulombService(){
        return new GoulombService(2);
    }
}
