import codifications.EliasGamma;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class EliasGammaReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
    	EliasGamma eliasGamma = new EliasGamma();
    	eliasGamma.encode(new File("src/main/resources/filesToEncode/alice29.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
    	EliasGamma eliasGamma = new EliasGamma();
        eliasGamma.decode(new File("src/main/resources/filesEncoded/alice29.txt.cod"));
    }

}