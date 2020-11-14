package br.unisinos.encodedecodestepbystep.service.redundancy;

import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

class HammingServiceTest {
    HammingService hammingService = new HammingService();
    HashMap<String, String> hammingTable = new HashMap<String, String>();

    @BeforeEach
    void setUp(){
        hammingTable.put("0000","0000000");
        hammingTable.put("0001","0001011");
        hammingTable.put("0010","0010111");
        hammingTable.put("0011","0011100");
        hammingTable.put("0100","0100110");
        hammingTable.put("0101","0101101");
        hammingTable.put("0110","0110001");
        hammingTable.put("0111","0111010");
        hammingTable.put("1000","1000101");
        hammingTable.put("1001","1001110");
        hammingTable.put("1010","1010010");
        hammingTable.put("1011","1011001");
        hammingTable.put("1100","1100011");
        hammingTable.put("1101","1101000");
        hammingTable.put("1110","1110100");
        hammingTable.put("1111","1111111");
    }

    @AfterAll
    public static void deleteErrorFile() {
        String absolutePath = Paths.get("").toAbsolutePath().toString();
        File myObj = new File(absolutePath + "\\src\\main\\resources\\error-hamming.txt");
        myObj.delete();
    }

    @Test
    void deveIntroduzirRedundanciaAoBinario() throws WrongFormatExpection {
        for (Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binary = entry.getKey();
            String messageExpected = entry.getValue();
            String binaryWithRedunduncy = hammingService.introduceRedunduncy(binary);
            Assertions.assertEquals(binaryWithRedunduncy, messageExpected);
        }
    }

    @Test
    void deveRemoverRedundanciaSeBinarioNaoTeveErros() throws WrongFormatExpection {
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryExpected = entry.getKey();
            String binaryWithRedunduncy = entry.getValue();

            String binary = hammingService.getValue(binaryWithRedunduncy);

            Assertions.assertEquals(binary, binaryExpected);
        }
    }

    @Test
    void deveCorrigirErroNoPrimeiroBit(){

        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try {
                String binaryFound = "";

                String binaryWithRedunduncy = entry.getValue();
                char characterAtFirstPosition = binaryWithRedunduncy.charAt(0) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= characterAtFirstPosition+ binaryWithRedunduncy.substring(1);

                binaryFound = hammingService.getValue(binaryWithRedunduncyChanged);

                Assertions.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection wrongFormatExpection) {
                wrongFormatExpection.printStackTrace();
            }
        }
    }

    @Test
    void deveCorrigirErroNoSegundoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryFound = "";
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtSecondPosition = binaryWithRedunduncy.charAt(1) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,1) + characterAtSecondPosition+ binaryWithRedunduncy.substring(2);
                binaryFound = hammingService.getValue(binaryWithRedunduncyChanged);

                Assertions.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void deveCorrigirErroNoNoTerceiroBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryFound = "";
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtThirdPosition = binaryWithRedunduncy.charAt(2) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,2) + characterAtThirdPosition+ binaryWithRedunduncy.substring(3);
                binaryFound = hammingService.getValue(binaryWithRedunduncyChanged);

                Assertions.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void deveCorrigirErroNoQuartoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryFound = "";
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtFourthPosition = binaryWithRedunduncy.charAt(3) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,3) + characterAtFourthPosition+ binaryWithRedunduncy.substring(4);
                binaryFound = hammingService.getValue(binaryWithRedunduncyChanged);

                Assertions.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void deveNotificarErroNoQuintoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtFifthPosition = binaryWithRedunduncy.charAt(4) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,4) + characterAtFifthPosition+ binaryWithRedunduncy.substring(5);
                hammingService.getValue(binaryWithRedunduncyChanged);
            } catch (WrongFormatExpection e) {
                Assertions.assertEquals(e.getMessage(),"Erro critico no bit 5 em hamming");
            }
        }
    }

    @Test
    void deveNotificarErroNoSextoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtSixthPosition = binaryWithRedunduncy.charAt(5) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,5) + characterAtSixthPosition+ binaryWithRedunduncy.substring(6);
                hammingService.getValue(binaryWithRedunduncyChanged);

            } catch (WrongFormatExpection e) {
                Assertions.assertEquals(e.getMessage(),"Erro critico no bit 6 em hamming");
            }
        }
    }

    @Test
    void deveNotificarErroNoSetimoBit(){

        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try{
                String binaryWithRedunduncy = entry.getValue();
                char characterAtSeventhPosition = binaryWithRedunduncy.charAt(6) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,6) + characterAtSeventhPosition;

                hammingService.getValue(binaryWithRedunduncyChanged);
            } catch(WrongFormatExpection e){
                Assertions.assertEquals(e.getMessage(),"Erro critico no bit 7 em hamming");
            }

        }
    }
}