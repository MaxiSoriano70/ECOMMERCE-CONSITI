package ecommerce.consiti.ecommerce.service.impl;

import ecommerce.consiti.ecommerce.entity.Category;
import ecommerce.consiti.ecommerce.entity.Product;
import ecommerce.consiti.ecommerce.repository.ICategoryRepository;
import ecommerce.consiti.ecommerce.repository.IProductRepository;
import ecommerce.consiti.ecommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IProductService {
    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Product createProduct(Product product) {
        if (product.getCategory() == null || product.getCategory().getCategoryId() == null) {
            throw new RuntimeException("El producto debe tener una categoría válida");
        }

        Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> searchForId(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> searchAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean updateProduct(Product product) {
        Optional<Product> productoOptional = productRepository.findById(product.getProductId());

        if (productoOptional.isPresent()) {
            Product productoExistente = productoOptional.get();

            if (product.getProductName() != null) {
                productoExistente.setProductName(product.getProductName());
            }
            if (product.getPrice() != null) {
                productoExistente.setPrice(product.getPrice());
            }
            if (product.getStockQuantity() != null) {
                productoExistente.setStockQuantity(product.getStockQuantity());
            }
            if (product.getCategory() != null) {
                productoExistente.setCategory(product.getCategory());
            }
            productRepository.save(productoExistente);
            return true;
        }
        return false;
    }
    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
