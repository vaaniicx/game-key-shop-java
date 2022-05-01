package at.vaaniicx.lap.controller;

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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DeveloperResponse>> getAllDeveloper() {

        List<DeveloperResponse> developerResponses = developerService.getAllDeveloper()
                .stream()
                .map(DeveloperResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(developerResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperResponse> getDeveloperById(@PathVariable("id") Long id) {

        DeveloperResponse developerResponse = DeveloperResponseMapper.INSTANCE.entityToResponse(developerService.getDeveloperById(id));

        return ResponseEntity.ok(developerResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<DeveloperEntity> updateDeveloper(@RequestBody @Validated UpdateDeveloperRequest request) {

        DeveloperEntity developer = developerService.getDeveloperById(request.getId());
        developer.setDeveloper(request.getDeveloper());

        DeveloperEntity persistedDeveloper = developerService.save(developer);

        return ResponseEntity.ok(persistedDeveloper);
    }

    @PostMapping("/register")
    public ResponseEntity<DeveloperResponse> registerDeveloper(@RequestBody @Validated RegisterDeveloperRequest request) {

        DeveloperEntity developer = new DeveloperEntity();
        developer.setDeveloper(request.getDeveloper());

        DeveloperEntity persistedDeveloper = developerService.save(developer);

        return ResponseEntity.ok(DeveloperResponseMapper.INSTANCE.entityToResponse(persistedDeveloper));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDeveloper(@PathVariable("id") Long id) {

        developerService.deleteById(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByDeveloperResponse>> getGamesByDeveloper(@PathVariable("id") Long id) {

        List<GamesByDeveloperResponse> gamesByDeveloperResponses = gameService.getAllGamesByDeveloperId(id)
                .stream()
                .map(e -> new GamesByDeveloperResponse(e.getId(), e.getTitle(), e.getAgeRestriction()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(gamesByDeveloperResponses);
    }
}
