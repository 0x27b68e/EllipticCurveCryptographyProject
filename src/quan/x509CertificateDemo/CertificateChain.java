package quan.x509CertificateDemo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

public class CertificateChain {

	public static void main(String[] args) {

		try {
			CertAndKeyGen certAndKeyGen = new CertAndKeyGen("RSA", "SHA1WithRSA",  null);
			certAndKeyGen.generate(1024);
			PrivateKey privateKey = certAndKeyGen.getPrivateKey();
			X509Certificate rootcertificate = certAndKeyGen.getSelfCertificate(new X500Name("CN=ROOT"), 365*24*3600);
			
			
			CertAndKeyGen certAndKeyGen2 = new CertAndKeyGen("RSA", "SHA1WithRSA",  null);
			certAndKeyGen2.generate(1024);
			PrivateKey privateKey2 = certAndKeyGen2.getPrivateKey();
			X509Certificate certificate2 = certAndKeyGen2.getSelfCertificate(new X500Name("CN=ROOT"), 365*24*3600);
			
			CertAndKeyGen certAndKeyGen3 = new CertAndKeyGen("RSA", "SHA1WithRSA",  null);
			certAndKeyGen3.generate(1024);
			PrivateKey privateKey3 = certAndKeyGen3.getPrivateKey();
			X509Certificate certificate3 = certAndKeyGen3.getSelfCertificate(new X500Name("CN=ROOT"), 365*24*3600);
			
			System.out.println(rootcertificate.toString());
			
			
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | CertificateException | SignatureException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
