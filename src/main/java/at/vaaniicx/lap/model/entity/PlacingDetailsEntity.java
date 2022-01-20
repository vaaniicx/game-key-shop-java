package at.vaaniicx.lap.model.entity;

import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "placing_details")
@Getter
@Setter
@NoArgsConstructor
public class PlacingDetailsEntity {

    @EmbeddedId
    private PlacingDetailsPk id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("placingId")
    private PlacingEntity placing;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private KeyCodeEntity keyCode;

    @Column(name = "price", nullable = false)
    private double price;
}
