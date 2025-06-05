package review.phase.insurance_travel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coverage {
    @Id
    @Min(value = 0, message = "Invalid id")
    private Long id;

    @Size(min = 3, message = "Invalid type of coverage")
    private String type;

    @Min(value = 2, message = "Invalid amount")
    private double amount;
}

