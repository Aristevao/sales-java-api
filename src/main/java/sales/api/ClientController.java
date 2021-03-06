package sales.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sales.domain.entity.Client;
import sales.domain.repository.ClientRepository;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Api("Clients API")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client save(@RequestBody @Valid Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody @Valid Client clientReq, @PathVariable Integer id) {
        clientRepository.findById(id)
                .map(foundClient -> {
                    clientReq.setId(id);
                    clientRepository.save(clientReq);
                    return foundClient;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @GetMapping("{id}")
    @ApiOperation("Get a client's data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public Client findById(@PathVariable Integer id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Client> findAll(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Client> example = Example.of(filter, matcher);
        return clientRepository.findAll(example);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientRepository
                .findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found"));
    }
}
