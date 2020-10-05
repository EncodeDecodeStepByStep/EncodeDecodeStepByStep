package utils;

import expections.WrongFormatExpection;

import javax.swing.*;
import java.io.*;

public class Reader implements ReaderInterface{
    public static final int LENGTH_PROTOCOLO_REMOCAO_BITS = 8;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private InputStream is;
    private int bytesLidos;
    private long localizacaoByteProtocoloRemocao;
    private double porcentagemLida;
    private double porcentagemByte;
    private JProgressBar jp;
    private String binary;

    public Reader(File file, JProgressBar jp) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(file);
        this.bytesLidos = 0;
        this.localizacaoByteProtocoloRemocao = file.length();
        this.binary = "";
        this.porcentagemLida = 0;
        this.porcentagemByte = 100 / this.localizacaoByteProtocoloRemocao;
        this.jp = jp;
    }

    public int read() throws IOException {
        if (this.jp != null) {
            this.porcentagemLida = this.porcentagemLida + (double) 100 / this.localizacaoByteProtocoloRemocao;
            this.jp.setValue( (int) porcentagemLida );
        }
        return bufferedReader.read();
    }

    public int readNextChar() throws IOException {
        if (this.binary.length() == 0) {
            updateNextByteOfBinary();
        }
        if ("-1".equals(this.binary)) {
            return -1;
        }
        char nextChar = this.binary.charAt(0);
        this.binary = this.binary.substring(1);
        if (this.jp != null) {
            this.porcentagemLida = this.porcentagemLida + (double) 100 / this.localizacaoByteProtocoloRemocao;
            this.jp.setValue( (int) porcentagemLida );
        }
        return nextChar;
    }

    @Override
    public int readNextCharSemHamming() throws IOException {
        return 0;
    }

    @Override
    public int readNextCharWithHamming() throws IOException, WrongFormatExpection {
        return 0;
    }

    private void updateNextByteOfBinary() throws IOException {
//        byte[] bytesAmais = is.readAllBytes();
        int byteLido = is.read();
        this.bytesLidos++;
        if (this.bytesLidos == (this.localizacaoByteProtocoloRemocao - 1)) {
            int byteProtocoloRemocao = is.read();
            this.binary = this.binary.concat(protoloDeRemocaoDeBits(StringUtils.longToStringBinary(byteLido).
                    concat(StringUtils.longToStringBinary(byteProtocoloRemocao))));

        } else {
            this.binary = byteLido == -1 ? "-1" : this.binary.concat(StringUtils.longToStringBinary(byteLido));
        }
    }

    private String protoloDeRemocaoDeBits(String binaryfinal) {
        String protolo = binaryfinal.substring(binaryfinal.length() - 1 - LENGTH_PROTOCOLO_REMOCAO_BITS);
        int bitsARemover = Integer.parseInt(protolo, 2);
        return binaryfinal.substring(0, binaryfinal.length() - (bitsARemover + LENGTH_PROTOCOLO_REMOCAO_BITS));
    }

    public void close() throws IOException {
        fileReader.close();
        bufferedReader.close();
        is.close();
    }

    public String readCabecalho() throws IOException {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            char c = (char) readNextChar();
            binaryString.append(c);
        }

        return binaryString.toString();
    }
}
