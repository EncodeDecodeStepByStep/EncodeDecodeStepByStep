package codifications;

import utils.MathUtils;
import utils.StringUtils;
import utils.Writer;
import utils.Reader;
import java.io.*;

import static codifications.Constants.*;

public class EliasGamma implements Codification {

    private static final byte STOP_BIT = 1;

    public EliasGamma(){}

    public void encode(File file) throws IOException {

        Reader reader = new Reader(file);
        Writer writer = new Writer(ENCODED_FOLDER+file.getName()+EXTENSION);
        String bits = "";

        int character = 0;
        while((character = reader.read())!=-1){
        	int unaryNumber = MathUtils.logBase2(character);
        	String unaryString = StringUtils.createStreamOnZeros(unaryNumber);

        	int rest = (int) (character - (Math.pow(2, unaryNumber)));
    		String restInBinary = StringUtils.integerToStringBinary(rest, unaryNumber);

            String codewards = unaryString + STOP_BIT + restInBinary;
            bits = bits.concat(codewards);
            while (bits.length() > 8){
                writer.write(bits.substring(0,8));
                bits = bits.substring(8);
            }
        }
        if(bits.length() != 0){
            writer.write(bits);
        }
        writer.close();
        reader.close();
    }

    public void decode(File file) throws IOException {
        Reader reader = new Reader(file);
        Writer writer = new Writer(DECODED_FOLDER+file.getName());

        boolean alreadyFoundStopBit = false;
        int prefixLength = 0;
        char character;

        while ((character = (char)reader.readNextChar()) != 65535) {
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
                    restInBinary += reader.readNextChar()-'0';
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
