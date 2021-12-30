package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.CategoryNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryEntity> getAll() {
        return new ArrayList<>(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public CategoryEntity getById(@PathVariable("id") Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);

        if (!entity.isPresent()) {
            throw new CategoryNotFoundException();
        }

        return entity.get();
    }
}
