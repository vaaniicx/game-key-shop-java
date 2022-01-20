package at.vaaniicx.lap.model.entity;

import at.vaaniicx.lap.model.entity.pk.CategoryGamePk;
import lombok.*;

import javax.persistence.*;

@Entity(name = "category_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
