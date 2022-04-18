package ru.gb.dao;

import ru.gb.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private EntityManagerFactory emFactory;

    public ProductDAO(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Product findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products = em.createQuery("from Product", Product.class).getResultList();
        em.close();
        return new ArrayList<>(products);
    }

    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class,id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
        em.getTransaction().commit();
        em.close();
    }

}

