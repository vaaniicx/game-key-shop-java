package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "role")
@Data
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role", length = 50, unique = true, nullable = false)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserEntity> users;
}
