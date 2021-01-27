package melnikov.qualification.repository;

import melnikov.qualification.entity.Party;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartyRepository extends PagingAndSortingRepository<Party,Integer> {
}
