package ecommerce.consiti.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(nullable = false)
    @Min(value = 0, message = "El totalAmount no puede ser negativo")
    private Float totalAmount = 0f;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    @NotNull(message = "El cliente es obligatorio")
    private Customer customer;
    @PrePersist
    public void prePersist() {
        if (orderDate == null) {
            orderDate = new Date();
        }
        if (totalAmount == null) {
            totalAmount = 0f;
        }
    }
}
