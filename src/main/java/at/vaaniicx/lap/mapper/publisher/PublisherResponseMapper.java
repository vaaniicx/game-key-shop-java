package at.vaaniicx.lap.mapper.publisher;

import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherResponseMapper {

    PublisherResponseMapper INSTANCE = Mappers.getMapper(PublisherResponseMapper.class);

    PublisherResponse entityToResponse(PublisherEntity source);
}
