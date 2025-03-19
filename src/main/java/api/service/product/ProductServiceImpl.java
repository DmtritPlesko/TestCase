package api.service.product;

import api.dto.ProductDto;
import api.exception.NotFoundException;
import api.mapper.ProductMapper;
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

}
