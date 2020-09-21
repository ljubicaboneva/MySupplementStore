package mk.finki.ukim.emt.supplementscatalog.port.rest;

import mk.finki.ukim.emt.supplementscatalog.application.SupplementCatalog;
import mk.finki.ukim.emt.supplementscatalog.domain.model.Supplement;
import mk.finki.ukim.emt.supplementscatalog.domain.model.SupplementId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplements")
public class SupplementCatalogController {

    private final SupplementCatalog supplementCatalog;

    public SupplementCatalogController(SupplementCatalog supplementCatalog) {
        this.supplementCatalog = supplementCatalog;
    }

    @GetMapping
    public List<Supplement> findAll(){
        return supplementCatalog.findAll();
    }

    @GetMapping("/{supplementId}")
    public ResponseEntity<Supplement> findById(@PathVariable String supplementId){
        return supplementCatalog.findById(new SupplementId(supplementId)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
