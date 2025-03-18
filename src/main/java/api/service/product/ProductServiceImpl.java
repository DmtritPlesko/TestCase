package api.service.product;

import api.dto.ProductDto;
import api.exception.NotFoundException;
import api.mapper.ProductMapper;
import api.model.Product;
import api.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    final ProductRepository repository;
    final ProductMapper mapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {

        return mapper.toProductDto(repository.save(mapper.toProduct(productDto)));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Продукт с id = " + id + " не найден"));

        mapper.updateProduct(product, productDto);

        return mapper.toProductDto(repository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

        throw new NotFoundException("Product c id = " + id + " не найден");
    }

}
