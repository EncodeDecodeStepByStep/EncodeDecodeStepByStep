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

class GoulombTest {

    WriterInterface writer;
    ReaderInterface reader;
    Goulomb goulomb;

    @BeforeEach
    void setUp() {
        this.goulomb = new Goulomb(2);
    }

    void setUp(ReaderWriterWrapper readerWriterWrapper) {
        this.writer = readerWriterWrapper.getWriterInterface();
        this.reader = readerWriterWrapper.getReaderInterface();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
        setUp(setUpEncodeAlice29());
        goulomb.encode(this.writer, this.reader);

        setUp(setUpDecodeAlice29());
        goulomb.decode(this.writer, this.reader);

        makeAssertions();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
        setUp(setUpEncodeSum());
        goulomb.encode(this.writer, this.reader);

        setUp(setUpDecodeSum());
        goulomb.decode(this.writer, this.reader);

        makeAssertions();
    }

//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoComTratamentoDeRuidoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
//        setUp(setUpEncodeWithRedundancyAlice29());
//        goulomb.encode(this.writer, this.reader);
//
//        setUp(setUpDecodeWithRedundancyAlice29());
//        goulomb.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }
//
//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoComTratamentoDeRuidoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
//        setUp(setUpEncodeWithRedundancySum());
//        goulomb.encode(this.writer, this.reader);
//
//        setUp(setUpDecodeWithRedundancySum());
//        goulomb.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoComSegundoByteCorrepondendoAoTamanhoDoDivisor() {
        goulomb = new Goulomb(21);
        String bitsIdentificacaoAlgoritmoEsperado = "0000111100010101";
        String bitsIdentificacaoAlgoritmoRetornado = goulomb.getBitsIdentificacaoAlgoritmo(new Writer());
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoSemByteCRC8QuandoNaoUtilizadoTratamentoDeRuido() {
        String bitsIdentificacaoAlgoritmoEsperado = "0000111100000010";
        String bitsIdentificacaoAlgoritmoRetornado = goulomb.getBitsIdentificacaoAlgoritmo(new Writer());
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoComByteCRC8QuandoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "000011110000001011001101";
        String bitsIdentificacaoAlgoritmoRetornado = goulomb.getBitsIdentificacaoAlgoritmo(new WriterRedundancy());
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }
}