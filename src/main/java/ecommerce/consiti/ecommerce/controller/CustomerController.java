package ecommerce.consiti.ecommerce.controller;

import ecommerce.consiti.ecommerce.entity.Category;
import ecommerce.consiti.ecommerce.entity.Customer;
import ecommerce.consiti.ecommerce.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    public ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> crearCliente(@RequestBody Customer customer) {
        Customer clienteARetornar = customerService.createCustomer(customer);
        if (clienteARetornar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteARetornar);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> traerTodos() {
        return ResponseEntity.ok(customerService.searchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> buscarClientePorId(@PathVariable Integer id) {
        Optional<Customer> cliente = customerService.searchForId(id);
        if (cliente.isPresent()) {
            Customer clienteARetornar = cliente.get();
            return ResponseEntity.ok(clienteARetornar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Integer id, @RequestBody Customer customer) {
        boolean actualizado = customerService.updateCustomer(id, customer);

        if (actualizado) {
            return ResponseEntity.ok("{\"message\": \"cliente modificado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"cliente no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarCliente(@PathVariable Integer id){
        Optional<Customer> clienteOptional = customerService.searchForId(id);
        if (clienteOptional.isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("{\"message\": \"cliente eliminado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"cliente no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }
}
