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
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(nullable = false)
    private String productName;
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Float price;
    @Min(value = 0, message = "La cantidad en stock no puede ser negativa")
    private Integer stockQuantity;
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    @NotNull(message = "La categoría no puede ser nula")
    private Category category;
}
