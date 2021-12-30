package at.vaaniicx.lap.controller;

import at.vaaniicx.lap.model.response.WarehouseEntranceDataResponse;
import at.vaaniicx.lap.repository.GameRepository;
import at.vaaniicx.lap.repository.KeyCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private KeyCodeRepository keyCodeRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/{id}")
    public Long getKeyAvailCountByGameId(@PathVariable Long id) {
        return keyCodeRepository.countByGameIdAndSold(id, false);
    }

    @GetMapping("/entrance")
    public List<WarehouseEntranceDataResponse> getWarehouseEntranceData() {
        List<WarehouseEntranceDataResponse> ret = new ArrayList<>();

        gameRepository.findAll().stream().filter(Objects::nonNull).forEach(g -> {
            Long amount = keyCodeRepository.countByGameIdAndSold(g.getId(), false);
            ret.add(new WarehouseEntranceDataResponse(g.getId(), g.getTitle(), amount, g.getPrice()));
        });

        return ret;
    }
}
