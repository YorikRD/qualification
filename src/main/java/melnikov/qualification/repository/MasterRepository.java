package melnikov.qualification.repository;

import melnikov.qualification.entity.Master;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MasterRepository extends PagingAndSortingRepository<Master, Integer>, JpaSpecificationExecutor<Master> {

    @Query(value = "SELECT m FROM Master m WHERE UPPER(m.name)= :masterName")
    public Master getByName(String masterName);
    }
