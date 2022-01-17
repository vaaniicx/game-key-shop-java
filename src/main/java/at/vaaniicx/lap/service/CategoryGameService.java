package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.CategoryGameNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.repository.CategoryGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryGameService {

    @Autowired
    private CategoryGameRepository repository;

    public List<CategoryGameEntity> getAllCategoryGames() {
        return repository.findAll();
    }

    public CategoryGameEntity getCategoryGameById(CategoryGamePk pk) {
        Optional<CategoryGameEntity> entity = repository.findById(pk);

        if (!entity.isPresent()) {
            throw new CategoryGameNotFoundException();
        }
        return entity.get();
    }

    public List<CategoryGameEntity> getCategoriesByGameId(Long id) {
        return repository.findByGameId(id);
    }

    public CategoryGameEntity save(CategoryGameEntity e) {
        return repository.save(e);
    }

    public List<CategoryGameEntity> getGamesByCategoryId(Long id) {
        return repository.findByCategoryId(id);
    }

    public void delete(CategoryGameEntity e) {
        repository.delete(e);
    }
}
