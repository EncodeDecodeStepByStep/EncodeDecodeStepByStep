package codifications;

import expections.WrongFormatExpection;
import utils.*;

import java.io.IOException;


public class Goulomb implements Codification {

    private static final byte STOP_BIT = 1;

    private int divisor; //K

    public Goulomb(int divisor) {
        this.divisor = divisor;
    }

    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo());

        int character = 0;
        while ((character = reader.read()) != -1) {
            int restOfDivision = character % this.divisor;
            int digitsToRepresentTheRest = MathUtils.logBase2(this.divisor);
            String restBinary = StringUtils.integerToStringBinary(restOfDivision, digitsToRepresentTheRest);

            int division = character / this.divisor;
            String zeros = StringUtils.createStreamOnZeros(division);
            String codewards = zeros + STOP_BIT + restBinary;
            writer.write(codewards);
        }
        writer.close();
        reader.close();
    }

    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
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
        return "00001111" + //identificaçãoAlgoritmo
                StringUtils.integerToStringBinary(divisor, 8); // informação extra divisor goloumb
    }
}
