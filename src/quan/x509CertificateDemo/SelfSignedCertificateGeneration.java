package quan.x509CertificateDemo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;
//https://www.pixelstech.net/article/1406724116-Generate-certificate-in-Java----Self-signed-certificate
public class SelfSignedCertificateGeneration {

	public static void main(String[] args) {
		try {
			// Create Cert and Key, contains (Cert and publicKey)
			CertAndKeyGen certAndKeyGen = new CertAndKeyGen("RSA", "SHA1WithRSA", null);
			certAndKeyGen.generate(1024);
			
			X509Certificate[]  chain = new X509Certificate[1];
			
			//seft sign
			chain[0] = certAndKeyGen.getSelfCertificate(new X500Name("CN=ROOT"), 365*24*3600);
			
			
			// print all off info 
			System.out.println(chain[0].toString());
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | CertificateException | SignatureException | IOException e) {
			e.printStackTrace();
		}

	}

}
