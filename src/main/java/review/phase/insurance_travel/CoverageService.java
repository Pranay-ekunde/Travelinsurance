package review.phase.insurance_travel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CoverageService {
    @Autowired
    private CoverageRepository repo;

    // CRUD
    public Coverage addCoverage(Coverage coverage){
        return repo.save(coverage);
    }
    public List<Coverage> readAll(){
        return repo.findAll();
    }
    public Coverage readById(long id){
        return repo.findById(id).orElse(null);
    }
    public void eraseById(long id){
        repo.deleteById(id);
    }
    
}
