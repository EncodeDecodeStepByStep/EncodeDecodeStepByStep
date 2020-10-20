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
import static br.unisinos.encodedecodestepbystep.service.codification.SetUpWriterReader.*;

class EliasGammaServiceTest {

    WriterInterface writer;
    ReaderInterface reader;
    EliasGammaService eliasGammaService;

    @BeforeEach
    void setUp() {
        this.eliasGammaService = new EliasGammaService();
    }

    void setUp(ReaderWriterWrapper readerWriterWrapper) {
        this.writer = readerWriterWrapper.getWriterInterface();
        this.reader = readerWriterWrapper.getReaderInterface();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
        Codification.setEncodeCodification(true);
        setUp(setUpEncodeAlice29());
        eliasGammaService.encode(this.writer, this.reader);

        Codification.setEncodeCodification(false);
        setUp(setUpDecodeAlice29());
        eliasGammaService.decode(this.writer, this.reader);

        makeAssertions();
    }

    @Test
    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
        Codification.setEncodeCodification(true);
        setUp(setUpEncodeSum());
        eliasGammaService.encode(this.writer, this.reader);

        Codification.setEncodeCodification(false);
        setUp(setUpDecodeSum());
        eliasGammaService.decode(this.writer, this.reader);

        makeAssertions();
    }

//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoComTratamentoDeRuidoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
//        setUp(setUpEncodeWithRedundancyAlice29());
//        eliasGamma.encode(this.writer, this.reader);
//
//        setUp(setUpDecodeWithRedundancyAlice29());
//        eliasGamma.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }
//
//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoComTratamentoDeRuidoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
//        setUp(setUpEncodeWithRedundancySum());
//        eliasGamma.encode(this.writer, this.reader);
//
//        setUp(setUpDecodeWithRedundancySum());
//        eliasGamma.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoSemByteCRC8QuandoNaoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "0000001100000000";
        String bitsIdentificacaoAlgoritmoRetornado = eliasGammaService.getBitsIdentificacaoAlgoritmo(new Writer("src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod"));
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoComByteCRC8QuandoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "000000110000000000111111";
        String bitsIdentificacaoAlgoritmoRetornado = eliasGammaService.getBitsIdentificacaoAlgoritmo(new WriterRedundancy("src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod"));
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }
}