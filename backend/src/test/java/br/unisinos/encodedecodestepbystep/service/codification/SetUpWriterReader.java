package br.unisinos.encodedecodestepbystep.service.codification;

import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.ReaderRedundancy;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;

import java.io.File;
import java.io.IOException;

public class SetUpWriterReader {

    private static final String PATH_FILES_TO_ENCODE_DECODE_TEST = "src/test/resources/filesToEncodeDecodeTest/";

    private static ReaderWriterWrapper setUpEncode(String fileName) throws IOException {
        WriterInterface writer = new Writer(PATH_FILES_TO_ENCODE_DECODE_TEST + fileName + ".cod");
        ReaderInterface reader = new Reader(new File(PATH_FILES_TO_ENCODE_DECODE_TEST + fileName), null);
        return new ReaderWriterWrapper(reader, writer);
    }

    private static ReaderWriterWrapper setUpDecode(String fileName) throws IOException {
        WriterInterface writer = new Writer(PATH_FILES_TO_ENCODE_DECODE_TEST + "decoded_" + fileName);
        ReaderInterface reader = new Reader(new File(PATH_FILES_TO_ENCODE_DECODE_TEST + fileName + ".cod"), null);
        return new ReaderWriterWrapper(reader, writer);
    }

    private static ReaderWriterWrapper setUpEncodeWithRedundancy(String fileName) throws IOException {
        WriterInterface writer = new WriterRedundancy(PATH_FILES_TO_ENCODE_DECODE_TEST + fileName + ".cod");
        ReaderInterface reader = new ReaderRedundancy(new File(PATH_FILES_TO_ENCODE_DECODE_TEST + fileName), null);
        return new ReaderWriterWrapper(reader, writer);
    }

    private static ReaderWriterWrapper setUpDecodeWithRedundancy(String fileName) throws IOException {
        WriterInterface writer = new WriterRedundancy(PATH_FILES_TO_ENCODE_DECODE_TEST + "decoded_" + fileName);
        ReaderInterface reader = new ReaderRedundancy(new File(PATH_FILES_TO_ENCODE_DECODE_TEST + fileName + ".cod"), null);
        return new ReaderWriterWrapper(reader, writer);
    }

    public static ReaderWriterWrapper setUpEncodeAlice29() throws IOException {
        return setUpEncode("alice29.txt");
    }

    public static ReaderWriterWrapper setUpDecodeAlice29() throws IOException {
        return setUpDecode("alice29.txt");
    }

    public static ReaderWriterWrapper setUpEncodeSum() throws IOException {
        return setUpEncode("sum");
    }

    public static ReaderWriterWrapper setUpDecodeSum() throws IOException {
        return setUpDecode("sum");
    }

    public static ReaderWriterWrapper setUpEncodeWithRedundancyAlice29() throws IOException {
        return setUpEncodeWithRedundancy("alice29.txt");
    }

    public static ReaderWriterWrapper setUpDecodeWithRedundancyAlice29() throws IOException {
        return setUpDecodeWithRedundancy("alice29.txt");
    }

    public static ReaderWriterWrapper setUpEncodeWithRedundancySum() throws IOException {
        return setUpEncodeWithRedundancy("sum");
    }

    public static ReaderWriterWrapper setUpDecodeWithRedundancySum() throws IOException {
        return setUpDecodeWithRedundancy("sum");
    }

}
