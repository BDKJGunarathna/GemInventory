import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailUtil {

    public static void sendMail(String recepient) throws Exception{
        System.out.println("Preparing to send email...");

        Properties properties=new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "jamisulochana@gmail.com";
        String password = "Jami1998@";
        System.out.println("Message send successfully!");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My first email from java app");
            message.setText("look my email");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
       return null;
    }
}
