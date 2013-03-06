package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Nickle
 * Date: 13-3-6
 * Time: 下午10:43
 * To change this template use File | Settings | File Templates.
 */
public class SenderWithSMTPVer {

    private String from;
    private String to;
    private String subject;
    private String content;
    private String copyTo;


    private String host;
    private String user;
    private String pwd;


    public void setHost(String host) {
        this.host = host;
    }

    public void setAccount(String user, String pwd){
        this.user = user;
        this.pwd = pwd;
    }


    public void send(String from, String to, String subject,String content){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        try{
            Session mailSeesion = Session.getDefaultInstance(properties);
            mailSeesion.setDebug(true);
            Message message = new MimeMessage(mailSeesion);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);
            message.saveChanges();
            Transport transport = mailSeesion.getTransport("smtp");
            transport.connect(host, user, pwd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SenderWithSMTPVer sm = new SenderWithSMTPVer();
        sm.setHost("smtp.126.com");
        sm.setAccount("jkl123hu", "1469575382");
        sm.send("jkl123hu@126.com", "weijing3.28@gmail.com", "test mail", "this is just a mail testing");
    }
}
