package ecommerce.consiti.ecommerce.service;

import ecommerce.consiti.ecommerce.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category createCategory(Category category);
    Optional<Category> searchForId(int id);
    List<Category> searchAll();
    void updateCategory(Category category);
    void deleteCategory(Integer id);
}
