package br.unisinos.encodedecodestepbystep.controller;

import br.unisinos.encodedecodestepbystep.controller.response.CodificationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
class EliasGammaControllerTest {

    @Autowired
    EliasGammaController eliasGammaController;

    private InputStream isEsperadoBeforeCodification;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        isEsperadoBeforeCodification = new FileInputStream(new File("src/test/resources/filesToEncodeDecodeTest/alice29.txt"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void deveSerOsMesmosCodewordsGravadosNoEncodeNoNextStepConcatenadoExcetoPeloCabecalhoQuandoEstiverNoProcessoDeEncode() throws InterruptedException, IOException {
        // TODO problemas pra testar devido ao paralelismo
//        eliasGammaController.encode("src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt");
//
//        CodificationDTO codificationDTORetornado = eliasGammaController.nextStep();
//        int i = 5;
//        while (!codificationDTORetornado.getCodeword().isEmpty()){
//            System.out.println(i++);
//            System.out.println(codificationDTORetornado.getCharacterBeforeCodification());
//
//            Assertions.assertEquals((char) isEsperadoBeforeCodification.read(), codificationDTORetornado.getCharacterBeforeCodification().charAt(0));
//            codificationDTORetornado.getCodeword(); // TODO assert
//
//            codificationDTORetornado = eliasGammaController.nextStep();
//        }

    }

    @Test
    void deveSerOsMesmosCodewordsGravadosNoDecodeNoNextStepConcatenadoExcetoPeloCabecalhoQuandoEstiverNoProcessoDeDecode() {
        //TODO
    }

    @Test
    void getProgressPercentage() {
        //TODO
    }
}