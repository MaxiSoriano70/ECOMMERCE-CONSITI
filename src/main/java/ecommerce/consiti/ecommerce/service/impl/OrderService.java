package ecommerce.consiti.ecommerce.service.impl;

import ecommerce.consiti.ecommerce.entity.Customer;
import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.repository.ICategoryRepository;
import ecommerce.consiti.ecommerce.repository.ICustomerRepository;
import ecommerce.consiti.ecommerce.repository.IOrderRepository;
import ecommerce.consiti.ecommerce.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderService {
    private IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Order createOrder(Order order) {
        if (order.getCustomer() == null || order.getCustomer().getCustomerId() == null) {
            throw new RuntimeException("La orden debe tener un cliente valido");
        }
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrad@"));

        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> searchForId(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> searchAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean updateOrder(Integer id, Order order) {
        Optional<Order> ordenOptional = orderRepository.findById(id);
        if (ordenOptional.isPresent()) {
            Order ordenExistente = ordenOptional.get();
            if (order.getOrderDate() != null) {
                ordenExistente.setOrderDate(order.getOrderDate());
            }
            if (order.getTotalAmount() != null) {
                ordenExistente.setTotalAmount(order.getTotalAmount());
            }
            if (order.getCustomer() != null && order.getCustomer().getCustomerId() != null) {
                Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                ordenExistente.setCustomer(customer);
            }
            orderRepository.save(ordenExistente);
            return true;
        }
        return false;
    }
    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
