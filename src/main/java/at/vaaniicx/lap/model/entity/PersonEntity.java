package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "person")
@Data
@NoArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    // TODO: Beziehung auf Many to One ändern (eine Adresse kann mehrere Personen haben)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<PlacingEntity> placings;

    public PersonEntity(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
