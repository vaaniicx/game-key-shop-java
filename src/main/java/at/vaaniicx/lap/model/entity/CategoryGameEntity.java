package at.vaaniicx.lap.model.entity;

import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "category_game")
@Data
@NoArgsConstructor
public class CategoryGameEntity {

    @EmbeddedId
    private CategoryGamePk id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private GameEntity game;
}
