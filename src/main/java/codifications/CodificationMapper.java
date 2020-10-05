package codifications;

import utils.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class CodificationMapper {

    private static final Map<String, Codification> NAMES_CODIFICATION_MAP = Map.of(
            "Delta", new Delta(),
            "Elias Gamma", new EliasGamma(),
            "Fibonacci", new Fibonacci(),
            "Goulomb", new Goulomb(-1),
            "Unario", new Unario()
    )
    , BITS_CODIFICATION_MAP = Map.of(
            "0000000100000000", new Delta(),
            "0000001100000000", new EliasGamma(),
            "0000011100000000", new Fibonacci(),
            "0001111100000000", new Unario()
    );

    public static Codification getCodificationByStringBits(String bits){
        return bits.startsWith("00001111")
                ? new Goulomb(Integer.parseInt(bits.substring(8,16), 2))
                : BITS_CODIFICATION_MAP.get(bits);
    }

    public static Codification getCodificationByStringName(String name, Integer divisor){
        return NAMES_CODIFICATION_MAP.get(name) instanceof Goulomb
                ? new Goulomb(divisor)
                : NAMES_CODIFICATION_MAP.get(name);
    }

    public static ReaderInterface getReader(String nameRedundancy, File file, JProgressBar jp) throws FileNotFoundException {
        return Map.of(
                "Com tratamento de ruido", new ReaderRedundancy(file, jp),
                "Sem tratamento de ruido", new Reader(file, jp)
                ).get(nameRedundancy);
    }

    public static WriterInterface getWriter(String nameRedundancy, String filePath) throws IOException {
        return Map.of(
                "Com tratamento de ruido", new WriterRedundancy(filePath),
                "Sem tratamento de ruido", new Writer(filePath)
        ).get(nameRedundancy);
    }

}
