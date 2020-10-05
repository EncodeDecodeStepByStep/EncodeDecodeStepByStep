import codifications.Delta;
import expections.WrongFormatExpection;
import org.junit.Test;
import utils.Reader;
import utils.ReaderRedundancy;
import utils.Writer;
import utils.WriterRedundancy;

import java.io.File;
import java.io.IOException;

public class DeltaTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException, WrongFormatExpection {
        Delta delta = new Delta();
        delta.encode(new Writer("src/main/resources/filesToEncode/arquivoFacil.cod"), new Reader(new File("src/main/resources/filesToEncode/arquivoFacil.txt"), null));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException, WrongFormatExpection {
        Delta delta = new Delta();
        delta.decode(new Writer("src/main/resources/filesToEncode/decode_arquivoFacil.txt"), new Reader(new File("src/main/resources/filesToEncode/arquivoFacil.cod"), null));
    }
}