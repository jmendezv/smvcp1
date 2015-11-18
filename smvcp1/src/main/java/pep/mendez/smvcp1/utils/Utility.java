package pep.mendez.smvcp1.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author pep
 *
 */
public class Utility implements UtilityConstants {
	
	public static boolean sendEmail(String email) throws IOException {
        // Recipient's email ID needs to be mentioned.
        //String to = "jmendez1@xtec.cat";
        //String to = "josep.mendez@gencat.cat";
        String to = email;
        //String to = "antoni.farran@gencat.cat";

        // Sender's email ID needs to be mentioned
        //String from = "sprl.sscc@gmail.com";
        final String from = "prevencio_risc_lab.ensenyament@gencat.cat";
        //final String username = "sprl.sscc@gmail.com";
        
        final String username = "prevencio_risc";

        // Assuming you are sending email from gmail
        String host = "smtp.gmail.com";

        // Port
        String port = "465";
        // sprl.sscc
        //final String password = "tcvtbeigxektzfou";
        
        final String password = "prevenc128";

        // Get system properties
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", "mail.gencat.cat");
        properties.put("mail.smtp.socketFactory.port", "25");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});

            // Set Subject: header field
            message.setSubject("Subject");

            // Now set the actual message
            //message.setText("Missatge per a " + city, "text/html");
            // Create the message part 
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setContent("<h1>Cos del missatge</h1><p>Missatge de prova</p>", "text/html");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            //messageBodyPart.attachFile(new File(filename));
            // First part, set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();

            String filename = "attachment.pdf";
            DataSource source = new ByteArrayDataSource(new FileDataSource(filename).getInputStream(), "application/pdf");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            messageBodyPart.setDisposition(Part.ATTACHMENT);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            //message.setContent("<h1>Missatge per a " + city  + "</h1>", "text/html");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return true;
    }
	

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
