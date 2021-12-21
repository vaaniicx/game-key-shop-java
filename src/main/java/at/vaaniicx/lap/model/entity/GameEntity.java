package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "game")
@Data
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 8000, nullable = false)
    private String description;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @Column(name = "original_price", nullable = false)
    private double originalPrice;

    @Column(name = "price")
    private double price;

    @Column(name = "savings")
    private double savings;

    @Column(name = "system_requirements", length = 8000, nullable = false)
    private String systemRequirements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private PublisherEntity publisher;

    @Column(name = "age_restriction", nullable = false)
    private byte ageRestriction;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<GamePictureEntity> gamePictures;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<KeyCodeEntity> keys;

    @ManyToMany(mappedBy = "games")
    private List<CategoryEntity> categories;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ShoppingCartGameEntity> shoppingCarts;


    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlacingDetailsEntity> placings;
}
