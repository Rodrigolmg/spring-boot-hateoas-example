package br.senaigo.mobile.service.impl;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.repositories.OrderRepository;
import br.senaigo.mobile.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Override
    @Transactional
    public Order post(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order get(Order order) {
        return null;
    }

    @Override
    @Transactional
    public void put(Order order) {

    }

    @Override
    @Transactional
    public void delete(Order order) {

    }

    @Override
    @Transactional
    public void patch(Order entity) {

    }

    @Override
    @Transactional
    public List<Order> post(List<Order> entities) {
        return null;
    }

    @Override
    @Transactional
    public void put(List<Order> entities) {

    }

    @Override
    @Transactional
    public void delete(List<Order> entities) {

    }

    @Override
    @Transactional
    public void patch(List<Order> entities) {

    }

    @Override
    public List<Order> get() {
        return null;
    }
}
