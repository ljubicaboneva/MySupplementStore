package mk.finki.ukim.emt.supplementscatalog.domain.repository;

import mk.finki.ukim.emt.supplementscatalog.domain.model.Supplement;
import mk.finki.ukim.emt.supplementscatalog.domain.model.SupplementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementRepository extends JpaRepository<Supplement, SupplementId> {
}
