package melnikov.qualification.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
public class Interval implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private LocalDateTime finish;

    private Set<Integer> availableOnes = new HashSet<>();

    public Interval() {
    }

    public Interval(LocalDateTime start, LocalDateTime finish, Human player1) {
        availableOnes = new HashSet<>();
        availableOnes.add(player1.getId());
        this.start = start;
        this.finish = finish;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public Set<Integer> getAvailableOnes() {
        return availableOnes;
    }

    public void setAvailableOnes(Set<Integer> availableOnes) {
        this.availableOnes = availableOnes;
    }

    @Override
    public int compareTo(Object o) {
        Objects.requireNonNull(o);
        if(start.isBefore(((Interval) o).getStart()))return -1;
        if (start.equals(((Interval) o).start)&&finish.equals(((Interval) o).finish))return 0;
        if (start.equals(((Interval) o).start)&&finish.isBefore(((Interval) o).getFinish())) return -1;
        return 1;
    }


    public Interval intersect (Interval other){
        if (this.equals(null)|other.equals(null))return null;
        Interval intesection = new Interval();
        if(this.getStart().isBefore(other.getStart())) {
            intesection.setStart(other.getStart());
        }else intesection.setStart(this.getStart());
        if(this.getFinish().isBefore(other.getFinish())){
            intesection.setFinish(this.getFinish());
        } else intesection.setFinish(other.getFinish());
        if (intesection.getStart().isAfter(intesection.getFinish())) return null;
        intesection.getAvailableOnes().addAll(this.getAvailableOnes());
        intesection.getAvailableOnes().addAll(other.getAvailableOnes());
        return intesection;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", finish=" + finish +
                '}';
    }
}