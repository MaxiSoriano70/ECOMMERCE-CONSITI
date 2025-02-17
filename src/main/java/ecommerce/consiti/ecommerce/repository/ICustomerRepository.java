package ecommerce.consiti.ecommerce.repository;

import ecommerce.consiti.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}
