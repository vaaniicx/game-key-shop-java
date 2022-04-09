package at.vaaniicx.lap.mapper.category;

import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.response.category.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryResponseMapper {

    CategoryResponse entityToResponse(CategoryEntity source);

    CategoryResponse entityToResponse(CategoryGameEntity source);
}
