package br.unisinos.encodedecodestepbystep.utils;

public class StringUtils {
    public static String integerToStringBinary(int integer, int finalLengthOfBinary) {
        String binary = Integer.toBinaryString(integer);
        //It´ll prepend n zeros until the string have the size of binary especified on the second parameter
        while (binary.length() != finalLengthOfBinary) {
            binary = "0" + binary;
        }
        return binary;
    }

    public static String longToStringBinary(long longer) {
        String binary = Long.toBinaryString(longer);
        //It´ll prepend n zeros until the string have the size of binary especified on the second parameter
        while (binary.length() != 8) {
            binary = "0" + binary;
        }
        return binary;
    }

    public static String createStreamOnZeros(int howManyZeros) {
        // howManyZeros 3 -> Return 000
        // howManyZeros 7 -> Return 0000000
        return new String(new char[howManyZeros]).replace("\0", "0");
    }

    public static String createStreamWithOnes(int quantityOfOnes) {
        // howManyZeros 3 -> Return 111
        // howManyZeros 7 -> Return 1111111
        return new String(new char[quantityOfOnes]).replace("\0", "1");
    }

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public static String convertToBinaryString(byte[] bytes) {
        StringBuilder binaryBuilder = new StringBuilder();
        for (Byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binaryBuilder.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binaryBuilder.toString();
    }

    public static String trativaPrimeiros8bits(String binaryString) {
        return "00000000".concat(binaryString.substring(8));
    }
}
