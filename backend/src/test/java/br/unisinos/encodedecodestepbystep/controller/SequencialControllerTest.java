package br.unisinos.encodedecodestepbystep.controller;

import br.unisinos.encodedecodestepbystep.controller.response.CodificationDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Because we work with static attributes we need to test if the others algorithms is not interfering in each other.
@SpringBootTest
public class SequencialControllerTest {

    @Autowired
    AutomaticContoller automaticContoller;

    @Autowired
    GoulombController goulombController;

    @Autowired
    DeltaController deltaController;

    @Autowired
    EliasGammaController eliasGammaController;

    @Autowired
    FibonacciController fibonacciController;

    @Autowired
    HuffmanController huffmanController;

    @Autowired
    UnarioController unarioController;

    private InputStream isEsperadoBeforeCodification;
    private InputStream isCodewordEsperado;


    void setUp() throws FileNotFoundException {
        this.isEsperadoBeforeCodification = new FileInputStream(new File("src/test/resources/filesToEncodeDecodeTest/AmazingDevs.txt"));
        this.isCodewordEsperado = new FileInputStream(new File(System.getProperty("user.dir") + "/public/backend_jar/database/CodewordsSizesArray.repository"));
    }

    void tearDown() throws IOException {
        this.isEsperadoBeforeCodification.close();
        this.isCodewordEsperado.close();
    }

    @Test
    void deveSerOsMesmosCodewordsGravadosNoEncodeNoNextStepConcatenadoExcetoPeloCabecalhoQuandoEstiverNoProcessoDeEncodeSequencial() throws InterruptedException, IOException {
        List<CodificationController> codificationControllerList = new ArrayList<>(Arrays.asList(deltaController, eliasGammaController, fibonacciController, huffmanController, goulombController, unarioController));
        Collections.shuffle(codificationControllerList);
        for (CodificationController codificationController : codificationControllerList) {
            System.out.println(codificationController.getClass());
            encode(codificationController);
        }
    }

    void encode(CodificationController codificationController) throws InterruptedException, IOException {
        setUp();
        StringBuilder codewordEsperado = new StringBuilder("");
        codificationController.encode("src/test/resources/filesToEncodeDecodeTest/AmazingDevs.txt");

        TimeUnit.SECONDS.sleep(10); // para dar tempo para iniciar thread do encode
        this.isCodewordEsperado.skip(17 + ("Delta".equals(Codification.getCodificationName()) ? 6 : 0)); // para skippar cabeçalho + virgula
        CodificationDTO codificationDTORetornado = automaticContoller.nextStep();

        while (!codificationDTORetornado.getStepsFinished()) {
            while (codewordEsperado.length() != codificationDTORetornado.getCodeword().length()) {
                codewordEsperado.append((char) this.isCodewordEsperado.read());
            }
            this.isCodewordEsperado.read(); // para jogar o caracter da virgula fora.

            Assertions.assertEquals((char) this.isEsperadoBeforeCodification.read(), codificationDTORetornado.getCharacterBeforeEncode().charAt(0));
            Assertions.assertEquals(codewordEsperado.toString(), codificationDTORetornado.getCodeword());

            codewordEsperado = new StringBuilder("");
            codificationDTORetornado = automaticContoller.nextStep();
        }
        Assertions.assertEquals(codificationDTORetornado.getNumberOfCharsTotal(), codificationDTORetornado.getNumberOfCharsReaded());
        tearDown();
    }

    @Test
    void deveSerOsMesmosCodewordsGravadosNoDecodeNoNextStepConcatenadoExcetoPeloCabecalhoQuandoEstiverNoProcessoDeEncodeEDecodeSequencialmente() throws IOException, InterruptedException, WrongFormatExpection {
        List<CodificationController> codificationControllerList = new ArrayList<>(Arrays.asList(deltaController, eliasGammaController, fibonacciController, goulombController, huffmanController, unarioController));
        Collections.shuffle(codificationControllerList);

        for (CodificationController codificationController : codificationControllerList) {
            System.out.println(codificationController.getClass());
            encode(codificationController);
            decode(automaticContoller);
        }
    }

    void decode(AutomaticContoller automaticContoller) throws InterruptedException, IOException {
        setUp();

        StringBuilder codewordEsperado = new StringBuilder("");
        automaticContoller.decode("src/test/resources/filesToEncodeDecodeTest/AmazingDevs.txt.cod");

        TimeUnit.SECONDS.sleep(10); // para dar tempo para iniciar thread do encode
        CodificationDTO codificationDTORetornado = automaticContoller.nextStep();
        while (!codificationDTORetornado.getStepsFinished()) {
            while (codewordEsperado.length() + 1 != codificationDTORetornado.getBitsBeforeDecode().length() + codificationDTORetornado.getCharacterDecoded().length()) {
                codewordEsperado.append((char) this.isCodewordEsperado.read());
            }
            this.isCodewordEsperado.read(); // para jogar o caracter da virgula fora.

            Assertions.assertEquals((char) this.isEsperadoBeforeCodification.read(), codificationDTORetornado.getCharacterDecoded().charAt(0));
            Assertions.assertEquals(codewordEsperado.toString(), codificationDTORetornado.getBitsBeforeDecode());

            codewordEsperado = new StringBuilder("");
            codificationDTORetornado = automaticContoller.nextStep();
        }
        tearDown();
    }
}
