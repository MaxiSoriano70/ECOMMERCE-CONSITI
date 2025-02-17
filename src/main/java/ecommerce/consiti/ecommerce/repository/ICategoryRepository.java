package ecommerce.consiti.ecommerce.repository;

import ecommerce.consiti.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
