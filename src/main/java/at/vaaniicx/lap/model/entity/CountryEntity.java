package at.vaaniicx.lap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", length = 50, unique = true, nullable = false)
    private String country;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "country",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LocationEntity> locations;
}
