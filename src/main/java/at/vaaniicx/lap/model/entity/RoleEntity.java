package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role", length = 50, unique = true, nullable = false)
    private String role;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserEntity> users;
}
