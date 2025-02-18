package ecommerce.consiti.ecommerce.controller;

import ecommerce.consiti.ecommerce.entity.Customer;
import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    public IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<Order> crearOrden(@RequestBody Order order){
        Order ordenARetornar = orderService.createOrder(order);
        if(ordenARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(ordenARetornar);
        }
    }
    @GetMapping
    public ResponseEntity<List<Order>> traerTodos(){
        return ResponseEntity.ok(orderService.searchAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> buscarOrdenPorId(@PathVariable Integer id){
        Optional<Order> orden = orderService.searchForId(id);
        if(orden.isPresent()){
            Order ordenARetornar = orden.get();
            return ResponseEntity.ok(ordenARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarOrden(@PathVariable Integer id, @RequestBody Order order) {
        boolean actualizado = orderService.updateOrder(id, order);

        if (actualizado) {
            return ResponseEntity.ok("{\"message\": \"orden modificada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"orden no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOrden(@PathVariable Integer id){
        Optional<Order> ordenOptional = orderService.searchForId(id);
        if (ordenOptional.isPresent()) {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("{\"message\": \"orden eliminada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"orden no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }
}
