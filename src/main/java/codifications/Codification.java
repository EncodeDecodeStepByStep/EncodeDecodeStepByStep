package codifications;

import expections.WrongFormatExpection;
import utils.Reader;
import utils.ReaderInterface;
import utils.Writer;
import utils.WriterInterface;

import java.io.File;
import java.io.IOException;

public interface Codification {


    void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection;

    void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection;

    String getBitsIdentificacaoAlgoritmo(WriterInterface writer);
}
