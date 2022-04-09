package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.category.CategoryNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.repository.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);

        if (!entity.isPresent()) {
            throw new CategoryNotFoundException();
        }

        return entity.get();
    }

    public CategoryEntity registerCategory(String category, String description) {
        return categoryRepository.save(CategoryEntity.builder().category(category).description(description).build());
    }

    public CategoryEntity updateCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public boolean deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException();
        }

        return !categoryRepository.existsById(id);
    }
}
