package ecommerce.consiti.ecommerce.service.impl;

import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.entity.OrderItem;
import ecommerce.consiti.ecommerce.entity.Product;
import ecommerce.consiti.ecommerce.repository.ICustomerRepository;
import ecommerce.consiti.ecommerce.repository.IOrderItemRepository;
import ecommerce.consiti.ecommerce.repository.IOrderRepository;
import ecommerce.consiti.ecommerce.repository.IProductRepository;
import ecommerce.consiti.ecommerce.service.IOrdenItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrdenItemService {

    private final IOrderItemRepository orderItemRepository;

    public OrderItemService(IOrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;

    @Transactional
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        /*Buscmos producto y orden*/
        Product product = productRepository.findById(orderItem.getProduct().getProductId())
                .orElseThrow(() -> new IllegalArgumentException("El producto no existe"));

        Order order = orderRepository.findById(orderItem.getOrder().getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("La orden no existe"));

        /*Validamos*/
        if (product.getPrice() == null) {
            throw new IllegalArgumentException("El producto tiene precio NULL");
        }

        if (orderItem.getQuantity() == null || orderItem.getQuantity() < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }

        /*Calcular precio del OrderItem*/
        orderItem.setPreci(product.getPrice() * orderItem.getQuantity());

        /*Obtener los ítems existentes de la orden*/
        List<OrderItem> existingItems = orderItemRepository.findByOrder(order);

        /* Calcular el total actual de la orden*/
        float currentTotal = existingItems.stream()
                .map(OrderItem::getPreci)
                .reduce(0.0f, Float::sum);

        /* Actualizar el total de la orden*/
        order.setTotalAmount(currentTotal + orderItem.getPreci());

        /*Asignar la orden y el producto al OrderItem*/
        orderItem.setOrder(order);
        orderItem.setProduct(product);

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
    public boolean updateOrderItem(Integer id, OrderItem orderItem) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isPresent()) {
            OrderItem existingOrderItem = orderItemOptional.get();

            if (orderItem.getProduct() != null) {
                Optional<Product> productOptional = productRepository.findById(orderItem.getProduct().getProductId());
                if (!productOptional.isPresent()) {
                    throw new IllegalArgumentException("El producto no existe");
                }
                existingOrderItem.setProduct(productOptional.get());
            }

            if (orderItem.getQuantity() != null && orderItem.getQuantity() >= 1) {
                existingOrderItem.setQuantity(orderItem.getQuantity());
            } else {
                throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
            }

            existingOrderItem.setPreci(existingOrderItem.getProduct().getPrice() * existingOrderItem.getQuantity());
            if (existingOrderItem.getOrder() != null) {
                existingOrderItem.getOrder().setTotalAmount(existingOrderItem.getOrder().getTotalAmount() + existingOrderItem.getPreci());
            }

            orderItemRepository.save(existingOrderItem);
            return true;
        }
        return false;
    }


    @Override
    public void deleteOrderItem(Integer id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isPresent()) {
            OrderItem orderItem = orderItemOptional.get();
            if (orderItem.getOrder() != null) {
                orderItem.getOrder().setTotalAmount(orderItem.getOrder().getTotalAmount() - orderItem.getPreci());
            }
            orderItemRepository.deleteById(id);
        }
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }
}
