package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "region")
@Data
@NoArgsConstructor
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "region", unique = true, nullable = false)
    private String region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private List<GameEntity> games;
}
