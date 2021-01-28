package melnikov.qualification.repository;

import melnikov.qualification.entity.Player;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player,Integer>, JpaSpecificationExecutor<Player> {


}
