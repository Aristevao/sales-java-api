package sales.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    public ClienteController() {
    }

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente(@PathVariable("name") String name) {
        return String.format("Hello %s ", name);
    }
}
