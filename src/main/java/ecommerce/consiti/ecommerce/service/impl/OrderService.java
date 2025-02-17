package ecommerce.consiti.ecommerce.service.impl;

import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.repository.IOrderRepository;
import ecommerce.consiti.ecommerce.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderService {
    private IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> searchForId(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> searchAll() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
