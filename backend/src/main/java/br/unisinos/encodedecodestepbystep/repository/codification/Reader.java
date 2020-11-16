package br.unisinos.encodedecodestepbystep.repository.codification;

import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.SystemUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.apache.commons.lang3.mutable.MutableDouble;

import java.io.*;

public class Reader implements ReaderInterface {
    public static final int LENGTH_PROTOCOLO_REMOCAO_BITS = 8;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private InputStream is;
    private int bytesLidos;
    private long localizacaoByteProtocoloRemocao;
    private double porcentageLida;
    private MutableDouble progressPercentage;
    private String binary;
    private File file;
    private BufferedReader bufferedReaderCodewordsSizeArray;


    public Reader(File file, MutableDouble progressPercentage) throws IOException {
        Codification.setNumberOfCharsTotal(file.length());
        Codification.setMustSaveInCodeword(true);
        if (Codification.isEncodeCodification()) {
            Codification.setFile(file);
        }

        this.file = file;
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(file);
        this.bytesLidos = 0;
        this.localizacaoByteProtocoloRemocao = file.length();
        this.binary = "";
        this.porcentageLida = 0;
        this.progressPercentage = progressPercentage;

        this.bufferedReaderCodewordsSizeArray = new BufferedReader(new FileReader(new File(SystemUtils.getCodewordsRepositoryPath())));
    }

    public Reader() throws IOException {
        Codification.setCharacterCodification("");

        this.file = Codification.getFile();
        this.fileReader = new FileReader(Codification.getFile());
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(Codification.getFile());

        this.bytesLidos = 0;
        this.localizacaoByteProtocoloRemocao = Codification.getFile().length();
        this.binary = "";
        this.porcentageLida = 0;
        this.progressPercentage = new MutableDouble(0);

        this.bufferedReaderCodewordsSizeArray = new BufferedReader(new FileReader(new File(SystemUtils.getCodewordsRepositoryPath())));
    }

    public int read() throws IOException {
        if (this.progressPercentage != null && this.porcentageLida < 100) {
            this.porcentageLida = this.porcentageLida + (double) 100 / this.localizacaoByteProtocoloRemocao;
            if (this.porcentageLida >= 100) {
                this.progressPercentage.setValue(100);
            } else {
                this.progressPercentage.setValue(porcentageLida);
            }
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
        if (this.progressPercentage != null && this.porcentageLida < 100) {
            this.porcentageLida = this.porcentageLida + (double) 100 / this.localizacaoByteProtocoloRemocao;
            if (this.porcentageLida >= 100) {
                this.progressPercentage.setValue(100);
            } else {
                this.progressPercentage.setValue(porcentageLida);
            }
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
//        String protolo = binaryfinal.substring(binaryfinal.length() - 1 - LENGTH_PROTOCOLO_REMOCAO_BITS);
        String protolo = binaryfinal.substring(binaryfinal.length() - LENGTH_PROTOCOLO_REMOCAO_BITS); // TODO testar
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
        for (int i = 0; i < 16; i++) {
            char c = (char) readNextChar();
            binaryString.append(c);
        }

        return binaryString.toString();
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public String readNextStep() throws IOException {
        if (Codification.isEncodeCodification()) {
            int cabecalhoExtra = "Delta".equals(Codification.getCodificationName()) ? 6 : 0;
            this.bufferedReaderCodewordsSizeArray.skip(Codification.getNumberOfCodewordsReaded() + 17 + cabecalhoExtra); // para ignorar o cabeçalho + a virgula
        } else {
            this.bufferedReaderCodewordsSizeArray.skip(Codification.getNumberOfCodewordsReaded());
        }
        this.bufferedReader.skip(Codification.getNumberOfCharsReaded());
        StringBuilder codeword = new StringBuilder("");
        while (true) {
            int charLido = this.bufferedReaderCodewordsSizeArray.read();
            Codification.setNumberOfCodewordsReaded(Codification.getNumberOfCodewordsReaded() + 1);
            if (-1 == charLido) {

                Codification.setStepsFinished(true);
                break;
            }
            if (',' == ((char) charLido)) {
                Codification.setCharacterCodification(String.valueOf((char) this.bufferedReader.read()));
                Codification.setNumberOfCharsReaded(Codification.getNumberOfCharsReaded() + 1);
                break;
            }
            codeword.append((char) charLido);
        }
        return codeword.toString();
    }
}
