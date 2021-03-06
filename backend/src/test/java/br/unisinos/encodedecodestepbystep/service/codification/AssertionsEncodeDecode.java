package br.unisinos.encodedecodestepbystep.service.codification;

import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class AssertionsEncodeDecode {

    private static File arquivoEsperado;
    private static File arquivoRetornado;

    public static void makeAssertions(String file) throws IOException {
        arquivoEsperado = new File("src/test/resources/filesToEncodeDecodeTest/" + file);
        arquivoRetornado = new File("src/test/resources/filesToEncodeDecodeTest/decoded_" + file);

        InputStream isParaEsperado = new FileInputStream(arquivoEsperado);
        InputStream isParaResultado = new FileInputStream(arquivoRetornado);

        if(System.getProperty("os.name").toLowerCase().contains("win") || System.getProperty("os.name").toLowerCase().contains("mac") ) {



        } else

        if(System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux") ||
        System.getProperty("os.name").toLowerCase().contains("aix")) {



        }

        int byteEsperado = 0;
        int byteRetornado = 0;
        while (byteEsperado != -1 && byteRetornado != -1) {
            byteEsperado = isParaEsperado.read();
            byteRetornado = isParaResultado.read();

            Assertions.assertEquals(byteEsperado, byteRetornado);
        }
    }
}
