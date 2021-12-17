package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "developer")
@Data
@NoArgsConstructor
public class DeveloperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "developer", length = 75, nullable = false)
    private String developer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "developer")
    private List<GameEntity> games;
}
