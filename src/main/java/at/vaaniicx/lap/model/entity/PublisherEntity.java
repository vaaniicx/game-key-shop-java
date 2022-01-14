package at.vaaniicx.lap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "publisher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publisher", length = 75, unique = true, nullable = false)
    private String publisher;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "publisher"
    )
    private List<GameEntity> games;
}
