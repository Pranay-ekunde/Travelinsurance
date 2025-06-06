package review.phase.insurance_travel;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @NotBlank(message = "Destination country must not be blank")
    private String destinationCountry;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Min(value = 1, message = "Traveler age must be greater than 0")
    private int travelerAge;

    @NotBlank(message = "Traveler name is required")
    private String travelerName;

    @NotBlank(message = "Contact info is required")
    private String contact;

    @Column(name = "coverage_id", nullable = true)  // allows nulls in the list
    private List<Long> selectedCoverageIds; // Can be empty or null
    @Column(nullable = true)
    private double totalAmount;
}