package br.unisinos.encodedecodestepbystep.service.codification;

import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class AssertionsEncodeDecode {

    private static File arquivoEsperado;
    private static File arquivoRetornado;

    public static void makeAssertions() throws IOException {
        arquivoEsperado = new File("src/test/resources/filesToEncodeDecodeTest/alice29.txt");
        arquivoRetornado = new File("src/test/resources/filesToEncodeDecodeTest/decode_alice29.txt");

        InputStream isParaEsperado = new FileInputStream(arquivoEsperado);
        InputStream isParaResultado = new FileInputStream(arquivoRetornado);

        int byteEsperado = 0;
        int byteRetornado = 0;
        while (byteEsperado != -1 && byteRetornado !=-1) {
            byteEsperado = isParaEsperado.read();
            byteRetornado = isParaResultado.read();

            Assertions.assertEquals(byteEsperado, byteRetornado);
        }
    }
}
