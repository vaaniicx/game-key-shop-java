package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.category.CategoryResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.request.UpdateCategoryRequest;
import at.vaaniicx.lap.model.request.category.RegisterCategoryRequest;
import at.vaaniicx.lap.model.response.category.CategoryResponse;
import at.vaaniicx.lap.model.response.category.GamesByCategoryResponse;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryGameService categoryGameService;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryGameService categoryGameService) {
        this.categoryService = categoryService;
        this.categoryGameService = categoryGameService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        List<CategoryResponse> response = categoryService.getAllCategories()
                .stream()
                .filter(Objects::nonNull)
                .map(CategoryResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long id) {

        CategoryEntity category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(CategoryResponseMapper.INSTANCE.entityToResponse(category));
    }

    @PostMapping("/update")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody @Validated UpdateCategoryRequest request) {

        CategoryEntity category = categoryService.getCategoryById(request.getId());
        category.setCategory(request.getCategory());
        category.setDescription(request.getDescription());

        CategoryEntity updatedCategory = categoryService.save(category);

        return ResponseEntity.ok(CategoryResponseMapper.INSTANCE.entityToResponse(updatedCategory));
    }

    @PostMapping("/register")
    public ResponseEntity<CategoryResponse> registerCategory(@RequestBody @Validated RegisterCategoryRequest request) {

        CategoryEntity category = new CategoryEntity();
        category.setCategory(request.getCategory());
        category.setDescription(request.getDescription());

        CategoryEntity persistedCategory = categoryService.save(category);

        return ResponseEntity.ok(CategoryResponseMapper.INSTANCE.entityToResponse(persistedCategory));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Long id) {

        categoryService.deleteById(id);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByCategoryResponse>> getGamesByCategory(@PathVariable("id") Long id) {

        List<GamesByCategoryResponse> response = categoryGameService.getGamesByCategoryId(id)
                .stream()
                .map(game ->
                        new GamesByCategoryResponse(game.getGame().getId(), game.getGame().getTitle()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}