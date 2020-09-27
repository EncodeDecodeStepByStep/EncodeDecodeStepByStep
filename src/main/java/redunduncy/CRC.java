package redunduncy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
	    
	    String binSecondByte = hexToBin(hexSecondByte);
	    int binLengthSecondByte = 8 - binSecondByte.length();
    	binSecondByte = StringUtils.createStreamOnZeros(binLengthSecondByte) + binSecondByte;
    	
	    String check = hexToBin(hexFirstByte) + binSecondByte + StringUtils.createStreamOnZeros(8);
	    check = removeZerosLeft(check);
	    
	    boolean finished = false;
	    
	    List<Integer> rightBits = new ArrayList<Integer>();
	    
	    for (int i = 0; i < check.length(); i++) {
	    	if (i < 9) continue;
	    	rightBits.add(Character.getNumericValue(check.charAt(i)));
	    }

	    while (finished == false) {
	    	String next = "";
	    	for (int i = 0; i < CRC_PRESET.length(); i++) {	
				int bit1 = check.charAt(i);
				int bit2 = CRC_PRESET.charAt(i);
				int finalBit = bit1 ^ bit2;
				next += Integer.toString(finalBit);
			}
			
			check = removeZerosLeft(next);
			int size = rightBits.size();
			for (int i = 0; i < size; i++) {
				if (check.length() >= 9) break;
		    	check += rightBits.remove(0);
		    }
			
			if (check.length() < 9) {
				finished = true;
			}
		}
	    String result = check.substring(0, 8);
		return result;
    
	}
	
	private static String hexToBin(String s) {
		return new BigInteger(s, 16).toString(2);
	}
	
	private static String removeZerosLeft(String s) {
		return s.replaceFirst("^0+(?!$)", "");
	}
}
