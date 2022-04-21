package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.game.GameNotFoundException;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Retourniert alle Spiele, sortiert nach Titel.
     *
     * @return - Liste aller Spiele
     */
    public List<GameEntity> getAllGamesOrderByTitle() {
        return gameRepository.findByOrderByTitleAsc();
    }

    /**
     * Retourniert das zur übergebenen ID zugehörige Spiel.
     *
     * @return - Spiel zur übergebenen ID
     */
    public GameEntity getGameById(Long id) {
        Optional<GameEntity> entity = gameRepository.findById(id);

        if (!entity.isPresent()) {
            throw new GameNotFoundException();
        }

        return entity.get();
    }

    /**
     * Retourniert alle zur übergebenen Veröffentlicher-ID zugehörigen Spiele.
     *
     * @return - Liste aller Spiele eines Veröffentlichers
     */
    public List<GameEntity> getAllGamesByPublisherId(Long id) {
        return gameRepository.findByPublisherId(id);
    }

    /**
     * Retourniert alle zur übergebenen Entwickler-ID zugehörigen Spiele.
     *
     * @return - Liste aller Spiele eines Entwicklers
     */
    public List<GameEntity> getAllGamesByDeveloperId(Long id) {
        return gameRepository.findByDeveloperId(id);
    }

    /**
     * Speichert das übergebene Entity-Objekt.
     *
     * @param entity - Zu persistierende Objekt
     * @return - Persistierte Objekt
     */
    public GameEntity save(GameEntity entity) {
        return gameRepository.save(entity);
    }
}
