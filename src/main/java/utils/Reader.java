package utils;

import java.io.*;

public class Reader {
    public static final int LENGTH_PROTOCOLO_REMOCAO_BITS = 8;
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
        String binaryAmais = StringUtils.convertToBinaryString(bytesAmais);
        String binary = protoloDeRemocaoDeBits(binaryAmais);

//        System.out.println(Arrays.toString(bytesAmais));
//        System.out.println(Arrays.toString(Writer.toByteArray(binary)));
//        System.out.println(binary);
        return binary;
    }

    private String protoloDeRemocaoDeBits(String binaryAmais){
        String protolo = binaryAmais.substring(binaryAmais.length() - 1 - LENGTH_PROTOCOLO_REMOCAO_BITS);
        int bitsARemover = Integer.parseInt(protolo, 2);
        return binaryAmais.substring(0, binaryAmais.length() - (bitsARemover + LENGTH_PROTOCOLO_REMOCAO_BITS -1));
    }

    public void close() throws IOException {
        fileReader.close();
        bufferedReader.close();
        is.close();
    }

}
