package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.CategoryNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.request.UpdateCategoryRequest;
import at.vaaniicx.lap.repository.CategoryRepository;
import at.vaaniicx.lap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> getAll() {
        return new ArrayList<>(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public CategoryEntity getById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/update")
    public CategoryEntity updateCategory(@RequestBody @Validated UpdateCategoryRequest request) {
        CategoryEntity category = categoryService.getCategoryById(request.getId());

        category.setCategory(request.getCategory());
        category.setDescription(request.getDescription());

        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Long id) {
        boolean deleted = categoryService.deleteCategory(id);

        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
