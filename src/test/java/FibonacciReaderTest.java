import codifications.Fibonacci;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FibonacciReaderTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.encode(new File("src/main/resources/filesToEncode/alice29.txt"));
//    	fibonacci.encode(new File("src/main/resources/filesToEncode/arquivoFacil.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.decode(new File("src/main/resources/filesEncoded/alice29.txt.cod"));
//    	fibonacci.decode(new File("src/main/resources/filesEncoded/arquivoFacil.txt.cod"));
    }

}
