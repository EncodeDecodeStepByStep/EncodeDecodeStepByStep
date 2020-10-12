package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.ReaderRedundancy;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import org.apache.commons.lang3.mutable.MutableDouble;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CodificationMapper {

    private static final Map<String, Codification> NAMES_CODIFICATION_MAP = Collections.unmodifiableMap(
            new HashMap<String, Codification>() {{
                put("Delta", new DeltaService());
                put("Elias Gamma", new EliasGammaService());
                put("Fibonacci", new FibonacciService());
                put("Goulomb", new GoulombService(-1));
                put("Unario", new UnarioService());
            }}
    );
    private static final Map<String, Codification> BITS_CODIFICATION_MAP = Collections.unmodifiableMap(
            new HashMap<String, Codification>() {{
                put("0000000100000000", new DeltaService());
                put("0000001100000000", new EliasGammaService());
                put("0000011100000000", new FibonacciService());
                put("0001111100000000", new UnarioService());
            }}
    );

    Codification getCodificationByStringBits(String bits) {
        return bits.startsWith("00001111")
                ? new GoulombService(Integer.parseInt(bits.substring(8, 16), 2))
                : BITS_CODIFICATION_MAP.get(bits);
    }

    public static Codification getCodificationByStringName(String name, Integer divisor) {
        return NAMES_CODIFICATION_MAP.get(name) instanceof GoulombService
                ? new GoulombService(divisor)
                : NAMES_CODIFICATION_MAP.get(name);
    }

    public static ReaderInterface getReader(String nameRedundancy, File file, MutableDouble progressPercentage) throws FileNotFoundException {
        return new HashMap<String, ReaderInterface>() {{
            put("Com tratamento de ruido", new ReaderRedundancy(file, progressPercentage));
            put("Sem tratamento de ruido", new Reader(file, progressPercentage));
        }}.get(nameRedundancy);
    }

    public static WriterInterface getWriter(String nameRedundancy, String filePath) throws IOException {
        return  new HashMap<String, WriterInterface>() {
            {
                put("Com tratamento de ruido", new WriterRedundancy(filePath));
                put("Sem tratamento de ruido", new Writer(filePath));
            }}.get(nameRedundancy);
    }

}
