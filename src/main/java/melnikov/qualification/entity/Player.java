package melnikov.qualification.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player extends Human{

    @ManyToMany()
    private Set<Party> games = new HashSet<>();
    @ManyToMany(mappedBy = "availableOnes")
    private Set<Interval> events =new HashSet<>();

}
