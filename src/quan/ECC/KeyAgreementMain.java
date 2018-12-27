package quan.ECC;

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
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

import javax.crypto.KeyAgreement;

/*The code displayed in Listing B. allows two users, U and V, to complete the ECDH key agreement protocol. The private keys
of those users are u and v, respectively, while their public keys are denoted as U = u·G and V = v ·G, respectively. The SunEC
implementation of the ECDH protocol produces a shared value which is the first coordinate of the elliptic curve point computed
as u · V = u · v · G = v · u · G = v · U.*/
public class KeyAgreementMain {
	
	public static void main(String[] args) {
		
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "SunEC");
			ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp192k1");
			keyPairGenerator.initialize(ecGenParameterSpec);
			
			KeyPair genKeyPair = keyPairGenerator.genKeyPair();
			
			PublicKey publicKeyU = genKeyPair.getPublic();
			PrivateKey privateKeyU = genKeyPair.getPrivate();
			
			System.out.println("User U Private: " + getStringFromKey(privateKeyU));
			System.out.println("User U Public: " + getStringFromKey(publicKeyU));
			
			PublicKey publicKeyV = genKeyPair.getPublic();
			PrivateKey privateKeyV = genKeyPair.getPrivate();
			
			System.out.println("User V Private: " + getStringFromKey(publicKeyV));
			System.out.println("User V Public: " + getStringFromKey(privateKeyV));
			
			KeyAgreement keyAgreementU = KeyAgreement.getInstance("ECDH");
			keyAgreementU.init(privateKeyU);
			keyAgreementU.doPhase(publicKeyV, true);

			KeyAgreement keyAgreementV = KeyAgreement.getInstance("ECDH");
			keyAgreementV.init(privateKeyV);
			keyAgreementV.doPhase(publicKeyU, true);
			
			
			System.out.println("Secret computed by U: 0x " +  (new BigInteger(1, keyAgreementU.generateSecret())).toString(16).toUpperCase());
			System.out.println("Secret computed by V: 0x " +  (new BigInteger(1, keyAgreementV.generateSecret())).toString(16).toUpperCase());
			
			
			
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchProviderException e) {
			e.printStackTrace();
		} 
	}
	
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

}
