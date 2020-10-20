package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import br.unisinos.encodedecodestepbystep.service.redundancy.CRCService;
import br.unisinos.encodedecodestepbystep.utils.MathUtils;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EliasGammaService implements CodificationService {

    private static final byte STOP_BIT = 1;

    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        Codification.setCodificationName("Elias Gamma");
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo(writer));

        int character = 0;
        while ((character = reader.read()) != -1) {
            character++;
            if (character == 1) {
                String codewards = "" + STOP_BIT;
                writer.write(codewards);
            } else {
                int unaryNumber = MathUtils.logBase2(character);
                String unaryString = StringUtils.createStreamOnZeros(unaryNumber);
                int rest = (int) (character - (Math.pow(2, unaryNumber)));

                String restInBinary = StringUtils.integerToStringBinary(rest, unaryNumber);

                String codewards = unaryString + STOP_BIT + restInBinary;
//                System.out.println(codewards);
                writer.write(codewards);
            }
        }
        writer.close();
        reader.close();
    }

    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        Codification.setCodificationName("Elias Gamma");
        StringBuilder bitsReaded = new StringBuilder("");
        reader.readCabecalho();// apenas para passar os bits do cabeçalho

        boolean alreadyFoundStopBit = false;
        int prefixLength = 0;
        char character;

        while ((character = (char) reader.readNextChar()) != 65535) {
            bitsReaded.append(character);
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
                    int nextChar = reader.readNextChar();
                    bitsReaded.append((char) nextChar);
                    restInBinary += nextChar - '0';
                }

                int rest = Integer.parseInt(restInBinary, 2);
                char finalNumber = (char) ((int) Math.pow(2, prefixLength) + rest);
                writer.write(--finalNumber, bitsReaded.toString());
                bitsReaded = new StringBuilder("");

                alreadyFoundStopBit = false;
                prefixLength = 0;
            }
        }
        writer.close();
        reader.close();
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo(WriterInterface writer) {
        String firstByte = "00000011"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        if (writer instanceof WriterRedundancy) {
            CRCService crcService = new CRCService();
            String encodedCRC = crcService.calculateCRC8(firstByte, secondByte);
            return firstByte + secondByte + encodedCRC;
        }
        return firstByte + secondByte;
    }
}
