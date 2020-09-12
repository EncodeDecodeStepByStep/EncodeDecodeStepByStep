import codifications.EliasGamma;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class EliasGammaReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
    	EliasGamma eliasGamma = new EliasGamma();
    	eliasGamma.encode(new File("src/main/resources/filesToEncode/alice29.txt"));
//    	eliasGamma.encode(new File("src/main/resources/filesToEncode/arquivoFacil.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
    	EliasGamma eliasGamma = new EliasGamma();
        eliasGamma.decode(new File("src/main/resources/filesEncoded/alice29.txt.cod"));
//        eliasGamma.decode(new File("src/main/resources/filesEncoded/arquivoFacil.txt.cod"));
    }
}

//[6, 32, -60, 24, -125, 16, 98, 12, 65, -112]
//25seg - primeira versao
//1seg - segunda versao