package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "address")
@Data
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Column(name = "street", length = 10, nullable = false)
    private String street;

    @Column(name = "house_number", length = 10, nullable = false)
    private String houseNumber;

    @Column(name = "door", length = 10)
    private String door;

    @Column(name = "stair", length = 10)
    private String stair;
}
