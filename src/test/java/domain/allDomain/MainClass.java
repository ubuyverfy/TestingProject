package domain.allDomain;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.Test;

public class MainClass {
	
	@Test
	public void callAllMethods() throws IOException, EmailException, MessagingException {
		
		HitAllDomain alldomain= new HitAllDomain();
		alldomain.lunchBrowser();
		alldomain.fetchDomain();
	}

}
