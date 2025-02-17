package ecommerce.consiti.ecommerce.repository;

import ecommerce.consiti.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
