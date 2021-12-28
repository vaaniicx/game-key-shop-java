package at.vaaniicx.lap.model.mapper;

import at.vaaniicx.lap.model.dto.CategoryDTO;
import at.vaaniicx.lap.model.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static CategoryDTO toDto(CategoryEntity e) {
        return new CategoryDTO(e.getId(), e.getCategory());
    }
}
