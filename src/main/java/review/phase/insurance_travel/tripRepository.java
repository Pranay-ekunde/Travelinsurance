package review.phase.insurance_travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface tripRepository extends JpaRepository<trip, Long> {
}

