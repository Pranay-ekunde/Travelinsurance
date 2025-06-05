package review.phase.insurance_travel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coverage")
public class CoverageApi {

    @Autowired
    private CoverageService service;

    @PostMapping("/new")
    public ResponseEntity<Coverage> callCreate(@Valid @RequestBody Coverage coverage) {
        Coverage created = service.addCoverage(coverage);
        return ResponseEntity.ok(created);
    }


    @GetMapping("/view")
    public ResponseEntity<List<Coverage>> callReadAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<?> callReadOne(@PathVariable long id) {
        Coverage found = service.readById(id);
        if (found == null) {
            return ResponseEntity.status(404).body("Coverage with ID " + id + " not found.");
        }
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> callDelete(@PathVariable long id) {
        if (service.readById(id) == null) {
            return ResponseEntity.status(404).body("Coverage with ID " + id + " not found.");
        }
        service.eraseById(id);
        return ResponseEntity.ok("Coverage with ID " + id + " deleted successfully.");
    }
}
