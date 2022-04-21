package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 8000, nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "salt", length = 24)
    private String salt; // wird aufgrund des BCrypt-Algorithmus nicht mehr gebraucht -> built-in Salts

    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @Column(name = "last_login")
    private Instant lastLogin;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @OneToOne
    @JoinColumn(name = "profile_picture_id", nullable = true)
    private ProfilePictureEntity profilePicture;
}