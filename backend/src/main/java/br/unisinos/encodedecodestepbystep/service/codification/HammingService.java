package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import br.unisinos.encodedecodestepbystep.service.redundancy.CRCService;
import br.unisinos.encodedecodestepbystep.utils.ErrorWriter;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class HammingService implements CodificationService {

    private static final int OUT_LENGTH = 7;
    private static final int IN_LENGTH = 4;

    private static final HashMap<String, Integer> zTable = new HashMap<String, Integer>() {
        {
            put("000", 0);
            put("001", 7);
            put("010", 6);
            put("011", 4);
            put("100", 5);
            put("101", 1);
            put("110", 2);
            put("111", 3);
        }
    };

    public static String introduceRedunduncy(String bytes) throws WrongFormatExpection {
        if (bytes.length() != IN_LENGTH) {
            return bytes;
        }
        //1110101
        char firstBit = bytes.charAt(0);
        char secondBit = bytes.charAt(1);
        char thirdBit = bytes.charAt(2);
        char fourthBit = bytes.charAt(3);

        if (verifyBits(firstBit, secondBit, thirdBit, fourthBit)) {
            throw new WrongFormatExpection("A mensagem não estava em binário");
        }

        int fifthBit = (firstBit + secondBit + thirdBit) % 2;
        int sixthBit = (secondBit + thirdBit + fourthBit) % 2;
        int seventhBit = (firstBit + thirdBit + fourthBit) % 2;

        return bytes + fifthBit + sixthBit + seventhBit;
    }

    public static String getValue(String bytes) throws WrongFormatExpection {
        if (bytes.length() != OUT_LENGTH) {
            return bytes;
        }

        char firstBit = bytes.charAt(0);
        char secondBit = bytes.charAt(1);
        char thirdBit = bytes.charAt(2);
        char fourthBit = bytes.charAt(3);
        char fifthBit = bytes.charAt(4);
        char sixth = bytes.charAt(5);
        char seventh = bytes.charAt(6);

        if (verifyBits(firstBit, secondBit, thirdBit, fourthBit, fifthBit, sixth, seventh)) {
            throw new WrongFormatExpection("A mensagem não estava em binário");
        }

        boolean fifthIsRight = String.valueOf((firstBit + secondBit + thirdBit) % 2).equals(String.valueOf(fifthBit));
        boolean sixthIsRight = String.valueOf((secondBit + thirdBit + fourthBit) % 2).equals(String.valueOf(sixth));
        boolean seventhIsRight = String.valueOf((firstBit + thirdBit + fourthBit) % 2).equals(String.valueOf(seventh));

        String firstZ = fifthIsRight ? "0" : "1";
        String secondZ = sixthIsRight ? "0" : "1";
        String thirdZ = seventhIsRight ? "0" : "1";

        String z = firstZ + secondZ + thirdZ;

        String value = String.valueOf(new char[]{firstBit, secondBit, thirdBit, fourthBit});

        if (!z.equals("000")) {
            int binaryWithProblem = zTable.get(z);

            int binaryWithProblemIndex = binaryWithProblem - 1;

            ErrorWriter errorWriter = ErrorWriter.getInstance();

            if (binaryWithProblemIndex < 4) {
                errorWriter.write("Encontrado um erro no " + (binaryWithProblemIndex + 1) + "º bit do codeword hamming");
                char[] valueChars = value.toCharArray();
                char wrongBinary = value.charAt(binaryWithProblem - 1);

                valueChars[binaryWithProblem - 1] = wrongBinary == '0' ? '1' : '0';
                value = String.valueOf(valueChars);
            } else {
                errorWriter.write("Encontrado um erro critico no " + (binaryWithProblemIndex + 1) + "º bit, não foi possivel corrigir");
                throw new WrongFormatExpection("Erro critico no bit " + (binaryWithProblemIndex + 1) + " em hamming");
            }
        }

        return value;
    }

    private static boolean verifyBits(char... args) {
        for (char bit : args)
            if (bit != '0' && bit != '1')
                return true;
        return false;
    }

    @Override
    public void encode(WriterInterface writer, ReaderInterface reader)throws IOException, WrongFormatExpection  {
        Codification.setCodificationName("Hamming");
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo(writer));

        int character = 0;

        String word = "";
        while (character != -1) {
            if(word.length()<IN_LENGTH){
                character = reader.read();
                String newWord =  StringUtils.integerToStringBinary(character,8);
                word+=newWord;
            }
            String wordFourBits = word.substring(0,IN_LENGTH);
            word = word.substring(IN_LENGTH);
            String codeword = introduceRedunduncy(wordFourBits);
            System.out.println(codeword);
            writer.write(codeword);

        }
        writer.write(word);

        reader.close();
        writer.close();
    }


    @Override
    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {

    }

    @Override
    public String getBitsIdentificacaoAlgoritmo(WriterInterface writer) {
        String firstByte = "00011111"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        if (writer instanceof WriterRedundancy) {
            CRCService crcService = new CRCService();
            String encodedCRC = crcService.calculateCRC8(firstByte, secondByte);
            return firstByte + secondByte + encodedCRC;
        }
        return firstByte + secondByte;
    }
}
