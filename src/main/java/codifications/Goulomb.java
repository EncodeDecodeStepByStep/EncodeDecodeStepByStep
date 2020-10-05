package codifications;

import redunduncy.CRC;
import utils.MathUtils;
import utils.Reader;
import utils.StringUtils;
import utils.Writer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static codifications.Constants.*;

public class Goulomb implements Codification {

    private static final byte STOP_BIT = 1;

    private int divisor; //K

    public Goulomb(int divisor) {
        this.divisor = divisor;
    }

    public void encode(File file, JProgressBar jp) throws IOException {

        Reader reader = new Reader(file, jp);
        System.out.println(file.getAbsolutePath() + file.getName() + EXTENSION);
        Writer writer = new Writer(file.getParentFile().getAbsolutePath()+ "\\" + file.getName() + EXTENSION);
        writer.write(getBitsIdentificacaoAlgoritmo());
        String bits = "";

        int character = 0;
        while ((character = reader.read()) != -1) {
            int restOfDivision = character % this.divisor;
            int digitsToRepresentTheRest = MathUtils.logBase2(this.divisor);
            String restBinary = StringUtils.integerToStringBinary(restOfDivision, digitsToRepresentTheRest);

            int division = character / this.divisor;
            String zeros = StringUtils.createStreamOnZeros(division);
            String codewards = zeros + STOP_BIT + restBinary;
            bits = bits.concat(codewards);
            while (bits.length() > 8) {
                writer.write(bits.substring(0, 8));
                bits = bits.substring(8);
            }
        }
        if (bits.length() != 0) {
            writer.write(bits);
        }
        writer.close();
        reader.close();
    }

    public void decode(File file, JProgressBar jp) throws IOException {
        Reader reader = new Reader(file, jp);
        Writer writer = new Writer(file.getParentFile().getAbsolutePath()+ "\\decoded_" + file.getName().replaceFirst("[.][^.]+$", ""));
        reader.readCabecalho();// apenas para passar os bits do cabeçalho

        boolean alreadyFoundStopBit = false;

        int digitsOnRest = MathUtils.logBase2(this.divisor);
        int quocient = 0;
        char character;

        while ((character = (char) reader.readNextChar()) != 65535) {
            if (!alreadyFoundStopBit) {
                if ((character - '0') == STOP_BIT) {
                    alreadyFoundStopBit = true;
                } else {
                    quocient++;
                }
            } else {
                String restInBinary = "";
                restInBinary += character;
                for (int i = 1; i < digitsOnRest; i++) {
                    restInBinary += reader.readNextChar() - '0';
                }
                int rest = Integer.parseInt(restInBinary, 2);
                char finalNumber = (char) ((quocient * this.divisor) + rest);
                writer.write(finalNumber);
                quocient = 0;
                alreadyFoundStopBit = false;
            }
        }
        writer.close();
        reader.close();
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo() {
        String firstByte = "00001111"; //identificaçãoAlgoritmo
        String secondByte = StringUtils.integerToStringBinary(divisor, 8); // informação extra goloumb
        CRC crc = new CRC();
        String encodedCRC = crc.calculateCRC8(firstByte, secondByte);
        return firstByte + secondByte + encodedCRC;
    }
}
