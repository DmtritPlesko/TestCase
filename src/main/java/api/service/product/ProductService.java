package api.service.product;

import api.dto.ProductDto;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

}
