package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.publisher.PublisherResponseMapper;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.request.management.publisher.UpdatePublisherRequest;
import at.vaaniicx.lap.model.response.game.GamesByPublisherResponse;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.PublisherService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final GameService gameService;
    private final PublisherResponseMapper publisherMapper;

    public PublisherController(PublisherService publisherService, GameService gameService) {
        this.publisherService = publisherService;
        this.gameService = gameService;
        this.publisherMapper = Mappers.getMapper(PublisherResponseMapper.class);
    }

    @GetMapping
    @ResponseBody
    public List<PublisherResponse> getAll() {
        return publisherService.getAllPublisher().stream().map(publisherMapper::entityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PublisherResponse getById(@PathVariable("id") Long publisherId) {
        return publisherMapper.entityToResponse(publisherService.getPublisherById(publisherId));
    }

    @PostMapping("/update")
    public PublisherEntity updatePublisher(@RequestBody @Validated UpdatePublisherRequest request) {
        PublisherEntity publisher = publisherService.getPublisherById(request.getId());

        publisher.setPublisher(request.getPublisher());

        return publisherService.updatePublisher(publisher);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisherById(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByPublisherResponse>> getGamesByPublisher(@PathVariable("id") Long id) {
        List<GamesByPublisherResponse> ret = gameService.getAllGamesByPublisherId(id).stream().map(g ->
                GamesByPublisherResponse.builder()
                        .gameId(g.getId())
                        .title(g.getTitle())
                        .ageRestriction(g.getAgeRestriction())
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
