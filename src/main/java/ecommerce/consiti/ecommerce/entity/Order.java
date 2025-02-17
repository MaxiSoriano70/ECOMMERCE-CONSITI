package ecommerce.consiti.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Date orderDate;
    private Float totalAmount;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customerId;
}
