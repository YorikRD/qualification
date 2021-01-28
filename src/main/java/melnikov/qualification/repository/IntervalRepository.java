package melnikov.qualification.repository;

import melnikov.qualification.entity.Interval;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IntervalRepository extends PagingAndSortingRepository<Interval,Integer>, JpaSpecificationExecutor<Interval> {
}
