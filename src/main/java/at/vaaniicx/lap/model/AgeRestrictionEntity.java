package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "ageRestriction")
@Data
@NoArgsConstructor
public class AgeRestrictionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "age", nullable = false)
    private byte age;

    @Column(name = "institution", nullable = false)
    private String institution;
}
