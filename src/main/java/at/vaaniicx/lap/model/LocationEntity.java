package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "location")
@Data
@NoArgsConstructor
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "postal", length = 10, nullable = false)
    private String postal;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private List<AddressEntity> addresses;
}
