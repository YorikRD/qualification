package melnikov.qualification.specifications;

import melnikov.qualification.entity.Player;
import melnikov.qualification.entity.Player_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

public class PlayerSpecifications {
    public static Specification<Player> playerByName(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Player_.name), name));

    }

    public static Specification<Player> playerEmail(String email) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Player_.email), email));

    }

    public static Specification<Player> playerByNameAndEmail(String name, String email) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Player> out = criteriaBuilder.createQuery(Player.class);
            Predicate nameUsed = criteriaBuilder.equal(root.get(Player_.name), name);
            Predicate emailUsed = criteriaBuilder.equal(root.get(Player_.email), email);
            out.select(root).where(nameUsed, emailUsed);
            return out.getRestriction();
        });
    }


}
