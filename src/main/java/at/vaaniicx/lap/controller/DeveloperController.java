package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.developer.DeleteDeveloperException;
import at.vaaniicx.lap.mapper.developer.DeveloperResponseMapper;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.model.request.developer.RegisterDeveloperRequest;
import at.vaaniicx.lap.model.request.developer.UpdateDeveloperRequest;
import at.vaaniicx.lap.model.response.developer.DeveloperResponse;
import at.vaaniicx.lap.model.response.developer.GamesByDeveloperResponse;
import at.vaaniicx.lap.service.DeveloperService;
import at.vaaniicx.lap.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private final DeveloperService developerService;
    private final GameService gameService;

    @Autowired
    public DeveloperController(DeveloperService developerService, GameService gameService) {
        this.developerService = developerService;
        this.gameService = gameService;
    }

    /**
     * Liefert alle Entwickler.
     *
     * @return - Liste aller Entwickler
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DeveloperResponse>> getAllDeveloper() {

        List<DeveloperResponse> developerResponses = developerService.getAllDeveloper()
                .stream()
                .map(DeveloperResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(developerResponses);
    }

    /**
     * Liefert einen Entwickler zur ID.
     *
     * @param id - ID zum Entwickler
     * @return - Entwickler zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeveloperResponse> getDeveloperById(@PathVariable("id") Long id) {

        DeveloperResponse developerResponse =
                DeveloperResponseMapper.INSTANCE.entityToResponse(developerService.getDeveloperById(id));

        return ResponseEntity.ok(developerResponse);
    }

    /**
     * Aktualisiert einen Entwickler.
     *
     * @param request - Update-Request
     * @return - Aktualisierter Entwickler
     */
    @PostMapping("/update")
    public ResponseEntity<DeveloperEntity> updateDeveloper(@RequestBody @Validated UpdateDeveloperRequest request) {

        DeveloperEntity developer = developerService.getDeveloperById(request.getId());
        developer.setDeveloper(request.getDeveloper()); // Neue Werte setzen

        DeveloperEntity persistedDeveloper = developerService.save(developer); // Persistieren

        return ResponseEntity.ok(persistedDeveloper);
    }

    /**
     * Registriert einen neuen Entwickler.
     *
     * @param request - Registrierungs-Request
     * @return - Registrierter Entwickler
     */
    @PostMapping("/register")
    public ResponseEntity<DeveloperResponse> registerDeveloper(@RequestBody @Validated RegisterDeveloperRequest request) {

        DeveloperEntity developer = new DeveloperEntity();
        developer.setDeveloper(request.getDeveloper()); // Neue Werte setzen

        DeveloperEntity persistedDeveloper = developerService.save(developer); // Persistieren

        return ResponseEntity.ok(DeveloperResponseMapper.INSTANCE.entityToResponse(persistedDeveloper));
    }

    /**
     * Löscht einen Entwickler zur ID.
     *
     * @param id - ID zum Entwickler
     * @return - Response-Entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable("id") Long id) {

        boolean isDeveloperSet = gameService.getAllGamesOrderByTitle()
                .stream()
                .anyMatch(game -> game.getDeveloper().getId() == id);

        // Wurde der Entwickler einem Spiel zugewiesen?
        if (!isDeveloperSet) {
            developerService.deleteById(id); // Nicht in Verwendung, kann gelöscht werden
        } else {
            throw new DeleteDeveloperException(); // In Verwendung, kann nicht gelöscht werden
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Liefert alle Spiele eines Entwicklers.
     *
     * @param id - ID zum Entwickler
     * @return - Liste aller Spiele des Entwicklers
     */
    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByDeveloperResponse>> getGamesByDeveloper(@PathVariable("id") Long id) {

        List<GamesByDeveloperResponse> gamesByDeveloperResponses = gameService.getAllGamesByDeveloperId(id)
                .stream()
                .map(e -> new GamesByDeveloperResponse(e.getId(), e.getTitle(), e.getAgeRestriction()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(gamesByDeveloperResponses);
    }
}
