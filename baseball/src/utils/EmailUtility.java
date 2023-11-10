package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
@WebServlet("/EmailUtility")
public class EmailUtility{

    public static void sendEmail(String to, String subject, String content) throws Exception {
        // メール送信に必要な設定値。ここではGmailのSMTPサーバーを例にしています。
        String host = "smtp.gmail.com";
        String username = "your-email@gmail.com"; // Gmailのメールアドレス
        String password = "your-password"; // Gmailのパスワード
        int port = 587;

        // SMTPサーバーの設定を行います。
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // セッションを生成します。
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // メールの内容を設定します。
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            // メールを送信します。
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("MailUtil sendEmail failed: " + e.getMessage(), e);
        }
    }
}
