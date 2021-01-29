package melnikov.qualification.specifications;


import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Interval_;
import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Player;
import org.springframework.data.jpa.domain.Specification;

public class IntervalSpecifications {
    public static Specification<Interval> intervalByParty(Party party) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Interval_.game), party));

    }

//    public static Specification<Interval> byPlayer(Player player) {
//        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Player_.email), email));
//
//    }
}
