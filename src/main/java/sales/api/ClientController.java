package sales.api;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sales.domain.entity.Cliente;
import sales.domain.repository.ClientesRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientesRepository clientesRepository;

    public ClientController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody Cliente clientReq, @PathVariable Integer id) {
        clientesRepository.findById(id)
                .map(foundClient -> {
                    clientReq.setId(id);
                    clientesRepository.save(clientReq);
                    return foundClient;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @GetMapping("{id}")
    public Cliente findById(@PathVariable Integer id) {
        return clientesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Cliente> findAll(Cliente filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Cliente> example = Example.of(filter, matcher);
        return clientesRepository.findAll(example);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientesRepository
                .findById(id)
                .map(client -> {
                    clientesRepository.delete(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }
}
