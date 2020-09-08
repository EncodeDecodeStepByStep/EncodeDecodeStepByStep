import codifications.Delta;
import codifications.Goulomb;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DeltaTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
        Delta delta = new Delta();
        delta.encode(new File("src/main/resources/filesToEncode/alice29.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
        Delta delta = new Delta();
        delta.decode(new File("src/main/resources/filesEncoded/alice29.txt.cod"));
    }

}
