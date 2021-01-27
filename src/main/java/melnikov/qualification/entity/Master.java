package melnikov.qualification.entity;

import melnikov.qualification.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Master extends Human {

    @OneToMany(mappedBy = "master", fetch = FetchType.LAZY)
    private Set<Party> games = new HashSet<>();

    public Master(String name, String email) {
        super.setName(name);
        super.setEmail(email);
    }

}
