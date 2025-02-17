package ecommerce.consiti.ecommerce.controller;

import ecommerce.consiti.ecommerce.entity.Order;
import ecommerce.consiti.ecommerce.entity.OrderItem;
import ecommerce.consiti.ecommerce.service.IOrdenItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/OrdenItem")
public class OrderItemController {
    public IOrdenItemService ordenItemService;

    public OrderItemController(IOrdenItemService ordenItemService) {
        this.ordenItemService = ordenItemService;
    }
    @PostMapping
    public ResponseEntity<OrderItem> crearOrdenItem(@RequestBody OrderItem orderItem){
        OrderItem ordenItemARetornar = ordenItemService.createOrderItem(orderItem);
        if(ordenItemARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(ordenItemARetornar);
        }
    }
    @GetMapping
    public ResponseEntity<List<OrderItem>> traerTodos(){
        return ResponseEntity.ok(ordenItemService.searchAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> buscarOrdenItemPorId(@PathVariable Integer id){
        Optional<OrderItem> ordenItem = ordenItemService.searchForId(id);
        if(ordenItem.isPresent()){
            OrderItem ordenItemARetornar = ordenItem.get();
            return ResponseEntity.ok(ordenItemARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOrdenItem(@RequestBody OrderItem orderItem){
        Optional<OrderItem> ordenItemOptional = ordenItemService.searchForId(orderItem.getOrderItemId());
        if (ordenItemOptional.isPresent()) {
            ordenItemService.updateOrderItem(orderItem);
            return ResponseEntity.ok("{\"message\": \"ordenItem modificado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"ordenItem no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOrdenItem(@PathVariable Integer id){
        Optional<OrderItem> ordenItemOptional = ordenItemService.searchForId(id);
        if (ordenItemOptional.isPresent()) {
            ordenItemService.deleteOrderItem(id);
            return ResponseEntity.ok("{\"message\": \"ordenItem eliminada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"ordenItem no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }
}
