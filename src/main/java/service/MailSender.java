/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MailSender {

    private static String fromEmail = "Tài khoản Gmail";
    private static String appPassword = "App Password lấy từ gmail yêu cầu tài khoản phải bật xác thực 2 yếu tố nhé.";

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

    public static void sendHoaDon(String toEmail, String fullName, String maHD, String fileUrl) {
        try {

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Cửa hàng Giày", "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            message.setSubject("Hóa đơn mua hàng - Mã: " + maHD, "UTF-8");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String content = """
            <html><body style='font-family: Arial; font-size:14px'>
            <p>Xin chào <b>%s</b>,</p>
            <p>Cám ơn bạn đã mua hàng tại Cửa hàng Giày!</p>
            <p>Chúng tôi gửi kèm hóa đơn mua hàng của bạn (mã đơn: <b>%s</b>).</p>
            <br>
            <p>Trân trọng,</p>
            <b>Cửa hàng Giày</b>
            </body></html>
            """.formatted(fullName, maHD);
            messageBodyPart.setContent(content, "text/html; charset=UTF-8");

            MimeBodyPart attachmentPart = new MimeBodyPart();

            File file = new File(fileUrl);
            byte[] fileBytes = java.nio.file.Files.readAllBytes(file.toPath());
            DataSource dataSource = new ByteArrayDataSource(fileBytes, "application/pdf");

            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName("HoaDon_" + maHD + ".pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Đã gửi hóa đơn PDF (từ URL) thành công!");
            JOptionPane.showMessageDialog(null, "Đã gửi hóa đơn thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Không thể gửi hóa đơn. Vui lòng kiểm tra kết nối Internet hoặc cấu hình email!");
        }
    }
}
