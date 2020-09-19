package mk.finki.ukim.emt.supplementscatalog.domain.repository;

import mk.finki.ukim.emt.supplementscatalog.domain.model.Type;
import mk.finki.ukim.emt.supplementscatalog.domain.model.TypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, TypeId> {

}
