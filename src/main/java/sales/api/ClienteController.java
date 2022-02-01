package sales.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    public ClienteController() {
    }

    @RequestMapping(
            value = "/hello/{name}",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"}, // request body
            produces = {"application/json", "application/xml"}  // response body
    )
    @ResponseBody
    public String helloCliente(@PathVariable("name") String name) {
        return String.format("Hello %s ", name);
    }
}
