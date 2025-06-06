package at.vaaniicx.lap.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "key_code")
@Getter
@Setter
@NoArgsConstructor
public class KeyCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Column(name = "key_code", length = 50, nullable = false)
    private String keyCode;

    @Column(name = "is_sold", nullable = false)
    private boolean sold;

    public KeyCodeEntity(GameEntity game, String keyCode, boolean sold) {
        this.game = game;
        this.keyCode = keyCode;
        this.sold = sold;
    }
}
