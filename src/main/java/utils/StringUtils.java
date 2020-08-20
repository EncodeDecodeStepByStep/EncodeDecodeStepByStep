package utils;

public class StringUtils {
    public static String integerToStringBinary(int integer, int finalLengthOfBinary){
        String binary = Integer.toBinaryString(integer);
        //It´ll prepend n zeros until the string have the size of binary especified on the second parameter
        while(binary.length() != finalLengthOfBinary){
            binary = "0" + binary;
        }
        return binary;
    }

    public static String createStreamOnZeros(int howManyZeros){
        // Enter 3 -> Return 000
        // Enter 7 -> Return 0000000
        return new String(new char[howManyZeros]).replace("\0", "0");
    }

}
