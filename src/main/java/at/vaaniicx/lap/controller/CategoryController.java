package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.category.DeleteCategoryException;
import at.vaaniicx.lap.mapper.category.CategoryResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.request.UpdateCategoryRequest;
import at.vaaniicx.lap.model.request.category.RegisterCategoryRequest;
import at.vaaniicx.lap.model.response.category.CategoryResponse;
import at.vaaniicx.lap.model.response.category.GamesByCategoryResponse;
import at.vaaniicx.lap.service.CategoryGameService;
import at.vaaniicx.lap.service.CategoryService;
import io.jsonwebtoken.lang.Collections;
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

    /**
     * Liefert alle Kategorien.
     *
     * @return - Liste aller Kategorien
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        List<CategoryResponse> response = categoryService.getAllCategories()
                .stream()
                .map(CategoryResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    /**
     * Liefert eine Kategorie mittels ID.
     *
     * @param id - ID der Kategorie
     * @return - Kategorie zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long id) {

        CategoryEntity category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(CategoryResponseMapper.INSTANCE.entityToResponse(category));
    }

    /**
     * Aktualisiert eine Kategorie.
     *
     * @param request - Update-Request
     * @return - Aktualisierte Kategorie
     */
    @PostMapping("/update")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody @Validated UpdateCategoryRequest request) {

        CategoryEntity category = categoryService.getCategoryById(request.getId());
        // Neue Werte setzen
        category.setCategory(request.getCategory());
        category.setDescription(request.getDescription());

        CategoryEntity updatedCategory = categoryService.save(category); // Persistieren

        return ResponseEntity.ok(CategoryResponseMapper.INSTANCE.entityToResponse(updatedCategory));
    }

    /**
     * Registriert eine neue Kategorie.
     *
     * @param request - Registrierungs-Request
     * @return - Registrierte Kategorie
     */
    @PostMapping("/register")
    public ResponseEntity<CategoryResponse> registerCategory(@RequestBody @Validated RegisterCategoryRequest request) {

        CategoryEntity category = new CategoryEntity(); // Neues Kategorie-Objekt
        category.setCategory(request.getCategory()); // Werte setzen
        category.setDescription(request.getDescription());

        CategoryEntity persistedCategory = categoryService.save(category); // Persistieren

        return ResponseEntity.ok(CategoryResponseMapper.INSTANCE.entityToResponse(persistedCategory));
    }

    /**
     * Löscht eine Kategorie mittels ID.
     *
     * @param id - ID der Kategorie
     * @return - Response-Entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {

        List<CategoryGameEntity> games = categoryGameService.getGamesByCategoryId(id);

        // Wurde die Kategorie einem Spiel zugewiesen?
        if (Collections.isEmpty(games)) {
            categoryService.deleteById(id); // Nicht in Verwendung, kann gelöscht werden
        } else {
            throw new DeleteCategoryException(); // In Verwendung, kann nicht gelöscht werden
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Liefert alle Spiele, die die Kategorie besitzen.
     *
     * @param id - ID zur Kategorie
     * @return - Liste aller Spiele
     */
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