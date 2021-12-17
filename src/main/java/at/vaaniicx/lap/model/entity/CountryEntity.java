package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "country")
@Data
@NoArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", unique = true, nullable = false)
    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<LocationEntity> locations;

}
