package spittr.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MD5Encoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] results = md5.digest(Utf8.encode(rawPassword));
		return new String(Hex.encode(results)).toUpperCase();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		boolean result = encodedPassword.equals(encode(rawPassword));
		return result;
	}

}
