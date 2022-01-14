package at.vaaniicx.lap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postal", length = 10, nullable = false)
    private String postal;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "location",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AddressEntity> addresses;

    public LocationEntity(String postal, String location) {
        this.postal = postal;
        this.location = location;
    }
}
