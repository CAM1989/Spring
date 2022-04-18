package ru.gb.dao;

import ru.gb.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private EntityManagerFactory emFactory;

    public CustomerDAO(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Customer findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }

    public List<Customer> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Customer> customers = em.createQuery("from Customer", Customer.class).getResultList();
        em.close();
        return new ArrayList<>(customers);
    }

    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class,id);
        em.remove(customer);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Customer customer) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            em.merge(customer);
        }
        em.getTransaction().commit();
        em.close();
    }

}
