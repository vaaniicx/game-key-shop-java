package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.key.GameFlatResponse;
import at.vaaniicx.lap.model.response.management.WarehouseEntranceDataResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private UserService userService;
    private GameService gameService;
    private KeyCodeService keyCodeService;

    @GetMapping("/game/flat/{id}")
    public GameFlatResponse getKeyManagementGameFlat(@PathVariable("id") Long gameId) {
        return new GameFlatResponse(gameId, gameService.getGameById(gameId).getTitle(),
                keyCodeService.getKeyCountByGameIdAndSold(gameId, true), keyCodeService.getKeyCountByGameIdAndSold(gameId, false));
    }

    @GetMapping("/game/key/{id}")
    public List<at.vaaniicx.lap.model.response.key.DataResponse> getKeyManagementData(@PathVariable("id") Long gameId) {
        List<at.vaaniicx.lap.model.response.key.DataResponse> ret = new ArrayList<>();

        keyCodeService.getAllKeyCodesByGameId(gameId).stream().filter(Objects::nonNull)
                .forEach(k -> {
                    UserEntity user = null;
                    if (k.getPerson() != null) {
                        user = userService.getUserByPersonId(k.getPerson().getId());
                    }

                    ret.add(new at.vaaniicx.lap.model.response.key.DataResponse(k.getId(), k.getKeyCode(), k.isSold(),
                            user != null ? user.getId() : null,
                            user != null ? user.getEmail() : null));
                });

        return ret;
    }

    @GetMapping("/warehouse/entrance")
    public List<WarehouseEntranceDataResponse> getWarehouseEntranceData() {
        List<WarehouseEntranceDataResponse> ret = new ArrayList<>();

        gameService.getAllGamesOrderByTitle().stream().filter(Objects::nonNull).forEach(g -> {
            Long amount = keyCodeService.getKeyCountByGameIdAndSold(g.getId(), false);
            ret.add(new WarehouseEntranceDataResponse(g.getId(), g.getTitle(), amount, g.getPrice()));
        });

        return ret;
    }
}
