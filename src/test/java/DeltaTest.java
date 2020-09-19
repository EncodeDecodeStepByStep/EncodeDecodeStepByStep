import codifications.Delta;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DeltaTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
        Delta delta = new Delta();
        delta.encode(new File("src/main/resources/filesToEncode/alice29.txt"));
//        delta.encode(new File("src/main/resources/filesToEncode/arquivoFacil.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
        Delta delta = new Delta();
        delta.decode(new File("src/main/resources/filesEncoded/alice29.txt.cod"));
//        delta.decode(new File("src/main/resources/filesEncoded/arquivoFacil.txt.cod"));
    }
}