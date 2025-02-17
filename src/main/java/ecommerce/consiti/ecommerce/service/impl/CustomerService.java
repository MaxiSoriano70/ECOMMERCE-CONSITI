package ecommerce.consiti.ecommerce.service.impl;

import ecommerce.consiti.ecommerce.entity.Customer;
import ecommerce.consiti.ecommerce.repository.ICustomerRepository;
import ecommerce.consiti.ecommerce.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> searchForId(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> searchAll() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
