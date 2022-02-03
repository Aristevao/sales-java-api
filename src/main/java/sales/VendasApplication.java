package sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sales.domain.entity.Cliente;
import sales.domain.repository.ClientesRepository;

@SpringBootApplication
public class VendasApplication {

//    @Bean
//    public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientesRepository) {
//        return args -> {
//            Cliente c = new Cliente(null, "Marie");
//            clientesRepository.save(c);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}