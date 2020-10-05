package codifications;

import expections.WrongFormatExpection;
import utils.*;

import java.io.File;
import java.io.IOException;

import static codifications.Constants.*;

public class EliasGamma implements Codification {

    private static final byte STOP_BIT = 1;

    public EliasGamma() {
    }

    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo());

        int character = 0;
        while ((character = reader.read()) != -1) {
            int unaryNumber = MathUtils.logBase2(character);
            String unaryString = StringUtils.createStreamOnZeros(unaryNumber);

            int rest = (int) (character - (Math.pow(2, unaryNumber)));
            String restInBinary = StringUtils.integerToStringBinary(rest, unaryNumber);

            String codewards = unaryString + STOP_BIT + restInBinary;
//            System.out.print(codewards);
            writer.write(codewards);
        }
        writer.close();
        reader.close();
    }

    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        reader.readCabecalho();// apenas para passar os bits do cabeçalho

        boolean alreadyFoundStopBit = false;
        int prefixLength = 0;
        char character;

        while ((character = (char) reader.readNextChar()) != 65535) {
            if (!alreadyFoundStopBit && (character - '0') == STOP_BIT) {
                alreadyFoundStopBit = true;
            } else {
                if (!alreadyFoundStopBit) {
                    prefixLength++;
                    continue;
                }

                String restInBinary = "";
                restInBinary += character;
                for (int i = 1; i < prefixLength; i++) {
                    restInBinary += reader.readNextChar() - '0';
                }

                int rest = Integer.parseInt(restInBinary, 2);
                char finalNumber = (char) ((int) Math.pow(2, prefixLength) + rest);
                writer.write(finalNumber);

                alreadyFoundStopBit = false;
                prefixLength = 0;
            }
        }
        writer.close();
        reader.close();
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo() {
        return "00000011" + //identificaçãoAlgoritmo
                "00000000"; // informação extra goloumb
    }
}
