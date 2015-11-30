package ManagedBeans;
/**
 * This class is used multiple times throughout our Residence Management System
 * to send email notifications to users of the program when certain transactions
 * occur that involve that user.
 */
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailBean {
	
	/**
	 * Variables used by EmailBean
	 */
	private int port = 465;
	private String host = "smtp.gmail.com";
	private String from = "luresidencemanagement@gmail.com";
	private boolean auth = true;
	private String username = "luresidencemanagement@gmail.com";		
	private String password = "ResManPass";
	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;
	
	/**
	 * Everything to do with sending an email.
	 * Always called from another class through an EmailBean instance.
	 * @param sendTo
	 * @param subject
	 * @param body
	 */
	public void sendEmail(String sendTo, String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		switch (protocol) {
		    case SMTPS:
		        props.put("mail.smtp.ssl.enable", true);
		        break;
		    case TLS:
		        props.put("mail.smtp.starttls.enable", true);
		        break;
		}
		
		Authenticator authenticator = null;
		if (auth) {
		    props.put("mail.smtp.auth", true);
		    authenticator = new Authenticator() {
		        private PasswordAuthentication pa = new PasswordAuthentication(username, password);
		        @Override
		        public PasswordAuthentication getPasswordAuthentication() {
		            return pa;
		        }
		    };
		}
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		
		MimeMessage message = new MimeMessage(session);
		try {
		    message.setFrom(new InternetAddress(from));
		    InternetAddress[] address = {new InternetAddress(sendTo)};
		    message.setRecipients(Message.RecipientType.TO, address);
		    message.setSubject(subject);
		    message.setSentDate(new Date());
		    message.setText(body + "\r\n\r\nYou can reply to this email to speak with the Residence Manager, and they will get back to you ASAP.\r\nHave a nice day.");
		    Transport.send(message);
		} catch (MessagingException ex) {
		    ex.printStackTrace();
		}
	}
}
