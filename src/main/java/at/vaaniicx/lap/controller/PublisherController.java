package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.publisher.PublisherResponseMapper;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.request.management.publisher.RegisterPublisherRequest;
import at.vaaniicx.lap.model.request.management.publisher.UpdatePublisherRequest;
import at.vaaniicx.lap.model.response.game.GamesByPublisherResponse;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;
import at.vaaniicx.lap.model.response.publisher.RegisterPublisherResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PublisherResponse>> getAllPublisher() {

        List<PublisherResponse> publisherResponses = publisherService.getAllPublisher()
                .stream()
                .map(PublisherResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(publisherResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getPublisherById(@PathVariable("id") Long publisherId) {

        PublisherEntity publisherById = publisherService.getPublisherById(publisherId);

        return ResponseEntity.ok(PublisherResponseMapper.INSTANCE.entityToResponse(publisherById));
    }

    @PostMapping("/update")
    public ResponseEntity<PublisherResponse> updatePublisher(@RequestBody @Validated UpdatePublisherRequest request) {

        PublisherEntity publisher = publisherService.getPublisherById(request.getId());
        publisher.setPublisher(request.getPublisher());

        PublisherEntity persistedPublisher = publisherService.save(publisher);

        return ResponseEntity.ok(PublisherResponseMapper.INSTANCE.entityToResponse(persistedPublisher));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterPublisherResponse> registerPublisher(@RequestBody @Validated RegisterPublisherRequest request) {

        PublisherEntity publisher = new PublisherEntity();
        publisher.setPublisher(request.getPublisher());

        PublisherEntity persistedPublisher = publisherService.save(publisher);

        return ResponseEntity.ok(new RegisterPublisherResponse(persistedPublisher.getId(), persistedPublisher.getPublisher()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePublisher(@PathVariable("id") Long id) {

        publisherService.deleteById(id);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByPublisherResponse>> getGamesByPublisher(@PathVariable("id") Long id) {

        List<GamesByPublisherResponse> gamesByPublisherResponses = gameService.getAllGamesByPublisherId(id)
                .stream()
                .map(e -> new GamesByPublisherResponse(e.getId(), e.getTitle(), e.getAgeRestriction()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(gamesByPublisherResponses);
    }
}
