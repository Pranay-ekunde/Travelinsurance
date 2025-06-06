package review.phase.insurance_travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private tripRepository tripRepo;
    @Autowired
    private CoverageRepository service;

    // Create a new trip
    public trip createTrip(trip trip) {
        return tripRepo.save(trip);
    }

    // Retrieve all trips
    public List<trip> getAllTrips() {
        return (List<trip>) tripRepo.findAll();
    }

    // Retrieve trip by ID
    public Optional<trip> getTripById(Long tripId) {
        return tripRepo.findById(tripId);
    }

    // Update an existing trip
    // public trip updateTrip(Long tripId, trip tripDetails) {
    //     return tripRepo.findById(tripId).map(trip -> {
    //         trip.setDestinationCountry(tripDetails.getDestinationCountry());
    //         trip.setStartDate(tripDetails.getStartDate());
    //         trip.setEndDate(tripDetails.getEndDate());
    //         trip.setTravelerAge(tripDetails.getTravelerAge());
    //         trip.setTravelerName(tripDetails.getTravelerName());
    //         trip.setContact(tripDetails.getContact());
    //         trip.setSelectedCoverageIds(tripDetails.getSelectedCoverageIds());
    //         return tripRepo.save(trip);
    //     }).orElse(null);
    // }

    public trip updatingAndCalculating(TripRequest tripRequest){
        long total = 0;
        for (Long long1 : tripRequest.getSelectedCoverageIds()) {
            total+=service.findById(long1).get().getAmount();
        }
        total+=(total*0.180);
        trip tr = tripRepo.findById(tripRequest.getTripId()).orElse(null);
        tr.setSelectedCoverageIds(tripRequest.getSelectedCoverageIds());
        tr.setTotalAmount(total);
        return tripRepo.save(tr);
    }

    // Delete a trip
    public boolean deleteTrip(Long tripId) {
        if (tripRepo.existsById(tripId)) {
            tripRepo.deleteById(tripId);
            return true;
        }
        return false;
    }

}
