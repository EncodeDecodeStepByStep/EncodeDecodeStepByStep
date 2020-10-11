package br.unisinos.encodedecodestepbystep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncodeDecodeStepByStepApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertAll(() -> EncodeDecodeStepByStepApplication.main(new String[]{"arg1", "arg2"}));
    }

}
