package utils;

import java.io.*;

public class Reader {
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private InputStream is;

    public Reader(File file) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(file);
    }

    public int read() throws IOException {
        return bufferedReader.read();
    }

    public String readBytes() throws IOException {
        byte[] bytesAmais = is.readAllBytes();
//        System.out.println(Arrays.toString(bytesAmais));
//        byte[] bytes = new byte[bytesAmais.length-4];
//        System.arraycopy(bytesAmais, 4, bytes, 0, bytesAmais.length-4);
        String binary = StringUtils.convertToBinaryString(bytesAmais);

//        System.out.println(Arrays.toString(bytesAmais));
//        System.out.println(Arrays.toString(Writer.toByteArray(binary)));
//        System.out.println(binary);
        return binary;
    }

    public void close() throws IOException {
        fileReader.close();
        bufferedReader.close();
        is.close();
    }

//    public String readWord(int quantity, Reader reader) throws IOException {
//        String codeword ="";
//        for(int i = 0;i<quantity;i++)
//            codeword+=reader.read()-'0';
//        return codeword;
//    }
}
