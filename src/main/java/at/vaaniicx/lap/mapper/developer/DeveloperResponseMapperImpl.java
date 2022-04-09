package at.vaaniicx.lap.mapper.developer;

import at.vaaniicx.lap.model.entity.DeveloperEntity;
import at.vaaniicx.lap.model.response.developer.DeveloperResponse;

public class DeveloperResponseMapperImpl implements DeveloperResponseMapper {

    @Override
    public DeveloperResponse entityToResponse(DeveloperEntity source) {
        if (source == null) {
            return null;
        }

        DeveloperResponse destination = new DeveloperResponse();
        destination.setId(source.getId());
        destination.setDeveloper(source.getDeveloper());

        return destination;
    }
}
