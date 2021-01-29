package melnikov.qualification.specifications;

import melnikov.qualification.entity.Master;
import melnikov.qualification.entity.Master_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

public class MasterSpecifications {
    public static Specification<Master> playerByName(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Master_.name), name));

    }

    public static Specification<Master> playerEmail(String email) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Master_.email), email));

    }

    public static Specification<Master> masterByNameAndEmail(String name, String email) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Master> out = criteriaBuilder.createQuery(Master.class);
            Predicate nameUsed = criteriaBuilder.equal(root.get(Master_.name), name);
            Predicate emailUsed = criteriaBuilder.equal(root.get(Master_.email), email);
            out.select(root).where(nameUsed, emailUsed);
            return out.getRestriction();
        });
    }
}
