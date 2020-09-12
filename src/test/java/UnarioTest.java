import codifications.EliasGamma;
import codifications.Unario;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UnarioTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
    	Unario unario = new Unario();
        unario.encode(new File("src/main/resources/filesToEncode/alice29.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
        Unario unario = new Unario();
        unario.decode(new File("src/main/resources/filesEncoded/alice29.txt.cod"));
    }

}
