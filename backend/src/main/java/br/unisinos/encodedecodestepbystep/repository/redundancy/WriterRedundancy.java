package br.unisinos.encodedecodestepbystep.repository.redundancy;

import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.service.codification.HammingService;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class WriterRedundancy implements WriterInterface {
    public static final int LENGTH_OF_BITS_IN_A_BYTE = 8;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private OutputStream os;
    private String bitsStringControle;
    private String bitsStringControleHamming;
    private File output;

    public WriterRedundancy(String path) throws IOException {
        output = new File(path);
        Codification.setFile(output);

        if (output.exists()) {
            output.delete();
        }
        this.fileWriter = new FileWriter(output);
        this.bufferedWriter = new BufferedWriter(fileWriter);
        this.os = new FileOutputStream(output);
        this.bitsStringControle = "";
        this.bitsStringControleHamming = "";
    }

    public static byte[] toByteArray(String input) {

        List<String> codewardsSplit = Arrays.asList(input.split("(?<=\\G.{8})"));
        byte[] bitMontados = new byte[codewardsSplit.size()];
        for (int i = 0; i < codewardsSplit.size(); i++) {
            bitMontados[i] = convertBitsToByte(codewardsSplit.get(i));
        }
        return bitMontados;
    }

    private static byte convertBitsToByte(String bits) {
        return (byte) Integer.parseInt(bits, 2);
    }

    public void write(char letter, String bitsReaded) throws IOException {
        bufferedWriter.write(letter);
    }

    public void write(String bits) throws IOException, WrongFormatExpection {
        this.bitsStringControle = bitsStringControle.concat(bits);
        updateBitStringHamming();

        while (bitsStringControleHamming.length() >= 8) {
            write8bitsOrConcatZerosToComplete(this.bitsStringControleHamming.substring(0, 8), null);
            this.bitsStringControleHamming = this.bitsStringControleHamming.substring(8);
        }
    }

    public void writeWithoutRepository(String bits) throws IOException, WrongFormatExpection {
        return;
    }

    public void writeSemHamming(String bits) throws IOException, WrongFormatExpection {
        write8bitsOrConcatZerosToComplete(bits, null);
    }

    private void updateBitStringHamming() throws WrongFormatExpection {
        while (bitsStringControle.length() >= 4) {
            this.bitsStringControleHamming = this.bitsStringControleHamming.concat(HammingService.introduceRedunduncy(bitsStringControle.substring(0, 4)));
            this.bitsStringControle = this.bitsStringControle.substring(4);
        }
    }

    private void write8bitsOrConcatZerosToComplete(String bits, Integer qntdBitsSemHammingNoFinal) throws IOException {
        int resto = (bits.length() % LENGTH_OF_BITS_IN_A_BYTE);
        int divisorMenosResto = LENGTH_OF_BITS_IN_A_BYTE - resto;
        if (resto != 0) {
            for (int i = 0; i < divisorMenosResto; i++) {
                bits = bits.concat("0");
            }
        }
        os.write(toByteArray(bits));
        if (divisorMenosResto != LENGTH_OF_BITS_IN_A_BYTE) {
            String bitsParaNaoFazerHamming = StringUtils.integerToStringBinary(qntdBitsSemHammingNoFinal, 2);
            String bitsParaDescartarNoDecode = StringUtils.integerToStringBinary(divisorMenosResto, LENGTH_OF_BITS_IN_A_BYTE - 2);
            os.write(toByteArray(bitsParaNaoFazerHamming.concat(bitsParaDescartarNoDecode)));
        }
    }

    public void close() throws IOException {
        if (bitsStringControle.length() > 0 || bitsStringControleHamming.length() > 0) {
            write8bitsOrConcatZerosToComplete(bitsStringControleHamming.concat(bitsStringControle), bitsStringControle.length());
            bitsStringControle = "";
            bitsStringControleHamming = "";
        }
        bufferedWriter.close();
        fileWriter.close();
        os.close();
    }

//     //TODO escrever arquivo intermediario
//    private void writeCodewordsSizeForStepsTempFile(Integer codewordSize) throws IOException {
//        this.fileWriterCodewordsSizeArray.write(codewordSize.toString().concat(","));
//    }

    @Override
    public File getFile() {
        return output;
    }
}
