package br.unisinos.encodedecodestepbystep.service.codification;

import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.unisinos.encodedecodestepbystep.service.codification.AssertionsEncodeDecode.makeAssertions;
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.setUpDecodeAmazingDevs;
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.setUpEncodeAmazingDevs;

//Because we work with static attributes we need to test if the others algorithms is not interfering in each other.
@SpringBootTest
public class SequencialServiceTest {

    @Autowired
    GoulombService goulombService;

    @Autowired
    DeltaService deltaService;

    @Autowired
    EliasGammaService eliasGammaService;

    @Autowired
    FibonacciService fibonacciService;

    @Autowired
    HuffmanService huffmanService;

    @Autowired
    UnarioService unarioService;

    WriterInterface writer;
    ReaderInterface reader;

    @BeforeEach
    void setUp() {
        this.fibonacciService = new FibonacciService();
    }

    void setUp(ReaderWriterWrapper readerWriterWrapper) {
        this.writer = readerWriterWrapper.getWriterInterface();
        this.reader = readerWriterWrapper.getReaderInterface();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEDecodeDeUmTxtSequencialmente() throws IOException, WrongFormatExpection {

        List<CodificationService> condificationServiceList = new ArrayList<>(Arrays.asList(deltaService, eliasGammaService, fibonacciService, goulombService, unarioService));
        Collections.shuffle(condificationServiceList);

        for (CodificationService condificationService : condificationServiceList) {
            Codification.setEncodeCodification(true);
            setUp(setUpEncodeAmazingDevs());
            condificationService.encode(this.writer, this.reader);

            Codification.setEncodeCodification(false);
            setUp(setUpDecodeAmazingDevs());
            condificationService.decode(this.writer, this.reader);

            System.out.println(condificationService.getClass());
            makeAssertions("AmazingDevs.txt");
        }
    }
}
