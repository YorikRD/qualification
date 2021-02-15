package melnikov.qualification.auxiliary.mailnotify;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailSender {
    public JavaMailSender javaMailSender;
    @Autowired
    public SimpleEmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendSimpleEmailResponse(String address, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("<noreply@gmail.com>");
        message.setTo(address);
        message.setSubject("possible meeting notify");
        message.setText(text);

        this.javaMailSender.send(message);
        return "Sent";
    }


}
