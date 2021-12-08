package at.vaaniicx.lap.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;

@Entity(name = "gamePicture")
@Data
@NoArgsConstructor
public class GamePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "is_thumb", nullable = false)
    private boolean thumb;
}
