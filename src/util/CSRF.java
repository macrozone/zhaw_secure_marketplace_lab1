package util;

import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

public class CSRF {
	
	private static SecureRandom prng = new SecureRandom();
	
    public static String createToken() {
    	byte[] token = new byte[16];
    	prng.nextBytes(token);
    	return DatatypeConverter.printHexBinary(token);
    }
    
    public static boolean validateToken(String token1, String token2) {
    	return token1.equals(token2);
    }
}

