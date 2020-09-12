package codifications;

import utils.MathUtils;
import utils.StringUtils;
import utils.Writer;
import utils.Reader;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import static codifications.Constants.*;

public class EliasGamma implements Codification {

    private static final byte STOP_BIT = 1;

    public EliasGamma(){}

    public void encode(File file) throws IOException {

        Reader reader = new Reader(file);
        Writer writer = new Writer(ENCODED_FOLDER+file.getName()+EXTENSION);
        String bits = "";

        int character = 0;
        while((character = reader.readString())!=-1){
        	int unaryNumber = MathUtils.logBase2(character);
        	String unaryString = StringUtils.createStreamOnZeros(unaryNumber);

        	int rest = (int) (character - (Math.pow(2, unaryNumber)));
    		String restInBinary = StringUtils.integerToStringBinary(rest, unaryNumber);

            String codewards = unaryString + STOP_BIT + restInBinary;
            bits = bits.concat(codewards);
//            writer.write(codewards);
        }
        writer.write(bits);
        writer.close();
        reader.close();
    }

    public void decode(File file) throws IOException {
        Reader reader = new Reader(file);
        Writer writer = new Writer(DECODED_FOLDER+file.getName());

        boolean alreadyFoundStopBit = false;
        int prefixLength = 0;
        byte[] bytesAmais = reader.readByte();
        System.out.println(Arrays.toString(bytesAmais));
//        byte[] bytes = new byte[bytesAmais.length-4];

//        System.arraycopy(bytesAmais, 4, bytes, 0, bytesAmais.length-4);
        String binary = StringUtils.convertToBinaryString(bytesAmais);

        System.out.println(Arrays.toString(bytesAmais));
        System.out.println(Arrays.toString(Writer.toByteArray(binary)));
        System.out.println(binary);

        for (int count = 0; count < binary.length(); count++) {
            char character = binary.charAt(count);
            System.out.println("a"+character);
        	if (!alreadyFoundStopBit && (character-'0') == STOP_BIT) {
        		alreadyFoundStopBit = true;
        	} else {
        		if (!alreadyFoundStopBit) {
        			prefixLength++;
        			continue;
        		}

        		String restInBinary = "";
                restInBinary += character;
                for (int i = 1; i < prefixLength; i++) {
                    restInBinary += binary.charAt(++count)-'0';
                    System.out.println("b"+restInBinary);

                }
                
                int rest = Integer.parseInt(restInBinary,2);
                char finalNumber = (char) ((int) Math.pow(2, prefixLength) + rest);
                writer.write(finalNumber);
                
                alreadyFoundStopBit = false;
                prefixLength = 0;
        	}
        }
        writer.close();
        reader.close();
    }
}
