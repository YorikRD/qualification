package melnikov.qualification.specifications;




import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Party_;
import org.springframework.data.jpa.domain.Specification;

public class PartySpecification {
    public static Specification<Party> partyByTitle(String title) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Party_.title), title));
    }

}

