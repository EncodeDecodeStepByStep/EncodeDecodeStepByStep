package redunduncy;

import java.math.BigInteger;

import utils.StringUtils;

public class CRC {
	private final static String CRC_PRESET = "100000111";
	// 16 bits = 2 bytes do cabeçalho
	private final static int D = 16;
	
	public String calculateCRC8(String firstByte, String secondByte) {
		long firstByteLong = Long.parseLong(firstByte, 2);
	    long secondByteLong = Long.parseLong(secondByte, 2);
	    int decimalFirstByte = (int) (firstByteLong);
	    int decimalSecondByte = (int) (secondByteLong);
	    String hexFirstByte = Integer.toHexString(decimalFirstByte);
	    String hexSecondByte = Integer.toHexString(decimalSecondByte);
	    
	    String check = hexToBin(hexFirstByte) + hexToBin(hexSecondByte) + StringUtils.createStreamOnZeros(8);
	    check = removeZerosLeft(check);
	    
	    boolean finished = false;
	    
	    int usedZeros = 9 - (check.length() - 8);
	    while (finished == false) {
	    	String next = "";
			for (int i = 0; i < CRC_PRESET.length(); i++) {
				int bit1 = check.charAt(i);
				int bit2 = CRC_PRESET.charAt(i);
				int finalBit = bit1 ^ bit2;
				next += Integer.toString(finalBit);
			}
			
			check = removeZerosLeft(next);
			if (check.length() < 9) {
				int count = 9 - check.length();
				check += StringUtils.createStreamOnZeros(count);
				usedZeros += count;
			}
			
			if (usedZeros > 8) {
				finished = true;
			}
		}
	    String teste = check.substring(0, 8);
		return check.substring(0, 8);
    
	}
	
	private static String hexToBin(String s) {
		return new BigInteger(s, 16).toString(2);
	}
	
	private static String removeZerosLeft(String s) {
		return s.replaceFirst("^0+(?!$)", "");
	}
}
