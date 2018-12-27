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

// Blockchain implementations such as Bitcoin, Ethereum uses Elliptic Curves (EC) to generate private and public  key pairs.
//Elliptic Curves Cryptography was invested by Neal Koblitz and Victor Miller in 1985.
// A 256 bit ECC public key provides comparable security to a 3071-bit RSA public key. The primary advantage of using Elliptic Curve based cryptography is reduced 
// key size and hence speed.

// The standards for Efficient Cryptography Group (SECG) is an international consortium (liên doanh) to develop commercial standards efficient and interoperable cryptography
// based on elliptic curve cryptography (ECC)

//In this table, you will find a set of elliptic curve domain parameters
// The elliptic curves use smaller key sizes with respect to RSA providing comparable security.
// Bitcoin and Ethereum both uses the same secp256k1 elliptic curve domain parameters.
// secp256k1 use the following elliptic curve equation: y^2 = x^3 + ax + b, ex: y^2 = x^3 + 7. 
// In the following slides we will go thru each parameter p,a,b,G,n,h
// Parameter a = 0.
// Parameter b =7.

//Elliptic Curve domain parameters:
//		Parameter					Elliptic Curve Key length  					RSA Key Length
//		secp192k1							192									  1536
//		secp192r1							192									  1536
//		secp224k1							224									  2048
//		secp224r1							224									  2048
//		secp256k1							256									  3072
//		secp256r1							256									  3072
//		secp384r1							384									  7680
//		secp512r1							512									  15360





public class Main {
	
	public static void main(String[] args) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "SunEC"); //using the SunEC cryptographic engine
//			ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp192r1"); //secp192r1 curve
			ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256r1"); //secp192r1 curve
			keyPairGenerator.initialize(ecGenParameterSpec);
			
			KeyPair generateKeyPair = keyPairGenerator.genKeyPair();
			PublicKey publicKey = generateKeyPair.getPublic();
			PrivateKey privateKey = generateKeyPair.getPrivate();
			
			System.out.println("public key: " + getStringFromKey(publicKey));
			
			System.out.println("private key: " + getStringFromKey(privateKey));
			
			
		} catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

}
