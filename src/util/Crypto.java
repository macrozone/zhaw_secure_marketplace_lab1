package util;

import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class Crypto {
	public static final int SCRYPT_N = 16384;
	public static final int SCRYPT_R = 8;
	public static final int SCRYPT_P = 1;
	public static final int SCRYPT_LENGTH = 32;

	public static String createScryptHash(String password) {
		return SCryptUtil.scrypt(password, SCRYPT_N, SCRYPT_R, SCRYPT_P);
	}

	public static String computeScryptHash(String password, String saltBase64) 
			throws GeneralSecurityException {
		byte[] hash = SCrypt.scrypt(password.getBytes(), 
				                        Base64.getDecoder().decode(saltBase64), 
				                        SCRYPT_N, SCRYPT_R, SCRYPT_P, SCRYPT_LENGTH);
		return Base64.getEncoder().encodeToString(hash);
	}	

	public static void main(String argv[]) throws Exception {
		System.out.println("scrypt hash of '" + argv[0] + "': " + createScryptHash(argv[0]));
	}
}