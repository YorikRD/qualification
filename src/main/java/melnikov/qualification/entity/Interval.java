package melnikov.qualification.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Integer.class
)
public class Interval extends Identifier implements Comparable {

    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private LocalDateTime finish;
    @ManyToOne
    private Party game;
    @ManyToMany
    private Set<Player> availableOnes = new HashSet<>();

    public Interval() {
    }

    public Interval(LocalDateTime start, LocalDateTime finish, Player player1) {
        availableOnes = new HashSet<>();
        availableOnes.add(player1);
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

    public Party getGame() {
        return game;
    }

    public void setGame(Party game) {
        this.game = game;
    }

    public Set<Player> getAvailableOnes() {
        return availableOnes;
    }

    public void setAvailableOnes(Set<Player> availableOnes) {
        this.availableOnes = availableOnes;
    }

    @Override
    public int compareTo(Object o) {
        Objects.requireNonNull(o);
        if (start.isBefore(((Interval) o).getStart())) return -1;
        if (start.equals(((Interval) o).start) && finish.equals(((Interval) o).finish)) return 0;
        if (start.equals(((Interval) o).start) && finish.isBefore(((Interval) o).getFinish())) return -1;
        return 1;
    }


    public Interval intersect(Interval other) {
        if (this.equals(null) || other.equals(null)) return null;
        Interval intersection = new Interval();
        if (this.getStart().isBefore(other.getStart())) {
            intersection.setStart(other.getStart());
        } else intersection.setStart(this.getStart());
        if (this.getFinish().isBefore(other.getFinish())) {
            intersection.setFinish(this.getFinish());
        } else intersection.setFinish(other.getFinish());
        if (intersection.getStart().isAfter(intersection.getFinish())) return null;
        Set<Player> summary = this.getAvailableOnes();
        summary.addAll(other.getAvailableOnes());
        intersection.setAvailableOnes(summary);
        return intersection;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", finish=" + finish +
                '}';
    }
}