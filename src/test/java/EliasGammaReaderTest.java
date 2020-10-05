import codifications.EliasGamma;
import expections.WrongFormatExpection;
import org.junit.Test;
import utils.Reader;
import utils.Writer;

import java.io.File;
import java.io.IOException;

public class EliasGammaReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException, WrongFormatExpection {
        EliasGamma eliasGamma = new EliasGamma();
        eliasGamma.encode(new Writer("src/main/resources/filesToEncode/arquivoFacil.txt.cod"), new Reader(new File("src/main/resources/filesToEncode/arquivoFacil.txt"), null));
//        eliasGamma.encode(new File("src/main/resources/filesToEncode/sum"));
//    	eliasGamma.encode(new File("src/main/resources/filesToEncode/arquivoFacil.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException, WrongFormatExpection {
        EliasGamma eliasGamma = new EliasGamma();
        eliasGamma.decode(new Writer("src/main/resources/filesToEncode/decode_arquivoFacil.txt"), new Reader(new File("src/main/resources/filesToEncode/arquivoFacil.txt.cod"), null));
//        eliasGamma.decode(new File("src/main/resources/filesEncoded/sum.cod"));
//        eliasGamma.decode(new File("src/main/resources/filesToEncode/arquivoFacil.cod"));
    }
}
