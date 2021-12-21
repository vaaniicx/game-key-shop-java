package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.GameDTO;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.service.GamePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;

@Component
public class GameMapper {

    @Autowired
    private GamePictureService gamePictureService;

    public GameDTO toDto(GameEntity entity) {
        return new GameDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getShortDescription(),
                entity.getReleaseDate(), entity.getOriginalPrice(), entity.getPrice(), entity.getSavings(),
                entity.getSystemRequirements(), entity.getDeveloper().getId(), entity.getAgeRestriction(),
                gamePictureService.getThumbPictureForGameId(entity.getId()) != null ?
                        blobToByteArray(gamePictureService.getThumbPictureForGameId(entity.getId()).getImage()) :
                        new byte[0]
                );
    }

    private byte[] blobToByteArray(Blob blob) {
        try {
            return blob.getBytes(1, ((int) blob.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}

