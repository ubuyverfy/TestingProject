package domain.allDomain;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SentMail {

	public static final String username = "ubuyramesh@gmail.com";
	public static final String password = "ubuy@123";
	public static String to = "ramesh.saini@ubuy.com";
	public static String from = "ubuyramesh@gmail.com";
	public static String protocol = "smtp";
	public static int port = 465;
	public static String host = "smtp.gmail.com";
	public static Transport transport;
	public static Message message;
	public static Session session;

	// public static Transport transport;
	public void sendEmail() throws MessagingException, IOException {

		Properties properties = System.getProperties();
		properties.put("mail.transport.protocol", protocol);
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		transport = session.getTransport();

		try {
		
			System.setProperty("mail.smtp.ssl.protocols", "TLSv1.3");
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Domain Error");
		MimeBodyPart text = new MimeBodyPart();
		text.setText("This domain is not working");
		//MimeBodyPart attchment = new MimeBodyPart();
		//attchment.attachFile(TakeSS.errorscreentshots);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(text);
		//multipart.addBodyPart(attchment);
		message.setContent(multipart);
		System.out.println("run here");
		transport.connect(host, username, password);
		transport.send(message);
		System.out.println("email sent");

	}catch(Exception e) {
		e.printStackTrace();
	}
		finally 
		{
			transport.close();
		}
		
	}
}
