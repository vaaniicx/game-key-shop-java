package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.entity.UserEntity;
import at.vaaniicx.lap.model.response.game.SlimGameResponse;
import at.vaaniicx.lap.model.response.key.KeyResponse;
import at.vaaniicx.lap.model.response.management.WarehouseEntranceDataResponse;
import at.vaaniicx.lap.service.GameService;
import at.vaaniicx.lap.service.KeyCodeService;
import at.vaaniicx.lap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final GameService gameService;
    private final KeyCodeService keyCodeService;

    @Autowired
    public ManagementController(GameService gameService, KeyCodeService keyCodeService) {
        this.gameService = gameService;
        this.keyCodeService = keyCodeService;
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
