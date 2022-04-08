package ru.gb.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);


    @PostConstruct
    public void init() {
        this.insert(new Product("Milk", 95L));
        this.insert(new Product("Bread", 25L));
        this.insert(new Product("Meat", 325L));
        this.insert(new Product("Fish", 225L));
        this.insert(new Product("Cheese", 235L));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void insert(Product product) {
        Long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    @Override
    public void update(Product product) {
        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public void delete(Long id) {
        productMap.remove(id);
    }

    @Override
    public long getCount() {
        return productMap.size();
    }
}
