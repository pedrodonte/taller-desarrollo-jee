import org.jboss.crypto.CryptoUtil;
import org.jboss.security.auth.spi.Util;
import org.junit.Test;


public class UserTest {
	
	String pas = "1100";
	
	@Test
	public void psw(){
		
		System.out.println(	Util.createPasswordHash("SHA-256", Util.BASE64_ENCODING, null, null, pas) );
		System.out.println(	CryptoUtil.createPasswordHash("SHA-256", Util.RFC2617_ENCODING, null, null, pas) );
	}

}
