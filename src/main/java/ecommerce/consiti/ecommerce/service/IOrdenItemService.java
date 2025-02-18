package ecommerce.consiti.ecommerce.service;

import ecommerce.consiti.ecommerce.entity.Customer;
import ecommerce.consiti.ecommerce.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface IOrdenItemService {
    OrderItem createOrderItem(OrderItem orderItem);
    Optional<OrderItem> searchForId(int id);
    List<OrderItem> searchAll();
    boolean updateOrderItem(Integer id, OrderItem orderItem);
    void deleteOrderItem(Integer id);
}
