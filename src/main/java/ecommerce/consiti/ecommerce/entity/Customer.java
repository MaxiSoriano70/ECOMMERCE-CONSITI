package ecommerce.consiti.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @Column(nullable = false)
    @NotNull(message = "El nombre no puede ser nulo")
    private String firstName;
    @Column(nullable = false)
    @NotNull(message = "El apellido no puede ser nulo")
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email(message = "El correo electrónico debe ser válido")
    @NotNull(message = "El email no puede ser nulo")
    private String email;
    private String phoneNumber;
}
