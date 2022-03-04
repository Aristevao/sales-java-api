package sales.api;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sales.domain.entity.Produto;
import sales.domain.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto product) {
        return productRepository.save(product);
    }
}
