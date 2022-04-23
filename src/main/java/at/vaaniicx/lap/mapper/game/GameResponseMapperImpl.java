package at.vaaniicx.lap.mapper.game;

import at.vaaniicx.lap.exception.gamepicture.GamePictureNotFoundException;
import at.vaaniicx.lap.mapper.category.CategoryResponseMapper;
import at.vaaniicx.lap.mapper.developer.DeveloperResponseMapper;
import at.vaaniicx.lap.mapper.publisher.PublisherResponseMapper;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.request.management.game.RegisterGameRequest;
import at.vaaniicx.lap.model.response.game.GameResponse;
import at.vaaniicx.lap.model.response.game.SlimGameResponse;
import at.vaaniicx.lap.util.ImageConversionHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameResponseMapperImpl implements GameResponseMapper {

    @Override
    public GameResponse entityToResponse(GameEntity source) {

        if (source == null) {
            return null;
        }

        GameResponse destination = new GameResponse();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setShortDescription(source.getShortDescription());
        destination.setReleaseDate(source.getReleaseDate());
        destination.setOriginalPrice(source.getOriginalPrice());
        destination.setPrice(source.getPrice());
        destination.setSavings(source.getSavings());
        destination.setSystemRequirements(source.getSystemRequirements());
        destination.setDeveloper(DeveloperResponseMapper.INSTANCE.entityToResponse(source.getDeveloper()));
        destination.setPublisher(PublisherResponseMapper.INSTANCE.entityToResponse(source.getPublisher()));
        destination.setGamePictures(getGamePicturesForGame(source.getGamePictures())
                .stream()
                .map(e -> ImageConversionHelper.blobToByteArray(e.getImage()))
                .collect(Collectors.toList())
        );
        destination.setAgeRestriction(source.getAgeRestriction());
        destination.setThumbnail(ImageConversionHelper.blobToByteArray(getThumbnailForGame(source.getGamePictures()).getImage()));
        destination.setCategories(source.getCategories()
                .stream()
                .map(CategoryResponseMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList())
        );

        return destination;
    }

    @Override
    public SlimGameResponse entityToSlimResponse(GameEntity source) {

        if (source == null) {
            return null;
        }

        SlimGameResponse destination = new SlimGameResponse();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setShortDescription(source.getShortDescription());
        destination.setReleaseDate(source.getReleaseDate());
        destination.setOriginalPrice(source.getOriginalPrice());
        destination.setPrice(source.getPrice());
        destination.setSavings(source.getSavings());
        destination.setSystemRequirements(source.getSystemRequirements());
        destination.setDeveloper(source.getDeveloper().getId());
        destination.setPublisher(source.getPublisher().getId());
        destination.setGamePictureIds(source.getGamePictures()
                .stream()
                .map(GamePictureEntity::getId)
                .collect(Collectors.toList())
        );
        destination.setAgeRestriction(source.getAgeRestriction());
        destination.setThumbnailId(getThumbnailForGame(source.getGamePictures()).getId());
        destination.setCategories(source.getCategories()
                .stream()
                .map(e -> e.getCategory().getId())
                .collect(Collectors.toList())
        );

        return destination;
    }

    @Override
    public GameEntity responseToEntity(RegisterGameRequest source) {

        if (source == null) {
            return null;
        }

        GameEntity destination = new GameEntity();
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setShortDescription(source.getShortDescription());
        destination.setReleaseDate(source.getReleaseDate());
        destination.setOriginalPrice(source.getOriginalPrice());
        destination.setPrice(source.getPrice());
        destination.setSavings(BigDecimal.valueOf(source.getOriginalPrice()).subtract(BigDecimal.valueOf(source.getPrice())).doubleValue());
        destination.setSystemRequirements(source.getSystemRequirements());
        destination.setAgeRestriction(source.getAgeRestriction());
        // TODO: Mapper fertig machen

        return destination;
    }

    private static List<GamePictureEntity> getGamePicturesForGame(Set<GamePictureEntity> gamePictures) {

        return gamePictures.stream().filter(e -> !e.isThumb()).collect(Collectors.toList());
    }

    private static GamePictureEntity getThumbnailForGame(Set<GamePictureEntity> gamePictures) {

        Optional<GamePictureEntity> thumbnail = gamePictures.stream().filter(GamePictureEntity::isThumb).findFirst();

        if (!thumbnail.isPresent()) {
            throw new GamePictureNotFoundException();
        }

        return thumbnail.get();
    }
}
