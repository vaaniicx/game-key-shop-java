package at.vaaniicx.lap.mapper.category;

import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.response.category.CategoryResponse;

public class CategoryResponseMapperImpl implements CategoryResponseMapper {

    @Override
    public CategoryResponse entityToResponse(CategoryEntity source) {
        if (source == null) {
            return null;
        }

        CategoryResponse destination = new CategoryResponse();
        destination.setId(source.getId());
        destination.setCategory(source.getCategory());
        destination.setDescription(source.getDescription());

        return destination;
    }

    @Override
    public CategoryResponse entityToResponse(CategoryGameEntity source) {
        if (source == null) {
            return null;
        }

        CategoryResponse destination = new CategoryResponse();
        destination.setId(source.getCategory().getId());
        destination.setCategory(source.getCategory().getCategory());
        destination.setDescription(source.getCategory().getDescription());

        return destination;
    }
}
