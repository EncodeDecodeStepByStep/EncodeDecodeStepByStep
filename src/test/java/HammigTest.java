import expections.WrongFormatExpection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redunduncy.Hamming;

import java.util.HashMap;
import java.util.Map;

public class HammigTest {

    Hamming hamming = new Hamming();
    HashMap<String, String> hammingTable = new HashMap<String, String>();

    @Before
    public void setUp(){
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

    @Test
    public void deveIntroduzirRedundanciaAoBinario() throws WrongFormatExpection {
        for (Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binary = entry.getKey();
            String messageExpected = entry.getValue();
            String binaryWithRedunduncy = hamming.introduceRedunduncy(binary);
            Assert.assertEquals(binaryWithRedunduncy, messageExpected);
        }
    }

    @Test
    public void deveRemoverRedundanciaSeBinarioNaoTeveErros() throws WrongFormatExpection {
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryExpected = entry.getKey();
            String binaryWithRedunduncy = entry.getValue();

            String binary = hamming.getValue(binaryWithRedunduncy);

            Assert.assertEquals(binary, binaryExpected);
        }
    }

    @Test
    public void deveCorrigirErroNoPrimeiroBit(){

        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try {
            String binaryFound = "";

                String binaryWithRedunduncy = entry.getValue();
                char characterAtFirstPosition = binaryWithRedunduncy.charAt(0) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= characterAtFirstPosition+ binaryWithRedunduncy.substring(1);

                binaryFound = hamming.getValue(binaryWithRedunduncyChanged);

                Assert.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection wrongFormatExpection) {
                wrongFormatExpection.printStackTrace();
            }
        }
    }

    @Test
    public void deveCorrigirErroNoSegundoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryFound = "";
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtSecondPosition = binaryWithRedunduncy.charAt(1) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,1) + characterAtSecondPosition+ binaryWithRedunduncy.substring(2);
                binaryFound = hamming.getValue(binaryWithRedunduncyChanged);

                Assert.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deveCorrigirErroNoNoTerceiroBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryFound = "";
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtThirdPosition = binaryWithRedunduncy.charAt(2) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,2) + characterAtThirdPosition+ binaryWithRedunduncy.substring(3);
                binaryFound = hamming.getValue(binaryWithRedunduncyChanged);

                Assert.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deveCorrigirErroNoQuartoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            String binaryFound = "";
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtFourthPosition = binaryWithRedunduncy.charAt(3) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,3) + characterAtFourthPosition+ binaryWithRedunduncy.substring(4);
                binaryFound = hamming.getValue(binaryWithRedunduncyChanged);

                Assert.assertEquals(binaryFound, binaryWithRedunduncy.substring(0, 4));
            } catch (WrongFormatExpection e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deveNotificarErroNoQuintoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtFifthPosition = binaryWithRedunduncy.charAt(4) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,4) + characterAtFifthPosition+ binaryWithRedunduncy.substring(5);
                hamming.getValue(binaryWithRedunduncyChanged);
            } catch (WrongFormatExpection e) {
                Assert.assertEquals(e.getMessage(),"Erro critico no bit 5 em hamming");
            }
        }
    }

    @Test
    public void deveNotificarErroNoSextoBit(){
        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try {
                String binaryWithRedunduncy = entry.getValue();
                char characterAtSixthPosition = binaryWithRedunduncy.charAt(5) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,5) + characterAtSixthPosition+ binaryWithRedunduncy.substring(6);
                hamming.getValue(binaryWithRedunduncyChanged);

            } catch (WrongFormatExpection e) {
                Assert.assertEquals(e.getMessage(),"Erro critico no bit 6 em hamming");
            }
        }
    }

    @Test
    public void deveNotificarErroNoSetimoBit(){

        for(Map.Entry<String, String> entry : hammingTable.entrySet()) {
            try{
                String binaryWithRedunduncy = entry.getValue();
                char characterAtSeventhPosition = binaryWithRedunduncy.charAt(6) == '0' ? '1' : '0';
                String binaryWithRedunduncyChanged= binaryWithRedunduncy.substring(0,6) + characterAtSeventhPosition;

                 hamming.getValue(binaryWithRedunduncyChanged);
            } catch(WrongFormatExpection e){
                Assert.assertEquals(e.getMessage(),"Erro critico no bit 7 em hamming");
            }

        }
    }
}
