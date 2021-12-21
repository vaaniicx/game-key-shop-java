package at.vaaniicx.lap.model.entity;

import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "placing_details")
@Data
@NoArgsConstructor
public class PlacingDetailsEntity {

    @EmbeddedId
    private PlacingDetailsPk id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("placingId")
    private PlacingEntity placing;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private GameEntity game;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "key_code_id", nullable = false)
    private KeyCodeEntity keyCode;

    @Column(name = "price", nullable = false)
    private double price;
}
