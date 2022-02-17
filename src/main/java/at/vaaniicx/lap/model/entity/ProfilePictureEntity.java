package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "profile_picture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfilePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture", nullable = false)
    private Blob picture;
}
