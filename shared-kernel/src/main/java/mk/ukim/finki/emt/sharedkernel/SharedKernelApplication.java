package mk.ukim.finki.emt.sharedkernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Configuration
@EnableScheduling
public class SharedKernelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedKernelApplication.class, args);
    }

}
