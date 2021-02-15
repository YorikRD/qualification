package melnikov.qualification;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import melnikov.qualification.auxiliary.MeetingCreator;
import melnikov.qualification.auxiliary.mailnotify.MyConstants;
import melnikov.qualification.auxiliary.mailnotify.SimpleEmailSender;
import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Master;
import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Player;

import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class MainDev {
    public static void main(String[] args) {
        try {
            checker1();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    public static void checker1() throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyConstants.MY_EMAIL, MyConstants.MY_PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sumkoprig@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("sumkoprig@gmail.com"));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);


    }

    public static void checker2(){
        Master master =  new Master ("Yorrr", "sumkoprig@gmail.com");
        Master m1 = new Master("p1","saranger@inbox.ru");
        Master m2 = new Master("p2","saranger@inbox.ru");
        Master m3 = new Master("p3","saranger@inbox.ru");
        Master m4 = new Master("p4","saranger@inbox.ru");

        Party party0 = new Party();


        Player player0  = master.asPlayer();
        Player player1  = m1.asPlayer();
        Player player2  = m2.asPlayer();
        Player player3  = m3.asPlayer();
        Player player4  = m4.asPlayer();

        party0.getPlayers().add(player0);
        party0.getPlayers().add(player1);
        party0.getPlayers().add(player2);
        party0.getPlayers().add(player3);
        party0.getPlayers().add(player4);

        Interval interval0 = new Interval();
        interval0.setStart(LocalDateTime.of(2021,11,03,12,00));
        interval0.setFinish(LocalDateTime.of(2021,11,03,18,00));
        interval0.getAvailableOnes().add(player0);
        player0.getEvents().add(interval0);
        party0.getMeetings().add(interval0);



        Interval interval1 = new Interval();
        interval1.setStart(LocalDateTime.of(2021,12,03,12,00));
        interval1.setFinish(LocalDateTime.of(2021,12,03,18,00));
        interval1.getAvailableOnes().add(player0);
        player0.getEvents().add(interval1);
        party0.getMeetings().add(interval1);

        Interval interval2 = new Interval();
        interval2.setStart(LocalDateTime.of(2021,12,03,11,00));
        interval2.setFinish(LocalDateTime.of(2021,12,03,18,00));
        interval2.getAvailableOnes().add(player1);
        player1.getEvents().add(interval2);
        party0.getMeetings().add(interval2);

        Interval interval3 = new Interval();
        interval3.setStart(LocalDateTime.of(2021,12,03,13,00));
        interval3.setFinish(LocalDateTime.of(2021,12,03,19,00));
        interval3.getAvailableOnes().add(player2);
        player2.getEvents().add(interval3);
        party0.getMeetings().add(interval3);

        Interval interval4 = new Interval();
        interval4.setStart(LocalDateTime.of(2021,12,03,12,00));
        interval4.setFinish(LocalDateTime.of(2021,12,03,17,00));
        interval4.getAvailableOnes().add(player3);
        player3.getEvents().add(interval4);
        party0.getMeetings().add(interval4);

        Interval interval5 = new Interval();
        interval5.setStart(LocalDateTime.of(2021,12,03,11,00));
        interval5.setFinish(LocalDateTime.of(2021,12,03,19,00));
        interval5.getAvailableOnes().add(player4);
        player4.getEvents().add(interval5);
        party0.getMeetings().add(interval5);


        MeetingCreator mC1 = new MeetingCreator(party0);
        mC1.createMeetings2();
        System.out.println(mC1.getCreatedMeetings());


    }
}
