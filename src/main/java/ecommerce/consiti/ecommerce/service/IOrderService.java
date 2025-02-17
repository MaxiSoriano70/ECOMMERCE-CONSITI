package ecommerce.consiti.ecommerce.service;

import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order createOrder(Order order);
    Optional<Order> searchForId(int id);
    List<Order> searchAll();
    void updateOrder(Order order);
    void deleteOrder(Integer id);
}
