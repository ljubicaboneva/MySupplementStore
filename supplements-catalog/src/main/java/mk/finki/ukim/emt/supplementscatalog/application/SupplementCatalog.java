package mk.finki.ukim.emt.supplementscatalog.application;

import lombok.NonNull;
import mk.finki.ukim.emt.supplementscatalog.domain.model.Supplement;
import mk.finki.ukim.emt.supplementscatalog.domain.model.SupplementId;
import mk.finki.ukim.emt.supplementscatalog.domain.repository.SupplementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class SupplementCatalog {

    private final SupplementRepository supplementRepository;

    public SupplementCatalog(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }
    
    public List<Supplement> findAll(){
        return supplementRepository.findAll();
    }

    public Optional<Supplement> findById(@NonNull SupplementId supplementId){
        Objects.requireNonNull(supplementId, "supplementId must not be null");
        return supplementRepository.findById(supplementId);
    }
}
