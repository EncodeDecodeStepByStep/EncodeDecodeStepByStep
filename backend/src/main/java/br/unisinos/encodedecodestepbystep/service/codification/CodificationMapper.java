package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.repository.codification.Writer;
import br.unisinos.encodedecodestepbystep.repository.redundancy.ReaderRedundancy;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CodificationMapper {

    private DeltaService deltaService;

    private EliasGammaService eliasGammaService;

    private FibonacciService fibonacciService;

    private GoulombService goulombService;

    private UnarioService unarioService;

    private UnarioService huffmanService;

    private Map<String, CodificationService> namesCodificationMap = new HashMap<>();
    private Map<String, CodificationService> bitsCodificationMap = new HashMap<>();

    @Autowired
    public CodificationMapper(DeltaService deltaService,
                              EliasGammaService eliasGammaService,
                              FibonacciService fibonacciService,
                              GoulombService goulombService,
                              UnarioService unarioService) {
        this.deltaService = deltaService;
        this.eliasGammaService = eliasGammaService;
        this.fibonacciService = fibonacciService;
        this.goulombService = goulombService;
        this.unarioService = unarioService;

        namesCodificationMap.put("Delta", deltaService);
        namesCodificationMap.put("Elias Gamma", eliasGammaService);
        namesCodificationMap.put("Fibonacci", fibonacciService);
        namesCodificationMap.put("Goulomb", goulombService);
        namesCodificationMap.put("Unario", unarioService);
        namesCodificationMap.put("Huffman", huffmanService);

        bitsCodificationMap.put("0000000100000000", deltaService);
        bitsCodificationMap.put("0000001100000000", eliasGammaService);
        bitsCodificationMap.put("0000011100000000", fibonacciService);
        bitsCodificationMap.put("0001111100000000", unarioService);
        bitsCodificationMap.put("0011111100000000", huffmanService);

    }

    public CodificationService getCodificationByStringName(String name, Integer divisor) {
        return namesCodificationMap.get(name) instanceof GoulombService
                ? new GoulombService(divisor)
                : namesCodificationMap.get(name);
    }

//    public static ReaderInterface getReader(String nameRedundancy, File file, MutableDouble progressPercentage) throws FileNotFoundException {
//        return new HashMap<String, ReaderInterface>() {{
//            put("Com tratamento de ruido", new ReaderRedundancy(file, progressPercentage));
//            put("Sem tratamento de ruido", new Reader(file, progressPercentage));
//        }}.get(nameRedundancy);
//    }
//
//    public static WriterInterface getWriter(String nameRedundancy, String filePath) throws IOException {
//        return new HashMap<String, WriterInterface>() {
//            {
//                put("Com tratamento de ruido", new WriterRedundancy(filePath));
//                put("Sem tratamento de ruido", new Writer(filePath));
//            }
//        }.get(nameRedundancy);
//    }

    public CodificationService getCodificationByStringBits(String bits) {
        if (bits.startsWith("00001111")){
            this.goulombService.setDivisor(Integer.parseInt(bits.substring(8, 16), 2));
            return this.goulombService;
        }
        return bitsCodificationMap.get(bits);
    }
}
