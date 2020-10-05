package codifications;

import redunduncy.CRC;
import utils.Reader;
import utils.StringUtils;
import utils.Writer;
import expections.WrongFormatExpection;
import utils.*;

import java.io.File;
import java.io.IOException;

import static codifications.Constants.*;

public class Unario implements Codification {
    @Override
    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo());

        int character = reader.read();
        int bit = 0;

        while (character != -1) {
            String codeword = "";
            if (bit == 0) {
                codeword = StringUtils.createStreamOnZeros(character);
                bit = 1;
            } else {
                codeword = StringUtils.createStreamWithOnes(character);
                bit = 0;
            }
            writer.write(codeword);
            character = reader.read();
        }
        reader.close();
        writer.close();
    }

    @Override
    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        reader.readCabecalho();// apenas para passar os bits do cabeçalho

        int bitRead = reader.readNextChar();
        int last = bitRead;
        int counter = 0;

        while (bitRead != -1) {
            bitRead = reader.readNextChar();
            counter++;
            if (bitRead != last) {
                char character = (char) counter;
                writer.write(character);
                last = bitRead;
                counter = 0;
            }
        }

        reader.close();
        writer.close();
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo() {
        String firstByte = "00011111"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        CRC crc = new CRC();
        String encodedCRC = crc.calculateCRC8(firstByte, secondByte);
        return firstByte + secondByte + encodedCRC;
    }
}
