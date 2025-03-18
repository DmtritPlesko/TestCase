package api.controller;

import api.dto.ProductDto;
import api.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@Valid @RequestBody ProductDto productDto) {
        return service.addProduct(productDto);
    }

    @PatchMapping(path = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable("productId") Long id,
                                    @RequestBody ProductDto productDto ) {
        return service.updateProduct(id,productDto);
    }

    @DeleteMapping(path = "/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long id) {
        service.deleteProduct(id);
    }
}
