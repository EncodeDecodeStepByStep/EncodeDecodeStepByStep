package br.unisinos.encodedecodestepbystep.service.codification;

import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HuffmanServiceTest {

    WriterInterface writer;
    ReaderInterface reader;
    HuffmanService huffmanService;

    @BeforeEach
    void setUp() {
        this.huffmanService = new HuffmanService();
    }

    void setUp(ReaderWriterWrapper readerWriterWrapper) {
        this.writer = readerWriterWrapper.getWriterInterface();
        this.reader = readerWriterWrapper.getReaderInterface();
    }

//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmTxt() throws IOException, WrongFormatExpection {
//        Codification.setEncodeCodification(true);
//        setUp(setUpEncodeAlice29());
//        huffmanService.encode(this.writer, this.reader);
//
//        Codification.setEncodeCodification(false);
//        setUp(setUpDecodeAlice29());
//        huffmanService.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }
//
//    @Test
//    void deveFicarIgualOArquivoOriginalQuandoExecutadoEncodeEAposDecodeDeUmExecutavel() throws IOException, WrongFormatExpection {
//        Codification.setEncodeCodification(true);
//        setUp(setUpEncodeSum());
//        huffmanService.encode(this.writer, this.reader);
//
//        Codification.setEncodeCodification(false);
//        setUp(setUpDecodeSum());
//        huffmanService.decode(this.writer, this.reader);
//
//        makeAssertions();
//    }

    @Test
    void deveRetornarIdentificaoEmBitsDoAlgoritmoSemByteCRC8QuandoNaoUtilizadoTratamentoDeRuido() throws IOException {
        String bitsIdentificacaoAlgoritmoEsperado = "0011111100000000";
        String bitsIdentificacaoAlgoritmoRetornado = huffmanService.getBitsIdentificacaoAlgoritmo(new Writer("src/test/resources/filesToEncodeDecodeTest/alice29.txt.cod"));
        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
    }

//    @Test
//    void deveRetornarIdentificaoEmBitsDoAlgoritmoComByteCRC8QuandoUtilizadoTratamentoDeRuido() throws IOException {
//        String bitsIdentificacaoAlgoritmoEsperado = "000000110000000000111111";
//        String bitsIdentificacaoAlgoritmoRetornado = huffmanService.getBitsIdentificacaoAlgoritmo(new WriterRedundancy("src/test/resources/filesToEncodeDecodeTest/alice29.txt.cod"));
//        Assertions.assertEquals(bitsIdentificacaoAlgoritmoEsperado, bitsIdentificacaoAlgoritmoRetornado);
//    }
}