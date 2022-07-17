
package IsotopicMightyGamers;

import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Voucher {
    
    public static void sendMail(String recepient, String htmlCode, String Subject) throws Exception
    {
        Properties p = new Properties();
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.port","587");
        p.put("mail.username", "mgzbd2022@gmail.com");
        p.put("mail.password", "evduvfblcrlumcfb");
        p.put("mail.smtp.ssl.trust", "*");
        
        String CompanyMail = "mgzbd2022@gmail.com";
        String password = "evduvfblcrlumcfb";
        
        Session S = Session.getDefaultInstance(p, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(CompanyMail,password);
            }
        });
        Message message = prepareMessage(S, CompanyMail, recepient,htmlCode, Subject);
        
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Email has been send successfully!!");
    }
    
    private static Message prepareMessage(Session Se, String Mail,String rec, String htmlCode, String Subject)
    {
        try {
            Message message = new MimeMessage(Se);
            message.setFrom(new InternetAddress(Mail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
            message.setSubject(Subject);
            message.setContent(htmlCode,"text/html");
            return message;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please make sure all the inputs are correct!");
        }
        
        return null;
    }
}
    
   
