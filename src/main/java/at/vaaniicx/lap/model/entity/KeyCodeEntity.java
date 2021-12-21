package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "key_code")
@Data
@NoArgsConstructor
public class KeyCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "key_code", length = 50, nullable = false)
    private String keyCode;

    @Column(name = "is_sold", nullable = false)
    private boolean sold;
}
