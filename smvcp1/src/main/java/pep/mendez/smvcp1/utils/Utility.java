package pep.mendez.smvcp1.utils;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Utility implements UtilityConstants {

	public static boolean sendEmail(JavaMailSender mailSender, String from, String to, String subject, String body) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(new InternetAddress(from));
			helper.setTo(new InternetAddress(to));
			helper.setReplyTo(new InternetAddress(from));
			helper.setSubject(subject);
			helper.setSentDate(new Date());
			helper.setText(body, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return true;
	}

}
