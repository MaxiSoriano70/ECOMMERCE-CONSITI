package ecommerce.consiti.ecommerce.controller;

import ecommerce.consiti.ecommerce.entity.Category;
import ecommerce.consiti.ecommerce.entity.Product;
import ecommerce.consiti.ecommerce.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    public IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> crearProducto(@RequestBody Product product){
        Product productoARetornar = productService.createProduct(product);
        if(productoARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoARetornar);
        }
    }
    @GetMapping
    public ResponseEntity<List<Product>> traerTodos(){
        return ResponseEntity.ok(productService.searchAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarProductoPorId(@PathVariable Integer id){
        Optional<Product> productoOptional = productService.searchForId(id);
        if(productoOptional.isPresent()){
            Product productoARetornar = productoOptional.get();
            return ResponseEntity.ok(productoARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarProducto(@RequestBody Product product) {
        boolean actualizado = productService.updateProduct(product);

        if (actualizado) {
            return ResponseEntity.ok("{\"message\": \"producto modificado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"producto no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarProducto(@PathVariable Integer id){
        Optional<Product> productoOptional = productService.searchForId(id);
        if (productoOptional.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("{\"message\": \"producto eliminado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"producto no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }
}
