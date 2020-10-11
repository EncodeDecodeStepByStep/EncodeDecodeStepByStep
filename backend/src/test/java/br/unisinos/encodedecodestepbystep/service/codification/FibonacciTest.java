package br.unisinos.encodedecodestepbystep.service.codification;

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
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.*;
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.setUpDecodeWithRedundancySum;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    WriterInterface writer;
    ReaderInterface reader;
    Fibonacci fibonacci;

    @BeforeEach
    void setUp() {
        this.fibonacci = new Fibonacci();
    }

    void setUp(ReaderWriterWrapper readerWriterWrapper) {
        this.writer = readerWriterWrapper.getWriterInterface();
        this.reader = readerWriterWrapper.getReaderInterface();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
        setUp(setUpEncodeAlice29());
        fibonacci.encode(this.writer, this.reader);

        setUp(setUpDecodeAlice29());
        fibonacci.decode(this.writer, this.reader);

        makeAssertions();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
        setUp(setUpEncodeSum());
        fibonacci.encode(this.writer, this.reader);

        setUp(setUpDecodeSum());
        fibonacci.decode(this.writer, this.reader);

        makeAssertions();
    }

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
    void deveRetornarIdentificaoEmBitsDoAlgoritmoSemByteCRC8QuandoNaoUtilizadoTratamentoDeRuido() {
        String bitsIdentificacaoAlgoritmoEsperado = "0000011100000000";
        String bitsIdentificacaoAlgoritmoRetornado = fibonacci.getBitsIdentificacaoAlgoritmo(new Writer());
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoComByteCRC8QuandoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "000001110000000001101011";
        String bitsIdentificacaoAlgoritmoRetornado = fibonacci.getBitsIdentificacaoAlgoritmo(new WriterRedundancy());
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }
}