package ua.nicety.service.mail;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static ua.nicety.service.mail.PdfGeneratorService.DEST;


@RequiredArgsConstructor
@Service
public class MailService {

    private final Properties emailProperties;

    public void sendEmail(String toEmail) {
        try {
            String fromEmail = emailProperties.getProperty("fromEmail");
            String yahooAccountAppPassword = emailProperties.getProperty("appPassword");

            Session session = Session.getInstance(emailProperties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, yahooAccountAppPassword);
                }
            });
            session.setDebug(true);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Schedule");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please check attachments for pdf file");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = DEST;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            send(message);
        } catch (Exception e) {
            throw new RuntimeException("mail sender failed" + e.getMessage());
        }

    }

    public void send(Message message) throws Exception{
        Transport.send(message);
    }

}
