package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.gb.persist.ProductRepository;
import ru.gb.persist.ProductRepositoryImpl;

@Configuration
@ComponentScan("ru.gb")
public class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    @Scope("prototype")
    public CartService cartService() {
        return new CartService();
    }
}
