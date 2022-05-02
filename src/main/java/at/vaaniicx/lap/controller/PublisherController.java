package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.exception.publisher.DeletePublisherException;
import at.vaaniicx.lap.mapper.publisher.PublisherResponseMapper;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.request.publisher.RegisterPublisherRequest;
import at.vaaniicx.lap.model.request.publisher.UpdatePublisherRequest;
import at.vaaniicx.lap.model.response.game.GamesByPublisherResponse;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;
import at.vaaniicx.lap.model.response.publisher.RegisterPublisherResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final  PublisherService publisherService;
    private final  GameService gameService;

    @Autowired
    public PublisherController(PublisherService publisherService, GameService gameService) {
        this.publisherService = publisherService;
        this.gameService = gameService;
    }

    /**
     * Liefert alle Publisher.
     *
     * @return - Liste aller Publisher
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PublisherResponse>> getAllPublisher() {

        List<PublisherResponse> publisherResponses = publisherService.getAllPublisher()
                .stream()
                .map(PublisherResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(publisherResponses);
    }

    /**
     * Liefert den Publisher zur ID.
     *
     * @param publisherId - ID zum Publisher
     * @return - Publisher zur ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getPublisherById(@PathVariable("id") Long publisherId) {

        PublisherEntity publisherById = publisherService.getPublisherById(publisherId);

        return ResponseEntity.ok(PublisherResponseMapper.INSTANCE.entityToResponse(publisherById));
    }

    /**
     * Aktualisiert einen Publisher.
     *
     * @param request - Update-Request
     * @return - Aktualisierter Publisher
     */
    @PostMapping("/update")
    public ResponseEntity<PublisherResponse> updatePublisher(@RequestBody @Validated UpdatePublisherRequest request) {

        PublisherEntity publisher = publisherService.getPublisherById(request.getId());
        publisher.setPublisher(request.getPublisher()); // Neue Werte setzen

        PublisherEntity persistedPublisher = publisherService.save(publisher); // Persistieren

        return ResponseEntity.ok(PublisherResponseMapper.INSTANCE.entityToResponse(persistedPublisher));
    }

    /**
     * Registriert einen Publisher.
     *
     * @param request - Registrierungs-Request
     * @return - Registrierter Publisher
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterPublisherResponse> registerPublisher(@RequestBody @Validated RegisterPublisherRequest request) {

        PublisherEntity publisher = new PublisherEntity(); // Erstellen
        publisher.setPublisher(request.getPublisher());

        PublisherEntity persistedPublisher = publisherService.save(publisher); // Persistieren

        return ResponseEntity.ok(new RegisterPublisherResponse(persistedPublisher.getId(), persistedPublisher.getPublisher()));
    }

    /**
     * Löscht einen Publisher zur ID.
     *
     * @param id - ID zum Publisher
     * @return - Response-Entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable("id") Long id) {

        boolean isPublisherSet = gameService.getAllGamesOrderByTitle()
                .stream()
                .anyMatch(game -> game.getPublisher().getId() == id);

        // Ist der Publisher einem Spiel zugeordnet?
        if (!isPublisherSet) {
            publisherService.deleteById(id); // Nicht in Verwendung, kann gelöscht werden
        } else {
            throw new DeletePublisherException(); // In Verwendung, kann nicht gelöscht werden
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Liefert alle Spiele zu einem Publisher.
     *
     * @param id - ID zum Publisher
     * @return - Liste aller Spiele eines Publishers
     */
    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByPublisherResponse>> getGamesByPublisher(@PathVariable("id") Long id) {

        List<GamesByPublisherResponse> gamesByPublisherResponses = gameService.getAllGamesByPublisherId(id)
                .stream()
                .map(e -> new GamesByPublisherResponse(e.getId(), e.getTitle(), e.getAgeRestriction()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(gamesByPublisherResponses);
    }
}
