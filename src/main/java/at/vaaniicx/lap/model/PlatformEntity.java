package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "platform")
@Data
@NoArgsConstructor
public class PlatformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "platform", unique = true, length = 50, nullable = false)
    private String platform;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    private List<GameEntity> games;
}
