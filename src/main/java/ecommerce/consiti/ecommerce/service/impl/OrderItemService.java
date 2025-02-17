package ecommerce.consiti.ecommerce.service.impl;

import ecommerce.consiti.ecommerce.entity.OrderItem;
import ecommerce.consiti.ecommerce.repository.IOrderItemRepository;
import ecommerce.consiti.ecommerce.service.IOrdenItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderItemService implements IOrdenItemService {
    private IOrderItemRepository orderItemRepository;

    public OrderItemService(IOrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Optional<OrderItem> searchForId(int id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItem> searchAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }
}
