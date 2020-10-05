import codifications.EliasGamma;
import expections.WrongFormatExpection;
import org.junit.Test;
import utils.Reader;
import utils.ReaderRedundancy;
import utils.Writer;
import utils.WriterRedundancy;

import java.io.File;
import java.io.IOException;

public class EliasGammaReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException, WrongFormatExpection {
        EliasGamma eliasGamma = new EliasGamma();
        eliasGamma.encode(new WriterRedundancy("src/main/resources/filesToEncode/alice29.txt.cod"), new ReaderRedundancy(new File("src/main/resources/filesToEncode/alice29.txt"), null));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException, WrongFormatExpection {
        EliasGamma eliasGamma = new EliasGamma();
        eliasGamma.decode(new WriterRedundancy("src/main/resources/filesToEncode/decode_alice29.txt"), new ReaderRedundancy(new File("src/main/resources/filesToEncode/alice29.txt.cod"), null));
    }
}
