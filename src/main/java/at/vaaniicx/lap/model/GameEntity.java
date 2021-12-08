package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity(name = "game")
@Data
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "release_date", nullable = false)
    private Instant releaseDate;

    @Column(name = "original_price", nullable = false)
    private double originalPrice;

    @Column(name = "price")
    private double price;

    @Column(name = "savings")
    private double savings;

    // MAX LENGTH
    @Column(name = "system_requirements")
    private String systemRequirements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperEntity developer;

    @Column(name = "age_restriction", nullable = false)
    private byte ageRestriction;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<GamePictureEntity> gamePictures;
    // Category -> many to many
}
