package utils;

import expections.WrongFormatExpection;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Writer implements WriterInterface{
    public static final int LENGTH_OF_BITS_IN_A_BYTE = 8;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private OutputStream os;
    private String bitsStringControle;


    public Writer(String path) throws IOException {
        File output = new File(path);

        if (output.exists()) {
            output.delete();
        }
        this.fileWriter = new FileWriter(output);
        this.bufferedWriter = new BufferedWriter(fileWriter);
        this.os = new FileOutputStream(output);
        this.bitsStringControle = "";
    }

    public void write(char letter) throws IOException {
        bufferedWriter.write(letter);
    }

    public void write(String bits) throws IOException {
        this.bitsStringControle = bitsStringControle.concat(bits);

        while(bitsStringControle.length() >= 8) {
            write8bitsOrConcatZerosToComplete(this.bitsStringControle.substring(0, 8));
            this.bitsStringControle = this.bitsStringControle.substring(8);
        }
    }

    public void write8bitsOrConcatZerosToComplete(String bytes) throws IOException {
        int resto = (bytes.length() % LENGTH_OF_BITS_IN_A_BYTE);
        int divisorMenosResto = LENGTH_OF_BITS_IN_A_BYTE - resto;
        if (resto != 0) {
            for (int i = 0; i < divisorMenosResto; i++) {
                bytes = bytes.concat("0");
            }
        }
        os.write(toByteArray(bytes));
        System.out.print(bytes);
        if (divisorMenosResto != LENGTH_OF_BITS_IN_A_BYTE) {
            os.write(toByteArray(StringUtils.integerToStringBinary(divisorMenosResto, LENGTH_OF_BITS_IN_A_BYTE)));
            System.out.print(StringUtils.integerToStringBinary(divisorMenosResto, LENGTH_OF_BITS_IN_A_BYTE));
        }
    }

    @Override
    public void writeSemHamming(String bits) throws IOException, WrongFormatExpection {
        write(bits);
    }

    public static byte[] toByteArray(String input) {

        List<String> codewardsSplit = Arrays.asList(input.split("(?<=\\G.{8})"));
        byte[] bitMontados = new byte[codewardsSplit.size()];
        for (int i = 0; i < codewardsSplit.size(); i++) {
            bitMontados[i] = convertBitsToByte(codewardsSplit.get(i));
        }
        return bitMontados;
    }

    private static byte convertBitsToByte(String bits) {
        return (byte) Integer.parseInt(bits, 2);
    }

    public void close() throws IOException {
        if(this.bitsStringControle.length() > 0){
            write8bitsOrConcatZerosToComplete(bitsStringControle);
            this.bitsStringControle = "";
        }
        bufferedWriter.close();
        fileWriter.close();
        os.close();
    }

    public String gravaBitsEmPartesDe8ERetornaOResto(String bits) throws IOException {
        while (bits.length() > 8) {
            write(bits.substring(0, 8));
            bits = bits.substring(8);
        }
        return bits;
    }
}