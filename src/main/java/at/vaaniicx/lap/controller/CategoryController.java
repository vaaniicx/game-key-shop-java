package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.dto.CategoryDTO;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.request.UpdateCategoryRequest;
import at.vaaniicx.lap.model.response.management.category.GamesByCategoryResponse;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.CategoryService;
import at.vaaniicx.lap.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryGameService categoryGameService;

    @GetMapping
    public List<CategoryEntity> getAll() {
        return new ArrayList<>(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable("id") Long id) {
        CategoryEntity category = categoryService.getCategoryById(id);

        return CategoryDTO.builder().id(category.getId()).category(category.getCategory())
                .description(category.getDescription()).build();
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

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByCategoryResponse>> getGamesByCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryGameService.getGamesByCategoryId(id).stream().map(g ->
                        GamesByCategoryResponse.builder()
                                .gameId(g.getId().getGameId())
                                .title(g.getGame().getTitle())
                                .build())
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
