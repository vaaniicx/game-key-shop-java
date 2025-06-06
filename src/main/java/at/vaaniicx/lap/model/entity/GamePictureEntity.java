package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "gamePicture")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GamePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Column(name = "image", nullable = false)
    private Blob image;

    @Column(name = "is_thumb", nullable = false)
    private boolean thumb;
}