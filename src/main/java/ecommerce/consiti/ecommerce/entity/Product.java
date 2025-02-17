package ecommerce.consiti.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private Float price;
    private Integer stockQuantity;
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;
}
