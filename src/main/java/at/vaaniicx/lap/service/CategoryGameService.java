package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.category.CategoryGameNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.repository.CategoryGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryGameService {

    private final CategoryGameRepository categoryGameRepository;

    public CategoryGameService(CategoryGameRepository categoryGameRepository) {
        this.categoryGameRepository = categoryGameRepository;
    }

    public CategoryGameEntity save(CategoryGameEntity e) {
        return categoryGameRepository.save(e);
    }

    public void delete(CategoryGameEntity e) {
        categoryGameRepository.delete(e);
    }

    public List<CategoryGameEntity> getGamesByCategoryId(Long id) {
        return categoryGameRepository.findByCategoryId(id);
    }

    public CategoryGameEntity getCategoryGameById(CategoryGamePk pk) {
        Optional<CategoryGameEntity> entity = categoryGameRepository.findById(pk);

        if (!entity.isPresent()) {
            throw new CategoryGameNotFoundException();
        }
        return entity.get();
    }
}
