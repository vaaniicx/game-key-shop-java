package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.repository.CategoryGameRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryGameService {

    private final CategoryGameRepository categoryGameRepository;

    public CategoryGameService(CategoryGameRepository categoryGameRepository) {
        this.categoryGameRepository = categoryGameRepository;
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
