package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.KeyCodeNotFoundException;
import at.vaaniicx.lap.exception.RoleNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.KeyCodeEntity;
import at.vaaniicx.lap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);

        if (!entity.isPresent()) {
            throw new KeyCodeNotFoundException();
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
            throw new RoleNotFoundException();
        }

        return !categoryRepository.existsById(id);
    }
}
