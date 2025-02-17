package ecommerce.consiti.ecommerce.repository;

import ecommerce.consiti.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
