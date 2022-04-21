package at.vaaniicx.lap.mapper.category;

import at.vaaniicx.lap.model.entity.CategoryEntity;
import at.vaaniicx.lap.model.entity.CategoryGameEntity;
import at.vaaniicx.lap.model.response.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryResponseMapper {

    CategoryResponseMapper INSTANCE = Mappers.getMapper(CategoryResponseMapper.class);

    CategoryResponse entityToResponse(CategoryEntity source);

    CategoryResponse entityToResponse(CategoryGameEntity source);
}
