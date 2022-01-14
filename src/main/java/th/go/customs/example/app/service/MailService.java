package th.go.customs.example.app.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import th.go.customs.example.app.vo.req.FwUserReq;

@Service
public class MailService {

	public void sendMail(FwUserReq req) throws AddressException, MessagingException {

		String host = "smtp.gmail.com";
		final String user = "nook01298@gmail.com";// change accordingly
		final String password = "uldzwaovcffaqovx";// change accordingly

		String to = "nopparatsiriban@gmail.com";// change accordingly

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", 587);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("javatpoint");
		message.setText("This is simple program of sending email using JavaMail API");

		// send the message
		Transport.send(message);

		System.out.println("message sent successfully...");

	}

}
