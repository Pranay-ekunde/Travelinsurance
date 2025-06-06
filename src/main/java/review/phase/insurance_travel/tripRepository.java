package review.phase.insurance_travel;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface tripRepository extends CrudRepository<trip, Long> {
}

