package ecommerce.consiti.ecommerce.repository;

import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrder(Order order);
}
