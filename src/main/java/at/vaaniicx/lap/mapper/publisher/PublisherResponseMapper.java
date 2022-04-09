package at.vaaniicx.lap.mapper.publisher;

import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PublisherResponseMapper {

    PublisherResponse entityToResponse(PublisherEntity source);
}
