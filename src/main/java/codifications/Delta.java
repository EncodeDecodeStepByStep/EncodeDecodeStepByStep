package codifications;

import utils.MathUtils;
import utils.Reader;
import utils.StringUtils;
import utils.Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codifications.Constants.*;

public class Delta implements Codification {
    private int biggestIncrease = 0;
    private int biggestDecrease = 0;
    private int quantOfDigits;

    private final int QUANTITY_OF_DIGITS_SIZE = 5;
    private final int FIRST_BINARY_SIZE = 8;
    private final String CHANGED = "1";
    private final String NO_CHANGES = "0";

    private final char NEGATIVE = '1';
    private final char POSITIVE = '0';

    @Override
    public void encode(File file) throws IOException {
        Reader reader = new Reader(file);
        Writer writer = new Writer(ENCODED_FOLDER+file.getName()+EXTENSION);

        int currentCharacter = reader.read();
        int nextCharacter = reader.read();
        List<Integer> characters = new ArrayList<Integer>();
        characters.add(currentCharacter);

        while(nextCharacter!=-1){
            characters.add(nextCharacter);
            updateBiggests(currentCharacter, nextCharacter);
            currentCharacter = nextCharacter;
            nextCharacter = reader.read();
        }

        int biggest = biggestDecrease>biggestIncrease? biggestDecrease : biggestIncrease;
        this.quantOfDigits = (int) Math.ceil(MathUtils.logBase2ToDouble(biggest));

        String quantityOfDigits = StringUtils.integerToStringBinary(this.quantOfDigits+1, QUANTITY_OF_DIGITS_SIZE);
        writeWord(quantityOfDigits, writer);

        currentCharacter = characters.get(0);
        String firstNumberInBinary = StringUtils.integerToStringBinary(currentCharacter, FIRST_BINARY_SIZE);

        writeWord(firstNumberInBinary, writer);

        for (int i = 1; i<characters.size();i++){
            nextCharacter = characters.get(i);

            int difference = Math.abs(currentCharacter - nextCharacter);
            String codeword = NO_CHANGES;
            if(difference!=0){
                char signal =  POSITIVE;
                if(currentCharacter>nextCharacter){
                    signal = NEGATIVE;
                }
                codeword = CHANGED + signal + StringUtils.integerToStringBinary(difference-1, this.quantOfDigits);
            }
            currentCharacter = nextCharacter;

            writeWord(codeword, writer);
        }
        writer.close();
        reader.close();
    }

    private void updateBiggests(int currentCharacter, int nextCharacter){
        int difference = Math.abs(currentCharacter-nextCharacter);
        if(currentCharacter>nextCharacter){
            if(difference>biggestDecrease){
                biggestDecrease = difference;
            }
        }else if(currentCharacter<nextCharacter){
            if(difference>biggestIncrease){
                biggestIncrease = difference;
            }
        }
    }

    @Override
    public void decode(File file) throws IOException {
        Reader reader = new Reader(file);
        Writer writer = new Writer(DECODED_FOLDER+file.getName());

        String quantOfDigitsString = readWord(QUANTITY_OF_DIGITS_SIZE, reader);
        this.quantOfDigits = Integer.parseInt(quantOfDigitsString,2);

        String firstNumberString = readWord(FIRST_BINARY_SIZE, reader);
        char firstNumber = (char) Integer.parseInt(firstNumberString,2);
        char lastCharacter = firstNumber;

        writer.write(firstNumber);

        int character =  reader.read();
        while(character!=-1){
            if(character!='0'){
                String codeword = readWord(quantOfDigits, reader);
                lastCharacter =  discoverCharacter(codeword, lastCharacter);
            }
            writer.write(lastCharacter);
            character = reader.read();
        }

        writer.close();
        reader.close();
    }

    private char discoverCharacter(String codeword, char lastSimbol){
        char signal = codeword.charAt(0);
        String restOfString = codeword.substring(1);
        int difference = Integer.parseInt(restOfString,2) + 1;
        return signal==NEGATIVE ? (char)(lastSimbol-difference) : (char)(lastSimbol+difference);
    }

    //TODO Passar isso para o reader
    public String readWord(int quantity, Reader reader) throws IOException {
        String codeword ="";
        for(int i = 0;i<quantity;i++)
            codeword+=reader.read()-'0';
        return codeword;
    }

    //TODO Passar isso para o writer
    public void writeWord(String codeword, Writer writer) throws  IOException{
        for (int charToSave : codeword.toCharArray())
            writer.write((char) charToSave);
    }
}
