package th.go.customs.example.app.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import th.go.customs.example.app.vo.req.FwUserReq;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class ResetPasswordService {

	@Autowired
	private JavaMailSender mailSender;

	public void resetPassword(FwUserReq req) {
		try {
			String host = "smtp.gmail.com";
			final String username = "nook01298@gmail.com";// change accordingly
			final String password = "uldzwaovcffaqovx";// change accordingly

			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
//		props.put("mail.smtp.socketFactory.port",587);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", 587);

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// Compose the message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(req.getEmail()));
			message.setHeader("Content-Type", "text/html;charset=TIS-620");
			message.setSubject("REset Password");

			String content = "Dear : [[name]],<br>" + "Please click the link below to verify your registration:<br>"
					+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>"
					+ "Your company name.";
//		content = content.replace("[[name]]", data.getName());

//		String content = html.getMailApprove(data.getName(), verifyURL);
//		content = content.replace("[[URL]]", verifyURL);
			message.setText(content, "utf-8", "html");

			// send the message

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println("message sent successfully...");

	}

	public void sendVerificationEmail(FwUserReq req) {
		final String username = "nook01298@gmail.com";
		final String password = "cpjxupvyhbdpglij";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.ssl.trust", "*");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nopparatsiriban@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
