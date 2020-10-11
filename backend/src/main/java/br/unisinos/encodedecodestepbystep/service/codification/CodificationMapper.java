package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.ReaderRedundancy;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;

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
                put("Delta", new Delta());
                put("Elias Gamma", new EliasGamma());
                put("Fibonacci", new Fibonacci());
                put("Goulomb", new Goulomb(-1));
                put("Unario", new Unario());
            }}
    );
    private static final Map<String, Codification> BITS_CODIFICATION_MAP = Collections.unmodifiableMap(
            new HashMap<String, Codification>() {{
                put("0000000100000000", new Delta());
                put("0000001100000000", new EliasGamma());
                put("0000011100000000", new Fibonacci());
                put("0001111100000000", new Unario());
            }}
    );

    Codification getCodificationByStringBits(String bits) {
        return bits.startsWith("00001111")
                ? new Goulomb(Integer.parseInt(bits.substring(8, 16), 2))
                : BITS_CODIFICATION_MAP.get(bits);
    }

    public static Codification getCodificationByStringName(String name, Integer divisor) {
        return NAMES_CODIFICATION_MAP.get(name) instanceof Goulomb
                ? new Goulomb(divisor)
                : NAMES_CODIFICATION_MAP.get(name);
    }

    public static ReaderInterface getReader(String nameRedundancy, File file, JProgressBar jp) throws FileNotFoundException {
        return new HashMap<String, ReaderInterface>() {{
            put("Com tratamento de ruido", new ReaderRedundancy(file, jp));
            put("Sem tratamento de ruido", new Reader(file, jp));
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
