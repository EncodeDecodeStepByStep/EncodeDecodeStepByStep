import codifications.Goulomb;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GoulombReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
        Goulomb goulomb = new Goulomb(32);
        goulomb.encode(new File("src/main/resources/filesToEncode/arquivoFacil.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
        Goulomb goulomb = new Goulomb(32);
        goulomb.decode(new File("src/main/resources/filesEncoded/arquivoFacil.txt.cod"));
    }

}
