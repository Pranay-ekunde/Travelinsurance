package review.phase.insurance_travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripApi {
    

    @Autowired
    private TripService tripService;

    // Create a new trip
    @PostMapping("/")
    public ResponseEntity<trip> createTrip(@RequestBody trip trp) {
        trip createdTrip = tripService.createTrip(trp);
        return ResponseEntity.ok(createdTrip);
    }

    // Retrieve all trips
    @GetMapping("/get/all")
    public ResponseEntity<List<trip>> getAllTrips() {
        List<trip> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    // Retrieve a trip by ID
    @GetMapping("/get/{tripId}")
    public ResponseEntity<trip> getTripById(@PathVariable Long tripId) {
        Optional<trip> trip = tripService.getTripById(tripId);
        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update trip coverage and calculate the total amount
    @PutMapping("/update")
    public ResponseEntity<trip> updateTripCoverage(@RequestBody TripRequest tripRequest) {
        trip updatedTrip = tripService.updatingAndCalculating(tripRequest);
        return updatedTrip != null ? ResponseEntity.ok(updatedTrip) : ResponseEntity.notFound().build();
    }

    // Delete a trip
    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
        boolean deleted = tripService.deleteTrip(tripId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
