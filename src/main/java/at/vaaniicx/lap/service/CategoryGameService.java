package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.category.CategoryGameNotFoundException;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import at.vaaniicx.lap.repository.CategoryGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryGameService {

    private final CategoryGameRepository categoryGameRepository;

    @Autowired
    public CategoryGameService(CategoryGameRepository categoryGameRepository) {
        this.categoryGameRepository = categoryGameRepository;
    }

    /**
     * Retourniert alle Spiele, die die übergebene Kategorie besitzen.
     * Gesucht wird mittels Kategorie-ID.
     *
     * @param id - ID der Kategorie
     * @return - Liste aller Spiele der Kategorie
     */
    public List<CategoryGameEntity> getGamesByCategoryId(Long id) {
        return categoryGameRepository.findByCategoryId(id);
    }

    /**
     * Retourniert das zum übergebenen Schlüssel zugehörige CategoryGame-Objekt.
     *
     * @param pk - Zusammengesetzer Schlüssel/Primary Key
     * @return - Gefundene Objekt
     */
    public CategoryGameEntity getCategoryGameById(CategoryGamePk pk) {

        Optional<CategoryGameEntity> entity = categoryGameRepository.findById(pk);

        if (!entity.isPresent()) {
            throw new CategoryGameNotFoundException();
        }
        return entity.get();
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public CategoryGameEntity save(CategoryGameEntity entity) {
        return categoryGameRepository.save(entity);
    }

    /**
     * Löscht das übergebene Entity-Objekt.
     *
     * @param entity - Zu löschende Objekt
     */
    public void delete(CategoryGameEntity entity) {
        categoryGameRepository.delete(entity);
    }

    /**
     * Löscht alle übergebenen Entity-Objekte.
     *
     * @param entities - Zu löschende Objekte
     */
    public void deleteAll(Set<CategoryGameEntity> entities) {
        categoryGameRepository.deleteAll(entities);
    }
}
