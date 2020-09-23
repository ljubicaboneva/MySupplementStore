package mk.finki.ukim.emt.supplementscatalog;

import mk.ukim.finki.emt.sharedkernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
@SpringBootApplication
public class SupplementsCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplementsCatalogApplication.class, args);
    }

}
