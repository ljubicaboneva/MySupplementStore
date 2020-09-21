package mk.ukim.finki.emt.mysupplementshop.clients;

import mk.ukim.finki.emt.mysupplementshop.clients.domain.model.Client;
import mk.ukim.finki.emt.mysupplementshop.clients.domain.model.ClientId;
import mk.ukim.finki.emt.mysupplementshop.clients.domain.repository.ClientRepository;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
public class DataGenerator {

    public final ClientRepository clientRepository;

    public DataGenerator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        if (clientRepository.findAll().size() == 0) {
            var clients = new ArrayList<Client>();
            clients.add(new Client(new ClientId("1"), Name.valueOf("Ljubica"),new Address("Kata Pockova 82","Strumica","Makedonija"),"ljubicaboneva@gmail.com"));
            clientRepository.saveAll(clients);
        }
    }
}
