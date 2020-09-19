package mk.finki.ukim.emt.supplementscatalog.application;

import mk.finki.ukim.emt.supplementscatalog.domain.repository.SupplementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SupplementCatalog {

    private final SupplementRepository supplementRepository;

    public SupplementCatalog(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }
}
