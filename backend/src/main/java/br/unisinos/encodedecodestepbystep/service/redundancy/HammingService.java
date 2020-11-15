package br.unisinos.encodedecodestepbystep.service.redundancy;


import br.unisinos.encodedecodestepbystep.utils.ErrorWriter;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;

import java.nio.file.Paths;
import java.util.HashMap;

public class HammingService implements Redunduncy {

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

    //    @Override
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

    private static boolean verifyBits(char... args) {
        for (char bit : args)
            if (bit != '0' && bit != '1')
                return true;
        return false;
    }

    //    @Override
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
            ErrorWriter.setFile(Paths.get("").toAbsolutePath().toString()+"\\src\\main\\resources", "hamming.txt");

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

}
