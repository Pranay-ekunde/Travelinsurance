package review.phase.insurance_travel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TripServiceTest {

    @Mock
    private tripRepository tripRepository;
    @Mock
    private CoverageRepository coverageRepository;

    @InjectMocks
    private TripService tripService;

    private trip trp;
    private TripRequest tRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trp = new trip();
        trp.setTripId(1L);
        trp.setDestinationCountry("France");
        trp.setStartDate(LocalDate.of(2025, 7, 1));
        trp.setEndDate(LocalDate.of(2025, 7, 15));
        trp.setTravelerAge(30);
        trp.setTravelerName("Pranay");
        trp.setContact("pranay@example.com");
    }

    @Test
    void testCreateTrip() {
        when(tripRepository.save(any(trip.class))).thenReturn(trp);

        trip savedTrip = tripService.createTrip(trp);

        assertNotNull(savedTrip);
        assertEquals("France", savedTrip.getDestinationCountry());
        verify(tripRepository, times(1)).save(trp);
    }

    @Test
    void testGetTripById() {
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trp));

        Optional<trip> result = tripService.getTripById(1L);

        assertNotNull(result);
        assertEquals("France", result.get().getDestinationCountry());
        verify(tripRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllTrips() {
        List<trip> tripList = Arrays.asList(trp, new trip());
        when(tripRepository.findAll()).thenReturn(tripList);

        List<trip> result = tripService.getAllTrips();

        assertEquals(2, result.size());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    void testUpdateTrip() {
        Coverage coverage1 = new Coverage();coverage1.setId(101L);coverage1.setAmount(100);coverage1.setType("medical");
        Coverage coverage2 = new Coverage();coverage2.setId(102L);coverage2.setAmount(10);coverage2.setType("emergency");
        Coverage coverage3 = new Coverage();coverage3.setId(103L);coverage3.setAmount(90);coverage3.setType("roll");
        TripRequest updatedTrip = new TripRequest();
        updatedTrip.setTripId(1L);
        updatedTrip.setDestinationCountry("Germany");
        updatedTrip.setStartDate(LocalDate.of(2025, 8, 1));
        updatedTrip.setEndDate(LocalDate.of(2025, 8, 15));
        updatedTrip.setTravelerAge(28);
        updatedTrip.setTravelerName("John");
        updatedTrip.setContact("john@example.com");
        updatedTrip.setSelectedCoverageIds(List.of(101L, 102L, 103L));

        trip trip = new trip();
        trip.setTripId(1L);
        trip.setDestinationCountry("Germany");
        trip.setStartDate(LocalDate.of(2025, 8, 1));
        trip.setEndDate(LocalDate.of(2025, 8, 15));
        trip.setTravelerAge(28);
        trip.setTravelerName("John");
        trip.setContact("john@example.com");
        trip.setSelectedCoverageIds(List.of(101L, 102L, 103L));


        when(coverageRepository.findById(101L)).thenReturn(Optional.of(coverage1));
        when(coverageRepository.findById(102L)).thenReturn(Optional.of(coverage2));
        when(coverageRepository.findById(103L)).thenReturn(Optional.of(coverage3));
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trp));
        when(tripRepository.save(any(trip.class))).thenReturn(trip);

        trip result = tripService.updatingAndCalculating(updatedTrip);

        assertNotNull(result);
        assertEquals("Germany", result.getDestinationCountry());
        verify(tripRepository).findById(1L);
        verify(tripRepository).save(any(trip.class));
    }

    @Test
    void testDeleteTrip() {
        when(tripRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tripRepository).deleteById(1L);

        tripService.deleteTrip(1L);

        verify(tripRepository, times(1)).deleteById(1L);
    }

}