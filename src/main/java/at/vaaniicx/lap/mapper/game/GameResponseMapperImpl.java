package at.vaaniicx.lap.mapper.game;

import at.vaaniicx.lap.mapper.category.CategoryResponseMapper;
import at.vaaniicx.lap.mapper.developer.DeveloperResponseMapper;
import at.vaaniicx.lap.mapper.publisher.PublisherResponseMapper;
import at.vaaniicx.lap.model.entity.GameEntity;
import at.vaaniicx.lap.model.entity.GamePictureEntity;
import at.vaaniicx.lap.model.response.game.GameResponse;
import at.vaaniicx.lap.util.ImageConversionHelper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameResponseMapperImpl implements GameResponseMapper {

    private DeveloperResponseMapper developerMapper = Mappers.getMapper(DeveloperResponseMapper.class);
    private PublisherResponseMapper publisherMapper = Mappers.getMapper(PublisherResponseMapper.class);
    private CategoryResponseMapper categoryMapper = Mappers.getMapper(CategoryResponseMapper.class);

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
        destination.setDeveloper(developerMapper.entityToResponse(source.getDeveloper()));
        destination.setPublisher(publisherMapper.entityToResponse(source.getPublisher()));
        destination.setGamePictures(getGamePicturesForGame(source.getGamePictures()));
        destination.setAgeRestriction(source.getAgeRestriction());
        destination.setThumbnail(getThumbnailForGame(source.getGamePictures()));
        destination.setCategories(source.getCategories().stream().map(e -> categoryMapper.entityToResponse(e)).collect(Collectors.toList()));

        return destination;
    }

    private static List<byte[]> getGamePicturesForGame(Set<GamePictureEntity> gamePictures) {
        return gamePictures.stream()
                .filter(e -> !e.isThumb())
                .map(e -> ImageConversionHelper.blobToByteArray(e.getImage())).collect(Collectors.toList());
    }

    private static byte[] getThumbnailForGame(Set<GamePictureEntity> gamePictures) {
        Optional<GamePictureEntity> thumbnail = gamePictures.stream().filter(GamePictureEntity::isThumb).findFirst();
        return thumbnail.map(e -> ImageConversionHelper.blobToByteArray(e.getImage())).orElse(null);
    }
}
