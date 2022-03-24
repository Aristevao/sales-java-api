package sales.api;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sales.domain.entity.Produto;
import sales.domain.repository.ProductRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto product) {
        return productRepository.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody Produto product, @PathVariable Integer id) {
        productRepository.findById(id)
                .map(foundProduct -> {
                    product.setId(id);
                    productRepository.save(product);
                    return foundProduct;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @GetMapping("{id}")
    public Produto findById(@PathVariable Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @GetMapping
    public List<Produto> findAll(Produto filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Produto> example = Example.of(filter, matcher);
        return productRepository.findAll(example);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productRepository
                .findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }
}
