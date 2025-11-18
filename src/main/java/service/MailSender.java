/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Administrator
 */
public class MailSender {

    private static String fromEmail = "tkredao02@gmail.com";
    private static String appPassword = "tqrw zado ddbb rmqt";

    public static void sendMailPassword(String toEmail, String fullName, String userName, String password) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.mime.charset", "UTF-8");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Cửa hàng Giày", "UTF-8"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail, false));
            message.setSubject("Khôi phục mật khẩu - Cửa hàng giày", "UTF-8");

            String content = """
                <html><body style='font-family: Arial; font-size:14px'>
                <p>Xin chào <b>%s</b></p>
                <p>- Tải khoản của bạn là: <b>%s</b></p>
                <p>- Mật khẩu của bạn là: <b>%s</b></p>
                <br>
                <p>Đây là email tự động, vui lòng không reply email này.</p> 
                <p>Cám ơn.</p>
                <br>
                <p>Trân trọng,
                <br>
                <b>Cửa hàng Giày</b></p>
                </body></html>
            """.formatted(fullName, userName, password);

            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendMailOTP(String toEmail, String fullName, String userName, String OTP) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.mime.charset", "UTF-8");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Cửa hàng Giày", "UTF-8"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail, false));
            message.setSubject("OTP - Cửa hàng giày", "UTF-8");

            String content = """
                <html><body style='font-family: Arial; font-size:14px'>
                <p>Xin chào <b>%s</b></p>
                <p>- Tải khoản của bạn là: <b>%s</b></p>
                <p>- OTP của bạn là: <b>%s</b></p>
                <br>
                <p>Đây là email tự động, vui lòng không reply email này.</p> 
                <p>Cám ơn.</p>
                <br>
                <p>Trân trọng,
                <br>
                <b>Cửa hàng Giày</b></p>
                </body></html>
            """.formatted(fullName, userName, OTP);

            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
