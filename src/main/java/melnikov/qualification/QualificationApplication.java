package melnikov.qualification;

import melnikov.qualification.entity.Interval;
import melnikov.qualification.services.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class QualificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(QualificationApplication.class,args);

    }
}
