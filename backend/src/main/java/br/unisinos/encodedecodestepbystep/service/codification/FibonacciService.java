package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.repository.redundancy.WriterRedundancy;
import br.unisinos.encodedecodestepbystep.service.redundancy.CRCService;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FibonacciService implements CodificationService {
    private int[] fibonacci_sequence;

    public FibonacciService() {
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

    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo(writer));

        int character = 0;
        while ((character = reader.read()) != -1) {
            String encodingBinary = this.getFibonacciEncoding(character);
            writer.write(encodingBinary);
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

    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
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
    public String getBitsIdentificacaoAlgoritmo(WriterInterface writer) {
        String firstByte = "00000111"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        if (writer instanceof WriterRedundancy) {
            CRCService crcService = new CRCService();
            String encodedCRC = crcService.calculateCRC8(firstByte, secondByte);
            return firstByte + secondByte + encodedCRC;
        }
        return firstByte + secondByte;
    }
}
