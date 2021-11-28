package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "profilepicture")
@Data
@NoArgsConstructor
public class ProfilePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "image", nullable = false)
    private Blob image;
}
