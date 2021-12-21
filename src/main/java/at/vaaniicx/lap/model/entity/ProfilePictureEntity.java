package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

import javax.persistence.*;

@Entity(name = "profilepicture")
@Data
@NoArgsConstructor
public class ProfilePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture", nullable = false)
    private Blob picture;
}
