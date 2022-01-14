package at.vaaniicx.lap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

import javax.persistence.*;

@Entity(name = "profile_picture")
@Data
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
