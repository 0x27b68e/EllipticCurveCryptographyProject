package quan.KeyStores;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Enumeration;

import javax.crypto.spec.SecretKeySpec;

//http://tutorials.jenkov.com/java-cryptography/keystore.html
//https://www.baeldung.com/java-keystore

public class Main {
// refer: https://www.baeldung.com/java-keystore
	public static void main(String[] args) {
		    char[] password = "password".toCharArray();
			saveKeyStores(password);
			
			KeyStore keyStore = getKeyStores(password);
			
			//
			Key key = getSymmetricKey(password, keyStore);
			System.out.println("Key: " + new String(key.getEncoded()));
	}
	
	public static void saveKeyStores(char[] charArray) {
		try {
			//store keystore
			KeyStore keyStore = KeyStore.getInstance("jceks");
			//create new KeyStore
			keyStore.load(null, charArray);
			
			FileOutputStream fileOutputStream = new FileOutputStream("keyStores.jceks");
			keyStore.store(fileOutputStream, charArray);
			
			System.out.println("ok");
			
			
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static KeyStore getKeyStores(char[] password) {
		try {
			KeyStore keyStore = KeyStore.getInstance("jceks");
			
			FileInputStream fileInputStream = new FileInputStream("keyStores.jceks");
			keyStore.load(fileInputStream, password);
			
			return keyStore;
			
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getAsymmetricKey(char[] password, KeyStore keyStore) {
		
	}
	
	
	public static Key getSymmetricKey(char[] password, KeyStore keyStore) {
		// we will store symmetric key using our keystore

		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec("AAAAAA".getBytes(), "AES");
			SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKeySpec);
			KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(password);

			keyStore.setEntry("symmetricAlias", secretKeyEntry, protectionParameter);
			
			// getKey
			Key key = keyStore.getKey("symmetricAlias", password);

			System.out.println("getCertificate: " + keyStore.getCertificate("symmetricAlias"));

			System.out.println("contains symmetricAlias ?:" + keyStore.containsAlias("symmetricAlias"));
			System.out.println("contains symmetricAlias2 ?:" + keyStore.containsAlias("symmetricAlias2"));

			Enumeration<String> aliases = keyStore.aliases();
			while (aliases.hasMoreElements()) {
				System.out.println(aliases.nextElement());

			}
			return key;

		} catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
