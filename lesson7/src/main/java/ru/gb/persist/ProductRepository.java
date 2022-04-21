package ru.gb.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p " +
            " from Product p " +
            "where p.name like :name")
            List<Product> findProductByProductLike(@Param("name") String name);

}

