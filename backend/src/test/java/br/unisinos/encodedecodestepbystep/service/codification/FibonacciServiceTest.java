package br.unisinos.encodedecodestepbystep.service.codification;

import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static br.unisinos.encodedecodestepbystep.service.codification.AssertionsEncodeDecode.makeAssertions;
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.setUpDecodeAlice29;
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.setUpEncodeAlice29;

class FibonacciServiceTest {

    WriterInterface writer;
    ReaderInterface reader;
    FibonacciService fibonacciService;

    @BeforeEach
    void setUp() {
        this.fibonacciService = new FibonacciService();
    }

    void setUp(ReaderWriterWrapper readerWriterWrapper) {
        this.writer = readerWriterWrapper.getWriterInterface();
        this.reader = readerWriterWrapper.getReaderInterface();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
        Codification.setEncodeCodification(true);
        setUp(setUpEncodeAlice29());
        fibonacciService.encode(this.writer, this.reader);

        Codification.setEncodeCodification(false);
        setUp(setUpDecodeAlice29());
        fibonacciService.decode(this.writer, this.reader);

        makeAssertions("alice29.txt");
    }

//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
//        Codification.setEncodeCodification(true);
//        setUp(setUpEncodeSum());
//        fibonacciService.encode(this.writer, this.reader);
//
//        Codification.setEncodeCodification(false);
//        setUp(setUpDecodeSum());
//        fibonacciService.decode(this.writer, this.reader);
//
//        makeAssertions("sum");
//    }

//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoComTratamentoDeRuidoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
//        setUp(setUpEncodeWithRedundancyAlice29());
//        fibonacci.encode(this.writer, this.reader);
//
//        setUp(setUpDecodeWithRedundancyAlice29());
//        fibonacci.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }
//
//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoComTratamentoDeRuidoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
//        setUp(setUpEncodeWithRedundancySum());
//        fibonacci.encode(this.writer, this.reader);
//
//        setUp(setUpDecodeWithRedundancySum());
//        fibonacci.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoSemByteCRC8QuandoNaoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "0000011100000000";
        String bitsIdentificacaoAlgoritmoRetornado = fibonacciService.getBitsIdentificacaoAlgoritmo(new Writer("src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod"));
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoComByteCRC8QuandoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "000001110000000001101011";
        String bitsIdentificacaoAlgoritmoRetornado = fibonacciService.getBitsIdentificacaoAlgoritmo(new WriterRedundancy("src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod"));
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }
}