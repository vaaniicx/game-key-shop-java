package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "developer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "developer", length = 75, nullable = false)
    private String developer;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "developer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GameEntity> games;
}
