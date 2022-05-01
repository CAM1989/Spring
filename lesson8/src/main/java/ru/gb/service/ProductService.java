package ru.gb.service;

import org.springframework.data.domain.Page;
import ru.gb.dto.ProductDto;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Integer page, Integer size, String sortField);

    Optional<ProductDto> findById(long id);

    ProductDto save(ProductDto product);

    void deleteById(long id);
}
