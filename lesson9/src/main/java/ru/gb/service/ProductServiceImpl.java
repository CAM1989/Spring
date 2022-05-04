package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.gb.dto.ProductDto;
import ru.gb.persist.Product;
import ru.gb.persist.ProductRepository;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sortField) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(ProductServiceImpl::productToDto);
    }

    @Override
    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id).map(ProductServiceImpl::productToDto);
    }

    @Override
    public ProductDto save(ProductDto product) {
        return productToDto(productRepository.save(
                new Product(
                        product.getId(),
                        product.getTitle(),
                        product.getPrice()
                )));
    }

    private static ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}