package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "game")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
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
    @ToString.Exclude
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "publisher_id", nullable = false)
    private PublisherEntity publisher;

    @Column(name = "age_restriction", nullable = false)
    private byte ageRestriction;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @ToString.Exclude
    private List<GamePictureEntity> gamePictures;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @ToString.Exclude
    private List<KeyCodeEntity> keys;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<CategoryGameEntity> categories;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<ShoppingCartGameEntity> shoppingCarts;
}
