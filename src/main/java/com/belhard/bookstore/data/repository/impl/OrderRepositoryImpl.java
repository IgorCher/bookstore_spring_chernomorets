package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    private static final String GET_ALL = "from Order";
    private static final String GET_BY_USER_ID = "from Order o where o.user.id = :user_id";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Order find(Long id) {
        return manager.find(Order.class, id);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return manager.createQuery(GET_ALL, Order.class).getResultList();
    }

    @Override
    @Transactional
    public List<Order> findByUserId(Long userId) {
        TypedQuery<Order> q = manager.createQuery(GET_BY_USER_ID, Order.class);
        q.setParameter("user_id", userId);
        return q.getResultList();
    }

    @Override
    public Order save(Order entity) {
        if (entity.getId() != null) {
            manager.merge(entity);
        } else {
            manager.persist(entity);
        }
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Order order = manager.find(Order.class, id);
        if (order != null) {
            manager.remove(order);
            return true;
        }
        return false;
    }
}
