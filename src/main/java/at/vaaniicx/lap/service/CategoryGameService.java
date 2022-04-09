package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.category.CategoryGameNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.repository.CategoryGameRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryGameService {

    private final CategoryGameRepository categoryGameRepository;

    public CategoryGameService(CategoryGameRepository categoryGameRepository) {
        this.categoryGameRepository = categoryGameRepository;
    }

    public List<CategoryGameEntity> getAllCategoryGames() {
        return categoryGameRepository.findAll();
    }

    public CategoryGameEntity getCategoryGameById(CategoryGamePk pk) {
        Optional<CategoryGameEntity> entity = categoryGameRepository.findById(pk);

        if (!entity.isPresent()) {
            throw new CategoryGameNotFoundException();
        }
        return entity.get();
    }

    public List<CategoryGameEntity> getCategoriesByGameId(Long id) {
        return categoryGameRepository.findByGameId(id);
    }

    public CategoryGameEntity save(CategoryGameEntity e) {
        return categoryGameRepository.save(e);
    }

    public List<CategoryGameEntity> getGamesByCategoryId(Long id) {
        return categoryGameRepository.findByCategoryId(id);
    }

    public void delete(CategoryGameEntity e) {
        categoryGameRepository.delete(e);
    }
}
