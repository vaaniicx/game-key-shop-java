package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.mapper.developer.DeveloperResponseMapper;
import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.model.request.management.developer.UpdateDeveloperRequest;
import at.vaaniicx.lap.model.response.developer.DeveloperResponse;
import at.vaaniicx.lap.model.response.developer.GamesByDeveloperResponse;
import at.vaaniicx.lap.service.DeveloperService;
import at.vaaniicx.lap.service.GameService;
import org.mapstruct.factory.Mappers;
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

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private GameService gameService;

    private DeveloperResponseMapper developerMapper = Mappers.getMapper(DeveloperResponseMapper.class);

    @GetMapping
    @ResponseBody
    public List<DeveloperResponse> getAll() {
        return developerService.getAllDeveloper().stream().map(developerMapper::entityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable("id") Long developerId) {
        return developerMapper.entityToResponse(developerService.getDeveloperById(developerId));
    }

    @PostMapping("/update")
    public DeveloperEntity updateDeveloper(@RequestBody @Validated UpdateDeveloperRequest request) {
        DeveloperEntity developer = developerService.getDeveloperById(request.getId());
        developer.setDeveloper(request.getDeveloper());

        return developerService.updateDeveloper(developer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDeveloper(@PathVariable("id") Long id) {
        developerService.deleteDeveloperById(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<List<GamesByDeveloperResponse>> getGamesByDeveloperId(@PathVariable("id") Long id) {
        List<GamesByDeveloperResponse> ret = gameService.getAllGamesByDeveloperId(id).stream().map(g ->
                        GamesByDeveloperResponse.builder()
                                .gameId(g.getId())
                                .title(g.getTitle())
                                .ageRestriction(g.getAgeRestriction())
                                .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
