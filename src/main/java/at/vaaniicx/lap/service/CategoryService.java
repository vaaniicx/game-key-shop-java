package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.category.CategoryNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retourniert alle Kategorien.
     *
     * @return - Liste mit allen Kategorien
     */
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retourniert die zur übergebenen ID zugehörige Kategorie.
     *
     * @return - Kategorie zur übergebenen ID
     */
    public CategoryEntity getCategoryById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);

        if (!entity.isPresent()) {
            throw new CategoryNotFoundException();
        }

        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public CategoryEntity save(CategoryEntity entity) {
        return categoryRepository.save(entity);
    }

    /**
     * Löscht das Entity-Objekt zur übergebenen ID.
     *
     * @param id - ID des zu löschenden Objekt
     */
    public void deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException();
        }
    }
}
