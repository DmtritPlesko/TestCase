package api.mapper;

import api.dto.ProductDto;
import api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "id",ignore = true)
    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

}
