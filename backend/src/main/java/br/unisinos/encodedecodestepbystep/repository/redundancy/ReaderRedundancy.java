package br.unisinos.encodedecodestepbystep.repository.redundancy;

import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.service.codification.HammingService;
import br.unisinos.encodedecodestepbystep.service.redundancy.CRCService;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.apache.commons.lang3.mutable.MutableDouble;

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
    private double porcentageLida;
    private MutableDouble progressPercentage;
    private File file;

    public ReaderRedundancy(File file, MutableDouble progressPercentage) throws FileNotFoundException {
        this.file = file;
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(file);
        this.bytesLidos = 0;
        this.localizacaoByteProtocoloRemocao = file.length();
        this.binary = "";
        this.binaryComHammingAplicado = "";
        this.porcentageLida = 0;
        this.progressPercentage = progressPercentage;
    }

    public int read() throws IOException {
        return bufferedReader.read();
    }

    public int readNextChar() throws IOException, WrongFormatExpection {
        int nextChar = readNextCharWithHamming();
        while (nextChar == -2) {

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
        if (this.binary.length() >= 7 && !this.binary.endsWith("-1") && !this.binary.contains("2") && !this.binary.contains("3")) {
            this.binaryComHammingAplicado = this.binaryComHammingAplicado.concat(HammingService.getValue(this.binary.substring(0, 7)));
            this.binary = this.binary.substring(7);

            char nextChar = this.binaryComHammingAplicado.charAt(0);
            this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
            return nextChar;
        } else {
            if (this.binary.contains("2") || this.binary.contains("3")) {
                int indiceMenor2Ou3 = Math.min(this.binary.contains("3") ? this.binary.indexOf("3") : 9, this.binary.contains("2") ? this.binary.indexOf("2") : 9);
                String substringSemHamming = this.binary.substring(indiceMenor2Ou3);
                this.binary = this.binary.substring(0, indiceMenor2Ou3);

                while (this.binary.length() >= 7) {
                    this.binaryComHammingAplicado = this.binaryComHammingAplicado.concat(HammingService.getValue(this.binary.substring(0, 7)));
                    this.binary = this.binary.substring(7);
                }

                substringSemHamming = substringSemHamming.replace("2", "0");
                substringSemHamming = substringSemHamming.replace("3", "1");
                this.binaryComHammingAplicado = this.binaryComHammingAplicado.concat(substringSemHamming);

                char nextChar = this.binaryComHammingAplicado.charAt(0);
                this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
                return nextChar;
            }
            if (this.binary.endsWith("-1")) {
                if (this.binaryComHammingAplicado.length() > 0) {
                    char nextChar = this.binaryComHammingAplicado.charAt(0);
                    this.binaryComHammingAplicado = this.binaryComHammingAplicado.substring(1);
                    return nextChar;
                } else {
                    if (this.binary.length() > 2) { // pois n queremos retornar a string "-1"
                        char nextChar = this.binary.charAt(0);
                        this.binary = this.binary.substring(1);
                        return nextChar;
                    } else {
                        this.binaryComHammingAplicado = "";
                        this.binary = "";
                        return -1;
                    }
                }
            } else {
                updateNextByteOfBinary();
                if (this.binaryComHammingAplicado.length() > 0) {
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
        int qtdBitsAIgnorarHamming = Integer.parseInt(protolo.substring(0, 2), 2);
        String bitsRemovidos = binaryfinal.substring(0, binaryfinal.length() - (bitsARemover + LENGTH_PROTOCOLO_REMOCAO_BITS));

        String bitsAIgnorarHamming = bitsRemovidos.substring(bitsRemovidos.length() - qtdBitsAIgnorarHamming);
        bitsRemovidos = bitsRemovidos.substring(0, bitsRemovidos.length() - qtdBitsAIgnorarHamming);
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
        CRCService crcService = new CRCService();

        StringBuilder binaryStringFirstByte = new StringBuilder();
        StringBuilder binaryStringSecondByte = new StringBuilder();
        StringBuilder binaryStringThirdByte = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            binaryStringFirstByte.append((char) readNextChar());
        }
        for (int i = 0; i < 8; i++) {
            binaryStringSecondByte.append((char) readNextChar());
        }
        for (int i = 0; i < 8; i++) {
            binaryStringThirdByte.append((char) readNextChar());
        }
        String decodedCrc = crcService.calculateCRC8(binaryStringFirstByte.toString(), binaryStringSecondByte.toString());
        if (decodedCrc.equals(binaryStringThirdByte)) {
            return "true";
        }

        return "false";
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public String readNextStep() throws IOException {
        //TODO
        return null;
    }
}
