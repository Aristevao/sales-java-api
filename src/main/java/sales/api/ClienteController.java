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
}
