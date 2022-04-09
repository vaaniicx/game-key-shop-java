package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.category.CategoryResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.request.UpdateCategoryRequest;
import at.vaaniicx.lap.model.response.category.CategoryResponse;
import at.vaaniicx.lap.model.response.category.GamesByCategoryResponse;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.CategoryService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryGameService categoryGameService;
    private final CategoryResponseMapper categoryMapper = Mappers.getMapper(CategoryResponseMapper.class);

    public CategoryController(CategoryService categoryService, CategoryGameService categoryGameService) {
        this.categoryService = categoryService;
        this.categoryGameService = categoryGameService;
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        List<CategoryEntity> categories = categoryService.getAllCategories();

        return categories.stream().map(categoryMapper::entityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable("id") Long id) {
        CategoryEntity category = categoryService.getCategoryById(id);

        return categoryMapper.entityToResponse(category);
    }

    @PutMapping("/update")
    public CategoryResponse updateCategory(@RequestBody @Validated UpdateCategoryRequest request) {
        CategoryEntity categoryById = categoryService.getCategoryById(request.getId());
        categoryById.setCategory(request.getCategory());
        categoryById.setDescription(request.getDescription());

        CategoryEntity updatedCategory = categoryService.updateCategory(categoryById);

        return categoryMapper.entityToResponse(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Long id) {
        boolean deleted = categoryService.deleteCategory(id);

        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByCategoryResponse>> getGamesByCategory(@PathVariable("id") Long id) {
        List<CategoryGameEntity> gamesByCategoryId = categoryGameService.getGamesByCategoryId(id);

        List<GamesByCategoryResponse> response = gamesByCategoryId.stream().map(game ->
                new GamesByCategoryResponse(game.getGame().getId(), game.getGame().getTitle())).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
