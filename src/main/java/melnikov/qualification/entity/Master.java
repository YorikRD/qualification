package melnikov.qualification.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import melnikov.qualification.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Integer.class
)
public class Master extends Human{

    @OneToMany(mappedBy = "master", fetch = FetchType.LAZY)
    private Set<Party> games = new HashSet<>();

    public Master(String name, String email) {
        super.setName(name);
        super.setEmail(email);
    }

    public Master() {
    }

    public Player asPlayer(){
        Player player= new Player();
        player.setName(getName());
        player.setEmail(getEmail());
        return player;
    }

    public Set<Party> getGames() {
        return games;
    }

    public void setGames(Set<Party> games) {
        this.games = games;
    }
}
