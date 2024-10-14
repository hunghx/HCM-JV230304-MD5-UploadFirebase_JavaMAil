package ra.springmail;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class MailService {
    @Autowired
    private JavaMailSender sender;
    // gửi mail thông thường
    public void sendEmailNormal(String to, String subject, String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setTo(to);
        mailMessage.setText(content);
        mailMessage.setFrom("Hunghx");
        sender.send(mailMessage);
    }
    // gửi nâng cao
    public void sendMailPro(String to, String subject, String content, File file ) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("hunghx");
        helper.setTo(to);
        helper.setCc("maixuansang1123@gmail.com");
        helper.setSubject(subject);
        String htmlContent = "<h1 style=\"color:red\">hello Hùng</h1>" +
                "<p>"+content+"</p>";
        helper.setText(htmlContent,true);
        // gửi file
        String fileName = file.getName();
        FileSystemResource resource = new FileSystemResource(file);
        helper.addAttachment(fileName,resource);
        sender.send(message);
    }
    public void sendMailVip(FormRequest request) throws MessagingException, IOException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(request.getEmail());
        helper.setCc(request.getCc());
        helper.setSubject(request.getSubject());
        String htmlContent = "<h1 style=\"color:red\">hello "+request.getEmail()+"</h1>" +
                "<p>"+request.getContent()+"</p>";
        helper.setText(htmlContent,true);
        // gửi file
        for (MultipartFile file : request.getFile()){
            String fileName = file.getName();
            DataSource dataSource = new ByteArrayDataSource(file.getBytes(),file.getContentType());
            helper.addAttachment(fileName,dataSource);
        }
        sender.send(message);
    }
}
