package ecommerce.consiti.ecommerce.repository;

import ecommerce.consiti.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
