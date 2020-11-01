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
        int i =0;
        int lengthChar = (int) reader.getFile().length();
        int[] values = new int[ lengthChar];

        Map<Integer, Integer> map = setUpCountMap(reader, values, i);
        Map<Integer, Integer> sortedMap = this.sortByValue(map, false);
        Codification.setHuffmanSorted(sortedMap);

        Map<Character, String> huffmanTree = setUpHuffmanTree(sortedMap);

        //writeTreeOnFile(writer, huffmanTree);
        //encodeWithHuffmanTree(writer, huffmanTree, values, i);

        writer.close();
        reader.close();
    }

    private Map<Character, String> setUpHuffmanTree(Map<Integer, Integer> sortedMap){
        Map<Character, String> huffmanTree = new HashMap<Character, String>();

        System.out.println(sortedMap.toString());

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {

        }
        Codification.setHuffmanTree(huffmanTree);
        return huffmanTree;
    }

    private Map<Integer, Integer> setUpCountMap(ReaderInterface reader, int[] values, int i )throws IOException {
        int character = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        while ((character = reader.read()) != -1) {
            values[i++] = character;

            if (map.containsKey(character)) {
                int lastValue = map.get(character) + 1;
                map.put(character, lastValue);
            } else {
                map.put(character, 1);
            }
        }
        return map;
    }

    private void encodeWithHuffmanTree(WriterInterface writer, Map<Character, String> huffmanTree, int[] values, int i) throws IOException, WrongFormatExpection{
        char newLineVariable = '\n';
        String newLineVariableFinal = StringUtils.integerToStringBinary((int) newLineVariable, 8);
        writer.writeWithoutRepository(newLineVariableFinal);

        for (i = 0; i < values.length; i++) {
            int key = values[i];
            writer.write(huffmanTree.get((char)key));
        }
    }

    private void writeTreeOnFile(WriterInterface writer, Map<Character, String> huffmanTree) throws IOException, WrongFormatExpection {
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
    }

    private Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap, final boolean order) {
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
