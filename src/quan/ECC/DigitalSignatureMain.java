package quan.ECC;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class DigitalSignatureMain {
	/*
	The code shown in Listing C. allows to generate the digital signature of the text “In teaching others we teach ourselves” using
	the ECDSA scheme. In addition to that, the application validates the resulting signature, simulating the action of the user who
	receives both the text and the signature.*/
	public static void main(String[] args) {
		
		
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("EC", "SunEC");
			ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp192r1"); // secp192r1 curve
			keyPairGenerator.initialize(ecGenParameterSpec);

			KeyPair generateKeyPair = keyPairGenerator.genKeyPair();
			PublicKey publicKey = generateKeyPair.getPublic();
			PrivateKey privateKey = generateKeyPair.getPrivate();

			System.out.println("public key: " + getStringFromKey(publicKey));

			System.out.println("private key: " + getStringFromKey(privateKey));

			Signature signature = Signature.getInstance("SHA1withECDSA", "SunEC");
			signature.initSign(privateKey);

			String string = "Hello world";
			signature.update(string.getBytes("UTF-8"));
			// Kí vào text
			byte[] sign = signature.sign();
			
			String string2 = new BigInteger(1, sign).toString(16);
			System.out.println("Signature: " + string2.toUpperCase());
			
			/// Dùng Public Key + Text để check private key
			Signature check = Signature.getInstance("SHA1withECDSA", "SunEC");
			check.initVerify(publicKey);
			check.update(string.getBytes("UTF-8"));
			System.out.println("Valid: " + check.verify(sign));
			
			// check spam text
			String spam = " Hello world 2";
			check.update(spam.getBytes("UTF-8"));
			System.out.println("Valid: " + check.verify(sign));

			

		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException
				| InvalidKeyException | UnsupportedEncodingException | SignatureException e) {
			e.printStackTrace();
		}
	}

	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

}
