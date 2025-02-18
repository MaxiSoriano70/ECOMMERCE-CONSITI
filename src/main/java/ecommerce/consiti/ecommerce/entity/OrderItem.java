package ecommerce.consiti.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    @NotNull(message = "La orden es obligatoria")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    @NotNull(message = "El producto es obligatorio")
    private Product product;
    @Min(value = 1, message = "La cantidad no puede ser menor que 1")
    private Integer quantity = 1;
    private Float preci;
    @PrePersist
    public void prePersist() {
        if (quantity == null || quantity < 1) {
            quantity = 1;
        }

        if (product == null) {
            throw new IllegalStateException("El producto no puede ser nulo en OrderItem");
        }

        if (product.getPrice() == null) {
            throw new IllegalStateException("El precio del producto no puede ser nulo en OrderItem");
        }

        preci = product.getPrice() * quantity;

        if (order != null) {
            if (order.getTotalAmount() == null) {
                order.setTotalAmount(0.0f);
            }
            order.setTotalAmount(order.getTotalAmount() + preci);
        }
    }
}
