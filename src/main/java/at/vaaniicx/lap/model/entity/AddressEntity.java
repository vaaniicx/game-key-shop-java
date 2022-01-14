package at.vaaniicx.lap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @Column(name = "house_number", length = 10, nullable = false)
    private String houseNumber;

    @Column(name = "door", length = 10)
    private String door;

    @Column(name = "stair", length = 10)
    private String stair;

    public AddressEntity(String street, String houseNumber, String door, String stair) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.door = door;
        this.stair = stair;
    }
}
