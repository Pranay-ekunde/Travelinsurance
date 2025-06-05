package review.phase.insurance_travel;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {

    private Long tripId;

    private String destinationCountry;

    private LocalDate startDate;

    private LocalDate endDate;

    private int travelerAge;

    private String travelerName;

    private String contact;

    private List<Long> selectedCoverageIds; // Can be empty or null
}
