package api.service.product;

import api.dto.ProductDto;
import api.exception.NotFoundException;
import api.mapper.ProductMapper;
import api.model.Product;
import api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private static final Long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "Test Product";

    @Test
    void addProduct_Success() {

        Product product = new Product(PRODUCT_ID, PRODUCT_NAME);
        ProductDto productDto = new ProductDto(PRODUCT_NAME);

        when(mapper.toProduct(any(ProductDto.class))).thenReturn(product);
        when(mapper.toProductDto(any(Product.class))).thenReturn(productDto);
        when(repository.save(any(Product.class))).thenReturn(product);

        ProductDto result = productService.addProduct(productDto);

        assertNotNull(result);
        assertEquals(PRODUCT_NAME, result.getName());

        verify(repository, times(1)).save(any(Product.class));
        verify(mapper, times(1)).toProductDto(any(Product.class));
    }

}