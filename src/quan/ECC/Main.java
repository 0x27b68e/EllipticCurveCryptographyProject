package quan.ECC;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

// refer http://www.academicpub.org/PaperInfo.aspx?PaperID=14496
// generates a key pair using the secp192r1 curve. Additionally, it displays on the screen the content of the public and private keys.

public class Main {
	
	public static void main(String[] args) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "SunEC"); //using the SunEC cryptographic engine
			ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp192r1"); //secp192r1 curve
			keyPairGenerator.initialize(ecGenParameterSpec);
			
			KeyPair generateKeyPair = keyPairGenerator.genKeyPair();
			PublicKey publicKey = generateKeyPair.getPublic();
			PrivateKey privateKey = generateKeyPair.getPrivate();
			
			System.out.println("public key: " + getStringFromKey(publicKey));
			
			System.out.println("private key: " + getStringFromKey(privateKey));
			
			
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

}
