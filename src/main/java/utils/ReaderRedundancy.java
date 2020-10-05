package utils;

import expections.WrongFormatExpection;
import redunduncy.Hamming;

import javax.swing.*;
import java.io.*;

public class ReaderRedundancy implements ReaderInterface {
    public static final int LENGTH_PROTOCOLO_REMOCAO_BITS = 8;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private InputStream is;
    private long bytesLidos;
    private long localizacaoByteProtocoloRemocao;
    private String binary;
    private String binaryComHammingAplicado;
    private double porcentagemLida;
    private double porcentagemByte;
    private JProgressBar jp;

    public ReaderRedundancy(File file, JProgressBar jp) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(file);
        this.bytesLidos = 0;
        this.localizacaoByteProtocoloRemocao = file.length();
        this.binary = "";
        this.binaryComHammingAplicado = "";
        this.porcentagemLida = 0;
        this.porcentagemByte = 100 / this.localizacaoByteProtocoloRemocao;
        this.jp = jp;
    }

    public int read() throws IOException {
        return bufferedReader.read();
    }

    public int readNextChar() throws IOException, WrongFormatExpection {
        int nextChar = readNextCharWithHamming();
        while (nextChar == -2){

            nextChar = readNextCharWithHamming();
        }
        return nextChar;
    }

    public int readNextCharSemHamming() throws IOException {
        if (this.binary.length() == 0) {
            updateNextByteOfBinary();
        }
        if ("-1".equals(this.binary)) {
            return -1;
        }
        char nextChar = this.binary.charAt(0);
        this.binary = this.binary.substring(1);
        return nextChar;
    }

    public int readNextCharWithHamming() throws IOException, WrongFormatExpection {
        if(this.binary.length() >= 7 && !this.binary.endsWith("-1") && !this.binary.contains("2") && !this.binary.contains("3")) {
            this.binaryComHammingAplicado = this.binaryComHammingAplicado.concat(Hamming.getValue(this.binary.substring(0,7)));
            this.binary = this.binary.substring(7);

            char nextChar = this.binaryComHammingAplicado.charAt(0);
            this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
            return nextChar;
        } else {
            if(this.binary.contains("2") || this.binary.contains("3")){
                int indiceMenor2Ou3 = Math.min(this.binary.contains("3") ? this.binary.indexOf("3") : 9, this.binary.contains("2") ? this.binary.indexOf("2") : 9);
                String substringSemHamming = this.binary.substring(indiceMenor2Ou3);
                this.binary = this.binary.substring(0, indiceMenor2Ou3);

                while (this.binary.length() >= 7){
                    this.binaryComHammingAplicado = this.binaryComHammingAplicado.concat(Hamming.getValue(this.binary.substring(0,7)));
                    this.binary = this.binary.substring(7);
                }

                substringSemHamming = substringSemHamming.replace("2", "0");
                substringSemHamming = substringSemHamming.replace("3", "1");
                this.binaryComHammingAplicado = this.binaryComHammingAplicado.concat(substringSemHamming);

                char nextChar = this.binaryComHammingAplicado.charAt(0);
                this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
                return nextChar;
            }
            if(this.binary.endsWith("-1")){
                if(this.binaryComHammingAplicado.length() > 0){
                    char nextChar = this.binaryComHammingAplicado.charAt(0);
                    this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
                    return nextChar;
                } else {
                    if (this.binary.length() > 2){ // pois n queremos retornar a string "-1"
                        char nextChar = this.binary.charAt(0);
                        this.binary = this.binary.substring(1);
                        return nextChar;
                    } else {
//                        System.out.println("Binary" + this.binary);
//                        System.out.println("BinaryHamming" + this.binaryComHammingAplicado);
                        this.binaryComHammingAplicado = "";
                        this.binary = "";
                        return -1;
                    }
                }
            } else {
                updateNextByteOfBinary();
                if(this.binaryComHammingAplicado.length() > 0) {
                    char nextChar = this.binaryComHammingAplicado.charAt(0);
                    this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
                    return nextChar;
                } else {
                    return -2;
                }
            }
        }
    }

    private void updateNextByteOfBinary() throws IOException {
        int byteLido = is.read();
        this.bytesLidos++;
        if (this.bytesLidos == (this.localizacaoByteProtocoloRemocao - 2)) {
            int ultimoBytePoluido = is.read();
            int byteProtocoloRemocao = is.read();
//            System.out.println("\nbyteLido"+StringUtils.longToStringBinary(byteLido));
//            System.out.println("byteProtocoloRemocao"+StringUtils.longToStringBinary(byteProtocoloRemocao));
            this.binary = this.binary.concat(protoloDeRemocaoDeBits(StringUtils.longToStringBinary(byteLido)
                    .concat(StringUtils.longToStringBinary(ultimoBytePoluido))
                    .concat(StringUtils.longToStringBinary(byteProtocoloRemocao))));

        } else {
            this.binary = byteLido == -1 ? "-1" : this.binary.concat(StringUtils.longToStringBinary(byteLido));
        }
    }

    private String protoloDeRemocaoDeBits(String binaryfinal) {
        String protolo = binaryfinal.substring(binaryfinal.length() - LENGTH_PROTOCOLO_REMOCAO_BITS);
        int bitsARemover = Integer.parseInt(protolo.substring(2), 2);
        int qtdBitsAIgnorarHamming = Integer.parseInt(protolo.substring(0,2), 2);
//        System.out.println("binaryfinal:"+binaryfinal);
        String bitsRemovidos = binaryfinal.substring(0, binaryfinal.length() - (bitsARemover + LENGTH_PROTOCOLO_REMOCAO_BITS));

//        System.out.println("bitsRemovidos qtd:"+qtdBitsAIgnorarHamming);
//        System.out.println("bitsRemovidos antes:"+bitsRemovidos);
        String bitsAIgnorarHamming = bitsRemovidos.substring(bitsRemovidos.length() - qtdBitsAIgnorarHamming);
        bitsRemovidos = bitsRemovidos.substring(0, bitsRemovidos.length() - qtdBitsAIgnorarHamming);
//        System.out.println(bitsAIgnorarHamming + " " + bitsRemovidos);
        bitsAIgnorarHamming = bitsAIgnorarHamming.replace("0", "2");
        bitsAIgnorarHamming = bitsAIgnorarHamming.replace("1", "3");
        return bitsRemovidos.concat(bitsAIgnorarHamming);
    }

    public void close() throws IOException {
        fileReader.close();
        bufferedReader.close();
        is.close();
    }

    public String readCabecalho() throws IOException, WrongFormatExpection {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            binaryString.append((char) readNextCharSemHamming());
        }
        return binaryString.toString(); //TODO pro cabeçalho com CRC
    }
}
