package ecommerce.consiti.ecommerce.service;

import ecommerce.consiti.ecommerce.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Customer createCustomer(Customer customer);
    Optional<Customer> searchForId(int id);
    List<Customer> searchAll();
    boolean updateCustomer(Integer id, Customer customer);
    void deleteCustomer(Integer id);
}
