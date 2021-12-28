package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.PublisherDTO;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {

    public static PublisherDTO toDto(PublisherEntity e) {
        return new PublisherDTO(e.getId(), e.getPublisher());
    }
}
