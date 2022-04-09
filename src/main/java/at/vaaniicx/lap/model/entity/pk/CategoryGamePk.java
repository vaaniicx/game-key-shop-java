package at.vaaniicx.lap.model.entity.pk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryGamePk implements Serializable {

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "game_id")
    private Long gameId;
}
