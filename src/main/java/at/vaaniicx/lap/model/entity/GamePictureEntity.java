package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "gamePicture")
@Data
@NoArgsConstructor
public class GamePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Column(name = "image", nullable = false)
    private Blob image;

    @Column(name = "is_thumb", nullable = false)
    private boolean thumb;
}