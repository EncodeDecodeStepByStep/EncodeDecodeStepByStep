package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import br.unisinos.encodedecodestepbystep.service.redundancy.CRCService;
import br.unisinos.encodedecodestepbystep.utils.MathUtils;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeltaService implements Codification {
    private int biggestIncrease = 0;
    private int biggestDecrease = 0;
    private int quantOfDigits;

    private static final int QUANTITY_OF_DIGITS_SIZE = 5;
    private static final int FIRST_BINARY_SIZE = 16;
    private static final String CHANGED = "1";
    private static final String NO_CHANGES = "0";

    private static final char NEGATIVE = '1';
    private static final char POSITIVE = '0';


    @Override
    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo(writer));
        //writer.write(CRC.calculateCRC8(getBitsIdentificacaoAlgoritmo().substring(0,8), getBitsIdentificacaoAlgoritmo().substring(8)));
//        String bits = "";

        int currentCharacter = reader.read();
        int nextCharacter = reader.read();
        List<Integer> characters = new ArrayList<Integer>();
        characters.add(currentCharacter);

        while (nextCharacter != -1) {
            characters.add(nextCharacter);
            updateBiggests(currentCharacter, nextCharacter);
            currentCharacter = nextCharacter;
            nextCharacter = reader.read();
        }

        int biggest = Math.max(biggestDecrease, biggestIncrease);
        this.quantOfDigits = (int) Math.ceil(MathUtils.logBase2ToDouble(biggest));

        String quantityOfDigits = StringUtils.integerToStringBinary(this.quantOfDigits + 1, QUANTITY_OF_DIGITS_SIZE);
        writer.write(quantityOfDigits);

        currentCharacter = characters.get(0);
        String firstNumberInBinary = StringUtils.integerToStringBinary(currentCharacter, FIRST_BINARY_SIZE);

        writer.write(firstNumberInBinary);

        for (int i = 1; i < characters.size(); i++) {
            nextCharacter = characters.get(i);

            int difference = Math.abs(currentCharacter - nextCharacter);
            String codeword = NO_CHANGES;
            if (difference != 0) {
                char signal = POSITIVE;
                if (currentCharacter > nextCharacter) {
                    signal = NEGATIVE;
                }
                codeword = CHANGED + signal + StringUtils.integerToStringBinary(difference - 1, this.quantOfDigits);
            }
            currentCharacter = nextCharacter;

            writer.write(codeword);
        }
        writer.close();
        reader.close();
    }

    private void updateBiggests(int currentCharacter, int nextCharacter) {
        int difference = Math.abs(currentCharacter - nextCharacter);
        if (currentCharacter > nextCharacter) {
            if (difference > biggestDecrease) {
                biggestDecrease = difference;
            }
        } else if (currentCharacter < nextCharacter) {
            if (difference > biggestIncrease) {
                biggestIncrease = difference;
            }
        }
    }

    @Override
    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        reader.readCabecalho();// apenas para passar os bits do cabeçalho

        String quantOfDigitsString = getCodeword(QUANTITY_OF_DIGITS_SIZE, reader);
        this.quantOfDigits = Integer.parseInt(quantOfDigitsString, 2);

        String firstNumberString = getCodeword(FIRST_BINARY_SIZE, reader);
        char firstNumber = (char) Integer.parseInt(firstNumberString, 2);
        char lastCharacter = firstNumber;

        writer.write(firstNumber);

        int character = reader.readNextChar();
        while (character != -1) {
            if (character != '0') {
                String codeword = getCodeword(quantOfDigits, reader);
                lastCharacter = discoverCharacter(codeword, lastCharacter);
            }
            writer.write(lastCharacter);
            character = reader.readNextChar();
        }

        writer.close();
        reader.close();
    }

    private char discoverCharacter(String codeword, char lastSimbol) {
        char signal = codeword.charAt(0);
        String restOfString = codeword.substring(1);
        int difference = Integer.parseInt(restOfString, 2) + 1;
        return signal == NEGATIVE ? (char) (lastSimbol - difference) : (char) (lastSimbol + difference);
    }

    private String getCodeword(int quantity, ReaderInterface reader) throws IOException, WrongFormatExpection {
        String codeword = "";
        for (int i = 0; i < quantity; i++)
            codeword += reader.readNextChar() - '0';
        return codeword;
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo(WriterInterface writer) {
        String firstByte = "00000001"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        if (writer instanceof WriterRedundancy) {
            CRCService crcService = new CRCService();
            String encodedCRC = crcService.calculateCRC8(firstByte, secondByte);
            return firstByte + secondByte + encodedCRC;
        }
        return firstByte + secondByte;
    }
}
