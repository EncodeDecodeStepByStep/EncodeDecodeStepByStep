package utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Writer {
    public static final int LENGTH_OF_BITS_IN_A_BYTE = 8;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private OutputStream os;

    public Writer(String path) throws IOException {
        File output = new File(path);

        if(output.exists()){
            output.delete();
        }
        this.fileWriter = new FileWriter(output);
        this.bufferedWriter = new BufferedWriter(fileWriter);
        this.os = new FileOutputStream(output);


    }

    public void write(char letter) throws IOException {
        bufferedWriter.write(letter);
    }

    public void write(String bytes) throws IOException {
//        System.out.println(bytes);
//        System.out.println(bytes.length());
        int resto = (bytes.length()%LENGTH_OF_BITS_IN_A_BYTE);
        int divisorMenosResto = LENGTH_OF_BITS_IN_A_BYTE-resto;
        if(resto != 0){
            System.out.println("Resto diferente de zero! Resto = " + resto);

            for (int i = 0; i < divisorMenosResto; i++) {
                bytes = bytes.concat("0");
            }
        }
//        System.out.println(Arrays.toString(toByteArray(bytes)));
        os.write(toByteArray(bytes));
        if(divisorMenosResto != LENGTH_OF_BITS_IN_A_BYTE){
            os.write(toByteArray(StringUtils.integerToStringBinary(divisorMenosResto, LENGTH_OF_BITS_IN_A_BYTE)));
        }
    }
    public static byte[] toByteArray(String input){

        List<String> codewardsSplit= Arrays.asList(input.split("(?<=\\G.{8})"));
        byte[] bitMontados = new byte[codewardsSplit.size()];
        for (int i = 0; i < codewardsSplit.size(); i++) {
            bitMontados[i] = convertBitsToByte(codewardsSplit.get(i));
        }

//        if(!String.format("%8s", Integer.toBinaryString(bitMontados[0] & 0xFF)).replace(' ', '0').equals(input.substring(0,8))){
//            System.out.println("ERROOO");
//        }
        return bitMontados;
    }

    private static byte convertBitsToByte(String bits) {
        return (byte) Integer.parseInt(bits,2);
    }

    public void close() throws IOException {
        bufferedWriter.close();
        fileWriter.close();
        os.close();
    }

    public String gravaBitsEmPartesDe8ERetornaOResto(String bits) throws IOException {
        while (bits.length() > 8){
            write(bits.substring(0,8));
            bits = bits.substring(8);
        }
        return bits;
    }
}
