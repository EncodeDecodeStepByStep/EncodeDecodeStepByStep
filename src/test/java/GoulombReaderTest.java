import codifications.Goulomb;
import expections.WrongFormatExpection;
import org.junit.Test;
import utils.Reader;
import utils.Writer;

import java.io.File;
import java.io.IOException;

public class GoulombReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException, WrongFormatExpection {
        Goulomb goulomb = new Goulomb(32);
        goulomb.encode(new Writer("src/main/resources/filesToEncode/arquivoFacil.cod"), new Reader(new File("src/main/resources/filesToEncode/arquivoFacil.txt"), null));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException, WrongFormatExpection {
        Goulomb goulomb = new Goulomb(32);
        goulomb.decode(new Writer("src/main/resources/filesToEncode/decode_arquivoFacil.txt"), new Reader(new File("src/main/resources/filesToEncode/arquivoFacil.cod"), null));
    }
}
