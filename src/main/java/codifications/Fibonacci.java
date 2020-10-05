package codifications;

import redunduncy.CRC;
import utils.Reader;
import utils.Writer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static codifications.Constants.*;

public class Fibonacci implements Codification {
    private int[] fibonacci_sequence;

    public Fibonacci() {
        this.fibonacci_sequence = this.createStructureFibonacci();
    }

    private int[] createStructureFibonacci() {
        int[] structure = new int[12];
        structure[0] = 1;
        structure[1] = 2;
        for (int i = 2; i < structure.length; i++) {
            structure[i] = structure[i - 1] + structure[i - 2];
        }
        return structure;
    }

    public void encode(File file, JProgressBar jp) throws IOException {

        Reader reader = new Reader(file, jp);
        Writer writer = new Writer(file.getParentFile().getAbsolutePath()+ "\\" + file.getName() + EXTENSION);
        writer.write(getBitsIdentificacaoAlgoritmo());
        String bits = "";

        int character = 0;
        while ((character = reader.read()) != -1) {
            String encodingBinary = this.getFibonacciEncoding(character);
            bits = bits.concat(encodingBinary);
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

    private String getFibonacciEncoding(int n) {
        n++;

        String binary = "1";
        boolean isFirstOccurrence = false;
        int necessaryLength = 0;

        for (int i = this.fibonacci_sequence.length - 1; i >= 0; i--) {
            if (n == 0) break;

            if (this.fibonacci_sequence[i] <= n) {
                if (necessaryLength == 0) {
                    necessaryLength = i + 1;
                }
                binary = "1" + binary;
                n = n - this.fibonacci_sequence[i];
                isFirstOccurrence = true;
            } else if (isFirstOccurrence) {
                binary = "0" + binary;
            }
        }

        for (int i = necessaryLength - (binary.length() - 1); i > 0; i--) {
            binary = "0" + binary;
        }
        return binary;
    }

    public void decode(File file, JProgressBar jp) throws IOException {
        Reader reader = new Reader(file, jp);
        Writer writer = new Writer(file.getParentFile().getAbsolutePath()+ "\\decoded_" + file.getName().replaceFirst("[.][^.]+$", ""));

        reader.readCabecalho();// apenas para passar os bits do cabeçalho
        String storedOccurrence = "";
        char numberOne = Integer.toString(1).charAt(0);

        char number;
        while ((number = (char) reader.readNextChar()) != 65535) {
            char lastChar = storedOccurrence.length() > 0 ? storedOccurrence.charAt(storedOccurrence.length() - 1) : 0;

            if (lastChar == numberOne && number == numberOne) {
                int ascii = this.decodeStringFibonacci(storedOccurrence);
                char teste = (char) ascii;
                writer.write(--teste);
                storedOccurrence = "";
            } else {
                storedOccurrence = storedOccurrence + number;
            }
        }

        writer.close();
        reader.close();
    }

    private int decodeStringFibonacci(String codeword) {
        char numberOne = Integer.toString(1).charAt(0);
        int total = 0;

        for (int i = 0; i < this.fibonacci_sequence.length && i < codeword.length(); i++) {
            if (codeword.charAt(i) == numberOne) {
                total += this.fibonacci_sequence[i];
            }
        }

        return total;
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo() {
        String firstByte = "00000111"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        CRC crc = new CRC();
        String encodedCRC = crc.calculateCRC8(firstByte, secondByte);
        return firstByte + secondByte + encodedCRC;
    }
}
