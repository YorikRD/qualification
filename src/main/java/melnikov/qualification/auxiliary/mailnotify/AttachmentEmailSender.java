package melnikov.qualification.auxiliary.mailnotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class AttachmentEmailSender {
    @Autowired
    private JavaMailSender emailSender;

    public String sendAttachmentEmail(String email, String path) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setFrom("<noreply@gmail.com>");
        helper.setSubject("Response email with table of games");
        helper.setText("with all respect");
        String path1 = path;
        FileSystemResource file1 = new FileSystemResource(new File(path1));
        helper.addAttachment("All1.xlsx", file1);
        emailSender.send(message);
        return "email sent";
    }
}
