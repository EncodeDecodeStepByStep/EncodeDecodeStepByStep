package codifications;

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
                ? new Goulomb(Integer.parseInt("1001", 2))
                : BITS_CODIFICATION_MAP.get(bits);
    }

    public static Codification getCodificationByStringName(String name, Integer divisor){
        return NAMES_CODIFICATION_MAP.get(name) instanceof Goulomb
                ? new Goulomb(divisor)
                : NAMES_CODIFICATION_MAP.get(name);
    }

}
