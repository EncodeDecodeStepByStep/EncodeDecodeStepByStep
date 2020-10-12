package br.unisinos.encodedecodestepbystep.domain;

import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.ReaderRedundancy;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class ReaderWriterWrapperTest {

    @Test
    void deveRetornarReaderWriterWrapperComRedundancyEPathsCorretosParaEncode() throws IOException {
        String pathEsperadoReader = "src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt";
        String pathEsperadoWriter = "src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod";

        ReaderWriterWrapper readerWriterWrapperRetornado = ReaderWriterWrapper.getEncodeReaderWriterWrapperRedundancy(pathEsperadoReader, new MutableDouble());

        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface() instanceof ReaderRedundancy);
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface() instanceof WriterRedundancy);
        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface().getFile().getAbsolutePath().endsWith(pathEsperadoReader));
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface().getFile().getAbsolutePath().endsWith(pathEsperadoWriter));
    }

    @Test
    void deveRetornarReaderWriterWrapperComRedundancyEPathsCorretosParaDecode() throws IOException {
        String pathEsperadoReader = "src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod";
        String pathEsperadoWriter = "src\\test\\resources\\filesToEncodeDecodeTest\\decoded_alice29.txt";

        ReaderWriterWrapper readerWriterWrapperRetornado = ReaderWriterWrapper.getDecodeReaderWriterWrapperRedundancy(pathEsperadoReader, new MutableDouble());

        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface() instanceof ReaderRedundancy);
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface() instanceof WriterRedundancy);
        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface().getFile().getAbsolutePath().endsWith(pathEsperadoReader));
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface().getFile().getAbsolutePath().endsWith(pathEsperadoWriter));
    }

    @Test
    void deveRetornarReaderWriterWrapperSemRedundancyEPathsCorretosParaEncode() throws IOException {
        String pathEsperadoReader = "src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt";
        String pathEsperadoWriter = "src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod";

        ReaderWriterWrapper readerWriterWrapperRetornado = ReaderWriterWrapper.getEncodeReaderWriterWrapperNormal(pathEsperadoReader, new MutableDouble());

        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface() instanceof Reader);
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface() instanceof Writer);
        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface().getFile().getAbsolutePath().endsWith(pathEsperadoReader));
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface().getFile().getAbsolutePath().endsWith(pathEsperadoWriter));
    }

    @Test
    void deveRetornarReaderWriterWrapperSemRedundancyEPathsCorretosParaDecode() throws IOException {
        String pathEsperadoReader = "src\\test\\resources\\filesToEncodeDecodeTest\\alice29.txt.cod";
        String pathEsperadoWriter = "src\\test\\resources\\filesToEncodeDecodeTest\\decoded_alice29.txt";

        ReaderWriterWrapper readerWriterWrapperRetornado = ReaderWriterWrapper.getDecodeReaderWriterWrapperNormal(pathEsperadoReader, new MutableDouble());

        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface() instanceof Reader);
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface() instanceof Writer);
        Assertions.assertTrue(readerWriterWrapperRetornado.getReaderInterface().getFile().getAbsolutePath().endsWith(pathEsperadoReader));
        Assertions.assertTrue(readerWriterWrapperRetornado.getWriterInterface().getFile().getAbsolutePath().endsWith(pathEsperadoWriter));
    }
}