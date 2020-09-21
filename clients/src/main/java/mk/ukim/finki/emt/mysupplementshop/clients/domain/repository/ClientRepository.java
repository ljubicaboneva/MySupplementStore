package mk.ukim.finki.emt.mysupplementshop.clients.domain.repository;

import mk.ukim.finki.emt.mysupplementshop.clients.domain.model.Client;
import mk.ukim.finki.emt.mysupplementshop.clients.domain.model.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientId> {
}
