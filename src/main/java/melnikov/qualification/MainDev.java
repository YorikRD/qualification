package melnikov.qualification;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Master;
import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainDev {
    public static void main(String[] args) {
//        Master master =  new Master ("Yorrr", "sumkoprig@gmail.com");
//        Master player2 = new Master("","saranger@inbox.ru");

        Interval interval1 = new Interval();
        interval1.setStart(LocalDateTime.of(2021,12,03,12,00));
        interval1.setFinish(LocalDateTime.of(2021,12,03,18,00));

//        Player masters1Ava= master.asPlayer();
//        masters1Ava.getEvents().add(interval1);
//        Party party1 = new Party();
//        party1.setMaster(master);
//        party1.getPlayers().add(masters1Ava);
//        party1.getMeetings().add(interval1);
//        party1.setTitle("my first");
//        interval1.setGame(party1);

        ObjectMapper mapperToJson = new ObjectMapper();

        String party1Json = null;
        try {
            party1Json=mapperToJson.writeValueAsString(interval1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(party1Json);


//        Master player3 = new Master(null,"sa124rangerinboxru");

//        Player TruPlayer = player1.asPlayer();
//        Player TruPlayer2 = player2.asPlayer();








    }
}
