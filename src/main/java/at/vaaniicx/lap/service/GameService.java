package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.GameNotFoundException;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameEntity getGameById(Long id) {
        Optional<GameEntity> entity = gameRepository.findById(id);

        if (!entity.isPresent()) {
            throw new GameNotFoundException();
        }

        return entity.get();
    }

    public List<GameEntity> getAllGames() {
        return gameRepository.findAll();
    }

    public List<GameEntity> getAllGamesOrderByTitle() {
        return gameRepository.findByOrderByTitleAsc();
    }

    public GameEntity registerGame(GameEntity entity) {
        return gameRepository.save(entity);
    }
}
