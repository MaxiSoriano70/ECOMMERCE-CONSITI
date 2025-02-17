package ecommerce.consiti.ecommerce.entity;

import jakarta.persistence.*;
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
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product productId;
    private Integer quantity;
    private Float preci;
}
