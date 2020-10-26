package br.unisinos.encodedecodestepbystep.service.codification;

import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class HuffmanService implements CodificationService {

    @Override
    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        Codification.setCodificationName("Huffman Estático");
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo(writer));

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<Character, Double> probabilityMap = new HashMap<Character, Double>();
        int character = 0;
        int i = 0;
        long lengthChar = reader.getFile().length();
        int[] values = new int[(int) lengthChar];

        while ((character = reader.read()) != -1) {
            double probability = 0;
            values[i++] = character;

            if (map.containsKey(character)) {
                int lastValue = map.get(character) + 1;
                probability = lastValue / (double) lengthChar;
                map.put(character, lastValue);
                probabilityMap.put((char) character, probability);
            } else {
                map.put(character, 1);
                probability = 1 / (double) lengthChar;
                probabilityMap.put((char) character, probability);
            }
        }

        Map<Integer, Integer> sortedMap = this.sortByValue(map, false);
        Codification.setHuffmanSorted(sortedMap);

        boolean newLine = false;
        int lengthEncode = 0;
        Map<Character, String> huffmanTree = new HashMap<Character, String>();
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int key = entry.getKey();

            if (!newLine) {
                huffmanTree.put((char) key, "0");
                newLine = true;
            } else {
                if ((lengthEncode+1) == sortedMap.size()) {
                    huffmanTree.put((char) key, StringUtils.createStreamWithOnes(lengthEncode));
                } else {
                    huffmanTree.put((char) key, StringUtils.createStreamWithOnes(lengthEncode) + "0");
                }
            }
            lengthEncode++;
        }

        Codification.setHuffmanTree(huffmanTree);

        char doisPontos = ':';
        String doisPontosFinal = StringUtils.integerToStringBinary((int) doisPontos, 8);
        char virgula = ',';
        String virgulaFinal = StringUtils.integerToStringBinary((int) virgula, 8);
        // Write the tree on file
        for (Map.Entry<Character, String> entry : huffmanTree.entrySet()) {
            char key = entry.getKey();
            String keyFinal = StringUtils.integerToStringBinary((int) key, 8);

            String valueFinal = "";
            for (char c : entry.getValue().toCharArray()) {
                valueFinal += StringUtils.integerToStringBinary((int) c, 8);
            }

            writer.writeWithoutRepository(keyFinal + doisPontosFinal + valueFinal + virgulaFinal);
        }

        char newLineVariable = '\n';
        String newLineVariableFinal = StringUtils.integerToStringBinary((int) newLineVariable, 8);
        writer.writeWithoutRepository(newLineVariableFinal);

        for (i = 0; i < values.length; i++) {
            int key = values[i];
            writer.write(huffmanTree.get((char)key));
        }

        writer.close();
        reader.close();
    }

    private Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap, final boolean order)
    {
        List<Entry<Integer, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    @Override
    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        Codification.setCodificationName("Huffman Estático");
        reader.readCabecalho();// apenas para passar os bits do cabeçalho
        char character;

        Map<String, Character> huffmanTree = new HashMap<String, Character>();

        char key = '0';
        String value = "";
        boolean isKey = true;
        while ((character = (char) reader.read()) != 65535) {
            if (character == '\n') break;

            if (isKey) {
                if (character == ':') {
                    isKey = false;
                } else {
                    key = character;
                }
            } else {
                if (character == ',') {
                    huffmanTree.put(value, key);
                    isKey = true;
                    value = "";
                } else {
                    value += character;
                }
            }
        }

        StringBuilder bitsReaded = new StringBuilder("");
        while ((character = (char) reader.readNextChar()) != 65535) {
            bitsReaded.append(character);
            if (character == '0' || huffmanTree.containsKey(bitsReaded.toString())) {
                if (huffmanTree.containsKey(bitsReaded.toString())) {
                    char huffmanValue = huffmanTree.get(bitsReaded.toString());
                    writer.write(huffmanValue, bitsReaded.toString());
                }
                bitsReaded = new StringBuilder("");
            }
        }

        writer.close();
        reader.close();
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo(WriterInterface writer) {
        String firstByte = "00111111"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        return firstByte + secondByte;
    }
}
