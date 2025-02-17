package ecommerce.consiti.ecommerce.service;

import ecommerce.consiti.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product createProduct(Product product);
    Optional<Product> searchForId(int id);
    List<Product> searchAll();
    boolean updateProduct(Product product);
    void deleteProduct(Integer id);
}
