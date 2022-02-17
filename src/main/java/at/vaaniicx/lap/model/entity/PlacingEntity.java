package at.vaaniicx.lap.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity(name = "placing")
@Getter
@Setter
@NoArgsConstructor
public class PlacingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "placing_date", nullable = false)
    private Instant placingDate;

    @OneToMany(
            mappedBy = "placing",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PlacingDetailsEntity> games;
}
