package at.vaaniicx.lap.mapper.publisher;

import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;

public class PublisherResponseMapperImpl implements PublisherResponseMapper {

    @Override
    public PublisherResponse entityToResponse(PublisherEntity source) {
        if (source == null) {
            return null;
        }

        PublisherResponse destination = new PublisherResponse();
        destination.setId(source.getId());
        destination.setPublisher(source.getPublisher());

        return destination;
    }
}
