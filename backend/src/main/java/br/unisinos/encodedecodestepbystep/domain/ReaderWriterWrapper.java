package br.unisinos.encodedecodestepbystep.domain;

import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.ReaderRedundancy;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.mutable.MutableDouble;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
public class ReaderWriterWrapper {

    private ReaderInterface readerInterface;
    private WriterInterface writerInterface;

    public static ReaderWriterWrapper getEncodeReaderWriterWrapperRedundancy(String path, MutableDouble progressPercentage) throws IOException {
        return new ReaderWriterWrapper(new ReaderRedundancy(new File(path), progressPercentage), new WriterRedundancy(path + ".cod"));
    }

    public static ReaderWriterWrapper getDecodeReaderWriterWrapperRedundancy(String path, MutableDouble progressPercentage) throws IOException {
        return new ReaderWriterWrapper(new ReaderRedundancy(new File(path), progressPercentage),
                new WriterRedundancy(path.substring(0, path.lastIndexOf("\\") + 1) + "decoded_" + path.substring(path.lastIndexOf("\\") + 1).replaceFirst("(?s)(.*)" + "[.][^.]+$", "$1" + "")));
    }

    public static ReaderWriterWrapper getEncodeReaderWriterWrapperNormal(String path, MutableDouble progressPercentage) throws IOException {
        return new ReaderWriterWrapper(new Reader(new File(path), progressPercentage), new Writer(path + ".cod"));
    }

    public static ReaderWriterWrapper getDecodeReaderWriterWrapperNormal(String path, MutableDouble progressPercentage) throws IOException {
        return new ReaderWriterWrapper(new Reader(new File(path), progressPercentage),
                new Writer(path.substring(0, path.lastIndexOf("\\") + 1) + "decoded_" + path.substring(path.lastIndexOf("\\") + 1).replaceFirst("(?s)(.*)" + "[.][^.]+$", "$1" + "")));
    }

}
