package sales.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sales.domain.entity.Cliente;
import sales.domain.repository.ClientesRepository;

import java.util.Optional;

@Controller
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity findById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if (cliente.isPresent())
            return ResponseEntity.ok(cliente.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente savedCliente = clientesRepository.save(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if (cliente.isPresent()) {
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@RequestBody Cliente clientReq, @PathVariable Integer id) {
        Optional<Cliente> client = clientesRepository.findById(id);
        if (client.isPresent()) {
            clientReq.setId(id);
            Cliente response = clientesRepository.save(clientReq);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
