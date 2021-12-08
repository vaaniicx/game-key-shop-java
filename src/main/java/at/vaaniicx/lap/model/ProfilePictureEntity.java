package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "profilepicture")
@Data
@NoArgsConstructor
public class ProfilePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "picture", nullable = false)
    private byte[] picture;
}
