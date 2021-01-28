package melnikov.qualification.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Integer.class
)
public class Player extends Human{
    @ManyToMany()
    private Set<Party> games = new HashSet<>();
    @ManyToMany(mappedBy = "availableOnes")
    private Set<Interval> events =new HashSet<>();

    public Set<Party> getGames() {
        return games;
    }

    public void setGames(Set<Party> games) {
        this.games = games;
    }

    public Set<Interval> getEvents() {
        return events;
    }

    public void setEvents(Set<Interval> events) {
        this.events = events;
    }
}
